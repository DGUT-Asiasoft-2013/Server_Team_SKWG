package com.cloudage.membercenter.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.CommomInfo;

@Repository
public interface ICommomInfoRepository extends PagingAndSortingRepository<CommomInfo, Integer>{

}
