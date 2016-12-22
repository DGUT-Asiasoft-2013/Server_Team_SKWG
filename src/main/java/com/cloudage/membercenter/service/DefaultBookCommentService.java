package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.BookComment;
import com.cloudage.membercenter.repository.IBookCommentRepository;

@Component
@Service
@Transactional
public class DefaultBookCommentService implements IBookCommentService {

	@Autowired
	IBookCommentRepository bookCommentRepo;
	
	@Override
	public Page<BookComment> findAllCommentsByBookId(int goods_id, int page) {
		// TODO Auto-generated method stub
		Sort sort = new Sort(Direction.DESC, "createDate");
        PageRequest pageRequest = new PageRequest(page, 6, sort);
		return bookCommentRepo.findBookCommentsByBookId(goods_id,pageRequest);
	}

//	@Override
//	public Page<BookComment> findAllCommentsByBookName(String goods_name, int page) {
//		// TODO Auto-generated method stub
//		Sort sort = new Sort(Direction.DESC, "createDate");
//        PageRequest pageRequest = new PageRequest(page, 6, sort);
//		return bookCommentRepo.findBookCommentsByBookId(goods_name,pageRequest);
//	}

}
