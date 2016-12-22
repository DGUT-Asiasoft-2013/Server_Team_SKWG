package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Shop;
import com.cloudage.membercenter.repository.IShopRepository;
@Component
@Service
@Transactional
public class DefaultShopService implements IShopService {
	@Autowired
	IShopRepository shopRepo;
	@Override
	public Shop save(Shop shop) {
		return shopRepo.save(shop);
	}
	@Override
	public Shop findByUserId(int userId) {
		return shopRepo.findByUserId(userId);
	}
	

}
