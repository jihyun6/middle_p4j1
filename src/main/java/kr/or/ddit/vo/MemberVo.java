package kr.or.ddit.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberVo {
	private int memNo;
	private String memId;
	private String memPwd;
	private String memName;
	private String memAlias;
	private String memMbti;
	private String memReg;
	private String memReg2;
	private int memAge;
	private String memGender;
	private String memTel;
	private String memEmail;
	private String memDelyn;
	private Date memJoinDate;
	private int memMileage;
	private int memAuth;
	private String memProUrl;
}