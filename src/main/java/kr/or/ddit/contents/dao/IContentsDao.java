package kr.or.ddit.contents.dao;

import java.util.List;

import kr.or.ddit.vo.ContentsVo;


public interface IContentsDao {
	public List<ContentsVo> contentsListType(ContentsVo contents);
	public List<ContentsVo> contentsListArea(ContentsVo contents);
	
	public ContentsVo contentsDetail(ContentsVo contents);
	
	public int contentsDelete(ContentsVo contents);
	public List<ContentsVo> selectMainContentsList();
	public List<ContentsVo> selectAllContentsList();
	
	public List<ContentsVo> getJTypeTopContents();
	public List<ContentsVo> getPTypeTopContents();
}
