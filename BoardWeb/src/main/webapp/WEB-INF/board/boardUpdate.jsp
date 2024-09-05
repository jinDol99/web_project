<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>글 상세 페이지</h3>

<form action="modifyBoard.do" method="post">
<input class="form-control" type="text" name="bno" value="${board.boardNo }">
<input class="form-control" type="text" name="keyword" value="${kw }">
<input class="form-control" type="text" name="searchCondition" value="${sc }">


<table class="table">
	<tr><td>제목</td><td colspan="3">
		<input class="form-control" type="text" name="title" value="${board.title }">
	</td></tr>
	<tr><td>글쓴이</td><td colspan="3">
		<input class="form-control" type="text" name="writer" value="${board.writer }">
	</td></tr>
	<tr><td>내용</td><td colspan="3">
		<input class="form-control" type="text" name="content" value="${board.content }"></td></tr>
	<tr>
		<td colspan="4" align="center">
			<input class="btn btn-primary" type="submit" value="완료">
      		<input type="button" class="btn btn-secondary" value="취소">
			<%-- <button class="btn btn-info" onclick="location.href='modifyBoard.do?page=${paging}'">완료</button>
			<button class="btn btn-secondary" onclick="location.href='boardList.do?page=${paging}'">취소</button> --%>
		</td>
	</tr>
</table>
</form>

<jsp:include page="../includes/footer.jsp"></jsp:include>