package kr.or.ddit.login.controller;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.login.service.MemberService;
import kr.or.ddit.login.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/login1.do")
public class Login extends HttpServlet{
		
	MemberService memberService = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login.do get 요청!");
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/join/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login.do post 요청");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		System.out.println("id : "+id+", pw : "+pw);
		
		MemberVo member = new MemberVo();
		member.setMemId(id);
		member.setMemPwd(pw);
		
		member = MemberService.login(member);
		
		System.out.println("member : "+member );
		
		
		
//		if(member == null) {
//			// 로그인 실패
//			resp.sendRedirect(req.getContextPath()+"/login.do");
//		}
//		else {
//			// 로그인 성공 
//			// 세션에 등록 이름은 member
//			HttpSession session = req.getSession();
//			session.setAttribute("member", member);
//			resp.sendRedirect(req.getContextPath()+"/main.do");
//		}
	}
}
