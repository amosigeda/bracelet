package com.wtwd.gms.dao;

import java.util.List;
import java.util.Map;

import com.wtwd.gms.entity.WtDeviceInfo;

public interface WtDeviceInfoDao extends BaseDao<WtDeviceInfo> {
    
	/**
	 * 批量导入设备
	 */
	public void saveBatchDevice(List<WtDeviceInfo> list);
	
	/**
	 * 查询设备总数
	 */
	public int queryDeviceNum(Map<String, Object> paramMap);
	
	public List<WtDeviceInfo> distinctData(Map<String, Object> paramMap);
	/**
	 * 查找入库时间
	 * @Title:           queryInputData
	 * @Description:     TODO
	 * @param:           @param param
	 * @param:           @return   
	 * @return:          List<WtDeviceInfo>   
	 * @throws
	 */
	public List<WtDeviceInfo> queryInputData(Map<String,Object> param);
	/**
	 * 根据条件获取sn号
	 * @Title:           querySn
	 * @Description:     TODO
	 * @param:           @param param
	 * @param:           @return   
	 * @return:          List<WtDeviceInfo>   
	 * @throws
	 */
	public List<WtDeviceInfo> querySn(Map<String,Object> param);
	/**
	 * 
	 * @Title:           deleteInputData
	 * @Description:     TODO
	 * @param:           @param param   
	 * @return:          void   
	 * @throws
	 */
	public int deleteInputData(Map<String, Object> param);
	
	List<WtDeviceInfo> queryStore(Map<String,Object> paramMap);
	
	int updateActivation(Map<String,Object> paramMap);
	
	int queryStroeCount(Map<String,Object> paramMap);
	
	int queryInputDataCount(Map<String,Object> paramMap);
	
}