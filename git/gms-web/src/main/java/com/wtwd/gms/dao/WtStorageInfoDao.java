package com.wtwd.gms.dao;

import java.util.List;
import java.util.Map;

import com.wtwd.gms.entity.WtStorageInfo;

public interface WtStorageInfoDao extends BaseDao<WtStorageInfo> {
	
	/**
	 * 获取所有数据
	 */
	public List<WtStorageInfo> queryDataInfo();
	/**
	 * 根据操作员名称查询操作数据
	 * @Title:           queryStorageInfoByWorkerName
	 * @Description:     TODO
	 * @param:           @param workName
	 * @param:           @return   
	 * @return:          List<WtStorageInfo>   
	 * @throws
	 */
	public List<WtStorageInfo> queryStorageInfoByWorkerName(String workName);
	/**
	 * 删除存储记录
	 * @Title:           deleteStorageInfo
	 * @Description:     TODO
	 * @param:           @param param
	 * @param:           @return   
	 * @return:          int   
	 * @throws
	 */
	public int deleteStorageInfo(Map<String,Object> param);
	/**
	 * 固定时间内查询
	 * @Title:           queryStorageInfoByCondition
	 * @Description:     TODO
	 * @param:           @param param
	 * @param:           @return   
	 * @return:          List<WtStorageInfo>   
	 * @throws
	 */
	public List<WtStorageInfo> queryStorageInfoByCondition(Map<String,Object> param);
}