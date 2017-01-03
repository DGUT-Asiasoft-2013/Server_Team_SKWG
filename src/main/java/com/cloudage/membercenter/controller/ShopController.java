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

import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.entity.Shop;
import com.cloudage.membercenter.entity.Subscribe;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IGoodsService;
import com.cloudage.membercenter.service.IShopService;
import com.cloudage.membercenter.service.ISubscribeService;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/api")
public class ShopController {
        @Autowired
        IShopService shopServier;
        @Autowired
        UserController userController;
        @Autowired
        IGoodsService goodsService;
        @Autowired
        ISubscribeService subscribeService;

        @RequestMapping(value = "/openshop", method = RequestMethod.POST)
        public Shop openShop(@RequestParam String shopName, @RequestParam String description, MultipartFile shopImage,
                        HttpServletRequest request) {
                Shop shop = new Shop();
                User owner = userController.getCurrentUser(request);
                shop.setShopName(shopName);
                shop.setDescription(description);
                shop.setOwner(owner);
                if (shopImage != null) {
                        try {
                                String realPath = request.getSession().getServletContext()
                                                .getRealPath("/WEB-INF/upload");
                                FileUtils.copyInputStreamToFile(shopImage.getInputStream(),
                                                new File(realPath, shopName + ".png"));
                                shop.setShopImage("upload/" + shopName + ".png");
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
                return shopServier.save(shop);
        }

        // 返回当前用户的店铺
        @RequestMapping(value = "/shop/myshop")
        public Shop findByUserId(HttpServletRequest request) {
                User me = userController.getCurrentUser(request);
                return shopServier.findByUserId(me.getId());
        }

        @RequestMapping("/shop/{shop_id}/subscribe")
        public int countSubscribe(@PathVariable int shop_id) {
                return subscribeService.countSubscribe(shop_id);
        }

        @RequestMapping("/shop/{shop_id}/issubscribed")
        public boolean checkSubscribed(@PathVariable int shop_id, HttpServletRequest request) {
                User me = userController.getCurrentUser(request);
                return subscribeService.checkSubscribed(me.getId(), shop_id);
        }

        @RequestMapping(value = "/shop/{shop_id}/subscribe", method = RequestMethod.POST)
        public int changeSubscribe(@PathVariable int shop_id, @RequestParam boolean subscribe,
                        HttpServletRequest request) {
                User me = userController.getCurrentUser(request);
                Shop shop = shopServier.findOne(shop_id);

                if (subscribe) {
                        subscribeService.addSubscribe(me, shop);
                } else {
                        subscribeService.removeSubscribe(me, shop);
                }
                return subscribeService.countSubscribe(shop_id);
        }

        @RequestMapping("/shop/{shop_id}/findsubscribe")
        public Page<Subscribe> findSubscribeByShopId(@PathVariable int shop_id, @RequestParam(defaultValue = "0") int page){
                return subscribeService.findSubscribeByShopId(shop_id, page);
        }
        
        @RequestMapping(value = "/shop/{othersId}/findshop")
        public Shop findByOthersId(@PathVariable int othersId) {
                return shopServier.findByUserId(othersId);
        }
        
}
