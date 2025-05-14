package kr.or.ddit.contents.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.ContentsVo;

public interface IContentsService {
	public List<ContentsVo> contentsListType(ContentsVo contents);
	public List<ContentsVo> contentsListArea(ContentsVo contents);
	
	public ContentsVo contentsDetail(ContentsVo contents);
	
	public int contentsDelete(ContentsVo contents);
	
	public  List<ContentsVo> getMainContentsList();
	public List<ContentsVo> selectAllContentsList();
	
	public List<ContentsVo> getJTypeTopContents();
	public List<ContentsVo> getPTypeTopContents();
}
