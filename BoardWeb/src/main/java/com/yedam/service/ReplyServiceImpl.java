package com.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.common.SearchDTO;
import com.yedam.mapper.ReplyMapper;
import com.yedam.vo.ReplyVO;

// [5-10] 인터페이스를 상속받는 impl 클래스 생성
public class ReplyServiceImpl implements ReplyService {

	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);

	@Override
	public List<ReplyVO> selectList(SearchDTO search) {
		return mapper.selectList(search.getBoardNo());	// 기존 사용
//		return mapper.selectListPaging(search);		
	}
	
	@Override
	public boolean deleteReply(int bno) {	// [5-f]
		return mapper.deleteReply(bno) == 1;
	}

	@Override
	public boolean removeDeleteReplys(String[] array) {
		return mapper.deleteReplys(array) > 0;
	}
	
	@Override
	public boolean addReply(ReplyVO reply) {	
		return mapper.addReply(reply) == 1;
	}
	
	@Override
	public int selectKey() {
		return mapper.selectKey();
	}
	
	@Override
	public int getReplyCount(int bno) {
		return mapper.selectReplyCount(bno);
	}
	
	@Override
	public List<ReplyVO> replyList(SearchDTO search) {
		return mapper.selectList(search.getBoardNo());	// 기존 사용
	}
	
}
