package com.cloudage.membercenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudage.membercenter.entity.CommomInfo;
import com.cloudage.membercenter.repository.ICommomInfoRepository;

@Component
@Service
@Transactional
public class DefaultCommomInfoService implements ICommomInfoService{
	@Autowired
	ICommomInfoRepository commomInfoRepo;

	@Override
	public CommomInfo save(CommomInfo commomInfo) {
		return commomInfoRepo.save(commomInfo);
	}

	@Override
	public CommomInfo findDefaultOfUser(int userId) {
		return commomInfoRepo.findDefaultCommomInfoOfUser(userId);
	}

	@Override
	public Page<CommomInfo> findAllOfUser(int userId, int page) {
		Sort sort = new Sort(Direction.ASC, "createDate");
        PageRequest pageRequest = new PageRequest(page, 6, sort);
		return commomInfoRepo.findAllOfUser(userId, pageRequest);
	}

	@Override
	public CommomInfo findCommomInfoByID(int commomInfoId) {
		return commomInfoRepo.findOne(commomInfoId);
	}

	@Override
	public void deleteCommomInfo(int infoId) {
		commomInfoRepo.delete(infoId);
	}
}
