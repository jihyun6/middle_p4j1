package kr.or.ddit.custormer.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.custormer.service.CustormerServiceImpl;
import kr.or.ddit.custormer.service.ICustormerService;
import kr.or.ddit.vo.CustormerVo;

@WebServlet("/custormerList.do")
public class CustormerController extends HttpServlet{
	private ICustormerService CustormerService;
	
	 @Override
	    public void init() throws ServletException {
		 CustormerService = CustormerServiceImpl.getInstance();
	    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		CustormerVo custormer = (CustormerVo) session.getAttribute("custormer");
		
		
		List<CustormerVo> custormerList = CustormerService.CustormerList(custormer);
		System.out.println(custormerList+"2222222222222");
		
		req.setAttribute("custormerList", custormerList);
		req.getRequestDispatcher("/WEB-INF/view/custormer/custormerList.jsp").forward(req, resp);
		
	}
}
