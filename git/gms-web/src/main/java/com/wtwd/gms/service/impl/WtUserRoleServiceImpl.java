package com.wtwd.gms.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtwd.gms.dao.WtRoleInfoDao;
import com.wtwd.gms.dao.WtUserRoleDao;
import com.wtwd.gms.entity.WtRoleInfo;
import com.wtwd.gms.entity.WtUserRole;
import com.wtwd.gms.service.WtUserRoleService;

@Service("wtUserRoleService")
public class WtUserRoleServiceImpl implements WtUserRoleService {

	@Autowired
	private WtRoleInfoDao wtRoleInfoDao;
	@Autowired
	private WtUserRoleDao wtUserRoleDao;
	 Logger  logger=Logger.getLogger(WtUserRoleServiceImpl.class); 
	/**
	 * 根据操作员ID获得该操作员的所有角色id所拼成的String，每个ID用“,”分隔
	 */
	public String getRoleIdsByOperatorId(Long operatorId) {
		// 得到操作员和角色列表
		List<WtUserRole> rpList = wtUserRoleDao.listByOperatorId(operatorId);
		
		// 构建StringBuffer来拼字符串
		StringBuffer roleIdsBuf = new StringBuffer("");
		for (WtUserRole rp : rpList) {
			roleIdsBuf.append(rp.getRoleId()).append(",");
		}
		String roleIds = roleIdsBuf.toString();
		// 截取字符串
		if (StringUtils.isNotBlank(roleIds) && roleIds.length() > 0) {
			roleIds = roleIds.substring(0, roleIds.length() - 1);
		}
		return roleIds;
	}
	
	/**
	 * 根据操作员id，获取该操作员所有角色的编码集合
	 */
	public Set<String> getRoleCodeByOperatorId(Long operatorId) {
		// 得到操作员和角色列表
		List<WtUserRole> rpList = wtUserRoleDao.listByOperatorId(operatorId);
		
		Set<String> roleCodeSet = new HashSet<String>();

		for (WtUserRole rp : rpList) {
			Long roleId = rp.getRoleId();
			WtRoleInfo role = wtRoleInfoDao.getById(roleId);
			if (role == null) {
				continue;
			}
			roleCodeSet.add(role.getRoleCode());
		}

		return roleCodeSet;
	}
	/**
	 * 根据id查询用户角色名
	 * Title: listByOperatorIdRoleName
	 * Description: 
	 * @param operatorId
	 * @return
	 * @see com.wtwd.gms.service.WtUserRoleService#listByOperatorIdRoleName(java.lang.Long)
	 */
	@Override
	public Set<String> listByOperatorIdRoleName(Long operatorId) {
		List<String> roleList = wtUserRoleDao.listByOperatorIdRoleName(operatorId);
		
		Set<String> roleNameSet = new HashSet<String>();
		
		/*for (WtRoleInfo wtRoleInfo : roleList) {
			if(wtRoleInfo.getRoleName() == null ){
				continue;
			}*/
		for (String string : roleList) {
			roleNameSet.add(string);
		}
			//roleNameSet.add(wtRoleInfo.getRoleName());
			//logger.error(wtRoleInfo.getRoleName());  
		//}
		return roleNameSet;
	}

	@Override
	public void insertUserRoleInfo(WtUserRole wtUserRole) {
		// TODO Auto-generated method stub
		wtUserRoleDao.insertUserRoleInfo(wtUserRole);
	}

	@Override
	public int delUserRoleInfo(Long userId) {
		// TODO Auto-generated method stub
		return wtUserRoleDao.delUserRoleInfo(userId);
	}

	@Override
	public Set<Long> findRoleIdByOperatorId(Long userId) {
		// TODO Auto-generated method stub
		List<WtUserRole> userRolesList = wtUserRoleDao.findRoleIdByOperatorId(userId);
		Set<Long> roleSet = new HashSet<Long>();
		for (WtUserRole wtUserRole : userRolesList) {
			roleSet.add(wtUserRole.getRoleId());
		}
		return roleSet;
	}

	

	
	
	
	
	
}
