package kr.or.ddit.talk.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.talk.service.ITalkService;
import kr.or.ddit.talk.service.TalkServiceImpl;
import kr.or.ddit.vo.BoardVo;
@WebServlet("/talkList.do")
public class TalkListController extends HttpServlet{
	
	ITalkService talkService = TalkServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("talkList실행");
		
		BoardVo talk = new BoardVo();
		List<BoardVo> talkList =talkService.talkList(talk);
		req.setAttribute("talkList", talkList);
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/board/talk/talkList.jsp").forward(req, resp);
	}
}
