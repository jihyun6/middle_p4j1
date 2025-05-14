package kr.or.ddit.api.dao;

import java.util.List;
import java.util.Map;

public interface ITrialDao {
	public List<Map<String, Object>> trialList();
	public void trialInsert(Map<String, Object> param);
}
