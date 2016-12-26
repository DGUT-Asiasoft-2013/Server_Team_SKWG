package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Goods;

public interface IGoodsService {
	Goods save(Goods goods);
	Goods findById(int goodsId);
	Page<Goods> findAllBySellerId(int SellerId, int page);
	Page<Goods> searchGoodsByKeyword(String keyword, int page);
	Page<Goods> findAll(int page);
	Page<Goods> sortGoodsBySortStyle(String keyword, String sortStyle, int page);
	Page<Goods> findAllByShopId(int shopId, int page);
	Page<Goods> classifyGoodsByType(String type, int page);
	Page<Goods> findAllWithSortStyle(String sort_style, int page);
}
