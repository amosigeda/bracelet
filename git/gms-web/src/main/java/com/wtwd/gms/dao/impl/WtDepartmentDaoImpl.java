package com.wtwd.gms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtwd.gms.dao.WtDepartmentDao;
import com.wtwd.gms.entity.WtDepartmentInfo;
@Repository
public class WtDepartmentDaoImpl extends BaseDaoImpl<WtDepartmentInfo> implements WtDepartmentDao{

	@Override
	public List<WtDepartmentInfo> queryAllDepartmentInfo() {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("queryDataInfo"));
	}

	@Override
	public WtDepartmentInfo queryDepartmentByDepartName(String departName) {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectOne(getStatement("queryDepartmentByDepartName"),departName);
	}

}
