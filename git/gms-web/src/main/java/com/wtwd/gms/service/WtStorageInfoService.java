package com.wtwd.gms.service;

import java.util.List;
import java.util.Map;

import com.wtwd.gms.entity.WtStorageInfo;

public interface WtStorageInfoService {

	/**
	 * 查询设备入库记录
	 */
	public List<WtStorageInfo> listBy(WtStorageInfo wsi);
	
	/**
	 * 获取所有数据
	 */
	public List<WtStorageInfo> queryDataInfo();

	//public void insertStorageInfo(String deviceName,String opertor,String status);
	
	public List<WtStorageInfo> queryStorageInfoByWorkerName(String worker);
	
	public List<WtStorageInfo> queryStorageInfoByCondition(Map<String, Object> param);
}
