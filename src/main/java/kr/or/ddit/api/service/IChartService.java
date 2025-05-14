package kr.or.ddit.api.service;

import java.util.List;

import kr.or.ddit.vo.PaymentVo;

public interface IChartService {

	public List<PaymentVo> chart (PaymentVo chart);
}
