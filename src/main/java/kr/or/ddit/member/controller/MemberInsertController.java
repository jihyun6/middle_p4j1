package kr.or.ddit.member.controller;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.util.RequestToVoMapper;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/memberInsert.do")
public class MemberInsertController extends HttpServlet {
	
	MemberServiceImpl memberService = MemberServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/member/memberInsert.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MemberVo member = RequestToVoMapper.mapRequestToVo(req, MemberVo.class);
		
		//세션에 저장되어있는 데이터 꺼내오기
		// Insert 수행
		int result = memberService.memberjoin(member);
		
		//boardService.fileWrite(req,boardNo);
		
		JSONObject jObj = new JSONObject();
		jObj.put("url", "/login.do");
		
		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jObj);
	}
}