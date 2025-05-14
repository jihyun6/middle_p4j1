package kr.or.ddit.mypage.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.coupon.service.CouponServiceImpl;
import kr.or.ddit.coupon.service.ICouponService;
import kr.or.ddit.vo.CouponVo;





@WebServlet("/mypageCouponList.do")
public class MypageCouponController extends HttpServlet{
	
	ICouponService couponService = CouponServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("mypageCouponList.do 실행");
		
		CouponVo coupon = new CouponVo();
		List<CouponVo> couponList = couponService.couponList(coupon);
		req.setAttribute("couponList", couponList);
		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/coupon/mypageCouponList.jsp").forward(req, resp);
	
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memNoStr = req.getParameter("memNo");
		int memNo = Integer.parseInt(memNoStr);
		
		List<CouponVo> couponList = couponService.mypageCouponList(memNo);
		req.setAttribute("couponList", couponList);
		
		System.out.println("couponList : " + couponList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/coupon/mypageCouponList.jsp");
		dispatcher.forward(req, resp);
	
	
	}
	
}
