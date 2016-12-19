package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Comment;

public interface ICommentService {
        Page<Comment> findCommentOfArticle(int articleId, int page);
        Page<Comment> findAllOfAuthorId(int authorId, int page);
        Page<Comment> findAllOfUserId(int userId, int page);
        Comment save(Comment comment);
}
