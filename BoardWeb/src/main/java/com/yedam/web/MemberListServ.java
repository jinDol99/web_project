package com.yedam.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;
import com.yedam.mapper.MemberMapper;
import com.yedam.vo.MemberVO;

// == 웹 서버의 클라이언트 요청 처리 순서 (교안 92p.) == //
// 1. 클라이언트의 페이지 요청 발생
// 2. 웹 서버가 전송받음 : init()
// 3. 서블릿 컨테이너(톰캣) 활성화 : memberList.action
// 4. 요청과 응답 객체 생성 후 관련 내용 처리: HttpServletRequest, HttpServletResponse
// 5. 4에서 만들어뒀던 요청 및 응답 정보를 담아 service() 처리


// == 서블릿의 생명주기 == //
// 1. init() : 서버 최초 실행 시 한 번만 실행
// 2. service() : 그 후 service()에서 계속 실행되어짐. 페이지 새로고침을 해도 init()이 안 호출되고 service()만 호출
// 3. destroy() : 서버 종료 시 실행

@WebServlet("/memberList.action")
public class MemberListServ extends HttpServlet{
	String iWantGoHome = "집가고싶다";

	public MemberListServ() {
		System.out.println("MemberListServ 생성자 호출");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init 메소드 호출");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service 메소드 호출");
		resp.setContentType("text/html;charset=utf-8");			// 현재 페이지의 타입을 HTML로 명시하여 태그를 사용할 수 있게끔 설정
		
		SqlSessionFactory factory = DataSource.getInstance();
		SqlSession session = factory.openSession(true);
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		List<MemberVO> list = mapper.memberList();
		
		String str = "<ul>";
		for (MemberVO member : list) {
			str += "<li>" + member.getMemberId() + " | " + member.getMemberName() + "</li>";
			
		}
		str += "</ul>";		
		
		resp.getWriter().print(str);
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy 메소드 호출");
	}
	
}