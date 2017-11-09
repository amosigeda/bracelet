package com.wtwd.gms.dao;

import java.util.List;
import java.util.Map;

import com.wtwd.gms.entity.WtActiveInfo;
import com.wtwd.gms.entity.WtBaseCount;
import com.wtwd.gms.entity.WtDeviceDateCount;
import com.wtwd.gms.entity.WtDeviceInfo;

/**
 * 
 * @ClassName:       WtActiveInfoDao
 * @Description:     TODO
 * @author:          wuhaihui
 * @date:            2017年7月27日        下午1:55:38
 */
public interface WtActiveInfoDao extends BaseDao<WtActiveInfo> {
   /* int deleteByPrimaryKey(Long id);

    int insert(WtActiveInfo record);

    int insertSelective(WtActiveInfo record);

    WtActiveInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WtActiveInfo record);

    int updateByPrimaryKey(WtActiveInfo record);*/
	/**
	 * @Title:           queryAllData
	 * @Description:     TODO
	 * @param:           @return   
	 * @return:          List<WtActiveInfo>   
	 * @throws
	 */
	List<WtActiveInfo> queryAllData();
	
	Long countActive(Map<String, Object> paramMap);
	//计算总数 count
	List<WtBaseCount> countActiveData(Map<String,Object> paramMap);
	//List<WtActiveInfo> listBy(Map<String,Object> paraMap);
	//拼接sql查询数据信息
	//List<WtDeviceDateCount> countActiveDate(Map<String, Object> paramMap);
	//计算七天活跃的机型
	//List<WtBaseCount> countActiveDevice();
	//计算某机型N天每天活跃的数据
	List<WtDeviceDateCount> countActiveDeviceEveryDay(Map<String, Object> paramMap);
	//计算N天活跃的机型名称
	List<WtBaseCount> countActiveDeviceName(Map<String,Object> paramMap);
	
	//计算N天活跃的国家名称
	List<WtBaseCount> countActiveCountryName(Map<String, Object> paramMap);
	//计算某国家N天每天活跃的数据
	List<WtDeviceDateCount> countActiveCountryEveryDay(Map<String,Object> paramMap);
	//计算Android,IOS设备每天活跃的数据
	List<WtDeviceDateCount> countOsEveryDay(Map<String, Object> paramMap);
	//计算Brand的名字
	List<WtBaseCount> countBrandName(Map<String, Object> paramMap);
	//计算某手机品牌每一天的活跃数据
	List<WtDeviceDateCount> countBrandEveryDay(Map<String, Object> paramMap);
	
	//計算手机机型的名称
	List<WtBaseCount> countModelName(Map<String, Object> paramMap);
	//计算某手机机型N天每天的活跃数据
	List<WtDeviceDateCount> countModelEveryDat(Map<String, Object> paramMap);
	
	//计算数量。占有率，总数
	//List<WtBaseCount> countTable(Map<String,Object> paramMap);
	
	//筛选不重复的数据
	List<WtBaseCount> distinctData(Map<String,Object> paramMap);

	List<WtBaseCount> getCountryMapData(Map<String, Object> paramMap);
	
	//List<WtDeviceInfo> distinct
	List<WtBaseCount> queryDevSn(Map<String, Object> paramMap);
	
	List<WtBaseCount> queryDevName(Map<String,Object> paramMap);
	
	List<WtBaseCount> timeTrend(Map<String,Object> paramMap);
	
	int queryActiveCount(Map<String,Object> paramMap);
	
	int countActiveNumber(Map<String,Object> paramMap);

	List<WtBaseCount> countActiveCountryNum(Map<String, Object> paramMap);

	List<WtBaseCount> countActiveDeviceNum(Map<String, Object> paramMap);
	
}