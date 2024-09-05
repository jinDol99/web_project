package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;

public class LogoutControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session에 logid 정보가 있는 세션을 삭제.
		HttpSession session = request.getSession();		// 현재 세션정보 파악
		session.invalidate();							// 현재 세션 정보 삭제
		
		response.sendRedirect("loginForm.do");			// 로그인 폼으로 이동

	}

}
