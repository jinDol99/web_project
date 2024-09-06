package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.PageDTO;
import com.yedam.common.SearchDTO;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		page = page == null ? "1" : page;	// page값이 null이면이면 1을 기본값으로 넣음
		
		
		// 검색조건 파라미터. URI에 2개(searchCondition, keyword) 포함된거 가져옴.
		String sc = request.getParameter("searchCondition");
		String kw = request.getParameter("keyword");
		
		if (sc == null || kw == null || sc.isEmpty() || kw.isEmpty()) {		// 검색 조건이 없으면...
			request.setAttribute("message", "검색 조건을 입력하세요.");
			
		} else {
			SearchDTO search = new SearchDTO();
			search.setSearchCondition(sc);		// T, W, TW
			search.setKeyword(kw);				// Java, html...
			search.setPage(Integer.parseInt(page));
			
			
			BoardService svc = new BoardServiceImpl();
			List<BoardVO> list = svc.boardList(search);
			request.setAttribute("list", list);
			
			// 페이징 처리를 위한 기능
			int totalCnt = svc.getTotalCnt(search);
			PageDTO paging = new PageDTO(Integer.parseInt(page), totalCnt);
			request.setAttribute("paging", paging);
			
			
			// jsp 페이지에 전달
			request.setAttribute("sc", sc);
			request.setAttribute("kw", kw);
			System.out.println("[BoardListControl.java] 페이지 전달 완료!");
		}
		
//		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/board/boardList.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("board/boardList.tiles");
		rd.forward(request, response);
	}
}
         

