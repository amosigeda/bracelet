package com.wtwd.gms.dao;

import java.util.List;

import com.wtwd.gms.entity.WtDevice;

public interface WtDeviceDao extends BaseDao<WtDevice>{
	
	List<WtDevice> queryAllDepartmentInfo();
	
	WtDevice queryDeviceByDeviceName(String devName);
}
