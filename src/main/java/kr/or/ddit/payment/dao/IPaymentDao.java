package kr.or.ddit.payment.dao;

import java.util.List;

import kr.or.ddit.vo.PaymentVo;

public interface IPaymentDao {

	public List<PaymentVo> paymentList(PaymentVo payment);
	public PaymentVo paymentDetail(PaymentVo payment);
	public int paymentInsert (PaymentVo payment);
	public void updatePaymentStatus(PaymentVo payment);
	public PaymentVo getLatestPayment(int memNo);
	public String getContentsNameByNo(String contentNo);
	public List<PaymentVo> getPaymentListByMember(int memNo);
	
	public List<PaymentVo> getPaymentList(int memNo);
	
}
