
package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.Gson;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.contents.service.ContentsServiceImpl;
import kr.or.ddit.contents.service.IContentsService;
import kr.or.ddit.travelplan.service.IPlanService;
import kr.or.ddit.travelplan.service.PlanServiceImpl;
import kr.or.ddit.util.RequestToVoMapper;
import kr.or.ddit.vo.AttachFileVo;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.ContentsVo;

@WebServlet("/boardUpdate.do")
public class BoardUpdateController extends HttpServlet{

	IBoardService boardService = BoardServiceImpl.getInstance();
	IContentsService contentsService = ContentsServiceImpl.getInstance();
	IPlanService planSercvice = PlanServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("BoardUpdateController에 왔다");
		
		//게시글 번호, 게시글 타입 파라미터 조회
		String boardNoStr = req.getParameter("boardNo");
		int boardNo = Integer.parseInt(boardNoStr);
		
		BoardVo board = new BoardVo();
		board.setBoardNo(boardNo);
	

		board = boardService.boardDetail(board);
		
		req.setAttribute("board", board);
		
		//여행일정 내 컨텐츠 목록 조회
		List<BoardVo> planConList=boardService.planContList(board);
		req.setAttribute("planConList", planConList);
       
		// 파일 목록 추가
        List<AttachFileVo> fileList = boardService.fileList(boardNo);
        req.setAttribute("fileList", fileList);
        
		ServletContext ctx = req.getServletContext();
		
		int codeNumber = board.getCodeNumber();
		if(codeNumber == 1) {
			ContentsVo cont = new ContentsVo();
        	List<ContentsVo> contList = planSercvice.contType(cont);
    		req.setAttribute("contentsList", contList);
    		req.getRequestDispatcher("/WEB-INF/view/board/plan/planUpdate.jsp").forward(req, resp);
		}
		if(codeNumber == 2) req.getRequestDispatcher("/WEB-INF/view/board/talk/talkUpdate.jsp").forward(req, resp);
		if(codeNumber == 3) {
			
			System.out.println("conTitle" + req.getParameter("conTitle"));
        	System.out.println("contentNo" + req.getParameter("contentNo"));
        	String contentsTitle = req.getParameter("conTitle");
        	String contentNoStr = req.getParameter("contentNo");
        	ContentsVo contents = new ContentsVo();
        	
        	if(contentsTitle != null ) {
        		contents.setConTitle(contentsTitle);
        	}
        	
        	if(contentNoStr != null ) {
        		contents.setContentNo(contentNoStr);
        	}
        	req.setAttribute("contents", contents);
			ctx.getRequestDispatcher("/WEB-INF/view/board/review/reviewUpdate.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BoardUpdateController에 왔다(post)");
		
		System.out.println("req.getParameterMap()" + req.getParameterMap());
		
		BoardVo board = RequestToVoMapper.mapRequestToVo(req, BoardVo.class);
		
//		Gson gson = new Gson();
//		
//		//form에서 전송된 데이터를 JSON String데이터로 변환
//		String JsonStr= gson.toJson(req.getParameterMap());
//		System.out.println(JsonStr);
//		
//		JsonStr = JsonStr.replace("[", "");
//		JsonStr = JsonStr.replace("]", "");
//		
//		//JSON Strirng 데이터를 vo타입으로 변환
//		BoardVo board = gson.fromJson(JsonStr, BoardVo.class);
		
		// 업데이트 수행
		int result = boardService.boardUpdate(board);
		
		JSONObject jObj = new JSONObject();
		jObj.put("url", "/boardDetail.do?boardNo=" + board.getBoardNo());

		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jObj);
		
		//resp.sendRedirect(req.getContextPath() + "/boardDetail.do?boardNo=" + board.getBoardNo());
		
//		System.out.println("JSONStr:" + JsonStr);
//		System.out.println(board);
		
		// 수정할 데이터 받기
//		String boardNoStr = req.getParameter("boardNo");
//		String boardName = req.getParameter("title");
//		String content = req.getParameter("content");
//		String date = req.getParameter("date");
//		String starStr = req.getParameter("boardStar");
		
//		int boardNo = Integer.parseInt(boardNoStr);
//		
//		// BoardVo 객체 설정
//		BoardVo boardVo = new BoardVo();
//		
//		if(starStr != null && !(starStr.equals(""))) {
//			int star = Integer.parseInt(starStr);
//			boardVo.setBoardStar(star);
//		}
//		
//		boardVo.setBoardNo(boardNo);
//		boardVo.setBoardName(boardName);
//		boardVo.setBoardContent(content);
//		boardVo.setBoardDate(date);

		/*
		BoardVo(boardNo=2, boardName=제목, boardContent=테스트, boardDate=2024-12-26 11:45:13.41964
		, boardUpdate=null, boardDelyn=null, boardClick=0, boardTag=null, boardStar=1, boardWeather=null
		, trialAreacode=0, contentsTypeId=0, memNo=0, codeNumber=0, travelStart=null, travelEnd=null
		, memName=null, contentsTypeName=null, codeName=null)
		 */
//		System.out.println(boardVo);

	}
}