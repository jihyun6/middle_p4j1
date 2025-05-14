package kr.or.ddit.api.service;

import java.util.List;
import java.util.Map;

public interface IDetailCommonService {
	public List<Map<String,Object>> detailCommonList();
	
	public void detailTourismInsert(Map<String, Object> param);
	public void detailExhibitionInsert(Map<String, Object> param);
	public void detailLeportsInsert(Map<String, Object> param);
	public void detailEventInsert(Map<String, Object> param);
	public void detailStayInsert(Map<String, Object> param);
	public void detailRestaurantInsert(Map<String, Object> param);
}
