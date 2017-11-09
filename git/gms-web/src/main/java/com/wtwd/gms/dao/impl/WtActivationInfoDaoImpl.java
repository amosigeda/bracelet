package com.wtwd.gms.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;












import com.wtwd.gms.dao.WtActivationInfoDao;
import com.wtwd.gms.entity.WtActivationInfo;
import com.wtwd.gms.entity.WtBaseCount;
import com.wtwd.gms.entity.WtDeviceDateCount;
import com.wtwd.gms.entity.WtReportInfo;

@Repository
public class WtActivationInfoDaoImpl extends BaseDaoImpl<WtActivationInfo> implements WtActivationInfoDao{

	

	@Override
	public List<WtActivationInfo> queryAllData() {
		// TODO 自动生成的方法存根
		return super.getSqlSession().selectList(getStatement("queryDataInfo"));
	}

	@Override
	public List<WtDeviceDateCount> countActivationDeviceEveryDay(
			Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return super.getSqlSession().selectList(getStatement("countActivationDeviceEveryDay"), paramMap);
	}

	@Override
	public List<WtBaseCount> countActivationDeviceName(Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return super.getSqlSession().selectList(getStatement("countActivationDeviceName"), paramMap);
	}

	@Override
	public List<WtBaseCount> countActivationCountryName(Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return super.getSqlSession().selectList(getStatement("countActivationCountryName"), paramMap);
	}

	@Override
	public List<WtDeviceDateCount> countActivationCountryEveryDay(
			Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return super.getSqlSession().selectList(getStatement("countActivationCountryEveryDay"), paramMap);
	}

	@Override
	public List<WtDeviceDateCount> countOsEveryDay(Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return super.getSqlSession().selectList(getStatement("countOs"), paramMap);
	}

	@Override
	public List<WtBaseCount> countBrandName(Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return super.getSqlSession().selectList(getStatement("countBrandName"), paramMap);
	}

	@Override
	public List<WtDeviceDateCount> countBrandEveryDay(
			Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return super.getSqlSession().selectList(getStatement("countBrandEveryDay"), paramMap);
	}

	@Override
	public List<WtBaseCount> countModelName(Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return super.getSqlSession().selectList(getStatement("countModelName"), paramMap);
	}

	@Override
	public List<WtDeviceDateCount> countModelEveryDay(
			Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return super.getSqlSession().selectList(getStatement("countModelEveryDay"), paramMap);
	}

	@Override
	public List<WtBaseCount> distinctData(Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return super.getSqlSession().selectList(getStatement("distinctData"), paramMap);
	}

	@Override
	public int countActivationData(Map<String, Object> paramMap) {
		// TODO 自动生成的方法存根
		return super.getSqlSession().selectOne(getStatement("countActivationNum"),paramMap);
	}

	@Override
	public List<WtBaseCount> getCountryMapData(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("getCountryMapData"),paramMap);
	}

	@Override
	public List<WtBaseCount> queryDevSn(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WtBaseCount> queryDevName(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("queryDevName"),paramMap);
	}

	@Override
	public List<WtActivationInfo> queryEquipment(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("EquipmentStatistics"),param);
	}

	@Override
	public List<WtActivationInfo> queryEquipmentLimit(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("EquipmentStatistics_limit100"),paramMap);
	}

	@Override
	public List<WtBaseCount> timeTrend(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("timeTrend"),paramMap);
	}

	@Override
	public List<WtActivationInfo> queryReportSN(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("reportSn"),paramMap);
	}

	@Override
	public int queryActivationCount(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectOne(getStatement("queryActivationCount"),paramMap);
	}

	@Override
	public List<WtBaseCount> countActvationCountryNum(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return  super.getSqlSession().selectList(getStatement("countActvationCountryNum"),paramMap);
	}

	@Override
	public List<WtBaseCount> countActvationDeviceNum(
			Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("countActvationDeviceNum"),paramMap);
	}



	

}
