package com.cloudage.membercenter.service;

import com.cloudage.membercenter.entity.Shop;

public interface IShopService {
	Shop save(Shop shop);
	Shop findByUserId(int userId);
	Shop findOne(Integer shopId);
}
