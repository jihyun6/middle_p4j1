package kr.or.ddit.review.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.review.service.IReviewService;
import kr.or.ddit.review.service.ReviewServiceImpl;
import kr.or.ddit.vo.BoardVo;


@WebServlet("/ReviewList.do")
public class ReviewListController extends HttpServlet {
    IReviewService reviewService = ReviewServiceImpl.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // 기본 파라미터와 검색 파라미터 받기
        String contentNo = req.getParameter("contentNo");
        String searchType = req.getParameter("searchType");
        String searchWord = req.getParameter("searchWord");
        String sortOrder = req.getParameter("sort");
        
        // BoardVo에 파라미터 설정
        BoardVo board = new BoardVo();
        board.setContentNo(contentNo);
        board.setSearchType(searchType);
        board.setSearchWord(searchWord);
        board.setSortOrder(sortOrder);
        
        // 리스트 조회
        List<BoardVo> reviewList = reviewService.reviewList(board);
        
        // 결과를 request에 저장
        req.setAttribute("boardList", reviewList);
        
        // JSP로 포워드
        req.getRequestDispatcher("/WEB-INF/view/board/review/reviewList.jsp")
           .forward(req, resp);
    }
}