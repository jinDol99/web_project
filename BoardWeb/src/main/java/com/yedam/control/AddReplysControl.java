package com.yedam.control;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.DebugUtil;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class AddReplysControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 댓글 작성자, 원본글 번호, 댓글 내용
		String replyerInput = request.getParameter("replyer");
		int boardNumInput = Integer.parseInt(request.getParameter("bno"));
		String replyInput = request.getParameter("reply");
		
		DebugUtil.printcurrVal("replyInput", replyInput);
		ReplyVO reply = new ReplyVO();
		reply.setReplyer(replyerInput);
		reply.setBoardNum(boardNumInput);
		reply.setReply(replyInput);
		reply.setReplyDate(new Date());
		
		ReplyService svc = new ReplyServiceImpl();
		
		// retCode => OK, retCode => NG
		// {"retCode": "OK", "retVal": {"replyNo": 19, "reply": "replyInput", "replyer": "replyerInput", "boardNo": 148}}
		if(svc.addReply(reply)) {
			response.getWriter().print("{\"retCode\": \"OK\", "
					+ "\"retVal\": {\"replyDate\": \"" + reply.getReplyDate() 
					+  "\"replyNo\": " + reply.getReplyNo()
					+ ", \"reply\": " + reply.getReply()
					+ ", \"replyer\": " + reply.getReplyer()
					+ ", \"boardNo\": " + reply.getBoardNum() + "}}");
			
		} else {
			response.getWriter().print("{\"retCode\": \"NG\"}");
		}
		

	}

}
