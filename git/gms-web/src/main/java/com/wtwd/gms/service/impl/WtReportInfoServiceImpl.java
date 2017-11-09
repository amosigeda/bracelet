package com.wtwd.gms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtwd.gms.dao.WtReportInfoDao;
import com.wtwd.gms.entity.WtReportInfo;
import com.wtwd.gms.service.WtReportInfoService;

@Service("WtReportInfoService")
public class WtReportInfoServiceImpl implements WtReportInfoService{

	
	@Autowired
	private WtReportInfoDao wtReportInfoDao;

	@Override
	public List<WtReportInfo> queryReportInfo(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return wtReportInfoDao.queryReportInfo(paramMap);
	}

	@Override
	public void insertWtReportInfo(WtReportInfo wtReportInfo) {
		// TODO Auto-generated method stub
		 wtReportInfoDao.insert(wtReportInfo);
	}
	


}
