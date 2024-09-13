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
import com.yedam.common.DebugUtil;
import com.yedam.control.EventControl;
import com.yedam.control.IntroControl;
import com.yedam.control.JavascriptControl;
import com.yedam.control.MainControl;
import com.yedam.control.SubControl;

// url에서 맨 마지막이 ".do"로 끝나는 모든 url은 모두 아래 코드를 실행
//@WebServlet("*.do")
@WebServlet()
public class FrontController extends HttpServlet {
	
	// == URL Pattern == //
	// URL 뒤에 붙는 패턴에 따라 실행되는 코드를 한 곳에 모아 관리
	// map 컬랙션을 활용하여 저장함.
	Map<String, Control> map;
	
	public FrontController() {
		map = new HashMap<>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("[FrontController.java] init 메소드 호출 완료");
		map.put("/main.do", new MainControl());
		map.put("/sub.do", new SubControl());
		map.put("/intro.do", new IntroControl());
		map.put("/javascript.do", new JavascriptControl());
		
		//--------------------------------------------
		// [12-6] FullCalendar 관련 URI - 컨트롤 매핑
		map.put("/eventList.do", new EventControl());
		map.put("/addEvent.do", new EventControl());
		map.put("/removeEvent.do", new EventControl());
		
		// FullCalendar 관련 URI - 컨트롤 매핑 끝
		//--------------------------------------------
		
		
		//--------------------------------------------
		// [12-6] Google Chart 관련 URI - 컨트롤 매핑
		map.put("/chart.do", new EventControl());
		map.put("/showChart.do", new EventControl());
		
		// Google Chart 관련 URI - 컨트롤 매핑 끝
		//--------------------------------------------
		
		
		Map<String, Control> memberMenu = MenuMember.getInstance().menuMap();
		Map<String, Control> boardMenu = MenuBoard.getInstance().menuMap();
	
		// [5-1]. 댓글 기능을 만들기 위해 새로운 컨트롤러 
		Map<String, Control> replyMenu = MenuReply.getInstance().menuMap();
		
		
		map.putAll(memberMenu);		// 멤버 관련 메뉴 추가
		map.putAll(boardMenu);		// 게시글 관련 메뉴 추가	
		map.putAll(replyMenu);		// 댓글 관련 메뉴 추가	
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");					// 요청방식이 POST일 경우에 body에 포함된 문자열을 utf-8로 인코딩
		
		String uri = req.getRequestURI();					// 만약 http://localhost/BoardWeb/main.do를 접속할 경우, "/BoardWeb/main.do"를 반환. 이 부분을 URI라고 함.
		String context = req.getContextPath();				// .getContextPath()는 "/BoardWeb"을 반환시켜줌
		String page = uri.substring(context.length());		// "/BoardWeb/main.do"에서 "/BoardWeb"를 자른 값. 즉, "/main.do"를 가져옴
		
		DebugUtil.printcurrVal("page", page);
		
		
		Control control = map.get(page);					// 어떤 ".do"냐에 따라 map에 저장(대응)되어 있는 control 생성자를 지정하여 Control 객체 생성
		control.exec(req, resp);							// 해당 Control 객체의 .exec() 실행
	}
	
	
}