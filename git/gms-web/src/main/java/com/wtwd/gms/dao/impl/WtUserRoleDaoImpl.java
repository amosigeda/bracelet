package com.wtwd.gms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtwd.gms.dao.WtUserRoleDao;
import com.wtwd.gms.entity.WtRoleInfo;
import com.wtwd.gms.entity.WtUserRole;

@Repository
public class WtUserRoleDaoImpl extends BaseDaoImpl<WtUserRole> implements WtUserRoleDao {

	/**
	 * 根据操作员ID查找该操作员关联的角色.
	 */
	public List<WtUserRole> listByOperatorId(Long operatorId) {
		return super.getSqlSession().selectList(getStatement("listByOperatorId"), operatorId);
	}

	@Override
	public List<String> listByOperatorIdRoleName(Long operatorId) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("listByOperatorIdRoleName"),operatorId);
	}
	/**
	 * 插入角色与用户
	 * Title: insertUserRoleInfo
	 * Description: 
	 * @param wtUserRole
	 * @see com.wtwd.gms.dao.WtUserRoleDao#insertUserRoleInfo(com.wtwd.gms.entity.WtUserRole)
	 */
	@Override
	public void insertUserRoleInfo(WtUserRole wtUserRole) {
		// TODO Auto-generated method stub
		super.getSqlSession().insert(getStatement("insertSelective"),wtUserRole);
	}

	@Override
	public int delUserRoleInfo(Long userId) {
		// TODO Auto-generated method stub
		return super.getSqlSession().insert(getStatement("deleteUserInfo"),userId);
	}

	@Override
	public List<WtUserRole> findRoleIdByOperatorId(Long userId) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("findRoleIdByOperatorId"),userId);
	}
	
}
