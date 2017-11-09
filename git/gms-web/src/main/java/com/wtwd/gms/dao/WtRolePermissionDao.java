package com.wtwd.gms.dao;

import java.util.List;

import com.wtwd.gms.entity.WtRolePermission;

public interface WtRolePermissionDao extends BaseDao<WtRolePermission> {
	
	/**
	 * 
	 * 根据角色ID字符串获取相应角色-权限关联信息.
	 */
	public List<WtRolePermission> listByRoleIds(String roleIdsStr);
	
	public void insertRolePermissionInfo(WtRolePermission wtRolePermission);
	
	void delRolePermissionInfo(Long roleId);
}