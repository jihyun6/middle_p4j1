package kr.or.ddit.black.service;

import java.util.List;

import jakarta.servlet.http.HttpServlet;
import kr.or.ddit.black.dao.BlacklistDaoImpl;
import kr.or.ddit.black.dao.IBlacklistDao;
import kr.or.ddit.vo.BlacklistVo;

public class BlacklistServiceImpl implements IBlacklistService{

	private static BlacklistServiceImpl instance;

	private BlacklistServiceImpl() {

	}

	public static BlacklistServiceImpl getInstance() {
		if (instance == null)
			instance = new BlacklistServiceImpl();
		return instance;
	}

		IBlacklistDao blacklistDao = BlacklistDaoImpl.getInstance();
	
	@Override
	public List<BlacklistVo> blacklistList(BlacklistVo blacklist) {
		// TODO Auto-generated method stub
		return blacklistDao.blacklistList(blacklist);
	}

	@Override
	public BlacklistVo blacklistDetail(BlacklistVo blacklist) {
		// TODO Auto-generated method stub
		return blacklistDao.blacklistDetail(blacklist);
	}

	@Override
	public int blacklistInsert(BlacklistVo blacklist) {
		// TODO Auto-generated method stub
		return blacklistDao.blacklistInsert(blacklist);
	}

	@Override
	public int blacklistDelete(BlacklistVo blacklist) {
		// TODO Auto-generated method stub
		return blacklistDao.blacklistDelete(blacklist);
	}

	@Override
	public int blacklistUpdate(BlacklistVo blacklist) {
		// TODO Auto-generated method stub
		return blacklistDao.blacklistUpdate(blacklist);
	}

	@Override
	public int checkDuplicate(int memNo) {
		// TODO Auto-generated method stub
		return 0;
	}


}
