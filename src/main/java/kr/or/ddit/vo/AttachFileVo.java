 package kr.or.ddit.vo;

import lombok.Data;

@Data
public class AttachFileVo {
	private int fbNo;
	private String fbOrgName;
	private String fbSaveName;
	private String fbExe;
	private long fbSize;
	private String fbDate;
	private String fbDelyn;
	private String fbPath;
	private int boardNo;
}