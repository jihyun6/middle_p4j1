package kr.or.ddit.member.controller;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.member.service.MemberServiceImpl;

@WebServlet("/emailcheck.do")
public class EmailCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberServiceImpl memberService = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//userId 체크
		String email = req.getParameter("email");
		System.out.println("email : " + email);
		
		String result = memberService.emailCheck(email);
		
		System.out.println("result" + result);
		
		
		JSONObject jObj = new JSONObject();
		jObj.put("result", result); // key, value

		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jObj);
	}
}