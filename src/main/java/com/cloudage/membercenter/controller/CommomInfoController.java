package com.cloudage.membercenter.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudage.membercenter.entity.CommomInfo;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.ICommomInfoService;

@RestController
@RequestMapping("/api")
public class CommomInfoController {
	@Autowired
	ICommomInfoService commomInfoService;
	@Autowired
	UserController userController;
	@RequestMapping(value="/commominfo")
	public CommomInfo addCommomInfo(@RequestParam String name, @RequestParam String adress,
			@RequestParam String tel, @RequestParam String postCode,
			HttpServletRequest request) {
		CommomInfo commomInfo = new CommomInfo();
		User me = userController.getCurrentUser(request);
		commomInfo.setName(name);
		commomInfo.setAdress(adress);
		commomInfo.setTel(tel);
		commomInfo.setPostCode(postCode);
		commomInfo.setUser(me);
		return commomInfoService.Save(commomInfo);
	}
}
