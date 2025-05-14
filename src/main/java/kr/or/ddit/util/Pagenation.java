package kr.or.ddit.util;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Pagenation<T> {
	/**
	 * Required Fields
	 * - 이 필드들은 페이징 계산을 위해 반드시 입력되어야 하는 필드 값들이다.  
	 * 
	 * pageNo : 현재 페이지 번호
	 * recordCountPerPage : 한 페이지당 게시되는 게시물 건 수
	 * pageSize : 페이지 리스트에 게시되는 페이지 건수,
	 * totalRecordCount : 전체 게시물 건 수. 
	 * lastPageNo : 페이지 마지막 번호
	 */

	private int pageNo;
	private int recordCountPerPage = 15;
	private int pageSize = 10;
	private int totalRecordCount;
	private int lastPageNo;

	Map<String, String> pageMap = new HashMap<String, String>(); // pagination 구성 할때, page 번호의 url에 ?search=test와 같이 구성하기 위한 파라미터들
	
	private T searchVo; // 검색 조건 vo로 처리하기 위해서 추가

	private int totalPageCount;
	
	public Pagenation() {
		this.pageNo = 1;
	}

	public Pagenation(int currentPage) {
		if (currentPage == 0) {
			currentPage = 1;
		}

		this.pageNo = currentPage;
	}

	public int getTotalPageCount() {
		totalPageCount = ((getTotalRecordCount() - 1) / getRecordCountPerPage()) + 1;
		return totalPageCount;
	}

	public int getLastPageNo() {
		lastPageNo = (totalRecordCount / recordCountPerPage) + 1;
		return lastPageNo;
	}

}
