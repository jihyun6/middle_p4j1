package kr.or.ddit.report.service;

import java.util.List;

import kr.or.ddit.vo.ReportVo;

public interface IReportService {

	public List<ReportVo> reportList(ReportVo report);
	
	public ReportVo reportDetail(ReportVo report);
	
	public int reportInsert (ReportVo report);
	
	public int reportDelete (ReportVo report);
	
	public int reportUpdate (ReportVo report);
	
	public int updateReportStatus(ReportVo reportVo);
	
	 public List<ReportVo> getReportsByBoardNo(int boardNo);
}
