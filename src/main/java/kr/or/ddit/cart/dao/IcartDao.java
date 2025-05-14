package kr.or.ddit.cart.dao;

import java.util.List;

import kr.or.ddit.vo.CartVo;
import kr.or.ddit.vo.PaymentVo;

public interface IcartDao {
    List<CartVo> cartlist(int memNo);
    int cartDelete(CartVo cart);
    List<CartVo> cartInsert(CartVo cart);
}
