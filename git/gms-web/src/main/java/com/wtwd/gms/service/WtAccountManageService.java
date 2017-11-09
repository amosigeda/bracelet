package com.wtwd.gms.service;

import java.util.List;

import com.wtwd.gms.entity.WtAccountManage;

public interface WtAccountManageService {

	int insertWtAccountManage(WtAccountManage wtAccountManage);
	
	List<WtAccountManage> queryWtAccountManagesInfo();
}
