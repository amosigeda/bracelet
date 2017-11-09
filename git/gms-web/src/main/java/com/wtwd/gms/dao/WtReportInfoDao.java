package com.wtwd.gms.dao;

import java.util.List;
import java.util.Map;

import com.wtwd.gms.entity.WtReportInfo;




public interface WtReportInfoDao extends BaseDao<WtReportInfo>{

	List<WtReportInfo> queryReportInfo(Map<String,Object> paramMap);

}
