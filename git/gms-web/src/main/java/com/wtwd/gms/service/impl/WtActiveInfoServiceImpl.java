package com.wtwd.gms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtwd.gms.dao.WtActiveInfoDao;
import com.wtwd.gms.entity.WtActiveInfo;
import com.wtwd.gms.entity.WtBaseCount;
import com.wtwd.gms.entity.WtDeviceDateCount;
import com.wtwd.gms.service.WtActiveInfoService;
@Service
public class WtActiveInfoServiceImpl implements WtActiveInfoService{

	@Autowired
	private WtActiveInfoDao wtActiveInfoDao;
	@Override
	public List<WtActiveInfo> queryAllData() {
		// TODO Auto-generated method stub
		//long id = 4;
		return wtActiveInfoDao.queryAllData();
	}
	@Override
	public Long countActive(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.countActive(paramMap);
	}
	@Override
	public List<WtActiveInfo> listBy(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.listBy(paraMap);
	}
	@Override
	public List<WtBaseCount> coutActiveDeviceName(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.countActiveDeviceName(paramMap);
	}
	@Override
	public List<WtDeviceDateCount> countActiveDeviceEveryDay(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.countActiveDeviceEveryDay(paramMap);
	}
	@Override
	public List<WtBaseCount> countActiveCountryName(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.countActiveCountryName(paramMap);
	}
	@Override
	public List<WtDeviceDateCount> countActiveCountryEveryDay(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.countActiveCountryEveryDay(paramMap);
	}
	@Override
	public List<WtDeviceDateCount> countsOsEveryDay(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.countOsEveryDay(paramMap);
	}
	@Override
	public List<WtBaseCount> countBrandName(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.countBrandName(paramMap);
	}
	@Override
	public List<WtDeviceDateCount> countBrandEveryDay(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.countBrandEveryDay(paramMap);
	}
	@Override
	public List<WtBaseCount> countModelName(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.countModelName(paramMap);
	}
	@Override
	public List<WtBaseCount> countActiveData(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.countActiveData(paramMap);
	}
	@Override
	public List<WtBaseCount> distinctData(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.distinctData(paramMap);
	}
	@Override
	public List<WtBaseCount> getCountryMapData(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.getCountryMapData(paramMap);
	}
	@Override
	public List<WtBaseCount> queryDevSn(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.queryDevSn(paramMap);
	}
	@Override
	public List<WtBaseCount> queryDevName(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.queryDevName(paramMap);
	}
	@Override
	public List<WtBaseCount> timeTrend(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.timeTrend(paramMap);
	}
	@Override
	public int insertActive(List<WtActiveInfo> list) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.insert(list);
	}
	@Override
	public int queryActiveCount(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.queryActiveCount(paramMap);
	}
	@Override
	public int countActiveNumber(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.countActiveNumber(paramMap);
	}
	@Override
	public List<WtBaseCount> countActiveCountryNum(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.countActiveCountryNum(paramMap);
	}
	@Override
	public List<WtBaseCount> countActiveDeviceNum(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActiveInfoDao.countActiveDeviceNum(paramMap);
	}
	

}
