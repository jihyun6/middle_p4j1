package kr.or.ddit.travelplan.controller;

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
import kr.or.ddit.contents.service.ContentsServiceImpl;
import kr.or.ddit.contents.service.IContentsService;
import kr.or.ddit.travelplan.service.IPlanService;
import kr.or.ddit.travelplan.service.PlanServiceImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.ContentsVo;

@WebServlet("/planUpdate.do")
public class PlanUpdateController extends HttpServlet{
	
	IPlanService planService = PlanServiceImpl.getInstance();
	IContentsService contentsService = ContentsServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String boardNoStr = req.getParameter("boardNo");
		int boardNo = Integer.parseInt(boardNoStr);
		
		BoardVo board = new BoardVo();
		board.setBoardNo(boardNo);
		
		ContentsVo cont = new ContentsVo();
		List<ContentsVo> contList = planService.contType(cont);
		req.setAttribute("contentsList", contList);

		System.out.println(contList);
		
		board = planService.planView(board);
		
		
		req.setAttribute("board", board);
		
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/board/plan/planUpdate.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Gson gson = new Gson();
		
		//form에서 전송된 데이터를 JSON String데이터로 변환
		String JsonStr= gson.toJson(req.getParameterMap());
		System.out.println(JsonStr);
		
		JsonStr = JsonStr.replace("[", "");
		JsonStr = JsonStr.replace("]", "");
		
		//JSON Strirng 데이터를 vo타입으로 변환
		BoardVo board = gson.fromJson(JsonStr, BoardVo.class);
		
		// 업데이트 수행
		int result = planService.planUpdate(board);
		
		JSONObject jObj = new JSONObject();
		jObj.put("url", "/boardDetail.do?boardNo=" + board.getBoardNo());

		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jObj);
	
	
		
	}
	
}
