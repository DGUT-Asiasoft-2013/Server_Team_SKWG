package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.repository.IGoodsRepository;
@Component
@Service
@Transactional
public class DefaultGoodsService implements IGoodsService{
	@Autowired
	IGoodsRepository goodsRepo;
	@Override
	public Goods save(Goods goods) {
		return goodsRepo.save(goods);
	}
	@Override
	public Goods findById(int goodsId) {
		return goodsRepo.findOne(goodsId);
	}

	@Override
	public Page<Goods> findAllBySellerId(int SellerId, int page) {
		Sort sort = new Sort(Direction.DESC, "createDate");
        PageRequest pageRequest = new PageRequest(page, 6, sort);
		return goodsRepo.findAllOfSellerId(SellerId, pageRequest);
	}
	
	@Override
	public Page<Goods> searchGoodsByKeyword(String keyword, int page) {
		// TODO Auto-generated method stub
		Sort sort=new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest=new PageRequest(page,6,sort);
		return goodsRepo.findAllByKeyword(keyword,pageRequest);
	}
	@Override
	public Page<Goods> findAll(int page) {
		// TODO Auto-generated method stub
		Sort sort=new Sort(Direction.DESC,"createDate");
		PageRequest pageRequest=new PageRequest(page,6,sort);
		return goodsRepo.findAll(pageRequest);
	}
	@Override
	public Page<Goods> sortGoodsBySortStyle(String keyword, String sortStyle, int page) {
		// TODO Auto-generated method stub
		Sort sort=new Sort(Direction.ASC,sortStyle);
		PageRequest pageRequest=new PageRequest(page,6,sort);
		return goodsRepo.findAllByKeyword(keyword,pageRequest);
	}
	@Override
	public Page<Goods> findAllByShopId(int shopId, int page) {
		Sort sort=new Sort(Direction.ASC, "goodsName");
		PageRequest pageRequest=new PageRequest(page,6,sort);
		return goodsRepo.findAllByShopId(shopId, pageRequest);
	}

	
	
}
