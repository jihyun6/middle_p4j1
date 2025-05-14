package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.love.service.ILoveService;
import kr.or.ddit.love.service.LoveServiceImpl;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.reply.service.ReplyServiceImpl;
import kr.or.ddit.vo.AttachFileVo;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.LoveVo;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.PlanContVo;
import kr.or.ddit.vo.ReplyVo;

@WebServlet("/boardDetail.do")
public class BoardDetailController extends HttpServlet {
    private IBoardService boardService = BoardServiceImpl.getInstance();
    private IReplyService replyService = ReplyServiceImpl.getInstance();
    ILoveService loveService = LoveServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 게시글 번호 파라미터 조회 시 null 체크
        String boardNoStr = req.getParameter("boardNo");
        if (boardNoStr == null || boardNoStr.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 게시글 번호입니다.");
            return;
        }

        try {
            int boardNo = Integer.parseInt(boardNoStr);

            // 게시글 조회
            BoardVo board = boardService.boardDetail(new BoardVo(boardNo));

            if (board == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "게시글을 찾을 수 없습니다.");
                return;
            }

            // 댓글 목록 조회
            ReplyVo replyParam = new ReplyVo();
            replyParam.setBoardNo(boardNo);
            List<ReplyVo> replyList = replyService.replyList(replyParam);
            
            //여행일정 내 컨텐츠 목록 조회
    		List<BoardVo> planConList=boardService.planContList(board);
    		req.setAttribute("planConList", planConList);
            
            //좋아요
            HttpSession session = req.getSession();
    		MemberVo member = (MemberVo) session.getAttribute("member");
    		
            if(session == null || session.getAttribute("member") == null) {
            	
            }else {
            	LoveVo loveCCount = new LoveVo();
    			loveCCount.setBoardNo(boardNo);
    			System.out.println(loveCCount);
    			int loveCount=loveService.loveBoardCount(loveCCount);
    			req.setAttribute("loveCount", loveCount);
    			System.out.println("loveCountdfdfsfsdfsdf"+loveCount);
    			
    			LoveVo love = new LoveVo();
    			love.setBoardNo(boardNo);
    			love.setMemNo(member.getMemNo());
    			
    			System.out.println("love"+love);
    			int check = loveService.lovedByMemBoard(love);
    			if (check == 1) {
    				req.setAttribute("lovedByMem", true);
    			} else {
    				req.setAttribute("lovedByMem", false);
    			}
				req.setAttribute("board", board);
	            System.out.println(board+":22222222222boarad");
            }

            // 안전한 출력
            if (replyList != null) {
                System.out.println("댓글 목록: " + replyList);
                System.out.println("댓글 개수: " + replyList.size());
            } else {
                System.out.println("댓글 목록이 null입니다.");
                replyList = new ArrayList<>(); // null 대신 빈 리스트
            }

            // 속성 설정
            req.setAttribute("board", board);
            System.out.println(board+":22222222222boarad");
            req.setAttribute("replyList", replyList);

            // 파일 목록 추가
            List<AttachFileVo> fileList = boardService.fileList(boardNo);
            req.setAttribute("fileList", fileList);

            // 게시판 유형에 따른 페이지 포워딩
            String forwardPath = determineForwardPath(board.getCodeNumber());
            req.getRequestDispatcher(forwardPath).forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 게시글 번호 형식입니다.");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다.");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    }

    // 게시판 유형에 따른 포워딩 경로 결정 메서드
    private String determineForwardPath(int codeNumber) {
        switch (codeNumber) {
            case 1: return "/WEB-INF/view/board/plan/planView.jsp";
            case 2: return "/WEB-INF/view/board/talk/talkDetail.jsp";
            case 3: return "/WEB-INF/view/board/review/reviewDetail.jsp";
            default:
                throw new IllegalArgumentException("알 수 없는 게시판 유형입니다.");
        }
    }
}