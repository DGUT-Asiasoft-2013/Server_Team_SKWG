package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Goods;

public interface IGoodsService {
	Goods save(Goods goods);
	Goods findById(int goodsId);
	Page<Goods> findAllByBuyerId(int buyerId, int page);
	Page<Goods> findAllBySellerId(int SellerId, int page);
}
