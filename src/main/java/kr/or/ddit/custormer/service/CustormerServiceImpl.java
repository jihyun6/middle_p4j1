package kr.or.ddit.custormer.service;

import java.util.List;

import kr.or.ddit.custormer.dao.CustormerDaoImpl;
import kr.or.ddit.custormer.dao.ICustormerDao;
import kr.or.ddit.vo.CustormerVo;

public class CustormerServiceImpl implements ICustormerService{

	private static CustormerServiceImpl instance;

	private CustormerServiceImpl() {
		
	}

	public static CustormerServiceImpl getInstance() {
		if (instance == null)
			instance = new CustormerServiceImpl();
		return instance;
	}
	
	ICustormerDao custormerDao = CustormerDaoImpl.getInstance();
	
	@Override
	public List<CustormerVo> CustormerList(CustormerVo custormer) {
		return custormerDao.CustormerList(custormer);
	}

	@Override
	public CustormerVo CustormerDetail(CustormerVo custormer) {
		return custormerDao.CustormerDetail(custormer);
	}

	@Override
	public int CustormerDelete(CustormerVo custormer) {
		return custormerDao.CustormerDelete(custormer);
	}

	@Override
	public int CustormerUpdate(CustormerVo custormer) {
		// TODO Auto-generated method stub
		return custormerDao.CustormerUpdate(custormer);
	}

	@Override
	public int custormerInsert(CustormerVo custormer) {
		// TODO Auto-generated method stub
		return custormerDao.custormerInsert(custormer);
	}

}
