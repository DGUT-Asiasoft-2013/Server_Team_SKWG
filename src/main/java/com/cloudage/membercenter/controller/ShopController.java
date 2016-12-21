package com.cloudage.membercenter.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudage.membercenter.entity.Shop;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IShopService;


@RestController
@RequestMapping("/api")
public class ShopController {
	@Autowired
	IShopService shopServier;
	@Autowired
	UserController userController;
	
	@RequestMapping(value = "/openshop", method = RequestMethod.POST)
	public Shop openShop(@RequestParam String shopName,
			@RequestParam String description,
			MultipartFile shopImage,
			HttpServletRequest request
			) {
		Shop shop = new Shop();
		User owner = userController.getCurrentUser(request);
		shop.setShopName(shopName);
		shop.setDescription(description);
		shop.setOwner(owner);
		if (shopImage != null) {
			try {
				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
				FileUtils.copyInputStreamToFile(shopImage.getInputStream(), new File(realPath, shopName + ".png"));
				shop.setShopImage("upload/" + shopName + ".png");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return shopServier.save(shop);
	}
}
