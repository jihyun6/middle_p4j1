package kr.or.ddit.payment.service;

import java.util.List;

import kr.or.ddit.vo.PaymentVo;

public interface IPaymentService {

	public List<PaymentVo> paymentList(PaymentVo payment);
	public PaymentVo paymentDetail(PaymentVo payment);
	public int paymentInsert (PaymentVo payment);
	public void updatePaymentStatus(PaymentVo payment);
	public PaymentVo getLatestPayment(int memNo);
	public List<PaymentVo> getPaymentListByMember(int memNo);
	
	public List<PaymentVo> getPaymentList(int memNo);
	

}
