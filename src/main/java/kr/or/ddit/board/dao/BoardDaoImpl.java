package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.AttachFileVo;
import kr.or.ddit.vo.BoardVo;

public class BoardDaoImpl extends MybatisDao implements IBoardDao{
	
	private static BoardDaoImpl instance;

	private BoardDaoImpl() {
		
	}

	public static BoardDaoImpl getInstance() {
		if (instance == null)
			instance = new BoardDaoImpl();
		return instance;
	}
	
	@Override
	public List<BoardVo> boardList(BoardVo board) {
		return selectList("board.boardList", board);
	}

	@Override
	public BoardVo boardDetail(BoardVo board) {
		return selectOne("board.boardDetail", board);
	}

	@Override
	public int boardUpdate(BoardVo board) {
		return update("board.boardUpdate", board);
	}

	@Override
	public int boardDelete(BoardVo board) {
		return update("board.boardDelete", board);
	}

	@Override
	public int boardInsert(BoardVo board) {
		return update("board.boardInsert", board);
	}

	@Override
	public int getFileNo() {
		return selectOne("file.getFileNo");
	}

	@Override
	public void uploadFile(AttachFileVo fileVo) {
		update("file.uploadFile", fileVo);
	}

	@Override
	public int getBoardNo() {
		return selectOne("board.getBoardNo");
	}

	@Override
	public List<AttachFileVo> fileList(int boardNo) {
		return selectList("file.fileList", boardNo);
	}

	@Override
	public AttachFileVo fileDetail(int fbNo) {
		return selectOne("file.fileDetail", fbNo);
	}

	@Override
	public List<BoardVo> planContList(BoardVo board) {
		// TODO Auto-generated method stub
		return selectList("board.planContList",board);
	}
	
}