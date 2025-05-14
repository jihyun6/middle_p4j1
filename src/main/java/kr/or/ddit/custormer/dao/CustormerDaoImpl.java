package kr.or.ddit.custormer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.notice.dao.InoticeDao;
import kr.or.ddit.notice.dao.NoticeDaoImpl;
import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.CustormerVo;

public class CustormerDaoImpl extends MybatisDao implements ICustormerDao{

	private static CustormerDaoImpl instance;

	private CustormerDaoImpl() {
		
	}

	public static CustormerDaoImpl getInstance() {
		if (instance == null)
			instance = new CustormerDaoImpl();
		return instance;
	}
	
	

	@Override
	public List<CustormerVo> CustormerList(CustormerVo custormer) {
		return selectList("custormer.custormerList", custormer);
	}

	@Override
	public CustormerVo CustormerDetail(CustormerVo custormer) {
		return selectOne("custormer.custormerDetail", custormer);
	}

	@Override
	public int CustormerDelete(CustormerVo custormer) {
		return update("custormer.custormerDelete", custormer);
	}

	@Override
	public int CustormerUpdate(CustormerVo custormer) {
		return update("custormer.custormerUpdate", custormer);
	}

	@Override
	public int custormerInsert(CustormerVo custormer) {
	SqlSession sql = MybatisUtil.getInstance();
		
		int res = 0;
		
		try {
			res = sql.insert("custormer.custormerInsert", custormer);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sql.commit();
			sql.close();
		}
		return res;
	}
	
}
