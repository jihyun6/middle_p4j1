package kr.or.ddit.talk.service;

import java.util.List;

import kr.or.ddit.talk.dao.ITalkDao;
import kr.or.ddit.talk.dao.TalkDaoImpl;
import kr.or.ddit.vo.BoardVo;

public class TalkServiceImpl implements ITalkService {

	private static TalkServiceImpl instance;

	private TalkServiceImpl() {

	}

	public static TalkServiceImpl getInstance() {
		if (instance == null)
			instance = new TalkServiceImpl();
		return instance;
	}

	ITalkDao talkDao = TalkDaoImpl.getInstance();
	
	@Override
	public List<BoardVo> talkList(BoardVo board) {
		return talkDao.talkList(board);
	}

	@Override
	public BoardVo talkDetail(BoardVo board) {
		return talkDao.talkDetail(board);
	}

	@Override
	public int talkInsert(BoardVo board) {
		return talkDao.talkInsert(board);
	}

	@Override
	public int talkDelete(BoardVo board) {
		return talkDao.talkDelete(board);
	}

	@Override
	public int talkUpdate(BoardVo board) {
		return talkDao.talkUpdate(board);
	}

}
