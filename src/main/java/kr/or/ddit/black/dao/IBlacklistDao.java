package kr.or.ddit.black.dao;

import java.util.List;

import kr.or.ddit.vo.BlacklistVo;

public interface IBlacklistDao {

	public List<BlacklistVo> blacklistList(BlacklistVo blacklist);
	
	public BlacklistVo blacklistDetail(BlacklistVo blacklist);
	
	public int blacklistInsert (BlacklistVo blacklist);
	
	public int blacklistDelete (BlacklistVo blacklist);
	
	public int blacklistUpdate (BlacklistVo blacklist);
	
	public int checkDuplicate(int memNo);

}
