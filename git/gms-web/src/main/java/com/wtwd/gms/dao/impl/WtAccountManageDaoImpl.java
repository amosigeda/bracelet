package com.wtwd.gms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wtwd.gms.dao.WtAccountManageDao;
import com.wtwd.gms.entity.WtAccountManage;

@Repository
public class WtAccountManageDaoImpl extends BaseDaoImpl<WtAccountManage> implements WtAccountManageDao{

	@Override
	public List<WtAccountManage> queryAllWtAccountManageInfo() {
		// TODO Auto-generated method stub
		return super.getSqlSession().selectList(getStatement("queryAllWtAccountManageInfo"));
	}

	@Override
	public int  insertWtAccountManage(WtAccountManage wtAccountManage) {
		// TODO Auto-generated method stub
		return super.getSqlSession().insert(getStatement("insertSelective"),wtAccountManage);
	}

}
