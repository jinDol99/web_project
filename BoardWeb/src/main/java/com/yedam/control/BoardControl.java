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
		String boardNo = request.getParameter("bno");	// 상세
//		String 
		
		
		
		// 검색조건 파라미터. URI에 2개(searchCondition, keyword) 포함된거 가져옴. [3-2]
		String sc = request.getParameter("searchCondition");
		String kw = request.getParameter("keyword");
		
		
		BoardService svc = new BoardServiceImpl();
		BoardVO board = svc.getBoard(Integer.parseInt(boardNo));
		
		System.out.println(board.getBoardNo());
		
		int currentPaging = Integer.parseInt(request.getParameter("page"));
		System.out.println(currentPaging);
		
		request.setAttribute("board", board);
		request.setAttribute("paging", currentPaging);
		
		//jsp 페이지에 전달 [3-3]
		request.setAttribute("sc", sc);
		request.setAttribute("kw", kw);
		
		// 카운트 증가 : 이걸... 했던가???
//		svc.addViewCount(Integer.parseInt(boardNo));
		
//		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/board/board.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("board/board.tiles");
		rd.forward(request, response);
		
	}
}
