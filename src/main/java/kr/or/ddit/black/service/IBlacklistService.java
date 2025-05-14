package kr.or.ddit.black.service;

import java.util.List;

import kr.or.ddit.vo.BlacklistVo;

public interface IBlacklistService {

	public List<BlacklistVo> blacklistList(BlacklistVo blacklist);
	
	public BlacklistVo blacklistDetail(BlacklistVo blacklist);
	
	public int blacklistInsert (BlacklistVo blacklist);
	
	public int blacklistDelete (BlacklistVo blacklist);
	
	public int blacklistUpdate (BlacklistVo blacklist);

	public int checkDuplicate(int memNo);
	
}
