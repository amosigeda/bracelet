package com.wtwd.gms.dao.impl;

import org.springframework.stereotype.Repository;

import com.wtwd.gms.dao.WtRoleInfoDao;
import com.wtwd.gms.entity.WtRoleInfo;

@Repository
public class WtRoleInfoDaoImpl extends BaseDaoImpl<WtRoleInfo> implements WtRoleInfoDao {

	@Override
	public WtRoleInfo findRoleInfoByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectOne(getStatement("findRoleInfoByRoleName"),roleName);
	}

}
