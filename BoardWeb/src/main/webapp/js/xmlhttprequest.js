/**
 * xmlhttprequest.js
 */

 // == 비동기 처리 객체 == //
 const xhtp = new XMLHttpRequest();			// 객체 생성
 xhtp.open('get', 'js/MOCK_DATA.json');		// 서버 페이지 지정
 xhtp.send();								// 서버 요청
 xhtp.onload = function() {					// 서버와 연결이 완료되면 내부 코드를 실행 
	 console.log(xhtp.responseText);		// xthp 객체의 responseText 속성 -> 결과값
	 let result = JSON.parse(xhtp.responseText);

	 // 보여줄 항목을 배열(fields)에 담기
	 let fields = ['id', 'first_name', 'last_name', 'gender', 'salary']

	 result.forEach(function(item, idx, ary) {
		 if(item.salary >= 5000 && item.gender == "Female") {
			 console.log(item);		// {id, first_name, last_name...} => tr > td*5 생성
			 
			 let tr = document.createElement('tr');
			 fields.forEach(field => {						// td 생성
				 let td = document.createElement('td');
				 td.innerHTML = item[field];				// item.id, item.first_name, item.last_name ... 갖고옴
				 tr.appendChild(td);				 
			 })
			 let btn = document.createElement('button');	// 버튼 요소 생성 (`<button>삭제</button>`)
			 btn.innerHTML = "삭제";
			 btn.addEventListener('click', () => {
				 tr.remove();
			 })
			 let td = document.createElement('td');
			 td.appendChild(btn);
			 tr.appendChild(td);
			 document.querySelector('.list').appendChild(tr);
		 }
	 })
 }
 