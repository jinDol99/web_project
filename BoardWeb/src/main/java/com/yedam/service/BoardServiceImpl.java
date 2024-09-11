package com.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.common.SearchDTO;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

// [박진석 | boardSetting] 3-2.
public class BoardServiceImpl implements BoardService {
	
	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
	
	
	@Override
	public List<BoardVO> boardList(SearchDTO search) {
//		return mapper.selectListPaging(search);
		// [10-2] 모든 게시글을 가져오기 위해 selectListPaging이 아닌 그냥 selectList를 호출하도록 변경
		return mapper.selectList();
	}

	@Override
	public boolean addBoard(BoardVO board) {
		return mapper.updateBoard(board) == 1;
	}

	@Override
	public boolean modifyBoard(BoardVO board) {
		return mapper.updateBoard(board) == 1;
	}

	@Override
	public boolean removeBoard(int boardNo) {
		return mapper.deleteBoard(boardNo) == 1;
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		return mapper.selectBoard(boardNo);
	}
	
	// 페이징을 위해 전체 건 수 반환
	@Override
	public int getTotalCnt(SearchDTO search) {
		return mapper.selectCount(search);
	}

	@Override
	public List<BoardVO> boardListPaging(SearchDTO search) {
		return mapper.selectListPaging(search);
	}
}
