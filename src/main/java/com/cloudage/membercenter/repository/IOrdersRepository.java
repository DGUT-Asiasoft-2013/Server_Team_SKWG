package com.cloudage.membercenter.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Orders;

@Repository
public interface IOrdersRepository extends PagingAndSortingRepository<Orders, Integer>{

        //通过卖家id找到卖家所有订单
        @Query("from Orders orders where orders.goods.seller.id = ?1")
        Page<Orders> findAllOfSeller(int sellerId, Pageable page);
        
        //通过买家id找到买家所有订单
        @Query("from Orders orders where orders.buyer.id = ?1")
        Page<Orders> findAllOfBuyer(int buyerId, Pageable page);
        
        //通过订单id查找订单
        @Query("from Orders orders where orders.ordersID = ?1")
        Orders findOrdersByOrdersID(String ordersID);
        
        @Query("from Orders orders where orders.buyer.id =?1 and orders.goods.id =?2 and ordersState = 1")
        Orders findPreOrderByID(int buyerId, int goodsId);

        //获取当前用户的所有订单
        @Query("from Orders orders where orders.buyer.id=?1")
		Page<Orders> findAllOfMine(Integer id, Pageable rePageRequest);
        
}
