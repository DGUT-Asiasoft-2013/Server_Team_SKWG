package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Shop;
import com.cloudage.membercenter.entity.Subscribe;
import com.cloudage.membercenter.entity.User;

public interface ISubscribeService {

        void addSubscribe(User user, Shop shop);
        void removeSubscribe(User user, Shop shop);
        int countSubscribe(int shopId);
        boolean checkSubscribed(int userId, int shopId);
        Page<Subscribe> findSubscribeByShopId(int shopId, int page);
        Page<Subscribe> findSubscribeByUserId(int userId, int page);
}
