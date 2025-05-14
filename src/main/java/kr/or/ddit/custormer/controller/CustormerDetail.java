package kr.or.ddit.custormer.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.custormer.service.CustormerServiceImpl;
import kr.or.ddit.custormer.service.ICustormerService;
import kr.or.ddit.vo.CustormerVo;

@WebServlet("/custormerDetail.do")
public class CustormerDetail extends HttpServlet{

	private ICustormerService custormerService;
	
	
	 @Override
	    public void init() throws ServletException {
		 custormerService = CustormerServiceImpl.getInstance();
	   }
	 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		CustormerVo custormer = (CustormerVo)
		session.getAttribute("custormer");
		
		System.out.println("custormer"+custormer);
		
		String custormers = req.getParameter("custNo");
		CustormerVo vo = new CustormerVo();
		int custNo = Integer.parseInt(custormers);

		vo.setCustNo(custNo);
		vo = custormerService.CustormerDetail(vo);
		
		CustormerVo custNo2 = custormerService.CustormerDetail(custormer);
		
		req.setAttribute("custNo", custNo);
		req.setAttribute("custTitle", vo.getCustTitle());
		req.setAttribute("custDate", vo.getCustDate());
		req.setAttribute("custContent", vo.getCustContent());
		
		req.getRequestDispatcher("/WEB-INF/view/custormer/custormerDetail.jsp").forward(req, resp);
	}
}
