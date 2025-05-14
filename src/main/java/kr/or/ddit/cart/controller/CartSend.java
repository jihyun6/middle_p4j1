package kr.or.ddit.cart.controller;

import java.io.IOException;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.util.RequestToVoMapper;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.PaymentVo;

@WebServlet("/cartsend.do")
public class CartSend extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		PaymentVo pvo = RequestToVoMapper.mapRequestToVo(req, PaymentVo.class);
		
		
		req.setAttribute("pvo", pvo);
		req.getRequestDispatcher("/WEB-INF/view/cart/cartsend.jsp");
	}
}
