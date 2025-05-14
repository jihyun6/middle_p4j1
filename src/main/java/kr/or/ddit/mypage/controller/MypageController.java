package kr.or.ddit.mypage.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.mypage.service.IMypageService;
import kr.or.ddit.mypage.service.MypageServiceImpl;
import kr.or.ddit.payment.service.IPaymentService;
import kr.or.ddit.payment.service.PaymentServiceImpl;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.PaymentVo;

@WebServlet("/mypage.do")
public class MypageController extends HttpServlet {
    private IMypageService mypageService = MypageServiceImpl.getInstance();
    private IPaymentService paymentService = PaymentServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MemberVo member = (MemberVo) session.getAttribute("member");

        if (member == null) {
            resp.sendRedirect("login.do");
            return;
        }

        // 회원 상세 정보 조회
        MemberVo mypage = mypageService.myPage(member);
        req.setAttribute("mypage", mypage);

        // 결제 내역 미리 로드 (선택사항)
        List<PaymentVo> paymentList = paymentService.getPaymentListByMember(member.getMemNo());
        req.setAttribute("paymentList", paymentList);

        req.getRequestDispatcher("/WEB-INF/view/mypage/mypageDetail.jsp").forward(req, resp);
    }
}