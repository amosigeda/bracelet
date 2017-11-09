package com.wtwd.gms.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtwd.gms.dao.WtPermissionDao;
import com.wtwd.gms.entity.WtPermission;
import com.wtwd.gms.service.WtPermissionService;
@Service
public class WtPermissionServiceImpl implements WtPermissionService{
	@Autowired
	WtPermissionDao wtPermissionDao;
	@Override
	public Set<String> listByOperatorIdPermissionName(Long operatorId) {
		// TODO Auto-generated method stub
		List<WtPermission> permissionsList = wtPermissionDao.listByOperatorIdPermissionName(operatorId);
		Set<String> permissionNameSet = new HashSet<String>();
		if(permissionsList != null && permissionsList.size() > 0 ){
			for (WtPermission wtPermission : permissionsList) {
				permissionNameSet.add(wtPermission.getPermissionName());
			}
		}
		return permissionNameSet;
	}

}
