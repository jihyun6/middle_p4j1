package kr.or.ddit.custormer.controller;

import java.io.IOException;

import org.json.JSONObject;

import com.google.gson.Gson;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.custormer.service.CustormerServiceImpl;
import kr.or.ddit.custormer.service.ICustormerService;
import kr.or.ddit.vo.CustormerVo;

@WebServlet("/custormerUpdate.do")
public class CustormerUpdate extends HttpServlet{

	ICustormerService custormerService = CustormerServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String custormer = req.getParameter("custNo");
		
		CustormerVo vo = new CustormerVo();
		int custNo = Integer.parseInt(custormer);
		vo.setCustNo(custNo);
		
		vo = custormerService.CustormerDetail(vo);
		
		
		req.setAttribute("custormerList", vo);
		req.setAttribute("custNo", custNo);
		req.setAttribute("custTitle", vo.getCustTitle());
		req.setAttribute("custDate", vo.getCustDate());
		req.setAttribute("custContent", vo.getCustContent());
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/custormer/custormerUpdate.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("---------------------------------------");
		Gson gson = new Gson();
		
		String JsonStr = gson.toJson(req.getParameterMap());
		
		System.out.println("---------------------------------------");
		System.out.println(JsonStr);
		
		JsonStr = JsonStr.replace("[", "");
		JsonStr = JsonStr.replace("]", "");
		
		CustormerVo custormer = gson.fromJson(JsonStr, CustormerVo.class);
		
		int result = custormerService.CustormerUpdate(custormer);
		
		JSONObject jobj = new JSONObject();
		jobj.put("url", "/custormerList.do");
		
		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jobj);
	}
}
