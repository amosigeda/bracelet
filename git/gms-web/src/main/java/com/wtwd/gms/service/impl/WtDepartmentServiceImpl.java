package com.wtwd.gms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtwd.gms.dao.WtDepartmentDao;
import com.wtwd.gms.entity.WtDepartmentInfo;
import com.wtwd.gms.service.WtDepartmentInfoService;
@Service
public class WtDepartmentServiceImpl implements WtDepartmentInfoService{
	
	@Autowired
	private WtDepartmentDao wtDepartmentDao;
	@Override
	public List<WtDepartmentInfo> queryAllDepartmentInfo() {
		// TODO Auto-generated method stub
		return wtDepartmentDao.queryAllDepartmentInfo();
	}

}
