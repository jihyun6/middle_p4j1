package kr.or.ddit.review.controller;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.review.service.IReviewService;
import kr.or.ddit.review.service.ReviewServiceImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/reviewInsert.do")
public class ReviewInsertController extends HttpServlet {

	IReviewService reviewService = ReviewServiceImpl.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/board/review/reviewInsert.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
    	MemberVo member = (MemberVo) session.getAttribute("member");
    	
		String title = req.getParameter("reviewTitle");
		String content = req.getParameter("reviewContent");
		String starStr = req.getParameter("boardStar");
		int star = Integer.parseInt(starStr);
		
		BoardVo board = new BoardVo();
		board.setBoardName(title);
		board.setBoardContent(content);
		board.setBoardStar(star);
		board.setMemNo(member.getMemNo());;
		reviewService.reviewInsert(board);
		
		resp.sendRedirect(req.getContextPath()+"/reviewList.do");
		
	
	}
	
	
	
}
