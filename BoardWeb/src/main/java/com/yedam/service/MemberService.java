package com.yedam.service;

import java.util.List;

import com.yedam.vo.MemberVO;

// 기능의 정의
public interface MemberService {
	List<MemberVO> getMembers();
	boolean addMember(MemberVO member);		// 목록
	boolean removeMember(String memberId);	// 추가
	boolean modifyMember(MemberVO member);	// 수정
	MemberVO getMember(String memberId); 	// 단건 조회
}

