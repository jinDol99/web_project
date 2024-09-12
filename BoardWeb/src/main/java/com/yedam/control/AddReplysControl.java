package com.yedam.control;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.common.DebugUtil;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class AddReplysControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//== [6-8] GSON 라이브러리 활용 ==//
		// 아래의 .getWriter처럼 쓰기엔 너무 불편함. 이를 해결하기 위해 Gson 라이브러리를 사용함
		// GSON을 사용하기 위해 우선 MVN Repository에서 가져온 코드를 pom.xml에 추가하자.
		// https://mvnrepository.com/artifact/com.google.code.gson/gson/2.11.0
		response.setContentType("text/json;charset=utf-8");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();	
		Map<String, Object> map = new HashMap<>();
		
		// 댓글 작성자, 원본글 번호, 댓글 내용
		String replyerInput = request.getParameter("replyer");
		int boardNumInput = Integer.parseInt(request.getParameter("bno"));
		String replyInput = request.getParameter("reply");
		
		DebugUtil.printcurrVal("replyInput", replyInput);
		
		ReplyVO reply = new ReplyVO();
		reply.setReplyer(replyerInput);
		reply.setBoardNum(boardNumInput);
		reply.setReply(replyInput);
//		reply.setReplyDate(new Date());
		
		ReplyService svc = new ReplyServiceImpl();
		
		// retCode => OK, retCode => NG
		// {"retCode": "OK", "retVal": {"replyNo": 19, "reply": "replyInput", "replyer": "replyerInput", "boardNo": 148}}
		if(svc.addReply(reply)) {
//			response.getWriter().print("{\"retCode\": \"OK\", "
//					+ "\"retVal\": {\"replyDate\": \"" + reply.getReplyDate() 
//					+  "\"replyNo\": " + reply.getReplyNo()
//					+ ", \"reply\": " + reply.getReply()
//					+ ", \"replyer\": " + reply.getReplyer()
//					+ ", \"boardNo\": " + reply.getBoardNum() + "}}");
			
			// [6-8-a]
			map.put("retCode", "OK");
			map.put("retVal", reply);
			
		} else {
//			response.getWriter().print("{\"retCode\": \"NG\"}");
			map.put("retCode", "NG");
		}
		
		// [6-8-b]
		String json = gson.toJson(map);	/// map 객체를 json 문자열로 변환
		response.getWriter().print(json);	/// 출력스트림에 json 문자열 출력

	}

}
