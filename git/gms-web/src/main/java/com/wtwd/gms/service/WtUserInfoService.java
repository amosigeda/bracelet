package com.wtwd.gms.service;

import java.util.List;

import com.wtwd.gms.entity.WtUserInfo;

public interface WtUserInfoService {

	/**
	 * 根据登录名查询用户
	 */
	WtUserInfo findOperatorByLoginName(String userName);
	/**
	 * 查找多有的user信息
	 * @Title:           findAllUser
	 * @Description:     TODO
	 * @param:           @return   
	 * @return:          List<WtUserInfo>   
	 * @throws
	 */
	List<WtUserInfo> findAllUser();
	
	void insertUserInfo(WtUserInfo userInfo);
	
	int  deleteUserInfo(Long id);
	
	String queryWorkerByUserName(String userName);
	
	int modifyPassword(WtUserInfo wtUserInfo);
}
