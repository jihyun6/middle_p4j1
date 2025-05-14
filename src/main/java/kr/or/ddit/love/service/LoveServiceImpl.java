package kr.or.ddit.love.service;

import kr.or.ddit.love.dao.ILoveDao;
import kr.or.ddit.love.dao.LoveDaoImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.LoveVo;

public class LoveServiceImpl implements ILoveService{
	
	private static LoveServiceImpl instance;

	private LoveServiceImpl() {

	}

	public static LoveServiceImpl getInstance() {
		if (instance == null) {
			instance = new LoveServiceImpl();
		}
		return instance;
	}

	ILoveDao loveDao = LoveDaoImpl.getInstance();

	@Override
	public LoveVo loveUpdate(LoveVo love) {
		return loveDao.loveUpdate(love);
	}

	@Override
	public int loveCancel(LoveVo love) {
		return loveDao.loveCancel(love);
	}

	@Override
	public int loveContentsCount(LoveVo love) {
		return loveDao.loveContentsCount(love);
	}

	@Override
	public int loveBoardCount(LoveVo love) {
		return loveDao.loveBoardCount(love);
	}

	@Override
	public int lovedByMemCon(LoveVo love) {
		return loveDao.lovedByMemCon(love);
	}

	@Override
	public int lovedByMemBoard(LoveVo love) {
		return loveDao.lovedByMemBoard(love);
	}

	@Override
	public LoveVo loveUpdateCon(LoveVo love) {
		return loveDao.loveUpdateCon(love);
	}

	

}
