package com.cloudage.membercenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;

import com.cloudage.membercenter.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User extends BaseEntity {
	String account;
	String passwordHash;
	String payPassword;
	double money;
	String email;
	String name;
	String avatar;
	String phoneNum;
	String address;

	String isStore;

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	@Column(nullable = false)
	public String getPhoneNum() {
		return phoneNum;
	}

	@Column(nullable = false)
	public String getAddress() {
		return address;
	}

	@Column(unique = true,nullable = false)
	public String getAccount() {
		return account;
	}

	@Column(nullable = false)
	@JsonIgnore
	public String getPasswordHash() {
		return passwordHash;
	}

	@Column(unique = true)
	public String getName() {
		return name;
	}

	@Column(nullable = true)
	public String getAvatar() {
		return avatar;
	}

	@Column(nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Column(nullable = false)
	public String getIsStore() {
		return isStore;
	}

	public void setIsStore(String isStore) {
		this.isStore = isStore;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
