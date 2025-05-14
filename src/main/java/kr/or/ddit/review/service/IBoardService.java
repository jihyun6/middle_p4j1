package kr.or.ddit.review.service;

import java.util.List;

import kr.or.ddit.vo.BoardVo;

public interface IBoardService {

	public List<BoardVo> reviewList(BoardVo board);
	
	public BoardVo reviewDetail(BoardVo board);
	
	public int reviewInsert (BoardVo board);
	
	public int reviewDelete (BoardVo board);
	
	public int reviewUpdate (BoardVo board);
}
