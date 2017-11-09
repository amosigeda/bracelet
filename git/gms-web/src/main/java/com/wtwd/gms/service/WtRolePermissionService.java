package com.wtwd.gms.service;

import java.util.Set;

import com.wtwd.gms.entity.WtRolePermission;

public interface WtRolePermissionService {

	/**
	 * 根据操作员ID，获取所有的功能权限集
	 */
	public Set<String> getPermissionsByOperatorId(Long operatorId);
	
	public void insertRolePermissionInfo(WtRolePermission wtRolePermission);
	
	void delRolePermissionInfo(Long roleId);
}
