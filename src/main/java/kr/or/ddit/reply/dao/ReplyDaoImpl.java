package kr.or.ddit.reply.dao;

import java.util.List;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.ReplyVo;


public class ReplyDaoImpl extends MybatisDao implements IReplyDao{

	private static ReplyDaoImpl instance;

	private ReplyDaoImpl() {

	}

	public static ReplyDaoImpl getInstance() {
		if (instance == null)
			instance = new ReplyDaoImpl();
		return instance;
	}

	
	
	@Override
	public List<ReplyVo> replyList(ReplyVo reply) {
	    // selectList() 메서드 사용 확인
	    return selectList("reply.replyList", reply);
	}

	@Override
	public ReplyVo replyDetail(ReplyVo reply) {
		// TODO Auto-generated method stub
		return selectOne("reply.replyDetail", reply);
	}

	@Override
	public int replyInsert(ReplyVo reply) {
		// TODO Auto-generated method stub
		return update("reply.replyInsert", reply);
	}

	@Override
	public int replyDelete(ReplyVo reply) {
		// TODO Auto-generated method stub
		return update("reply.replyDelete", reply);
	}

	@Override
	public int replyUpdate(ReplyVo reply) {
		// TODO Auto-generated method stub
		return update("reply.replyUpdate", reply);
	}


}
