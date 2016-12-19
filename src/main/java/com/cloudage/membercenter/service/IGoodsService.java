package com.cloudage.membercenter.service;

import com.cloudage.membercenter.entity.Goods;

public interface IGoodsService {
	Goods save(Goods goods);
	Goods findById(int goodsId);
}
