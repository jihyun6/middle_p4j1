package kr.or.ddit.travelplan.controller;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.travelplan.service.IPlanService;
import kr.or.ddit.travelplan.service.PlanServiceImpl;
import kr.or.ddit.vo.BoardVo;

@WebServlet("/planDelete.do")
public class PlanDeleteController extends HttpServlet{

	
	IPlanService planService = PlanServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String boardNoStr = req.getParameter("boardNo");
		int boardNo = Integer.parseInt(boardNoStr);
		
		System.out.println(boardNo);
		
		BoardVo board = new BoardVo();
		board.setBoardNo(boardNo);
		
		planService.planDelete(board);
		
		resp.sendRedirect(req.getContextPath()+"/planList.do");
		
	}
	
}
