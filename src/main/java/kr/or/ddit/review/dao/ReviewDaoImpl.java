package kr.or.ddit.review.dao;

import java.util.List;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.BoardVo;

public class ReviewDaoImpl extends MybatisDao implements IReviewDao {
    private static ReviewDaoImpl instance;
    
    private ReviewDaoImpl() {
        super(); // MybatisDao의 초기화 보장
    }
    
    public static ReviewDaoImpl getInstance() {
        if (instance == null) {
            synchronized (ReviewDaoImpl.class) {
                if (instance == null) {
                    instance = new ReviewDaoImpl();
                }
            }
        }
        return instance;
    }
    
    @Override
    public List<BoardVo> reviewList(BoardVo board) {
        try {
            return selectList("review.reviewList", board);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("리뷰 목록 조회 실패", e);
        }
    }


	@Override
	public BoardVo reviewDetail(BoardVo board) {
		// TODO Auto-generated method stub
		return selectOne("review.reviewDetail", board);
	}

	@Override
	public int reviewInsert(BoardVo board) {
		// TODO Auto-generated method stub
		return update("review.reviewInsert", board);
	}

	@Override
	public int reviewDelete(BoardVo board) {
		// TODO Auto-generated method stub
		return update("review.reviewDelete", board);
	}

	@Override
	public int reviewUpdate(BoardVo board) {
		// TODO Auto-generated method stub
		return update("review.reviewUpdate", board);
	}
	
}
