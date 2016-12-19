package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Orders;

public interface IOrdersService {
        Orders save(Orders orders);
        Page<Orders> findAllBySellerId(int sellerId, int page);
        Page<Orders> findAllByBuyerId(int buyerId, int page);
}
