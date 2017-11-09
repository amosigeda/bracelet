package com.wtwd.gms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtwd.gms.dao.WtDeviceDao;
import com.wtwd.gms.entity.WtDevice;
@Repository
public class WtDeviceDaoImpl extends BaseDaoImpl<WtDevice> implements  WtDeviceDao{

	@Override
	public List<WtDevice> queryAllDepartmentInfo() {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("queryDataInfo"));
	}

	@Override
	public WtDevice queryDeviceByDeviceName(String devName) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectOne(getStatement("queryDeviceByDeviceName"),devName);
	}

}
