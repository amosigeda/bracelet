package com.wtwd.gms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mysql.fabric.xmlrpc.base.Data;
import com.wtwd.gms.dao.WtVersionInfoDao;
import com.wtwd.gms.entity.WtDeviceInfo;
import com.wtwd.gms.entity.WtVersioInfo;
import com.wtwd.gms.excel.DateUtil;
import com.wtwd.gms.service.WtDeviceInfoService;
import com.wtwd.gms.utils.JsonUtils;

@Controller
@RequestMapping("/version")
public class WtVersionController extends BaseController{

	@Autowired
	private WtDeviceInfoService wtDeviceInfoService;
	@RequestMapping("/list")
	public String verison(){
		return "user/version";
	}
	
	@RequestMapping("/data")
	@ResponseBody
	public String getVersionTable(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		WtVersioInfo wtVersioInfo = new WtVersioInfo();
		wtVersioInfo.setVersoinId("1.0");
		Date date = DateUtil.parse("2017-09-25 00:00:00");
		System.out.println(date);
		wtVersioInfo.setUpdateDate(date);
		int sum = wtDeviceInfoService.queryDeviceNum(resultMap);
		wtVersioInfo.setInputSum(Long.valueOf(sum));
		resultMap.put("data", wtVersioInfo);
		return JsonUtils.objectToJson(resultMap); 
	}
	@RequestMapping("/departData")
	@ResponseBody
	public String getDepartVersionTable(@RequestParam String userStatus){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("departName",userStatus);
		WtVersioInfo wtVersioInfo = new WtVersioInfo();
		wtVersioInfo.setVersoinId("1.0");
		Date date = DateUtil.parse("2017-09-25 00:00:00");
		System.out.println(date);
		wtVersioInfo.setUpdateDate(date);
		int sum = wtDeviceInfoService.queryDeviceNum(resultMap);
		wtVersioInfo.setInputSum(Long.valueOf(sum));
		resultMap.put("data", wtVersioInfo);
		return JsonUtils.objectToJson(resultMap); 
	}
}
