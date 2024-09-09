package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardUpdateControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		System.out.println("[BoardUpdateControl.java] bno : " + bno);
		
		String sc = request.getParameter("searchCondition");
		String kw = request.getParameter("keyword");
		String paging = request.getParameter("page");
		
		BoardService svc = new BoardServiceImpl();
		BoardVO bvo = svc.getBoard(bno);
		
		System.out.println(bvo.getTitle());
		System.out.println(bvo.getContent());
		System.out.println(bvo.getWriter());
		
		request.setAttribute("board", bvo);
		request.setAttribute("sc", sc);
		request.setAttribute("kw", kw);
		request.setAttribute("paging", paging);
		
//		request.getRequestDispatcher("WEB-INF/board/boardUpdate.jsp").forward(request, response);
		request.getRequestDispatcher("board/boardUpdate.tiles").forward(request, response);
	}

}
