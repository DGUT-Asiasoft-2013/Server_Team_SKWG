package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Goods;

public interface IGoodsService {
	Goods save(Goods goods);
	Goods findById(int goodsId);
	Page<Goods> findAllBySellerId(int SellerId, int page);
	Page<Goods> searchGoodsByKeyword(String keyword, int page);
	Page<Goods> findAll(int page);
	//排序
	Page<Goods> sortGoodsBySortStyle(String sortStyle, int page);
	//按销量高到低排序
	Page<Goods> sortGoodsSales(int page);
	
	Page<Goods> findAllByShopId(int shopId, int page);
	Page<Goods> findAllOnSaleByShopId(int shopId, int page);
	Page<Goods> findAllOffSaleByShopId(int shopId, int page);
	Page<Goods> classifyGoodsByType(String type, int page);
	//查找+分类
	Page<Goods> searchAndClassify(String keyword, String goodsType, int page);
	//查找+排序
	Page<Goods> searchAndSort(String keyword, String sortStyle, int page);
	//分类+排序
	Page<Goods> classifyAndSort(String goodsType, String sortStyle, int page);
	//查找+分类+排序
	Page<Goods> searchAndClassifyAndSort(String keyword, String goodsType, String sortStyle, int page);
	//按价格范围查找
	Page<Goods> searchGoodsBetweenMinAndMax(double minprice, double maxprice, int page);
}
