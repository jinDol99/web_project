package com.yedam.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.common.SearchDTO;
import com.yedam.vo.ReplyVO;

//[5-5] 관련 매퍼 생성
public interface ReplyMapper {
	List<ReplyVO> selectList(int bno);	// 댓글 목록
	// [9-3]
	List<ReplyVO> selectListPaging(SearchDTO search);	// 댓글 목록
	int deleteReply(int rno); 			// [5-c] 댓글 삭제
	int deleteReplys(String[] rno);
	int addReply(ReplyVO reply);
	int selectKey();
	
	int selectReplyCount(int bno);	// [9-13] 댓글 건수
	
	
	// -------------------------------------------
	// (임시) [12-2] FullCalendar 일정 관련 매퍼
	List<Map<String, Object>> selectEvent();
	int insertEvent(SearchDTO event);
	int deleteEvent(SearchDTO event);
	
	// FullCalendar 일정 관련 매퍼 끝
	// -------------------------------------------
}
