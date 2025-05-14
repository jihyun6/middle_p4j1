package kr.or.ddit.travelplan.service;

import java.util.List;

import kr.or.ddit.travelplan.dao.IPlanDao;
import kr.or.ddit.travelplan.dao.PlanDaoImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.ContentsVo;

public class PlanServiceImpl implements IPlanService {

	private static PlanServiceImpl instance;

	private PlanServiceImpl() {

	}

	public static PlanServiceImpl getInstance() {
		if (instance == null) {
			instance = new PlanServiceImpl();
		}
		return instance;
	}

	IPlanDao planDao = PlanDaoImpl.getInstance();
	
	
	@Override
	public List<BoardVo> planList(BoardVo board) {
		return planDao.planList(board);
	}

	@Override
	public int planInsert(BoardVo board) {
		return planDao.planInsert(board);
	}

	@Override
	public BoardVo planView(BoardVo board) {
		return planDao.planView(board);
	}

	@Override
	public int planUpdate(BoardVo board) {
		return planDao.planUpdate(board);
	}

	@Override
	public int planDelete(BoardVo board) {
		return planDao.planDelete(board);
	}

	@Override
	public List<ContentsVo> contType(ContentsVo contents) {
		return planDao.contType(contents);
	}

}
