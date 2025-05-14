package kr.or.ddit.review.controller;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.review.service.IReviewService;
import kr.or.ddit.review.service.ReviewServiceImpl;
import kr.or.ddit.vo.BoardVo;

@WebServlet("/reviewUpdate.do")
public class ReviewUpdateController extends HttpServlet {

	IReviewService reviewService = ReviewServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String boardNoStr = req.getParameter("boardNo");
		int boardNo = Integer.parseInt(boardNoStr);

		BoardVo boardVo = new BoardVo();
		boardVo.setBoardNo(boardNo);

		BoardVo board = reviewService.reviewDetail(boardVo);
		req.setAttribute("board", board);

		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/board/reviewUpdate.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("reviewUpdate.do post 실행!");

		// 수정할 데이터 받기
		String boardNoStr = req.getParameter("boardNo");
		String boardName = req.getParameter("title");
		String content = req.getParameter("content");
		String date = req.getParameter("date");
		String starStr = req.getParameter("boardStar");

		int boardNo = Integer.parseInt(boardNoStr);
		int star = Integer.parseInt(starStr);	
		
		
		// BoardVo 객체 설정
		BoardVo boardVo = new BoardVo();
		boardVo.setBoardNo(boardNo);
		boardVo.setBoardName(boardName);
		boardVo.setBoardContent(content);
		boardVo.setBoardDate(date);
		boardVo.setBoardStar(star);
		
		System.out.println(boardVo);

		// 업데이트 수행
		int result = reviewService.reviewUpdate(boardVo);

		resp.sendRedirect(req.getContextPath() + "/reviewDetail.do?boardNo=" + boardNo);

	}



}
