package kr.or.ddit.contents.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.contents.service.ContentsServiceImpl;
import kr.or.ddit.contents.service.IContentsService;
import kr.or.ddit.vo.ContentsVo;

@WebServlet("/contentsListArea.do")
public class ContentsListAreaController extends HttpServlet {
	
	IContentsService contservice =ContentsServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String trialAreacodeStr =req.getParameter("trialAreacode");
		int trialAreacode =  Integer.parseInt(trialAreacodeStr);
	
		ContentsVo contents = new ContentsVo();
		contents.setTrialAreacode(trialAreacode);

		List<ContentsVo> contentsList = contservice.contentsListArea(contents);
		req.setAttribute("contentsList", contentsList);
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/contents/contentsListArea.jsp").forward(req, resp);
	}
}
