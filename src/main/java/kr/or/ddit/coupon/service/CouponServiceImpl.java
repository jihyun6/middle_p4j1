package kr.or.ddit.coupon.service;

import java.util.List;

import kr.or.ddit.coupon.dao.CouponDaoImpl;
import kr.or.ddit.coupon.dao.ICouponDao;
import kr.or.ddit.vo.CouponVo;


public class CouponServiceImpl implements ICouponService {
	
	private static CouponServiceImpl instance;

	private CouponServiceImpl() {

	}

	public static CouponServiceImpl getInstance() {
		if (instance == null)
			instance = new CouponServiceImpl();
		return instance;
	}

	ICouponDao couponDao = CouponDaoImpl.getInstance();

	@Override
	public List<CouponVo> couponList(CouponVo coupon) {
		return couponDao.couponList(coupon);
	}

	@Override
	public int couponUpdate(CouponVo coupon) {
		return couponDao.couponUpdate(coupon);
	}

	@Override
	public int couponDelete(CouponVo coupon) {
		return couponDao.couponDelete(coupon);
	}

	@Override
	public int couponInsert(CouponVo coupon) {
		return couponDao.couponInsert(coupon);
	}

	@Override
	public CouponVo couponDetail(CouponVo coupon) {
		return couponDao.couponDetail(coupon);
	}

	@Override
	public List<CouponVo> mypageCouponList(int coupon) {
		return couponDao.mypageCouponList(coupon);
	}
	
	
	
	
}
