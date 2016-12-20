package com.cloudage.membercenter.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudage.membercenter.entity.Article;
import com.cloudage.membercenter.entity.Comment;
import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.entity.Likes;
import com.cloudage.membercenter.entity.Orders;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.ILikesRepository;
import com.cloudage.membercenter.service.IArticleService;
import com.cloudage.membercenter.service.ICommentService;
import com.cloudage.membercenter.service.IGoodsService;
import com.cloudage.membercenter.service.ILikesService;
import com.cloudage.membercenter.service.IOrdersService;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/api")
public class APIController {

        @Autowired
        IUserService userService;

        @Autowired
        IArticleService articleService;

        @Autowired
        ICommentService commentService;

        @Autowired
        ILikesService likesService;

        @Autowired
        IGoodsService goodsService;

        @Autowired
        IOrdersService ordersService;

        @RequestMapping(value = "/hello", method = RequestMethod.GET)
        public @ResponseBody String hello() {
                return "HELLO WORLD";
        }

        @RequestMapping(value = "/register", method = RequestMethod.POST)
    	public User register(@RequestParam String account, @RequestParam String passwordHash, @RequestParam String email,
    			@RequestParam String name, @RequestParam String address, @RequestParam String phoneNum,
    			MultipartFile avatar, HttpServletRequest request) {

    		User user = new User();
    		user.setAccount(account);
    		user.setPasswordHash(passwordHash);
    		user.setEmail(email);
    		user.setName(name);
    		user.setAddress(address);
    		user.setPhoneNum(phoneNum);

    		if (avatar != null) {
    			try {
    				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
    				FileUtils.copyInputStreamToFile(avatar.getInputStream(), new File(realPath, account + ".png"));
    				user.setAvatar("upload/" + account + ".png");
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}

    		return userService.save(user);
    	}
        @RequestMapping(value = "/login", method = RequestMethod.POST)
        public User login(@RequestParam String account, @RequestParam String passwordHash, HttpServletRequest request) {

                User user = userService.findByAccount(account);
                if (user != null && user.getPasswordHash().equals(passwordHash)) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("uid", user.getId());
                        return user;
                } else {
                        return null;
                }
        }

        @RequestMapping(value = "/change", method = RequestMethod.POST)
        public boolean changePassword(@RequestParam String passwordHash, @RequestParam String newPasswordHash,
                        HttpServletRequest request) {
                User user = getCurrentUser(request);
                if (user.getPasswordHash().equals(passwordHash)) {
                        user.setPasswordHash(newPasswordHash);
                        userService.save(user);
                        return true;
                } else {
                        return false;
                }
        }

        @RequestMapping(value = "/me", method = RequestMethod.GET)
        public User getCurrentUser(HttpServletRequest request) {
                HttpSession session = request.getSession(true);
                Integer uid = (Integer) session.getAttribute("uid");
                return userService.findById(uid);
        }

        @RequestMapping(value = "/recover", method = RequestMethod.POST)
        public Boolean passwordRecover(@RequestParam String email, @RequestParam String passwordHash,
                        HttpServletRequest request) {
                User user = userService.findByEmail(email);
                if (user == null) {
                        return false;
                } else {
                        user.setPasswordHash(passwordHash);
                        userService.save(user);
                        return true;
                }
        }

        @RequestMapping(value = "/articles/{userId}")
        public List<Article> getArticlesByUserID(@PathVariable Integer userId) {
                return articleService.findAllByAuthorId(userId);
        }

        @RequestMapping(value = "/article", method = RequestMethod.POST)
        public Article addArticle(@RequestParam String title, @RequestParam String text, HttpServletRequest request) {
                User currentUser = getCurrentUser(request);
                Article article = new Article();
                article.setAuthor(currentUser);
                article.setTitle(title);
                article.setText(text);
                return articleService.save(article);
        }

        @RequestMapping(value = "/goods", method = RequestMethod.POST)
        public Goods addGoods(@RequestParam String goodsName,
        				@RequestParam String goodsType,
                        @RequestParam String goodsPrice,
                        @RequestParam String goodsCount,
                        @RequestParam String goodsImage,
                        @RequestParam String publisher,
                        @RequestParam String author,
                        @RequestParam String pubDate,
                        @RequestParam String pritime,
                        HttpServletRequest request) {
                Goods goods = new Goods();
                User seller = getCurrentUser(request);
                goods.setGoodsName(goodsName);
                goods.setGoodsType(goodsType);
                goods.setGoodsPrice(goodsPrice);
                goods.setGoodsCount(goodsCount);
                goods.setGoodsImage(goodsImage);
                goods.setPublisher(publisher);
                goods.setAuthor(author);
                goods.setPubDate(pubDate);
                goods.setPritime(pritime);
                goods.setSeller(seller);
                return goodsService.save(goods);
        }

        @RequestMapping(value = "/goods/b/{buyerId}")
        public Page<Goods> getGoodsByUserID(@PathVariable Integer buyerId, @RequestParam(defaultValue="0") int page) {
                return goodsService.findAllByBuyerId(buyerId, page);
        }

        @RequestMapping(value = "/goods/s/{sellerId}")
        public Page<Goods> getGoodsBySellerID(@PathVariable Integer sellerId, @RequestParam(defaultValue="0") int page) {
                return goodsService.findAllBySellerId(sellerId, page);
        }
        
        @RequestMapping("/feeds/{page}")
        public Page<Article> getFeeds(@PathVariable int page) {
                return articleService.getFeeds(page);
        }

        @RequestMapping("/feeds")
        public Page<Article> getFeeds() {
                return getFeeds(0);
        }

        @RequestMapping("/article/{article_id}/comments/{page}")
        public Page<Comment> getCommentsOfArticle(@PathVariable int article_id, @PathVariable int page) {
                return commentService.findCommentOfArticle(article_id, page);
        }

        @RequestMapping("/article/{article_id}/comments")
        public Page<Comment> getCommentsOfArticle(@PathVariable int article_id) {
                return commentService.findCommentOfArticle(article_id, 0);
        }

        @RequestMapping("/comments")
        public Page<Comment> getCommentsOfAuthor(@RequestParam(defaultValue = "0") int page,
                        HttpServletRequest request) {
                User me = getCurrentUser(request);
                return commentService.findAllOfAuthorId(me.getId(), page);
        }

        @RequestMapping("/mycomments")
        public Page<Comment> getCommentOfUser(@RequestParam(defaultValue = "0") int page, HttpServletRequest request) {
                User me = getCurrentUser(request);
                return commentService.findAllOfUserId(me.getId(), page);
        }

        @RequestMapping(value = "/article/{article_id}/comments", method = RequestMethod.POST)
        public Comment postComment(@RequestParam String text, @PathVariable int article_id,
                        HttpServletRequest request) {
                User me = getCurrentUser(request);
                Article article = articleService.findOne(article_id);
                Comment comment = new Comment();
                comment.setAuthor(me);
                comment.setArticle(article);
                comment.setText(text);
                return commentService.save(comment);
        }

        @RequestMapping("/article/{article_id}/likes")
        public int countLikes(@PathVariable int article_id) {
                return likesService.countLikes(article_id);
        }

        @RequestMapping("/article/{article_id}/isliked")
        public boolean checkLiked(@PathVariable int article_id, HttpServletRequest request) {
                User me = getCurrentUser(request);
                return likesService.checkLiked(me.getId(), article_id);
        }

        @RequestMapping(value = "/article/{article_id}/likes", method = RequestMethod.POST)
        public int changeLikes(@PathVariable int article_id, @RequestParam boolean likes, HttpServletRequest request) {
                User me = getCurrentUser(request);
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

        // 生成订单
        @RequestMapping(value = "/goods/{goods_id}/orders", method = RequestMethod.POST)
        public Orders addOrders(@RequestParam int ordersID, @RequestParam int ordersState,
                        @RequestParam String goodsQTY, @RequestParam String goodsSum, @RequestParam String buyerName,
                        @RequestParam String buyerPhoneNum, @RequestParam String buyerAddress,
                        @RequestParam String paySum, @PathVariable int goods_id, HttpServletRequest request) {

                User me = getCurrentUser(request);
                Goods goods = goodsService.findById(goods_id);
                Orders orders = new Orders();
                if (ordersState == 0) {
                        orders.setOrdersID(ordersID);
                        orders.setBuyer(me);
                        orders.setBuyerName(buyerName);
                        orders.setBuyerPhoneNum(buyerPhoneNum);
                        orders.setBuyerAddress(buyerAddress);
                        orders.setGoods(goods);
                        orders.setGoodsQTY(goodsQTY);
                        orders.setGoodsSum(goodsSum);
                        return ordersService.save(orders);
                }
                if (ordersState == 1) {
                        orders.setPaySum(paySum);
                        return ordersService.save(orders);
                } else {
                        return null;
                }
        }

        @RequestMapping("/ordersOfSeller")
        public Page<Orders> getOrdersOfSeller(@RequestParam(defaultValue = "0") int page, HttpServletRequest request) {
                User me = getCurrentUser(request);
                return ordersService.findAllBySellerId(me.getId(), page);
        }

        @RequestMapping("/ordersOfBuyer")
        public Page<Orders> getOrdersOfBuyer(@RequestParam(defaultValue = "0") int page, HttpServletRequest request) {
                User me = getCurrentUser(request);
                return ordersService.findAllByBuyerId(me.getId(), page);
        }

        @RequestMapping("/deleteOrders")
        public void deleteOrders(@RequestParam int ordersID , HttpServletRequest request) {
                Orders orders = ordersService.findOrdersByOrdersID(ordersID);
                ordersService.deleteOrders(orders);
        }
        
}
