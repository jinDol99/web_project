package com.yedam.web;

import java.util.HashMap;
import java.util.Map;

import com.yedam.common.Control;
import com.yedam.control.AddReplysControl;
import com.yedam.control.RemoveReplyControl;
import com.yedam.control.RemoveReplysControl;
import com.yedam.control.ReplyCountControl;
import com.yedam.control.ReplyListControl;
import com.yedam.control.ReplyMenuControl;

// [5-2] 클래스는 기존의 다른 Menuxxx.java 파일을 참고할 것.
public class MenuReply {
	private static MenuReply instance = new MenuReply();
	
	private MenuReply() {}
	
	public static MenuReply getInstance() {
		return instance;
	}
	
	
	public Map<String, Control> menuMap(){
		Map<String, Control> menu = new HashMap<>();
	
		// [5-4] 댓글의 목록의 JSON 형식으로 출력하는 페이지
		menu.put("/replyList.do", new ReplyListControl());
		// [5-a] 이번에는 댓글 삭제를 해보자
		menu.put("/removeReply.do", new RemoveReplyControl());
		menu.put("/removeReplys.do", new RemoveReplysControl());
		menu.put("/addReply.do", new AddReplysControl());
		
		menu.put("/replyCount.do", new ReplyCountControl());
		
		// [11-2]댓글 작성 dataTable용
		menu.put("/replyTable.do", new ReplyMenuControl());
		
		
		
		
		
		return menu;
	}
}
