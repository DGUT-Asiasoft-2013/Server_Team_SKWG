package com.cloudage.membercenter.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.CommomInfo;
import com.cloudage.membercenter.repository.ICommomInfoRepository;

@Component
@Service
@Transactional
public class DefaultCommomInfoService implements ICommomInfoService{
	ICommomInfoRepository commomInfoRepo;

	@Override
	public CommomInfo Save(CommomInfo commomInfo) {
		return commomInfoRepo.save(commomInfo);
	}
}
