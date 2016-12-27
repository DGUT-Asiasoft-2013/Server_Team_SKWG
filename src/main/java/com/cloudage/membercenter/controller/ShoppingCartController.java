package com.cloudage.membercenter.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.entity.ShoppingCart;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.entity.ShoppingCart.Key;
import com.cloudage.membercenter.service.IGoodsService;
import com.cloudage.membercenter.service.IShoppingCartService;

@RestController
@RequestMapping("/api")
public class ShoppingCartController {
	@Autowired
	IShoppingCartService shoppingCartService;
	@Autowired
	IGoodsService goodsService;
	@Autowired
	UserController userController;
	
	// 添加至购物车
	@RequestMapping(value="/shoppingcart/add/{goods_id}", method = RequestMethod.POST)
	public ShoppingCart addCart(@PathVariable int goods_id, @RequestParam int quantity,
			HttpServletRequest request) {
		User me = userController.getCurrentUser(request);
		Goods goods = goodsService.findById(goods_id);
		if(shoppingCartService.checkCart(me.getId(), goods.getId())) {
			ShoppingCart cart = shoppingCartService.findById(me.getId(), goods.getId());
			cart.setQuantity(cart.getQuantity() + quantity);
			return shoppingCartService.save(cart);
		} else {
			ShoppingCart.Key key = new Key();
			key.setBuyer(me);
			key.setGoods(goods);
			
			ShoppingCart cart = new ShoppingCart();
			cart.setId(key);
			cart.setQuantity(quantity);
			return shoppingCartService.save(cart);
		}
	}
	
	// 返回当前用户购物车
	@RequestMapping(value="/shoppingcart/get/{page}")
	public Page<ShoppingCart> getAllByUserId(@PathVariable int page, HttpServletRequest request) {
		User me = userController.getCurrentUser(request);
		return shoppingCartService.findAllByUserId(me.getId(), page);
	}
}
