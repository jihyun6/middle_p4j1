package kr.or.ddit.planCont.dao;

import java.util.List;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.PlanContVo;

public class PlanContDaoImpl extends MybatisDao implements IplanContDao {

	private static PlanContDaoImpl instance;

	private PlanContDaoImpl() {

	}

	public static PlanContDaoImpl getInstance() {
		if (instance == null) {
			instance = new PlanContDaoImpl();
		}
		return instance;
	}

	@Override
	public List<PlanContVo> planContList(PlanContVo plcoVo) {
		return selectOne("planCont.planContList", plcoVo);
	}
	
}
