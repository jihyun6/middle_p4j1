package kr.or.ddit.api.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.api.dao.DetailCommonDaoImpl;
import kr.or.ddit.api.dao.IDetailCommonDao;

public class DetailDommonServiceImpl implements IDetailCommonService{
	
	IDetailCommonDao detailCommonDao = DetailCommonDaoImpl.getInstance();
	
	private static DetailDommonServiceImpl instance;

	private DetailDommonServiceImpl() {

	}

	public static DetailDommonServiceImpl getInstance() {
		if (instance == null)
			instance = new DetailDommonServiceImpl();
		return instance;
	}
			
			
	@Override
	public List<Map<String, Object>> detailCommonList() {
		return detailCommonDao.detailCommonList();
	}

	@Override
	public void detailTourismInsert(Map<String, Object> param) {
		detailCommonDao.detailTourismInsert(param);
		
	}

	@Override
	public void detailExhibitionInsert(Map<String, Object> param) {
		detailCommonDao.detailExhibitionInsert(param);
	}

	@Override
	public void detailLeportsInsert(Map<String, Object> param) {
		detailCommonDao.detailLeportsInsert(param);
	}

	@Override
	public void detailEventInsert(Map<String, Object> param) {
		detailCommonDao.detailEventInsert(param);
	}

	@Override
	public void detailStayInsert(Map<String, Object> param) {
		detailCommonDao.detailStayInsert(param);
	}

	@Override
	public void detailRestaurantInsert(Map<String, Object> param) {
		detailCommonDao.detailRestaurantInsert(param);
	}
}
