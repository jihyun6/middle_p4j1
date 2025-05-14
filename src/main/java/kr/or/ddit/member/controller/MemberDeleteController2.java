package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/memberDelete2.do")
public class MemberDeleteController2 extends HttpServlet {
    private IMemberService memberService = MemberServiceImpl.getInstance();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
    	System.out.println("memberDelete2.do");
    	
    	// 파라미터 검증
        String memNoStr = req.getParameter("memNo");
        if(memNoStr == null || memNoStr.trim().isEmpty()) {
        	resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 요청입니다.");
        	return;
        }
        int memNo = Integer.parseInt(memNoStr);
    	
        try {
            // 삭제할 회원 정보 설정
            MemberVo member = new MemberVo();
            member.setMemNo(Integer.parseInt(memNoStr));
            member.setMemDelyn("Y");
            
            	int result = memberService.memberDelete(member);
            
            System.out.println("삭제가 완료되었습니다");
            
            // JSON 응답 생성
            JSONObject jObj = new JSONObject();
            if(result > 0) {
                jObj.put("status", "success");
            } else {
                jObj.put("status", "fail");
            }
            
            // 응답 전송
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().print(jObj);
            
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 회원 번호입니다.");
        }
    }
}