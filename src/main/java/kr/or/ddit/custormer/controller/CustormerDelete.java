package kr.or.ddit.custormer.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.custormer.service.CustormerServiceImpl;
import kr.or.ddit.custormer.service.ICustormerService;
import kr.or.ddit.vo.CustormerVo;

@WebServlet("/custormerDelete.do")
public class CustormerDelete extends HttpServlet{

	ICustormerService custormerService = CustormerServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String custormerStr = req.getParameter("custNo");
		int custNo = Integer.parseInt(custormerStr);
		System.out.println("custNo"+111111111);
		
		
		CustormerVo custor = new CustormerVo();
		custor.setCustNo(custNo);
		
		int cnt = custormerService.CustormerDelete(custor);
		
		if(cnt>0) {
			resp.sendRedirect(req.getContextPath()+"/custormerList.do");			
		}

		
	}
}
