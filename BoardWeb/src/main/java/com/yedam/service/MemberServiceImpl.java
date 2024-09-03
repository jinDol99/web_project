package com.yedam.service;

import com.yedam.vo.MemberVO;

public class MemberServiceImpl implements MemberService{
//	SqlSession sqlSession
	
	mapper = new selectMember();
	
	@Override
	public List<MemberVO> getMembers() {
		return mapper.memberList();
	}
	
	@Override
	public boolean addMemeber(MemberVO member) {
		if(mapper.selectMember(member.getMemberId()) != null) {
			return false;		// 이미 존재하는 아이디
		}
		return mapper.insertMember(member) == 1;
	}
	
	@Override
	private boolean removeMember(String memberId) {
		return mapper.deleteMember(memberId) == 1;
	}
	
	@Override
	private boolean modifyMember(MemberVO member) {
		return mapper.modifyMember(memberId) == 1;
	}
	
	@Override
	public MemberVO getMember(String memberId) {
		return mapper.selectMember(MemberId);
	}
}
