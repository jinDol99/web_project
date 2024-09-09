package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.DebugUtil;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;

// [5-b] 늘 하던대로 com.yedam.control에 해당 클래스 만들기
public class RemoveReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// [5-g] 인터페이스랑 매퍼 등 일련의 작업과정은 구현됬으니 결과를 받았을 때의 알고리즘 짜기
		// 삭제할 댓글 번호(rno) 전달 받으면 그거 삭제 -> 잘 삭제됬다는 int 반환
		String rno = request.getParameter("rno");
		
		DebugUtil.printcurrVal("rno", rno);
		
		
		ReplyService svc = new ReplyServiceImpl();
		if (svc.deleteReply(Integer.parseInt(rno))) {
			// {"retCode": "OK"}									// 꿀팁: "..." 안에 여러 큰 따옴표가 있어 이스케이프 문자(\)를 일일히 쓰기 번거롭다면
			response.getWriter().print("{\"retCode\": \"OK\"}");	// 그냥 주석으로 출력하려는 문자열을 입력한 후, "..." 안에 복붙하면 IDE가 알아서
																	// 이스케이프 문자를 추가해줌.
			
		} else {
			response.getWriter().print("{\"retCode\": \"NG\"}");
		}
	}

}
