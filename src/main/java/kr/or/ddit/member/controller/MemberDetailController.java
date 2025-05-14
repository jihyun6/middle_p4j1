package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mypage.service.IMypageService;
import kr.or.ddit.mypage.service.MypageServiceImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.MemberVo;
@WebServlet("/memberDetail.do")
public class MemberDetailController extends HttpServlet{

	IMemberService memberService = MemberServiceImpl.getInstance();
	IMypageService mypageService = MypageServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	try {
		String memNoStr = req.getParameter("memNo");
		if(memNoStr == null || memNoStr.trim().isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "회원 번호가 없습니다.");
			return;
		}
		
		int memNo = Integer.parseInt(memNoStr);
		
		MemberVo memberVo = new MemberVo();
		memberVo.setMemNo(memNo);
		
		MemberVo member = memberService.memberDetail(memberVo);
		
		if(member ==null) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 회원을 찾을 수 없습니다.");
		}
		
		req.setAttribute("member", member);
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/member/memberDetail.jsp").forward(req, resp);
	} catch(NumberFormatException e) {
		resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 회원번호 형식입니다.");
	} catch(Exception e) {
		e.printStackTrace();
		resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"서버 오류가 발생했습니다.");
	}
		
	}
	
	/*
	 * @Override protected void doPost(HttpServletRequest req, HttpServletResponse
	 * resp) throws ServletException, IOException {
	 * 
	 * HttpSession session = req.getSession(); MemberVo member = (MemberVo)
	 * session.getAttribute("member"); req.setAttribute("member", member);
	 * 
	 * System.out.println("memberDetail doPost실행");
	 * 
	 * List<BoardVo> boardList = mypageService.mypagePlanList(member);
	 * req.setAttribute("boardList", boardList);
	 * 
	 * System.out.println("boardList : " + boardList);
	 * 
	 * RequestDispatcher dispatcher =
	 * req.getRequestDispatcher("/WEB-INF/view/board/plan/planList.jsp");
	 * dispatcher.forward(req, resp);
	 * 
	 * }
	 */
}
