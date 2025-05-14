package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.contents.service.ContentsServiceImpl;
import kr.or.ddit.contents.service.IContentsService;
import kr.or.ddit.travelplan.service.IPlanService;
import kr.or.ddit.travelplan.service.PlanServiceImpl;
import kr.or.ddit.util.RequestToVoMapper;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.ContentsVo;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/boardInsert.do")
@MultipartConfig
public class BoardInsertController extends HttpServlet{

	IBoardService boardService = BoardServiceImpl.getInstance();
	IPlanService planService = PlanServiceImpl.getInstance();
	IContentsService contentsService = ContentsServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청 파라미터에 따라 게시판 조회 분류
    	//파라미터에 따라 값 꺼내오기
    	String codeNoStr = req.getParameter("codeNo");
    	int codeNumber = Integer.parseInt(codeNoStr);
    	
    	//세션에 저장되어있는 데이터 꺼내오기
    	HttpSession session = req.getSession();
    	MemberVo member = (MemberVo) session.getAttribute("member");
    	
    	req.setAttribute("codeNumber", codeNumber);
    	req.setAttribute("member", member);
        if(codeNumber == 1) {
        	ContentsVo cont = new ContentsVo();
        	List<ContentsVo> contList = planService.contType(cont);
    		req.setAttribute("contentsList", contList);
        	req.getRequestDispatcher("/WEB-INF/view/board/plan/planInsert.jsp").forward(req, resp);
        }
        if(codeNumber == 2) req.getRequestDispatcher("/WEB-INF/view/board/talk/talkInsert.jsp").forward(req, resp);
        if(codeNumber == 3) {
        	
        	System.out.println("conTitle" + req.getParameter("conTitle"));
        	System.out.println("contentNo" + req.getParameter("contentNo"));
        	String contentsTitle = req.getParameter("conTitle");
        	String contentNoStr = req.getParameter("contentNo");
        	ContentsVo contents = new ContentsVo();

        	if(contentsTitle != null ) {
        		contents.setConTitle(contentsTitle);
        		req.setAttribute("contents", contents);
        	}
        	
        	if(contentNoStr != null ) {
        		contents.setContentNo(contentNoStr);
        	}
        	
        	req.getRequestDispatcher("/WEB-INF/view/board/review/reviewInsert.jsp").forward(req, resp);
        }
		
//		ServletContext ctx = req.getServletContext();
//		ctx.getRequestDispatcher("/WEB-INF/view/board/talk/talkInsert.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Gson gson = new Gson();
		
		//form에서 전송된 데이터를 JSON String데이터로 변환
//		String JsonStr= gson.toJson(req.getParameterMap());
//		System.out.println(JsonStr);
//		
//		JsonStr = JsonStr.replace("[", "");
//		JsonStr = JsonStr.replace("]", "");
//		
//		//JSON Strirng 데이터를 vo타입으로 변환
//		BoardVo board = gson.fromJson(JsonStr, BoardVo.class);
		
		
		BoardVo board = RequestToVoMapper.mapRequestToVo(req, BoardVo.class);
		
		//BoardNo 구하기
		int boardNo = boardService.getBoardNo();
		
		//세션에 저장되어있는 데이터 꺼내오기
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("member") == null) {
            // 로그인되지 않은 경우 처리
            req.setAttribute("errorMsg", "로그인이 필요합니다.");
            req.getRequestDispatcher("/WEB-INF/view/error/login.jsp").forward(req, resp);
            return;
        }
    	
    	MemberVo member = (MemberVo) session.getAttribute("member");
		board.setMemNo(member.getMemNo());
		
		// Insert 수행
		int result = boardService.boardInsert(board);
		if(req.getContentType().contains("multipart/form-data")) {
			boardService.fileWrite(req,boardNo);
		}else {
			
			if(req.getParameter("codeNumber").equals("3")) {
				JSONObject jObj = new JSONObject();
				jObj.put("url", "/contentsDetail.do?contentNo=" + req.getParameter("contentNo"));
				
				resp.setContentType("application/x-json; charset=utf-8");
				resp.getWriter().print(jObj);
				
			}
			
			
			else {
				JSONObject jObj = new JSONObject();
				jObj.put("url", "/boardList.do?codeNo=" + board.getCodeNumber());
				
				resp.setContentType("application/x-json; charset=utf-8");
				resp.getWriter().print(jObj);
				
			}
		}
		
		//		System.out.println("insert post");
		
		//로그인-멤버 정보 불러오기
//		String memNoStr = req.getParameter("memNo");
//		int memNo = Integer.parseInt(memNoStr);
		
//		String boardNoStr = req.getParameter("boardNo");
//		int boardNo = Integer.parseInt(boardNoStr);
		
//		String boardName = req.getParameter("boardName");
//		String boardContent = req.getParameter("boardContent");
//		String boardTag = req.getParameter("boardTag");
//		String boardStarStr = req.getParameter("boardStar");
//		int boardStar = Integer.parseInt(boardStarStr);
		//필요하면 추가
//		String trialAreacodeStr = req.getParameter("trialAreacode");
//		int trialAreacode = Integer.parseInt(trialAreacodeStr);
		
		//캘린더 연동
//		String travelStart = req.getParameter("travelStart");
//		String travelEnd = req.getParameter("travelEnd");
		
		//작성내용 담아주기
//		BoardVo talk = new BoardVo();
//		talk.setMemNo(memNo);
//		talk.setBoardNo(boardNo);
//		talk.setBoardName(boardName);
//		talk.setBoardContent(boardContent);
//		talk.setBoardTag(boardTag);
//		talk.setBoardStar(boardStar);
//		talk.setTrialAreacode(trialAreacode);
//		talk.setTravelStart(travelStart);
//		talk.setTravelEnd(travelEnd);

//		boardService.talkInsert(talk);

//		resp.sendRedirect(req.getContextPath()+"/talkList.do");
	}
}
