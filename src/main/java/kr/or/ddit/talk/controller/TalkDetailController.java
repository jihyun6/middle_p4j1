package kr.or.ddit.talk.controller;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.love.service.ILoveService;
import kr.or.ddit.love.service.LoveServiceImpl;
import kr.or.ddit.talk.service.ITalkService;
import kr.or.ddit.talk.service.TalkServiceImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.LoveVo;
@WebServlet("/talkDetail.do")
public class TalkDetailController extends HttpServlet{
	
	ITalkService talkService = TalkServiceImpl.getInstance();
	ILoveService loveService = LoveServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("detail 실행");
		
		String boardNoStr = req.getParameter("boardNo");
		int boardNo = Integer.parseInt(boardNoStr);
		String loveNoStr = req.getParameter("loveNo");
		int loveNo = Integer.parseInt(loveNoStr);
		
		BoardVo talk = new BoardVo();
		talk.setBoardNo(boardNo);

		talk=talkService.talkDetail(talk);
		req.setAttribute("talk", talk);
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/board/talk/talkDetail.jsp").forward(req, resp);
	}
}
