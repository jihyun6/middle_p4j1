package kr.or.ddit.contents.service;

import java.util.List;

import kr.or.ddit.contents.dao.ContentsDaoImpl;
import kr.or.ddit.contents.dao.IContentsDao;
import kr.or.ddit.vo.ContentsVo;

public class ContentsServiceImpl implements IContentsService {
	
	private static ContentsServiceImpl instance;

	private ContentsServiceImpl() {

	}

	public static ContentsServiceImpl getInstance() {
		if (instance == null)
			instance = new ContentsServiceImpl();
		return instance;
	}

	IContentsDao contentsDao = ContentsDaoImpl.getInstance();

	@Override
	public ContentsVo contentsDetail(ContentsVo contents) {
		return contentsDao.contentsDetail(contents);
	}



	@Override
	public int contentsDelete(ContentsVo contents) {
		return contentsDao.contentsDelete(contents);
	}

	@Override
	public List<ContentsVo> contentsListType(ContentsVo contents) {
		return contentsDao.contentsListType(contents);
	}

	@Override
	public List<ContentsVo> contentsListArea(ContentsVo contents) {
		return contentsDao.contentsListArea(contents);
	}
	
    @Override
    public List<ContentsVo> getMainContentsList() {
        return contentsDao.selectMainContentsList();
    }

    @Override
    public List<ContentsVo> selectAllContentsList() {
        return contentsDao.selectMainContentsList();  // 기존의 잘 작동하던 메서드 활용
    }

	@Override
	public List<ContentsVo> getJTypeTopContents() {
		// TODO Auto-generated method stub
		 return contentsDao.getJTypeTopContents();
	}

	@Override
	public List<ContentsVo> getPTypeTopContents() {
		// TODO Auto-generated method stub
		 return contentsDao.getJTypeTopContents();
	}
	


}
