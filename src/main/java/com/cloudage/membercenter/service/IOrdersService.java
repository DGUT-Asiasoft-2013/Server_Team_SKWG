package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Orders;

public interface IOrdersService {
        Orders save(Orders orders);
        Page<Orders> findAllBySellerId(int sellerId, int page);
        Page<Orders> findAllByBuyerId(int buyerId, int page);
        Orders findOrdersByOrdersID(String ordersID);
        void deleteOrders(Orders orders);
        Orders findPreOrderByID(int buyerId, int goodsId);
        List<Orders> findAllByOrdersId(String ordersId);
        Page<Orders> findAllByOrdersId(String orderId, int page);
        // 获取买家全部退货订单
        Page<Orders> findAllRefundOfBuyer(int userId, int page);
        // 获取商家全部退货订单
        Page<Orders> findAllRefundOfSeller(int userId, int page);
        //获取当前用户的订单
		Page<Orders> findAllOfMine(Integer id, int page);
		// 或取当前用户不同状态的订单
		Page<Orders> findAllofMineWithState(int userId, int state, int page);
		// 获取卖家不同状态的订单
		Page<Orders> findOfSellerWithState(int sellerId, int state, int page);
}
