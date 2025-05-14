package kr.or.ddit.travelplan.dao;

import java.util.List;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.ContentsVo;

public class PlanDaoImpl extends MybatisDao implements IPlanDao {

	private static PlanDaoImpl instance;

	private PlanDaoImpl() {

	}

	public static PlanDaoImpl getInstance() {
		if (instance == null) {
			instance = new PlanDaoImpl();
		}
		return instance;
	}
	
	
	@Override
	public List<BoardVo> planList(BoardVo board) {
		return selectList("plan.planList", board);
	}

	@Override
	public int planInsert(BoardVo board) {
		return update("plan.planInsert", board);
	}

	@Override
	public BoardVo planView(BoardVo board) {
		return selectOne("plan.planView", board);
	}

	@Override
	public int planUpdate(BoardVo board) {
		return update("plan.planUpdate", board);
	}

	@Override
	public int planDelete(BoardVo board) {
		return update("plan.planDelete", board);
	}

	@Override
	public List<ContentsVo> contType(ContentsVo contents) {
		return selectList("plan.contentsType", contents);
	}

}
