package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;

@Data
public class ReportVo {
    private int reNo;
    private int reReason;
    private String reComment;
    private String reStatus;
    private String reRecieptDate;
    private String reClearDate;
    private int memNo;
    private int blackNo;
    private String memName;
    private int reCount;
    private int boardNo;
    private List<String> groupedComments; // 추가: 그룹화된 코멘트를 저장할 리스트
 
}
