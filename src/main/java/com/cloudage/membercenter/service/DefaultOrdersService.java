package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Orders;
import com.cloudage.membercenter.repository.IOrdersRepository;

@Component
@Service
@Transactional
public class DefaultOrdersService implements IOrdersService{
        @Autowired
        IOrdersRepository ordersRepo;

        @Override
        public Orders save(Orders orders) {
                return ordersRepo.save(orders);
        }

        @Override
        public Page<Orders> findAllBySellerId(int sellerId, int page) {
                Sort sort = new Sort(Direction.DESC, "createDate");
                PageRequest pageRequest = new PageRequest(page, 6, sort);
                return ordersRepo.findAllOfSeller(sellerId, pageRequest);
        }

        @Override
        public Page<Orders> findAllByBuyerId(int buyerId, int page) {
                Sort sort = new Sort(Direction.DESC, "createDate");
                PageRequest pageRequest = new PageRequest(page, 6, sort);
                return ordersRepo.findAllOfBuyer(buyerId, pageRequest);
        }

        @Override
        public Orders findOrdersByOrdersID(int ordersID) {
                return ordersRepo.findOrdersByOrdersID(ordersID);
        }
        
}
