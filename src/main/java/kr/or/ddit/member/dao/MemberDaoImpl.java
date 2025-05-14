package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MemberVo;
import kr.or.ddit.vo.SearchCriteriaVo;

public class MemberDaoImpl extends MybatisDao implements IMemberDao {
	
	private static MemberDaoImpl instance;

	private MemberDaoImpl() {

	}

	public static MemberDaoImpl getInstance() {
		if (instance == null)
			instance = new MemberDaoImpl();
		return instance;
	}
	
	
	@Override
	public String idCheck(String id) {
		return selectOne("member.idCheck", id);
	}	
	
	@Override
	public String nickCheck(String nick) {
		return selectOne("member.nickCheck", nick);
	}
	
	@Override
	public String emailCheck(String email) {
		return selectOne("member.emailCheck", email);
	}
	
	@Override
	public int memberJoin(MemberVo vo) {

		SqlSession sql = MybatisUtil.getInstance();

		int res3 = 0;

		try {
			res3 = sql.insert("member.memberJoin", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sql.commit();
			sql.close();
		}
		return res3;
	}

	public MemberVo login(MemberVo member) {
		return selectOne("member.login", member);
	}

	@Override
	public List<MemberVo> memberList(MemberVo member) {
		// TODO Auto-generated method stub
		return selectList("member.memberList", member);
	}

	@Override
	public MemberVo memberDetail(MemberVo member) {
		// TODO Auto-generated method stub
		return selectOne("member.memberDetail", member);
	}
	
    @Override
    public List<MemberVo> searchMembers(SearchCriteriaVo criteria) {

        return selectList("member.searchMembers", criteria);
    }

	@Override
	public int memberDelete(MemberVo member) {
		// TODO Auto-generated method stub
		return update("member.memberDelete", member);
	}
}
