package kr.or.ddit.vo;

import java.sql.Date;
import lombok.Data;

@Data
public class PaymentVo {
    private int payNo;
    private Date payDate;
    private String payMethod;
    private String payStatus;
    private int payPrice;
    private String payDelyn;
    private int memNo;
    private String contentNo;     
    private int contentsTypeId; 
    private String contentTitle;  // con_title 컬럼 매핑
    
    private String contentsTypeName;
    private int totalSales;
    private int visitorCount;
    private String contentFirstImage;  // 컨텐츠 대표 이미지
    private int contentCount;  
}