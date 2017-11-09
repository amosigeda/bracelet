package com.wtwd.gms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wtwd.gms.entity.WtActiveInfo;
import com.wtwd.gms.entity.WtBaseCount;
import com.wtwd.gms.entity.WtDeviceInfo;
import com.wtwd.gms.entity.WtMapResult;
import com.wtwd.gms.excel.DateUtil;
import com.wtwd.gms.service.WtActivationInfoService;
import com.wtwd.gms.service.WtActiveInfoService;
import com.wtwd.gms.service.WtDeviceInfoService;
import com.wtwd.gms.utils.JsonUtils;

/**
 * 地区分布
 * @author liufeng
 * @date 2017-6-29
 */
@Controller
@RequestMapping("/region")
public class RegionController extends BaseController {

	
	@Autowired
	private WtDeviceInfoService wtDeviceInfoService;
	@Autowired
	private WtActiveInfoService wtActiveInfoService;
	
	@Autowired
	private WtActivationInfoService wtActivationInfoService;
	@RequestMapping("/list")
	public String showList(){
		return "device/region_distribution";
	}
	
	@RequestMapping("/Map/active/{date}/{userType}/{userStatus}")
	@ResponseBody
	public String mapActive(@PathVariable String date,@PathVariable String userType,@PathVariable String userStatus){
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		Map<String,Object> paramMap = new HashMap<>();
		
		
		String begainDate = null;
		String endDate = null;
		//List<String> dateList = new ArrayList<String>();
		int dateNumber = Integer.valueOf(date);
		if(dateNumber > 0){
			begainDate = getPastDate(dateNumber-1);
			endDate = getPastDate(0);
		//	dateList = countDate(date);
		}else{
			begainDate = getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = getPastDate(0);
			//dateList.add(getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		
		if(userType != null && "ADMIN".equals(userType) && (!"".equals(userType))){
			//查找所有设备国家的类型
			//paramMap.put("date", date);
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			List<WtBaseCount> resultList = wtActiveInfoService.countActiveCountryName(paramMap);
			if(resultList != null && resultList.size() > 0){
				System.out.println(resultList.size());
			}
			result.put("data", resultList);
			/*String reString = JsonUtils.objectToJson(result);
			json.put("series", reString);*/
			/*JSONArray jsonArray = (JSONArray) JSONObject.toJSON(result);
			json.put("series", jsonArray);*/
		}else if(userType != null &&("department".equals(userType)||"worker".equals(userType))){
			//查找事业部机型
			List<WtDeviceInfo> devList = queryDepartDevList(userStatus);
			List<WtMapResult> mapReusltList = new ArrayList<>();
			List<String> devNameList = new ArrayList<>();
			if(devList != null && devList.size() >0){
				for (WtDeviceInfo wtDeviceInfo : devList) {
					devNameList.add(wtDeviceInfo.getModel());
				}
				//查询设备下的数据
				paramMap.put("devList",devNameList);
				paramMap.put("BegainDate", begainDate);
				paramMap.put("EndDate", endDate);
				//paramMap.put("date", date);
				System.out.println("fdasfffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
				List<WtBaseCount> resultList = wtActiveInfoService.getCountryMapData(paramMap);
				System.out.println(resultList.size()+"=================================================================");
				/*if(resultList != null && resultList.size() > 0){
					for (WtBaseCount resultBean : resultList) {
						WtMapResult wtMapResult = new WtMapResult();
						wtMapResult.setName(resultBean.getCountry());
						wtMapResult.setValue(resultBean.getCount());
						mapReusltList.add(wtMapResult);
					}
				}*/
				//System.out.println(mapReusltList.size());
				result.put("data", resultList);
				/*String reString = JsonUtils.objectToJson(result);
				json.put("series", reString);*/
				/*JSONArray jsonArray = (JSONArray) JSONObject.toJSON(result);
				json.put("series", jsonArray);*/
				
				//System.out.println(resultList.size());
			}else{
				WtBaseCount wtBaseCount = new WtBaseCount();
				wtBaseCount.setCountry("");
				wtBaseCount.setCount(0);
				json.put("data", wtBaseCount);
				return json.toString();
			}
		}
		return JsonUtils.objectToJson(result);
	}
	
	@RequestMapping("/Map/active/table/{date}/{userType}/{userStatus}")
	@ResponseBody
	public String mapTableActive(@PathVariable String date,@PathVariable String userType,@PathVariable String userStatus){
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		Map<String,Object> paramMap = new HashMap<>();
		List<WtBaseCount> resultList = new ArrayList<>();
		int sum =0;
		
		String begainDate = null;
		String endDate = null;
		//List<String> dateList = new ArrayList<String>();
		int dateNumber = Integer.valueOf(date);
		if(dateNumber > 0){
			begainDate = getPastDate(dateNumber-1);
			endDate = getPastDate(0);
		//	dateList = countDate(date);
		}else{
			begainDate = getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = getPastDate(0);
			//dateList.add(getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		
		if(userType != null && "ADMIN".equals(userType)){
			//查找所有设备国家的类型
			//paramMap.put("date", date);
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			List<WtBaseCount> dataList = wtActiveInfoService.countActiveCountryName(paramMap);
			int allData = wtActiveInfoService.countActiveNumber(paramMap);
			if(allData > 0){
				sum = allData;
				if(dataList != null && dataList.size() > 0){
					System.out.println(dataList.size());
					for (WtBaseCount wtBaseCount : dataList) {
						float occupancy = (float) ((float) wtBaseCount.getCount() / (float) sum) * 100;
						wtBaseCount.setOccupancy(occupancy+"%");
						resultList.add(wtBaseCount);
					}
				}
			}else{
				json.put("data", resultList);
				return json.toString();
			}
			JSONArray jsonArray = (JSONArray) JSONObject.toJSON(resultList);
			json.put("data", jsonArray);
		}else if(userType != null &&("department".equals(userType)||"worker".equals(userType))){
			List<WtDeviceInfo> devList = queryDepartDevList(userStatus);
			List<String> devNameList= new ArrayList<>();
			
			//paramMap.put("date",date);
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			if(devList != null && devList.size() > 0){
				
				paramMap.put("devList", devList);
				List<WtBaseCount> dataList = wtActiveInfoService.countActiveCountryName(paramMap);
				List<WtBaseCount> allData = wtActiveInfoService.countActiveData(paramMap);
				if(allData != null && allData.size() > 0){
					sum = allData.size();
					if(dataList != null && dataList.size() > 0){
						System.out.println(dataList.size());
						for (WtBaseCount wtBaseCount : dataList) {
							float occupancy = (float) ((float) wtBaseCount.getCount() / (float) sum) * 100;
							wtBaseCount.setOccupancy(occupancy+"%");
							resultList.add(wtBaseCount);
						}
					}
					JSONArray jsonArray = (JSONArray) JSONObject.toJSON(resultList);
					json.put("data", jsonArray);
				}else{
					json.put("data", resultList);
					return json.toString();
				}
				
			}else {
				json.put("data", resultList);
			}
			
			
		}
		return json.toString();
	}
	@RequestMapping(value="/Map/table/dev/{date}/{userType}/{userStatus}/{country}",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryTableDev(@PathVariable String date,@PathVariable String userType,@PathVariable String userStatus,@PathVariable String country){
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		Map<String,Object> paramMap = new HashMap<>();
		List<WtBaseCount> resultList = new ArrayList<>();
		

		String begainDate = null;
		String endDate = null;
		//List<String> dateList = new ArrayList<String>();
		int dateNumber = Integer.valueOf(date);
		if(dateNumber > 0){
			begainDate = getPastDate(dateNumber-1);
			endDate = getPastDate(0);
		//	dateList = countDate(date);
		}else{
			begainDate = getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = getPastDate(0);
			//dateList.add(getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		if(userType != null && "ADMIN".equals(userType)){
			int sum =0;
			float occupy = 0;
			//查找所有设备国家的类型
			//paramMap.put("date", date);
			
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			paramMap.put("countryName",country);
			List<WtBaseCount> dataList = wtActiveInfoService.queryDevName(paramMap);
			int allData = wtActiveInfoService.countActiveNumber(paramMap);
			if( allData > 0){
				sum = allData;
				if(dataList != null && dataList.size() > 0){
					System.out.println(dataList.size());
					for (WtBaseCount wtBaseCount : dataList) {
						float occupancy = (float) ((float) wtBaseCount.getCount() / (float) sum) * 100;
						wtBaseCount.setOccupancy(occupancy+"%");
						resultList.add(wtBaseCount);
					}
				}
			}else{
				json.put("data", resultList);
				return json.toString();
			}
			JSONArray jsonArray = (JSONArray) JSONObject.toJSON(resultList);
			json.put("data", jsonArray);
		}else if(userType != null &&("department".equals(userType)||"worker".equals(userType))){
			List<WtDeviceInfo> devList = queryDepartDevList(userStatus);
			List<String> devNameList = new ArrayList<>();
			int sum=0;
			if(devList != null && devList.size() > 0){
				/*for (WtDeviceInfo wtDeviceInfo : devList) {
					devNameList.add(wtDeviceInfo.getModel());
				}*/
				//paramMap.put("date", date);
				paramMap.put("BegainDate", begainDate);
				paramMap.put("EndDate", endDate);
				paramMap.put("countryName",country);
				paramMap.put("devList",devList);
				List<WtBaseCount> dataList = wtActiveInfoService.queryDevName(paramMap);
				List<WtBaseCount> allData = wtActiveInfoService.countActiveData(paramMap);
				if(allData != null && allData.size() > 0){
					sum = allData.size();
					if(dataList != null && dataList.size() > 0){
						System.out.println(dataList.size());
						for (WtBaseCount wtBaseCount : dataList) {
							float occupancy = (float) ((float) wtBaseCount.getCount() / (float) sum) * 100;
							wtBaseCount.setOccupancy(occupancy+"%");
							resultList.add(wtBaseCount);
						}
					}
				}else{
					json.put("data", resultList);
					return json.toString();
				}
				JSONArray jsonArray = (JSONArray) JSONObject.toJSON(resultList);
				json.put("data", jsonArray);
			}
		}
		return json.toString();
	}
	
	
	
	@RequestMapping("/Map/activation/{date}/{userType}/{userStatus}")
	@ResponseBody
	public String mapActivation(@PathVariable String date,@PathVariable String userType,@PathVariable String userStatus){
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		Map<String,Object> paramMap = new HashMap<>();
		
		String begainDate = null;
		String endDate = null;
		//List<String> dateList = new ArrayList<String>();
		int dateNumber = Integer.valueOf(date);
		if(dateNumber > 0){
			begainDate = getPastDate(dateNumber-1);
			endDate = getPastDate(0);
		//	dateList = countDate(date);
		}else{
			begainDate = getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = getPastDate(0);
			//dateList.add(getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		if(userType != null && "ADMIN".equals(userType) && (!"".equals(userType))){
			//查找所有设备国家的类型
			//paramMap.put("date", date);
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			List<WtBaseCount> resultList = wtActivationInfoService.countActivationCountryName(paramMap);
			
			result.put("data", resultList);
			/*String reString = JsonUtils.objectToJson(result);
			json.put("series", reString);*/
			/*JSONArray jsonArray = (JSONArray) JSONObject.toJSON(result);
			json.put("series", jsonArray);*/
		}else if(userType != null &&("department".equals(userType)||"worker".equals(userType))){
			//查找事业部机型
			List<WtDeviceInfo> devList = queryDepartDevList(userStatus);
			List<WtMapResult> mapReusltList = new ArrayList<>();
			List<String> devNameList = new ArrayList<>();
			if(devList != null && devList.size() >0){
				for (WtDeviceInfo wtDeviceInfo : devList) {
					devNameList.add(wtDeviceInfo.getModel());
				}
				//查询设备下的数据
				paramMap.put("devList",devNameList);
				//paramMap.put("date", date);
				paramMap.put("BegainDate", begainDate);
				paramMap.put("EndDate", endDate);
				System.out.println("fdasfffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
				List<WtBaseCount> resultList = wtActivationInfoService.getCountryMapData(paramMap);
				System.out.println(resultList.size()+"=================================================================");
				/*if(resultList != null && resultList.size() > 0){
					for (WtBaseCount resultBean : resultList) {
						WtMapResult wtMapResult = new WtMapResult();
						wtMapResult.setName(resultBean.getCountry());
						wtMapResult.setValue(resultBean.getCount());
						mapReusltList.add(wtMapResult);
					}
				}*/
				//System.out.println(mapReusltList.size());
				result.put("data", resultList);
				/*String reString = JsonUtils.objectToJson(result);
				json.put("series", reString);*/
				/*JSONArray jsonArray = (JSONArray) JSONObject.toJSON(result);
				json.put("series", jsonArray);*/
				
				//System.out.println(resultList.size());
			}else{
				WtBaseCount wtBaseCount = new WtBaseCount();
				wtBaseCount.setCountry("");
				wtBaseCount.setCount(0);
				json.put("data", wtBaseCount);
				return json.toString();
			}
		}
		return JsonUtils.objectToJson(result);
	}
	
	@RequestMapping("/Map/activation/table/{date}/{userType}/{userStatus}")
	@ResponseBody
	public String mapTableActivation(@PathVariable String date,@PathVariable String userType,@PathVariable String userStatus){
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		Map<String,Object> paramMap = new HashMap<>();
		List<WtBaseCount> resultList = new ArrayList<>();
		int sum =0;
		
		String begainDate = null;
		String endDate = null;
		//List<String> dateList = new ArrayList<String>();
		int dateNumber = Integer.valueOf(date);
		if(dateNumber > 0){
			begainDate = getPastDate(dateNumber-1);
			endDate = getPastDate(0);
		//	dateList = countDate(date);
		}else{
			begainDate = getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = getPastDate(0);
			//dateList.add(getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		if(userType != null && "ADMIN".equals(userType)){
			//查找所有设备国家的类型
			//paramMap.put("date", date);
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			List<WtBaseCount> dataList = wtActivationInfoService.countActivationCountryName(paramMap);
			int allData = wtActivationInfoService.countActivationData(paramMap);
			if( allData > 0){
				sum = allData;
				if(dataList != null && dataList.size() > 0){
					System.out.println(dataList.size());
					for (WtBaseCount wtBaseCount : dataList) {
						float occupancy = (float) ((float) wtBaseCount.getCount() / (float) sum) * 100;
						wtBaseCount.setOccupancy(occupancy+"%");
						resultList.add(wtBaseCount);
					}
				}else{
					json.put("data", resultList);
					return json.toString();
				}
			}else{
				json.put("data", "");
				return json.toString();
			}
			JSONArray jsonArray = (JSONArray) JSONObject.toJSON(resultList);
			json.put("data", jsonArray);
		}else if(userType != null &&("department".equals(userType)||"worker".equals(userType))){
			List<WtDeviceInfo> devList = queryDepartDevList(userStatus);
			List<String> devNameList= new ArrayList<>();
			
			//paramMap.put("date",date);
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			if(devList != null && devList.size() > 0){
				
				paramMap.put("devList", devList);
				List<WtBaseCount> dataList = wtActivationInfoService.countActivationCountryName(paramMap);
				int allData = wtActivationInfoService.countActivationData(paramMap);
				if(allData > 0  ){
					sum = allData;
					if(dataList != null && dataList.size() > 0){
						//System.out.println(dataList.size());
						for (WtBaseCount wtBaseCount : dataList) {
							float occupancy = (float) ((float) wtBaseCount.getCount() / (float) sum) * 100;
							wtBaseCount.setOccupancy(occupancy+"%");
							resultList.add(wtBaseCount);
						}
					}else{
						json.put("data", resultList);
						return json.toString();
					}
					JSONArray jsonArray = (JSONArray) JSONObject.toJSON(resultList);
					json.put("data", jsonArray);
				}else{
					json.put("data", resultList);
					return json.toString();
				}
				
			}else {
				json.put("data", resultList);
			}
			
			
		}
		return json.toString();
	}
	
	@RequestMapping(value="/Map/table/dev/activation/{date}/{userType}/{userStatus}/{country}",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryActivationTableDev(@PathVariable String date,@PathVariable String userType,@PathVariable String userStatus,@PathVariable String country){
		Map<String, Object> result = new HashMap<String, Object>();
		JSONObject json = new JSONObject();
		Map<String,Object> paramMap = new HashMap<>();
		List<WtBaseCount> resultList = new ArrayList<>();
		System.out.println("ddddddddddddddddddddd");
		
		String begainDate = null;
		String endDate = null;
		//List<String> dateList = new ArrayList<String>();
		int dateNumber = Integer.valueOf(date);
		if(dateNumber > 0){
			begainDate = getPastDate(dateNumber-1);
			endDate = getPastDate(0);
		//	dateList = countDate(date);
		}else{
			begainDate = getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = getPastDate(0);
			//dateList.add(getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		if(userType != null && "ADMIN".equals(userType)){
			int sum =0;
			float occupy = 0;
			//查找所有设备国家的类型
			//paramMap.put("date", date);
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			paramMap.put("countryName",country);
			List<WtBaseCount> dataList = wtActivationInfoService.queryDevName(paramMap);
			int allData = wtActivationInfoService.countActivationData(paramMap);
			if(allData > 0){
				sum = allData;
				if(dataList != null && dataList.size() > 0){
					System.out.println(dataList.size());
					for (WtBaseCount wtBaseCount : dataList) {
						float occupancy = (float) ((float) wtBaseCount.getCount() / (float) sum) * 100;
						wtBaseCount.setOccupancy(occupancy+"%");
						resultList.add(wtBaseCount);
					}
				}
			}else{
				json.put("data", resultList);
				return json.toString();
			}
			JSONArray jsonArray = (JSONArray) JSONObject.toJSON(resultList);
			json.put("data", jsonArray);
		}else if(userType != null &&("department".equals(userType)||"worker".equals(userType))){
			List<WtDeviceInfo> devList = queryDepartDevList(userStatus);
			List<String> devNameList = new ArrayList<>();
			int sum=0;
			if(devList != null && devList.size() > 0){
				/*for (WtDeviceInfo wtDeviceInfo : devList) {
					devNameList.add(wtDeviceInfo.getModel());
				}*/
				//paramMap.put("date", date);
				paramMap.put("BegainDate", begainDate);
				paramMap.put("EndDate", endDate);
				paramMap.put("countryName",country);
				paramMap.put("devList",devList);
				List<WtBaseCount> dataList = wtActivationInfoService.queryDevName(paramMap);
				int allData = wtActivationInfoService.countActivationData(paramMap);
				if(allData > 0){
					sum = allData;
					if(dataList != null && dataList.size() > 0){
						System.out.println(dataList.size());
						for (WtBaseCount wtBaseCount : dataList) {
							float occupancy = (float) ((float) wtBaseCount.getCount() / (float) sum) * 100;
							wtBaseCount.setOccupancy(occupancy+"%");
							resultList.add(wtBaseCount);
						}
					}
				}else{
					json.put("data", resultList);
					return json.toString();
				}
				JSONArray jsonArray = (JSONArray) JSONObject.toJSON(resultList);
				json.put("data", jsonArray);
			}
		}
		return json.toString();
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
	public List<WtDeviceInfo> queryDepartDevList(String departName){
		Map<String, Object> param = new HashMap<>();
		param.put("departName", departName);
		
		return wtDeviceInfoService.distanctData(param);
	}
	
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				- past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}
	
	public static ArrayList<String> countDate(int intervals) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		ArrayList<String> fetureDaysList = new ArrayList<>();
		for (int i = 0; i < intervals; i++) {
			pastDaysList.add(getPastDate(i));
			fetureDaysList.add(getFutureDate(i));
		}
		return pastDaysList;
	}
	
	private static String getFutureDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				+ past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
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
}
