package kr.or.ddit.vo;

import lombok.Data;

@Data
public class ReplyVo {

	private int replyNo;
	private String replyContent;
	private String replyDate;
	private String replyDelyn;
	private int preplyNo;
	private int boardNo;
	public int memNo;
	public String memName;
	
		
	
}
