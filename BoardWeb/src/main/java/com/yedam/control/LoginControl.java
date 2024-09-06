package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class LoginControl implements Control{
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberService svc = new MemberServiceImpl();
		MemberVO member = svc.loginCheck(id, pw);
		
		if (member == null) {
			request.setAttribute("message", "아이디와 비밀번호를 확인하세요.");
//			request.getRequestDispatcher("WEB-INF/html/logForm.jsp").forward(request, response);
			request.getRequestDispatcher("html/logForm.tiles").forward(request, response);
			return;
		}
	
		// Session 객체 - 요청정보(브라우저)를 확인해서 쿠키를 통해 생성된 정보 구분
		HttpSession session = request.getSession();
		session.setAttribute("logid", id);	// 세션에 로그인 아이디 값(id)을 logid 이름으로 저장
		session.setAttribute("logName", member.getMemberName());
		
//		response.sendRedirect("boardList.do");
		
		
		System.out.println("[LoginControl.java] member.getAuthority() : " + member.getAuthority());
		
		// 일반 사용자, 관리자용 main 페이지를 다른 템플릿에서 실행
		if(member.getAuthority().equals("User")) {
			System.out.println("[LoginControl.java] User 페이지 이동");
			request.getRequestDispatcher("main/main.tiles").forward(request, response);
		} else if(member.getAuthority().equals("Admin")){
			System.out.println("[LoginControl.java] Admin 페이지 이동");
			request.getRequestDispatcher("admin/layoutAdmin/template.tiles").forward(request, response);
		}
	
	}
}
