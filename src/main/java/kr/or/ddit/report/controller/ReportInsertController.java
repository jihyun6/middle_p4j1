package kr.or.ddit.report.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.report.service.ReportServiceImpl;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.ReportVo;

@WebServlet("/reportInsert.do")
public class ReportInsertController extends HttpServlet {
    
    private ReportServiceImpl reportService = ReportServiceImpl.getInstance();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        JSONObject jsonResponse = new JSONObject();
        
        try {
            // 로그인 체크
            HttpSession session = req.getSession(false);
            if(session == null || session.getAttribute("member") == null) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                jsonResponse.put("status", "fail");
                jsonResponse.put("message", "로그인이 필요합니다.");
                out.print(jsonResponse.toString());
                return;
            }
            
            // 파라미터 받기
            int boardNo = Integer.parseInt(req.getParameter("boardNo"));
            int reReason = Integer.parseInt(req.getParameter("reReason"));
            String reComment = req.getParameter("reComment");
            int boardMemNo = Integer.parseInt(req.getParameter("boardMemNo")); // 게시글 작성자의 memNo를 파라미터로 받기
            
            // 신고한 회원 정보
            MemberVo reporter = (MemberVo) session.getAttribute("member");
            
            // ReportVo 설정
            ReportVo report = new ReportVo();
            report.setBoardNo(boardNo);
            report.setReReason(reReason);
            report.setReComment(reComment);
            report.setMemNo(boardMemNo);  // 게시글 작성자 번호
            report.setReStatus("미처리");  // 초기 상태
            
            // 서비스 호출
            int result = reportService.reportInsert(report);
            
            if(result > 0) {
                jsonResponse.put("status", "success");
                jsonResponse.put("message", "신고가 정상적으로 접수되었습니다.");
            } else {
                jsonResponse.put("status", "fail");
                jsonResponse.put("message", "신고 처리에 실패했습니다.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "시스템 오류가 발생했습니다.");
        }
        
        out.print(jsonResponse.toString());
    }
}