package com.yedam.common;

import lombok.Data;

// 페이징 계산을 위한 클래스
@Data
public class PageDTO {
	
	// 현재 페이지 정보 -> 1 .. 3 .. 10
	
	// 이전, 이후 정보 <<(1~10) 11.. 16.. 20 (21~30)>>
	int page;					// 현재 페이지
	int startPage, endPage;		// 다음&이전 페이지
	boolean prev, next;			// 이전 묶음 페이지, 다음 묶음 페이지
	
	public PageDTO (int page, int totalCnt) {	// 예) 현재 페이지: 3, 페이지당 5건 표시, 전체 건수: 76건 -> 필요 페이지건: 76/5 (나머지 있으면 올림) -> 16개
		this.page = page;
		this.endPage = (int) Math.ceil(page / 10.0) * 10;	// 20 : 마지막 페이지 계산
		this.startPage = this.endPage - 9;					// 11 : 첫번째 페이지 계산
		
		int realEnd = (int) Math.ceil(totalCnt / 5.0);		// 16
		this.endPage = this.endPage > realEnd ? realEnd : this.endPage;
		
		// 이전 묶음, 이후 묶음 버튼 활성화 여부
		prev = this.startPage > 1;	// 1, 11, 21
		next = this.endPage < realEnd ? true : false;
	}
	
}
