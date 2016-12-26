package com.cloudage.membercenter.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Likes;
import com.cloudage.membercenter.entity.ShoppingCart;

@Repository
public interface IShoppingCartRepository extends PagingAndSortingRepository<ShoppingCart, ShoppingCart.Key>{
	@Query("select count(*) from ShoppingCart cart where cart.id.buyer.id = ?1 and cart.id.goods.id = ?2")
	int checkCartExsist(int userId, int goodsId);
	@Query("from ShoppingCart cart where cart.id.buyer.id = ?1 and cart.id.goods.id = ?2")
	ShoppingCart findbyKey(int userId, int goodsId);
}
