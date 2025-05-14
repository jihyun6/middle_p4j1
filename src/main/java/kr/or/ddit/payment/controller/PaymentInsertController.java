package kr.or.ddit.payment.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.payment.service.IPaymentService;
import kr.or.ddit.payment.service.PaymentServiceImpl;
import kr.or.ddit.vo.PaymentVo;

@WebServlet("/paymentInsert.do")
public class PaymentInsertController extends HttpServlet {
   private IPaymentService paymentService = PaymentServiceImpl.getInstance();
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try {
           String memNoStr = req.getParameter("memNo");
           String payPriceStr = req.getParameter("payPrice");
           
           // null 체크  null: 클라이언트가 해당 파라미터를 보내지 않은 경우.
           // isEmpty(): 파라미터가 비어 있는 경우.
           // 문제 상황: 필수 파라미터가 누락되었을 때 예외(IllegalArgumentException)를 던집니다.
           if(memNoStr == null || memNoStr.trim().isEmpty() || 
              payPriceStr == null || payPriceStr.trim().isEmpty()) {
               throw new IllegalArgumentException("필수 값이 누락되었습니다.");
           }
           
           PaymentVo payment = new PaymentVo();
           payment.setMemNo(Integer.parseInt(memNoStr));
           payment.setContentNo(req.getParameter("contentNo"));
           payment.setContentsTypeId(Integer.parseInt(req.getParameter("contentsTypeId")));
           payment.setPayPrice(Integer.parseInt(payPriceStr));
           payment.setPayMethod(req.getParameter("payMethod"));
           payment.setPayStatus(req.getParameter("payStatus"));
           payment.setPayDate(new java.sql.Date(System.currentTimeMillis()));
           payment.setPayDelyn("N");
           
           // 실제 실행되는 단계 데이터베이스에 결제 정보 삽입
           int result = paymentService.paymentInsert(payment);
           
           
           // 클라이언트 응답
           // Content-Type: 응답 데이터 형식을 text/plain으로 지정.
           // 
           resp.setContentType("text/plain");
           resp.setCharacterEncoding("UTF-8");
           resp.getWriter().write(result > 0 ? "success" : "fail");
           
       } catch(Exception e) {
           e.printStackTrace();
           resp.sendError(500, "결제 정보 저장 실패: " + e.getMessage());
       }
   }
}