package kr.or.ddit.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * NoArgsConstructor: 기본생성자 추가
 * 
 */

@Data
@NoArgsConstructor
public class BoardVo {
	private int boardNo; //게시판 번호
	private String boardName; //제목
	private String boardContent; //내용
	private String boardDate; //작성일자
	private String boardUpdate; //수정일자
	private String boardDelyn; //삭제여부
	private int boardClick; //조회수
	private String boardTag; //태그
	private int boardStar; //별점
	private String boardWeather; //오늘의날씨
	private int trialAreacode; //지역코드
	private int contentsTypeId; //컨텐츠타입ID
	private int memNo; //회원번호 
	private int codeNumber; //코드번호
	private String travelStart; //여행시작일
	private String travelEnd;	//여행종료일
	
	private String memName; //회원명
	private String contentsTypeName; //컨텐츠명
	private String codeName; //게시판 유형
	private String contentNo; //컨텐츠번호 
	private String conTitle; //컨텐츠이름
	
	private int loveNo;
	private int loveCount;
	
    public BoardVo(int boardNo) {  // 게시글 번호로 객체 생성하는 생성자 추가
        this.boardNo = boardNo;
    }
    private String searchType;    // 검색 유형
    private String searchWord;    // 검색어
    private String sortOrder;     // 정렬 옵션
}