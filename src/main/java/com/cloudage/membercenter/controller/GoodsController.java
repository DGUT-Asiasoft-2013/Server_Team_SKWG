package com.cloudage.membercenter.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudage.membercenter.entity.BookComment;
import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IBookCommentService;
import com.cloudage.membercenter.service.ICommentService;
import com.cloudage.membercenter.service.IGoodsService;

@RestController
@RequestMapping("/api")
public class GoodsController {
	@Autowired
	IGoodsService goodsService;

	@Autowired
	UserController userController;

	@Autowired 
	IBookCommentService bookCommentService;

	@RequestMapping(value = "/goods", method = RequestMethod.POST)
	public Goods addGoods(@RequestParam String goodsName,
			@RequestParam String goodsType,
			@RequestParam String goodsPrice,
			@RequestParam String goodsCount,
			@RequestParam String publisher,
			@RequestParam String author,
			@RequestParam String pubDate,
			@RequestParam String pritime,
			MultipartFile goodsImage,
			HttpServletRequest request) {
		Goods goods = new Goods();
		User seller = userController.getCurrentUser(request);
		goods.setGoodsName(goodsName);
		goods.setGoodsType(goodsType);
		goods.setGoodsPrice(goodsPrice);
		goods.setGoodsCount(goodsCount);
		goods.setPublisher(publisher);
		goods.setAuthor(author);
		goods.setPubDate(pubDate);
		goods.setPritime(pritime);
		goods.setSeller(seller);
		if (goodsImage != null) {
			try {
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
				FileUtils.copyInputStreamToFile(goodsImage.getInputStream(), new File(realPath, goodsName + ".png"));
				goods.setGoodsImage("upload/" + goodsName + ".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return goodsService.save(goods);
	}

	@RequestMapping(value = "/goods/s/{sellerId}")
	public Page<Goods> getGoodsBySellerID(@PathVariable Integer sellerId, @RequestParam(defaultValue="0") int page) {
		return goodsService.findAllBySellerId(sellerId, page);
	}

	@RequestMapping(value = "/goods/s")
	public Page<Goods> getGoodsBySellerID( @RequestParam(defaultValue="0") int page) {
		return goodsService.findAll( page);
	}


	//商品搜索
	@RequestMapping("/goods/search/{keyword}")
	public Page<Goods> searchGoodsByKeyword(@PathVariable String keyword,
			@RequestParam(defaultValue="0") int page){

		return goodsService.searchGoodsByKeyword(keyword,page);
	}

	//书本评论
	@RequestMapping("/goods/{goods_id}/comments")
	public Page<BookComment> getCommentByGoodsId(
			@PathVariable int goods_id,
			@RequestParam(defaultValue="0") int page){
		return bookCommentService.findAllCommentsByBookId(goods_id,page);
	}
}
