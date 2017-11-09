package com.wtwd.gms.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtwd.gms.dao.WtRolePermissionDao;
import com.wtwd.gms.entity.WtRolePermission;

@Repository
public class WtRolePermissionDaoImpl extends BaseDaoImpl<WtRolePermission> implements WtRolePermissionDao {

	/**
	 * 根据角色ID字符串获取相应角色-权限关联信息.
	 */
	public List<WtRolePermission> listByRoleIds(String roleIdsStr) {
		List<String> roldIds = Arrays.asList(roleIdsStr.split(","));
		return super.getSqlSession().selectList(getStatement("listByRoleIds"), roldIds);
	}

	@Override
	public void insertRolePermissionInfo(WtRolePermission wtRolePermission) {
		// TODO Auto-generated method stub
		 super.getSqlSession().insert(getStatement("insertSelective"),wtRolePermission);
	}

	@Override
	public void delRolePermissionInfo(Long roleId) {
		// TODO Auto-generated method stub
		super.getSqlSession().insert(getStatement("delRolePermissionInfo"),roleId);
	}
	
}
