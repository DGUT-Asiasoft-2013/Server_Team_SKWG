package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloudage.membercenter.entity.Goods;

@Repository
public interface IGoodsRepository extends PagingAndSortingRepository<Goods, Integer> {
	@Query("from Goods goods where goods.shop.owner.id = ?1 and goods.onSale = 1")
	Page<Goods> findAllOfSellerId(int sellerId, Pageable page);

	@Query("from Goods goods where goods.shop.owner.id = ?1 and goods.onSale = 1")
	Page<Goods> findAllOnSaleOfSellerId(int sellerId, Pageable page);

	@Query("from Goods goods where goods.goodsName like %?1% or goods.author like %?1% and goods.onSale = 1" )
	Page<Goods> findAllByKeyword(String keyword, Pageable pageRequest);

	@Query("from Goods goods where goods.shop.id = ?1 and goods.onSale = 1")
	Page<Goods> findAllByShopId(int shopId, Pageable page);

	//所有在售商品
	@Query("from Goods goods where goods.onSale = 1")
	Page<Goods> findAllOnSale(Pageable page);
	
	// 查询当前店铺所有正在销售的商品
	@Query("from Goods goods where goods.shop.id = ?1 and goods.onSale = 1")
	Page<Goods> findAllOnSaleByShopId(int shopId, Pageable page);

	// 查询当前店铺所有已下架的商品
	@Query("from Goods goods where goods.shop.id = ?1 and goods.onSale = 0")
	Page<Goods> findAllOffSaleByShopId(int shopId, Pageable page);

	@Query("from Goods goods where goods.goodsType= ?1 and goods.onSale = 1")
	Page<Goods> findAllByType(String type, Pageable pageRequest);
	//查找+分类
	@Query("from Goods goods where goods.goodsName like %?1% or goods.author like %?1%  and goods.goodsType=?2 and goods.onSale = 1")
	Page<Goods> findSearchAndClassify(String keyword, String goodsType, Pageable pageRequest);
	//Page<Goods> findSearchAndClassify(String keyword, String goodsType, Pageable pageRequest);
	//查找+排序
	@Query("from Goods goods where goods.goodsName like %?1% or goods.author like %?1% and goods.onSale = 1" )
	Page<Goods> findSearchAndSort(String keyword, Pageable pageRequest);
	//分类+排序
	@Query("from Goods goods where goods.goodsType=?1 and goods.onSale = 1")
	Page<Goods> findclassifyAndSort(String goodsType,Pageable pageRequest);
	//查找+分类+排序
	@Query("from Goods goods where goods.goodsName like %?1% or goods.author like %?1%  and goods.goodsType=?2 and goods.onSale = 1")
	Page<Goods> findSearchAndClassifyAndSort(String keyword, String goodsType, Pageable pageRequest);

	//按价格范围查找
	@Query("from Goods goods where goods.goodsPrice between ?1 and ?2 and goods.onSale = 1")
	Page<Goods> findSearchAndClassify(double minprice, double maxprice, Pageable pageRequest);




}
