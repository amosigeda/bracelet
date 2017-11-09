package com.wtwd.gms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtwd.gms.dao.WtUserInfoDao;
import com.wtwd.gms.entity.WtUserInfo;

@Repository
public class WtUserInfoDaoImpl extends BaseDaoImpl<WtUserInfo> implements WtUserInfoDao {

	
	/**
	 * 根据ID查找用户
	 * Title: findByLoginName
	 * Description: 
	 * @param userName
	 * @return
	 * @see com.wtwd.gms.dao.WtUserInfoDao#findByLoginName(java.lang.String)
	 */
	public WtUserInfo findByLoginName(String userName) {
		return super.getSqlSession().selectOne(getStatement("findByLoginName"), userName);
	}
	/**
	 * 查找所有用户
	 * Title: findAllUser
	 * Description: 
	 * @return
	 * @see com.wtwd.gms.dao.WtUserInfoDao#findAllUser()
	 */
	@Override
	public List<WtUserInfo> findAllUser() {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("alluser"));
	}
	@Override
	public void insertUserInfo(WtUserInfo userInfo) {
		// TODO Auto-generated method stub
		
		super.getSqlSession().insert(getStatement("insertSelective"), userInfo);
		
	}
	@Override
	public String queryWorkerByUserName(String userName) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectOne(getStatement("queryWorkerByUserName"),userName);
	}
	@Override
	public int modifyPassword(WtUserInfo wtUserInfo) {
		// TODO Auto-generated method stub
		return super.getSqlSession().update(getStatement("modifyPassword"),wtUserInfo);
	}


}
