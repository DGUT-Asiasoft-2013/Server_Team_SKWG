package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Comment;

@Repository
public interface ICommentRepository extends PagingAndSortingRepository<Comment, Integer> {
        
        @Query("from Comment comment where comment.article.id = ?1")
        Page<Comment> findAllOfArticleId(int articleId, Pageable page);
        
        @Query("from Comment comment where comment.article.author.id = ?1")
        Page<Comment> findAllOfAuthorId(int authorId, Pageable page);
        
        @Query("from Comment comment where comment.author.id = ?1")
        Page<Comment> findAllOfUserId(int userId, Pageable page);
}
