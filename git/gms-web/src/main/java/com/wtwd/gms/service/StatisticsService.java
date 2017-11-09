package com.wtwd.gms.service;

import java.util.List;
import java.util.Map;

import com.wtwd.gms.entity.WtDeviceDateCount;

public interface StatisticsService {

	List<WtDeviceDateCount> querySevenMobileOS(Map<String, Object> paraMap);
}
