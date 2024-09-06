package com.yedam.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class RemoveBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// bno (글 삭제 용도), paging(특정 page로 이동)
		
		String bno = request.getParameter("bno");	// 상세조회 할 게시글 번호
		String page = request.getParameter("paging");
		
		// 검색조건 파라미터. URI에 2개(searchCondition, keyword) 포함된거 가져옴. [4-2]
		String sc = request.getParameter("searchCondition");
		String kw = request.getParameter("keyword");
		
		// 로그인 정보
		HttpSession session = request.getSession();
		String logid = (String) session.getAttribute("logid");
		
		BoardService svc = new BoardServiceImpl();
		BoardVO board = svc.getBoard(Integer.parseInt(bno));
		
		// 로그인 정보가 없거나 로그인 정보와 작성자가 다르다면 삭제불가
		if (logid == null || !logid.equals(board.getWriter())) {
			request.setAttribute("message", "삭제 권한이 없습니다.");
			request.setAttribute("board", board);
			request.setAttribute("paging", page);
			
//			request.getRequestDispatcher("WEB-INF/board/board.jsp").forward(request, response);
			request.getRequestDispatcher("board/board.tiles").forward(request, response);
			return;
		}
		
		// 게시글 정상 삭제
		if (svc.removeBoard(Integer.parseInt(bno))) {			// getRequestDispatcher()와 달리, sendRedirect()는 파라미터에 request와 response 정보를 담지 못하기 때문에
//			request.setAttribute("paging", page);				// URI에 파라미터(?page=...)를 담는 방식으로 전달함.
			response.sendRedirect("boardList.do?page=" + page + "&searchCondition=" + sc + "&keyword=" + kw);	// sc, kw 추가 [4-3]
		}
		

	}

}
