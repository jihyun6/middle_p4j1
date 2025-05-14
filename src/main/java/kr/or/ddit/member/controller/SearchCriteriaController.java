package kr.or.ddit.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.SearchCriteriaVo;

@WebServlet("/searchMember.do")
public class SearchCriteriaController extends HttpServlet {
    private IMemberService memberService = MemberServiceImpl.getInstance();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        SearchCriteriaVo criteria = new SearchCriteriaVo();
        
        // 기본 검색 필드
        criteria.setSearchField(req.getParameter("searchField"));
        criteria.setSearchText(req.getParameter("searchText"));
        
        // 마일리지 필터
        criteria.setMileageCondition(req.getParameter("mileageCondition"));
        String mileageValue = req.getParameter("mileageValue");
        if (mileageValue != null && !mileageValue.isEmpty()) {
            criteria.setMileageValue(Integer.parseInt(mileageValue));
        }
        
        // 가입일자 필터
        String joinDateFrom = req.getParameter("joinDateFrom");
        String joinDateTo = req.getParameter("joinDateTo");
        if (joinDateFrom != null && !joinDateFrom.isEmpty()) {
            criteria.setJoinDateFrom(Date.valueOf(joinDateFrom));
        }
        if (joinDateTo != null && !joinDateTo.isEmpty()) {
            criteria.setJoinDateTo(Date.valueOf(joinDateTo));
        }
        
        // 연령대 필터
        String ageGroup = req.getParameter("ageGroup");
        if (ageGroup != null && !ageGroup.isEmpty()) {
            criteria.setAgeGroup(Integer.parseInt(ageGroup));
        }
        
        
        // 검색 실행
        List<MemberVo> searchResults = memberService.searchMembers(criteria);
        
        // 결과 설정 및 페이지 이동
        req.setAttribute("memberList", searchResults);
        req.getRequestDispatcher("/WEB-INF/view/member/memberList.jsp").forward(req, resp);
    }
}
