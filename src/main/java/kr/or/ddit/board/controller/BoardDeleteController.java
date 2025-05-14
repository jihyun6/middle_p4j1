package kr.or.ddit.board.controller;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.travelplan.service.IPlanService;
import kr.or.ddit.travelplan.service.PlanServiceImpl;
import kr.or.ddit.vo.BoardVo;

@WebServlet("/boardDelete.do")
public class BoardDeleteController extends HttpServlet{
	
	IBoardService boardService = BoardServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String boardNoStr = req.getParameter("boardNo");
		int boardNo = Integer.parseInt(boardNoStr);
		
		String codeNoStr = req.getParameter("codeNo");
		int codeNumber = Integer.parseInt(codeNoStr);
		
		System.out.println(boardNo);
		
		BoardVo board = new BoardVo();
		board.setBoardNo(boardNo);
		
		boardService.boardDelete(board);
		
		if(codeNumber == 3) {
			String contentNo = req.getParameter("contentNo");			
			resp.sendRedirect(req.getContextPath()+"/contentsDetail.do?contentNo=" + contentNo);
		}else {
			resp.sendRedirect(req.getContextPath()+"/boardList.do?codeNo=" + codeNumber);
		}
	}
}
