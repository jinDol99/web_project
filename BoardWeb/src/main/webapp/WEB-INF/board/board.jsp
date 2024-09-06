<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<h3>글 상세 페이지</h3>
<p>searchCondition: ${sc }, keyword: ${kw }</p>
<table class="table">
	<tr><td>제목</td><td colspan="3">${board.title }</td></tr>
	<tr><td>글쓴이</td><td colspan="3">${board.writer } ${board.boardNo }</td></tr>
	<tr><td>조회수</td><td>123</td><td> 작성시간</td><td>${board.creationDate }</td></tr>
	<tr><td>내용</td><td colspan="3">${board.content }</td></tr>
	<tr>
		<td colspan="4" align="center">
			<%-- <button class="btn btn-secondary" onclick="location.href='boardList.do?keyword=${kw}&searchCondition=${sc}&page=${paging}'">목록으로</button> --%>
			<button class="btn btn-secondary" onclick="form_submit('boardList.do')">목록으로</button>
			<!-- 게시글의 작성자(board.writer)와 로그인된 사람(logid)와 일치하지 않으면(ne) 클래스에 "disabled" (비활성화) 속성값을 삽입-->
			<%-- <button class="btn btn-info ${board.writer ne logid ? 'disabled' : ''}" onclick="location.href='boardUpdate.do?page=${paging}&bno=${board.boardNo }'">수정은 숙제</button> --%>
			<button class="btn btn-secondary" onclick="form_submit('boardUpdate.do')">수정은 숙제</button>
			<!-- 삭제 페이지에도 2개의 파라미터를 추가로 넘김 [4-1] -->
			<%-- <button class="btn btn-danger" onclick="location.href='removeBoard.do?keyword=${kw}&searchCondition=${sc}&page=${paging}&bno=${board.boardNo }'">삭제</button> --%>
			<!-- [5-2] -->
			<button class="btn btn-danger" onclick="form_submit('removeBoard.do')">삭제</button>
			
			<c:if test="${!empty message }">
				<span style="color: red;">${message }</span>
			</c:if>
		</td>
	</tr>
</table>

<!-- [5-1] -->
<form action="removeBoard.do" name="actForm">
	<input type="hidden" name="keyword"  value="${kw }">
	<input type="hidden" name="searchCondition"  value="${sc }">
	<input type="hidden" name="page"  value="${paging }">
	<input type="hidden" name="bno"  value="${board.boardNo }">
</form>




<script>
	// [5-3]
	function form_submit(uri) {
		// 매개값으로 이동할 컨트롤을 받아서 파라미러틑 전달 [5-4]
		// console.log(document.forms.actForm.submit());
		console.log(uri);
		document.forms.actForm.action = uri;
		document.forms.actForm.submit();
	}
</script>
<!-- [ 숙제 ] 
상세 페이지와 비슷하게 새 페이지를 구현.
작성자는 수정 안되게끔
수정 완료 or 취소하면 방금 보고있었던 상세페이지로 ㄱㄱ

+ 추가 조건
http://localhost/BoardWeb/boardList.do?keyword=JAVA&searchCondition=T&page=4
위 검색조건으로 SELECT 한 후 특정 상세 페이지를 들어갔다 나오면 조건이 해제가 되어짐
상세페이지 조회, 수정을 해도 검색조건이 유지되도록 파라미터를 손보자!

TODO boardUpdate.do 매핑하기!!!! (boardIpdate.jsp는 있음)
 -->