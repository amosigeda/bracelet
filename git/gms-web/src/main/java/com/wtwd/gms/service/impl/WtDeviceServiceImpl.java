package com.wtwd.gms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtwd.gms.dao.WtDeviceDao;
import com.wtwd.gms.entity.WtDevice;
import com.wtwd.gms.service.WtDeviceService;
@Service
public class WtDeviceServiceImpl implements WtDeviceService{

	@Autowired
	private WtDeviceDao wtDeviceDao;
	@Override
	public List<WtDevice> queryAllDepartmentInfo() {
		// TODO Auto-generated method stub
		return wtDeviceDao.queryAllDepartmentInfo();
	}
	@Override
	public void insertDeviceIfNoExist(String devName) {
		// TODO Auto-generated method stub
		WtDevice device = wtDeviceDao.queryDeviceByDeviceName(devName);
		if(device == null){
			WtDevice newDevice = new WtDevice();
			newDevice.setDevName(devName);
			wtDeviceDao.insert(newDevice);
		}
	}

}
