package com.yedam.service;

import java.util.List;

import com.yedam.common.SearchDTO;
import com.yedam.vo.BoardVO;

// [박진석 | boardSetting] 3-1.
public interface BoardService {
	List<BoardVO> boardList(SearchDTO search);				// 목록
	List<BoardVO> boardListPaging(SearchDTO search);
	
	boolean addBoard(BoardVO board);		// 추가
	boolean modifyBoard(BoardVO board);		// 수정
	boolean removeBoard(int boardNo);		// 삭제
	BoardVO getBoard(int boardNo);			// 단건조회
	
	int getTotalCnt(SearchDTO search);
}
