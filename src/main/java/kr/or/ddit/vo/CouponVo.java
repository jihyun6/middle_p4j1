package kr.or.ddit.vo;

import lombok.Data;

@Data
public class CouponVo {

	private int cupNo;
	private String cupName;
	private String cupDate;
	private int cupDiscount;
	private int memNo;
}
