package kr.or.ddit.api.dao;

import java.util.List;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.PaymentVo;

public class ChartDaoImpl extends MybatisDao implements IChartDao{

	private static ChartDaoImpl instance;

	private ChartDaoImpl() {

	}

	public static ChartDaoImpl getInstance() {
		if (instance == null)
			instance = new ChartDaoImpl();
		return instance;
	}

	
	@Override
	public List<PaymentVo> chart(PaymentVo chart) {
		// TODO Auto-generated method stub
		return selectList("chart.chart", chart);
	}

}
