package kr.or.ddit.notice.service;

import java.util.List;

import kr.or.ddit.notice.dao.InoticeDao;
import kr.or.ddit.notice.dao.NoticeDaoImpl;
import kr.or.ddit.vo.NoticeVo;

public class NoticeServiceImpl implements InoticeService{

private static NoticeServiceImpl instance;
	
	private NoticeServiceImpl() {

	}

	public static NoticeServiceImpl getInstance() {
		if (instance == null)
			instance = new NoticeServiceImpl();
		return instance;
	}
	
	InoticeDao noticeDao = NoticeDaoImpl.getInstance();
	
	@Override
	public List<NoticeVo> NoticeList(NoticeVo notice) {
		return noticeDao.NoticeList(notice);
	}

	@Override
	public int noticeDelete(NoticeVo notice) {
		return noticeDao.noticeDelete(notice);
	}

	@Override
	public NoticeVo noticeDetail(NoticeVo notice) {
		return noticeDao.NoticeDetail(notice);
	}

	@Override
	public int noticeUpdate(NoticeVo notice) {
		return noticeDao.noticeUpdate(notice);
	}

	@Override
	public int noticeInsert(NoticeVo notice) {
		// TODO Auto-generated method stub
		return noticeDao.noticeInsert(notice);
	}


}
