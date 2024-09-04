package com.yedam.web;

import java.util.HashMap;
import java.util.Map;

import com.yedam.common.Control;
import com.yedam.control.BoardListControl;

public class MenuMember {
	private static MenuMember instance = new MenuMember();
	
	private MenuMember() {}
	
	public static MenuMember getInstance() {
		return instance;
	}
	
	
	public Map<String, Control> menuMap(){
		Map<String, Control> menu = new HashMap<>();
		
//		// 기능 등록.
//		map.put("/addForm.do", new AddFormControl());			// 회원 등록 페이지
//		map.put("/addMember.do", new AddMemberControl());		// 회원 조회 페이지
//		map.put("/memberList.do", new MemberListControl());
//		map.put("/getMember.do", new GetMemberControl());		// 회원 아이디로 상세조회	
//		map.put("/modifyForm.do", new ModFormControl());		// 수정화면 호출
//		map.put("/modifyMember.do", new ModifyMemberControl());	// 수정처리
//		map.put("/removeMember.do", new RemoveMemberControl());	// 삭제처리
		menu.put("/boardList.do", new BoardListControl());
		
		return menu;
	}
}
