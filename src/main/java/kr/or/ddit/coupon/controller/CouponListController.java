package kr.or.ddit.coupon.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.coupon.service.CouponServiceImpl;
import kr.or.ddit.coupon.service.ICouponService;
import kr.or.ddit.vo.CouponVo;

@WebServlet("/couponList.do")
public class CouponListController extends HttpServlet{
	
	ICouponService couponService = CouponServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("couponlist 실행");
		
		CouponVo coupon = new CouponVo();
		List<CouponVo> couponList = couponService.couponList(coupon);
		req.setAttribute("couponList", couponList);
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/coupon/couponList.jsp").forward(req, resp);
	}
}
