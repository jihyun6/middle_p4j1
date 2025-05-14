package kr.or.ddit.black.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.black.service.BlacklistServiceImpl;
import kr.or.ddit.black.service.IBlacklistService;

@WebServlet("/checkBlacklistDuplicate.do")
public class CheckBlacklistDuplicateController extends HttpServlet {
    
    private IBlacklistService blacklistService = BlacklistServiceImpl.getInstance();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int memNo = Integer.parseInt(req.getParameter("memNo"));
        
        int count = blacklistService.checkDuplicate(memNo);
        
        resp.setContentType("application/json");
        resp.getWriter().write("{\"isDuplicate\":" + (count > 0) + "}");
    }
}
