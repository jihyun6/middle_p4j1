package kr.or.ddit.login.service;

import java.lang.reflect.Member;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import kr.or.ddit.login.dao.MemberDaoImpl;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.MemberVo;

public class MemberServiceImpl implements MemberService {

	
	
	private MemberDaoImpl dao;
	
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getDao();
	}
	
	//객체 생성(new)
	private static MemberServiceImpl service;
	
	public static MemberServiceImpl getServiceImpl() {
		
		if(service ==null) service = new MemberServiceImpl();
		
		return service;
	}
	
	
	@Override
	public List<BoardVo> BoardList(BoardVo Board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member getJoinMember(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Member getLoginMember(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(Member member) {
		// TODO Auto-generated method stub
		return false;
	}

	//userId : asdf
	@Override
	public String idCheck(String id) {
		return dao.idCheck(id);
	}

	@Override
	public String nickCheck(String nick) {
		return dao.nickCheck(nick);
	}


	public static MemberServiceImpl getInstance() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int Memberjoin(MemberVo vo) {
		
		return dao.memberJoin(vo);
		
	}


	@Override
	public String emailCheck(String email) {
		return dao.emailCheck(email);
	}


	


	

	

}
