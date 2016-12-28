package com.cloudage.membercenter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudage.membercenter.entity.Bill;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IBillService;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/api")
public class BillController {
	@Autowired
	IBillService billService;

	@Autowired
	UserController userController;

	//显示当前用户账单
	@RequestMapping(value = "/GetBill/{page}", method = RequestMethod.GET)
	public Page<Bill> getYourBill(@PathVariable int page,
			HttpServletRequest request) {
		User me = userController.getCurrentUser(request);
		return billService.findBillByUserID(me.getId(), page);
	}


	//添加收入
	@RequestMapping(value = "/addIncome",method = RequestMethod.POST)
	public Bill addIncomeBill(@RequestParam double item,@RequestParam String detial,
			@RequestParam int state,
			HttpServletRequest request){
		User me = userController.getCurrentUser(request);
		double money = me.getMoney();
		Bill oneBill = new Bill();
		
		oneBill.setBillState(state);
		oneBill.setDetial(detial);
		oneBill.setItem(item);
		oneBill.setMoney(money);
		oneBill.setUser(me);
		
		return oneBill;
	}

}
