package kr.or.ddit.love.controller;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.love.service.ILoveService;
import kr.or.ddit.love.service.LoveServiceImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.ContentsVo;
import kr.or.ddit.vo.LoveVo;
import kr.or.ddit.vo.MemberVo;


@WebServlet("/lovedByUser.do")
public class LovedByUserController extends HttpServlet{
	
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
		System.out.println("lovecheck post");
		String contentNo = req.getParameter("contentNo");
		String memNoStr= req.getParameter("memNo");
		int memNo = Integer.parseInt(memNoStr);
		
		LoveVo love = new LoveVo();
		love.setContentNo(contentNo);
		love.setMemNo(memNo);
		
		int check =loveService.lovedByMemBoard(love);
		love.setLovedByMem(check);
		System.out.println(love+"211111111111111");
		JSONObject jObj = new JSONObject();
		jObj.put("lovedByMem", check);
//		jObj.put("lovedByMem", check);
		if(check==1) {
		jObj.put("lovedByMem", true);
		}else{
		jObj.put("lovedByMem", false);
		}
		req.setAttribute("lovedByMem", check);

		
		
		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jObj);
		System.out.println(jObj+"dddddddddddddd");
		
	}
}
