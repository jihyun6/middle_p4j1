package kr.or.ddit.planCont.service;

import java.util.List;

import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.PlanContVo;

public interface IplanContService {
	public List<PlanContVo> planContList(PlanContVo plcoVo);
	
}
