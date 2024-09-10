/**
 *  replyService.js
 * 	[6-2] 새로운 JS 생성. 여기서는 댓글과 관련된 모든 로직이 담김
 */


 let fields = ['replyNo', 'reply', 'replyer', 'replyDate'];
 
 
 ///== ReplyVO 값을 tr 생성해주는 함수 ==///
// 댓글정보 -> tr>td*4 생성 반환.
function makeRow(reply = {}) {	// 매개변수로 reply 객체(`reply = {}`)를 활용함.
	console.log('================ reply ================')
	console.log(reply)
	let tr = document.createElement('tr');
	tr.setAttribute('data-row', reply.replyNo);		// [6-5-a] tr(한 행)에 data-row 속성을 넣음.
													// 속성값으로는 매개변수 reply 객체의 댓글번호(replyNo)를 활용
	
	// 체크박스 생성.
	let btn = document.createElement('input'); //
	btn.setAttribute('type', 'checkbox');

	let td = document.createElement('td'); // <td><button>삭제</button></td>
	td.appendChild(btn);
	tr.appendChild(td); // <tr>td.....<td><button>삭제</button></td></tr>
	
	// td생성.
	fields.forEach(field => {
		let td = document.createElement('td');
		
		if (field == 'replyDate') {					// [6-8-a] 날짜포맷 변경함수를 만들었으니 써먹어야지ㅇㅇ
			let today = new Date(reply[field]);		// 반복문 중 "replyDate" 필드값을 가져와 td에 넣으려고 하는 경우
			td.innerHTML = today.dateFormat();		// 값을 Date 객체에 넣은 후, 이를 변환함수를 적용시킨 뒤 innerHTML 넣음. 
		} else {									
			td.innerHTML = reply[field];			// 그 외의 경우에는 그냥 HTML에 값 그대로 넣음.
			
		}
		tr.appendChild(td);
	})

	// 삭제버튼.
	btn = document.createElement('button'); // <button>삭제</button>
	btn.innerHTML = '삭제';
	btn.addEventListener('click', deleteRowFnc);

	td = document.createElement('td'); // <td><button>삭제</button></td>
	td.appendChild(btn);
	tr.appendChild(td); // <tr>td.....<td><button>삭제</button></td></tr>

	return tr;
}



//== [6-8] 날짜 포맷 변경 함수 ==//
/// 날짜포맷 : yyyy-MM-dd HH:mm:ss 반환하는 메소드를 Date 객체 추가
Date.prototype.dateFormat = function() {
	let yyyy = this.getFullYear();						// 2024
	let MM = ('0' + (this.getMonth()-1)).slice(-2);		// 07, 09, 10(010인데 뒤의 두 자리 자름), month는 0부터 시작?해서 -1함
	let dd = ('0' + this.getDate()).slice(-2);			// 09, 31(031인데 위의 두 자리 자름)
	
	let hh = ('0' + this.getHours()).slice(-2);			// 이하 설명 동일
	let mm = ('0' + this.getMinutes()).slice(-2);
	let ss = ('0' + this.getSeconds()).slice(-2);
	
	return yyyy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss; 
}



///== [6-9] 서비스 메소드를 통해서 ajax 기능을 실행 ==///
/// 1. 목록, 2. 삭제, 2. 추가, 4. ...
const svc = {
	replyList: function(bno=1, successCallback, errorCallback) {	// 매개변수로 1개의 값(bno), 2개의 함수(callback)를 전달
		fetch('replyList.do?bno=' + bno)
		.then(resolve => resolve.json())
		.then(successCallback)				// 통신을 일단 한 뒤, 실행결과에 대해선 호출된 곳으로 다시 건내준다는 의미에서
		.catch(errorCallback)				// success"Callback"과 error"Callback"을 사용함
	},
	
	// [7-4-a] row 삭제를 위해 댓글을 삭제하는 메소드 구현
	removeReply(rno=1, successCallback, errorCallback) {		// 함수를 정의할 때 위와 같이 function 키워드를 써도 되지만
																// 함수명 뒤 곧바로 중괄호를 열어도 됨
																
		fetch('removeReply.do?rno=' + rno)		// 매개값과 .do 컨트롤명 제외하면 위와 동일
		.then(resolve => resolve.json())
		.then(successCallback)
		.catch(errorCallback)
	},
	
	// [7-5-a] "댓글 등록" 버튼에 이벤트 설정을 위해 댓글 INSERT 하는 메소드 구현
	addReply(param = {bno, reply, replyer}, successCallback, errorCallback) {
		fetch('addReply.do', {
			method: 'post',
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			body: 'bno=' + param.bno + '&reply=' + param.reply + '&replyer=' + param.replyer
		})
		.then(resolve => resolve.json())
		.then(successCallback)
		.catch(errorCallback)
	}
	
}














