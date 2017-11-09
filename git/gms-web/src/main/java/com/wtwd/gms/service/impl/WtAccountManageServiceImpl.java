package com.wtwd.gms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtwd.gms.dao.WtAccountManageDao;
import com.wtwd.gms.entity.WtAccountManage;
import com.wtwd.gms.service.WtAccountManageService;
@Service
public class WtAccountManageServiceImpl implements WtAccountManageService{
	@Autowired
	private WtAccountManageDao wtAccountManageDao;
	@Override
	public int insertWtAccountManage(WtAccountManage wtAccountManage) {
		// TODO Auto-generated method stub
		System.out.println("666666666666");
		int result = wtAccountManageDao.insertWtAccountManage(wtAccountManage);
		return result;
	}
	@Override
	public List<WtAccountManage> queryWtAccountManagesInfo() {
		// TODO Auto-generated method stub
		return wtAccountManageDao.queryAllWtAccountManageInfo();
	}

}
