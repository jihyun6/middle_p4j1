package kr.or.ddit.api.service;

import java.util.List;
import java.util.Map;

public interface IContentsService {
	public List<Map<String,Object>> contentsList();
	public void contentsDefaultInput(Map<String, Object> param);
}
