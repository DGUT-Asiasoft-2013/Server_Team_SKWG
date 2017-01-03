package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Chat;

public interface IChatService {
        Chat save(Chat chat);
        Page<Chat> findChatById(int senderId, int receiverId, int page);
        Page<Chat> findChatByUserId(int userId, int page);
}
