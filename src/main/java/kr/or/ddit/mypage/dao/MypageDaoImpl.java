package kr.or.ddit.mypage.dao;

import java.util.List;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.LoveVo;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.PaymentVo;

public class MypageDaoImpl extends MybatisDao implements IMypageDao {

	private static MypageDaoImpl instance;

	private MypageDaoImpl() {

	}

	public static MypageDaoImpl getInstance() {
		if (instance == null) {
			instance = new MypageDaoImpl();
		}
		return instance;
	}

	@Override
	public MemberVo myPage(MemberVo member) {
		return selectOne("mypage.mypageMember", member);
	}

	@Override
	public List<BoardVo> mypagePlanList(int member) {
		return selectList("mypage.mypagePlanList", member);
	}

	@Override
	public List<BoardVo> mypageTalkList(int member) {
		return selectList("mypage.mypageTalkList", member);
	}

	@Override
	public List<BoardVo> mypageReviewList(int member) {
		return selectList("mypage.mypageReviewList", member);
	}

	@Override
	public List<LoveVo> mypageLoveList(int member) {
		return selectList("mypage.mypageLoveList", member);
	}



    @Override
    public List<PaymentVo> mypagePaymentList(int memNo) {
        return selectList("mypage.mypagePaymentList", memNo);
    }

}
