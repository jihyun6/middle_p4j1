package kr.or.ddit.review.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.review.service.IReviewService;
import kr.or.ddit.review.service.ReviewServiceImpl;
import kr.or.ddit.vo.BoardVo;
@WebServlet("/reviewDelete.do")
public class ReviewDeleteController extends HttpServlet {
    
    IReviewService reviewService = ReviewServiceImpl.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. boardNo 파라미터 받기
        String boardNoStr = req.getParameter("boardNo");
        int boardNo = Integer.parseInt(boardNoStr);
        
        // 2. BoardVo 객체 생성 및 설정
        BoardVo boardVo = new BoardVo();
        boardVo.setBoardNo(boardNo);
        boardVo.setBoardDelyn("Y");  // 삭제 표시로 Y 설정
        
        // 3. 서비스 메서드 호출하여 업데이트
        int result = reviewService.reviewDelete(boardVo);
        
        // 4. 목록 페이지로 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/reviewList.do");
    }

}
