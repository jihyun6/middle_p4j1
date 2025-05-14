package kr.or.ddit.login.controller;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.login.service.MemberServiceImpl;

@WebServlet("/emailckeck.do1")
public class EmailCheck extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public EmailCheck() {
		
	}
	
	// /P4J1_Project/idCheck.do?email=jadasff
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String useremail = req.getParameter("email");
		//useremail : JJ@GC.COM
		System.out.println("useremail : " + useremail);
		
		MemberServiceImpl service = MemberServiceImpl.getServiceImpl();
		
		String result3 = service.emailCheck(useremail);
		
		//result3 : JJ@GC.COM
		System.out.println("result3 : " + result3);
		
		JSONObject jobj3 = new JSONObject();
		
		jobj3.put("flag3", result3);
		
		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jobj3);
	}
}
