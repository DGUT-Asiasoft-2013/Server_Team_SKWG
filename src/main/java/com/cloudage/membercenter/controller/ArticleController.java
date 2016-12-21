package com.cloudage.membercenter.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudage.membercenter.entity.Article;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IArticleService;
import com.cloudage.membercenter.service.ILikesService;

@RestController
@RequestMapping("/api")
public class ArticleController {
	
	 @Autowired
     IArticleService articleService;
	 
	 @Autowired
	 UserController userController;
	 
	 @Autowired
     ILikesService likesService;
	 @RequestMapping(value = "/articles/{userId}")
     public List<Article> getArticlesByUserID(@PathVariable Integer userId) {
             return articleService.findAllByAuthorId(userId);
     }

     @RequestMapping(value = "/article", method = RequestMethod.POST)
     public Article addArticle(@RequestParam String title, @RequestParam String text, HttpServletRequest request) {
             User currentUser = userController.getCurrentUser(request);
             Article article = new Article();
             article.setAuthor(currentUser);
             article.setTitle(title);
             article.setText(text);
             return articleService.save(article);
     }
     
     @RequestMapping("/feeds/{page}")
     public Page<Article> getFeeds(@PathVariable int page) {
             return articleService.getFeeds(page);
     }

     @RequestMapping("/feeds")
     public Page<Article> getFeeds() {
             return getFeeds(0);
     }
     
     @RequestMapping("/article/{article_id}/likes")
     public int countLikes(@PathVariable int article_id) {
             return likesService.countLikes(article_id);
     }

     @RequestMapping("/article/{article_id}/isliked")
     public boolean checkLiked(@PathVariable int article_id, HttpServletRequest request) {
             User me = userController.getCurrentUser(request);
             return likesService.checkLiked(me.getId(), article_id);
     }

     @RequestMapping(value = "/article/{article_id}/likes", method = RequestMethod.POST)
     public int changeLikes(@PathVariable int article_id, @RequestParam boolean likes, HttpServletRequest request) {
             User me = userController.getCurrentUser(request);
             Article article = articleService.findOne(article_id);

             if (likes) {
                     likesService.addLike(me, article);
             } else {
                     likesService.removeLike(me, article);
             }
             return likesService.countLikes(article_id);
     }

     @RequestMapping("/article/s/{keyword}")
     public Page<Article> searchArticleWithKeyword(@PathVariable String keyword,
                     @RequestParam(defaultValue = "0") int page) {
             return articleService.searchArticlWithKeyword(keyword, page);
     }
}
