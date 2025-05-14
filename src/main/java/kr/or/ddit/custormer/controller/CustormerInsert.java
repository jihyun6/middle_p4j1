package kr.or.ddit.custormer.controller;

import java.io.IOException;

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
import kr.or.ddit.custormer.service.CustormerServiceImpl;
import kr.or.ddit.util.RequestToVoMapper;
import kr.or.ddit.vo.CustormerVo;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/custormerInsert.do")
public class CustormerInsert extends HttpServlet{
	
	IBoardService boardService = BoardServiceImpl.getInstance();
	CustormerServiceImpl custormerService = CustormerServiceImpl.getInstance(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/custormer/custormerInsert.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CustormerVo custormer = RequestToVoMapper.mapRequestToVo(req, CustormerVo.class);
		
		// 로그인 체크
		HttpSession session = req.getSession(false);
		if(session == null || session.getAttribute("member") == null) {
			req.setAttribute("errorMsg", "로그인이 필요합니다.");
			req.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(req, resp);
			return;
		}
		
		 MemberVo member = (MemberVo) session.getAttribute("member");
		 custormer.setMemNo(member.getMemNo()); // 로그인한 사용자의 memNo 설정
				
		// 공지사항 등록 처리
		    try {
		        int result =  custormerService.custormerInsert(custormer); // 등록 성공 여부 확인
		        System.out.println("result"+result);
		        
		        if (result > 0) {
		        	JSONObject jObj = new JSONObject();
					jObj.put("url", "/custormerList.do");
					
					resp.setContentType("application/x-json; charset=utf-8");
					resp.getWriter().print(jObj);
		        	
		        	
//		            resp.sendRedirect("/noticeList.do"); // 성공 시 공지사항 목록으로 이동
		        } else {
		            req.setAttribute("errorMsg", "공지사항 등록에 실패했습니다.");
		            req.getRequestDispatcher("/WEB-INF/view/custormer/custormerInsert.jsp").forward(req, resp);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        req.setAttribute("errorMsg", "오류가 발생했습니다.");
		        req.getRequestDispatcher("/WEB-INF/view/custormer/custormerInsert.jsp").forward(req, resp);
		    }			 
		 
	}
}
