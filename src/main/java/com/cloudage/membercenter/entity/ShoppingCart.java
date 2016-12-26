package com.cloudage.membercenter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import org.springframework.data.annotation.Persistent;

import com.cloudage.membercenter.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class ShoppingCart {
	@Embeddable
	public static class Key implements Serializable {
		User buyer;
		Goods goods;
		@JsonIgnore
		@ManyToOne(optional=false)
		public User getBuyer() {
			return buyer;
		}
		public void setBuyer(User buyer) {
			this.buyer = buyer;
		}
		@JsonIgnore
		@ManyToOne(optional=false)
		public Goods getGoods() {
			return goods;
		}
		public void setGoods(Goods goods) {
			this.goods = goods;
		}
	}

	Key id;
	int quantity;	// 数量
	Date createDate;
	
	@EmbeddedId
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	@Column(updatable=false)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@PrePersist
	void onPrePersist() {
		createDate = new Date();
	}
	
	
}
