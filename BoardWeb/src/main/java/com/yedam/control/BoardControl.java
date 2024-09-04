package com.yedam.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardControl implements Control {
	
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bno = request.getParameter("bno");
		
		BoardService svc = new BoardServiceImpl();
		BoardVO board = svc.getBoard(Integer.parseInt(bno));
		
		int currentPaging = Integer.parseInt(request.getParameter("page"));
		System.out.println(currentPaging);
		
		request.setAttribute("board", board);
		request.setAttribute("paging", currentPaging);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/board/board.jsp");
		rd.forward(request, response);
		
	}
}
