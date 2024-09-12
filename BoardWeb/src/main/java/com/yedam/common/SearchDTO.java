package com.yedam.common;

import lombok.Data;

// 검색 조건을 담기 위한 클래스
@Data
public class SearchDTO {
	private String searchCondition;
	private String keyword;
	private int page;
	// [9-3] 
	private int boardNo;
	
	String title;
	String Start;
	String end;
}
