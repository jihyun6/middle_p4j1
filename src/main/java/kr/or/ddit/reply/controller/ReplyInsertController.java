package kr.or.ddit.reply.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyServiceImpl;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.ReplyVo;

@WebServlet("/replyInsert.do")
public class ReplyInsertController extends HttpServlet {
    private IReplyService replyService = ReplyServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        // 인코딩 설정
        req.setCharacterEncoding("UTF-8");
        
        // 세션 체크 (false: 기존 세션 없으면 null 반환)
        HttpSession session = req.getSession(false);
        
        // 세션 및 로그인 상태 확인
        if (session == null || session.getAttribute("member") == null) {   //현재 세션이 없으면 새로 생성하지 않고 null을 반환
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다."); //세션이 없거나 세션에 "member" 속성이 없으면 401 Unauthorized 에러 반환
            return;
        }
        
        // 세션에서 회원 정보 가져오기
        MemberVo loginMember = (MemberVo) session.getAttribute("member");  // 세션에 로그인한 회원정보 가져오기
        
        // 파라미터 추출 ( 게시판 번호와 댓글 내용을 request에서 가져옴 )
        // form 데이터나 ajax 요청으로 전송된 데이터를 받음.
        String boardNoStr = req.getParameter("boardNo");     
        String replyContent = req.getParameter("replyContent");  
        
        // 파라미터 유효성 검사
        //게시글 번호나 댓글 내용이 없으면 400 Bad Request 에러 반환
        if (boardNoStr == null || boardNoStr.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "게시글 정보가 없습니다."); 
            return;
        }
        
        if (replyContent == null || replyContent.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "댓글 내용을 입력해주세요.");
            return;
        }
        
        try {
            // ReplyVo 객체를 생성하여 댓글정보 설정
            ReplyVo reply = new ReplyVo();
            reply.setBoardNo(Integer.parseInt(boardNoStr)); // 게시판 번호
            reply.setReplyContent(replyContent); // 댓글 내용
            reply.setMemNo(loginMember.getMemNo()); // 회원 번호(작성자)
            
            // 댓글 삽입
            int result = replyService.replyInsert(reply);
            
            // 응답 처리
            if (result > 0) {
                // JSON 응답
                resp.setContentType("application/json");  //Content-Type을 'application/json'으로 설정
                resp.setCharacterEncoding("UTF-8");
                
                JSONObject jObj = new JSONObject();
        		jObj.put("url", "/boardDetail.do?boardNo=" + boardNoStr);   // 등록 성공 시 이동할 URL 정보를 JSON으로 전달(해당 게시글의 상세 페이지 URL반환)
        		resp.getWriter().print(jObj);
//                PrintWriter out = resp.getWriter();
//                out.print("{\"status\":\"success\"}");
//                out.flush();
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "댓글 작성에 실패했습니다.");
            }
            
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 게시글 번호입니다.");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "시스템 오류가 발생했습니다.");
        }
    }
}