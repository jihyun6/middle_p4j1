package kr.or.ddit.api.dao;

import java.util.List;

import kr.or.ddit.vo.PaymentVo;

public interface IChartDao {

	public List<PaymentVo> chart (PaymentVo chart);
}
