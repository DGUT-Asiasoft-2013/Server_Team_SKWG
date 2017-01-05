package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Orders;

public interface IOrdersService {
        Orders save(Orders orders);
        Page<Orders> findAllBySellerId(int sellerId, int page);
        Page<Orders> findAllByBuyerId(int buyerId, int page);
        Orders findOrdersByOrdersID(String ordersID);
        void deleteOrders(Orders orders);
        Orders findPreOrderByID(int buyerId, int goodsId);
        
        //获取当前用户的订单
		Page<Orders> findAllOfMine(Integer id, int page);
		// 或取当前用户不同状态的订单
		Page<Orders> findAllofMineWithState(int userId, int state, int page);
		// 获取卖家不同状态的订单
		Page<Orders> findOfSellerWithState(int sellerId, int state, int page);
}
