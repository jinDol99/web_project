package com.yedam.mapper;

import java.util.List;

import com.yedam.vo.BoardVO;

// tbl_board 테이블과 관련된 
public interface BoardMapper {
	List<BoardVO> selectList();		// [박진석 | boardSetting] 2-1.
	List<BoardVO> selectListPaging(int page);	// n페이지씩 일부만 보이게 하는 전체 출력
	int insertBoard(BoardVO board);
	int updateBoard(BoardVO board);
	int deleteBoard(int boardNo);
	BoardVO selectBoard(int boardNo);
	
	// 페이징 계산을 위한 전체 건수
	int selectCount();
}
