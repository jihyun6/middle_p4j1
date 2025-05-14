package kr.or.ddit.travelplan.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.contents.service.ContentsServiceImpl;
import kr.or.ddit.contents.service.IContentsService;
import kr.or.ddit.travelplan.service.IPlanService;
import kr.or.ddit.travelplan.service.PlanServiceImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.ContentsVo;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/planInsert.do")
public class PlanInsertController extends HttpServlet{

	IPlanService planService = PlanServiceImpl.getInstance();
	IContentsService contentsService = ContentsServiceImpl.getInstance();
	IBoardService boardService = BoardServiceImpl.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/*
		 * System.out.println("planInsert.do doGet 실행!");
		 * 
		 * HttpSession session = req.getSession(); MemberVo member = (MemberVo)
		 * session.getAttribute("member");
		 * 
		 * BoardVo board = new BoardVo();
		 * 
		 * req.setAttribute("boardList", board); req.setAttribute("member", member);
		 */
		
		
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/board/plan/planInsert.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("planInsert.do doPost 실행");
		
//		String codeNoStr = req.getParameter("codeNumber");
//		int codeNo = Integer.parseInt(codeNoStr);
		
		String contentNo = req.getParameter("contentNo");
		
		String contentsTypeIdStr = req.getParameter("contentsTypeId");
		int contentsTypeId = Integer.parseInt(contentsTypeIdStr);
		
		String conTitle = req.getParameter("contTitle");
		
		BoardVo board = new BoardVo();
//		board.setCodeNumber(codeNo);
		board.setContentNo(contentNo);
		board.setContentsTypeId(contentsTypeId);
		
		planService.planInsert(board);
		req.setAttribute("board", board);

		System.out.println("board : "+ board);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/board/plan/planInsert.jsp");
		dispatcher.forward(req, resp); 
	}
}
	

