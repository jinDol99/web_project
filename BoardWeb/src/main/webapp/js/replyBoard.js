/**
 * [7-1] Board
 *  replyBoard.js
 *  replyService에 정의된 메소드를 통해서 기능 실행
 */

//====================================		// 이벤트와 관련된 함수처럼 종류별로 메소드들을 따로 모아두는 편
//		이벤트(댓글등록)
//====================================

//== [7-6] 댓글 등록 이벤트핸들러 ==//
/// bno, replyer, reply
document.querySelector('#addReply').addEventListener('click', addReplyFunc);

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
				if (result.retCode == 'OK') {
					console.log("=============[addReply.do] successCallback의 result ===========");
					console.log(result);

					//list.appendChild(makeRow(result.retVal));		// TODO 한 행 추가하는 코드. 밑 err 해결하면 이후 수정 필요

					// [8-2] Swal.fire() 함수를 사용하면 끝!
					// 더 많은 예시는 https://sweetalert2.github.io/ 페이지를 참조.
					Swal.fire("댓글 등록 완료!");



				} else {
					Swal.fire("댓글 등록 중 예외 발생!");
				}
			},

			// 매개변수(함수객체) 세번째: 서버 호출에 실패했을 때의 실행 함수
			function(err) {
				Swal.fire("댓글 등록 실패!");
				console.log("=============[addReply.do] errorCallback의 err ===========");
				console.log(err);	
				
				// TODO reply DB에 저장 자체는 잘 되는데, 계속 err 메시지가 뜸. 해결 필요!
			}
		)
	} // end of if-else		
}


//== [7-2] 댓글 목록 그리기 ==//

console.log("[board.jsp] bno: " + bno + " | writer: " + writer)
svc.replyList(				// replyService에서 replyList(댓글내역 가져오기) 메서드 실행한 결과를 관련 요청 및 응답 매개변수와 함께 호출

	// 매개변수 첫번째: 게시글 번호
	bno,

	// 매개변수(함수객체) 두번째: 서버 호출에 성공했을 때의 실행 함수
	function(result) {
		console.log(result);
		result.forEach(reply => {
			document.querySelector('div.content ul').appendChild(makeLi(reply));
		})
	},

	// 매개변수(함수객체) 세번째: 서버 호출에 실패했을 때의 실행 함수
	function(err) {

	}
)


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

	console.log(cloned);
	return cloned
}



///== [7-4] 버튼이 포함된 row 삭제 ==///
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