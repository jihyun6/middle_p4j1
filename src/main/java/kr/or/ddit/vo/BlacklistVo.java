package kr.or.ddit.vo;

import lombok.Data;

@Data
public class BlacklistVo {

	private int blackNo;
	private int memNo;
	private String blackReason;
	private String blackStartDate;
	private String blackEndDate;
	private String memName;
}
