package kr.or.ddit.notice.dao;

import java.util.List;

import kr.or.ddit.vo.NoticeVo;

public interface InoticeDao {

	public List<NoticeVo> NoticeList(NoticeVo notice);
	public int noticeDelete(NoticeVo notice);
	public NoticeVo NoticeDetail(NoticeVo notice);
	public int noticeUpdate(NoticeVo notice);
	public int noticeInsert(NoticeVo notice);
	
}
