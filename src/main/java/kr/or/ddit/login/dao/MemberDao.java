package kr.or.ddit.login.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.catalina.tribes.Member;

import kr.or.ddit.vo.BoardVo;
import kr.or.ddit.vo.MemberVo;



public interface MemberDao {
	public List<BoardVo> prodList(BoardVo Board);
	
	public BoardVo BoardDetail(BoardVo Board);

	public int BoardInsert(BoardVo Board);
	
	public int BoardDelete(BoardVo Board);
	
	public int BoardUpdate(BoardVo Board);
	
	public int selectCntMemberByUseridUserpw(Connection conn, Member member);

	public static BoardVo login(BoardVo member) {
		// TODO Auto-generated method stub
		return null;
		
	}
	//id중복검사 
	public String idCheck(String id);
	//닉네임중복검사
	public String nickCheck(String nick);
	//이메일 중복검사
	public String emailCheck(String email);
	//저장 가입하기 
		public int memberJoin(MemberVo  vo);
}
