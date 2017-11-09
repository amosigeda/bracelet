package com.wtwd.gms.dao;

import java.util.List;

import com.wtwd.gms.entity.WtPermission;

public interface WtPermissionDao extends BaseDao<WtPermission> {
    
	/**
	 * 根据实体ID集字符串获取对象列表.
	 */
	public List<WtPermission> findByIds(String idStr);
	
	public List<WtPermission> listByOperatorIdPermissionName(Long operatorId);
	
}