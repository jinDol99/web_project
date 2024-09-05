package com.yedam.vo;

import java.util.Date;

import lombok.Data;

// [박진석 | boardSetting] 1-3.
@Data
public class BoardVO {
	private int boardNo;
	private String title;
	private String content;
	private String writer;
	private int viewCnt;
	private Date creationDate;
	private String image;
	
}
