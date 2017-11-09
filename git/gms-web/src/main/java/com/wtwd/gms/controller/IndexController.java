package com.wtwd.gms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.pool.Size;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.wtwd.gms.entity.WtActivationInfo;
import com.wtwd.gms.entity.WtBaseCount;
import com.wtwd.gms.entity.WtDepartmentInfo;
import com.wtwd.gms.entity.WtDeviceInfo;
import com.wtwd.gms.entity.WtMapResult;
import com.wtwd.gms.excel.DateUtil;
import com.wtwd.gms.service.WtActivationInfoService;
import com.wtwd.gms.service.WtActiveInfoService;
import com.wtwd.gms.service.WtDepartmentInfoService;
import com.wtwd.gms.service.WtDeviceInfoService;
import com.wtwd.gms.utils.JsonUtils;

/**
 * 首页
 * @author liufeng
 * @date 2017-6-28
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

	private static final Log log = LogFactory.getLog(IndexController.class);
	
	@Autowired
	private WtDeviceInfoService wtDeviceInfoService;
	
	@Autowired
	private WtActivationInfoService wtActivationInfoService;
	
	@Autowired
	private WtActiveInfoService wtActiveInfoService;
	
	@Autowired
	private WtDepartmentInfoService wtDepartmentInfoService;
	
	/**
	 * 首页
	 * 
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model){
		Map<String, Object> paramMap = new HashMap<String, Object>();
	/*	//今日激活
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(new Date());
		calendar2.add(Calendar.DAY_OF_MONTH,0);
		Date d1 = calendar2.getTime();
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		paramMap.put("createTime", df1.format(d1));*/
		int deviceNum = wtDeviceInfoService.queryDeviceNum(paramMap);
		paramMap.put("isActive", 1);
		int activeDeviceNum = wtDeviceInfoService.queryDeviceNum(paramMap);
		int unActiveDeviceNum = deviceNum - activeDeviceNum;
		//昨日激活
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date d = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		paramMap.clear();
		paramMap.put("createTime", df.format(d));
		int yesterdayAcNum = wtDeviceInfoService.queryDeviceNum(paramMap);
		
		model.addAttribute("deviceNum", deviceNum);
		model.addAttribute("activeDeviceNum", activeDeviceNum);
		model.addAttribute("unActiveDeviceNum", unActiveDeviceNum);
		model.addAttribute("yesterdayAcNum", yesterdayAcNum);
		return "system/index";
	}
	
	//@RequestMapping("/")
	
	/**
	 * 数据展示
	 */
	@RequestMapping("/showList")
	public void showList(HttpServletResponse response){
		try {
			//入库新增
			int deviceNum = wtDeviceInfoService.queryDeviceNum(null);
			System.out.println("deviceNum:"+deviceNum);
			//累积激活
			
			//存货明细

			
		} catch (Exception e) {
			log.error("IndexController-->showList error:"+e);
		}
	}
	@RequestMapping("/baseData")
	@ResponseBody
	public String baseData(@RequestParam String userType,@RequestParam String userStatus){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		JSONObject jsonObject = new JSONObject();
		if(userType != null && !"".equals(userType)&& "ADMIN".equals(userType)){
			paramMap.put("createTime",DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE));
			//int todayList = wtDeviceInfoService.
			//今日入库
			int todayInputNum = wtDeviceInfoService.queryInputDataCount(paramMap);
			//累积激活
			paramMap.clear();
			/*paramMap.put("allData",
					getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));*/
			//List<WtActivationInfo> allData = wtActivationInfoService.listBy(paramMap);
			int allData = wtActivationInfoService.queryActivationCount(paramMap);
			//存货明细
			paramMap.clear();
			paramMap.put("no_isActive", 1);
			/*List<WtDeviceInfo> storeList = wtDeviceInfoService.queryStore(paramMap);*/
			int storeList = wtDeviceInfoService.queryStroeCount(paramMap);
			//昨日激活
			paramMap.clear();
			paramMap.put("yesTodayDate",
					getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			System.out.println(getFormatPastDate(1,
					DateUtil.PATTERN_CLASSICAL_SIMPLE));
			
			int yesTodayActivation = wtActivationInfoService.queryActivationCount(paramMap);
			jsonObject.put("todayInputNum",todayInputNum);
			jsonObject.put("allActivationNum", allData);
			jsonObject.put("storeNum", storeList);
			jsonObject.put("yesTodayActivation", yesTodayActivation);
		}else if(userType != null && !"".equals(userType)&&("department".equals(userType) || "worker".equals(userType))){
			List<String> devNameList= queryDepartDevList(userStatus);
			
			//事业部今日入库
			if(devNameList == null || devNameList.size() <= 0){
				jsonObject.put("todayInputNum",0);
				jsonObject.put("allActivationNum", 0);
				jsonObject.put("storeNum", 0);
				jsonObject.put("yesTodayActivation",0);
				return jsonObject.toString();
			}
			paramMap.put("departName", userStatus);
			paramMap.put("createTime",DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE));
			int todayList = wtDeviceInfoService.queryDeviceNum(paramMap);
			jsonObject.put("todayInputNum",todayList);
			//事业部累积激活
			paramMap.clear();
			paramMap.put("devList",devNameList);
			paramMap.put("isActive",1);
			
			int departActiationList = wtDeviceInfoService.queryStroeCount(paramMap);
			jsonObject.put("allActivationNum",departActiationList);
			//事业部库存明细
			paramMap.clear();
			paramMap.put("devList", devNameList);
			paramMap.put("no_isActive", 1);
			int storeList = wtDeviceInfoService.queryStroeCount(paramMap);
			jsonObject.put("storeNum",storeList);
			//事业部昨日激活
			paramMap.clear();
			paramMap.put("yesTodayDate",
					getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			System.out.println(getFormatPastDate(1,
					DateUtil.PATTERN_CLASSICAL_SIMPLE));
			List<WtDeviceInfo> devNameList2 = queryDepartDeviceInfoList(userStatus);
			paramMap.put("devList",devNameList2 );
			int yesTodayActivation = wtActivationInfoService.queryActivationCount(paramMap);
			System.out.println("昨日活跃" + yesTodayActivation);
			jsonObject.put("yesTodayActivation", yesTodayActivation);
			
		}
		
		return jsonObject.toString();
	}
	
	@RequestMapping("/showStore")
	@ResponseBody
	public String showStore(@RequestParam String userType,@RequestParam String userStatus){
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		List<Integer> storeDataList = new ArrayList<>();
		List<Integer> saleDataList = new ArrayList<>();
		Map<String,Object> dataMap = new HashMap<>();
		if(userType != null && !"".equals(userType)&& "ADMIN".equals(userType)){
			//查询各事业部的库存和销售
			List<WtDepartmentInfo> departmentList = wtDepartmentInfoService.queryAllDepartmentInfo();
			Map<String,Object> paramMap = new HashMap<>();
			
			if (departmentList == null || departmentList.size() <= 0) {
				dataMap.put("Xdata", "");
				dataMap.put("Ydata1", "");
				dataMap.put("Ydata2", "");
				return JsonUtils.objectToJson(dataMap);
			}
			List<String> departName = new ArrayList<>();
			for (WtDepartmentInfo wtDepartmentInfo : departmentList) {
				departName.add(wtDepartmentInfo.getDepartName());
			}
			dataMap.put("Xdata", departName);
			//查询事业部及其下的机型
			for (WtDepartmentInfo wtDepartmentInfo : departmentList) {
				/*List<String> devList = queryDepartDevList(wtDepartmentInfo.getDepartName());
				for (String dev : devList) {
					
				}*/
				paramMap.clear();
				//dataMap.put("department", value)
				paramMap.put("department",wtDepartmentInfo.getDepartName());
				paramMap.put("isActive",1);
				//事业部所有的销量
				int saleList = wtDeviceInfoService.queryStroeCount(paramMap);
				
				saleDataList.add(saleList);
				
				
				
				paramMap.clear();
				paramMap.put("department", wtDepartmentInfo.getDepartName());
				paramMap.put("no_isActive", 1);
				int storeList = wtDeviceInfoService.queryStroeCount(paramMap);
				
				storeDataList.add(storeList);
				
			}
			dataMap.put("Ydata1", storeDataList);
			dataMap.put("Ydata2", saleDataList);
			resultList.add(dataMap);
			return JsonUtils.objectToJson(dataMap);
			//事业部机型的销售和库存总和
		}else if(userType != null && !"".equals(userType)&&("department".equals(userType) || "worker".equals(userType))){
			//事业部下所有的设备
			
			List<String> devNameList = queryDepartDevList(userStatus);
			Map<String,Object> paramMap = new HashMap<>();
			if(devNameList == null || devNameList.size() <=0){
				List<String> nullDevName = new ArrayList<String>();
				nullDevName.add("无设备");
				storeDataList.add(0);
				saleDataList.add(0);
				dataMap.put("Xdata", nullDevName);
				dataMap.put("Ydata1", storeDataList);
				dataMap.put("Ydata2", saleDataList);
				return JsonUtils.objectToJson(dataMap);
			}
			dataMap.put("Xdata", devNameList);
			//查询事业部及其下的机型
			for (String devName : devNameList) {
			
				paramMap.clear();
				paramMap.put("devName",devName);
				paramMap.put("isActive",1);
				//事业部所有的销量
				int saleList = wtDeviceInfoService.queryStroeCount(paramMap);
					saleDataList.add(saleList);
				paramMap.clear();
				paramMap.put("devName", devName);
				paramMap.put("no_isActive", 1);
				int storeList = wtDeviceInfoService.queryStroeCount(paramMap);
					storeDataList.add(storeList);
			}
			dataMap.put("Ydata1", storeDataList);
			dataMap.put("Ydata2", saleDataList);
			resultList.add(dataMap);
			return JsonUtils.objectToJson(dataMap);
		}else{
			
		}
		dataMap.put("Xdata", "");
		dataMap.put("Ydata1", "");
		dataMap.put("Ydata2", "");
		return JsonUtils.objectToJson(dataMap);
	}
	
	@RequestMapping("/showPie")
	@ResponseBody
	public String showPie(@RequestParam String userType,@RequestParam String userStatus){
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		Map<String,Object> dataMap = new HashMap<>();
		if(userType != null && !"".equals(userType)&& "ADMIN".equals(userType)){
			//查询各事业部的库存和销售
			List<WtDepartmentInfo> departmentList = wtDepartmentInfoService.queryAllDepartmentInfo();
			Map<String,Object> paramMap = new HashMap<>();
			
			if (departmentList == null || departmentList.size() <= 0) {
				dataMap.put("legends", "");
				dataMap.put("series","");
				return JsonUtils.objectToJson(dataMap);
			}
			List<String> departName = new ArrayList<>();
			for (WtDepartmentInfo wtDepartmentInfo : departmentList) {
				departName.add(wtDepartmentInfo.getDepartName());
			}
			dataMap.put("legends", departName);
			List<WtMapResult> mapResults = new ArrayList<>();
			//查询事业部及其下的机型
			for (WtDepartmentInfo wtDepartmentInfo : departmentList) {
				WtMapResult wtMapResult = new WtMapResult();
				paramMap.clear();
				paramMap.put("department", wtDepartmentInfo.getDepartName());
				paramMap.put("no_isActive", 1);
				int storeList = wtDeviceInfoService.queryStroeCount(paramMap);
					wtMapResult.setValue(storeList);
				wtMapResult.setName(wtDepartmentInfo.getDepartName());
				mapResults.add(wtMapResult);
			}
			dataMap.put("series",mapResults);
			resultList.add(dataMap);
			return JsonUtils.objectToJson(dataMap);
			//事业部机型的销售和库存总和
		}else if(userType != null && !"".equals(userType)&&("department".equals(userType) || "worker".equals(userType))){
			//事业部下所有的设备
			
			List<String> devNameList = queryDepartDevList(userStatus);
			List<WtMapResult> mapResults = new ArrayList<>();
			Map<String,Object> paramMap = new HashMap<>();
			if(devNameList == null || devNameList.size() <=0){
				List<String> nullDevName = new ArrayList<String>();
				nullDevName.add("无设备");
				dataMap.put("legends", nullDevName);
				WtMapResult wtMapResult = new WtMapResult();
				wtMapResult.setValue(0);
				wtMapResult.setName("无设备");
				mapResults.add(wtMapResult);
				dataMap.put("series",mapResults);
				return JsonUtils.objectToJson(dataMap);
			}
			dataMap.put("legends", devNameList);
			
			//查询事业部及其下的机型
			for (String devName : devNameList) {
				/*List<String> devList = queryDepartDevList(wtDepartmentInfo.getDepartName());
				for (String dev : devList) {
					
				}*/
				//dataMap.put("department", value)
			
				//事业部所有的销量
				WtMapResult wtMapResult = new WtMapResult();
				paramMap.clear();
				paramMap.put("devName", devName);
				paramMap.put("no_isActive", 1);
				int storeList = wtDeviceInfoService.queryStroeCount(paramMap);
					wtMapResult.setValue(storeList);
				wtMapResult.setName(devName);
				mapResults.add(wtMapResult);
			}
			dataMap.put("series", mapResults);
			//dataMap.put("Ydata2", saleDataList);
			//resultList.add(dataMap);
			return JsonUtils.objectToJson(dataMap);
		}else{
			dataMap.put("legends", "");
			dataMap.put("series","");
			return JsonUtils.objectToJson(dataMap);
		}
		
	}
	
	@RequestMapping("/showActive")
	@ResponseBody
	public String indexActive(@RequestParam String userType,@RequestParam String userStatus){
		
		Map<String, Object> resultMap = new HashMap<>();
		//横坐标数据
		List<String> timeList = timeList();
		List<Integer> timeReusltList = new ArrayList<>();
		List<Integer> timeReusltList2 = new ArrayList<>();
		if(userType != null && !"".equals(userType)&& "ADMIN".equals(userType)){
			//查询今日活跃，每小时
			String activeTime = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			//String activationTime = "2017-09-29";
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("activeTime",activeTime);
			List<WtBaseCount> activeTimeList = wtActiveInfoService.timeTrend(paramMap);
			resultMap.put("XData", timeList);
			if(activeTimeList!=null && activeTimeList.size() >0 ){
				int timeCountSize = activeTimeList.size() -1;
				List<String> hourList = new ArrayList<>();
				for (WtBaseCount wtBaseCount : activeTimeList) {
					hourList.add(wtBaseCount.getHour());
				}
				int index = 0;
				for(int i = 0;i<timeList.size();i++){
					if(hourList.contains(timeList.get(i))){
						timeReusltList.add(activeTimeList.get(index).getCount());
						if (index >= 0 && index < timeCountSize) {
							index++;
						}
					}else{
						timeReusltList.add(0);
					}
				}
				resultMap.put("data1", timeReusltList);
				System.out.println(JsonUtils.objectToJson(resultMap));
			}else{
				for(int i = 0;i<timeList.size();i++){
					timeReusltList.add(0);
				}
				resultMap.put("data1", timeReusltList);
			}
			//查询昨日活跃，每小时
			paramMap.clear();
			String activationTime2 = getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE);
			//String activationTime = "2017-09-29";
			paramMap.put("activeTime",activationTime2);
			List<WtBaseCount> activeTimeList2 = wtActiveInfoService.timeTrend(paramMap);
			if(activeTimeList2!=null && activeTimeList2.size() >0 ){
				int timeCountSize2 = activeTimeList2.size() -1;
				List<String> hourList2 = new ArrayList<>();
				for (WtBaseCount wtBaseCount : activeTimeList2) {
					hourList2.add(wtBaseCount.getHour());
				}
				int index2 = 0;
				for(int i = 0;i<timeList.size();i++){
					if(hourList2.contains(timeList.get(i))){
						timeReusltList2.add(activeTimeList2.get(index2).getCount());
						if (index2 >= 0 && index2 < timeCountSize2) {
							index2++;
						}
					}else{
						timeReusltList2.add(0);
					}
				}
				resultMap.put("data2", timeReusltList2);
			}else{
				for(int i = 0;i<timeList.size();i++){
					timeReusltList2.add(0);
				}
				resultMap.put("data2", timeReusltList2);
			}
			
			return JsonUtils.objectToJson(resultMap);
		}else if(userType != null && !"".equals(userType)&&("department".equals(userType) || "worker".equals(userType))){
			List<String> devNameList = queryDepartDevList(userStatus);
			
			//查询今日活跃，每小时
			String activeTime = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			//String activationTime = "2017-09-29";
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("devName", devNameList);
			paramMap.put("activeTime",activeTime);
			List<WtBaseCount> activeTimeList = wtActiveInfoService.timeTrend(paramMap);
			resultMap.put("XData", timeList);
			if(activeTimeList!=null && activeTimeList.size() >0 ){
				int timeCountSize = activeTimeList.size() -1;
				List<String> hourList = new ArrayList<>();
				for (WtBaseCount wtBaseCount : activeTimeList) {
					hourList.add(wtBaseCount.getHour());
				}
				int index = 0;
				for(int i = 0;i<timeList.size();i++){
					if(hourList.contains(timeList.get(i))){
						timeReusltList.add(activeTimeList.get(index).getCount());
						if (index >= 0 && index < timeCountSize) {
							index++;
						}
					}else{
						timeReusltList.add(0);
					}
				}
				resultMap.put("data1", timeReusltList);
				System.out.println(JsonUtils.objectToJson(resultMap));
			}else{
				for(int i = 0;i<timeList.size();i++){
					timeReusltList.add(0);
				}
				resultMap.put("data1", timeReusltList);
			}
			//查询昨日活跃，每小时
			paramMap.clear();
			String activeTime2 = getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE);
			//String activationTime = "2017-09-29";
			paramMap.put("devName", devNameList);
			paramMap.put("activeTime",activeTime2);
			List<WtBaseCount> activeTimeList2 = wtActiveInfoService.timeTrend(paramMap);
			if(activeTimeList2!=null && activeTimeList2.size() >0 ){
				int timeCountSize2 = activeTimeList2.size() -1;
				List<String> hourList2 = new ArrayList<>();
				for (WtBaseCount wtBaseCount : activeTimeList2) {
					hourList2.add(wtBaseCount.getHour());
				}
				int index2 = 0;
				for(int i = 0;i<timeList.size();i++){
					if(hourList2.contains(timeList.get(i))){
						timeReusltList2.add(activeTimeList2.get(index2).getCount());
						if (index2 >= 0 && index2 < timeCountSize2) {
							index2++;
						}
					}else{
						timeReusltList2.add(0);
					}
				}
				resultMap.put("data2", timeReusltList2);
			}else{
				for(int i = 0;i<timeList.size();i++){
					timeReusltList2.add(0);
				}
				resultMap.put("data2", timeReusltList2);
			}
			
			return JsonUtils.objectToJson(resultMap);
		}else{
			for(int i = 0;i<timeList.size();i++){
				timeReusltList.add(0);
			}
			resultMap.put("XData", timeList);
			resultMap.put("data1", timeReusltList);
			resultMap.put("data2", timeReusltList);
			return JsonUtils.objectToJson(resultMap);
		}

	}
	@RequestMapping("/showActivation")
	@ResponseBody
	public String indexActivation(@RequestParam String userType,@RequestParam String userStatus){
		Map<String, Object> resultMap = new HashMap<>();
		//横坐标数据
		List<String> timeList = timeList();
		List<Integer> timeReusltList = new ArrayList<>();
		List<Integer> timeReusltList2 = new ArrayList<>();
		if(userType != null && !"".equals(userType)&& "ADMIN".equals(userType)){
			//查询今日活跃，每小时
			String activationTime = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			//String activationTime = "2017-09-29";
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("activationTime",activationTime);
			List<WtBaseCount> activationTimeList = wtActivationInfoService.timeTrend(paramMap);
			resultMap.put("XData", timeList);
			if(activationTimeList!=null && activationTimeList.size() >0 ){
				int timeCountSize = activationTimeList.size() -1;
				List<String> hourList = new ArrayList<>();
				for (WtBaseCount wtBaseCount : activationTimeList) {
					hourList.add(wtBaseCount.getHour());
				}
				int index = 0;
				for(int i = 0;i<timeList.size();i++){
					if(hourList.contains(timeList.get(i))){
						timeReusltList.add(activationTimeList.get(index).getCount());
						if (index >= 0 && index < timeCountSize) {
							index++;
						}
					}else{
						timeReusltList.add(0);
					}
				}
				resultMap.put("data1", timeReusltList);
				System.out.println(JsonUtils.objectToJson(resultMap));
			}else{
				for(int i = 0;i<timeList.size();i++){
					timeReusltList.add(0);
				}
				resultMap.put("data1", timeReusltList);
			}
			//查询昨日活跃，每小时
			paramMap.clear();
			String activationTime2 = getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE);
			//String activationTime = "2017-09-29";
			paramMap.put("activationTime",activationTime2);
			List<WtBaseCount> activationTimeList2 = wtActivationInfoService.timeTrend(paramMap);
			if(activationTimeList2!=null && activationTimeList2.size() >0 ){
				int timeCountSize2 = activationTimeList2.size() -1;
				List<String> hourList2 = new ArrayList<>();
				for (WtBaseCount wtBaseCount : activationTimeList2) {
					hourList2.add(wtBaseCount.getHour());
				}
				int index2 = 0;
				for(int i = 0;i<timeList.size();i++){
					if(hourList2.contains(timeList.get(i))){
						timeReusltList2.add(activationTimeList2.get(index2).getCount());
						if (index2 >= 0 && index2 < timeCountSize2) {
							index2++;
						}
					}else{
						timeReusltList2.add(0);
					}
				}
				resultMap.put("data2", timeReusltList2);
			}else{
				for(int i = 0;i<timeList.size();i++){
					timeReusltList2.add(0);
				}
				resultMap.put("data2", timeReusltList2);
			}
			
			return JsonUtils.objectToJson(resultMap);
		}else if(userType != null && !"".equals(userType)&&("department".equals(userType) || "worker".equals(userType))){
			List<String> devNameList = queryDepartDevList(userStatus);
			
			//查询今日活跃，每小时
			String activationTime = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE);
			//String activationTime = "2017-09-29";
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("devName", devNameList);
			paramMap.put("activationTime",activationTime);
			List<WtBaseCount> activationTimeList = wtActivationInfoService.timeTrend(paramMap);
			resultMap.put("XData", timeList);
			if(activationTimeList!=null && activationTimeList.size() >0 ){
				int timeCountSize = activationTimeList.size() -1;
				List<String> hourList = new ArrayList<>();
				for (WtBaseCount wtBaseCount : activationTimeList) {
					hourList.add(wtBaseCount.getHour());
				}
				int index = 0;
				for(int i = 0;i<timeList.size();i++){
					if(hourList.contains(timeList.get(i))){
						timeReusltList.add(activationTimeList.get(index).getCount());
						if (index >= 0 && index < timeCountSize) {
							index++;
						}
					}else{
						timeReusltList.add(0);
					}
				}
				resultMap.put("data1", timeReusltList);
				System.out.println(JsonUtils.objectToJson(resultMap));
			}else{
				for(int i = 0;i<timeList.size();i++){
					timeReusltList.add(0);
				}
				resultMap.put("data1", timeReusltList);
			}
			//查询昨日活跃，每小时
			paramMap.clear();
			String activationTime2 = getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE);
			//String activationTime = "2017-09-29";
			paramMap.put("devName", devNameList);
			paramMap.put("activationTime",activationTime2);
			List<WtBaseCount> activationTimeList2 = wtActivationInfoService.timeTrend(paramMap);
			if(activationTimeList2!=null && activationTimeList2.size() >0 ){
				int timeCountSize2 = activationTimeList2.size() -1;
				List<String> hourList2 = new ArrayList<>();
				for (WtBaseCount wtBaseCount : activationTimeList2) {
					hourList2.add(wtBaseCount.getHour());
				}
				int index2 = 0;
				for(int i = 0;i<timeList.size();i++){
					if(hourList2.contains(timeList.get(i))){
						timeReusltList2.add(activationTimeList2.get(index2).getCount());
						if (index2 >= 0 && index2 < timeCountSize2) {
							index2++;
						}
					}else{
						timeReusltList2.add(0);
					}
				}
				resultMap.put("data2", timeReusltList2);
			}else{
				for(int i = 0;i<timeList.size();i++){
					timeReusltList2.add(0);
				}
				resultMap.put("data2", timeReusltList2);
			}
			
			return JsonUtils.objectToJson(resultMap);
		}else{
			for(int i = 0;i<timeList.size();i++){
				timeReusltList.add(0);
			}
			resultMap.put("XData", timeList);
			resultMap.put("data1", timeReusltList);
			resultMap.put("data2", timeReusltList);
			return JsonUtils.objectToJson(resultMap);
		}

	}
	/**
	 * 获取事业部下所有的设备
	 * @Title:           queryDepartDevList
	 * @Description:     TODO
	 * @param:           @param departName
	 * @param:           @return   
	 * @return:          List<WtDeviceInfo>   
	 * @throws
	 */
	public List<String> queryDepartDevList(String departName){
		Map<String, Object> param = new HashMap<>();
		param.put("departName", departName);
		List<WtDeviceInfo> devList = wtDeviceInfoService.distanctData(param);
		if(devList != null && devList.size() > 0){
			List<String> devNameList = new ArrayList<>();
			for (WtDeviceInfo wtDeviceInfo : devList) {
				devNameList.add(wtDeviceInfo.getModel());
			}
			return devNameList;
		}
		return null;
	}
	
	public List<String> timeList(){
		List<String> timeList = new ArrayList<>();
		timeList.add("0");
		timeList.add("1");
		timeList.add("2");
		timeList.add("3");
		timeList.add("4");
		timeList.add("5");
		timeList.add("6");
		timeList.add("7");
		timeList.add("8");
		timeList.add("9");
		timeList.add("10");
		timeList.add("11");
		timeList.add("12");
		timeList.add("13");
		timeList.add("14");
		timeList.add("15");
		timeList.add("16");
		timeList.add("17");
		timeList.add("18");
		timeList.add("19");
		timeList.add("20");
		timeList.add("21");
		timeList.add("22");
		timeList.add("23");
		return timeList;
	}
	
	private static String getFormatPastDate(int past, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				- past);
		Date today = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String result = simpleDateFormat.format(today);
		return result;
	}
	/**
	 * 获取事业部下所有的设备
	 * @Title:           queryDepartDevList
	 * @Description:     TODO
	 * @param:           @param departName
	 * @param:           @return   
	 * @return:          List<WtDeviceInfo>   
	 * @throws
	 */
	public List<WtDeviceInfo> queryDepartDeviceInfoList(String departName){
		Map<String, Object> param = new HashMap<>();
		param.put("departName", departName);
		
		return wtDeviceInfoService.distanctData(param);
	}
	
}
