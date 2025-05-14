package kr.or.ddit.notice.controller;

import java.io.IOException;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.notice.service.InoticeService;
import kr.or.ddit.notice.service.NoticeServiceImpl;
import kr.or.ddit.vo.NoticeVo;

@WebServlet("/noticeUpdate.do")
public class NoticeUpdate extends HttpServlet{

	InoticeService noticeService = NoticeServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		//String noticeStr = req.getParameter("noticeNo");
		//int noticeNo = Integer.parseInt(noticeStr);
		
		//HttpSession session = req.getSession();
		//NoticeVo notice =(NoticeVo) session.getAttribute("notice");
		//System.out.println("111111111111111111111111"+notice);
		//System.out.println(noticeNo+"noticeNo");
		
		//NoticeVo notice = new NoticeVo();
		//notice.setNoticeNo(noticeNo);
		
		//noticeNo를 불러오고
		String notices = req.getParameter("noticeNo"); 
		
		//VO에 담고
		NoticeVo vo = new NoticeVo();
		int noticeNo = Integer.parseInt(notices);
		vo.setNoticeNo(noticeNo);
		
		
		
		vo = noticeService.noticeDetail(vo);
		
		System.out.println(vo+"111111111111111111111111");
		
		req.setAttribute("noticeList", vo);
		req.setAttribute("noticeNo", noticeNo);
		req.setAttribute("noticeName", vo.getNoticeName());
		req.setAttribute("noticeDate", vo.getNoticeDate());
		req.setAttribute("noticeContent", vo.getNoticeContent());
		
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/notice/noticeUpdate.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("---------------------------------------");
		Gson gson = new Gson();
		
		String JsonStr = gson.toJson(req.getParameterMap());
		
		System.out.println("---------------------------------------");
		System.out.println(JsonStr);
	
		JsonStr = JsonStr.replace("[", "");
		JsonStr = JsonStr.replace("]", "");
		
		NoticeVo notice = gson.fromJson(JsonStr, NoticeVo.class);
		
		System.out.println("---------------------------------------");
		System.out.println(notice);
		
		int result = noticeService.noticeUpdate(notice);
				
		JSONObject jobj = new JSONObject();
		jobj.put("url", "/noticeDetail.do?noticeNo=" + notice.getNoticeNo());
		
		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jobj);
		
		
	}
}
