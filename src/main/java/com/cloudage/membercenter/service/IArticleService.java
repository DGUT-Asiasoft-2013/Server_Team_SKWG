package com.cloudage.membercenter.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

import com.cloudage.membercenter.entity.Article;
import com.cloudage.membercenter.entity.User;

public interface IArticleService {
	    Article findArticleById(int article_id);
        List<Article> findAllByAuthor(User user);
        List<Article> findAllByAuthorId(Integer userId);
        Page<Article> getForums(int page);
        Page<Article> searchArticlWithKeyword(String keyword, int page);
        Page<Article> findAllArticleOfMe(int userId, int page);
        Article save(Article article);
        Article findOne(Integer articleId);
        int deleteArticleById(int article_id);
        
}
