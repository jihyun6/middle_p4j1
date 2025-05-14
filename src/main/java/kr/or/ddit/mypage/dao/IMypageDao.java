package kr.or.ddit.mypage.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.LoveVo;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.PaymentVo;

public interface IMypageDao {

	public MemberVo myPage(MemberVo member);
	
	public List<BoardVo> mypagePlanList(int member);
	
	public List<BoardVo> mypageTalkList(int member);
	
	public List<BoardVo> mypageReviewList(int member);

	public List<LoveVo> mypageLoveList(int member);
	
	public List<PaymentVo> mypagePaymentList(int memNo);
}
