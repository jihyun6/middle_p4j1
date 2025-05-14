package kr.or.ddit.notice.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletContext;
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

@WebServlet("/noticeDetail.do")
public class NoticeDetailController extends HttpServlet{

	private InoticeService NoticeService;
	
	
	 @Override
	    public void init() throws ServletException {
		 NoticeService = NoticeServiceImpl.getInstance();
	    }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		

		
		
		HttpSession session = req.getSession(); NoticeVo notice = (NoticeVo)
		session.getAttribute("notice");

		
		System.out.println(notice+"11111111111111111");
		 
		
		
	
		
		String notices = req.getParameter("noticeNo");
		NoticeVo vo = new NoticeVo();
		int noticeNo = Integer.parseInt(notices);
		
	
		
		System.out.println(noticeNo+"2222222222222222222");
		vo.setNoticeNo(noticeNo);
		vo = NoticeService.noticeDetail(vo);
		
		
		
		
		System.out.println(vo+"2222222222222222222");
		
		NoticeVo noticeNo2 = NoticeService.noticeDetail(notice);
		System.out.println(noticeNo2+"3333333333333333333");
		
		req.setAttribute("noticeNo", noticeNo);
		req.setAttribute("noticeName", vo.getNoticeName());
		req.setAttribute("noticeDate", vo.getNoticeDate());
		req.setAttribute("noticeContent", vo.getNoticeContent());
		
		req.getRequestDispatcher("/WEB-INF/view/notice/noticeDetail.jsp").forward(req, resp);
	}
}
