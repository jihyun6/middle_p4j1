package kr.or.ddit.love.controller;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.love.service.ILoveService;
import kr.or.ddit.love.service.LoveServiceImpl;
import kr.or.ddit.util.RequestToVoMapper;
import kr.or.ddit.vo.LoveVo;
import kr.or.ddit.vo.MemberVo;


@WebServlet("/loveCancel.do")
public class LoveCancelController extends HttpServlet{
	
	ILoveService loveService = LoveServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//로그인 세션 값 가져오기
				HttpSession session = req.getSession();
		    	MemberVo member = (MemberVo) session.getAttribute("member");
		    	req.setAttribute("member", member);
	}
			
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String contentNo = req.getParameter("contentNo");
		String memNoStr= req.getParameter("memNo");
		int memNo = Integer.parseInt(memNoStr);
		String loveCountStr = req.getParameter("loveCount");
		int loveCount = Integer.parseInt(loveCountStr);
		
	
		
		JSONObject jObj = new JSONObject();
		jObj.put("contentNo", contentNo);
		jObj.put("memNo", memNo);
		jObj.put("loveCount", loveCount);
		
		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jObj);
		
		LoveVo love = new LoveVo();
		love.setContentNo(contentNo);
		love.setLoveCount(loveCount);
		love.setMemNo(memNo);
		loveService.loveCancel(love);
		
	}
}
