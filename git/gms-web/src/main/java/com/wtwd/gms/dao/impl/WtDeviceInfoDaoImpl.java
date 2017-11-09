package com.wtwd.gms.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wtwd.gms.dao.WtDeviceInfoDao;
import com.wtwd.gms.entity.WtDeviceInfo;

@Repository
public class WtDeviceInfoDaoImpl extends BaseDaoImpl<WtDeviceInfo> implements WtDeviceInfoDao {

	/**
	 * 批量导入设备
	 */
	public void saveBatchDevice(List<WtDeviceInfo> list) {
		super.getSqlSession().insert(getStatement("saveBatchDevice"), list);
	}

	/**
	 * 查询设备总数
	 */
	public int queryDeviceNum(Map<String, Object> paramMap) {
		return super.getSqlSession().selectOne(getStatement("queryDeviceNum"), paramMap);
	}

	@Override
	public List<WtDeviceInfo> distinctData(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("distinctData"), paramMap);
	}

	@Override
	public List<WtDeviceInfo> queryInputData(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("queryInputData"),param);
	}

	@Override
	public List<WtDeviceInfo> querySn(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("querySn"),param);
	}

	@Override
	public int deleteInputData(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return super.getSqlSession().delete(getStatement("deleteInputData"),param);
	}

	@Override
	public List<WtDeviceInfo> queryStore(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("queryStore"),paramMap);
	}

	@Override
	public int updateActivation(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().update(getStatement("updateActivation"),paramMap);
	}

	@Override
	public int queryStroeCount(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectOne(getStatement("queryStoreCount"),paramMap);
	}

	@Override
	public int queryInputDataCount(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectOne(getStatement("queryInputDataCount"),paramMap);
	}
}
