package com.cloudage.membercenter.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Push;

@Repository
public interface IPushRepository extends PagingAndSortingRepository<Push, Integer>{
     
        @Query("from Push p where p.receiver.id = ?1")
        Page<Push> findPushByUserId(int userId, Pageable page);

        
}
