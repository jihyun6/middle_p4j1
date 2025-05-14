package kr.or.ddit.talk.dao;

import java.util.List;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.BoardVo;

public class TalkDaoImpl extends MybatisDao implements ITalkDao {

	private static TalkDaoImpl instance;

	private TalkDaoImpl() {

	}

	public static TalkDaoImpl getInstance() {
		if (instance == null) {
			instance = new TalkDaoImpl();
		}
		return instance;
	}
	
	@Override
	public List<BoardVo> talkList(BoardVo board) {
		return selectList("talk.talkList",board);
	}

	@Override
	public BoardVo talkDetail(BoardVo board) {
		return selectOne("talk.talkDetail", board);
	}

	@Override
	public int talkInsert(BoardVo board) {
		return update("talk.talkInsert",board);
	}

	@Override
	public int talkDelete(BoardVo board) {
		return update("talk.talkDelete", board);
	}

	@Override
	public int talkUpdate(BoardVo board) {
		return update("talk.talkUpdate", board);
	}

}
