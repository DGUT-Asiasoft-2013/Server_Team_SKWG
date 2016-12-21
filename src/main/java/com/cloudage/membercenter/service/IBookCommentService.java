package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.BookComment;

public interface IBookCommentService {

	Page<BookComment> findAllCommentsByBookId(int goods_id, int page);

}
