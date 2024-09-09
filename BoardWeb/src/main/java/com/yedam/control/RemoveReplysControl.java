package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;

public class RemoveReplysControl implements Control {
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] parmas = request.getParameterValues("rno");	// `?rno=1&rno=2&rno=3` 처럼 같은 파라미터명으로 여러 값이 넘어올 때
																// getParameterValues()를 사용하면 String 배열 형태로 여러 값을 담을 수 있음
		ReplyService svc = new ReplyServiceImpl();
		if(svc.removeDeleteReplys(parmas)) {
			response.getWriter().print("{\"retCode\": \"OK\"}");
		} else {
			response.getWriter().print("{\"retCode\": \"NG\"}");
			
		}
	}
}