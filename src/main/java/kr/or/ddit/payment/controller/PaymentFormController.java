package kr.or.ddit.payment.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.vo.PaymentVo;

@WebServlet("/paymentForm.do")
public class PaymentFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // payment 객체는 이전 페이지에서 전달받았다고 가정
        PaymentVo payment = (PaymentVo) req.getAttribute("payment");
        
        req.setAttribute("payment", payment);
        req.getRequestDispatcher("/WEB-INF/view/payment/paymentForm.jsp").forward(req, resp);
    }
}
