package kr.or.ddit.cart.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisDao;
import kr.or.ddit.vo.CartVo;

public class CartDaoImpl extends MybatisDao implements IcartDao {
    private static CartDaoImpl dao;

    private CartDaoImpl() {}

    public static CartDaoImpl getDao() {
        if (dao == null) {
            dao = new CartDaoImpl();
        }
        return dao;
    }

    @Override
    public List<CartVo> cartlist(int memNo) {
        try {
            // MybatisDao의 selectList 메서드 활용
            return selectList("cart.cartlist", memNo);
        } catch (Exception e) {
            System.err.println("장바구니 조회 중 오류: " + e.getMessage());
            return new ArrayList<>();
        }
    }
	

    @Override
    public int cartDelete(CartVo cart) {
        try {
            return update("cart.cartDelete", cart);
        } catch (Exception e) {
            System.err.println("장바구니 삭제 중 오류: " + e.getMessage());
            return 0;
        }
    }


	@Override
	public List<CartVo> cartInsert(CartVo cart) {
		return selectList("cart.cartInsert", cart);
	}
	
}
