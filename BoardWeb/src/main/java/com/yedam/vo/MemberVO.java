package com.yedam.vo;

import java.util.Date;

import lombok.Data;

// lombok 활용: getter 및 setter, toString, 메소드 생성에 도움
// lombok 설치 후 라이브러리 pom에 추가
// 추가 후 아래 한개의 어노테이션을 사용하면 굳이 getter, setter, toString 등의 기본 생성자와 메소드를 추가하지 않아도 곧바로 사용 가능함.
@Data
public class MemberVO {
	private String memberId;
	private String memberName;
	private String password;
	private String email;
	private String authority;
	private Date creationDate;
}
