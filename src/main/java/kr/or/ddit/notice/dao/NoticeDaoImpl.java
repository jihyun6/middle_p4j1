package kr.or.ddit.notice.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.NoticeVo;

public class NoticeDaoImpl extends MybatisDao implements InoticeDao{

	private static NoticeDaoImpl instance;

	private NoticeDaoImpl() {
		
	}

	public static NoticeDaoImpl getInstance() {
		if (instance == null)
			instance = new NoticeDaoImpl();
		return instance;
	}

	@Override
	public List<NoticeVo> NoticeList(NoticeVo notice) {
		return selectList("notice.noticeList", notice);
	}

	@Override
	public int noticeDelete(NoticeVo notice) {
		return update("notice.noticeDelete", notice);
	}

	@Override
	public NoticeVo NoticeDetail(NoticeVo notice) {
		return selectOne("notice.noticeDetail", notice);
	}

	@Override
	public int noticeUpdate(NoticeVo notice) {
		return update("notice.noticeUpdate", notice);
	}

	@Override
	public int noticeInsert(NoticeVo notice) {
		
		SqlSession sql = MybatisUtil.getInstance();
		
		int res = 0;
		
		try {
			res = sql.insert("notice.noticeInsert", notice);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sql.commit();
			sql.close();
		}
		return res;
	}

	
	
}
