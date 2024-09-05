<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>회원상세정보</h3>
	<table class="table">
		<tr>
			<th>회원아이디</th><td>${memberInfo.memberId }</td>
		</tr>
		<tr>
			<th>회원이름</th><td>${memberInfo.memberName }</td>
		</tr>
		<tr>
			<th>비밀번호</th><td>${memberInfo.password }</td>
		</tr>
		<tr>
			<th>이메일</th><td>${memberInfo.email }</td>
		</tr>
		<tr>
			<th>가입일자</th><td>${memberInfo.creationDate }</td>
		</tr>
		<tr>
			<td><button class="btn btn-info" onclick="location.href='modifyForm.do?id=${memberInfo.memberId }'">수정</button>
			<td><button class="btn brn-danger" onclick="locaction.href='removeMember.do?id=${memberInfo.memberId }'">삭제</button>
		</tr>
	</table>
<jsp:include page="../includes/footer.jsp"></jsp:include>