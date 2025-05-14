package kr.or.ddit.cart.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.cart.service.CartServiceImpl;
import kr.or.ddit.cart.service.IcartService;
import kr.or.ddit.vo.CartVo;

@WebServlet("/cartDelete.do")
public class CartDeleteController extends HttpServlet{
	
	IcartService cartService = CartServiceImpl.getServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("cartNo값이 여러개가 넘어온다.");
		
		String cartNoStr = req.getParameter("cartNo");
		int cartNo = Integer.parseInt(cartNoStr);

		CartVo cart = new CartVo();
		cart.setCartNo(cartNo);
		
		cartService.cartDelete(cart);
		
//		resp.sendRedirect(req.getContextPath()+"/cartList.do");
	}
}
