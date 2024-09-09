package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.common.DebugUtil;
import com.yedam.mapper.BoardMapper;
import com.yedam.mapper.MemberMapper;
import com.yedam.vo.BoardVO;

public class ModifyBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[ModifyBoardControl.java] 호출 완료!");
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		String keyword = request.getParameter("keyword");
		String searchCondition = request.getParameter("searchCondition");
		int paging = Integer.parseInt(request.getParameter("paging"));
		
		BoardVO bvo = new BoardVO();
		bvo.setBoardNo(boardNo);
		bvo.setTitle(title);
		bvo.setWriter(writer);
		bvo.setContent(content);
		
		DebugUtil.printcurrVal("paging", paging);
		
		SqlSessionFactory factory = DataSource.getInstance();
		SqlSession session = factory.openSession(true);
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		
		try {
			if(mapper.updateBoard(bvo) == 1) {
				response.sendRedirect("boardList.do");
			} else {
				request.setAttribute("message", boardNo + "수정할 정보가 없습니다.");
//				request.getRequestDispatcher("WEB-INF/html/modifyForm.jsp").forward(request, response);
				request.getRequestDispatcher("html/modifyForm.tiles").forward(request, response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		System.out.println("[ModifyBoardControl.java] boardNo: " +  bvo.getBoardNo());

	}

}
