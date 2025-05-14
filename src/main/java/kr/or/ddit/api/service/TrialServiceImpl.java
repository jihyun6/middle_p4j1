package kr.or.ddit.api.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.api.dao.ITrialDao;
import kr.or.ddit.api.dao.ITrialDaoImpl;

public class TrialServiceImpl implements ITrialService{
	
	private static TrialServiceImpl instance;
	ITrialDao trialDao = ITrialDaoImpl.getInstance();
	
	private TrialServiceImpl() {

	}

	public static TrialServiceImpl getInstance() {
		if (instance == null)
			instance = new TrialServiceImpl();
		return instance;
	}
	
	@Override
	public List<Map<String, Object>> trialList() {
		return trialDao.trialList();
	}

	@Override
	public void trialInsert(Map<String, Object> param) {
		trialDao.trialInsert(param);
	}
}
