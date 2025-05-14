package kr.or.ddit.index.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.api.dao.ContentsDaoImpl;
import kr.or.ddit.api.service.ITrialService;
import kr.or.ddit.api.service.TrialServiceImpl;
import kr.or.ddit.contents.service.ContentsServiceImpl;
import kr.or.ddit.contents.service.IContentsService;
import kr.or.ddit.vo.ContentsVo;

@WebServlet("/main.do")
public class MainController extends HttpServlet {
    
    private ITrialService trialService = TrialServiceImpl.getInstance();
    private IContentsService contentsService = ContentsServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 기존 trial 데이터
        List<Map<String, Object>> trialList = trialService.trialList();
        req.setAttribute("trialList", trialList);

        // 전체 컨텐츠 목록 가져오기
        List<ContentsVo> contentsList = contentsService.selectAllContentsList();
        req.setAttribute("contentsList", contentsList);

        // J타입 선호 컨텐츠 10개 가져오기
        List<ContentsVo> jTypeContentsList = contentsService.getJTypeTopContents();
        req.setAttribute("jTypeContentsList", jTypeContentsList);

        // P타입 선호 컨텐츠 10개 가져오기
        List<ContentsVo> pTypeContentsList = contentsService.getPTypeTopContents();
        req.setAttribute("pTypeContentsList", pTypeContentsList);

        ServletContext ctx = req.getServletContext();
        ctx.getRequestDispatcher("/WEB-INF/main2.jsp").forward(req, resp);
    }
}