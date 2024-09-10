package com.yedam.mapper;

import java.util.List;

import com.yedam.vo.ReplyVO;

//[5-5] 관련 매퍼 생성
public interface ReplyMapper {
	List<ReplyVO> selectList(int bno);	// 댓글 목록
	int deleteReply(int rno); 			// [5-c] 댓글 삭제
	int deleteReplys(String[] rno);
	int addReply(ReplyVO reply);
	int selectKey();
}
