package kr.or.ddit.black.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.black.service.BlacklistServiceImpl;
import kr.or.ddit.black.service.IBlacklistService;
import kr.or.ddit.vo.BlacklistVo;

@WebServlet("/blacklistInsert.do")
public class BlacklistInsertController extends HttpServlet {
    
    private IBlacklistService blacklistService = BlacklistServiceImpl.getInstance();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            
            
            // 받은 파라미터 출력
            System.out.println("memNo: " + req.getParameter("memNo"));
            System.out.println("memName: " + req.getParameter("memName"));
            System.out.println("blackReason: " + req.getParameter("blackReason"));
            System.out.println("startDate: " + req.getParameter("blackStartDate"));
            System.out.println("endDate: " + req.getParameter("blackEndDate"));
            
            BlacklistVo blacklist = new BlacklistVo();
            blacklist.setMemNo(Integer.parseInt(req.getParameter("memNo")));
            blacklist.setMemName(req.getParameter("memName"));
            blacklist.setBlackReason(req.getParameter("blackReason"));
            blacklist.setBlackStartDate(req.getParameter("blackStartDate"));
            blacklist.setBlackEndDate(req.getParameter("blackEndDate"));
            
            System.out.println("BlacklistVo: " + blacklist);  // VO 객체 출력
            
            int result = blacklistService.blacklistInsert(blacklist);
            System.out.println("insert 결과: " + result);  // 결과 출력
            
            if(result > 0) {
                resp.getWriter().write("{\"result\":\"success\"}");
            } else {
                throw new RuntimeException("등록 실패");
            }
        } catch(Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            resp.getWriter().write("{\"result\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
        }
    }
}