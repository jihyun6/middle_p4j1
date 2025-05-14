package kr.or.ddit.api.service;

import java.util.List;
import java.util.Map;

public interface ITrialService {
	public List<Map<String, Object>> trialList();
	public void trialInsert(Map<String, Object> param);
}