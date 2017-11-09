package com.wtwd.gms.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.wtwd.gms.dao.WtPermissionDao;
import com.wtwd.gms.dao.WtRolePermissionDao;
import com.wtwd.gms.entity.WtPermission;
import com.wtwd.gms.entity.WtRolePermission;
import com.wtwd.gms.service.WtRolePermissionService;
import com.wtwd.gms.service.WtUserRoleService;

@Service("wtRolePermissionService")
public class WtRolePermissionServiceImpl implements WtRolePermissionService {

	@Autowired
	private WtUserRoleService wtUserRoleService;
	@Autowired
	private WtPermissionDao wtPermissionDao;
	@Autowired
	private WtRolePermissionDao wtRolePermissionDao;
	
	/**
	 * 根据操作员ID，获取所有的功能权限集
	 */
	public Set<String> getPermissionsByOperatorId(Long operatorId) {
		// 根据操作员Id查询出关联的所有角色id
		String roleIds = wtUserRoleService.getRoleIdsByOperatorId(operatorId);
		
		String permissionIds = getActionIdsByRoleIds(roleIds);
		Set<String> permissionSet = new HashSet<String>();

		// 根据角色ID字符串得到该用户的所有权限拼成的字符串
		if (!StringUtils.isEmpty(permissionIds)) {
			List<WtPermission> permissions = wtPermissionDao.findByIds(permissionIds);
			
			for (WtPermission permission : permissions) {
				permissionSet.add(permission.getPermission());
			}
		}
		return permissionSet;
	}
	
	/**
	 * 根据角色ID集得到所有权限ID集
	 */
	private String getActionIdsByRoleIds(String roleIds) {
		// 得到角色－权限表中roleiId在ids中的所有关联对象
		List<WtRolePermission> listRolePermission = wtRolePermissionDao.listByRoleIds(roleIds);// 构建StringBuffer
		StringBuffer actionIdsBuf = new StringBuffer("");
		// 拼接字符串
		for (WtRolePermission pmsRolePermission : listRolePermission) {
			actionIdsBuf.append(pmsRolePermission.getPermissionId()).append(",");
		}
		String actionIds = actionIdsBuf.toString();
		// 截取字符串
		if (StringUtils.isEmpty(actionIds) && actionIds.length() > 0) {
			actionIds = actionIds.substring(0, actionIds.length() - 1); // 去掉最后一个逗号
		}
		return actionIds;
	}

	@Override
	public void insertRolePermissionInfo(WtRolePermission wtRolePermission) {
		// TODO Auto-generated method stub
		wtRolePermissionDao.insertRolePermissionInfo(wtRolePermission);
	}

	@Override
	public void delRolePermissionInfo(Long roleId) {
		// TODO Auto-generated method stub
		wtRolePermissionDao.delRolePermissionInfo(roleId);
	}
	
	
}
