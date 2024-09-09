/**
 *  reply.js
 */
let replyer = 'user01'
let bno = 148;			// 여기선 명시적으로 user01이 148번 게시글에 댓글 단다고 하드코딩 함.


// 댓글등록 버튼 이벤트
document.querySelector('#addReply').addEventListener('click', function() {
	let reply = document.querySelector('#reply').value;
	
	const delHtp = new XMLHttpRequest();
	delHtp.open('get', '/addReplys.do?replyer=' + replyer + '&bno=' + bno + '&reply=' + reply);
	delHtp.send();
	delHtp.onload = function() {
		let result = JSON.parse(delHtp.responseText);
		console.log(result);	// retCode, retVal =>{}
		let tr = makeRow(result.retVal)
		/*document.document.querySelector('.list').appendChild	// TODO*/
	}
});

/*
// 댓글등록 함수 실행
function addReplyFunc(e) {
	console.log("댓글부대 호출");
	let reply = document.querySelector('#reply').value;
	
	if(reply == "") {
		alert("내용을 입력해주세요.")
	} else {
		const delHtp = new XMLHttpRequest();
		delHtp.open('get', '/removeReplys.do?replyer=' + replyer + '&bno=' + bno + '&reply=' + reply);
		delHtp.send();
		delHtp.onload = function() {
			let result = JSON.parse(delHtp.responseText);
			
			if (result.retCode == 'OK') {
				alert(replyer +'님의 댓글이 ' + bno + '번 게시글에 정상적으로 입력되었습니다");
			} else {
				alert("처리 중 예외");
			}
		}
	} // 빈칸검증 if end
}
*/




const xhtp = new XMLHttpRequest; 	// 객체 생성

xhtp.open('get', 'replyList.do?bno=148');			// 서버 페이지 지정
xhtp.send();
xhtp.onload = function() {
	console.log(xhtp.responseText);
	let result = JSON.parse(xhtp.responseText);	// json -> 객체
	console.log(result);
	result.forEach(reply => {
		document.querySelector('.list').appendChild(makeRow(reply));
	});
}

let fields = ['replyNo', 'reply', 'replyer', 'replyDate'];

//== 댓글 정보 -> tr > td * 4 생성 및 변환 ==//
function makeRow(reply = {}) {
	let tr = document.createElement('tr');
	
	// checkbox 생성
	let td = document.createElement("td");
	
	let chkbox = document.createElement('input');
	chkbox.setAttribute('type', 'checkbox');
	td.appendChild(chkbox);
	tr.appendChild(td);

	// td 생성
	fields.forEach(field => {
		let td = document.createElement('td');
		td.innerHTML = reply[field];
		tr.appendChild(td);
	})
	
	// 삭제 버튼
	let btn = document.createElement('button');
	btn.innerHTML = '삭제';
	btn.addEventListener('click', deleteRowFnc);
	
	let td2 = document.createElement("id");
	td2.appendChild(btn);
	tr.appendChild(td2);				// <tr>td...<tr>
	
	return tr;
}


//== 실제로 삭체 처리할 함수 ==//
function deleteRowFnc(e) {
	console.log(e.target.parentElement.parentElement.firstChild.innerHTML);
	let rno = e.target.parentElement.parentElement.firstChild.innerHTML;
	console.log("부모-부모-자식요소: ")
	console.log(e.target.parentElement.parentElement.children[1].innerHTML);
	
	const delHtp = new XMLHttpRequest();
	delHtp.open('get', 'removeReply.do?rno=' + rno);	// 컨트롤 지정
	delHtp.send();
	delHtp.onload = function() {
		let result = JSON.parse(delHtp.responseText);
		if (result.retCode == "OK") {
			e.target.parentElement.parentElement.remove();	// tr을 삭제
		} else if (result.retCode == "NG") {
			alert('알 수 없는 예외 발생');
		} else {
			alert("잘못된 반환 코드");
		}
	}
}

/*let checkAllBox = document.getElementById('#checkAllBox');

function checkAll() {
	if (this.checked) {
		console.log("Dddddddd");		
	} else {
		console.log("false!");
	}
}*/

// 전체선택 체크박스 이벤트
document.querySelector('thead input[type="checkbox"]').addEventListener('change', function (e) {
	document.querySelectorAll('tbody input[type="checkbox"').forEach(item => {
		item.checked = this.checked;
	});
})



// 선택삭제 이벤트
document.querySelector('#delChecked').addEventListener('click', delCheckedFunc1());


// 선택삭제 함수 실행
function delCheckedFunc1(e) {
	// ?rno=21&rno=22&rno23
	let params = "";
	document.querySelectorAll('.list input[type="checkbox"]:checked').forEach(item => {
		let rno = item.parentElement.nextElementSibling.innerHTML;
		params += "rno=" + rno;
	})
	
	const delHtp = new XMLHttpRequest();
	delHtp.open('get', '/removeReplys.do?' + params);
	delHtp.send();
	delHtp.onload = function() {
		let result = JSON.parse(delHtp.responseText);
		
		if (result.retCode == 'OK') {
			document.querySelectorAll('.list input[type="checkbox"]:checked').forEach(item => {
				item.parentElement.parentElement.remove();
			})
			alert("지움ㅇㅇ")
		} else {
			alert("처리 중 예외");
		}
		
	}
	
}



// 선택삭제 이벤트(반복 활용)
/*
document.querySelector('#delChecked').addEventListener('click', function(e) {
	document.querySelectorAll('.list input[type="checkbox"]:checked').forEach(item => {
		let rno = item.parentElement.nextElementSibling.innerHTML;
		
		const delHtp = new XMLHttpRequest();
		delHtp.open('get', 'removeReply.do?rno=' + rno);	// 컨트롤 지정
		delHtp.send();
		delHtp.onload = function() {
			let result = JSON.parse(delHtp.responseText);
			if (result.retCode == "OK") {
				item.target.parentElement.parentElement.remove();
			} else if (result.retCode == "NG") {
				alert('알 수 없는 예외 발생');
			} else {
				alert("잘못된 반환 코드");
			}
		}
	})
})
*/