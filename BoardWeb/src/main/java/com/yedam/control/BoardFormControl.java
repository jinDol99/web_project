package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;

public class BoardFormControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("WEB-INF/board/boardForm.jsp").forward(request, response);
		request.getRequestDispatcher("board/boardForm.tiles").forward(request, response);
		System.out.println("board/boardForm.tiles");
	}

}
