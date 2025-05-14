package kr.or.ddit.api.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.ContentsVo;

public class ContentsDaoImpl extends MybatisDao implements IContentsDao{
	
	private static ContentsDaoImpl instance;

	private ContentsDaoImpl() {

	}

	public static ContentsDaoImpl getInstance() {
		if (instance == null)
			instance = new ContentsDaoImpl();
		return instance;
	}
	
	@Override
	public List<Map<String, Object>> contentsList() {
		return selectList("content.contentsList");
	}

	@Override
	public void contentsDefaultInput(Map<String, Object> param) {
		 update("content.contentsInput", param);
	}


	@Override
	public List<ContentsVo> selectMainContentsList() {
	    ContentsVo contents = new ContentsVo();
	    // 모든 타입의 컨텐츠를 가져오거나, 특정 타입을 지정할 수 있습니다
	    return selectList("contents.contentsListType", contents);
	}
}
