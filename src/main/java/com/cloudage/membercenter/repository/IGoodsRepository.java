package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Goods;

@Repository
public interface IGoodsRepository extends PagingAndSortingRepository<Goods, Integer> {
	@Query("from Goods goods where goods.seller.id = ?1")
	Page<Goods> findAllOfSellerId(int sellerId, Pageable page);
	
	@Query("from Goods goods where goods.goodsName like %?1% or goods.author like %?1% " )
	Page<Goods> findAllByKeyword(String keyword, Pageable pageRequest);
	
	@Query("from Goods goods where goods.shop.id = ?1")
	Page<Goods> findAllByShopId(int shopId, Pageable page);
	
	@Query("from Goods goods where goods.goodsType= ?1")
	Page<Goods> findAllByType(String type, Pageable pageRequest);
}
