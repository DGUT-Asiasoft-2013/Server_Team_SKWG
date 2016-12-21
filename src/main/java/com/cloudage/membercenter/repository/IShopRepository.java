package com.cloudage.membercenter.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Shop;
@Repository
public interface IShopRepository extends PagingAndSortingRepository<Shop, Integer>{

}
