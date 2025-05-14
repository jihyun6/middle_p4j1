package kr.or.ddit.cart.service;

import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.cart.dao.CartDaoImpl;
import kr.or.ddit.vo.CartVo;

public class CartServiceImpl implements IcartService {
    private CartDaoImpl dao;

    private CartServiceImpl() {
        dao = CartDaoImpl.getDao();
    }

    private static CartServiceImpl service;

    public static CartServiceImpl getServiceImpl() {
        if (service == null) {
            service = new CartServiceImpl();
        }
        return service;
    }

    @Override
    public List<CartVo> cartlist(int memNo) {
        List<CartVo> list = dao.cartlist(memNo);
        return list != null ? list : new ArrayList<>();
    }


    @Override
    public int cartDelete(CartVo cart) {
        return dao.cartDelete(cart);
    }

	@Override
	public List<CartVo> cartInsert(CartVo cart) {
		return dao.cartInsert(cart);
	}
	
	
}
