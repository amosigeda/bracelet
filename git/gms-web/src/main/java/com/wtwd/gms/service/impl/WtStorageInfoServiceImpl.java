package com.wtwd.gms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtwd.gms.dao.WtStorageInfoDao;
import com.wtwd.gms.entity.WtStorageInfo;
import com.wtwd.gms.service.WtStorageInfoService;

/**
 * 设备入库记录表
 * @author liufeng
 * @date 2017-7-3
 */
@Service("wtStorageInfoService")
public class WtStorageInfoServiceImpl implements WtStorageInfoService {

	@Autowired
	private WtStorageInfoDao wtStorageInfoDao;

	/**
	 * 多条件查询设备入库信息
	 */
	public List<WtStorageInfo> listBy(WtStorageInfo wsi) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(wsi.getBeginDate() != null){
			paramMap.put("beginDate", wsi.getBeginDate());
		}
		if(wsi.getEndDate() != null){
			paramMap.put("endDate", wsi.getEndDate());
		}
		return wtStorageInfoDao.listBy(paramMap);
	}

	/**
	 * 获取所有数据
	 */
	public List<WtStorageInfo> queryDataInfo() {
		return wtStorageInfoDao.queryDataInfo();
	}

	@Override
	public List<WtStorageInfo> queryStorageInfoByWorkerName(String worker) {
		// TODO Auto-generated method stub
		return wtStorageInfoDao.queryStorageInfoByWorkerName(worker);
	}

	@Override
	public List<WtStorageInfo> queryStorageInfoByCondition(
			Map<String, Object> param) {
		// TODO Auto-generated method stub
		return wtStorageInfoDao.queryStorageInfoByCondition(param);
	}

	
	
}
