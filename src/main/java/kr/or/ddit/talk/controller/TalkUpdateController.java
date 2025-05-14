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

@WebServlet("/talkUpdate.do")
public class TalkUpdateController extends HttpServlet{
	
	ITalkService talkService = TalkServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("update get");
		
		String boardNoStr= req.getParameter("boardNo");
		int boardNo = Integer.parseInt(boardNoStr);
		
		BoardVo talk = new BoardVo();
		talk.setBoardNo(boardNo);
		talk=talkService.talkDetail(talk);
		
		req.setAttribute("talk", talk);
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/board/talk/talkUpdate.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("update post");
		
		//로그인-멤버 정보 불러오기
//		String memNoStr = req.getParameter("memNo");
//		int memNo = Integer.parseInt(memNoStr);
		
		String boardNoStr = req.getParameter("boardNo");
		int boardNo = Integer.parseInt(boardNoStr);
		
		String boardName = req.getParameter("boardName");
		String boardContent = req.getParameter("boardContent");
		String boardTag = req.getParameter("boardTag");
		String boardStarStr = req.getParameter("boardStar");
		int boardStar = Integer.parseInt(boardStarStr);
		//필요하면 추가
//		String trialAreacodeStr = req.getParameter("trialAreacode");
//		int trialAreacode = Integer.parseInt(trialAreacodeStr);
		
		//캘린더 연동
//		String travelStart = req.getParameter("travelStart");
//		String travelEnd = req.getParameter("travelEnd");
		
		//작성내용 담아주기
		BoardVo talk = new BoardVo();
//		talk.setMemNo(memNo);
		talk.setBoardNo(boardNo);
		talk.setBoardName(boardName);
		talk.setBoardContent(boardContent);
		talk.setBoardTag(boardTag);
		talk.setBoardStar(boardStar);
//		talk.setTrialAreacode(trialAreacode);
//		talk.setTravelStart(travelStart);
//		talk.setTravelEnd(travelEnd);

		talkService.talkUpdate(talk);
		
		resp.sendRedirect(req.getContextPath()+"/talkDetail.do?boardNo="+boardNo);

	}
}
