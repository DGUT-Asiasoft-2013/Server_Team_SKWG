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
import com.cloudage.membercenter.entity.Shop;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IBookCommentService;
import com.cloudage.membercenter.service.ICommentService;
import com.cloudage.membercenter.service.IGoodsService;
import com.cloudage.membercenter.service.IShopService;

@RestController
@RequestMapping("/api")
public class GoodsController {
	@Autowired
	IGoodsService goodsService;

	@Autowired
	UserController userController;

	@Autowired 
	IBookCommentService bookCommentService;

	@Autowired
	ShopController shopController;

	@RequestMapping(value = "/goods", method = RequestMethod.POST)
	public Goods addGoods(@RequestParam String goodsName,
			@RequestParam String goodsType,
			@RequestParam double goodsPrice,
			@RequestParam int goodsCount,
			@RequestParam String publisher,
			@RequestParam String author,
			@RequestParam String pubDate,
			@RequestParam String pritime,
			MultipartFile goodsImage,
			HttpServletRequest request) {
		Goods goods = new Goods();
		User seller = userController.getCurrentUser(request);
		Shop shop = shopController.findByUserId(request);
		goods.setGoodsName(goodsName);
		goods.setGoodsType(goodsType);
		goods.setGoodsPrice(goodsPrice);
		goods.setGoodsCount(goodsCount);
		goods.setGoodsSales(0); 				//新上架商品时初始销量为0
		goods.setOnSale(true);
		goods.setPublisher(publisher);
		goods.setAuthor(author);
		goods.setPubDate(pubDate);
		goods.setPritime(pritime);
		goods.setSeller(seller);
		goods.setShop(shop);
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
	//获取所有商品
	@RequestMapping(value = "/goods/s")
	public Page<Goods> getGoodsBySellerID( 
			@RequestParam(defaultValue="0") int page
			) {
		return goodsService.findAll(page);
	}
//	@RequestMapping(value = "/goods/s/{sort_style}")
//	public Page<Goods> getGoodsBySellerID( 
//			@RequestParam(defaultValue="0") int page,
//			@PathVariable String sort_style
//			) {
//		return goodsService.findAllWithSortStyle(sort_style,page);
//	}


	// 通过店铺id查询商品
	@RequestMapping(value = "/goods/get/{shop_id}")
	public Page<Goods> getGoodsByShopId(@PathVariable int shop_id,@RequestParam(defaultValue = "0") int page) {
		return goodsService.findAllByShopId(shop_id, page);
	}

	//返回当前用户店铺的所有商品
	@RequestMapping("/goods/mygoods")
	public Page<Goods> searchGoodsOfMine(HttpServletRequest request,
			@RequestParam(defaultValue="0") int page){
		Shop shop=shopController.findByUserId(request);
		return goodsService.findAllByShopId(shop.getId(),page);
	}

	//返回当前用户店铺已上架的商品
	@RequestMapping("/goods/mygoods/onsale")
	public Page<Goods> searchOnSaleGoodsOfMine(HttpServletRequest request,
			@RequestParam(defaultValue="0") int page){
		Shop shop=shopController.findByUserId(request);
		return goodsService.findAllOnSaleByShopId(shop.getId(),page);
	}
	
	//返回当前用户店铺已下架的商品
		@RequestMapping("/goods/mygoods/offsale")
		public Page<Goods> searchOffSaleGoodsOfMine(HttpServletRequest request,
				@RequestParam(defaultValue="0") int page){
			Shop shop=shopController.findByUserId(request);
			return goodsService.findAllOffSaleByShopId(shop.getId(),page);
		}
	

	//商品搜索
	@RequestMapping("/goods/search/{keyword}")
	public Page<Goods> searchGoodsByKeyword(@PathVariable String keyword,
			@RequestParam(defaultValue="0") int page){

		return goodsService.searchGoodsByKeyword(keyword,page);
	}
	//商品搜索(价格范围)
	@RequestMapping("/goods/search/{minprice}/{maxprice}")
	public Page<Goods> searchGoodsBetweenMinAndMax(@PathVariable double minprice,
			@PathVariable double maxprice,
			@RequestParam(defaultValue="0") int page){

		return goodsService.searchGoodsBetweenMinAndMax(minprice,maxprice,page);
	}
	//商品排序
	@RequestMapping("/goods/sort/{sortStyle}")
	public Page<Goods> sortGoods(
			@PathVariable String sortStyle,
			@RequestParam(defaultValue="0") int page){

		return goodsService.sortGoodsBySortStyle(sortStyle,page);
	}
	//商品分类
	@RequestMapping("/goods/classify/{type}")
	public Page<Goods> classifyGoods(
			@PathVariable String type,
			@RequestParam(defaultValue="0") int page){

		return goodsService.classifyGoodsByType(type,page);
	}
	//商品查找+分类
	@RequestMapping("/goods/search/{keyword}/classify/{goodsType}")
	public Page<Goods> searchAndClassify(@PathVariable String keyword,
			@PathVariable String goodsType,
			@RequestParam(defaultValue="0") int page){
		return goodsService.searchAndClassify(keyword,goodsType,page);
	}
	//查找+排序
	@RequestMapping("/goods/search/{keyword}/sort/{sortStyle}")
	public Page<Goods> searchAndSort(@PathVariable String keyword,
			@PathVariable String sortStyle,
			@RequestParam(defaultValue="0") int page){
		return goodsService.searchAndSort(keyword,sortStyle,page);
	}
	//分类+排序
	@RequestMapping("/goods/classify/{goodsType}/sort/{sortStyle}")
	public Page<Goods> classifyAndSort(@PathVariable String goodsType,
			@PathVariable String sortStyle,
			@RequestParam(defaultValue="0") int page){
		return goodsService.classifyAndSort(goodsType,sortStyle,page);
	}
	//商品查找+分类+排序
	@RequestMapping("/goods/search/{keyword}/classify/{goodsType}/sort/{sortStyle}")
	public Page<Goods> searchAndClassify(@PathVariable String keyword,
			@PathVariable String goodsType,
			@PathVariable String sortStyle,
			@RequestParam(defaultValue="0") int page){
		return goodsService.searchAndClassifyAndSort(keyword,goodsType,sortStyle,page);
	}
	

	//商品评论
	@RequestMapping("/goods/{goods_id}/comments")
	public Page<BookComment> getCommentByGoodsId(
			@PathVariable int goods_id,
			@RequestParam(defaultValue="0") int page){
		return bookCommentService.findAllCommentsByBookId(goods_id,page);
	}

	@RequestMapping(value="/goods/{goods_id}/addcomments", method = RequestMethod.POST)
	public void addComment(@PathVariable int goods_id, @RequestParam String commentText,
			HttpServletRequest request) {
		BookComment comment = new BookComment();
		Goods goods = goodsService.findById(goods_id);
		User me = userController.getCurrentUser(request);
		comment.setAuthor(me);
		comment.setText(commentText);
		comment.setGoods(goods);
		bookCommentService.save(comment);
	}

	@RequestMapping("/goods/setonsale/{goods_id}")
	public void changeOnSaleState(@PathVariable int goods_id, @RequestParam boolean state) {
		Goods goods = goodsService.findById(goods_id);
		goods.setOnSale(state);
		goodsService.save(goods);
	}
	
	

	//	@RequestMapping("/goods/{goods_name}/comments")
	//	public Page<BookComment> getCommentByGoodsName(
	//			@PathVariable String  goods_name,
	//			@RequestParam(defaultValue="0") int page){
	//		return bookCommentService.findAllCommentsByBookName(goods_name,page);
	//	}
}
