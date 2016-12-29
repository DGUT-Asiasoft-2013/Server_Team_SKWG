package com.cloudage.membercenter.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudage.membercenter.entity.Article;
import com.cloudage.membercenter.entity.Comment;
import com.cloudage.membercenter.entity.Orders;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IArticleService;
import com.cloudage.membercenter.service.ILikesService;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/api")
public class ArticleController {

	@Autowired
    IUserService userService;
	
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
	public Article addArticle(@RequestParam String title, 
			@RequestParam String text,
			@RequestParam String articleImgName,
			MultipartFile articlesImage,
			HttpServletRequest request) {
		User currentUser = userController.getCurrentUser(request);
		Article article = new Article();
		article.setAuthor(currentUser);
		article.setTitle(title);
		article.setText(text);
		if (articlesImage != null) {
			try {
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
				FileUtils.copyInputStreamToFile(articlesImage.getInputStream(), new File(realPath, articleImgName + ".png"));
				article.setArticlesImage("upload/" + articleImgName + ".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return articleService.save(article);
	}

	@RequestMapping("/myarticles")
	public Page<Article> getArticleOfMe(@RequestParam(defaultValue = "0") int page, HttpServletRequest request) {
		User me = userController.getCurrentUser(request);
		return articleService.findAllArticleOfMe(me.getId(), page);
	}
	
	@RequestMapping(value = "/modify/{article_id}", method = RequestMethod.POST)
    public boolean modifyArticle(@RequestParam String title, @RequestParam String text,
   		 @PathVariable int article_id,HttpServletRequest request) {
   	     Article article=articleService.findArticleById(article_id);
            if (!(article.getTitle().equals(title))||!(article.getText().equals(text))) {
           	 article.setTitle(title);
                article.setText(text);
                    articleService.save(article);
                    return true;
            } else {
                    return false;
            }
    }
	
	@RequestMapping("/forums/{page}")
	public Page<Article> getForums(@PathVariable int page) {
		return articleService.getForums(page);
	}

	@RequestMapping("/forums")
	public Page<Article> getForum() {
		return getForums(0);
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
	
	@Modifying
	@RequestMapping(value="/article/{article_id}/delete",method=RequestMethod.DELETE)
	public int deleteArticleById(@PathVariable int article_id){
		return articleService.deleteArticleById(article_id);
	}
	
	@Modifying
	@RequestMapping(value="/article/{article_id}/deletelike",method=RequestMethod.DELETE)
	public int deleteLikeByArticleId(@PathVariable int article_id){
		return likesService.deleteLikeByArticleId(article_id);
	}
	
	//支付打赏
    @RequestMapping(value="/article/reward",method=RequestMethod.POST)
    public boolean  payForReward(@RequestParam double money,
    		HttpServletRequest request) {
    	User me=userController.getCurrentUser(request);
    	if(me.getMoney()>=money){
    		me.setMoney(me.getMoney()-money);
        	userService.save(me);
    		return true;
    	}
    	else{
    		return false;
    	}
    }
}
