package com.wtwd.gms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wtwd.gms.dao.WtActivationInfoDao;
import com.wtwd.gms.entity.WtActivationInfo;
import com.wtwd.gms.entity.WtBaseCount;
import com.wtwd.gms.entity.WtDeviceDateCount;
import com.wtwd.gms.service.WtActivationInfoService;
import com.wtwd.gms.service.WtDeviceInfoService;
import com.wtwd.gms.utils.JsonUtils;

@Service
public class WtActivationInFoServiceImpl implements WtActivationInfoService {

	@Autowired
	private WtActivationInfoDao wtActivationInfoDao;
	@Autowired
	private WtDeviceInfoService wtDeviceInfoService;
	@Override
	public List<WtActivationInfo> queryAllData() {
		// TODO 自动生成的方法存根
		return wtActivationInfoDao.queryAllData();
	}
    @Override
	public List<WtActivationInfo> listBy(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return wtActivationInfoDao.listBy(paraMap);
	}
	@Override
	public List<WtDeviceDateCount> countActivationDeviceEveryDay(
			Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return wtActivationInfoDao.countActivationDeviceEveryDay(paramMap);
	}

	@Override
	public List<WtBaseCount> countActivationDeviceName(Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return wtActivationInfoDao.countActivationDeviceName(paramMap);
	}

	@Override
	public List<WtBaseCount> countActivationCountryName(Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return wtActivationInfoDao.countActivationCountryName(paramMap);
	}

	@Override
	public List<WtDeviceDateCount> countActivationCountryEveryDay(
			Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return wtActivationInfoDao.countActivationCountryEveryDay(paramMap);
	}

	@Override
	public List<WtDeviceDateCount> countOsEveryDay(Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return wtActivationInfoDao.countOsEveryDay(paramMap);
	}

	@Override
	public List<WtBaseCount> countBrandName(Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return wtActivationInfoDao.countBrandName(paramMap);
	}

	@Override
	public List<WtDeviceDateCount> countBrandEveryDay(
			Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return wtActivationInfoDao.countBrandEveryDay(paramMap);
	}

	@Override
	public List<WtBaseCount> countModelName(Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return wtActivationInfoDao.countModelName(paramMap);
	}

	@Override
	public List<WtDeviceDateCount> countModelEveryDay(
			Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return wtActivationInfoDao.countModelEveryDay(paramMap);
	}

	@Override
	public List<WtBaseCount> distinctData(Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return wtActivationInfoDao.distinctData(paramMap);
	}
	@Override
	public int countActivationData(Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return wtActivationInfoDao.countActivationData(paramMap);
	}
	@Override
	public List<WtBaseCount> getCountryMapData(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActivationInfoDao.getCountryMapData(paramMap);
	}
	@Override
	public List<WtBaseCount> queryDevName(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActivationInfoDao.queryDevName(paramMap);
	}
	@Override
	public List<WtActivationInfo> queryEquipment(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActivationInfoDao.queryEquipment(paramMap);
	}
	@Override
	public List<WtActivationInfo> queryEquipmentLimit(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActivationInfoDao.queryEquipmentLimit(paramMap);
	}
	@Override
	public List<WtBaseCount> timeTrend(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActivationInfoDao.timeTrend(paramMap);
	}
	@Override
	public List<WtActivationInfo> queryReportSN(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActivationInfoDao.queryReportSN(paramMap);
	}
	@Transactional
	@Override
	public int insertActivation(List<WtActivationInfo> list) {
		// TODO Auto-generated method stub
		Map<String,Object> paramMap = new HashMap<String, Object>();
		int insertResult = wtActivationInfoDao.insert(list);
		paramMap.put("devList",list);
		int updateRsult = wtDeviceInfoService.updateActivation(paramMap);
		if(insertResult > 0 && updateRsult > 0){
			return 1;
		}else{
			return -1;
		}
	}
	@Override
	public int queryActivationCount(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActivationInfoDao.queryActivationCount(paramMap);
	}
	@Override
	public List<WtBaseCount> countActvationCountryNum(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActivationInfoDao.countActvationCountryNum(paramMap);
	}
	@Override
	public List<WtBaseCount> countActvationDeviceNum(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtActivationInfoDao.countActvationDeviceNum(paramMap);
	}

}
