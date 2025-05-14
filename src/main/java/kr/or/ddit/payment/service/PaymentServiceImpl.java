package kr.or.ddit.payment.service;
import java.util.List;
import kr.or.ddit.payment.dao.IPaymentDao;
import kr.or.ddit.payment.dao.PaymentDaoImpl;
import kr.or.ddit.vo.PaymentVo;

public class PaymentServiceImpl implements IPaymentService {
    private static PaymentServiceImpl instance;
    private IPaymentDao paymentDao;  // 필드로 이동
    
    private PaymentServiceImpl() {
        paymentDao = PaymentDaoImpl.getInstance();  // 생성자에서 초기화
    }
    
    public static PaymentServiceImpl getInstance() {
        if (instance == null)
            instance = new PaymentServiceImpl();
        return instance;
    }
    
    @Override
    public List<PaymentVo> paymentList(PaymentVo payment) {
        return paymentDao.paymentList(payment);
    }
    
    @Override
    public PaymentVo paymentDetail(PaymentVo payment) {
        return paymentDao.paymentDetail(payment);
    }
    
    @Override
    public int paymentInsert(PaymentVo payment) {
        int result = paymentDao.paymentInsert(payment);
        if (result > 0) {
            // 결제가 성공적으로 이루어진 경우, 상태를 '결제완료'로 업데이트
            updatePaymentStatus(payment);
        }
        return result;
    }
    
    @Override
    public void updatePaymentStatus(PaymentVo payment) {
        payment.setPayStatus("결제완료");
        paymentDao.updatePaymentStatus(payment);
    }
    
    public PaymentVo getLatestPayment(int memNo) {
        return paymentDao.getLatestPayment(memNo);
    }
    
    @Override
    public List<PaymentVo> getPaymentListByMember(int memNo) {
        return paymentDao.getPaymentListByMember(memNo);
    }

	@Override
	public List<PaymentVo> getPaymentList(int memNo) {
		return paymentDao.getPaymentList(memNo);
	}
    
}