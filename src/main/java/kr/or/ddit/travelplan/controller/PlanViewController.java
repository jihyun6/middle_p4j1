package kr.or.ddit.travelplan.controller;

import java.io.IOException;

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
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.PlanContVo;

@WebServlet("/planView.do")
public class PlanViewController extends HttpServlet{

	IPlanService planService = PlanServiceImpl.getInstance();
	IplanContService plcoService = PlanContServiceImpl.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String boardNoStr = req.getParameter("boardNo");
		int boardNo = Integer.parseInt(boardNoStr);
		
		BoardVo board = new BoardVo();
		board.setBoardNo(boardNo);
		
		BoardVo planView = planService.planView(board);
		System.out.println(planView);
		PlanContVo plco = new PlanContVo();
//		PlanContVo plcoView = plcoService.planContList(plco);
		req.setAttribute("planView", planView);
		
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/board/plan/planView.jsp").forward(req, resp);
		
	}
	
}
