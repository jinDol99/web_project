package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class MemberListControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService svc = new MemberServiceImpl();
		List<MemberVO> list = svc.getMembers();
		
		request.setAttribute("memberList", list);
		request.getRequestDispatcher("admin/memberList.tiles").forward(request, response);
		
		// TODO : 나머지 회원 조회 페이지는 BaordListControl.java를 참고하여 진행하자
	}
}
