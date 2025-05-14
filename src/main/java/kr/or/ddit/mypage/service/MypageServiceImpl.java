package kr.or.ddit.mypage.service;

import java.util.List;

import kr.or.ddit.mypage.dao.IMypageDao;
import kr.or.ddit.mypage.dao.MypageDaoImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.LoveVo;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.PaymentVo;

public class MypageServiceImpl implements IMypageService {

	private static MypageServiceImpl instance;

	private MypageServiceImpl() {

	}

	public static MypageServiceImpl getInstance() {
		if (instance == null) {
			instance = new MypageServiceImpl();
		}
		return instance;
	}

	IMypageDao mypageDao = MypageDaoImpl.getInstance();
	
	
	@Override
	public MemberVo myPage(MemberVo member) {
		return mypageDao.myPage(member);
	}

	@Override
	public List<BoardVo> mypagePlanList(int member) {
		return mypageDao.mypagePlanList(member);
	}

	@Override
	public List<BoardVo> mypageTalkList(int member) {
		return mypageDao.mypageTalkList(member);
	}

	@Override
	public List<BoardVo> mypageReviewList(int member) {
		return mypageDao.mypageReviewList(member);
	}

	@Override
	public List<LoveVo> mypageLoveList(int member) {
		return mypageDao.mypageLoveList(member);
	}

    @Override
    public List<PaymentVo> mypagePaymentList(int memNo) {
        return mypageDao.mypagePaymentList(memNo);
    }
}
