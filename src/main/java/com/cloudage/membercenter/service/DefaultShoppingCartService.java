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
import com.cloudage.membercenter.entity.ShoppingCart;
import com.cloudage.membercenter.entity.ShoppingCart.Key;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.IShoppingCartRepository;
@Component
@Service
@Transactional
public class DefaultShoppingCartService implements IShoppingCartService {

	@Autowired
	IShoppingCartRepository shoppingCartRepo;
	
	@Override
	public void addShoppingCart(User buyer, Goods goods, int quantity) {
		ShoppingCart.Key key = new Key();
		key.setBuyer(buyer);
		key.setGoods(goods);
		
		ShoppingCart cart = new ShoppingCart();
		cart.setId(key);
		cart.setQuantity(quantity);
		shoppingCartRepo.save(cart);
	}
	
	@Override
	public boolean checkCart(int userId, int goodsId) {
		return shoppingCartRepo.checkCartExsist(userId, goodsId) > 0;
	}
	
	// 通过key查询
	@Override
	public ShoppingCart findById(int userId, int goodsId) {
		return shoppingCartRepo.findbyKey(userId, goodsId);
	}

	@Override
	public ShoppingCart save(ShoppingCart cart) {
		return shoppingCartRepo.save(cart);
	}

	
	@Override
	public Page<ShoppingCart> findAllByUserId(int userId, int page) {
		 Sort sort = new Sort(Direction.DESC, "createDate");
         PageRequest pageRequest = new PageRequest(page, 6, sort);
		return shoppingCartRepo.findAllByUserId(userId, pageRequest);
	}

	@Override
	public void delete(Key cartKey) {
		shoppingCartRepo.delete(cartKey);
	}

	@Override
	public ShoppingCart findById(int cartId) {
		// TODO Auto-generated method stub
		return null;
	}


}
