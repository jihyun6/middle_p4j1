package kr.or.ddit.api.service;

import java.util.List;

import kr.or.ddit.api.dao.ChartDaoImpl;
import kr.or.ddit.api.dao.IChartDao;
import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.PaymentVo;

public class ChartServiceImpl extends MybatisDao implements IChartDao{

	private static ChartServiceImpl instance;
	
	ChartDaoImpl chartDao = ChartDaoImpl.getInstance();
			
	private ChartServiceImpl() {

	}

	public static ChartServiceImpl getInstance() {
		if (instance == null)
			instance = new ChartServiceImpl();
		return instance;
	}

	
	
	@Override
	public List<PaymentVo> chart(PaymentVo chart) {
		// TODO Auto-generated method stub
		return chartDao.chart(chart);
	}


}
