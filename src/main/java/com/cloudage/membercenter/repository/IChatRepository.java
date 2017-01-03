package com.cloudage.membercenter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cloudage.membercenter.entity.Chat;

@Repository
public interface IChatRepository extends PagingAndSortingRepository<Chat, Integer>{
        
        @Query("from Chat chat where (chat.sender.id =?1 and chat.receiver.id = ?2) or (chat.receiver.id = ?1 and chat.sender.id = ?2)")
        Page<Chat> findChatById(int senderId, int receiverId, Pageable page);
        
        @Query("from Chat chat where chat.sender.id = ?1 or chat.receiver.id = ?1")
        Page<Chat> findChatByUserId(int userId, Pageable page);
}
