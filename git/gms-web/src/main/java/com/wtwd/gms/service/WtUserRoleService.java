package com.wtwd.gms.service;

import java.util.Set;

import com.wtwd.gms.entity.WtUserRole;

public interface WtUserRoleService {

	/**
	 * 根据操作员ID获得该操作员的所有角色id所拼成的String，每个ID用“,”分隔
	 */
	public String getRoleIdsByOperatorId(Long operatorId);
	
	/**
	 * 根据操作员id，获取该操作员所有角色的编码集合
	 */
	public Set<String> getRoleCodeByOperatorId(Long operatorId);
	/**
	 * 根据id获取角色名
	 * @Title:           listByOperatorIdRoleName
	 * @Description:     TODO
	 * @param:           @param operatorId
	 * @param:           @return   
	 * @return:          Set<String>   
	 * @throws
	 */
	public Set<String> listByOperatorIdRoleName(Long operatorId);
	/**
	 * 
	 * @Title:           insertUserRoleInfo
	 * @Description:     TODO
	 * @param:           @param wtUserRole   
	 * @return:          void   
	 * @throws
	 */
	void insertUserRoleInfo(WtUserRole wtUserRole);
	
	public int delUserRoleInfo(Long userId); 
	
	public Set<Long> findRoleIdByOperatorId(Long userId);
}
