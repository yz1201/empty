package cn.dbdj1201.sc.order.service;

import cn.dbdj120.sc.common.pojo.PageResult;
import cn.dbdj1201.sc.order.pojo.Order;

/**
 * @author tyz1201
 * @datetime 2020-04-04 23:31
 **/
public interface IOrderService {
    Long createOrder(Order order);

    PageResult<Order> queryUserOrderList(Integer page, Integer rows, Integer status);

    Boolean updateStatus(Long id, Integer status);

    Order queryById(Long id);
}
