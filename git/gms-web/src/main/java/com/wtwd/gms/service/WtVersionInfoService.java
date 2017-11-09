package com.wtwd.gms.service;

import com.wtwd.gms.entity.WtVersioInfo;

public interface WtVersionInfoService {

	void insertWtVersionInfo(WtVersioInfo wtVersioInfo);
	
	void updateWtVersionInfo(WtVersioInfo wtVersioInfo);
	
	WtVersioInfo getWtVersioInfoByID(String version_id);
	
}
