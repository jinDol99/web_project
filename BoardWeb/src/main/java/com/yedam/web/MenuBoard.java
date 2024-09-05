package com.yedam.web;

import java.util.HashMap;
import java.util.Map;

import com.yedam.common.Control;
import com.yedam.control.AddBoardControl;
import com.yedam.control.AddFormControl;
import com.yedam.control.AddMemberControl;
import com.yedam.control.BoardControl;
import com.yedam.control.BoardFormControl;
import com.yedam.control.GetMemberControl;
import com.yedam.control.MemberListControl;
import com.yedam.control.ModFormControl;
import com.yedam.control.ModifyMemberControl;
import com.yedam.control.RemoveBoardControl;
import com.yedam.control.RemoveMemberControl;

public class MenuBoard {
	private static MenuBoard instance = new MenuBoard();
	
	private MenuBoard() {}
	
	public static MenuBoard getInstance() {
		return instance;
	}
	
	
	public Map<String, Control> menuMap(){
		Map<String, Control> menu = new HashMap<>();
		
//		// 기능 등록.
		menu.put("/addForm.do", new AddFormControl());			// 회원 등록 페이지
		menu.put("/addMember.do", new AddMemberControl());		// 회원 조회 페이지
		menu.put("/memberList.do", new MemberListControl());
		menu.put("/getMember.do", new GetMemberControl());		// 회원 아이디로 상세조회	
		menu.put("/modifyForm.do", new ModFormControl());		// 수정화면 호출
		menu.put("/modifyMember.do", new ModifyMemberControl());	// 수정처리
		menu.put("/removeMember.do", new RemoveMemberControl());	// 삭제처리
		
		menu.put("/removeBoard.do", new RemoveBoardControl());	// 게시글 삭제 처리
		menu.put("/addBoard.do", new AddBoardControl());	// 게시글 신규 작성 처리
		menu.put("/addboardForm.do", new BoardFormControl());	// 게시글 신규 작성 호출
		
		
		return menu;
	}
}
