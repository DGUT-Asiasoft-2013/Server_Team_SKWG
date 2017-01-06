package com.cloudage.membercenter.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Subscribe;

@Repository
public interface ISubscribesRepository extends PagingAndSortingRepository<Subscribe, Subscribe.Key>{

        @Query("select count(*) from Subscribe s where s.id.shop.id = ?1")
        int subscribeCountsOfShop(int shopId);
        
        @Query("select count(*) from Subscribe s where s.id.user.id = ?1 and s.id.shop.id = ?2")
        int checkSubscribeExsists(int userId, int shopId);
        
        @Query("from Subscribe s where s.id.shop.id = ?1")
        Page<Subscribe> findSubscribeByShopId(int shopId, Pageable page);
        
        @Query("from Subscribe s where s.id.user.id = ?1")
        Page<Subscribe> findSubscribeByUserid(int userId, Pageable page);
}
