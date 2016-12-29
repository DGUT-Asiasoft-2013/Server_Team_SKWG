package com.cloudage.membercenter.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudage.membercenter.entity.Bill;

public interface IBillRepository extends PagingAndSortingRepository<Bill, Integer> {

	@Query("from Bill b where b.user.id = ?1")
	Page<Bill> findBillByUserID(int ID , Pageable page);
}
