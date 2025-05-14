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
import kr.or.ddit.vo.NoticeVo;

@WebServlet("/noticeDelete.do")
public class NoticeDeleteController extends HttpServlet{

	InoticeService noticeService = NoticeServiceImpl.getInstance();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String noticeStr = req.getParameter("noticeNo");
		System.out.println(noticeStr+"noticeStr11111111111111");
		
		
		int noticeNo = Integer.parseInt(noticeStr);
		
		
		
		NoticeVo notice = new NoticeVo();
		notice.setNoticeNo(noticeNo);
		
		noticeService.noticeDelete(notice);
		
		resp.sendRedirect(req.getContextPath()+"/noticeList.do");
	}
		
}

