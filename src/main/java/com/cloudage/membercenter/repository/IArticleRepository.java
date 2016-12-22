package com.cloudage.membercenter.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Article;
import com.cloudage.membercenter.entity.Comment;
import com.cloudage.membercenter.entity.User;

@Repository
public interface IArticleRepository extends PagingAndSortingRepository<Article, Integer>{

	@Query("from Article article where article.id = ?1")
	Article findArticleById(int article_id);
	
	@Query("from Article article where article.author = ?1")
	List<Article> findAllByAuthor(User user);
	
	@Query("from Article article where article.author.id = ?1")
	Page<Article> findAllArticleOfMe(int userId, Pageable page);

	@Query("from Article article where article.author.id = ?1")
	List<Article> findAllByAuthorId(Integer userId);

	@Query("from Article article where article.text like %?1%")
	Page<Article> searchArticleWihtKeyword(String keyword, Pageable page);
}
