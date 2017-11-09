package com.wtwd.gms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtwd.gms.dao.WtRoleInfoDao;
import com.wtwd.gms.entity.WtRoleInfo;
import com.wtwd.gms.service.WtRoleInfoService;
@Service
public class WtRoleInfoServiceImpl implements WtRoleInfoService{

	@Autowired
	WtRoleInfoDao wtRoleInfoDao;
	@Override
	public void insertRoleInfo(WtRoleInfo wtRoleInfo) {
		// TODO Auto-generated method stub
		wtRoleInfoDao.insert(wtRoleInfo);
	}
	@Override
	public WtRoleInfo findRoleInfoByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return wtRoleInfoDao.findRoleInfoByRoleName(roleName);
	}
	@Override
	public void delRoleInfo(Long id) {
		// TODO Auto-generated method stub
		wtRoleInfoDao.delete(id);
	}

	
}
