package kr.or.ddit.board.controller;

import java.io.IOException;

import org.json.JSONObject;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVo;

@WebServlet("/boardUpdateAjax.do")
public class BoardUpdateAjaxController extends HttpServlet{

	IBoardService boardService = BoardServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BoardUpdateController에 왔다(post)");
		
		//gson
//		Gson gson = new Gson();
//		gson.toJson(req.);
		
		
		// 수정할 데이터 받기
		String boardNoStr = req.getParameter("boardNo");
		String boardName = req.getParameter("title");
		String content = req.getParameter("content");
		String date = req.getParameter("date");
		String starStr = req.getParameter("boardStar");
		
		int boardNo = Integer.parseInt(boardNoStr);
		
		// BoardVo 객체 설정
		BoardVo boardVo = new BoardVo();
		
		if(starStr != null && !(starStr.equals(""))) {
			int star = Integer.parseInt(starStr);
			boardVo.setBoardStar(star);
		}
		
		boardVo.setBoardNo(boardNo);
		boardVo.setBoardName(boardName);
		boardVo.setBoardContent(content);
		boardVo.setBoardDate(date);

		/*
		BoardVo(boardNo=2, boardName=제목, boardContent=테스트, boardDate=2024-12-26 11:45:13.41964
		, boardUpdate=null, boardDelyn=null, boardClick=0, boardTag=null, boardStar=1, boardWeather=null
		, trialAreacode=0, contentsTypeId=0, memNo=0, codeNumber=0, travelStart=null, travelEnd=null
		, memName=null, contentsTypeName=null, codeName=null)
		 */
		System.out.println(boardVo);
		
		// 업데이트 수행
		int result = boardService.boardUpdate(boardVo);
		System.out.println("boardUpdateAjax.do->result : " + result);
		
		//클라이언트 reviewUpdate.jsp -> 달러("샵frm").serialize() : form 태그 안의 요소들-> 서버 
		//서버 -> JSON 타입의 String문자{"boardNo":3} -> 클라이언트
		JSONObject jObj = new JSONObject();
		jObj.put("boardNo", boardNoStr); // key, value

		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jObj);
	}
}