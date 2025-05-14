package kr.or.ddit.reply.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.reply.dao.IReplyDao;
import kr.or.ddit.reply.dao.ReplyDaoImpl;
import kr.or.ddit.vo.ReplyVo;

public class ReplyServiceImpl implements IReplyService{

	private static ReplyServiceImpl instance;

	private ReplyServiceImpl() {

	}

	public static ReplyServiceImpl getInstance() {
		if (instance == null)
			instance = new ReplyServiceImpl();
		return instance;
	}

	IReplyDao replyDao = ReplyDaoImpl.getInstance();
	
	
	@Override
	public List<ReplyVo> replyList(ReplyVo reply) {
	    try {
	        List<ReplyVo> result = replyDao.replyList(reply);
	        // null 대신 빈 리스트 반환
	        return result != null ? result : new ArrayList<>();
	    } catch (Exception e) {
	        // 예외 발생 시 빈 리스트 반환
	        e.printStackTrace();
	        return new ArrayList<>();
	    }
	}

	@Override
	public ReplyVo replyDetail(ReplyVo reply) {
		// TODO Auto-generated method stub
		return replyDao.replyDetail(reply);
	}

	@Override
	public int replyInsert(ReplyVo reply) {
		// TODO Auto-generated method stub
		return replyDao.replyInsert(reply);
	}

	@Override
	public int replyDelete(ReplyVo reply) {
		// TODO Auto-generated method stub
		return replyDao.replyDelete(reply);
	}

	@Override
	public int replyUpdate(ReplyVo reply) {
		// TODO Auto-generated method stub
		return replyDao.replyUpdate(reply);
	}

}
