package kr.or.ddit.report.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.report.dao.IReportDao;
import kr.or.ddit.report.dao.ReportDaoImpl;
import kr.or.ddit.vo.ReportVo;

public class ReportServiceImpl implements IReportService {
    private static ReportServiceImpl instance;
    private IReportDao reportDao;
    
    private ReportServiceImpl() {
        reportDao = ReportDaoImpl.getInstance();
    }
    
    public static ReportServiceImpl getInstance() {
        if (instance == null)
            instance = new ReportServiceImpl();
        return instance;
    }
    
    @Override
    public List<ReportVo> reportList(ReportVo report) {
        try {
            List<ReportVo> list = reportDao.reportList(report);
            return list != null ? list : new ArrayList<>();
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    @Override
    public ReportVo reportDetail(ReportVo report) {
        try {
            return reportDao.reportDetail(report);
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public int reportInsert(ReportVo report) {
        try {
            return reportDao.reportInsert(report);
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public int reportDelete(ReportVo report) {
        try {
            return reportDao.reportDelete(report);
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public int reportUpdate(ReportVo report) {
        try {
            return reportDao.reportUpdate(report);
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    

    
    @Override
    public List<ReportVo> getReportsByBoardNo(int boardNo) {
        try {
            List<ReportVo> reports = reportDao.getReportsByBoardNo(boardNo);
            return reports != null ? reports : new ArrayList<>();
        } catch(Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public int updateReportStatus(ReportVo reportVo) {
        try {
            return reportDao.updateReportStatus(reportVo);
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    
    
}