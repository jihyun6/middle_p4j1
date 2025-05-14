package kr.or.ddit.reply.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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

@WebServlet("/replyUpdate.do")
public class ReplyUpdateController extends HttpServlet {
    private IReplyService replyService = ReplyServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
    	// 수정할 댓글 번호 추출
        int replyNo = Integer.parseInt(req.getParameter("replyNo"));
        
        // 댓글 상세 조회 파라미터 생성
        ReplyVo replyParam = new ReplyVo();
        replyParam.setReplyNo(replyNo);
        
        // 댓글 상세 정보 조회
        ReplyVo reply = replyService.replyDetail(replyParam);
       
        // 조회된 댓글 정보를 요청 속성에 설정
        req.setAttribute("reply", reply);
        
        // 댓글 수정 폼 페이지로 포워딩
        req.getRequestDispatcher("/WEB-INF/view/reply/replyUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");  // 응답을 JSON으로 설정
        resp.setCharacterEncoding("UTF-8");
        
        // 세션에서 회원 번호 추출
        HttpSession session = req.getSession(false);
    	
		/* 세션 확인 절차인데 401 에러가 떠서 별로 보기가 좋지 않음
		 * if (session == null || session.getAttribute("member") == null) {
		 * resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다."); return; }
		 */
        
        System.out.println("1");

        MemberVo member = (MemberVo) session.getAttribute("member");
        int memNo = member.getMemNo();

        // 파라미터 추출(댓글번호, 게시글 번호, 댓글 내용) 및 검증
        String replyNoStr = req.getParameter("replyNo");
        String boardNoStr = req.getParameter("boardNo");
        String replyContent = req.getParameter("replyContent");
        System.out.println("2");

        if (replyNoStr == null || boardNoStr == null || replyContent == null ||
            replyNoStr.trim().isEmpty() || boardNoStr.trim().isEmpty() || replyContent.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 정보가 누락되었습니다.");
            return;
        }
        System.out.println("3");
        try {
            // 댓글 수정 정보 생성
        	// ReplyVo 객체에 수정할 정보 설정
            ReplyVo updatedReply = new ReplyVo();
            updatedReply.setReplyNo(Integer.parseInt(replyNoStr));
            updatedReply.setReplyContent(replyContent);
            updatedReply.setMemNo(memNo);
            updatedReply.setBoardNo(Integer.parseInt(boardNoStr));

            // 댓글 수정 서비스 호출
            int result = replyService.replyUpdate(updatedReply);
            System.out.println("4");
            
            
			/*
			 * JSONObject jObj = new JSONObject(); jObj.put("url",
			 * "/boardDetail.do?boardNo=" + boardNoStr); resp.getWriter().print(jObj);
			 */
            
            
            
            System.out.println(result);
            // 수정 결과에 따른 처리
            // 수정 성공 시 JSON응답 생성
//            PrintWriter out = resp.getWriter();
            if (result > 0) {
                JSONObject jObj = new JSONObject();
                jObj.put("status", "success");
                jObj.put("boardNo", boardNoStr);
        		resp.getWriter().print(jObj);
            	
            	
            	
//                resp.setContentType("application/json");
//                resp.setCharacterEncoding("UTF-8");
//                JSONObject jObj = new JSONObject(); jObj.put("url", "/boardDetail.do?boardNo=" + boardNoStr); 
//                resp.getWriter().print(jObj);
				/*
				 * // 직접 JSON 문자열 생성 out.print("{\"status\": \"success\"}");
				 */
            } else {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN, "댓글 수정 권한이 없습니다.");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 번호 형식입니다.");
        }
    }
}