package kr.or.ddit.board.controller;

import java.io.IOException;
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
import kr.or.ddit.util.RequestToVoMapper;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.CouponVo;
import kr.or.ddit.vo.LoveVo;
import kr.or.ddit.vo.MemberVo;


@WebServlet("/boardList.do")
public class BoardListController extends HttpServlet {
    private IBoardService BoardService;
    ILoveService loveService = LoveServiceImpl.getInstance();
    
    @Override
    public void init() throws ServletException {
        BoardService = BoardServiceImpl.getInstance();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	//요청 파라미터에 따라 게시판 조회 분류
    	//파라미터에 따라 값 꺼내오기
    	String codeNoStr = req.getParameter("codeNo");
    	int codeNumber = Integer.parseInt(codeNoStr);

    	BoardVo board = new BoardVo();
    	
    	board.setCodeNumber(codeNumber);
    	
    	List<BoardVo> boardList = BoardService.boardList(board);
    	
    	LoveVo love = RequestToVoMapper.mapRequestToVo(req, LoveVo.class);
			
    	req.setAttribute("boardList", boardList);
    	System.out.println("djflskdjf"+boardList);
        if(codeNumber == 1) req.getRequestDispatcher("/WEB-INF/view/board/plan/planList.jsp").forward(req, resp);
        if(codeNumber == 2) req.getRequestDispatcher("/WEB-INF/view/board/talk/talkList.jsp").forward(req, resp);
        if(codeNumber == 3) req.getRequestDispatcher("/WEB-INF/view/board/review/reviewList.jsp").forward(req, resp);
    }
}