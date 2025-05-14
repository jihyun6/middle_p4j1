package kr.or.ddit.contents.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.ContentsVo;

public class ContentsDaoImpl extends MybatisDao implements IContentsDao {
	
	private static ContentsDaoImpl instance;

	private ContentsDaoImpl() {

	}

	public static ContentsDaoImpl getInstance() {
		if (instance == null)
			instance = new ContentsDaoImpl();
		return instance;
	}

	

	@Override
	public ContentsVo contentsDetail(ContentsVo contents) {
		return selectOne("contents.contentsDetail", contents);
	}


	@Override
	public int contentsDelete(ContentsVo contents) {
		return update("contents.contentsDelete", contents);
	}

	@Override
	public List<ContentsVo> contentsListType(ContentsVo contents) {
		return selectList("contents.contentsListType",contents);
	}

	@Override
	public List<ContentsVo> contentsListArea(ContentsVo contents) {
		return selectList("contents.contentsListArea",contents);
	}

	@Override
	public List<ContentsVo> selectMainContentsList() {
	    return selectList("contents.selectMainContentsList");
	}

	@Override
	public List<ContentsVo> selectAllContentsList() {
		// TODO Auto-generated method stub
		return selectList("contents.selectAllContentsList");
	}

	@Override
	public List<ContentsVo> getJTypeTopContents() {

		return selectList("contents.selectJTypeTopContents");
	}

	@Override
	public List<ContentsVo> getPTypeTopContents() {

        return selectList("contents.selectPTypeTopContents");
	}
	

}
