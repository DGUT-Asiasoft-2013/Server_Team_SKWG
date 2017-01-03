package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.Push;

public interface IPushService {
        Push save(Push push);
        Page<Push> findPushByUserId(int userId, int page);
}
