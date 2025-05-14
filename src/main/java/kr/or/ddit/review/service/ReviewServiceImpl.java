package kr.or.ddit.review.service;

import java.util.List;

import kr.or.ddit.review.dao.IReviewDao;
import kr.or.ddit.review.dao.ReviewDaoImpl;
import kr.or.ddit.vo.BoardVo;

public class ReviewServiceImpl implements IReviewService{

	private static ReviewServiceImpl instance;

	private ReviewServiceImpl() {

	}

	public static ReviewServiceImpl getInstance() {
		if (instance == null)
			instance = new ReviewServiceImpl();
		return instance;
	}

	IReviewDao reviewDao = ReviewDaoImpl.getInstance();
	
	
	@Override
	public List<BoardVo> reviewList(BoardVo board) {
		// TODO Auto-generated method stub
		return reviewDao.reviewList(board);
	}

	@Override
	public BoardVo reviewDetail(BoardVo board) {
		// TODO Auto-generated method stub
		return reviewDao.reviewDetail(board);
	}

	@Override
	public int reviewInsert(BoardVo board) {
		// TODO Auto-generated method stub
		return reviewDao.reviewInsert(board);
	}

	@Override
	public int reviewDelete(BoardVo board) {
		// TODO Auto-generated method stub
		return reviewDao.reviewDelete(board);
	}

	@Override
	public int reviewUpdate(BoardVo board) {
		// TODO Auto-generated method stub
		return reviewDao.reviewUpdate(board);
	}

	
}
