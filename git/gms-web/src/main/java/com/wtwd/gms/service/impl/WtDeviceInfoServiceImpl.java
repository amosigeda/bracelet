package com.wtwd.gms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wtwd.gms.dao.WtActivationInfoDao;
import com.wtwd.gms.dao.WtDeviceInfoDao;
import com.wtwd.gms.dao.WtStorageInfoDao;
import com.wtwd.gms.entity.WtDeviceInfo;
import com.wtwd.gms.entity.WtStorageInfo;
import com.wtwd.gms.service.WtDeviceInfoService;

/**
 * 设备service实现
 * @author liufeng
 * @date 2017-7-1
 */
@Service("wtDeviceInfoService")
public class WtDeviceInfoServiceImpl implements WtDeviceInfoService {

	@Autowired
	private WtDeviceInfoDao wtDeviceInfoDao;
	@Autowired
	private WtStorageInfoDao wtStorageInfoDao;
	@Autowired
	private WtActivationInfoDao wtActivationInfoDao;
	
	/**
	 * 批量导入设备
	 */
	@Transactional
	public void saveBatchDevice(List<WtDeviceInfo> list,WtDeviceInfo wdi,String operator) {
		//更新设备导入记录
		WtStorageInfo wsi = new WtStorageInfo();
		//String	date = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL);
		Date date = list.get(0).getCreateTime();
		wsi.setDeviceName(wdi.getModel());
		wsi.setNum(list.size());//新增数量
		wsi.setTotal(list.size());//累计数
		wsi.setStatus("成功");
		wsi.setInventory(list.size());//存货总数
		wsi.setOperator(operator);
		wsi.setEditTime(date);
		wtStorageInfoDao.insert(wsi);
		//System.out.println("id:"+wsi.getId());
		//批量导入excel
		
		wtDeviceInfoDao.saveBatchDevice(list);
	}

	/**
	 * 查询设备总数
	 */
	public int queryDeviceNum(Map<String, Object> paramMap) {
		return wtDeviceInfoDao.queryDeviceNum(paramMap);
	}

	@Override
	public List<WtDeviceInfo> distanctData(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtDeviceInfoDao.distinctData(paramMap);
	}

	@Override
	public int queryInputData(String date) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("createTime", date);
		List<WtDeviceInfo> list = wtDeviceInfoDao.queryInputData(param);
		return list.size();
	}

	@Override
	public List<WtDeviceInfo> querySn(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return wtDeviceInfoDao.querySn(param);
	}

	@Transactional
	public int deleteStorageInfoAndInputDataInfo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		
		int devInfoResult = wtDeviceInfoDao.deleteInputData(param);
		int storageInfoResult = wtStorageInfoDao.deleteStorageInfo(param);
		if(devInfoResult <1 || storageInfoResult <1){
			return 0;
		}
		return 1;
	}

	@Override
	public List<WtDeviceInfo> queryStore(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtDeviceInfoDao.queryStore(paramMap);
	}

	@Override
	public int updateActivation(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtDeviceInfoDao.updateActivation(paramMap);
	}

	@Override
	public int queryStroeCount(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtDeviceInfoDao.queryStroeCount(paramMap);
	}

	@Override
	public int queryInputDataCount(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtDeviceInfoDao.queryInputDataCount(paramMap);
	}
	
}
