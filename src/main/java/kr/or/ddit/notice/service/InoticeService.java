package kr.or.ddit.notice.service;

import java.util.List;

import kr.or.ddit.vo.NoticeVo;

public interface InoticeService {

	public List<NoticeVo> NoticeList(NoticeVo notice);
	public int noticeDelete(NoticeVo notice);
	public NoticeVo noticeDetail(NoticeVo notice);
	public int noticeUpdate(NoticeVo notice);
	public int noticeInsert(NoticeVo notice);
}
