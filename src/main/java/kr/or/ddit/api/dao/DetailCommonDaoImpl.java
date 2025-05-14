package kr.or.ddit.api.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.util.MybatisDao;

public class DetailCommonDaoImpl  extends MybatisDao implements IDetailCommonDao{
	
	private static DetailCommonDaoImpl instance;

	private DetailCommonDaoImpl() {

	}

	public static DetailCommonDaoImpl getInstance() {
		if (instance == null)
			instance = new DetailCommonDaoImpl();
		return instance;
	}
	
	@Override
	public List<Map<String, Object>> detailCommonList() {
		return selectList("detailCommon.detailCommonList");
	}

	@Override
	public void detailTourismInsert(Map<String, Object> param) {
		update("detailCommon.detailTourismInsert", param);
	}

	@Override
	public void detailExhibitionInsert(Map<String, Object> param) {
		update("detailCommon.detailExhibitionInsert", param);
	}

	@Override
	public void detailLeportsInsert(Map<String, Object> param) {
		update("detailCommon.detailLeportsInsert", param);
	}

	@Override
	public void detailEventInsert(Map<String, Object> param) {
		update("detailCommon.detailEventInsert", param);
	}

	@Override
	public void detailStayInsert(Map<String, Object> param) {
		update("detailCommon.detailStayInsert", param);
	}

	@Override
	public void detailRestaurantInsert(Map<String, Object> param) {
		update("detailCommon.detailRestaurantInsert", param);
	}
}
