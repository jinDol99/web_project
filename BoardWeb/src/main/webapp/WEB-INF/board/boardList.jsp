<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>게시글 목록</h3>

<table class="table">
	<thead>
		<tr>
			<th>글번호</th><th>제목</th><th>작성자</th><th>작성일시</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="board" items="${list}">
			<tr>
				<td><c:out value=" ${board.boardNo }" /></td>
				<td>${board.title }</td>
				<td>${board.writer }</td>
				<td><fmt:formatDate value="${board.creationDate }" pattern="yyyy.MM.dd HH:mm:ss"/>  </td>
			</tr>	
		</c:forEach>
	</tbody>
</table>

<!-- 페이징 -->
<nav aria-label="...">
  <ul class="pagination">
    <li class="page-item disabled">
      <a class="page-link">Previous</a>
    </li>
    <li class="page-item"><a class="page-link" href="boardList.do?page=1">1</a></li>
    <li class="page-item active" aria-current="page">
      <a class="page-link" href="boardList.do?page=2">2</a>
    </li>
    <li class="page-item"><a class="page-link" href="boardList.do?page=3">3</a></li>
    <li class="page-item">
      <a class="page-link" href="#">Next</a>
    </li>
  </ul>
</nav>

<p>${paging }</p>


<%-- 
<p>${'문자열, 숫자, boolean null '} </p>
<p>${3+5 > 10 ? '참입니다' : '거짓입니다' }</p>
<p>${list }</p>p

<%
	String name = "HongKildong";
%>

<c:set var="name" value="HongKildong"></c:set>
<c:out value="${name}"></c:out>
<c:set var="age" value="20"></c:set>                                              
<c:out value= "${age > = 20 ? '성인' : '미성인' }"></c:out>

<c:if test="${name = 'hongKilDong' }">
	<p>맞습니다</p>
</c:if> --%>

<jsp:include page="../includes/footer.jsp"></jsp:include>