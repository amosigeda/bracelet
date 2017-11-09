package com.wtwd.gms.service;

import java.util.Set;

public interface WtPermissionService {

	public Set<String> listByOperatorIdPermissionName(Long operatorId); 
}
