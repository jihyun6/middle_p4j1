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

@WebServlet("/replyDelete.do")
public class ReplyDeleteController extends HttpServlet {
    private IReplyService replyService = ReplyServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        
        // 세션 검증
        HttpSession session = req.getSession(false); //현재 세션이 없으면 새로 생성하지 않고 null을 반환
        if (session == null || session.getAttribute("member") == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");  //세션이 없거나 세션에 "member" 속성이 없으면 401 Unauthorized 에러 반환
            return;
        }
        
        // 댓글 번호, 게시글 번호 검증
        String replyNoStr = req.getParameter("replyNo");
        String boardNoStr = req.getParameter("boardNo");
        
        if (replyNoStr == null || replyNoStr.trim().isEmpty() || 
            boardNoStr == null || boardNoStr.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 요청입니다."); //파라미터가 없거나 공백인 경우 400 Bad Request 에러 반환
            return;
        }
        
        try {
            MemberVo member = (MemberVo) session.getAttribute("member"); // 세션에 로그인한 회원 정보를 가져옴
            
         // 삭제할 댓글 정보를 ReplyVo객체에 설정
            ReplyVo deletedReply = new ReplyVo(); 
            deletedReply.setReplyNo(Integer.parseInt(replyNoStr));
            deletedReply.setMemNo(member.getMemNo()); // 댓글 번호와 회원번호를 설정하여 자신의 댓글만 삭제가능하도록 함.
            
            int result = replyService.replyDelete(deletedReply);

            JSONObject jObj = new JSONObject();
            jObj.put("status", "success");  // status: 성공 상태
            jObj.put("boardNo", boardNoStr);  // 삭제된 댓글이 속한 게시글 번호
    		resp.getWriter().print(jObj);  //응답: JSON 형태로 처리 결과를 전달하여 클라이언트에서 활용 가능
            
//            resp.setContentType("application/json;charset=UTF-8");
//            PrintWriter out = resp.getWriter();
//            
//            if (result > 0) {
//                out.print("{\"status\": \"success\"}");
//            } else {
//                out.print("{\"status\": \"fail\"}");
//            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 댓글 번호입니다.");
        }
    }
}