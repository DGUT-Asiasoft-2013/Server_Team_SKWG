package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.BookComment;

public interface IBookCommentService {
	void save(BookComment comment);
	Page<BookComment> findAllCommentsByBookId(int goods_id, int page);

	//Page<BookComment> findAllCommentsByBookName(String goods_name, int page);

}
