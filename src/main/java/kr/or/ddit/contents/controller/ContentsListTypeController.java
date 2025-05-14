package kr.or.ddit.contents.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.contents.service.ContentsServiceImpl;
import kr.or.ddit.contents.service.IContentsService;
import kr.or.ddit.love.service.ILoveService;
import kr.or.ddit.love.service.LoveServiceImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.ContentsVo;
import kr.or.ddit.vo.LoveVo;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/contentsListType.do")
public class ContentsListTypeController extends HttpServlet {
	
	IContentsService contservice =ContentsServiceImpl.getInstance();
	ILoveService loveService = LoveServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String contentNo = req.getParameter("contentNo");
		
		String contentsTypeIdStr  = req.getParameter("contentsTypeId");
		int contentsTypeId = Integer.parseInt(contentsTypeIdStr);

		
		ContentsVo contents = new ContentsVo();
		contents.setContentsTypeId(contentsTypeId);
		contents.setContentNo(contentNo);
		List<ContentsVo> contentsListType = contservice.contentsListType(contents);
		
		List<ContentsVo> contentsList = contservice.contentsListType(contents);
		req.setAttribute("contentsList", contentsList);
//		req.setAttribute("boardList", boardNo);
		
		LoveVo loveCCount = new LoveVo();
		loveCCount.setContentNo(contentNo);
		int loveCount=loveService.loveContentsCount(loveCCount);
		req.setAttribute("loveCount", loveCount);
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/contents/contentsListType.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		String contentNo = req.getParameter("contentNo");
		String contentsTypeIdStr = req.getParameter("contentsTypeId");
		int contentsTypeId = Integer.parseInt(contentsTypeIdStr);
		
		ContentsVo cont = new ContentsVo();
		cont.setContentsTypeId(contentsTypeId);
		
		List<ContentsVo> contentsList = contservice.contentsListType(cont);
		System.out.println("contentsList : " + contentsList);
		
		//좋아요
        HttpSession session = req.getSession();
		MemberVo member = (MemberVo) session.getAttribute("member");
		
		if(session == null || session.getAttribute("member") == null) {
						
		}else {
			LoveVo loveCCount = new LoveVo();
			loveCCount.setContentNo(contentNo);
			int loveCount=loveService.loveContentsCount(loveCCount);
			req.setAttribute("loveCount", loveCount);
			System.out.println("loveCountdfdfsfsdfsdf"+loveCount);
			
			LoveVo love = new LoveVo();
			love.setContentNo(contentNo);
			love.setMemNo(member.getMemNo());
			
			System.out.println("love"+love);
			int check = loveService.lovedByMemCon(love);
			if (check == 1) {
				req.setAttribute("lovedByMemCon", true);
			} else {
				req.setAttribute("lovedByMemCon", false);
			}
		
		}
		
//		cont.setContentsTypeId(contArr[1]);
//		cont.setContentsTypeId(contArr[2]);
//		cont.setContentsTypeId(contArr[3]);
//		cont.setContentsTypeId(contArr[4]);
//		cont.setContentsTypeId(contArr[5]);
//		
		
//      int contentsTypeId = Integer.parseInt(contentsTypeIdStr);
		
//		  JSONObject jObj = new JSONObject();
//		  jObj.put("contentsTypeId", contentsTypeId);
//		  jObj.put("contentsList", contentsList);
		  
//		  System.out.println("jObj : " + jObj);
		  
//		  resp.setContentType("application/x-json; charset=utf-8");
//		  resp.getWriter().print(jObj);
		 	
		
		req.setAttribute("contentsList", contentsList);
		req.getRequestDispatcher("/WEB-INF/view/contents/contentsListType.jsp").forward(req, resp);
		
		System.out.println("성공 !");
	
	
	}
	
}
