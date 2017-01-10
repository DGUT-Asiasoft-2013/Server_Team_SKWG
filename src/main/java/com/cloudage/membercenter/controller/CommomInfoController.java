package com.cloudage.membercenter.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudage.membercenter.entity.CommomInfo;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.ICommomInfoService;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

@RestController
@RequestMapping("/api")
public class CommomInfoController {
	@Autowired
	ICommomInfoService commomInfoService;
	@Autowired
	UserController userController;
	
	@RequestMapping(value="/commominfo/add")
	public CommomInfo addCommomInfo(@RequestParam String name, @RequestParam String address,
			@RequestParam String tel, @RequestParam String postCode,
			HttpServletRequest request) {
		CommomInfo commomInfo = new CommomInfo();
		User me = userController.getCurrentUser(request);
		commomInfo.setName(name);
		commomInfo.setAddress(address);
		commomInfo.setTel(tel);
		commomInfo.setPostCode(postCode);
		commomInfo.setUser(me);
		commomInfo.setDefaultInfo(false);
		return commomInfoService.save(commomInfo);
	}
	@RequestMapping(value="/commominfo/change/{info_id}")
	public CommomInfo editCommomInfo(@PathVariable int info_id,
			@RequestParam String name, @RequestParam String address,
			@RequestParam String tel, @RequestParam String postCode
			) {
		CommomInfo commomInfo = commomInfoService.findCommomInfoByID(info_id);
		commomInfo.setName(name);
		commomInfo.setAddress(address);
		commomInfo.setTel(tel);
		commomInfo.setPostCode(postCode);
		return commomInfoService.save(commomInfo);
	}
	
	// 设置默认地址
	@RequestMapping("/commominfo/setdefault/{infoId}")
	public CommomInfo setDefault(@PathVariable int infoId, HttpServletRequest request) {
		User me = userController.getCurrentUser(request);
		CommomInfo beforeDefaut = commomInfoService.findDefaultOfUser(me.getId());
		CommomInfo newDefault = commomInfoService.findCommomInfoByID(infoId);
		if(beforeDefaut != null) {
			beforeDefaut.setDefaultInfo(false);
			commomInfoService.save(beforeDefaut);
		}
		newDefault.setDefaultInfo(true);
		commomInfoService.save(newDefault);
		return newDefault;
	}
	
	@RequestMapping("/commominfo/delete/{infoId}")
	public void deleteCommomInfo(@PathVariable int infoId) {
		commomInfoService.deleteCommomInfo(infoId);
	}
	// 返回当前用户的默认收货地址
	@RequestMapping(value="/commominfo/default")
	public CommomInfo getDefaultCommomInfoOfUser(HttpServletRequest request) {
		User me = userController.getCurrentUser(request);
		return commomInfoService.findDefaultOfUser(me.getId());
	}
	
	// 返回当前用户保存的所有收货地址
	@RequestMapping(value = "/commominfo/myinfo/{page}")
	public Page<CommomInfo> getAllOfUser(@PathVariable int page, HttpServletRequest request) {
		User me = userController.getCurrentUser(request);
		return commomInfoService.findAllOfUser(me.getId(), page);
	}
	
}
