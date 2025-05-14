package kr.or.ddit.login.controller;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.login.service.MemberServiceImpl;

@WebServlet("/nickcheck.do1")
public class NickCheck extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public NickCheck() {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String usernick = req.getParameter("nick");
		//usernick : 채성실
		System.out.println("usernick : "+usernick);
		
		MemberServiceImpl service = MemberServiceImpl.getServiceImpl();
		System.out.println("service : "+service);
		
		String result2 = service.nickCheck(usernick);
		System.out.println("result2 : "+result2);
		
		JSONObject jobj = new JSONObject();
		
		jobj.put("flag2", result2);
		
		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jobj);
	}
}
