package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.SearchCriteriaVo;

public interface IMemberService {

	//id 중복검사
	public String idCheck(String id);
	
	//닉네임 중복검사
	public String nickCheck(String nick);
	
	//이메일 중복검사
	public String emailCheck(String email);
	
	//member insert
	public int memberjoin(MemberVo vo);

	public MemberVo login(MemberVo member);

	public List<MemberVo> memberList (MemberVo member);
	
	public MemberVo memberDetail (MemberVo member);
	
	public List<MemberVo> searchMembers(SearchCriteriaVo criteria);
	
	public int memberDelete(MemberVo member);
}
