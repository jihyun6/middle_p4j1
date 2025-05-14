package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.SearchCriteriaVo;

public class MemberServiceImpl implements IMemberService {
	MemberDaoImpl memberDao = MemberDaoImpl.getInstance();

	private static MemberServiceImpl instance;

	private MemberServiceImpl() {

	}

	public static MemberServiceImpl getInstance() {
		if (instance == null)
			instance = new MemberServiceImpl();
		return instance;
	}

	@Override
	public String idCheck(String id) {
		return memberDao.idCheck(id);
	}

	@Override
	public String nickCheck(String nick) {
		return memberDao.nickCheck(nick);
	}

	@Override
	public String emailCheck(String email) {
		return memberDao.emailCheck(email);
	}

	@Override
	public int memberjoin(MemberVo vo) {
		return memberDao.memberJoin(vo);
	}

	@Override
	public MemberVo login(MemberVo member) {
		return memberDao.login(member);
	}

	@Override
	public List<MemberVo> memberList(MemberVo member) {
		// TODO Auto-generated method stub
		return memberDao.memberList(member);
	}

	@Override
	public MemberVo memberDetail(MemberVo member) {
		// TODO Auto-generated method stub
		return memberDao.memberDetail(member);
	}


	@Override
	public List<MemberVo> searchMembers(SearchCriteriaVo criteria) {
		return memberDao.searchMembers(criteria);
		
	}

	@Override
	public int memberDelete(MemberVo member) {
		// TODO Auto-generated method stub
		return memberDao.memberDelete(member);
	}
}
