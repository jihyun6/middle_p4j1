package kr.or.ddit.black.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.black.service.BlacklistServiceImpl;
import kr.or.ddit.black.service.IBlacklistService;
import kr.or.ddit.vo.BlacklistVo;

@WebServlet("/blacklistDelete.do")
public class BlacklistDeleteController extends HttpServlet {
    
    private IBlacklistService blacklistService = BlacklistServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String blackNoStr = req.getParameter("blackNo");
            int blackNo = Integer.parseInt(blackNoStr);

            BlacklistVo blackVo = new BlacklistVo();
            blackVo.setBlackNo(blackNo);

            int result = blacklistService.blacklistDelete(blackVo);  // 삭제 실행

            if(result > 0) {  // 삭제성공
                resp.sendRedirect(req.getContextPath() + "/blacklistList.do");   // 목록으로 이동
            } else {
                throw new RuntimeException("삭제 실패");
            }
        } catch(Exception e) {
            e.printStackTrace();
            // 에러 처리 페이지로 이동하거나 메시지 표시
            resp.sendError(500, "삭제 처리 중 오류가 발생했습니다.");
        }
    }
}