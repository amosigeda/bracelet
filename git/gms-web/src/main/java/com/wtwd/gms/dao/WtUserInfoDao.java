package com.wtwd.gms.dao;

import java.util.List;

import com.wtwd.gms.entity.WtUserInfo;

public interface WtUserInfoDao extends BaseDao<WtUserInfo> {
	
	/**
	 * 根据操作员登录名获取操作员信息.
	 */
	WtUserInfo findByLoginName(String loginName);
	/**
	 * 查找所有的用户信息
	 * @Title:           findAllUser
	 * @Description:     TODO
	 * @param:           @return   
	 * @return:          List<WtUserInfo>   
	 * @throws
	 */
	List<WtUserInfo> findAllUser();
	/**
	 * 插入User信息
	 * @Title:           insertUserInfo
	 * @Description:     TODO
	 * @param:           @param userInfo   
	 * @return:          void   
	 * @throws
	 */
	void insertUserInfo(WtUserInfo userInfo);
	/**
	 * 通过用户名查找此用户部门下的工作人员
	 * @Title:           queryWorkerByUserName
	 * @Description:     TODO
	 * @param:           @param userName
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	String queryWorkerByUserName(String userName);
	
	int modifyPassword(WtUserInfo wtUserInfo);
}