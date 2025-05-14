package kr.or.ddit.vo;

import lombok.Data;

@Data
public class LoveVo {
	
	private int loveNo;
	private String contentNo;
	private String replyNo;
	private int boardNo;
	private int memNo;
	private int lovedByMem;
	private int loveCount;
	private String conFirstimage2;
	private String conTitle;
}
