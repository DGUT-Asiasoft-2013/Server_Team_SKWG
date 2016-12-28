package com.cloudage.membercenter.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cloudage.membercenter.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bill extends BaseEntity {

	User user; // 账单对应用户
	UUID billNumber; // 账单流水号
	int billState; // 收支类型		1.收入    0.支出
	Date createDate; // 创建时间
	String detial; // 收支备注
	Double item;   //收支金额
	Double money; // 余额


	@Column(nullable = false)
	public Double getItem() {
		return item;
	}

	@ManyToOne(optional = false)
	@JsonIgnore
	public User getUser() {
		return user;
	}

	@Column(updatable = false)
	public UUID getBillNumber() {
		return billNumber;
	}

	@Column(nullable = false)
	public int getBillState() {
		return billState;
	}

	@Column(updatable = false)
	public Date getCreateDate() {
		return createDate;
	}

	@Column(nullable = false)
	public String getDetial() {
		return detial;
	}

	@Column(nullable = false)
	public Double getMoney() {
		return money;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setBillNumber(UUID billNumber) {
		this.billNumber = billNumber;
	}

	public void setBillState(int billState) {
		this.billState = billState;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setDetial(String detial) {
		this.detial = detial;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public void setItem(Double item) {
		this.item = item;
	}
	// 自动写入时间
	@PrePersist
	void onPrePersist() {
		createDate = new Date();
		billNumber = UUID.randomUUID();
	}

}
