package kr.or.ddit.mypage.controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.mypage.service.IMypageService;
import kr.or.ddit.mypage.service.MypageServiceImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/mypageTalkList.do")
public class MypageTalkListController extends HttpServlet{

	IMypageService mypageService = MypageServiceImpl.getInstance(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("mypageTalkList.do Get실행");

		HttpSession session = req.getSession();
    	MemberVo member = (MemberVo) session.getAttribute("member");
    	
    	String memNoStr = req.getParameter("memNo");
		int memNo = Integer.parseInt(memNoStr);
		
    	List<BoardVo> boardList = mypageService.mypageTalkList(memNo);
    	
    	req.setAttribute("member", memNo);
		req.setAttribute("boardList", boardList);
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/board/talk/talkList.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
		 * HttpSession session = req.getSession(); MemberVo member = (MemberVo)
		 * session.getAttribute("member"); req.setAttribute("member", member);
		 */
		
		System.out.println("mypageTalkList.do doPost실행");
		
		String memNoStr = req.getParameter("memNo");
		int memNo = Integer.parseInt(memNoStr);
		
		System.out.println("memNo : "+ memNo);
		
		List<BoardVo> boardList = mypageService.mypageTalkList(memNo);
		req.setAttribute("boardList", boardList);
		
		/*
		 * JSONObject jObj = new JSONObject(); jObj.put("memNo", memNo);
		 * 
		 * resp.setContentType("application/x-json; charset=utf-8");
		 * resp.getWriter().print(jObj);
		 */
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/board/talk/talkList.jsp");
		dispatcher.forward(req, resp); 
		
		
	
	}
}
