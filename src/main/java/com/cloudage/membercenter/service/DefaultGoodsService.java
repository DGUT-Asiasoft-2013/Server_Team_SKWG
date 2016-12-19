package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
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

}
