package com.wtwd.gms.service;

import java.util.List;
import java.util.Map;

import com.wtwd.gms.entity.WtReportInfo;


public interface WtReportInfoService {

	List<WtReportInfo> queryReportInfo(Map<String, Object> paramMap);
	
	void insertWtReportInfo(WtReportInfo wtReportInfo);
}
