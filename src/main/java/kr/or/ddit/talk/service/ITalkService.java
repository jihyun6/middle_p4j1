package kr.or.ddit.talk.service;

import java.util.List;

import kr.or.ddit.vo.BoardVo;

public interface ITalkService {
	public List<BoardVo> talkList(BoardVo board);
	
	public BoardVo talkDetail(BoardVo board);
	
	public int talkInsert (BoardVo board);
	
	public int talkDelete (BoardVo board);
	
	public int talkUpdate (BoardVo board);
}
