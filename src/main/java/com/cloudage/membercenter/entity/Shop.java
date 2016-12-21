package com.cloudage.membercenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.cloudage.membercenter.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Shop extends BaseEntity{
	String shopName;	// 店铺名称
	String description;	// 简介
	String shopImage;	// 店铺图片
	User owner;			// 店家
	@Column(nullable=false)
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToOne(optional=false)	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	@Column(nullable=true)
	public String getShopImage() {
		return shopImage;
	}
	public void setShopImage(String shopImage) {
		this.shopImage = shopImage;
	}


}
