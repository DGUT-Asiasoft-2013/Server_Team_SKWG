package com.cloudage.membercenter.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudage.membercenter.entity.Article;
import com.cloudage.membercenter.entity.Comment;
import com.cloudage.membercenter.entity.Goods;
import com.cloudage.membercenter.entity.Likes;
import com.cloudage.membercenter.entity.Orders;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.repository.ILikesRepository;
import com.cloudage.membercenter.service.IArticleService;
import com.cloudage.membercenter.service.ICommentService;
import com.cloudage.membercenter.service.IGoodsService;
import com.cloudage.membercenter.service.ILikesService;
import com.cloudage.membercenter.service.IOrdersService;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/api")
public class UserController {

        @Autowired
		IUserService userService;

        @RequestMapping(value = "/hello", method = RequestMethod.GET)
        public @ResponseBody String hello() {
                return "HELLO WORLD";
        }

        @RequestMapping(value = "/register", method = RequestMethod.POST)
    	public User register(@RequestParam String account, @RequestParam String passwordHash, @RequestParam String email,
    			@RequestParam String name, @RequestParam String address, @RequestParam String phoneNum,
    			MultipartFile avatar, HttpServletRequest request) {
        	//判断用户是否存在
//        	User isuser = userService.findByAccount(account);
//        	User ismail = userService.findByEmail(email);
//        	if(isuser!=null||ismail!=null){
//        		return null;
//        	}
    		User user = new User();
    		user.setAccount(account);
    		user.setEmail(email);
    		user.setPasswordHash(passwordHash);
    		user.setName(name);
    		user.setAddress(address);
    		user.setPhoneNum(phoneNum);
    		user.setIsStore("0");

    		if (avatar != null) {
    			try {
    				String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
    				FileUtils.copyInputStreamToFile(avatar.getInputStream(), new File(realPath, account + ".png"));
    				user.setAvatar("upload/" + account + ".png");
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}

    		return userService.save(user);
    	}
        @RequestMapping(value = "/login", method = RequestMethod.POST)
        public User login(@RequestParam String account, @RequestParam String passwordHash, HttpServletRequest request) {

                User user = userService.findByAccount(account);
                if (user != null && user.getPasswordHash().equals(passwordHash)) {
                        HttpSession session = request.getSession(true);
                        session.setAttribute("uid", user.getId());
                        return user;
                } else {
                        return null;
                }
        }

        @RequestMapping(value = "/change", method = RequestMethod.POST)
        public boolean changePassword(@RequestParam String passwordHash, @RequestParam String newPasswordHash,
                        HttpServletRequest request) {
                User user = getCurrentUser(request);
                if (user.getPasswordHash().equals(passwordHash)) {
                        user.setPasswordHash(newPasswordHash);
                        userService.save(user);
                        return true;
                } else {
                        return false;
                }
        }

        @RequestMapping(value = "/me", method = RequestMethod.GET)
        public  User getCurrentUser(HttpServletRequest request) {
                HttpSession session = request.getSession(true);
                Integer uid = (Integer) session.getAttribute("uid");
                return userService.findById(uid);
        }

        @RequestMapping(value = "/recover", method = RequestMethod.POST)
        public boolean passwordRecover(@RequestParam String email, @RequestParam String passwordHash,
                        HttpServletRequest request) {
                User user = userService.findByEmail(email);
                if (user == null) {
                        return false;
                } else {
                        user.setPasswordHash(passwordHash);
                        userService.save(user);
                        return true;
                }
        }
        
        @RequestMapping(value = "/becomeshop", method = RequestMethod.POST)
        public void becomeShop(HttpServletRequest request) {
        	User user = getCurrentUser(request);
        	user.setIsStore("1");
        	System.out.println("g");
        	userService.save(user);
        }

        @RequestMapping(value = "/exit")
       public void exitServer(HttpServletRequest request){
        	User me = getCurrentUser(request);
        	if(me!=null){
        	request.getSession(true).removeAttribute("uid");
        	}
        }

        @RequestMapping(value = "/changeMessage",method = RequestMethod.POST)
        public User changeMessage(@RequestParam String type,@RequestParam String value,
        		HttpServletRequest request){
        	User me = getCurrentUser(request);
        	
        	if(type.equals("changeName")){
        		me.setName(value);
    		}else if(type.equals("changeEmail")){
    			me.setEmail(value);
    		}else if(type.equals("changePhone")){
    			me.setPhoneNum(value);
    		}else if(type.equals("changeAddress")){
    			me.setAddress(value);
    		}
        	return userService.save(me);
        }
        
        //设置支付密码
        @RequestMapping(value="user/setPayPassword",method=RequestMethod.POST)
        public boolean setPayPassword(HttpServletRequest request,
        		@RequestParam String payPassword){
        	User me=getCurrentUser(request);
        	if(me.getPayPassword()!=null){
        		me.setPayPassword(payPassword);
        		userService.save(me);
        		return true;
       	}
        	else{
        		return false;
        	}
        }
        
        //验证支付密码是否正确
        @RequestMapping(value = "/payPassword", method = RequestMethod.POST)
        public boolean ensurePayPassword(@RequestParam String payPassword,
        		HttpServletRequest request) {
        	User user =getCurrentUser(request);
        	if (user != null && user.getPayPassword().equals(payPassword)) {
        		return true;
        	} else {
        		return false;
        	}
        }

}
