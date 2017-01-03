package com.cloudage.membercenter.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudage.membercenter.entity.Chat;
import com.cloudage.membercenter.entity.User;
import com.cloudage.membercenter.service.IChatService;
import com.cloudage.membercenter.service.IUserService;

@RestController
@RequestMapping("/api")
public class ChatController {
        @Autowired
        IUserService userService;
        
        @Autowired
        IChatService chatService;
        
        @Autowired
        UserController userController;
        
        @RequestMapping(value="/chat", method =RequestMethod.POST)
        public Chat saveChat(@RequestParam int receiverId,
                        @RequestParam String content,
                        HttpServletRequest request){
                User me = userController.getCurrentUser(request);
                User receiver = userService.findById(receiverId);
                Chat chat = new Chat();
                chat.setSender(me);
                chat.setReceiver(receiver);
                chat.setContent(content);
                return chatService.save(chat);
        }
        
        @RequestMapping("/findchat/{receiverId}")
        public Page<Chat> getChatById(@PathVariable int receiverId,
                        @RequestParam(defaultValue = "0") int page,
                        HttpServletRequest request){
                User me = userController.getCurrentUser(request);
                return chatService.findChatById(me.getId(), receiverId, page);
        }
        
        @RequestMapping("/findmychat")
        public Page<Chat> getChatByUserId(@RequestParam(defaultValue = "0") int page,
                        HttpServletRequest request){
                User me = userController.getCurrentUser(request);
                return chatService.findChatByUserId(me.getId(), page);
        }
        
}
