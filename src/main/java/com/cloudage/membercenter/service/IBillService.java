package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Bill;

public interface IBillService {
	Bill save(Bill bill);
	Page<Bill> findBillByUserID(int userId, int page);
}
