package com.yedam.control;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.common.DebugUtil;
import com.yedam.common.SearchDTO;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;


/*
 * ===== [12-7] 이번엔 관련된 컨트롤을 전부 통합하여 관리할 것임. 여기선 FullCalendar의 조회, 추가, 삭제를 한꺼번에 관리.
 *	"/eventList.do" 	-> 
 * 	"/addEvent.do" 		->
 * 	"/removeEvent.do"	->
 */

public class EventControl implements Control {
	ReplyService svc = new ReplyServiceImpl();	// 모든 서비스 메소드에서 사용할 수 있도록 전역적으로 설정
	
	
	//== 갖고온 URI를 검증하고 각 URI 값에 맞는 메소드를 실행하도록 분기점 기능을 수행하는 메소드 ==//
	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();				// 요청 URI 값에서 "/", ".do"을 분리하여 
		String context = request.getContextPath();	
		String page = uri.substring(context.length());
		
		String methodName = page.substring(1, page.indexOf("."));	// 1번 인덱스부터 "."이 들어있는 곳 까지 자름
		DebugUtil.printcurrVal("methodName", methodName);
		
		try {
			Class<?> cls = Class.forName(this.getClass().getName());
			Method method = cls.getDeclaredMethod(methodName	/// 메소드명
					, HttpServletRequest.class					/// 파라미터1
					, HttpServletResponse.class					/// 파라미터2
			);
			method.invoke(this, request, response);				/// 메소드 실행
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//== "/BoardWeb/eventList.do"가 호출되면 실행하는 컨트롤 메소드. ==//
	public void eventList(HttpServletRequest request, HttpServletResponse response){
		System.out.println("[EventControl.java] eventList 메소드 실행");
		response.setContentType("text/json;charset=utf-8");
		
		
		List<Map<String, Object>> list = svc.eventList();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list);
		
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//== "/BoardWeb/eventList.do"가 호출되면 실행하는 컨트롤 메소드. ==//
	public void addEvent(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[EventControl.java] addEvent 메소드 실행");
		
		String title = request.getParameter("title");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		SearchDTO event = new SearchDTO();
		event.setTitle(title);
		event.setStart(start);
		event.setEnd(end);
		
		try {
			if(svc.addEvent(event)) {
					response.getWriter().print("{\"retCode\": \"OK\"}");
			} else {
				response.getWriter().print("{\"retCode\": \"NG\"}");
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	//== "BoardWeb/removeEvent.do"가 호출되면 실행하는 컨트롤 메소드. ==//
	public void removeEvent(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("[EventControl.java] removeEvent 메소드 실행");
		
		String title = request.getParameter("title");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		SearchDTO event = new SearchDTO();
		event.setTitle(title);
		event.setStart(start);
		event.setEnd(end);
		
		try {
			if(svc.removeEvent(event)) {
					response.getWriter().print("{\"retCode\": \"OK\"}");
			} else {
				response.getWriter().print("{\"retCode\": \"NG\"}");
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
