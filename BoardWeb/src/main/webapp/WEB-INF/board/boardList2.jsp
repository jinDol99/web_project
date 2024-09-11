<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<h3>게시글 목록</h3>

<div class="center">
	<form action="boardList.do">
		<div class="row">
			<!-- 검색 조건(title, writer 검색) -->
			<div class="col-sm-4">
				<select name="searchCondition" class="form-control">
					<option value="">선택하세요</option>
					<!-- 페이징이 넘어가도 값이 유지되도록 한번 지정하면 변수에 해당 값 저장  -->
					<option value="T" ${sc eq 'T' ? 'selected' : '' }>제목</option>
					<option value="W" ${sc eq 'W' ? 'selected' : '' }>작성자</option>
					<option value="TW" ${sc eq 'TW' ? 'selected' : '' }>제목 & 작성자</option>
				</select>
			</div>
			<!-- 키워드 -->
			<div class="col-sm-5">
				<!-- 키워드 input에 변수값을 입력하여 페이징이 넘어가도 값을 출력 -->
				<input type="text" name="keyword" class="form-control" value="${kw}">
			</div>
			<!-- 조회버튼 -->
			<div class="col-sm-2">
				<input type="submit" value="조회" class="form-control">
			</div>
		</div>
	</form>
</div>

<c:choose>
	<c:when test="${!empty message }">
		<p>검색 결과가 없습니다.</p>
	</c:when>
	
	<c:otherwise>
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
						<!-- kw와 sc를 추가해서 URI 파라미터를 추가함 [3-1] -->
						<td><a href="board.do?keyword=${kw}&searchCondition=${sc}&page=${paging.page}&bno=${board.boardNo }">${board.title }</a></td>
						<td>${board.writer }</td>
						<td><fmt:formatDate value="${board.creationDate }" pattern="yyyy.MM.dd HH:mm:ss"/>  </td>
					</tr>	
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 페이징 -->
		<nav aria-label="...">
		  <ul class="pagination">
		    <li class="page-item ${paging.prev ? '' : 'disabled' }">
		      <a class="page-link" href="boardList.do?keyword=${kw}&searchCondition=${sc}&page=${paging.startPage-1}">Previous</a>
		    </li>
			    <c:forEach var="pg" begin="${paging.startPage }" end="${paging.endPage }">
			    <c:choose>
			    	<c:when test="${paging.page == pg }">	<!--  현재 페이징일 경우:  -->
			    		<li class="page-item active" aria-current="page">
					    	<a class="page-link" href="boardList.do?keyword=${kw}&searchCondition=${sc}&page=${pg}">${pg}</a>
					    </li>
			    	</c:when>
			    	<c:otherwise>							<!-- 현재 페이징 아닐 경우 -->
			    		<li class="page-item">
			    			<a class="page-link" href="boardList.do?keyword=${kw}&searchCondition=${sc}&page=${pg}">${pg}</a>
			   			</li>
			    	</c:otherwise>
			    </c:choose>
			    </c:forEach>
		    <li class="page-item ${paging.next ? '' : 'disabled' }">
		      <a class="page-link" href="boardList.do?keyword=${kw}&searchCondition=${sc}&page=${paging.endPage+1}">Next</a>
		    </li>
		  </ul>
		</nav>
		
		<p>${paging }</p>
	</c:otherwise>
</c:choose>




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

