package kr.or.ddit.board.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.util.UploadUtil;
import kr.or.ddit.vo.AttachFileVo;
import kr.or.ddit.vo.BoardVo;

public class BoardServiceImpl implements IBoardService{
	private static BoardServiceImpl instance;
	
	private BoardServiceImpl() {

	}

	public static BoardServiceImpl getInstance() {
		if (instance == null)
			instance = new BoardServiceImpl();
		return instance;
	}

	//BoardDao
	IBoardDao boardDao = BoardDaoImpl.getInstance();
	
	@Override
	public List<BoardVo> boardList(BoardVo board) {
		return boardDao.boardList(board);
	}

	@Override
	public BoardVo boardDetail(BoardVo board) {
		return boardDao.boardDetail(board);
	}

	@Override
	public int boardUpdate(BoardVo board) {
		return boardDao.boardUpdate(board);
	}

	@Override
	public int boardDelete(BoardVo board) {
		return boardDao.boardDelete(board);
	}

	@Override
	public int boardInsert(BoardVo board) {
		return boardDao.boardInsert(board);
	}

	@Override
	public void fileWrite(HttpServletRequest req, int boardNo) {
		try {
			for(Part part: req.getParts()) {
				String fileName = UploadUtil.getFileName(part);
				
				//file name이 있는 경우
				if(fileName != null) {
					AttachFileVo fileVo = new AttachFileVo();
					
					//file 확장자 구하기
					String[] token = fileName.split("\\.");
					String ext = token[token.length-1];
					
					// 파일 용량
					long size = part.getSize();
					
					//파일 저장이름 생성
					String saveName = UUID.randomUUID().toString().replace("-","");
					
					//파일 저장번호 가져오기
					int file_no = boardDao.getFileNo();
					
					//boardNo
					
					fileVo.setFbNo(file_no);
					fileVo.setFbExe(ext);
					fileVo.setFbOrgName(fileName);
					fileVo.setFbSaveName(saveName);
					fileVo.setFbPath(UploadUtil.rootPath);
					fileVo.setFbSize(size);
					fileVo.setBoardNo(boardNo);;
					
					boardDao.uploadFile(fileVo);
					
					//file 저장
					part.write("D:/uploadFolder/"+saveName);
				}
			}
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getBoardNo() {
		return boardDao.getBoardNo();
	}

	@Override
	public List<AttachFileVo> fileList(int boardNo) {
		return boardDao.fileList(boardNo);
	}

	@Override
	public AttachFileVo fileDetail(int fbNo) {
		return boardDao.fileDetail(fbNo);
	}

	@Override
	public List<BoardVo> planContList(BoardVo board) {
		// TODO Auto-generated method stub
		return boardDao.planContList(board);
	}
}