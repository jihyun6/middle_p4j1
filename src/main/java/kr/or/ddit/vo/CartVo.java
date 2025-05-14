package kr.or.ddit.vo;

import lombok.Data;

@Data
public class CartVo {
    private int cartNo;
    private int cartHeadcount;
    private int cartRank;
    private int cartPrice;
    private String contentNo;
    private int memNo;
    private int contentsTypeId;
    private String cartDelyn;
    
    // 추가: conTitle 필드
    private String conTitle;
}
