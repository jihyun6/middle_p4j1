package kr.or.ddit.report.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.ReportVo;

public class ReportDaoImpl extends MybatisDao implements IReportDao {
	private static ReportDaoImpl instance;

	private ReportDaoImpl() {
		super(); // MybatisDao의 초기화 보장
	}

	public static ReportDaoImpl getInstance() {
		if (instance == null) {
			synchronized (ReportDaoImpl.class) {
				if (instance == null) {
					instance = new ReportDaoImpl();
				}
			}
		}
		return instance;
	}

	@Override
	public List<ReportVo> reportList(ReportVo report) {
		try {
			return selectList("report.reportList", report);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("신고 목록 조회 실패", e);
		}

	}

	@Override
	public ReportVo reportDetail(ReportVo report) {
		// TODO Auto-generated method stub
		return selectOne("report.reportDetail", report);
	}

	@Override
	public int reportInsert(ReportVo report) {
		// TODO Auto-generated method stub
		return update("report.reportInsert", report);
	}

	@Override
	public int reportDelete(ReportVo report) {
		// TODO Auto-generated method stub
		return update("report.reportDelete", report);
	}

	@Override
	public int reportUpdate(ReportVo report) {
		// TODO Auto-generated method stub
		return update("report.reportUpdate", report);
	}
	
	@Override
	public List<ReportVo> getReportsByBoardNo(int boardNo) {
	    try {
	        return selectList("report.getReportsByBoardNo", boardNo);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("게시글별 신고 내역 조회 실패", e);
	    }
	}

	@Override
	public int updateReportStatus(ReportVo reportVo) { 
	    try {
	        // 상태 업데이트
	        int statusResult = update("report.updateStatus", reportVo);
	        
	        // 처리완료인 경우에만 카운트 증가
	        if("2".equals(reportVo.getReStatus())) {
	            update("report.increaseCount", reportVo);
	            
	            // 게시글 상태도 업데이트
	            if(reportVo.getBoardNo() > 0) {
	                update("report.reportDelete", reportVo);
	            }
	        }
	        
	        return statusResult;
	    } catch(Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("상태 업데이트 실패", e);
	    }
	}
	
	
}
