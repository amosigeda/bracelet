package com.wtwd.gms.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wtwd.gms.dao.WtActiveInfoDao;
import com.wtwd.gms.entity.WtActiveInfo;
import com.wtwd.gms.entity.WtBaseCount;
import com.wtwd.gms.entity.WtDeviceDateCount;
@Repository
public class WtActiveInfoDaoImpl  extends BaseDaoImpl<WtActiveInfo> implements WtActiveInfoDao{

	/*@Override
	public List<WtActiveInfo> queryAllData() {
		// TODO Auto-generated method stub
		return null;
	}*/
	public List<WtActiveInfo> queryAllData() {
		return super.getSqlSession().selectList(getStatement("queryDataInfo"));
	}

	/**
	 * 
	 * Title: countActive
	 * Description: 
	 * @param paraMap
	 * @return
	 * @see com.wtwd.gms.dao.WtActiveInfoDao#countActive(java.util.Map)
	 */
	
	public Long countActive(Map<String, Object> paramMap) {
		 return super.getSqlSession().selectOne(getStatement("countActive"));
	}
	
	/*public List<WtDeviceDateCount> countActiveDate(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countActiveDate"));
	}*/
	/**
	 * 
	 * Title: countActiveDevice
	 * Description: 计算七天活跃的机型名称
	 * @return
	 * @see com.wtwd.gms.dao.WtActiveInfoDao#countActiveDevice()
	 */
	/*public List<WtBaseCount> countActiveDevice() {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countActiveDevice"));
	}*/
	/**
	 * 
	 * Title: countActiveDeviceEveryDay
	 * Description: 计算某设备N天每天的活跃数量
	 * @param paramMap
	 * @return
	 * @see com.wtwd.gms.dao.WtActiveInfoDao#countActiveDeviceEveryDay(java.util.Map)
	 */
	public List<WtDeviceDateCount> countActiveDeviceEveryDay(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countActiveDeviceEveryDay"), paramMap);
	}
	/**
	 * 
	 * Title: countActiveDeviceName
	 * Description: 计算N天活跃的设备名top5
	 * @param paramMap
	 * @return
	 * @see com.wtwd.gms.dao.WtActiveInfoDao#countActiveDeviceName(java.util.Map)
	 */
	public List<WtBaseCount> countActiveDeviceName(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countActiveDeviceName"), paramMap);
	}

	@Override
	public List<WtBaseCount> countActiveCountryName(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countActiveCountryName"), paramMap);
	}

	@Override
	public List<WtDeviceDateCount> countActiveCountryEveryDay(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countActiveCountryEveryDay"), paramMap);
	}

	@Override
	public List<WtDeviceDateCount> countOsEveryDay(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countOs"), paramMap);
	}

	@Override
	public List<WtBaseCount> countBrandName(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countBrandName"), paramMap);
	}

	@Override
	public List<WtDeviceDateCount> countBrandEveryDay(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countBrandEveryDay"), paramMap);
	}

	@Override
	public List<WtBaseCount> countModelName(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countModelName"), paramMap);
	}

	@Override
	public List<WtDeviceDateCount> countModelEveryDat(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countModelEveryDay"), paramMap);
	}

	@Override
	public List<WtBaseCount> countActiveData(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countActiveNum"), paramMap);
	}

	@Override
	public List<WtBaseCount> distinctData(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("distinctData"), paramMap);
	}

	@Override
	public List<WtBaseCount> getCountryMapData(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("getCountryMapData"),paramMap);
	}

	@Override
	public List<WtBaseCount> queryDevSn(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("queryDevSn"),paramMap);
	}

	@Override
	public List<WtBaseCount> queryDevName(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		 return super.getSqlSession().selectList(getStatement("queryDevName"),paramMap);
	}

	@Override
	public List<WtBaseCount> timeTrend(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("timeTrend"),paramMap);
	}

	@Override
	public int queryActiveCount(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectOne(getStatement("queryActiveCount"),paramMap);
	}

	@Override
	public int countActiveNumber(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectOne(getStatement("countActiveNumber"),paramMap);
	}

	@Override
	public List<WtBaseCount> countActiveCountryNum(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countActiveCountryNum"),paramMap);
	}

	@Override
	public List<WtBaseCount> countActiveDeviceNum(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countActiveDeviceNum"),paramMap);
	}

	
}
