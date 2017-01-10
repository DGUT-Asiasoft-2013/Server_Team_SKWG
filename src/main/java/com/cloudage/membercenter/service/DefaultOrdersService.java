package com.cloudage.membercenter.service;

import java.util.List;

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
        public Orders findOrdersByOrdersID(String ordersID) {
                return ordersRepo.findOrdersByOrdersID(ordersID);
        }
        
        @Override
        public void deleteOrders(Orders orders) {
                ordersRepo.delete(orders);
        }

        @Override
        public Orders findPreOrderByID(int buyerId, int goodsId) {
                return ordersRepo.findPreOrderByID(buyerId, goodsId);
        }

        //获取当前用户的订单
		@Override
		public Page<Orders> findAllOfMine(Integer id, int page) {
			Sort sort=new Sort(Direction.DESC,"createDate");
			PageRequest rePageRequest=new PageRequest(page, 5,sort);
			return ordersRepo.findAllOfMine(id,rePageRequest);
		}

		// 获取当前用户不同状态的订单
		@Override
		public Page<Orders> findAllofMineWithState(int userId, int state, int page) {
			Sort sort=new Sort(Direction.DESC,"createDate");
			PageRequest pageRequest=new PageRequest(page, 10,sort);
			return ordersRepo.findAllOfMineWithState(userId, state, pageRequest);
		}
		
		// 获取卖家不同状态的订单
		@Override
		public Page<Orders> findOfSellerWithState(int sellerId, int state, int page) {
			Sort sort=new Sort(Direction.DESC,"createDate");
			PageRequest pageRequest=new PageRequest(page, 10,sort);
			return ordersRepo.findBySellerWithState(sellerId, state, pageRequest);
		}

		@Override
		public List<Orders> findAllByOrdersId(String ordersId) {
			
			return ordersRepo.findAllByOrdersId(ordersId);
		}

		// 获取买家全部退货订单
		@Override
		public Page<Orders> findAllRefundOfBuyer(int userId, int page) {
			Sort sort=new Sort(Direction.DESC,"createDate");
			PageRequest pageRequest=new PageRequest(page, 10,sort);
			return ordersRepo.findAllRefundOfBuyer(userId, pageRequest);
		}

		@Override
		public Page<Orders> findAllRefundOfSeller(int userId, int page) {
			Sort sort=new Sort(Direction.DESC,"createDate");
			PageRequest pageRequest=new PageRequest(page, 10,sort);
			return ordersRepo.findAllRefundOfSeller(userId, pageRequest);
		}
        

}
