package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.Chat;
import com.cloudage.membercenter.repository.IChatRepository;

@Component
@Service
@Transactional
public class DefaultChatService implements IChatService{
        
        @Autowired
        IChatRepository chatRepo;

        @Override
        public Chat save(Chat chat) {
                return chatRepo.save(chat);
        }

        @Override
        public Page<Chat> findChatById(int senderId, int receiverId, int page) {
                Sort sort = new Sort(Direction.ASC, "createDate");
                PageRequest pageRequest = new PageRequest(page, 100, sort);
                return chatRepo.findChatById(senderId, receiverId, pageRequest);
        }

        @Override
        public Page<Chat> findChatByUserId(int userId, int page) {
                Sort sort = new Sort(Direction.DESC, "createDate");
                PageRequest pageRequest = new PageRequest(page, 100, sort);
                return chatRepo.findChatByUserId(userId, pageRequest);
        }
}
