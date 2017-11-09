package com.wtwd.gms.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtwd.gms.dao.WtPermissionDao;
import com.wtwd.gms.entity.WtPermission;

@Repository
public class WtPermissionDaoImpl extends BaseDaoImpl<WtPermission> implements WtPermissionDao {

	/**
	 * 根据实体ID集字符串获取对象列表.
	 */
	public List<WtPermission> findByIds(String idStr) {
		List<String> ids = Arrays.asList(idStr.split(","));
		return this.getSqlSession().selectList(getStatement("findByIds"), ids);
	}
	/**
	 * 根据操作员求出权限名
	 * Title: listByOperatorIdPermissionName
	 * Description: 
	 * @param operatorId
	 * @return
	 * @see com.wtwd.gms.dao.WtPermissionDao#listByOperatorIdPermissionName(java.lang.Long)
	 */
	@Override
	public List<WtPermission> listByOperatorIdPermissionName(Long operatorId) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList(getStatement("listByOperatorIdPermissionName"),operatorId);
	}
	
}
