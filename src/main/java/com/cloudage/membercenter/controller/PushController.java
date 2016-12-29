package com.cloudage.membercenter.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudage.membercenter.entity.Push;
import com.cloudage.membercenter.entity.Shop;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IPushService;
import com.cloudage.membercenter.service.IShopService;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/api")
public class PushController {

        @Autowired
        IPushService pushService;
        
        @Autowired
        UserController userController;
        
        @Autowired
        IUserService userService;
        
        @Autowired
        IShopService shopService;
        
        @RequestMapping(value="/push", method=RequestMethod.POST)
        public Push savePush(@RequestParam int shopId, @RequestParam int receiverId, @RequestParam String content){
                Shop shop = shopService.findOne(shopId);
                User receiver = userService.findById(receiverId);
                Push push = new Push();
                push.setShop(shop);
                push.setReceiver(receiver);
                push.setContent(content);
                return pushService.save(push);
        }
        
        @RequestMapping("/findpush/{userId}")
        public Page<Push> getPushByUserId(@RequestParam(defaultValue = "0") int page, HttpServletRequest request){
                User me = userController.getCurrentUser(request);
                return pushService.findPushByUserId(me.getId(), page);
        }
}
