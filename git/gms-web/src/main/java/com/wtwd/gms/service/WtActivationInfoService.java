package com.wtwd.gms.service;

import java.util.List;
import java.util.Map;

import com.wtwd.gms.entity.WtActivationInfo;
import com.wtwd.gms.entity.WtBaseCount;
import com.wtwd.gms.entity.WtDeviceDateCount;

public interface WtActivationInfoService {
	
	List<WtActivationInfo> queryAllData();
	
	List<WtActivationInfo> listBy(Map<String,Object> paraMap);
	
	List<WtDeviceDateCount> countActivationDeviceEveryDay(Map<String, Object> paramMap);
	
	List<WtBaseCount> countActivationDeviceName(Map<String,Object> paramMap);
	//计算N天活跃的国家名称
	List<WtBaseCount> countActivationCountryName(Map<String, Object> paramMap);
	
	List<WtDeviceDateCount> countActivationCountryEveryDay(Map<String,Object> paramMap);
	//计算Android,IOS设备每天活跃的数据
	List<WtDeviceDateCount> countOsEveryDay(Map<String, Object> paramMap);
	
	//计算Brand的名字
	List<WtBaseCount> countBrandName(Map<String, Object> paramMap);
	//计算某手机品牌每一天的活跃数据
	List<WtDeviceDateCount> countBrandEveryDay(Map<String, Object> paramMap);
		
	//計算手机机型的名称
	List<WtBaseCount> countModelName(Map<String, Object> paramMap);
	//计算某手机机型N天每天的活跃数据
	List<WtDeviceDateCount> countModelEveryDay(Map<String, Object> paramMap);
	//筛选不重复的数据
	
	int countActivationData(Map<String, Object> paramMap);
	
	List<WtBaseCount> distinctData(Map<String,Object> paramMap);
	
	List<WtBaseCount> getCountryMapData(Map<String, Object> paramMap);
	
	List<WtBaseCount> queryDevName(Map<String,Object> paramMap);
	
	List<WtActivationInfo> queryEquipment(Map<String,Object> paramMap);
	
	List<WtActivationInfo> queryEquipmentLimit(Map<String,Object> paramMap);
	
	List<WtBaseCount> timeTrend(Map<String, Object> paramMap);
	
	List<WtActivationInfo> queryReportSN(Map<String, Object> paramMap);
	
	int insertActivation(List<WtActivationInfo> list);
	
	int queryActivationCount(Map<String,Object> paramMap);
	
	List<WtBaseCount> countActvationCountryNum(Map<String,Object> paramMap);

	List<WtBaseCount> countActvationDeviceNum(Map<String, Object> paramMap);
}
