package com.yedam.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.mapper.MemberMapper;
import com.yedam.vo.MemberVO;

public class ModifyMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		resp.setContentType("text/html;charset=utf-8");

		// 파라미터 4개 값을 읽어 db 반영 -> 목록으로 이동
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pw = req.getParameter("pass");
		String mail = req.getParameter("email");

		MemberVO mvo = new MemberVO();
		mvo.setMemberId(id);
		mvo.setMemberName(name);
		mvo.setPassword(pw);
		mvo.setEmail(mail);

		SqlSessionFactory factory = DataSource.getInstance();
		SqlSession session = factory.openSession(true);
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		try {
			if (mapper.updateMember(mvo) == 1) {
				resp.sendRedirect("memberList.do");
			} else {
				req.setAttribute("message", id + "수정할 정보가 없습니다.");
				req.getRequestDispatcher("WEB-INF/html/modifyForm.jsp").forward(req, resp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
	}

}
