package com.wtwd.gms.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import sun.print.resources.serviceui;

import com.wtwd.gms.common.core.PageBean;
import com.wtwd.gms.common.core.PageParam;
import com.wtwd.gms.dao.WtReportInfoDao;
import com.wtwd.gms.entity.WtReportInfo;

/**
 * 报表统计
 * @author huangzhe
 * @date 2017-8-15
 */
@Repository
public class WtReportInfoDaoImpl extends BaseDaoImpl<WtReportInfo> implements WtReportInfoDao {

	@Override
	public List<WtReportInfo> queryReportInfo(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectList(getStatement("queryReportInfo"), paramMap);
	}
	
	 

}
