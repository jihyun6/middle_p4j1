package kr.or.ddit.cart.controller;

import java.io.IOException;

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

@WebServlet("/cartInsert.do")
public class CartInsertController extends HttpServlet{
	
	IcartService cartService= CartServiceImpl.getServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
System.out.println("cart post 실행");
		
		HttpSession session = req.getSession();
    	MemberVo member = (MemberVo) session.getAttribute("member");
    	req.setAttribute("member", member);
		
		String contentNo= req.getParameter("contentNo");
		String contentsTypeIdStr= req.getParameter("contentsTypeId");
		int contentsTypeId = Integer.parseInt(contentsTypeIdStr);
		String conPrice= req.getParameter("conPrice");
		int cartPrice= Integer.parseInt(conPrice);
		
		CartVo cart = new CartVo();
		cart.setContentNo(contentNo);
		cart.setContentsTypeId(contentsTypeId);
		cart.setCartPrice(cartPrice);
		cart.setMemNo(member.getMemNo());
		
		cartService.cartInsert(cart);
	}
}
