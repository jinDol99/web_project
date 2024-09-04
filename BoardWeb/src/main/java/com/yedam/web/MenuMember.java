package com.yedam.web;

import java.util.HashMap;
import java.util.Map;

import com.yedam.common.Control;
import com.yedam.control.BoardControl;
import com.yedam.control.BoardListControl;
import com.yedam.control.LoginFormControl;

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
		menu.put("/board.do", new BoardControl());	// 임시로 내가 만들어봄: 회원 상세로 가는 페이지 처리
		
		// 로그인 관련
		menu.put("loginForm.do", new LoginFormControl());
		
		return menu;
	}
}
