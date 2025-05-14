package kr.or.ddit.contents.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.contents.service.ContentsServiceImpl;
import kr.or.ddit.contents.service.IContentsService;
import kr.or.ddit.love.service.ILoveService;
import kr.or.ddit.love.service.LoveServiceImpl;
import kr.or.ddit.review.service.IReviewService;
import kr.or.ddit.review.service.ReviewServiceImpl;
import kr.or.ddit.travelplan.service.IPlanService;
import kr.or.ddit.travelplan.service.PlanServiceImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.ContentsVo;
import kr.or.ddit.vo.LoveVo;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/contentsDetail.do")
public class ContentsDetailController extends HttpServlet{
	
	
	IContentsService contservice =ContentsServiceImpl.getInstance();
	ILoveService loveService = LoveServiceImpl.getInstance();
	IPlanService planService = PlanServiceImpl.getInstance();
	IReviewService reviewService = ReviewServiceImpl.getInstance();
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("detail 실행");
		HttpSession session = req.getSession();
		MemberVo member = (MemberVo) session.getAttribute("member");
		
    	
		String contentNo = req.getParameter("contentNo");
		ContentsVo contents = new ContentsVo();
		contents.setContentNo(contentNo);
		contents= contservice.contentsDetail(contents);
		
		if(session == null || session.getAttribute("member") == null) {
			req.setAttribute("contents", contents);
			
			BoardVo board = new BoardVo();
			board.setContentNo(contentNo);
			List<BoardVo> boardList = reviewService.reviewList(board);
	        
	        // 결과를 request에 저장
	        req.setAttribute("boardList", boardList);
			
			ServletContext ctx = req.getServletContext();
			ctx.getRequestDispatcher("/WEB-INF/view/contents/contentsDetail.jsp").forward(req, resp);
			
		}else {
		
			BoardVo plan = new BoardVo();
			plan.setMemNo(member.getMemNo());
			List<BoardVo> planList= planService.planList(plan);
			
			LoveVo loveCCount = new LoveVo();
			loveCCount.setContentNo(contentNo);
			System.out.println(loveCCount);
			int loveCount=loveService.loveContentsCount(loveCCount);
			req.setAttribute("loveCount", loveCount);
			System.out.println("loveCountdfdfsfsdfsdf"+loveCount);
			
			LoveVo love = new LoveVo();
			love.setContentNo(contentNo);
			love.setMemNo(member.getMemNo());
			
			System.out.println("love"+love);
			int check = loveService.lovedByMemCon(love);
			if (check == 1) {
				req.setAttribute("lovedByMemCon", true);
			} else {
				req.setAttribute("lovedByMemCon", false);
			}
			
			req.setAttribute("contents", contents);
			req.setAttribute("planList", planList);
			
			BoardVo board = new BoardVo();
			board.setContentNo(contentNo);
			List<BoardVo> boardList = reviewService.reviewList(board);
	        
	        // 결과를 request에 저장
	        req.setAttribute("boardList", boardList);
			
			ServletContext ctx = req.getServletContext();
			ctx.getRequestDispatcher("/WEB-INF/view/contents/contentsDetail.jsp").forward(req, resp);
		}
		
	}
}
