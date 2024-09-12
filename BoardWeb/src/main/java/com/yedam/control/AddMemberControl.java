package com.yedam.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class AddMemberControl implements Control {
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		MemberVO mvo = new MemberVO();
		
		
//		String id;
//		mvo.setMemberId(id);
//		String name;
//		mvo.setMemberName(name);
//		String pw;
//		mvo.setPassword(pw);
//		String mail;
//		mvo.setEmail(mail);
		
		MemberService svc = new MemberServiceImpl();
		// 회원등록이 정상적일 경우 -> 회원 목록에 페이지 출력.
		// 회원 등록이 비정상적일 경우 -> 회원등록 페이지 이동 (msg 전달)
		// 현재 페이지 : addMember.do -> 페이지 재지정 방식
		//		1) forwarding: 파라미터 전달. 2) redirect : 파라미터 전달 불가
		
		
		if (svc.addMember(mvo)) {
//			PrintWriter out = response.getWriter();
//			out.print(req, res);
			response.sendRedirect("memberList.do");
		} else {
//			request.getRequestDispatcher("WEB-INF/html/addFrom.jsp").forward(request, response);
			request.getRequestDispatcher("html/addFrom.tiles").forward(request, response);
		}
		
		
	}
}
