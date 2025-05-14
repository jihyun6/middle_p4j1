package kr.or.ddit.vo;

import lombok.Data;

@Data
public class NoticeVo {

	private int noticeNo;
	private String noticeName;
	private String noticeContent;
	private String noticeDate;
	private String noticeUpdate;
	private String noticeDelyn;
	private int memNo;
	private int codeNumber;
}
