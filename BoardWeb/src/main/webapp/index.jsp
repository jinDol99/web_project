<!-- jsp 페이지를 만들기 전 [Preferences]를 들어가서 [Web] - [CSS], [HTML], [JSP]의-->
<!-- 인코딩 방식을 UTF-8 로 변경해준 뒤 JSP 파일을 생성하자. -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>박진석의 홈입니다.</title>
</head>
<body>
	<h3>반갑습니다. 박진석입니다.</h3>
	<a href="html/intro.jsp">intro 페이지로 이동</a>
	<a href="html/addForm.jsp">회원등록 페이지로 이동</a>
	<a href="http://192.168.0.50/BoardWeb/">남의 페이지로 이동</a>
</body>
</html>