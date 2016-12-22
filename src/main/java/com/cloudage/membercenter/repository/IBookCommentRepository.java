package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.BookComment;
@Repository
public interface IBookCommentRepository extends PagingAndSortingRepository<BookComment, Integer> {

	@Query("from BookComment bookcomment where bookcomment.goods.id=?1")
	Page<BookComment> findBookCommentsByBookId(int goods_id, Pageable pageRequest);

//	@Query("from BookComment bookcomment where bookcomment.goods.goodsName=?1")
//	Page<BookComment> findBookCommentsByBookId(String goods_name, Pageable pageRequest);

}
