package kr.or.ddit.planCont.service;

import java.util.List;

import kr.or.ddit.planCont.dao.IplanContDao;
import kr.or.ddit.planCont.dao.PlanContDaoImpl;
import kr.or.ddit.talk.dao.ITalkDao;
import kr.or.ddit.talk.dao.TalkDaoImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.PlanContVo;

public class PlanContServiceImpl implements IplanContService {
	private static PlanContServiceImpl instance;

	private PlanContServiceImpl() {

	}

	public static PlanContServiceImpl getInstance() {
		if (instance == null) {
			instance = new PlanContServiceImpl();
		}
		return instance;
	}

	IplanContDao planContDao = PlanContDaoImpl.getInstance();
	
	@Override
	public List<PlanContVo> planContList(PlanContVo plcoVo) {
		return planContDao.planContList(plcoVo);
	}

}
