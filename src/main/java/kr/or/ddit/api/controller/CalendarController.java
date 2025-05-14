package kr.or.ddit.api.controller;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.travelplan.service.IPlanService;

@WebServlet("/Calendar.do")
public class CalendarController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext ctx = req.getServletContext();
			ctx.getRequestDispatcher("/WEB-INF/view/calendar2.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Calendar.do doPost 실행");
		
		String memNoStr = req.getParameter("memNo");
		int memNo = Integer.parseInt(memNoStr);
		
		JSONObject jObj = new JSONObject();
		jObj.put("memNo", memNo);
		
		System.out.println("jObj : " + jObj);
		
		resp.setContentType("application/x-json; charset=utf-8");	
		resp.getWriter().print(jObj);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/calendar2.jsp");
		dispatcher.forward(req, resp); 
		
		System.out.println("성공 !-!");
	
	}
}
