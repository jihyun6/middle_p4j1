package kr.or.ddit.login.controller;

import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.login.service.MemberService;
import kr.or.ddit.login.service.MemberServiceImpl;
import kr.or.ddit.vo.BoardVo;

@WebServlet("/main.do/login.do")
public class mainLoginController extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login.do get 요청");
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/join/mainlogin.jsp").forward(req, resp);

	}
	
}
