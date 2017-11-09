package com.wtwd.gms.service;

import java.util.List;
import java.util.Map;

import com.wtwd.gms.entity.WtActiveInfo;
import com.wtwd.gms.entity.WtBaseCount;
import com.wtwd.gms.entity.WtDeviceDateCount;
/**
 * 
 * @ClassName:       WtActiveInfoService
 * @Description:     TODO
 * @author:          wuhaihui
 * @date:            2017年7月27日        下午2:29:45
 */
public interface WtActiveInfoService {

	List<WtActiveInfo> queryAllData();
	
	Long countActive(Map<String, Object> paramMap);
	
	List<WtActiveInfo> listBy(Map<String,Object> paraMap);
	
	//List<WtDeviceDateCount> countActiveDate(Map<String, Object> paramMap);
	//返回七天活跃的机型名称
	/*List<WtBaseCount> countActiveDevice();*/
	
	List<WtDeviceDateCount> countActiveDeviceEveryDay(Map<String, Object> paramMap);
	
	List<WtBaseCount> coutActiveDeviceName(Map<String, Object> paramMap);
	
	List<WtBaseCount> countActiveCountryName(Map<String, Object> paramMap);
	
	List<WtDeviceDateCount> countActiveCountryEveryDay(Map<String,Object> paramMap);
	
	List<WtDeviceDateCount> countsOsEveryDay(Map<String,Object> paramMap);
	
	List<WtBaseCount> countBrandName(Map<String, Object> paramMap);
	
	List<WtDeviceDateCount> countBrandEveryDay(Map<String,Object> paramMap);
	
	List<WtBaseCount> countModelName(Map<String, Object> paramMap);
	
	List<WtBaseCount> countActiveData(Map<String, Object> paramMap);
	
	List<WtBaseCount> distinctData(Map<String, Object> paramMap);

	List<WtBaseCount> getCountryMapData(Map<String, Object> paramMap);
	
	List<WtBaseCount> queryDevSn(Map<String,Object> paramMap);
	
	List<WtBaseCount> queryDevName(Map<String,Object> paramMap);
	
	List<WtBaseCount> timeTrend(Map<String, Object> paramMap);
	
	int insertActive(List<WtActiveInfo> list);
	
	int queryActiveCount(Map<String,Object> paramMap);
	
	int countActiveNumber(Map<String,Object> paramMap);

	List<WtBaseCount> countActiveCountryNum(Map<String, Object> paramMap);

	List<WtBaseCount> countActiveDeviceNum(Map<String, Object> paramMap);
} 
