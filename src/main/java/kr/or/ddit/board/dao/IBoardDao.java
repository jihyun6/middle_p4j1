package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.vo.AttachFileVo;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.PlanContVo;

public interface IBoardDao {
	public List<BoardVo> boardList(BoardVo board);
	public BoardVo boardDetail(BoardVo board);
	public int boardUpdate(BoardVo board);
	public int boardDelete(BoardVo board);
	public int boardInsert(BoardVo board);
	
	public int getFileNo();
	public void uploadFile(AttachFileVo fileVo);
	public int getBoardNo();
	public List<AttachFileVo> fileList(int boardNo);
	public AttachFileVo fileDetail(int fileNo);
	public List<BoardVo> planContList(BoardVo board);
}