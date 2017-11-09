package com.wtwd.gms.service;

import java.util.List;

import com.wtwd.gms.entity.WtDevice;

public interface WtDeviceService {
	
	List<WtDevice> queryAllDepartmentInfo();
	
	void insertDeviceIfNoExist(String devName);
	
	
}
