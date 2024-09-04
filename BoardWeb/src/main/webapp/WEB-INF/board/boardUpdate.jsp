<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>글 상세 페이지</h3>
<form>
<table class="table">
	<tr><td>제목</td><td colspan="3">
		<input class="form-control" type="text" name="title" >${board.title }
	</td></tr>
	<tr><td>글쓴이</td><td colspan="3">
		<input class="form-control" type="text" name="writer" >${board.writer }
	</td></tr>
	<tr><td>내용</td><td colspan="3">
		<input class="form-control" type="text" name="writer" >${board.content }</td></tr>
	<tr>
		<td colspan="4" align="center">
			<button class="btn btn-info">완료</button>
			<button class="btn btn-secondary" onclick="location.href='boardList.do?page=${paging}'">취소</button>
		</td>
	</tr>
</table>
</form>

<jsp:include page="../includes/footer.jsp"></jsp:include>