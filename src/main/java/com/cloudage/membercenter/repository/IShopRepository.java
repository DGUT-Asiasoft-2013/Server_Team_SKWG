package com.cloudage.membercenter.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Shop;
@Repository
public interface IShopRepository extends PagingAndSortingRepository<Shop, Integer>{
	@Query("from Shop shop where shop.owner.id = ?1")
	public Shop findByUserId(int userId);
	
	@Query("from Shop shop where shop.shopName = ?1")
	public Shop findOne(String shopname);
}
