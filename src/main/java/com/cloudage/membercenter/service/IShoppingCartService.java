package com.cloudage.membercenter.service;

import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.entity.ShoppingCart;
import com.cloudage.membercenter.entity.User;

public interface IShoppingCartService {
	ShoppingCart save(ShoppingCart cart);
	void addShoppingCart(User buyer, Goods goods, int quantity);
	boolean checkCart(int userId, int goodsId);
	ShoppingCart findById(int userId, int goodsId);
}
