package com.yedam.web;

import java.io.IOException;
import java.io.PrintWriter;

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

// url에서 마지막에 "/AddMemberServlet"이 포함되어 있다면 아래 코드 실행
@WebServlet("/AddMemberServlet")
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddMemberServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");		// 브라우저에서 한글을 사용할 수 있게끔 처리
		
		String id = request.getParameter("id");			// 웹 브라우저(사용자)의 요청정보 중 id 값을 읽어들임
		String name = request.getParameter("name");		// 요청정보 중 name 값을 읽어들임
		String pw = request.getParameter("pass");		// 이하 동일
		String mail = request.getParameter("email");
		
		MemberVO mvo = new MemberVO();
		mvo.setMemberId(id);
		mvo.setMemberName(name);
		mvo.setPassword(pw);
		mvo.setEmail(mail);
		
		SqlSessionFactory factory = DataSource.getInstance();
		SqlSession session = factory.openSession(true);
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		if(mapper.insertMember(mvo) == 1) {
			PrintWriter out = response.getWriter();		// 출력 스트림을 반환
			out.print("등록됨");						// 웹 브라우저에게 전달
		}
		
		// http://localhost/BoardWeb/AddMemberServlet?id=user05&name=newbie&pass=1111&email=newbie@email.com
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}