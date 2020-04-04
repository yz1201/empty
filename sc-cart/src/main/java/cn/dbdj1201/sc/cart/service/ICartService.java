package cn.dbdj1201.sc.cart.service;

import cn.dbdj1201.sc.cart.pojo.Cart;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-04 21:45
 **/
public interface ICartService {
    void addCart(Cart cart);

    List<Cart> queryCartList();

    void updateCarts(Cart cart);

    void deleteCart(String skuId);
}
