package com.wtwd.gms.service;

import java.util.List;
import java.util.Map;

import com.wtwd.gms.entity.WtDeviceInfo;

/**
 * 设备service
 * @author liufeng
 * @date 2017-7-1
 */
public interface WtDeviceInfoService {

	/**
	 * 批量导入设备
	 */
	public void saveBatchDevice(List<WtDeviceInfo> list,WtDeviceInfo wdi,String operator);
	
	/**
	 * 查询设备总数
	 */
	public int queryDeviceNum(Map<String, Object> paramMap);
	
	public List<WtDeviceInfo> distanctData(Map<String, Object> paramMap);
	/**
	 * 入库查询
	 * @Title:           queryInputData
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @return   
	 * @return:          int   
	 * @throws
	 */
	public int queryInputData(String date);
	/**
	 * 
	 * @Title:           querySn
	 * @Description:     TODO
	 * @param:           @param param
	 * @param:           @return   
	 * @return:          List<WtDeviceInfo>   
	 * @throws
	 */
	public List<WtDeviceInfo> querySn(Map<String,Object> param);
	
	public int deleteStorageInfoAndInputDataInfo(Map<String,Object> param);
	
	List<WtDeviceInfo> queryStore(Map<String, Object> paramMap);
	
	int updateActivation(Map<String,Object> paramMap);
	
	int queryStroeCount(Map<String,Object> paramMap);
	
	int queryInputDataCount(Map<String,Object> paramMap);
}
