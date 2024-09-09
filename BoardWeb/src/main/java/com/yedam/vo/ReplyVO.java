package com.yedam.vo;

import java.util.Date;
import lombok.Data;

// [5-6] 관련 VO 생성: 어노테이션 반드시 지정!
@Data
public class ReplyVO {
	private int replyNo;
	private String replyer;
	private String reply;
	private int boardNum;
	private Date replyDate;
	
}
