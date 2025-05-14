package kr.or.ddit.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class SearchCriteriaVo {
    // 기본 검색 필드
    private String searchField;
    private String searchText;
    
    // 마일리지 필터
    private String mileageCondition;
    private Integer mileageValue;
    
    // 가입일자 필터
    private Date joinDateFrom;
    private Date joinDateTo;
    
    // 연령대 필터
    private Integer ageGroup;
    
}