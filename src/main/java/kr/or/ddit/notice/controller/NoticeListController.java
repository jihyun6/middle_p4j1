package kr.or.ddit.notice.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.notice.service.InoticeService;
import kr.or.ddit.notice.service.NoticeServiceImpl;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.NoticeVo;

@WebServlet("/noticeList.do")
public class NoticeListController extends HttpServlet{
	private InoticeService NoticeService;
	
	 @Override
	    public void init() throws ServletException {
		 NoticeService = NoticeServiceImpl.getInstance();
	    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		HttpSession session = req.getSession();
		NoticeVo notice = (NoticeVo) session.getAttribute("notice");
		System.out.println(notice+"11111111111111111");
		
//		int codeNumber = Integer.parseInt(notice);
		

		
//		notice.setCodeNumber(codeNumber);
		
		List<NoticeVo> noticeList = NoticeService.NoticeList(notice);
		
		req.setAttribute("noticeList", noticeList);
		req.getRequestDispatcher("/WEB-INF/view/notice/noticeList.jsp").forward(req, resp);
		
	}
}
