package kr.or.ddit.contents.controller;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.contents.service.ContentsServiceImpl;
import kr.or.ddit.contents.service.IContentsService;
import kr.or.ddit.vo.ContentsVo;

@WebServlet("/contentsDelete.do")
public class ContentsDeleteController extends HttpServlet{
	
	IContentsService contentsService = ContentsServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
System.out.println("delete get");
		
		String contentNo= req.getParameter("contentNo");
		
		String contentsTypeIdStr= req.getParameter("contentsTypeId");
		int contentsTypeId = Integer.parseInt(contentsTypeIdStr);
		
		ContentsVo contents = new ContentsVo();
		contents.setContentNo(contentNo);
		
		contentsService.contentsDelete(contents);
		
		resp.sendRedirect(req.getContextPath()+"/contentsListType.do?contentsTypeId="+contentsTypeId);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("포스트로는 못받음 ㅠㅠ");
		
		String contentNo= req.getParameter("contentNo");
		
		String contentsTypeIdStr= req.getParameter("contentsTypeId");
		int contentsTypeId = Integer.parseInt(contentsTypeIdStr);
		
		ContentsVo contents = new ContentsVo();
		contents.setContentNo(contentNo);
		
		contentsService.contentsDelete(contents);
		
		resp.sendRedirect(req.getContextPath()+"/contentsListType.do?contentsTypeId="+contentsTypeId);
	}
}
