package kr.or.ddit.vo;

import lombok.Data;

@Data
public class ContentsVo {
	public ContentsVo() {}
	private String contentNo;
	private String conCreatedtime;
	private String conHomepage;
	private String conModifiedtime;
	private String conTel;
	private String conTitle;
	private String conFirstimage;
	private String conFirstimage2;
	private String conAddr1;
	private String conAddr2;
	private String conZipcode;
	private String conMapx;
	private String conMapy;
	private String conOverview;
	private int trialAreacode;
	private int contentsTypeId;
	private int memNo;
	private String contentsTypeName;
	private String conDelyn;
	private int conPrice;
	
	private String tourAccomcount;
	private String tourExpagerange;
	private String tourExpguide;
	private String tourInfocenter;
	private String tourOpendate;
	private String tourParking;
	private String tourRestdate;
	private String tourUseseason;
	private String tourUsetime;
	
	private String stayAccomcount;
	private String stayCheckintime;
	private String stayCheckouttime;
	private String stayChkcooking;
	private String stayInfocenter;
	private String stayParking;
	private String stayRoomcount;
	private String stayReservation;
	private String stayRoomtype;
	private String stayScale;
	private int stayPrice;
	
	private String lepoAccomcount;
	private String lepoExpagerange;
	private String lepoInfocenter;
	private String lepoOpenperiod;
	private String lepoParkingfee;
	private String lepoParking;
	private String lepoReservation;
	private String lepoRestdate;
	private String lepoScale;
	private String lepoPrice;
	private String lepoUsetime;
	
	private String eventAgelimit;
	private String eventBookingplace;
	private String eventDiscountinfo;
	private String eventEnddate;
	private String eventHomepage;
	private String eventPlace;
	private String eventStartdate;
	private String eventGrade;
	private String eventPlaceinfo;
	private String eventPlaytime;
	private String eventProgram;
	private String eventSpendtime;
	private String eventPrice;
	
	private String exhibAccomcount;
	private String exhibDiscount;
	private String exhibInfocenter;
	private String exhibParking;
	private String exhibParkingfee;
	private String exhibRestdate;
	private String exhibPrice;
	private String exhibUsetime;
	private String exhibScale;
	private String exhibSpendtime;
	
	private String resFirstmenu;
	private String resInfocenter;
	private String resOpentime;
	private String resPacking;
	private String resParking;
	private String resReservation;
	private String resScale;
	private String resRestdate;
	private String resTreatmenu;
	private int resPrice;
	
	private int loveNo;
	private int loveCount;
}
