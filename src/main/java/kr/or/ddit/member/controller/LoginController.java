package kr.or.ddit.member.controller;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/login.do")
public class LoginController extends HttpServlet{
	
	IMemberService memberService = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("login.do Get 요청??????");
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("login.do Post 요청");
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		MemberVo member = new MemberVo();
		
		member.setMemId(id);
		member.setMemPwd(pw);
		
		member = memberService.login(member);
		
		JSONObject jObj = new JSONObject();
		
		System.out.println("??????");
		//로그인 실패한경우
		if( member == null) {
			jObj.put("status", "no"); // key, value
		}
		
		else {
			//로그인 성공
			HttpSession session = req.getSession(true);
			session.setAttribute("member", member);
			jObj.put("url", "/main.do");
			jObj.put("status", "ok");
			System.out.println(session.getAttribute("member"));
		}
		
		
		resp.setContentType("a"
				+ "plication/x-json; charset=utf-8");
		resp.getWriter().print(jObj);
		
		//resp.sendRedirect(req.getContextPath() + "/main.do");
	}
}