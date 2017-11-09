package com.wtwd.gms.service;

import com.wtwd.gms.entity.WtRoleInfo;

public interface WtRoleInfoService {

	void insertRoleInfo(WtRoleInfo wtRoleInfo);
	
	WtRoleInfo findRoleInfoByRoleName(String roleName);
	
	void delRoleInfo(Long id);
}
