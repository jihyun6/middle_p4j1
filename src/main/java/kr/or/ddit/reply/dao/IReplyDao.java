package kr.or.ddit.reply.dao;

import java.util.List;

import kr.or.ddit.vo.ReplyVo;

public interface IReplyDao {

	public List<ReplyVo> replyList(ReplyVo reply);
	
	public ReplyVo replyDetail(ReplyVo reply);
	
	public int replyInsert (ReplyVo reply);
	
	public int replyDelete (ReplyVo reply);
	
	public int replyUpdate (ReplyVo reply);
}
