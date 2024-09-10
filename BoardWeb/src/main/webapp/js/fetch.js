/**
 * 	fetch.js (비동기처리: 서버상의 리소스 요청)
 * [6-1] 새로운 fetch.js 생성
 */
// 내가 공부하려고 필기한 주석
/// 교수님 주석



let bno = 148;			// 댓글 달 게시글 번호(148)와 댓글 작성자(user01)는 임의로 명시하고 작업 진행
let writer = 'user01';


 //== fetch 연습 ==//
 fetch('js/data.js')			// XMLHttpRequest.open()을 썼다면 ajax에서는 fetch()를 사용함
 								// fetch는 relyList.do 처럼 특정 컨트롤러를 요청할수도 있지만,
 								// 왼쪽 처럼 JSON 형식의 데이터를 불러와 요청할 수도 있음.
 								// fetch의 결과는 promise 객체로 반환
 								
 .then(function(resolve) {		// 정상 실행의 경우 (resolve 매개변수로 변환된 결과와 함께) then 함수를 실행
	 console.log(resolve);
	 return resolve.text();		// 참고로 .text()를 사용하여 객체를 텍스트 형식으로 변환 가능
 })
 .then(function(result) {		// resolve 변수를 return하면 다시 promise 객체로 변환되어 반환되어짐
	 console.log(result);		// 즉, 다시 then() 함수를 사용하여 이 promise 객체를 사용 가능함
	 
	 // `[`의 위치 `]`의 위치 사이의 substring
	 let json = result.substring(result.indexOf('['), result.indexOf(']')+1);
	 console.log(JSON.parse(json));		// resolve된 것을 JSON으로 이렇게 파싱하여 활용 가능함.
 })
 
 .catch(function() {			// -> 비정상 실행의 경우 catch 함수를 실행
	 console.log('에러 발생', err)
 })		
 
 
 
 //== [6-4] 서버의 댓글목록 요청 작업 ==//
 const list = document.querySelector('tbody.list');
 
 fetch('replyList.do?bno=' + bno)
 //.then(resolve => {
	// return resolve.json();				// replyList.do의 결과로 JSON 형식의 reply 데이터를 가져옴
 //})
 
  .then(resolve => resolve.json())		/// 응답객체(response)의 정보를 json 문자열 -> 객체 변경 메소드: json()
  										// 즉, 위 3줄을 간략화(return 제외)하면 이렇게 사용 가능
 
 .then(result => {
 	console.log(result);				/// [배열]
 	result.forEach(reply => {			// 가져온 JSON 데이터를 한 행씩 반복 처리
		 let tr = makeRow(reply);		// 한 행에 4열씩 td를 만들고 이 4개의 td를 tr 변수에 넘김
		 list.appendChild(tr);			// tr 변수는 list의 자식으로 삽입
	 })									// makeRow()의 동작 방식은 ReplyService.js를 참조하자
 })
 .catch(err => {
	 console.log('catch 예외', err);
 })
 
 
 
 //== [6-5] 댓글 삭제 처리 ==//
 function deleteRowFnc(e) {
	 let rno = e.target.parentElement.parentElement.dataset.row;	/// <tr data-rno=23
	 																// 6-5-a 에서 추가한 data-row 속성값을 가져옴
	 console.log("=================[deleteRowFnc] rno ========================");
	 console.log(rno);
	 
	 fetch('removeReply.do?rno=' + rno)
	 .then(resolve => resolve.json())
	 .then(result => {
		 if (result.retCode == 'OK') {
			 alert('삭제 완료')
		 } else {
			 alert('삭제 처리중 예외 발생')			 
		 }
	 }) // fetch end
	 
 }
 
 //== [6-6] 이벤트 (등록) ==//
 document.querySelector('#addReply').addEventListener('click', addReplyFunc);
 document.querySelector('#deleteRowFnc')
 
 
 
 //== 함수들 ==//
 //==== [6-7] 새 댓글 추가 ====//
 function addReplyFunc(e) {
	 let reply = document.querySelector('#reply').value;	// "댓글내용" 폼의 내용(.value)을 가져옴
	 
	 fetch('addReply.do', {													// GET 방식이 아닌 POST 방식으로 통신하려고 할 때는 
		 method: 'post',													// fetch의 두번째 매개변수로 객체를 건네야 함.
		 headers: {'Content-Type': 'application/x-www-form-urlencoded'},	// 객체의 세부 내용은 왼쪽과 동일하게 입력하면 되며
		 body: 'bno=' + bno + '&reply=' + reply + '&replyer=' + writer		// POST는 전달할 값을 URL이 아닌 body에 담아 전송하므로
		 																	// 'body'의 value 값으로 해당 내용을 집어넣으면 됨.
	 })		/// 주소표시줄 addReply.do?bno=148&reply=...&replyer=user01
	 .then(resolve => resolve.json)
	 .then(result => {
		 console.log("=============[addReply.do] fetch의 result ===========");
		 console.log(result);		// result의 형태를 반드시 확인하는것이 좋음!
		 
		 if(result.retCode == 'OK') {						// 6-8-b에서 뿌려준 mapJSON으로 변환된 map 정보를 가져와 활용
			 list.appendChild(makeRow(result.retVal));
		 } else {
			 alert("처리 중 예외 발생");
		 }
	 })
 }
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 