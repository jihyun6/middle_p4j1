package kr.or.ddit.report.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.report.service.IReportService;
import kr.or.ddit.report.service.ReportServiceImpl;
import kr.or.ddit.vo.ReportVo;

@WebServlet("/reportList.do")
public class ReportListController extends HttpServlet {
	private IReportService reportService;

	@Override
	public void init() throws ServletException {
		reportService = ReportServiceImpl.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ReportVo report = new ReportVo();
		List<ReportVo> reportList = reportService.reportList(report);

		System.out.println("reportList size: " + reportList.size()); // 데이터 확인
		for (ReportVo vo : reportList) {
			System.out.println(vo); // 각 데이터 확인
		}

		req.setAttribute("reportList", reportList);
		req.getRequestDispatcher("/WEB-INF/view/report/reportList.jsp").forward(req, resp);
	}

}
