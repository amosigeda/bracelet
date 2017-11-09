package com.wtwd.gms.dao;

import java.util.List;

import com.wtwd.gms.entity.WtRoleInfo;
import com.wtwd.gms.entity.WtUserRole;

public interface WtUserRoleDao extends BaseDao<WtUserRole> {
	
	/**
	 * 根据操作员ID查找该操作员关联的角色.
	 */
	public List<WtUserRole> listByOperatorId(Long operatorId);
	/**
	 * 通过id查找对应权限
	 * @Title:           listByOperatorIdRoleName
	 * @Description:     TODO
	 * @param:           @param operatorId
	 * @param:           @return   
	 * @return:          List<WtRoleInfo>   
	 * @throws
	 */
	public List<String> listByOperatorIdRoleName(Long operatorId);
	/**
	 * 插入角色与用户
	 * @Title:           insertUserRoleInfo
	 * @Description:     TODO
	 * @param:           @param wtUserRole   
	 * @return:          void   
	 * @throws
	 */
	public void insertUserRoleInfo(WtUserRole wtUserRole);
	/**
	 * 
	 * @Title:           delUserRoleInfo
	 * @Description:     TODO
	 * @param:           @param userId
	 * @param:           @return   
	 * @return:          int   
	 * @throws
	 */
	public int delUserRoleInfo(Long userId);
	
	public List<WtUserRole> findRoleIdByOperatorId(Long operatorId);
}