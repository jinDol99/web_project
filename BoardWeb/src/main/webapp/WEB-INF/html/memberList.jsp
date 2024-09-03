<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<table>
	</thead>
	<tbody>
<%
		for (MemberVO member : list) {
%>
		<tr>
			<td><a href="getMember.do?id=<%=member.getMemberId()%>"> <%=member.getMemberId()%></a>
			<td><%=member.getMemberName()%></td>
			<td><%=member.getEmail()%></td>
			<td><%=member.getAuthority()%></td>
		</tr>
<%
		}
%>
	</tbody>
</table>
<jsp:include page="../includes/footer.jsp"></jsp:include>