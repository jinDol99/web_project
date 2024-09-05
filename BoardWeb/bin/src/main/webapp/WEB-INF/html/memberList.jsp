<%@page import="com.yedam.vo.MemberVO" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<%
	// JSP -> 서블릿으로 변환되어져서 실행.
	// memberList.jsp -> memberList_jsp.java -> memberList_jsp.class 실행
	List<MemberVO> list = (List<MemberVO>) request.getAttribute("memberlist");
%>
<table>
	</thead>
	<tbody>
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