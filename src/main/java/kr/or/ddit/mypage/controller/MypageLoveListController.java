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
import kr.or.ddit.contents.service.ContentsServiceImpl;
import kr.or.ddit.contents.service.IContentsService;
import kr.or.ddit.mypage.service.IMypageService;
import kr.or.ddit.mypage.service.MypageServiceImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.ContentsVo;
import kr.or.ddit.vo.LoveVo;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/mypageLoveList.do")
public class MypageLoveListController extends HttpServlet {

	IMypageService mypageService = MypageServiceImpl.getInstance();
	IContentsService contentsService = ContentsServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("mypageLoveList.do Get실행");

		HttpSession session = req.getSession();
    	MemberVo member = (MemberVo) session.getAttribute("member");
		
    	String memNoStr = req.getParameter("memNo");
		int memNo = Integer.parseInt(memNoStr);
    	
//    	ContentsVo content = new ContentsVo();
//    	ContentsVo contentsList = contentsService.contentsDetail(content);
    	List<LoveVo> loveList = mypageService.mypageLoveList(memNo);
    	
    	req.setAttribute("member", member);
		req.setAttribute("loveList", loveList);
//		req.setAttribute("contentsList", contentsList);
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/love/loveList.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		System.out.println("mypageLoveList.do doPost실행");
		
		String memNoStr = req.getParameter("memNo");
		int memNo = Integer.parseInt(memNoStr);

		List<LoveVo> loveList = mypageService.mypageLoveList(memNo);
		req.setAttribute("loveList", loveList);
		
		
		
		System.out.println("loveList : " + loveList);
		
		/*
		 * JSONObject jObj = new JSONObject(); jObj.put("memNo", memNo);
		 * 
		 * resp.setContentType("application/x-json; charset=utf-8");
		 * resp.getWriter().print(jObj);
		 */
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/love/loveList.jsp");
		dispatcher.forward(req, resp); 
		
		System.out.println("성공 !");
	
	
	}
	
}
