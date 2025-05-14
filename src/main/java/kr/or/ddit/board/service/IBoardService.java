package kr.or.ddit.board.service;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import kr.or.ddit.vo.AttachFileVo;
import kr.or.ddit.vo.BoardVo;

public interface IBoardService {
	public List<BoardVo> boardList(BoardVo board);
	public BoardVo boardDetail(BoardVo board);
	public int boardUpdate(BoardVo board);
	public int boardDelete(BoardVo board);
	public int boardInsert(BoardVo board);
	
	
	public void fileWrite(HttpServletRequest req, int BoardNo);
	public int getBoardNo();
	public List<AttachFileVo> fileList(int boardNo);
	public AttachFileVo fileDetail(int fileNo);
	public List<BoardVo> planContList(BoardVo board);
}
