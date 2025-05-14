package kr.or.ddit.coupon.controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.coupon.service.CouponServiceImpl;
import kr.or.ddit.coupon.service.ICouponService;
import kr.or.ddit.util.RequestToVoMapper;
import kr.or.ddit.vo.CouponVo;

@WebServlet("/couponInsert.do")
public class CouponInsertController extends HttpServlet{
	ICouponService couponService = CouponServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("couponlist get 실행");
		
//		CouponVo coupon = new CouponVo();
//		int couponInsert = couponService.couponInsert(coupon);
//		req.setAttribute("couponInsert", couponInsert);
//		
		ServletContext ctx = req.getServletContext();
		ctx.getRequestDispatcher("/WEB-INF/view/coupon/couponInsert.jsp").forward(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("couponlist post 실행");
		CouponVo coupon = RequestToVoMapper.mapRequestToVo(req, CouponVo.class);
		couponService.couponInsert(coupon);
		JSONObject jObj = new JSONObject();
		jObj.put("url", "/couponList.do");
		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jObj);
	}
}
