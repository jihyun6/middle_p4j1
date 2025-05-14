package kr.or.ddit.util;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class MybatisDao {
	
	public <T> List<T> selectList(String statement){
		SqlSession session = MybatisUtil.getInstance(true);
		List<T> list = null;
		try {
			list = session.selectList(statement);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return list;
	}

	public <T> List<T> selectList(String statement, Object param){
		SqlSession session = MybatisUtil.getInstance(true);
		List<T> list = null;
		try {
			list = session.selectList(statement, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return list;
	}
	
	public <T> T selectOne(String statement) {
		SqlSession session = MybatisUtil.getInstance(true);
		T obj = null;
		try {
			obj = session.selectOne(statement);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return obj;
	}
	
	public <T> T selectOne(String statement, Object param) {
		SqlSession session = MybatisUtil.getInstance(true);
		T obj = null;
		try {
			obj = session.selectOne(statement, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return obj;
	}
	
	public int update(String satement) {
		SqlSession session = MybatisUtil.getInstance(true);
		int num =0;
		try {
			 num = session.update(satement);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return num;
	}
	
	public int update(String satement, Object param) {
		SqlSession session = MybatisUtil.getInstance(true);
		int num =0;
		try {
			 num = session.update(satement, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.close();
		return num;
	}
}