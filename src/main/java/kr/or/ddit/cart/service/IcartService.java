package kr.or.ddit.cart.service;

import java.util.List;

import kr.or.ddit.vo.CartVo;
import kr.or.ddit.vo.PaymentVo;

public interface IcartService {
    List<CartVo> cartlist(int memNo);
    int cartDelete(CartVo cart);
    List<CartVo> cartInsert(CartVo cart);
}
