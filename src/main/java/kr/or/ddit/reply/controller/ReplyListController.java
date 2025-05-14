package kr.or.ddit.reply.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyServiceImpl;
import kr.or.ddit.vo.ReplyVo;
@WebServlet("/replyList.do")
public class ReplyListController extends HttpServlet {
    private IReplyService replyService = ReplyServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // request에서 게시글 번호 파라미터를 가져옴
        String boardNoStr = req.getParameter("boardNo");
        
        // null 체크 및 유효성 검사
        if (boardNoStr == null || boardNoStr.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "게시글 번호가 유효하지 않습니다.");
            return;
        }
        
        try {
            int boardNo = Integer.parseInt(boardNoStr);  // 문자열로 받은 게시글 번호를 정수로 변환
            
            ReplyVo replyParam = new ReplyVo();  // ReplyVo 객체를 생성하여 게시글 번호 설정
            replyParam.setBoardNo(boardNo);
            
            List<ReplyVo> replyList = replyService.replyList(replyParam);   // 서비스를 통해 게시글 댓글 목록 조회
            
            req.setAttribute("replyList", replyList);  // 조회된 댓글 목록을 request 속성에 저장
            req.getRequestDispatcher("/WEB-INF/view/reply/replyList.jsp").forward(req, resp);
            
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 게시글 번호 형식입니다.");
        }
    }
}