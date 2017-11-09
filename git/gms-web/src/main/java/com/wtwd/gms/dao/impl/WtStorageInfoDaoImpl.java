package com.wtwd.gms.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wtwd.gms.dao.WtStorageInfoDao;
import com.wtwd.gms.entity.WtStorageInfo;

/**
 * 入库记录表
 * @author liufeng
 * @date 2017-7-3
 */
@Repository
public class WtStorageInfoDaoImpl extends BaseDaoImpl<WtStorageInfo> implements WtStorageInfoDao {

	/**
	 * 获取所有数据
	 */
	public List<WtStorageInfo> queryDataInfo() {
		return super.getSqlSession().selectList(getStatement("queryDataInfo"));
	}

	@Override
	public List<WtStorageInfo> queryStorageInfoByWorkerName(String workName) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("queryStorageInfoByWorkerName"),workName);
	}

	@Override
	public int deleteStorageInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return super.getSqlSession().delete(getStatement("deleteStorageInfo"),param);
	}

	@Override
	public List<WtStorageInfo> queryStorageInfoByCondition(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("queryStorageInfoByCondition"),param);
	}
	
}
