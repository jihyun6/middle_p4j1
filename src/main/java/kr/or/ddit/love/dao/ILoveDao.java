package kr.or.ddit.love.dao;

import kr.or.ddit.vo.LoveVo;

public interface ILoveDao {
	LoveVo loveUpdate (LoveVo love);       // 좋아요 추가
	LoveVo loveUpdateCon(LoveVo love);
	int loveCancel(LoveVo love);   // 좋아요 삭제
	
	int lovedByMemCon(LoveVo love); // 좋아요 여부 확인
	int lovedByMemBoard(LoveVo love); // 좋아요 여부 확인
	
	int loveContentsCount(LoveVo love); //좋아요 수 확인
	int loveBoardCount(LoveVo love); //좋아요 수 확인
}
