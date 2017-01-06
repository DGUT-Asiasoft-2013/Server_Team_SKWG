package com.cloudage.membercenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.cloudage.membercenter.util.DateRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Goods extends DateRecord {
	String goodsName;
	String goodsType;
	double goodsPrice;
	int goodsCount;
	int goodsSales; // 商品销量
	String goodsImage;
	String publisher;
	String author;
	String pubDate;
	String pritime;
	User seller;
	Shop shop;

	@Column(nullable = false)
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(nullable = false)
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public int getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getPritime() {
		return pritime;
	}

	public void setPritime(String pritime) {
		this.pritime = pritime;
	}

	@ManyToOne(optional = false)
	@JsonIgnore
	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	@ManyToOne(optional = false)
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	@Column(nullable = true)
	public int getGoodsSales() {
		return goodsSales;
	}

	public void setGoodsSales(int goodsSales) {
		this.goodsSales = goodsSales;
	}

}
