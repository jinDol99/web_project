/**
 * [7-1] Board
 *  replyBoard.js
 *  replyService에 정의된 메소드를 통해서 기능 실행
 */
let pagination;

// DOM 요소(쉽게 말해 HTML 코드들)를 다 읽어들인 다음에 JS 코드 실행
document.addEventListener("DOMContentLoaded", function(e) {

	//====================================		// 이벤트와 관련된 함수처럼 종류별로 메소드들을 따로 모아두는 편
	//		이벤트(댓글등록)
	//====================================

	//== [7-6] 댓글 등록 이벤트핸들러 ==//
	/// bno, replyer, reply
	document.querySelector('#addReply').addEventListener('click', addReplyFunc);


	// [9-9] 페이지 링크 클릭 이벤트 핸들러
	document.querySelectorAll('ul.pagination a').forEach(aTag => {
		aTag.addEventListener('click', showReplyListFunc)
	})

	//====================================		// 이벤트와 관련된 함수처럼 종류별로 메소드들을 따로 모아두는 편
	//		이벤트 끝
	//====================================


	pagination = document.querySelector('ul.pagination');

	showReplyListAndPagingList();	// 댓글 갱신
	

})



// 댓글 리스트 갱신(리스트 출력 후 페이징 처리) 함수
function showReplyListAndPagingList() {
	//== [7-2] 댓글 목록 그리기 ==//
	svc.replyList(				// replyService에서 replyList(댓글내역 가져오기) 메서드 실행한 결과를 관련 요청 및 응답 매개변수와 함께 호출
		// 매개변수 첫번째: 게시글 번호, 댓글 페이지 정보
		{ bno, page },

		// 매개변수(함수객체) 두번째: 서버 호출에 성공했을 때의 실행 함수
		function(result) {
			console.log(result);
			
			document.querySelectorAll('div.content li').forEach((li, idx) => {
				if (idx > 2) {	// 제목열과 <hr> 열은 지우면 안됨
					li.remove();
				}
			});
			
			result.forEach(reply => {
				document.querySelector('div.content ul').appendChild(makeLi(reply));
			})
			showPagingListFunc();
		},

		// 매개변수(함수객체) 세번째: 서버 호출에 실패했을 때의 실행 함수
		function(err) {
			console.log(err);
		}
	);
}


function addReplyFunc(e) {
	if (writer == '' || writer == null) {
		alert('로그인 후 댓글 등록이 가능합니다.')
	} else {
		let reply = document.querySelector('#reply').value;

		svc.addReply(

			// 매개변수 첫번째: bno, replyer, reply 3개가 담긴 객체
			param = { bno: bno, replyer: writer, reply: reply },

			// 매개변수(함수객체) 두번째: 서버 호출에 성공했을 때의 실행 함수
			function(result) {
				console.log(result);
				if (result.retCode == 'OK') {
					console.log("=============[addReply.do] successCallback의 result ===========");
					console.log(result);

					//list.appendChild(makeRow(result.retVal));		// TODO 한 행 추가하는 코드. 밑 err 해결하면 이후 수정 필요

					// [8-2] Swal.fire() 함수를 사용하면 끝!
					// 더 많은 예시는 https://sweetalert2.github.io/ 페이지를 참조.
					Swal.fire("댓글 등록 완료!");
					
					page = 1;
					
					showReplyListAndPagingList();	// 댓글 추가하고 리스트 갱신함



				} else {
					Swal.fire("댓글 등록 중 예외 발생!");
				}
			},

			// 매개변수(함수객체) 세번째: 서버 호출에 실패했을 때의 실행 함수
			function(err) {
				Swal.fire("댓글 등록 실패!");
				console.log("=============[addReply.do] errorCallback의 err ===========");
				console.log(err);
				
				showReplyListAndPagingList();

				// TODO reply DB에 저장 자체는 잘 되는데, 계속 err 메시지가 뜸. 해결 필요!
			}
		)
	} // end of if-else		
}


//== [9-10] 링크 클릭 시 이벤트핸들러 ==//
function showReplyListFunc(e) {
	page = e.target.dataset.page;			// 페이지 번호
	showReplyListAndPagingList();
}


let page = 1;

console.log("[replyBoard.js] bno: " + bno + " | page: " + page + " | writer: " + writer)



//== [7-3] 댓글정보를 토대로 <li> 생성하는 함수 ==//
function makeLi(reply = {}) {
	let cloned = document.querySelector('#template').cloneNode(true);	// 특정 DOM 요소를 복제. cloneNode()를 true로 하면
	// template id값을 가진 요소의 하위 요소도 같이 복제.

	cloned.style.display = 'block'										// 현재 jsp의 template는 display 속성이 'none'으로 되어 있음.
	// 이걸 그대로 clone 하면 해당 속성도 같이 복제되어져서
	// 화면에 보이지 않기 때문에 display 스타일을 block(기본)으로 변경.

	cloned.setAttribute('data-rno', reply.replyNo);						// template 요소에 'data-rno' 속성과 댓글 번호 속성값을 삽입
	cloned.querySelector('span').innerHTML = reply.replyNo;						// template 아래 span 태그 내부에 댓글번호를 삽입
	cloned.querySelector('span:nth-of-type(2)').innerHTML = reply.reply;		// template 아래 2번째 span 태그 내부에 댓글 내용을 삽입
	cloned.querySelector('span:nth-of-type(3)').innerHTML = reply.replyer;		// template 아래 3번째 span 태그 내부에 댓글 작성자를 삽입
	cloned.querySelector('button').addEventListener('click', deleteLiFnc)		// template 아래 button에 deleteLiFnc 함수 이벤트를 걺

	//console.log(cloned);
	return cloned
}



///== [7-4] 버튼이 포함된 row 삭제 ==///
// TODO: [추가미션] 마지막 댓글 페이징에서 하나의 댓글이 남았을 때 페이징이 자동으로 전 페이징으로 넘어가게끔 만들어보자.
// 예를 들어, 8페이지에서 마지막 한개의 댓글을 삭제했을때 8페이지가 유지되지 않고 7페이지로 자동으로 이동되게끔 해보자.

function deleteLiFnc(e) {

	// [8-3] 단순한 alert 외에도 confirm창도 지원. 더 많은 예시는 https://sweetalert2.github.io/ 페이지를 참조.
	Swal.fire({
		title: "정말 삭제하시겠습니까?",
		text: "이 작업은 되돌릴 수 없습니다.",
		icon: "warning",
		showCancelButton: true,
		confirmButtonColor: "#3085d6",
		cancelButtonColor: "#d33",
		confirmButtonText: "예"
	}).then((result) => {
		if (result.isConfirmed) {
			let rno = e.target.parentElement.parentElement.dataset.rno;		// 삭제할 글 번호를 부모 요소(li)로 부터 가져옴
			svc.removeReply(

				// 매개변수 첫번째: 댓글 번호
				rno,

				// 매개변수(함수객체) 두번째: 서버 호출에 성공했을 때의 실행 함수
				function(result) {
					if (result.retCode == 'OK') {
						Swal.fire({
							title: "삭제 성공",
							text: "삭제 처리가 정상적으로 완료되었습니다.",
							icon: "success"
						});

						// 삭제 후 댓글 리스트 다시 그리기
						showReplyListAndPagingList();
						// 리스트 다시 그리기 끝

					} else {
						Swal.fire({
							icon: "error",
							title: "삭제 처리 중 예외 발생",
							text: "Something went wrong!",
							footer: '<a href="#">Why do I have this issue?</a>'
						});
					}
				},

				// 매개변수(함수객체) 세번째: 서버 호출에 실패했을 때의 실행 함수
				function(err) {
					console.log(err);
				}
			)

		}
	});
}

//== [9-16] 댓글 개수를 활용하여 페이지 리스트 생성
function showPagingListFunc() {
	svc.replyPagingCount(
		bno, 					// 글번호 매개변수 
		makePagingList,			// 서버 요청 성공시 실행(콜백)함수
		function(err) {			// 서버 요청 실패시 실행(콜백)함수
			console.log(err);
		}
	);
}

// 서버 요청 성공 시 실행할 콜백 함수(위에랑 이어짐)
function makePagingList(result) {
	pagination.innerHTML = '';
	console.log("==================result=====================");
	console.log(result);
	let totalCnt = result.totalCount;
	let startPage, endPage, realEnd;	// 각각 첫 페이지, 마지막 페이지, 
	let prev, next;						// 이전 및 이후 페이지 여부

	endPage = Math.ceil(page / 5) * 5;
	startPage = endPage - 4;
	realEnd = Math.ceil(totalCnt / 5);

	endPage = endPage > realEnd ? realEnd : endPage;

	prev = startPage > 1;
	next = endPage < realEnd;


	// 이전 페이지 생성
	let li = document.createElement('li');
	li.className = 'page-item';

	let aTag = document.createElement('a');
	aTag.className = 'page-link';
	aTag.innerHTML = 'Previous';
	li.appendChild(aTag);	// <li class="page-item"><a class="page-link" href="#"></a></li>

	if (prev) {
		aTag.setAttribute('href', '#');
		aTag.setAttribute('data-page', startPage - 1);
	} else {
		li.classList.add('disabled');
	}
	pagination.appendChild(li);		// <ul><li class=...></li></ul>


	// li 생성. 페이지 범위에 들어갈...
	for (let p = startPage; p <= endPage; p++) {
		let li = document.createElement('li');
		li.className = 'page-item';

		let aTag = document.createElement('a');
		aTag.className = 'page-link';
		aTag.setAttribute('href', '#');

		aTag.setAttribute('data-page', p);

		aTag.innerHTML = p;
		li.appendChild(aTag);	// <li class="page-item"><a class="page-link" href="#"></a></li>

		if (p == page) {
			li.classList.add('active');
			li.setAttribute('aria-current', 'page');
		}

		pagination.appendChild(li);		// <ul><li class=...></li></ul>
	}


	// 이후 페이지 생성
	li = document.createElement('li');
	li.className = 'page-item';

	aTag = document.createElement('a');
	aTag.className = 'page-link';
	aTag.innerHTML = 'Next';
	li.appendChild(aTag);	// <li class="page-item"><a class="page-link" href="#"></a></li>

	if (next) {
		aTag.setAttribute('href', '#');
		aTag.setAttribute('data-page', endPage + 1);
	} else {
		li.classList.add('disabled');
	}
	pagination.appendChild(li);		// <ul><li class=...></li></ul>


	// 이벤트 등록
	document.querySelectorAll('ul.pagination a').forEach(aTag => {
		aTag.addEventListener('click', showReplyListFunc)
	})

}
















