package kr.or.ddit.planCont.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.planCont.service.IplanContService;
import kr.or.ddit.planCont.service.PlanContServiceImpl;
import kr.or.ddit.travelplan.service.IPlanService;
import kr.or.ddit.travelplan.service.PlanServiceImpl;
import kr.or.ddit.vo.PlanContVo;
@WebServlet("/planCont.do")
public class PlanContController extends HttpServlet{
	
	IplanContService plcoService = PlanContServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("plco get 실행");
		
		PlanContVo plco = new PlanContVo();
		List<PlanContVo> planConList=plcoService.planContList(plco);
		req.setAttribute("planConList", planConList);
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/").forward(req, resp);
				}
}
