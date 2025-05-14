package kr.or.ddit.custormer.service;

import java.util.List;

import kr.or.ddit.vo.CustormerVo;
import kr.or.ddit.vo.NoticeVo;

public interface ICustormerService {

	public List<CustormerVo> CustormerList(CustormerVo custormer);
	public CustormerVo CustormerDetail(CustormerVo custormer);
	public int CustormerDelete(CustormerVo custormer);
	public int CustormerUpdate(CustormerVo custormer);
	public int custormerInsert(CustormerVo custormer);
}
