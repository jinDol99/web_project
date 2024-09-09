package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.DebugUtil;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

//[5-3] 컨트롤러 생성
public class ReplyListControl implements Control {
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB 추출된 정보를 활용하여 JSON 포맷의 문자열을 반환
		// [{"replyNo": 1, "replyer": "user01", "reply": "1번 댓글", "boardNum": 148 ...},
		//  {"replyNo": 2, "...": ... },
		//  { ... }, {}, ... {}]  형태로 되어 있는 것이 JSON.
		
		// [5-15] 컨트롤러 상세 내용 작성
		response.setContentType("text/json;charset=utf-8");
		
		String bno = request.getParameter("bno");
		ReplyService svc = new ReplyServiceImpl();
		List<ReplyVO> list = svc.selectList(Integer.parseInt(bno));
		
		String json = "[";
		for (int i = 0; i < list.size(); i++) {
			json += "{\"replyNo\":" + list.get(i).getReplyNo() + ","
					+ "\"replyer\": \"" + list.get(i).getReplyer() + "\", "
					+ "\"reply\": \"" + list.get(i).getReply() + "\", "
					+ "\"boardNum\":" + list.get(i).getBoardNum() + ","
					+ "\"boardNum\": \"" + list.get(i).getReplyDate() + "\" }";
			
			System.out.print("JSON: " + json);
			
			if ( i != list.size() - 1) {
				json += ",";
			}
		}
		json += "]";	// json 파일은 배열이므로 시작과 끝을 `[`와 `]`으로 마무리.
		
		response.getWriter().print(json);
		System.out.println("출력 완료");
	}
}