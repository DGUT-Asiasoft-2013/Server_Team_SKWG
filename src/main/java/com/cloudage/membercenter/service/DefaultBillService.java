package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Bill;
import com.cloudage.membercenter.entity.ShoppingCart;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IBillRepository;

@Component
@Service
@Transactional
public class DefaultBillService implements IBillService {

	@Autowired
	IBillRepository billRepo;

	@Override
	public Bill save(Bill bill) {
		return billRepo.save(bill);
	}

	@Override
	public Page<Bill> findBillByUserID(int userId, int page) {
		Sort sort = new Sort(Direction.DESC, "createDate");
		PageRequest pageRequest = new PageRequest(page, 10, sort);
		return billRepo.findBillByUserID(userId, pageRequest);
	}


}
