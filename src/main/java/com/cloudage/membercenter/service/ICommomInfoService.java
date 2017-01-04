package com.cloudage.membercenter.service;

import org.springframework.data.domain.Page;

import com.cloudage.membercenter.entity.CommomInfo;

public interface ICommomInfoService {
	CommomInfo save(CommomInfo commomInfo);
	CommomInfo findDefaultOfUser(int userId);
	CommomInfo findCommomInfoByID(int commomInfoId);
	Page<CommomInfo> findAllOfUser(int userId, int page);
	void deleteCommomInfo(int infoId);
}
