package kr.or.ddit.review.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.review.service.ReviewServiceImpl;
import kr.or.ddit.vo.BoardVo;
import lombok.Data;

@Data
@WebServlet("/reviewDetail.do")
public class ReviewDetailController extends HttpServlet {
    
    private ReviewServiceImpl reviewService = ReviewServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	
        String boardNoStr = req.getParameter("boardNo");
        int boardNo = Integer.parseInt(boardNoStr);

        BoardVo boardVo = new BoardVo();
        boardVo.setBoardNo(boardNo);

        // 단일 조회 BoardVo로 받음
        BoardVo board = reviewService.reviewDetail(boardVo);
        req.setAttribute("board", board); 

        ServletContext ctx = req.getServletContext();
        ctx.getRequestDispatcher("/WEB-INF/view/board/reviewDetail.jsp").forward(req, resp);
    }
}
