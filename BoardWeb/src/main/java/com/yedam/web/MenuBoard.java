package com.yedam.web;

import java.util.HashMap;
import java.util.Map;

import com.yedam.common.Control;
import com.yedam.control.AddFormControl;
import com.yedam.control.AddMemberControl;
import com.yedam.control.GetMemberControl;
import com.yedam.control.IntroControl;
import com.yedam.control.MainControl;
import com.yedam.control.MemberListControl;
import com.yedam.control.ModFormControl;
import com.yedam.control.ModifyMemberControl;
import com.yedam.control.RemoveMemberControl;
import com.yedam.control.SubControl;

public class MenuBoard {
	private static MenuBoard instance = new MenuBoard();
	
	private MenuBoard() {}
	
	public static MenuBoard getInstance() {
		return instance;
	}
	
//	public Map<String, Control> menuMap(){
//		Map<String, Control> menu = new HashMap<>();
//		
//		// 기능 등록.
//		map.put("/addForm.do", new AddFormControl());			// 회원 등록 페이지
//		map.put("/addMember.do", new AddMemberControl());		// 회원 조회 페이지
//		map.put("/memberList.do", new MemberListControl());
//		map.put("/getMember.do", new GetMemberControl());		// 회원 아이디로 상세조회	
//		map.put("/modifyForm.do", new ModFormControl());		// 수정화면 호출
//		map.put("/modifyMember.do", new ModifyMemberControl());	// 수정처리
//		map.put("/removeMember.do", new RemoveMemberControl());	// 삭제처리
//		
//		return menu;
	}
}
