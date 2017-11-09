package com.wtwd.gms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtwd.gms.dao.WtUserInfoDao;
import com.wtwd.gms.entity.WtUserInfo;
import com.wtwd.gms.service.WtUserInfoService;

@Service("wtUserInfoService")
public class WtUserInfoServiceImpl implements WtUserInfoService {

	@Autowired
	private WtUserInfoDao wtUserInfoDao;

	/**
	 * 根据登录名查询用户
	 */
	public WtUserInfo findOperatorByLoginName(String userName) {
		return wtUserInfoDao.findByLoginName(userName);
	}
	/**
	 * 查找所有用户
	 * Title: findAllUser
	 * Description: 
	 * @return
	 * @see com.wtwd.gms.service.WtUserInfoService#findAllUser()
	 */
	@Override
	public List<WtUserInfo> findAllUser() {
		// TODO Auto-generated method stub
		return wtUserInfoDao.findAllUser();
	}
	@Override
	public void insertUserInfo(WtUserInfo userInfo) {
		// TODO Auto-generated method stub
		wtUserInfoDao.insert(userInfo);
	}
	@Override
	public int deleteUserInfo(Long id) {
		// TODO Auto-generated method stub
		return wtUserInfoDao.delete(id);
	}
	@Override
	public String queryWorkerByUserName(String userName) {
		// TODO Auto-generated method stub
		return wtUserInfoDao.queryWorkerByUserName(userName);
	}
	@Override
	public int modifyPassword(WtUserInfo wtUserInfo) {
		// TODO Auto-generated method stub
		return wtUserInfoDao.modifyPassword(wtUserInfo);
	}
	
	
	
	
}
