package com.yedam.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataSource {
	// SqlSessionFactory 생성
	public static SqlSessionFactory getInstance() {
		
		// 아래 코드는 직접 입력하지 말고, Mybatis 홈페이지의 코드를 복붙하자.
		// https://mybatis.org/mybatis-3/ko/getting-started.html#xml%EC%97%90%EC%84%9C-sqlsessionfactory-%EB%B9%8C%EB%93%9C%ED%95%98%EA%B8%B0
		
		// 3줄짜리 코드를 복붙한 뒤, import, try-catch만 설정하자.
		String resource = "config/mybatis-config.xml";	
		InputStream inputStream = null;		// null로 초기화하는 부분 추가
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		return sqlSessionFactory;
	}
}

// 모든 설정이 끝났으면 이제는 xml을 설정하러 가자!
