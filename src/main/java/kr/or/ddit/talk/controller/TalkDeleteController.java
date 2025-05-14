package kr.or.ddit.talk.controller;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.talk.service.ITalkService;
import kr.or.ddit.talk.service.TalkServiceImpl;
import kr.or.ddit.vo.BoardVo;

@WebServlet("/talkDelete.do")
public class TalkDeleteController extends HttpServlet{
	ITalkService talkService = TalkServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("delete get");
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/board/talk/talkList.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("delete post");
		
		String boardNoStr= req.getParameter("boardNo");
		int boardNo = Integer.parseInt(boardNoStr);
		
		BoardVo talk = new BoardVo();
		talk.setBoardNo(boardNo);
		
		talkService.talkDelete(talk);
		
		
		resp.sendRedirect(req.getContextPath()+"/talkList.do");
	}

}
