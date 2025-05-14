package kr.or.ddit.login.service;

import java.lang.reflect.Member;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.MemberVo;

public interface MemberService {

	//id 중복검사
	public String idCheck(String id);
	
	//닉네임 중복검사
	public String nickCheck(String nick);
	
	//이메일 중복검사
	public String emailCheck(String email);
	
	public List<BoardVo> BoardList(BoardVo Board);
	
	public Member getJoinMember(HttpServletRequest req); 	
	
	public int Memberjoin(MemberVo vo); 	
	
	public Member getLoginMember(HttpServletRequest req); 	
	
	public static MemberVo login(MemberVo member) {
		// TODO Auto-generated method stub
		return null;
	}

	boolean login(Member member);

}
