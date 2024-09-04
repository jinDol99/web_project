<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>글 상세 페이지</h3>
<table class="table">
	<tr><td>제목</td><td colspan="3">${board.title }</td></tr>
	<tr><td>글쓴이</td><td colspan="3">${board.writer }</td></tr>
	<tr><td>조회수</td><td>123</td><td> 작성시간</td><td>${board.creationDate }</td></tr>
	<tr><td>내용</td><td colspan="3">${board.content }</td></tr>
	<tr>
		<td colspan="4" align="center">
			<button class="btn btn-secondary" onclick="location.href='boardList.do?page=${paging}'">목록으로</button>
			<button class="btn btn-info" onclick="location.href='boardUpdate.do?page=${paging}&bno=${board.bno }'">수정은 숙제</button>
			<button class="btn btn-danger">삭제</button>
		</td>
	</tr>
</table>

<!-- [ 숙제 ] 
상세 페이지와 비슷하게 새 페이지를 구현.
작성자는 수정 안되게끔
수정 완료 or 취소하면 방금 보고있었던 상세페이지로 ㄱㄱ

TODO boardUpdate.do 매핑하기!!!! (boardIpdate.jsp는 있음)
 -->


<jsp:include page="../includes/footer.jsp"></jsp:include>