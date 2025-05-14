package kr.or.ddit.black.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.black.service.BlacklistServiceImpl;
import kr.or.ddit.black.service.IBlacklistService;
import kr.or.ddit.vo.BlacklistVo;
import kr.or.ddit.vo.ReportVo;

@WebServlet("/blacklistList.do")
public class BlacklistListController extends HttpServlet {

	IBlacklistService blacklistService = BlacklistServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    BlacklistVo blacklist = new BlacklistVo();
	    List<BlacklistVo> blacklistList = blacklistService.blacklistList(blacklist);
	    
	    // 데이터 확인용 로그
	    System.out.println("블랙리스트 크기: " + blacklistList.size());
	    for(BlacklistVo vo : blacklistList) {
	        System.out.println(vo);
	    }
	    
	    req.setAttribute("blacklistList", blacklistList);
	    ServletContext ctx = req.getServletContext();
	    ctx.getRequestDispatcher("/WEB-INF/view/blacklist/blacklist.jsp").forward(req, resp);
	}
}
