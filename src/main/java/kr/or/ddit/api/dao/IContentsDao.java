package kr.or.ddit.api.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.ContentsVo;

public interface IContentsDao {
	public List<Map<String,Object>> contentsList();
	public void contentsDefaultInput(Map<String, Object> param);
	public List<ContentsVo> selectMainContentsList();
}
