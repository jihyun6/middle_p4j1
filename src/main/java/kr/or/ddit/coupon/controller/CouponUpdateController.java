package kr.or.ddit.coupon.controller;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.coupon.service.CouponServiceImpl;
import kr.or.ddit.coupon.service.ICouponService;
import kr.or.ddit.util.RequestToVoMapper;
import kr.or.ddit.vo.CouponVo;

@WebServlet("/couponUpdate.do")
public class CouponUpdateController extends HttpServlet{
	
	ICouponService couponService = CouponServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("couponUpdate post 실행");
		
		CouponVo coupon = RequestToVoMapper.mapRequestToVo(req, CouponVo.class);
		couponService.couponUpdate(coupon);
		
		JSONObject jObj = new JSONObject();
		jObj.put("url", "/couponList.do");
		resp.setContentType("application/x-json; charset=utf-8");
		resp.getWriter().print(jObj);
		
		
	}
}
