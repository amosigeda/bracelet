package com.wtwd.gms.dao;

import java.util.List;

import com.wtwd.gms.entity.WtDepartmentInfo;

public interface WtDepartmentDao  extends BaseDao<WtDepartmentInfo>{
	
	List<WtDepartmentInfo> queryAllDepartmentInfo();
	
	WtDepartmentInfo queryDepartmentByDepartName(String departName);
}
