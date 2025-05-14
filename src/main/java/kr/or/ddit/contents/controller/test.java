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

@WebServlet("/maptest.do")
public class test extends HttpServlet{
	IContentsService contservice =ContentsServiceImpl.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String contentNo = req.getParameter("contentNo");
		ContentsVo contents = new ContentsVo();
		contents.setContentNo(contentNo);
		contents= contservice.contentsDetail(contents);
		
		req.setAttribute("contents", contents);
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/contents/mapTest.jsp").forward(req, resp);
	}
}
