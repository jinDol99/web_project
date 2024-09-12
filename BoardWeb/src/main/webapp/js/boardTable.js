/**
 * boardTable.js
 */
new DataTable('#example', {
	ajax: 'replyTable.do?bno=' + bno,

 	columnDefs: [		// [11-6] 테이블에 특정 열을 만들어 새로운 값을 삽입
        {
            render: (data, type, row) => "<button onClick='deleteRow(" + row.replyNo + ")'>삭제</button>",
            targets: 4	// n번 인덱스의 열에 위의 렌더린된 내용을 추가함
        },
    ],	
	
	columns: [
		{ data: 'replyNo' },
		{ data: 'reply' },
		{ data: 'replyer' },
		{ 
			data: 'replyDate',
			render: function (data) {
				return data;
			}
			
		}
	],
	lengthMenu: [				// [11-2] 한 번에 보여줄 행의 개수 옵션 설정
		[5, 10, 20, -1],
		[5, 10, 20, 'All']
	],
	order: {					// [11-3] 초기값으로 정렬을 어떻게 할것인지 설정
		idx: 0,					// 마찬가지로 배열이 아닌 객체 형식으로 사용 가능
		dir: 'desc'
	},
});

const table = new DataTable('#example');



//===========================//
//== [11-5] 삭제 기능 구현 ==//
let delNum;

table.on('click', 'tbody tr', (e) => {
	delNum = e.currentTarget.firstChild.innerText;
    let classList = e.currentTarget.classList;
 
    if (classList.contains('selected')) {
        classList.remove('selected');
    }
    else {
        table.rows('.selected').nodes().each((row) => row.classList.remove('selected'));
        classList.add('selected');
    }
});
 
document.querySelector('#delReply').addEventListener('click', function () {
	if(delNum == undefined) {
		alert('삭제할 댓글을 선택해주세요')
	} else {
		fetch('removeReply.do?rno=' + delNum)
		.then(resolve => resolve.json())
		.then( function(result) {
			console.log("▼▼ [boardTable.js] removeReply.do - result ▼▼");
			console.log(result)
			
			table.row('.selected').remove().draw(false);
			alert("삭제완");
		})
		.catch( function(err) {
			alert("댓글 삭제 오류");
			console.log("▼▼ [boardTable.js] removeReply.do - err ▼▼");
			console.log(err);
		})
	} // end of if-else
});

//== [11-5] 삭제 기능 구현 끝 ==//
//==============================//




//=====================================//
//== [11-6] 행 속 삭제버튼 기능 구현 ==//
function deleteRow(row) {	
	delNum = row
	
	fetch('removeReply.do?rno=' + delNum)
		.then(resolve => resolve.json())
		.then( function(result) {
			console.log("▼▼ [boardTable.js] removeReply.do - result ▼▼");
			console.log(result)
			
			table.row('.selected').remove().draw(false);		// DataTable에서 행을 삭제하는 코드는 왼쪽과 같음.
			alert("삭제 완료");									// 좀 더 상세히 말하면, <tr>(행) 중에서 클래스 속성값이
																// "selected"가 존재하는 <tr>만 삭제(.remove())하는 원리임.
																// 여기서 "selected"는 특정 행에서 마우스 클릭 이벤트가 발생하면
																// 자동으로 tr의 class 속성에 append되게 됨.   
																
																// 그런데, 우리가 맨든 <button>요소는 <tr> <td> 안에 들어가 있음.
																// button을 클릭하면 onclick 이벤트가 발생하는 것은 물론,
																// 상위 요소(<tr>)에도 마우스 클릭 이벤트가 동시에 들어가기 때문에
																// ".selected"를 기준으로 .remove() 처리해도 의외로 문제가 없는 것임.
																
																// 만약 부모요소에 이벤트가 가는 것이 싫다고 한다면
																// .stopPropagation() 함수를 이용하면 됨! (아주 가끔 쓰일거임)
		})
		.catch( function(err) {
			alert("댓글 삭제 오류");
			console.log("▼▼ [boardTable.js] removeReply.do - err ▼▼");
			console.log(err);
		})
}
//== [11-6] 삭제버튼 기능 구현 끝 ==//
//==================================//



/// [11-1] addReply에 클릭
/// repluNo: 111, reply: test, replyer: user01, replyDate: 2023.11.11
document.querySelector('#addReply').addEventListener('click', addNewRow);

/*
function addNewRow() {		// 테이블에 값을 추가. 실제 DB에는 적용 X
	table.row
		.add({				// [주의] API 문서에서는 배열( [] )에 담아서 값을 넘기라고 했는데
			replyNo: 111,	// 실제로는 객체( {} ) 형식으로 담아 값을 넘겨야 잘 됨.
			reply: 'test',
			replyer: 'user01',
			replyDate: 'Sep 11, 2023, 4:05:42 PM'
		})
	.draw(false);
}
*/


//== [11-4] 실제 DB에 값을 추가하고 테이블에도 행을 추가하기 ==//

function addNewRow() {
	let reply = document.querySelector("#reply").value;
	
	fetch("addReply.do", {
		method: 'post',
		headers: {'Content-Type': 'application/x-www-form-urlencoded'},
		body: 'bno=' + bno + '&reply=' + reply + '&replyer=' + writer
		})
	.then(resolve => resolve.json())
	.then( function(result) {
		console.log("▼▼ [boardTable.js] addReply.do - result ▼▼");
		console.log(result)
		
		table.row
		.add({
			replyNo: result.retVal.boardNum,
			reply: result.retVal.reply,
			replyer: result.retVal.replyer,
			replyDate: new Date()
		})
		.draw(false);
		
	})
	.catch( function(err) {
		Swal.fire("댓글 등록 실패!");
		console.log("▼▼ [boardTable.js] addReply.do - err ▼▼");
		console.log(err);
	})
}



/// 날짜포맷 : yyyy-MM-dd HH:mm:ss 반환하는 메소드를 Date 객체 추가
function convDateFormat(date) {
	let yyyy = date.getFullYear();
	let MM = ('0' + (date.getMonth()-1)).slice(-2);
	let dd = ('0' + date.getDate()).slice(-2);
	
	let hh = ('0' + date.getHours()).slice(-2);
	let mm = ('0' + date.getMinutes()).slice(-2);
	let ss = ('0' + date.getSeconds()).slice(-2);
	
	return yyyy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss; 
}
















