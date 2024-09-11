package com.yedam.service;

import java.util.List;

import com.yedam.common.SearchDTO;
import com.yedam.vo.ReplyVO;

// [5-9] 서비스 인터페이스 생성 및 추상메소드 생성
public interface ReplyService {
	List<ReplyVO> selectList(SearchDTO search);
	boolean deleteReply(int rno);		// [5-e] 삭제 메소드 
	boolean removeDeleteReplys(String[] array);	// 여러 건 삭제 메소드
	boolean addReply(ReplyVO reply);
	int selectKey();
	
	int getReplyCount(int bno);	// [9-14] 댓글 건수
	List<ReplyVO> replyList(SearchDTO search);
}
