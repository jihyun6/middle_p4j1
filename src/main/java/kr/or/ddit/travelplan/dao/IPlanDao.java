package kr.or.ddit.travelplan.dao;

import java.net.ContentHandler;
import java.util.List;

import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.ContentsVo;

public interface IPlanDao {
	
	public List<BoardVo> planList(BoardVo board);
	
	
	public int planInsert(BoardVo board);
	
	public BoardVo planView(BoardVo board);
	
	public int planUpdate(BoardVo board);
	
	public int planDelete(BoardVo board);
	
	public List<ContentsVo> contType (ContentsVo contents);
	
}
