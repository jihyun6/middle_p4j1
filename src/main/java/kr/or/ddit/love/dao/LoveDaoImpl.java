package kr.or.ddit.love.dao;


import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.LoveVo;

public class LoveDaoImpl extends MybatisDao implements ILoveDao {
	
	private static LoveDaoImpl instance;

	private LoveDaoImpl() {

	}

	public static LoveDaoImpl getInstance() {
		if (instance == null)
			instance = new LoveDaoImpl();
		return instance;
	}

	@Override
	public LoveVo loveUpdate(LoveVo love) {
		return selectOne("love.loveUpdate", love);
	}

	@Override
	public int loveCancel(LoveVo love) {
		return update("love.loveCancel", love);
	}


	@Override
	public int loveContentsCount(LoveVo love) {
		return selectOne("love.loveContentsCount", love);
	}

	@Override
	public int loveBoardCount(LoveVo love) {
		return selectOne("love.loveBoardCount", love);
	}

	@Override
	public int lovedByMemCon(LoveVo love) {
		return selectOne("love.lovedByMemCon", love);
	}

	@Override
	public int lovedByMemBoard(LoveVo love) {
		return selectOne("love.lovedByMemBoard", love);
	}

	@Override
	public LoveVo loveUpdateCon(LoveVo love) {
		return selectOne("love.loveUpdate", love);
	}



}
