package kr.or.ddit.api.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.api.dao.ContentsDaoImpl;
import kr.or.ddit.api.dao.IContentsDao;

public class ContentsServiceImpl implements IContentsService{
	
	private static ContentsServiceImpl instance;
	IContentsDao contentDetailDao = ContentsDaoImpl.getInstance();
	
	private ContentsServiceImpl() {

	}

	public static ContentsServiceImpl getInstance() {
		if (instance == null)
			instance = new ContentsServiceImpl();
		return instance;
	}
	
	@Override
	public List<Map<String, Object>> contentsList() {
		return contentDetailDao.contentsList();
	}

	@Override
	public void contentsDefaultInput(Map<String, Object> param) {
		contentDetailDao.contentsDefaultInput(param);
	}

}
