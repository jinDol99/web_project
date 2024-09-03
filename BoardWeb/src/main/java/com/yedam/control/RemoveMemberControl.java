package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.mapper.MemberMapper;

public class RemoveMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html;charset=utf-8");
		
		String id = req.getParameter("id");
		
		SqlSessionFactory factory = DataSource.getInstance();
		SqlSession session = factory.openSession(true);
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		
		if (mapper.deleteMember(id) == 1) {
			resp.sendRedirect("memberList.do");
		} else {
			req.setAttribute("message", id + "삭제할 정보가 없습니다.");
			req.getRequestDispatcher("WEB-INF/html/modifyForm.jsp").forward(req, resp);
		}

	}

}
