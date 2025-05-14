package kr.or.ddit.planCont.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.PlanContVo;

public interface IplanContDao {
	public List<PlanContVo> planContList(PlanContVo plcoVo);
	
	
}
