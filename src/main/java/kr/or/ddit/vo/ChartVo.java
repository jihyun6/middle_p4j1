package kr.or.ddit.vo;

import lombok.Data;

@Data
public class ChartVo {

	private int payNo;
	private String payDate;
	private String payMethod;
	private String payStatus;
	private int payPrice;
	private String payDelyn;
	private int memNo;
	private int contentsTypeId;
	private String contentNo;


	
}
