package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.CommomInfo;

@Repository
public interface ICommomInfoRepository extends PagingAndSortingRepository<CommomInfo, Integer>{
	@Query("from CommomInfo commomInfo where commomInfo.user.id = ?1 and commomInfo.defaultInfo = 1")
	CommomInfo findDefaultCommomInfoOfUser(int userId);
	@Query("from CommomInfo commomInfo where commomInfo.user.id = ?1")
	Page<CommomInfo> findAllOfUser(int userId, Pageable page);
}
