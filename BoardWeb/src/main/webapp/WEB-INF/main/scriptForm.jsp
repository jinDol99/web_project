<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<p>스크립트 연습</p>
<div id="show">
	<!-- 	
	<p>이름: Hong</p>
	<p>연락처: 010-1111-2222</p>
	<ul>
		<li>이름 Kim, 연락처 1234-1234</li>
		<li>이름 Kim, 연락처 3456-3456</li>
	</ul>
 -->
</div>

<!-- 

<table>
	<tr>
		<th>id</th>
		<td><input type="text" id="id"></td>
	</tr>
	<tr>
		<th>name</th>
		<td><input type="text" id="name"></td>
	</tr>
	<tr>
		<th>point</th>
		<td><input type="number" id="point"></td>
	</tr>
	<tr>
		<td colspan="2"><button id="addBtn">추가</button></td>
	</tr>
</table>


<table id="target">
	<thead>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>포인트</th>
		</tr>
	</thead>
	<tbody id="list">
		<tr>
			<td>user01</td>
			<td>홍길동</td>
			<td>1200</td>
		</tr>
	</tbody>
</table>

 -->
<hr>

<div>
	salary: <input id="salary"><br> gender: <select
		id="gender">
		<option value="Male">남성</option>
		<option value="Female">여성</option>
	</select>
	<button id="searchBtn">검색</button>
</div>


<!-- <h3>목록</h3>
<table class="table">
	<thead>
		<tr>
			<th>아이디</th>
			<th>FisrtName</th>
			<th>LastName</th>
			<th>Gender</th>
			<th>salary</th>
			<th>DELETE</th>
		</tr>
	</thead>

	<tbody class="list">
	</tbody>
</table> -->

<h3>목록</h3>
<table class="table">
	<tr>
		<td>댓글 내용: <input type="text" id="reply"></td>
		<td><button id="addReply">댓글등록</button> 
	</tr>
</table>


<table class="table">
	<thead>
		<tr>
			<th><input type='checkbox' id='checkAllBox' onclick='checkAll()'></th>
			<th>댓글번호</th>
			<th>댓글내용</th>
			<th>작성자</th>
			<th>작성일시</th>
			<th><button id='delChecked'>선택삭제</button></th>
		</tr>
	</thead>

	<tbody class="list">
	</tbody>
</table>

<!-- <script src="js/basic.js" ></script>
<script src="js/basic2.js"></script>
<script src="js/basic3.js"></script>

<script src="js/data.js"></script>
<script src="js/basic4.js"></script>

<script src="js/ajax.js"></script>				console.log로 동기 & 비동기 실습
<script src="js/xmlhttprequest.js"></script>	JSON 데이터를 table에 넣고 동적으로 삭제 -->

<!-- [6-3] 두 개의 새로 만든 js를 가져옴. 해당 자바스크립트에서 사용한 전역변수들은 서로서로 공유하여 사용 가능 -->
<script src="js/replyService.js"></script>
<script src="js/replyBoard.js"></script>
<!-- <script src="js/fetch.js"></script> -->