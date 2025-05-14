package kr.or.ddit.login.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.catalina.tribes.Member;
import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.MemberVo;

public class MemberDaoImpl extends MybatisDao implements MemberDao {



	private MemberDaoImpl() {
		
	}
	private static MemberDaoImpl dao;
	
	public static MemberDaoImpl getDao() {
		if(dao == null) dao = new MemberDaoImpl();
		
		return dao;
	}
	
	
	public MemberVo login(MemberVo member) {
		return selectOne("member.login", member);
	}
	
	@Override
	public List<BoardVo> prodList(BoardVo Board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVo BoardDetail(BoardVo Board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int BoardInsert(BoardVo Board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int BoardDelete(BoardVo Board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int BoardUpdate(BoardVo Board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCntMemberByUseridUserpw(Connection conn, Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	//id : asdf
	@Override
	public String idCheck(String id) {
		
		SqlSession sql = MybatisUtil.getInstance();
		
		String resId = null;
		
		try {
			resId = sql.selectOne("member.idCheck", id);
		} catch (Exception e) {
			   e.printStackTrace();
		}finally {
			sql.commit(); //select일경우 생략 가능 
			sql.close();
		}
		
		
		return resId;
	}


	@Override
	public String nickCheck(String nick) {
		SqlSession sql2 = MybatisUtil.getInstance();
		
		String resNick = null;
		
		try {
			resNick = sql2.selectOne("member.nickCheck", nick);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sql2.commit();
			sql2.close();
		}
		
		return resNick;
	}


	@Override
	public int memberJoin(MemberVo vo) {
		
		SqlSession sql = MybatisUtil.getInstance();
		
		int res3 = 0;
		
		try {
			res3 = sql.insert("member.memberJoin", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sql.commit();
			sql.close();
		}
		return res3;
	}


	@Override
	public String emailCheck(String email) {
		SqlSession sql3 = MybatisUtil.getInstance();
		
		String resEmail = null;
		
		try {
			resEmail  = sql3.selectOne("member.emailckeck", email);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sql3.commit();
			sql3.close();
		}
		return resEmail;
	}

}
