<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<jsp:include page="../includes/header.jsp"></jsp:include>
<form action="login.do">
	<table class="table">
		<tr>
			<th>아이디</th><td><input type="text" name="id></td>
		</tr>
		<tr>
			<th>비밀번호</th><td><input type="password" name="pw"></td>
		</tr>
		<tr>
			<td colspan="2"><input class="btn btn-primary" type="submit" value="로그인"></td>
			
		</tr>
	</table>
</form>