package kr.or.ddit.api.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.util.MybatisDao;

public class ITrialDaoImpl  extends MybatisDao implements ITrialDao{
	
	private static ITrialDaoImpl instance;

	private ITrialDaoImpl() {

	}

	public static ITrialDaoImpl getInstance() {
		if (instance == null)
			instance = new ITrialDaoImpl();
		return instance;
	}
	
	@Override
	public List<Map<String, Object>> trialList() {
		return selectList("trial.trialList");
	}

	@Override
	public void trialInsert(Map<String, Object> param) {
		update("trial.trialInsert", param);
	}
}
