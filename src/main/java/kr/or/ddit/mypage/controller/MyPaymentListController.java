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
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.PaymentVo;

@WebServlet("/myPaymentList.do")
public class MyPaymentListController extends HttpServlet {
    private IMypageService mypageService = MypageServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        MemberVo member = (MemberVo) session.getAttribute("member");
        
        if (member == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("로그인이 필요합니다.");
            return;
        }
        
        List<PaymentVo> paymentList = mypageService.mypagePaymentList(member.getMemNo());
        
        req.setAttribute("paymentList", paymentList);
        req.getRequestDispatcher("/WEB-INF/view/member/myPaymentListFragment.jsp")
           .forward(req, resp);
    }
}