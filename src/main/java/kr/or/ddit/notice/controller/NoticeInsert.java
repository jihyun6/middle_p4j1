package kr.or.ddit.notice.controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.notice.service.NoticeServiceImpl;
import kr.or.ddit.util.RequestToVoMapper;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.ContentsVo;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.NoticeVo;

@WebServlet("/noticeInsert.do")
public class NoticeInsert extends HttpServlet{

	IBoardService boardService = BoardServiceImpl.getInstance();
	NoticeServiceImpl noticeService = NoticeServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ServletContext ctx = req.getServletContext();
		req.getRequestDispatcher("/WEB-INF/view/notice/noticeInsert.jsp").forward(req, resp);

    	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		NoticeVo notice = RequestToVoMapper.mapRequestToVo(req, NoticeVo.class);
		
		/*
		 * if(req.getContentType().contains("multipart/form-data")) { HttpSession
		 * session = req.getSession(false); if (session == null
		 * ||session.getAttribute("member") == null) { req.setAttribute("errorMsg",
		 * "로그인이 필요합니다.");
		 * req.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(req,
		 * resp); return; } MemberVo member = (MemberVo) session.getAttribute("member");
		 * notice.setMemNo(notice.getMemNo()); }else {
		 * 
		 * int result = noticeService.noticeInsert(notice);
		 * 
		 * }
		 */
	    // 로그인 체크
	    HttpSession session = req.getSession(false);
	    if (session == null || session.getAttribute("member") == null) {
	        req.setAttribute("errorMsg", "로그인이 필요합니다.");
	        req.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(req, resp);
	        return;
	    }
	    
	    MemberVo member = (MemberVo) session.getAttribute("member");
	    notice.setMemNo(member.getMemNo()); // 로그인한 사용자의 memNo 설정
	    
	    // 공지사항 등록 처리
	    try {
	        int result = noticeService.noticeInsert(notice); // 등록 성공 여부 확인
	        System.out.println("result"+result);
	        
	        if (result > 0) {
	        	JSONObject jObj = new JSONObject();
				jObj.put("url", "/noticeList.do");
				
				resp.setContentType("application/x-json; charset=utf-8");
				resp.getWriter().print(jObj);
	        	
	        	
//	            resp.sendRedirect("/noticeList.do"); // 성공 시 공지사항 목록으로 이동
	        } else {
	            req.setAttribute("errorMsg", "공지사항 등록에 실패했습니다.");
	            req.getRequestDispatcher("/WEB-INF/view/notice/noticeInsert.jsp").forward(req, resp);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        req.setAttribute("errorMsg", "오류가 발생했습니다.");
	        req.getRequestDispatcher("/WEB-INF/view/notice/noticeInsert.jsp").forward(req, resp);
	    }			 
		}
	}

