package kr.or.ddit.black.dao;

import java.util.List;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.BlacklistVo;

public class BlacklistDaoImpl extends MybatisDao implements IBlacklistDao {

    private static BlacklistDaoImpl instance;

    private BlacklistDaoImpl() {}

    public static BlacklistDaoImpl getInstance() {
        if (instance == null) {
            instance = new BlacklistDaoImpl();
        }
        return instance;
    }

    @Override
    public List<BlacklistVo> blacklistList(BlacklistVo blacklist) {
     
        return selectList("blacklist.blacklistList", blacklist);
    }

    @Override
    public BlacklistVo blacklistDetail(BlacklistVo blacklist) {
      
        return selectOne("blacklist.blacklistDetail", blacklist);
    }

    @Override
    public int blacklistInsert(BlacklistVo blacklist) {
    
        return update("blacklist.blacklistInsert", blacklist);
    }

    @Override
    public int blacklistDelete(BlacklistVo blacklist) {
    
        return update("blacklist.blacklistDelete", blacklist);
    }

    @Override
    public int blacklistUpdate(BlacklistVo blacklist) {
   
        return update("blacklist.blacklistUpdate", blacklist);
    }
    
    public int checkDuplicate(int memNo) {
        return selectOne("blacklist.checkDuplicate", memNo);
    }
}
