package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class AddBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//== multipart(이미지 등의 파일을 간편히 업로드) 요청을 처리. 서버의 위치(image) 파일 복사. ==//
		MultipartRequest mr;
		
		System.out.println( request.getContentType().indexOf("multipart/form-data"));
		
		String saveDir = request.getServletContext().getRealPath("images");		// 이미지 파일 저장공간을 지정
		int maxSize = 5 * 1024 * 1024;											// 이미지 최대 업로드 제한 용량을 5MB로 설정.
		
		// MultipartRequestdml 파라미터는 각각 요청, 파일저장경로, 최대파일크기, 인코딩 방식, 리네임정책을 의미.
		mr = new MultipartRequest(request, saveDir, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String writer = mr.getParameter("writer");
		String img = mr.getFilesystemName("srcImage");
		
		BoardVO board = new BoardVO();
		board.setTitle(title);
		board.setContent(content);
		board.setWriter(writer);
		board.setImage(img);
		
		BoardService svc = new BoardServiceImpl();
		svc.addBoard(board);
		
		response.sendRedirect("boardList.do");
	}

}
