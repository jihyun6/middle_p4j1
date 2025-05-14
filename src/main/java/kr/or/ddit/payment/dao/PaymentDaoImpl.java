package kr.or.ddit.payment.dao;

import java.util.List;
import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.PaymentVo;

public class PaymentDaoImpl extends MybatisDao implements IPaymentDao {
    private static PaymentDaoImpl instance;
    
    private PaymentDaoImpl() {
    }
    
    public static PaymentDaoImpl getInstance() {
        if (instance == null)
            instance = new PaymentDaoImpl();
        return instance;
    }

    @Override
    public List<PaymentVo> paymentList(PaymentVo payment) {
        return selectList("payment.paymentList", payment);
    }

    @Override
    public PaymentVo paymentDetail(PaymentVo payment) {
        return selectOne("payment.paymentDetail", payment);
    }

    @Override
    public int paymentInsert(PaymentVo payment) {
        // 먼저 결제 정보를 삽입
        int result = update("payment.paymentInsert", payment);
        
        // 성공적으로 삽입되었다면, 같은 결제번호의 모든 항목 상태를 업데이트
        if (result > 0) {
            updatePaymentStatus(payment);
        }
        return result;
    }

    @Override
    public void updatePaymentStatus(PaymentVo payment) {
        // 같은 결제번호를 가진 모든 항목의 상태를 '결제완료'로 변경
        update("payment.updatePaymentStatus", payment);
    }

    @Override
    public PaymentVo getLatestPayment(int memNo) {
        return selectOne("payment.getLatestPayment", memNo);
    }
    
    @Override
    public List<PaymentVo> getPaymentList(int memNo) {
        return selectList("payment.getPaymentList", memNo);
    }
    
    @Override
    public String getContentsNameByNo(String contentNo) {
        return selectOne("payment.getContentsNameByNo", contentNo);
    }
    
    @Override
    public List<PaymentVo> getPaymentListByMember(int memNo) {
        return selectList("payment.paymentList", memNo);
    }
}