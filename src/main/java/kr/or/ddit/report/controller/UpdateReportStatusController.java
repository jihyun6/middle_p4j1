package kr.or.ddit.report.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.report.service.IReportService;
import kr.or.ddit.report.service.ReportServiceImpl;
import kr.or.ddit.vo.ReportVo;

@WebServlet("/updateReportStatus.do")
public class UpdateReportStatusController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// 파라미터 받기
			String reStatus = req.getParameter("reStatus"); // String으로 받음
			int reNo = Integer.parseInt(req.getParameter("reNo"));
			int memNo = Integer.parseInt(req.getParameter("memNo"));

			// ReportVo 객체 생성 및 설정
			ReportVo reportVo = new ReportVo();
			reportVo.setReNo(reNo);
			reportVo.setMemNo(memNo);
			reportVo.setReStatus(reStatus); // String 타입 그대로 설정

			// 서비스 호출
			IReportService reportService = ReportServiceImpl.getInstance();
			int result = reportService.updateReportStatus(reportVo);

			// JSON 응답
			// AJAX를 사용하는 비동기 
			// 페이지 전체를 새로고침하지 않고 부분 업데이트처리
			resp.setContentType("application/json"); // 응답 타입을 JSON으로 지정
			resp.getWriter().write("{\"result\":\"success\"}"); // JSON 형식의 응답 데이터

		} catch (Exception e) {
			// 에러 처리
			// SC_INTERNAL_SERVER_ERROR = HTTP 상태 코드 500을 나타내는 상수
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			resp.getWriter().write("{\"result\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
		}
	}
}
