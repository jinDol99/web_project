<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
  
  <!-- [10-1] 페이징, 정렬, 검색 등을 편하게 해주는 라이브러리 "datatables"을 사용하기 위해 CSS, Jquery, JS를 추가 -->
  <!-- Jquery는 홈페이지에서 다운받은 바로 실행 가능한 jquery-3.7.1.js 파일을 그대로 활용. -->
  <link rel="stylesheet"  href="//cdn.datatables.net/2.1.5/css/dataTables.dataTables.min.css">
  <script src="js/jquery-3.7.1.js"></script>
  <script src="//cdn.datatables.net/2.1.5/js/dataTables.min.js"></script>
<h3>게시글 목록</h3>


	<!-- [10-3] 기존의 페이징, 검색 부분 등 필요없는 부분은 모두 지우고 데이터를 모두 가져오는 부분만 남겨둠 -->
	<!--  그리고 테이블의 속성을 아래와 같이 지정. -->
		<table id="example" class="display" style="width: 100%">
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
			<tfoot>
				<tr>
					<th>글번호</th><th>제목</th><th>작성자</th><th>작성일시</th>
				</tr>
			</tfoot>
		</table>

<script>
	// [10-4] 테이블을 사용하기 위해 자바스크립트에서 새로운 DataTable 객체를 생성
	new DataTable('#example');
</script>


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

