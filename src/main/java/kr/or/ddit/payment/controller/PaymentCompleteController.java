package kr.or.ddit.payment.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.cart.service.CartServiceImpl;
import kr.or.ddit.cart.service.IcartService;
import kr.or.ddit.payment.service.IPaymentService;
import kr.or.ddit.payment.service.PaymentServiceImpl;
import kr.or.ddit.vo.CartVo;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.PaymentVo;

@WebServlet("/paymentComplete.do")
public class PaymentCompleteController extends HttpServlet {
    private IPaymentService paymentService = PaymentServiceImpl.getInstance();
    private IcartService cartService = CartServiceImpl.getServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        try {
            String pgToken = req.getParameter("pg_token");
            System.out.println("PG Token: " + pgToken);

            HttpSession session = req.getSession();
            MemberVo member = (MemberVo) session.getAttribute("member");

            if (member == null) {
                resp.sendRedirect("login.do");
                return;
            }

            List<PaymentVo> paymentList = paymentService.getPaymentList(member.getMemNo());
            PaymentVo paymentVal = paymentList.get(0);
            for(int i=0;i<paymentList.size();i++) {
            	PaymentVo payment = paymentList.get(i);
            	
            	System.out.println("조회된 결제 정보: " + payment);
                System.out.println("컨텐츠 제목: " + payment.getContentTitle());

                payment.setPayStatus("결제완료");
                paymentService.updatePaymentStatus(payment);
            }
            
            List<CartVo> cartList = cartService.cartlist(member.getMemNo());
            req.setAttribute("cartList", cartList);
            req.setAttribute("payment", paymentVal);
            req.getRequestDispatcher("/WEB-INF/view/payment/paymentComplete.jsp")
               .forward(req, resp);
            
//            if (payment != null) {
//                // 추가 로깅
//                System.out.println("조회된 결제 정보: " + payment);
//                System.out.println("컨텐츠 제목: " + payment.getContentTitle());
//
//                payment.setPayStatus("결제완료");
//                paymentService.updatePaymentStatus(payment);
//
//                List<CartVo> cartList = cartService.cartlist(member.getMemNo());
//                req.setAttribute("cartList", cartList);
//                req.setAttribute("payment", payment);
//                req.getRequestDispatcher("/WEB-INF/view/payment/paymentComplete.jsp")
//                   .forward(req, resp);
//            } else {
//                System.out.println("결제 정보를 찾을 수 없음 - 회원 번호: " + member.getMemNo());
//                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "결제 정보를 찾을 수 없습니다.");
//            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 
                "결제 완료 처리 중 오류가 발생했습니다.");
        }
    }
}