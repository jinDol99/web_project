package com.yedam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.vo.MemberVO;

public interface MemberMapper {
	List<MemberVO> memberList();
	int insertMember(MemberVO mvo);
	int updateMember(MemberVO member);
	int deleteMember(String memberId);
	MemberVO selectMember(String memberId);
	
	// id, pw 로그인 정보 확인
	// `@Param` 어노테이션을 사용하면 뒤에 붙는 이름으로 SQL 쿼리(xml)에서 사용 가능한 이름으로 지정 가능함
	MemberVO loginMember(@Param("id") String id, @Param("pw") String pw);
	
}
