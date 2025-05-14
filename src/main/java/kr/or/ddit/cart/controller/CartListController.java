package kr.or.ddit.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.ddit.cart.service.CartServiceImpl;
import kr.or.ddit.cart.service.IcartService;
import kr.or.ddit.vo.CartVo;
import kr.or.ddit.vo.MemberVo;

@WebServlet("/cartList.do")
public class CartListController extends HttpServlet {
    private final IcartService cartService = CartServiceImpl.getServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        MemberVo member = (MemberVo) session.getAttribute("member");

        if (member == null) {
            resp.sendRedirect("login.do");
            return;
        }

        List<CartVo> cartList = cartService.cartlist(member.getMemNo());
        
        int totalAmount = cartList.stream()
            .mapToInt(CartVo::getCartPrice)
            .sum();

        req.setAttribute("cartList", cartList);
        req.setAttribute("totalAmount", totalAmount);

        req.getRequestDispatcher("/WEB-INF/view/cart/cartlist.jsp")
           .forward(req, resp);
    }

    // 선택적으로 필요하다면 유지
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String action = req.getParameter("action");
        
        if ("payment".equals(action)) {
            // 결제 페이지로 이동
            req.getRequestDispatcher("/WEB-INF/view/payment/payment.jsp")
               .forward(req, resp);
        } else {
            // 기본 장바구니 목록 로직
            HttpSession session = req.getSession();
            MemberVo member = (MemberVo) session.getAttribute("member");

            if (member == null) {
                resp.sendRedirect("login.do");
                return;
            }

            List<CartVo> cartList = cartService.cartlist(member.getMemNo());
            
            req.setAttribute("cartList", cartList);
            req.getRequestDispatcher("/WEB-INF/view/cart/cartView.jsp")
               .forward(req, resp);
        }
    }
}