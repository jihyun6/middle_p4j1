package kr.or.ddit.coupon.dao;

import java.util.List;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.CouponVo;

public class CouponDaoImpl extends MybatisDao implements ICouponDao {
	
	private static CouponDaoImpl instance;

	private CouponDaoImpl() {

	}

	public static CouponDaoImpl getInstance() {
		if (instance == null)
			instance = new CouponDaoImpl();
		return instance;
	}

	@Override
	public List<CouponVo> couponList(CouponVo coupon) {
		return selectList("coupon.couponList", coupon);
	}


	@Override
	public int couponUpdate(CouponVo coupon) {
		return update("coupon.couponUpdate", coupon);
	}

	@Override
	public int couponDelete(CouponVo coupon) {
		return update("coupon.couponDelete", coupon);
	}

	@Override
	public int couponInsert(CouponVo coupon) {
		return update("coupon.couponInsert", coupon);
	}

	@Override
	public CouponVo couponDetail(CouponVo coupon) {
		return selectOne("coupon.couponDetail", coupon);
	}

	@Override
	public List<CouponVo> mypageCouponList(int coupon) {
		return selectList("coupon.mypageCouponList", coupon);
	}

	

	
}
