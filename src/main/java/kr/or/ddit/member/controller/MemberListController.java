package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVo;
@WebServlet("/memberList.do")
public class MemberListController extends HttpServlet{

	IMemberService memberService = MemberServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	MemberVo member = new MemberVo();
	List<MemberVo> memberList = memberService.memberList(member);
	
	req.setAttribute("memberList", memberList);
	ServletContext ctx = req.getServletContext();
	ctx.getRequestDispatcher("/WEB-INF/view/member/memberList.jsp").forward(req, resp);
	
	}
}
