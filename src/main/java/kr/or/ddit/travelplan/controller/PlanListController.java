package kr.or.ddit.travelplan.controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.travelplan.service.IPlanService;
import kr.or.ddit.travelplan.service.PlanServiceImpl;
import kr.or.ddit.vo.BoardVo;

@WebServlet("/planList.do")
public class PlanListController extends HttpServlet{
	
	
	IPlanService planService = PlanServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		BoardVo board = new BoardVo();
//		List<BoardVo> planList= planService.planList(board);
//		
//		req.setAttribute("planList", planList);
//		
//		ServletContext ctx = req.getServletContext();
//		ctx.getRequestDispatcher("/WEB-INF/view/board/plan/planList.jsp").forward(req, resp);
	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		String title = req.getParameter("title");
//		String travelStart = req.getParameter("travelStart");
//		String travelEnd = req.getParameter("travelEnd");
//		
//
//		BoardVo plan = new BoardVo();
//		plan.setBoardName("title");
//		
//		
//		planService.planList(plan);
//		
//}
}