package com.wtwd.gms.dao;

import java.util.List;

import com.wtwd.gms.entity.WtAccountManage;

public interface WtAccountManageDao extends BaseDao<WtAccountManage>{

	int insertWtAccountManage(WtAccountManage wtAccountManage);
	List<WtAccountManage> queryAllWtAccountManageInfo();
}
