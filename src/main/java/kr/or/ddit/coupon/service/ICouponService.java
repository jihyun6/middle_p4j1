package kr.or.ddit.coupon.service;

import java.util.List;

import kr.or.ddit.vo.CouponVo;

public interface ICouponService {
	
	public int couponInsert(CouponVo coupon);
	
	public List<CouponVo> couponList(CouponVo coupon);
	
	public CouponVo couponDetail(CouponVo coupon);
	
	public int couponUpdate(CouponVo coupon);
	
	public int couponDelete(CouponVo coupon);
	
	public List<CouponVo> mypageCouponList(int coupon);
	
}
