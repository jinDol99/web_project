package com.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.control.AddFormControl;
import com.yedam.control.AddMemberControl;
import com.yedam.control.GetMemberControl;
import com.yedam.control.IntroControl;
import com.yedam.control.MainControl;
import com.yedam.control.MemberListControl;
import com.yedam.control.ModFormControl;
import com.yedam.control.ModifyMemberControl;
import com.yedam.control.SubControl;

// url에서 맨 마지막이 ".do"로 끝나는 모든 url은 모두 아래 코드를 실행
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	
	// == URL Pattern == //
	// URL 뒤에 붙는 패턴에 따라 실행되는 코드를 한 곳에 모아 관리
	// map 컬랙션을 활용하여 저장함.
	Map<String, Control> map;
	
	public FrontController() {
		System.out.println("FrontController 생성자");
		map = new HashMap<>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메소드");
		map.put("/main.do", new MainControl());
		map.put("/sub.do", new SubControl());
		map.put("/intro.do", new IntroControl());
		
		// 기능 등록.
		map.put("/addForm.do", new AddFormControl());			// 회원 등록 페이지
		map.put("/addMember.do", new AddMemberControl());		// 회원 조회 페이지
		map.put("/memberList.do", new MemberListControl());
		map.put("/getMember.do", new GetMemberControl());		// 회원 아이디로 상세조회	
		map.put("/modifyForm.do", new ModFormControl());		// 수정화면 호출
		map.put("/modifyMember.do", new ModifyMemberControl());	// 수정처리
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");					// 요청방식이 POST일 경우에 body에 포함된 문자열을 utf-8로 인코딩
		
		String uri = req.getRequestURI();					// 만약 http://localhost/BoardWeb/main.do를 접속할 경우, "/BoardWeb/main.do"를 반환. 이 부분을 URI라고 함.
		String context = req.getContextPath();				// .getContextPath()는 "/BoardWeb"을 반환시켜줌
		String page = uri.substring(context.length());		// "/BoardWeb/main.do"에서 "/BoardWeb"를 자른 값. 즉, "/main.do"를 가져옴
		System.out.println(page);
		
		
		Control control = map.get(page);					// 어떤 ".do"냐에 따라 map에 저장(대응)되어 있는 control 생성자를 지정하여 Control 객체 생성
		control.exec(req, resp);							// 해당 Control 객체의 .exec() 실행
	}
	
	
}