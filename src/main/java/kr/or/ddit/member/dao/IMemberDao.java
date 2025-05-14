package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.SearchCriteriaVo;

public interface IMemberDao {
	
	//id중복검사 
	public String idCheck(String id);
	
	//닉네임중복검사
	public String nickCheck(String nick);
	
	//이메일 중복검사
	public String emailCheck(String email);
	
	//membr insert
	public int memberJoin(MemberVo vo);
	
	//member login
	public MemberVo login(MemberVo member);
	
	public List<MemberVo> memberList (MemberVo member);
	
	public MemberVo memberDetail (MemberVo member);
	
	public List<MemberVo> searchMembers(SearchCriteriaVo criteria);
	
	public int memberDelete(MemberVo member);
}