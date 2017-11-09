
package com.wtwd.gms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wtwd.gms.entity.EchartJson;
import com.wtwd.gms.entity.WtActivationInfo;
import com.wtwd.gms.entity.WtBaseCount;
import com.wtwd.gms.entity.WtDepartmentInfo;
import com.wtwd.gms.entity.WtDevice;
import com.wtwd.gms.entity.WtDeviceDateCount;
import com.wtwd.gms.entity.WtDeviceInfo;
import com.wtwd.gms.entity.EchartJson.Item;
import com.wtwd.gms.excel.DateUtil;
import com.wtwd.gms.service.WtActivationInfoService;
import com.wtwd.gms.service.WtDepartmentInfoService;
import com.wtwd.gms.service.WtDeviceInfoService;
import com.wtwd.gms.service.WtDeviceService;
import com.wtwd.gms.utils.JsonUtils;



/**
 * 设备激活
 * @author huangzhe
 * @data 2017年8月24日
 */
@Controller
@RequestMapping("/device/activation")
public class DeviceActivationController extends BaseController{


	@Autowired
	private WtActivationInfoService wtActivationInfoService;
	
	@Autowired
	private WtDepartmentInfoService wtDepartmentInfoService;
	
	@Autowired
	private WtDeviceService wtDeviceService;
	
	@Autowired
	private WtDeviceInfoService wtDeviceInfoService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		
		return "device/device_activation";
	}
	@RequestMapping("/baseData")
	@ResponseBody
	public String baseData(@RequestParam String userType,@RequestParam String userStatus){
	
		JSONObject jsonObject = new JSONObject();
		if(userType != null && !"".equals(userType)&& "ADMIN".equals(userType)){
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(DateUtil.format(new Date(),
					DateUtil.PATTERN_CLASSICAL_SIMPLE));
			map.put("activation_time",
					DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE));
			// map.put("i",1 );
			int todayActivation = wtActivationInfoService.queryActivationCount(map);
			System.out.println("今日活跃" + todayActivation);
			//model.addAttribute("todayActivationData", todayActivation.size());
			map.clear();
			map.put("yesTodayDate",
					getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			System.out.println(getFormatPastDate(1,
					DateUtil.PATTERN_CLASSICAL_SIMPLE));
			
			int yesTodayActivation = wtActivationInfoService.queryActivationCount(map);
			System.out.println("昨日活跃" + yesTodayActivation);
			//model.addAttribute("yesterdayActivationData", yesTodayActivation.size());
			map.clear();
			map.put("weekBegainDate",
					getFormatPastDate(6, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			map.put("weekEndDate",
					DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE));
			System.out.println(DateUtil.format(new Date(),
					DateUtil.PATTERN_CLASSICAL_SIMPLE)
					+ " "
					+ getFormatPastDate(7, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			
			int weekBegainActivation = wtActivationInfoService.queryActivationCount(map);
			System.out.println("七日活跃" + weekBegainActivation);
			//model.addAttribute("sevenActivationData", weekBegainActivation.size());
			map.clear();
			map.put("allData",
					getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			int allData = wtActivationInfoService.queryActivationCount(map);
			
			System.out.println("累积活跃" + allData);
			//model.addAttribute("allActivationData", allData.size());
			jsonObject.put("todayActivation",todayActivation);
			jsonObject.put("yesTodayActivation", yesTodayActivation);
			jsonObject.put("weekBegainActivation", weekBegainActivation);
			jsonObject.put("allData", allData);
		}else if(userType != null && !"".equals(userType)&&("department".equals(userType) || "worker".equals(userType))){
			
			List<WtDeviceInfo> devNameList = queryDepartDevList(userStatus);
			if(devNameList == null || devNameList.size() <= 0){
				jsonObject.put("todayActivation",0);
				jsonObject.put("yesTodayActivation", 0);
				jsonObject.put("weekBegainActivation", 0);
				jsonObject.put("allData",0);
				return jsonObject.toString();
			}
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(DateUtil.format(new Date(),
					DateUtil.PATTERN_CLASSICAL_SIMPLE));
			map.put("activation_time",
					DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE));
			// map.put("i",1 );
			map.put("devList", devNameList);
			int todayActivation = wtActivationInfoService.queryActivationCount(map);
			System.out.println("今日活跃" + todayActivation);
			//model.addAttribute("todayActivationData", todayActivation.size());
			map.clear();
			map.put("yesTodayDate",
					getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			System.out.println(getFormatPastDate(1,
					DateUtil.PATTERN_CLASSICAL_SIMPLE));
			map.put("devList", devNameList);
			int yesTodayActivation = wtActivationInfoService.queryActivationCount(map);
			System.out.println("昨日活跃" + yesTodayActivation);
			//model.addAttribute("yesterdayActivationData", yesTodayActivation.size());
			map.clear();
			map.put("weekBegainDate",
					getFormatPastDate(6, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			map.put("weekEndDate",
					DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE));
			System.out.println(DateUtil.format(new Date(),
					DateUtil.PATTERN_CLASSICAL_SIMPLE)
					+ " "
					+ getFormatPastDate(7, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			map.put("devList", devNameList);
			int weekBegainActivation = wtActivationInfoService.queryActivationCount(map);
			System.out.println("七日活跃" + weekBegainActivation);
			//model.addAttribute("sevenActivationData", weekBegainActivation.size());
			map.clear();
			map.put("allData",
					getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			map.put("devList", devNameList);
			int allData = wtActivationInfoService.queryActivationCount(map);
			
			System.out.println("累积活跃" + allData);
			//model.addAttribute("allActivationData", allData.size());
			jsonObject.put("todayActivation",todayActivation);
			jsonObject.put("yesTodayActivation", yesTodayActivation);
			jsonObject.put("weekBegainActivation", weekBegainActivation);
			jsonObject.put("allData", allData);
		}
		
		return jsonObject.toString();
	}

	
	@RequestMapping("/getChartData/device/{date}")
	@ResponseBody
	public String countActivationDevice(@PathVariable int date) {
		Map<String, Object> paramMap = new HashMap<>();
		Map<String, Object> paramMap2 = new HashMap<>();
		
		
		String begainDate = null;
		String endDate = null;
		List<String> dateList = new ArrayList<String>();
		if(date > 0){
			begainDate = getPastDate(date-1);
			endDate = getPastDate(0);
			dateList = countDate(date);
		}else{
			begainDate = getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = getPastDate(0);
			dateList.add(getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		paramMap.put("BegainDate", begainDate);
		paramMap.put("EndDate", endDate);
		//paramMap.put("date", date);

		// paramMap.put("countryName", "美国");
		// 获取N天活跃最多的五个设备名称
		List<WtBaseCount> devNameList = wtActivationInfoService
				.countActivationDeviceName(paramMap);
		//List<String> dateList = countDate(date);
		// List<Double> countList = new ArrayList<>();
		/*for (String string : dateList) {
			System.out.println(string + "fsadf");
		}*/
		int devDateCountSize;
		EchartJson echartJson = new EchartJson();
		System.out.println("devNameList :" + devNameList.size());
		echartJson.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
		if (devNameList != null && devNameList.size() > 0) {
			System.out.println(devNameList.size());
			paramMap2.clear();
			for (WtBaseCount wtBaseCount : devNameList) {
				Item item = echartJson.new Item();
				paramMap2.put("devName", wtBaseCount.getDev_name());
				//paramMap2.put("date", date);
				paramMap2.put("BegainDate", begainDate);
				paramMap2.put("EndDate", endDate);
				item.setName(wtBaseCount.getDev_name());
				// 获取某个设备名称N天的活跃记录
				List<WtDeviceDateCount> devDateCountList = wtActivationInfoService
						.countActivationDeviceEveryDay(paramMap2);

				if (devDateCountList != null && devDateCountList.size() > 0) {
					List<String> devDateCountDateList = new ArrayList<>();
					// 获取某设备N天活跃的记录日期
					for (WtDeviceDateCount wtDeviceDateCount : devDateCountList) {
						devDateCountDateList.add(DateUtil.format(
								wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
					}
					System.out.println(devDateCountList.size());
					devDateCountSize = devDateCountList.size() - 1;
					int index = 0;
					List<Long> countData = new ArrayList<>();
					for (int i = 0; i < dateList.size(); i++) {
						if (devDateCountDateList.contains(dateList.get(i))) {
							countData.add(devDateCountList.get(index)
									.getCount());
							if (index >= 0 && index < devDateCountSize) {
								index++;
							}
						} else {
							countData.add(0L);
						}
					}
					item.setData(countData);
					echartJson.getItemData().add(item);
				}
			}
		} else {
			Item item = echartJson.new Item();
			item.setName("");
			List<Long> countData = new ArrayList<>();
			for (int i = 0; i < dateList.size(); i++) {
				countData.add(0L);
			}
			item.setData(countData);
			echartJson.getItemData().add(item);
		}
		return JsonUtils.objectToJson(echartJson);
	}
					
	/**
	 * 
	 * @Title:           countActiveCountry
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping(value = "/getChartData/country/{date}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String countActiveCountry(@PathVariable int date) {
		Map<String, Object> countryNameParamMap = new HashMap<String, Object>();
		Map<String, Object> countryEveryParamMap = new HashMap<String, Object>();
		
		String begainDate = null;
		String endDate = null;
		List<String> dateList = new ArrayList<String>();
		if(date > 0){
			begainDate = getPastDate(date-1);
			endDate = getPastDate(0);
			dateList = countDate(date);
		}else{
			begainDate = getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = getPastDate(0);
			dateList.add(getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		countryNameParamMap.put("BegainDate", begainDate);
		countryNameParamMap.put("EndDate", endDate);
		
		//countryNameParamMap.put("date", date);

		List<WtBaseCount> countryNameList = wtActivationInfoService
				.countActivationCountryName(countryNameParamMap);
		//List<String> dateList = countDate(date);
		int countryDateCountSize;
		EchartJson echartJson = new EchartJson();
		echartJson.setxAxisData(dateList);
		if (countryNameList != null && countryNameList.size() > 0) {
			for (WtBaseCount wtBaseCount : countryNameList) {
				Item item = echartJson.new Item();
				System.out.println(wtBaseCount.getCountry());
				item.setName(wtBaseCount.getCountry());
				countryEveryParamMap.clear();
				countryEveryParamMap.put("countryName",
						wtBaseCount.getCountry());
				//countryEveryParamMap.put("date", date);
				countryEveryParamMap.put("BegainDate", begainDate);
				countryEveryParamMap.put("EndDate", endDate);
				List<WtDeviceDateCount> countryDateCountList = wtActivationInfoService
						.countActivationCountryEveryDay(countryEveryParamMap);
				List<String> countryDateCountDateList = new ArrayList<>();
				for (WtDeviceDateCount wtDeviceDateCount : countryDateCountList) {
					countryDateCountDateList.add(DateUtil.format(
							wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
				}
				if (countryDateCountList != null
						&& countryDateCountList.size() > 0) {
					countryDateCountSize = countryDateCountList.size() - 1;
					int index = 0;
					List<Long> countData = new ArrayList<>();
					for (WtDeviceDateCount wtDeviceDateCount : countryDateCountList) {
						System.out.println(wtDeviceDateCount.getDate());
					}
					for (int i = 0; i < dateList.size(); i++) {
						if (countryDateCountDateList.contains(dateList.get(i))) {
							countData.add(countryDateCountList.get(index)
									.getCount());
							if (index >= 0 && index < countryDateCountSize) {
								index++;
							}
						} else {
							countData.add(0L);
						}
					}
					item.setData(countData);
					echartJson.getItemData().add(item);
				}
			}
		} else {
			Item item = echartJson.new Item();
			item.setName("");
			List<Long> countData = new ArrayList<>();
			for (int i = 0; i < dateList.size(); i++) {
				countData.add(0L);
			}
			item.setData(countData);
			echartJson.getItemData().add(item);
		}
		System.out.println(JsonUtils.objectToJson(echartJson));
		return JsonUtils.objectToJson(echartJson);
	}
	
	/**
	 * 
	 * @Title:           getDeviceTbale
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping("/getTableData/device/{date}")
	@ResponseBody
	public String getDeviceTbale(@PathVariable int date) {
		Map<String, Object> devNameParam = new HashMap<>();
		Map<String, Object> devActiveParam = new HashMap<>();
		//devNameParam.put("date", date);
		devNameParam.put("limit", 5);
		
		String begainDate = null;
		String endDate = null;
		//List<String> dateList = new ArrayList<String>();
		if(date > 0){
			begainDate = getPastDate(date-1);
			endDate = getPastDate(0);
			//dateList = countDate(date);
		}else{
			begainDate = getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = getPastDate(0);
			//dateList.add(getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		devNameParam.put("BegainDate", begainDate);
		devNameParam.put("EndDate", endDate);
		/*String todayDate = getPastDate(0);
		String dateTime;
		if (date == 7) {
			dateTime = getPastDate(7);
		} else {
			dateTime = getPastDate(30);
		}*/

		// String lastMothDate = getPastDate(20);
		List<WtBaseCount> devNameList = wtActivationInfoService
				.countActivationDeviceName(devNameParam);
		List<WtBaseCount> resultData = new ArrayList<>();
		if (devNameList != null && devNameList.size() > 0) {
			System.out.println(devNameList.size());
			for (WtBaseCount wtBaseCount : devNameList) {
				WtBaseCount resultBean = new WtBaseCount();
				//int sumActive = 0;// 累积活跃
				int dayActive = 0;// N天活跃
				devActiveParam.clear();
				System.out.println(wtBaseCount.getDev_name());
				devActiveParam.put("devName", wtBaseCount.getDev_name());
				// 某设备总活跃
				int devSumActiveList = wtActivationInfoService
						.queryActivationCount(devActiveParam);
				/*if (devSumActiveList != null && devSumActiveList.size() > 0) {
					System.out.println("devsumActiveList :"
							+ devSumActiveList.size());
					sumActive = devSumActiveList.size();
				}*/
				/*devActiveParam.put("weekBegainDate", dateTime);
				devActiveParam.put("weekEndDate", todayDate);*/
				devActiveParam.put("BegainDate", begainDate);
				devActiveParam.put("EndDate", endDate);

				// List<WtDeviceDateCount> devActiveList =
				// wtActiveInfoService.countActiveDeviceEveryDay(devActiveParam);
				 dayActive = wtActivationInfoService
						.queryActivationCount(devActiveParam);
				//dayActive = devDateActiveList.size();

				resultBean.setDev_name(wtBaseCount.getDev_name());
				resultBean.setDayActive(dayActive);
				resultBean.setSum(devSumActiveList);

				resultData.add(resultBean);
			}
		}
		return JsonUtils.objectToJson(resultData);
	}
	/**
	 * 
	 * @Title:           getCountryTbale
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping(value = "/getTableData/country/{date}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getCountryTbale(@PathVariable int date) {
		Map<String, Object> countryNameParam = new HashMap<>();
		Map<String, Object> countryActiveParam = new HashMap<>();
		String begainDate = null;
		String endDate = null;
		//List<String> dateList = new ArrayList<String>();
		if(date > 0){
			begainDate = getPastDate(date-1);
			endDate = getPastDate(0);
			//dateList = countDate(date);
		}else{
			begainDate = getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = getPastDate(0);
			//dateList.add(getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		countryNameParam.put("BegainDate", begainDate);
		countryNameParam.put("EndDate", endDate);
		//countryNameParam.put("date", date);
		countryNameParam.put("limit", 5);
		/*String todayDate = getPastDate(0);
		String dateTime;
		if (date == 7) {
			dateTime = getPastDate(7);
		} else {
			dateTime = getPastDate(30);
		}*/
		// String lastMothDate = getPastDate(20);
		List<WtBaseCount> countryNameList = wtActivationInfoService
				.countActivationCountryName(countryNameParam);
		List<WtBaseCount> resultData = new ArrayList<>();
		if (countryNameList != null && countryNameList.size() > 0) {
			System.out.println(countryNameList.size());
			for (WtBaseCount wtBaseCount : countryNameList) {
				WtBaseCount resultBean = new WtBaseCount();
				//int sumActive = 0;// 累积活跃
				//int dayActive = 0;// N天活跃
				countryActiveParam.clear();
				System.out.println(wtBaseCount.getCountry());
				countryActiveParam.put("countryName", wtBaseCount.getCountry());
				// 某设备总活跃
				int devSumActiveList = wtActivationInfoService
						.queryActivationCount(countryActiveParam);
				/*if (devSumActiveList != null && devSumActiveList.size() > 0) {
					System.out.println("devsumActiveList :"
							+ devSumActiveList.size());
					sumActive = devSumActiveList.size();
				}*/
				/*countryActiveParam.put("weekBegainDate", dateTime);
				countryActiveParam.put("weekEndDate", todayDate);*/
				countryActiveParam.put("BegainDate", begainDate);
				countryActiveParam.put("EndDate", endDate);
				// List<WtDeviceDateCount> devActiveList =
				// wtActiveInfoService.countActiveDeviceEveryDay(devActiveParam);
				int devDateActiveList = wtActivationInfoService
						.queryActivationCount(countryActiveParam);
				//dayActive = devDateActiveList.size();

				resultBean.setCountry(wtBaseCount.getCountry());
				resultBean.setDayActive(devDateActiveList);
				resultBean.setSum(devSumActiveList);

				resultData.add(resultBean);
			}
		}
		return JsonUtils.objectToJson(resultData);
	}
	
	
	/**
	 * 
	 * @Title:           getSearchCountry
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @param departName
	 * @param:           @param devName
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */

	/**
	 * 事业部
	 * @Title:           getDepartment
	 * @Description:     TODO
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping("/getDepartment")
	@ResponseBody
	public String getDepartment() {

		List<WtDepartmentInfo> departmentInfos = wtDepartmentInfoService
				.queryAllDepartmentInfo();
		if (departmentInfos != null && departmentInfos.size() > 0) {
			return JsonUtils.objectToJson(departmentInfos);
		} else {
			return null;
		}
	}
	
	@RequestMapping("/getDevice")
	@ResponseBody
	public String getDevice() {
		List<WtDevice> dev = new ArrayList<>();
		List<WtDevice> devices = wtDeviceService.queryAllDepartmentInfo();
		if (devices != null && devices.size() > 0) {
			return JsonUtils.objectToJson(devices);
		} else {
			return JsonUtils.objectToJson(dev);
		}
	}
	

/**
 * 
 * @Title:           getSearchDevice
 * @Description:     TODO
 * @param:           @param date
 * @param:           @param departName
 * @param:           @param country
 * @param:           @return   
 * @return:          String   
 * @throws
 */
	
	@RequestMapping("/getSearch/device/{date}")
	@ResponseBody
	public String getSearchDevice(@PathVariable int date,
			@RequestParam(value = "", required = false) String departName,
			@RequestParam(value = "", required = false) String country) {
		Map<String, Object> param = new HashMap<>();
		List<WtDeviceInfo> departDevNameList = new ArrayList<>();
		List<WtBaseCount> activationDevNameList = new ArrayList<>();
		System.out.println(departName);
		EchartJson echartJson = new EchartJson();
	//	List<String> dateList = countDate(date);
		echartJson.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
		
		String begainDate = null;
		String endDate = null;
		List<String> dateList = new ArrayList<String>();
		if(date > 0){
			begainDate = getPastDate(date-1);
			endDate = getPastDate(0);
			dateList = countDate(date);
		}else{
			begainDate = getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = getPastDate(0);
			dateList.add(getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		
		
		if (departName != null && !"".equals(departName)) {
			
			param.put("departName", departName);
			// 查找事业部下所有的设备名
				departDevNameList = wtDeviceInfoService.distanctData(param);
				if (departDevNameList != null && departDevNameList.size() > 0) {
					System.out.println("事业部下的设备数 " + departDevNameList.size());
				} else {
					Item item = echartJson.new Item();
					item.setName("");
					List<Long> countData = new ArrayList<>();
					for (int i = 0; i < dateList.size(); i++) {
						countData.add(0L);
					}
					item.setData(countData);
					echartJson.getItemData().add(item);
					return JsonUtils.objectToJson(echartJson);
				}
				// N天活跃的设备名list

				param.clear();
				if (country != null && !"".equals(country)) {
					param.put("countryName", country);
					System.out.println("国家" + country);
				}
				//param.put("date", date);
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				param.put("departName", departName);
				activationDevNameList = wtActivationInfoService.countActivationDeviceName(param);
				System.out.println("N天活跃的设备数" + activationDevNameList.size());
				;

				if (activationDevNameList != null && activationDevNameList.size() > 0) {
					
					
					List<String> devNameList = new ArrayList<>();
					
					List<String> activationDevName = new ArrayList<>();
					for (WtBaseCount baseBean : activationDevNameList) {
						activationDevName.add(baseBean.getDev_name());
					}
					for (WtDeviceInfo devInfo : departDevNameList) {
						devNameList.add(devInfo.getModel());
					}
					List<String> devName = new ArrayList<>();
					
					for (String departDevName : activationDevName) {
						if (devNameList.contains(departDevName)) {
							devName.add(departDevName);
							System.out
									.println("某事业部、在某国家N天活跃的全部设备" + departDevName);
						}
					}
					if (devName != null && devName.size() > 0) {
						int devDateCountSize;
						echartJson
								.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
						Map<String, Object> paramMap = new HashMap<>();
						for (String devname : devName) {
							Item item = echartJson.new Item();
							if (country != null && !"".equals(country)) {
								paramMap.put("countryName", country);
								
							}
							paramMap.put("devName", devname);
							//paramMap.put("date", date);
							paramMap.put("BegainDate", begainDate);
							paramMap.put("EndDate", endDate);
							item.setName(devname);
							
							List<WtDeviceDateCount> devDateCountList = wtActivationInfoService
									.countActivationDeviceEveryDay(paramMap);

							if (devDateCountList != null
									&& devDateCountList.size() > 0) {
								List<String> devDateCountDateList = new ArrayList<>();
								
								for (WtDeviceDateCount wtDeviceDateCount : devDateCountList) {
									devDateCountDateList.add(DateUtil.format(
											wtDeviceDateCount.getDate(),
											"yyyy-MM-dd"));
								}
								System.out.println(devDateCountList.size());
								devDateCountSize = devDateCountList.size() - 1;
								int index = 0;
								List<Long> countData = new ArrayList<>();
								for (int i = 0; i < dateList.size(); i++) {
									if (devDateCountDateList.contains(dateList
											.get(i))) {
										countData.add(devDateCountList.get(index)
												.getCount());
										if (index >= 0 && index < devDateCountSize) {
											index++;
										}
									} else {
										countData.add(0L);
									}
								}
								item.setData(countData);
								echartJson.getItemData().add(item);
							}
						}
					} else {
						Item item = echartJson.new Item();
						item.setName("");
						List<Long> countData = new ArrayList<>();
						for (int i = 0; i < dateList.size(); i++) {
							countData.add(0L);
						}
						item.setData(countData);
						echartJson.getItemData().add(item);
					}
				} else {
					Item item = echartJson.new Item();
					item.setName("");
					List<Long> countData = new ArrayList<>();
					for (int i = 0; i < dateList.size(); i++) {
						countData.add(0L);
					}
					item.setData(countData);
					echartJson.getItemData().add(item);
				}
			} else if (country != null && !"".equals(country)) {
				System.out.println("国家" + country);
				Map<String, Object> paramMap = new HashMap<>();
				//paramMap.put("date", date);
				paramMap.put("BegainDate", begainDate);
				paramMap.put("EndDate", endDate);
				paramMap.put("countryName", country);
				List<WtBaseCount> devNameList = new ArrayList<>();
				devNameList = wtActivationInfoService.countActivationDeviceName(paramMap);
				
				int devDateCountSize;
				echartJson.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
				if (devNameList != null && devNameList.size() > 0) {
					Map<String, Object> paramMap2 = new HashMap<>();
					for (WtBaseCount wtBaseCount : devNameList) {
						Item item = echartJson.new Item();
						if (country != null && !"".equals(country)) {
							paramMap2.put("countryName", country);
							
						}
						paramMap2.put("devName", wtBaseCount.getDev_name());
						//paramMap2.put("date", date);
						paramMap2.put("BegainDate", begainDate);
						paramMap2.put("EndDate", endDate);
						item.setName(wtBaseCount.getDev_name());
						// 获取某个设备名称N天的活跃记录
						List<WtDeviceDateCount> devDateCountList = wtActivationInfoService
								.countActivationDeviceEveryDay(paramMap2);

						if (devDateCountList != null && devDateCountList.size() > 0) {
							List<String> devDateCountDateList = new ArrayList<>();
							// 获取某设备N天活跃的记录日期
							for (WtDeviceDateCount wtDeviceDateCount : devDateCountList) {
								devDateCountDateList.add(DateUtil.format(
										wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
							}
							System.out.println(devDateCountList.size());
							devDateCountSize = devDateCountList.size() - 1;
							int index = 0;
							List<Long> countData = new ArrayList<>();
							for (int i = 0; i < dateList.size(); i++) {
								if (devDateCountDateList.contains(dateList.get(i))) {
									countData.add(devDateCountList.get(index)
											.getCount());
									if (index >= 0 && index < devDateCountSize) {
										index++;
									}
								} else {
									countData.add(0L);
								}
							}
							item.setData(countData);
							echartJson.getItemData().add(item);
						}
					}
				} else {
					Item item = echartJson.new Item();
					item.setName("");
					List<Long> countData = new ArrayList<>();
					for (int i = 0; i < dateList.size(); i++) {
						countData.add(0L);
					}
					item.setData(countData);
					echartJson.getItemData().add(item);
				}
			} else {
				Map<String, Object> paramMap = new HashMap<>();
				Map<String, Object> paramMap2 = new HashMap<>();
				//paramMap.put("date", date);
				paramMap.put("BegainDate", begainDate);
				paramMap.put("EndDate", endDate);
				// 获取N天活跃最多的五个设备名称
				List<WtBaseCount> devNameList = wtActivationInfoService
						.countActivationDeviceName(paramMap);
				int devDateCountSize;
				System.out.println("devNameList :" + devNameList.size());
				echartJson.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
				if (devNameList != null && devNameList.size() > 0) {
					System.out.println(devNameList.size());
					paramMap2.clear();
					for (WtBaseCount wtBaseCount : devNameList) {
						Item item = echartJson.new Item();
						paramMap2.put("devName", wtBaseCount.getDev_name());
						//paramMap2.put("date", date);
						paramMap2.put("BegainDate", begainDate);
						paramMap2.put("EndDate", endDate);
						item.setName(wtBaseCount.getDev_name());
						// 获取某个设备名称N天的活跃记录
						List<WtDeviceDateCount> devDateCountList = wtActivationInfoService
								.countActivationDeviceEveryDay(paramMap2);

						if (devDateCountList != null && devDateCountList.size() > 0) {
							List<String> devDateCountDateList = new ArrayList<>();
							// 获取某设备N天活跃的记录日期
							for (WtDeviceDateCount wtDeviceDateCount : devDateCountList) {
								devDateCountDateList.add(DateUtil.format(
										wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
							}
							System.out.println(devDateCountList.size());
							devDateCountSize = devDateCountList.size() - 1;
							int index = 0;
							List<Long> countData = new ArrayList<>();
							for (int i = 0; i < dateList.size(); i++) {
								if (devDateCountDateList.contains(dateList.get(i))) {
									countData.add(devDateCountList.get(index)
											.getCount());
									if (index >= 0 && index < devDateCountSize) {
										index++;
									}
								} else {
									countData.add(0L);
								}
							}
							item.setData(countData);
							echartJson.getItemData().add(item);
						}
					}
				} else {
					Item item = echartJson.new Item();
					item.setName("");
					List<Long> countData = new ArrayList<>();
					for (int i = 0; i < dateList.size(); i++) {
						countData.add(0L);
					}
					item.setData(countData);
					echartJson.getItemData().add(item);
				}
			}
			return JsonUtils.objectToJson(echartJson);
		}
	/**
	 * 
	 * @Title:           getSearchCountry
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @param departName
	 * @param:           @param devName
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping("/getSearch/country/{date}")
	@ResponseBody
	public String getSearchCountry(@PathVariable int date,
			@RequestParam(value = "", required = false) String departName,
			@RequestParam(value = "", required = false) String devName) {
		Map<String, Object> param = new HashMap<>();
		List<WtDeviceInfo> departDevNameList = new ArrayList<>();
		List<WtBaseCount> activationDevNameList = new ArrayList<>();
		System.out.println(departName);
		EchartJson echartJson = new EchartJson();
		echartJson.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
		//List<String> dateList = countDate(date);
		
		
		
		String begainDate = null;
		String endDate = null;
		List<String> dateList = new ArrayList<String>();
		if(date > 0){
			begainDate = getPastDate(date-1);
			endDate = getPastDate(0);
			dateList = countDate(date);
		}else{
			begainDate = getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = getPastDate(0);
			dateList.add(getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		if (departName != null && !"".equals(departName)) {
			// 查询事业部下所有的设备
			// 查找数据库中对应的设备名
			param.put("departName", departName);
			// 查找事业部下所有的设备名
						departDevNameList = wtDeviceInfoService.distanctData(param);
						if (departDevNameList != null && departDevNameList.size() > 0) {
							System.out.println("事业部下的设备数 " + departDevNameList.size());
						} else {
							Item item = echartJson.new Item();
							item.setName("");
							List<Long> countData = new ArrayList<>();
							for (int i = 0; i < dateList.size(); i++) {
								countData.add(0L);
							}
							item.setData(countData);
							echartJson.getItemData().add(item);
							return JsonUtils.objectToJson(echartJson);
						}
						param.clear();
						
						if (devName != null && !"".equals(devName)) {
							System.out.println("传上的设备名为"+devName);
							boolean flag = false;
							for (WtDeviceInfo deviceInfo : departDevNameList) {
								if (deviceInfo.getModel().equals(devName)) {
									flag = true;
									break;
								}
							}
							if (flag) {
								System.out.println("事业部包含此设备名");
								Map<String, Object> countryNameParamMap = new HashMap<String, Object>();
								Map<String, Object> countryEveryParamMap = new HashMap<String, Object>();
								//countryNameParamMap.put("date", date);
								countryNameParamMap.put("BegainDate", begainDate);
								countryNameParamMap.put("EndDate", endDate);
								countryNameParamMap.put("devName", devName);
								//查询活跃的国家
								List<WtBaseCount> countryNameList = wtActivationInfoService
										.countActivationCountryName(countryNameParamMap);
								int countryDateCountSize;
								echartJson.setxAxisData(dateList);
								if (countryNameList != null && countryNameList.size() > 0) {
									for (WtBaseCount wtBaseCount : countryNameList) {
										Item item = echartJson.new Item();
										System.out.println(wtBaseCount.getCountry());
										item.setName(wtBaseCount.getCountry());
										countryEveryParamMap.clear();
										countryEveryParamMap.put("countryName",
												wtBaseCount.getCountry());
										//countryEveryParamMap.put("date", date);
										countryEveryParamMap.put("BegainDate", begainDate);
										countryEveryParamMap.put("EndDate", endDate);
										countryEveryParamMap.put("devName", devName);
										List<WtDeviceDateCount> countryDateCountList = wtActivationInfoService
												.countActivationCountryEveryDay(countryEveryParamMap);
										List<String> countryDateCountDateList = new ArrayList<>();
										for (WtDeviceDateCount wtDeviceDateCount : countryDateCountList) {
											countryDateCountDateList.add(DateUtil.format(
													wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
										}
										if (countryDateCountList != null
												&& countryDateCountList.size() > 0) {
											countryDateCountSize = countryDateCountList.size() - 1;
											int index = 0;
											List<Long> countData = new ArrayList<>();
											for (WtDeviceDateCount wtDeviceDateCount : countryDateCountList) {
												System.out.println(wtDeviceDateCount.getDate());
											}
											for (int i = 0; i < dateList.size(); i++) {
												if (countryDateCountDateList.contains(dateList.get(i))) {
													countData.add(countryDateCountList.get(index)
															.getCount());
													if (index >= 0 && index < countryDateCountSize) {
														index++;
													}
												} else {
													countData.add(0L);
												}
											}
											item.setData(countData);
											echartJson.getItemData().add(item);
										}
									}
									return JsonUtils.objectToJson(echartJson);
								} else {
									Item item = echartJson.new Item();
									item.setName("");
									List<Long> countData = new ArrayList<>();
									for (int i = 0; i < dateList.size(); i++) {
										countData.add(0L);
									}
									item.setData(countData);
									echartJson.getItemData().add(item);
									return JsonUtils.objectToJson(echartJson);
								}

							} else{
								Item item1 = echartJson.new Item();
								item1.setName("");
								List<Long> countData = new ArrayList<>();
								for (int i = 0; i < dateList.size(); i++) {
									countData.add(0L);
								}
								item1.setData(countData);
								echartJson.getItemData().add(item1);
								return JsonUtils.objectToJson(echartJson);
							}
							// param.put("countryName", devName);
						}
						//param.put("date", date);
						param.put("BegainDate", begainDate);
						param.put("EndDate", endDate);
						param.put("departName", departName);
						
						activationDevNameList = wtActivationInfoService.countActivationDeviceName(param);
						System.out.println("N天活跃的设备数" + activationDevNameList.size());
						if (activationDevNameList != null && activationDevNameList.size() > 0) {
							// 事业部N天活跃的设备名
							List<String> devNameList = new ArrayList<>();
							// 获取活跃的设备列表
							List<String> activationDevName = new ArrayList<>();
							for (WtBaseCount baseBean : activationDevNameList) {
								activationDevName.add(baseBean.getDev_name());
							}
							for (WtDeviceInfo devInfo : departDevNameList) {
								devNameList.add(devInfo.getModel());
							}
							List<String> devNameList2 = new ArrayList<>();
							// 得到N天某事业部和某国家活跃的全部设备
							for (String departDevName : activationDevName) {
								if (devNameList.contains(departDevName)) {
									devNameList2.add(departDevName);
									System.out.println("某事业部N天活跃的全部设备" + departDevName);
								}
							}
							List<WtBaseCount> countryName = new ArrayList<>();
							Set<String> counrtySetName = new HashSet<String>();
							for (String dev : devNameList2) {
								param.clear();
								param.put("devModel", dev);
								//param.put("date", date);
								param.put("BegainDate", begainDate);
								param.put("EndDate", endDate);
								// 查询设备名对应活跃的国家数
								countryName = wtActivationInfoService
										.countActivationCountryName(param);
								if (countryName != null && countryName.size() > 0) {
									for (WtBaseCount country : countryName) {
										counrtySetName.add(country.getCountry());
									}
								}
							}
							/*
							 * List<String> devDateCountDateList = new ArrayList<>(); //
							 * 获取某设备N天活跃的记录日期 for (WtDeviceDateCount wtDeviceDateCount :
							 * devDateCountList) { devDateCountDateList.add(DateUtil.format(
							 * wtDeviceDateCount.getDate(), "yyyy-MM-dd")); }
							 */
							if (counrtySetName != null && counrtySetName.size() > 0) {
								for (String countryNameString : counrtySetName) {
									// 设备在某国家的活跃总数
									List<Long> activationDateSum = new ArrayList<>();
									for (int i = 0; i < dateList.size(); i++) {
										activationDateSum.add(0L);
									}
									for (String dev : devNameList2) {
										int devDateCountSize = 0;
										param.clear();
										Item item = echartJson.new Item();
										param.put("countryName", countryNameString);
										item.setName(countryNameString);
										//param.put("date", date);
										param.put("BegainDate", begainDate);
										param.put("EndDate", endDate);
										param.put("devName", dev);
										List<WtDeviceDateCount> countryDateList = wtActivationInfoService
												.countActivationDeviceEveryDay(param);
										if (countryDateList != null
												&& countryDateList.size() > 0) {
											List<String> devDateCountDateList = new ArrayList<>();
											// 获取某设备N天活跃的记录日期
											for (WtDeviceDateCount wtDeviceDateCount : countryDateList) {
												devDateCountDateList.add(DateUtil.format(
														wtDeviceDateCount.getDate(),
														"yyyy-MM-dd"));
											}
											System.out.println(countryDateList.size());
											devDateCountSize = countryDateList.size() - 1;
											int index = 0;
											List<Long> countData = new ArrayList<>();
											for (int i = 0; i < dateList.size(); i++) {
												if (devDateCountDateList.contains(dateList
														.get(i))) {
													countData.add(countryDateList
															.get(index).getCount());
													long sum = activationDateSum.get(i)
															+ countryDateList.get(index)
																	.getCount();
													activationDateSum.set(i, sum);
													if (index >= 0
															&& index < devDateCountSize) {
														index++;
													}
												} else {
													countData.add(0L);
												}
											}
											item.setData(countData);
											echartJson.getItemData().add(item);
										}
									}

								}
							}else{
								Item item = echartJson.new Item();
								item.setName("");
								List<Long> countData = new ArrayList<>();
								for (int i = 0; i < dateList.size(); i++) {
									countData.add(0L);
								}
								item.setData(countData);
								echartJson.getItemData().add(item);
								return JsonUtils.objectToJson(echartJson);
							}
						} else {
							Item item = echartJson.new Item();
							item.setName("");
							List<Long> countData = new ArrayList<>();
							for (int i = 0; i < dateList.size(); i++) {
								countData.add(0L);
							}
							item.setData(countData);
							echartJson.getItemData().add(item);
							return JsonUtils.objectToJson(echartJson);
						}
		}else if(devName != null && !"".equals(devName)){
			Map<String, Object> countryNameParamMap = new HashMap<String, Object>();
			Map<String, Object> countryEveryParamMap = new HashMap<String, Object>();
			//countryNameParamMap.put("date", date);
			countryNameParamMap.put("BegainDate", begainDate);
			countryNameParamMap.put("EndDate", endDate);
			countryNameParamMap.put("devModel", devName);
			List<WtBaseCount> countryNameList = wtActivationInfoService
					.countActivationCountryName(countryNameParamMap);
			int countryDateCountSize;
			echartJson.setxAxisData(dateList);
			if (countryNameList != null && countryNameList.size() > 0) {
				for (WtBaseCount wtBaseCount : countryNameList) {
					Item item = echartJson.new Item();
					System.out.println(wtBaseCount.getCountry());
					item.setName(wtBaseCount.getCountry());
					countryEveryParamMap.clear();
					countryEveryParamMap.put("countryName",
							wtBaseCount.getCountry());
					//countryEveryParamMap.put("date", date);
					countryEveryParamMap.put("BegainDate", begainDate);
					countryEveryParamMap.put("EndDate", endDate);
					countryEveryParamMap.put("devName", devName);
					List<WtDeviceDateCount> countryDateCountList = wtActivationInfoService
							.countActivationCountryEveryDay(countryEveryParamMap);
					List<String> countryDateCountDateList = new ArrayList<>();
					for (WtDeviceDateCount wtDeviceDateCount : countryDateCountList) {
						countryDateCountDateList.add(DateUtil.format(
								wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
					}
					if (countryDateCountList != null
							&& countryDateCountList.size() > 0) {
						countryDateCountSize = countryDateCountList.size() - 1;
						int index = 0;
						List<Long> countData = new ArrayList<>();
						for (WtDeviceDateCount wtDeviceDateCount : countryDateCountList) {
							System.out.println(wtDeviceDateCount.getDate());
						}
						for (int i = 0; i < dateList.size(); i++) {
							if (countryDateCountDateList.contains(dateList.get(i))) {
								countData.add(countryDateCountList.get(index)
										.getCount());
								if (index >= 0 && index < countryDateCountSize) {
									index++;
								}
							} else {
								countData.add(0L);
							}
						}
						item.setData(countData);
						echartJson.getItemData().add(item);
					}
				}
			} else {
				Item item = echartJson.new Item();
				item.setName("");
				List<Long> countData = new ArrayList<>();
				for (int i = 0; i < dateList.size(); i++) {
					countData.add(0L);
				}
				item.setData(countData);
				echartJson.getItemData().add(item);
			}
			System.out.println(JsonUtils.objectToJson(echartJson));
			return JsonUtils.objectToJson(echartJson);
		}else{
			Map<String, Object> countryNameParamMap = new HashMap<String, Object>();
			Map<String, Object> countryEveryParamMap = new HashMap<String, Object>();
			//countryNameParamMap.put("date", date);
			countryNameParamMap.put("BegainDate", begainDate);
			countryNameParamMap.put("EndDate", endDate);
			List<WtBaseCount> countryNameList = wtActivationInfoService
					.countActivationCountryName(countryNameParamMap);
			int countryDateCountSize;
			echartJson.setxAxisData(dateList);
			if (countryNameList != null && countryNameList.size() > 0) {
				for (WtBaseCount wtBaseCount : countryNameList) {
					Item item = echartJson.new Item();
					System.out.println(wtBaseCount.getCountry());
					item.setName(wtBaseCount.getCountry());
					countryEveryParamMap.clear();
					countryEveryParamMap.put("countryName",
							wtBaseCount.getCountry());
					//countryEveryParamMap.put("date", date);
					countryEveryParamMap.put("BegainDate", begainDate);
					countryEveryParamMap.put("EndDate", endDate);
					List<WtDeviceDateCount> countryDateCountList = wtActivationInfoService
							.countActivationCountryEveryDay(countryEveryParamMap);
					List<String> countryDateCountDateList = new ArrayList<>();
					for (WtDeviceDateCount wtDeviceDateCount : countryDateCountList) {
						countryDateCountDateList.add(DateUtil.format(
								wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
					}
					if (countryDateCountList != null
							&& countryDateCountList.size() > 0) {
						countryDateCountSize = countryDateCountList.size() - 1;
						int index = 0;
						List<Long> countData = new ArrayList<>();
						for (WtDeviceDateCount wtDeviceDateCount : countryDateCountList) {
							System.out.println(wtDeviceDateCount.getDate());
						}
						for (int i = 0; i < dateList.size(); i++) {
							if (countryDateCountDateList.contains(dateList.get(i))) {
								countData.add(countryDateCountList.get(index)
										.getCount());
								if (index >= 0 && index < countryDateCountSize) {
									index++;
								}
							} else {
								countData.add(0L);
							}
						}
						item.setData(countData);
						echartJson.getItemData().add(item);
					}
				}
			} else {
				Item item = echartJson.new Item();
				item.setName("");
				List<Long> countData = new ArrayList<>();
				for (int i = 0; i < dateList.size(); i++) {
					countData.add(0L);
				}
				item.setData(countData);
				echartJson.getItemData().add(item);
			}
			System.out.println(JsonUtils.objectToJson(echartJson));
			return JsonUtils.objectToJson(echartJson);
		}
		return JsonUtils.objectToJson(echartJson);
	}
	/**
	 * 
	 * @Title:           queryDepartDev
	 * @Description:     TODO
	 * @param:           @param departName
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping("/queryDepartDev")
	@ResponseBody
	public String queryDepartDev(@RequestParam(value="",required=false) String departName){
			
		Map<String, Object> param = new HashMap<>();
		param.put("departName", departName);
		List<WtDeviceInfo> devList = wtDeviceInfoService.distanctData(param);
		List<String> devNameList = new ArrayList<>();
		if(devList != null && devList.size() > 0){
			
			return JsonUtils.objectToJson(devList);
		}
		param.clear();
		param.put("data", devNameList);
		return JsonUtils.objectToJson(param);
	}
	/**
	 * 查询国家
	 * @Title:           queryCountry
	 * @Description:     TODO
	 * @param:           @param userStatus
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping(value="/getCountry")
	@ResponseBody
	public String queryCountry(@RequestParam(value="",required=true) String userStatus){
		Map<String, Object> paramMap = new HashMap<>();
		List<WtBaseCount> devList3 = new ArrayList<>();
		//JSONObject jsonObject = new JSONObject();
		//System.out.println(userStatus+"=======================================================================================");
		if(userStatus != null && (!"".equals(userStatus)) && "admin".equals(userStatus)){
			//查询所有国家
			paramMap.put("country", userStatus);
			List<WtBaseCount> devList = wtActivationInfoService.distinctData(paramMap);
			return JsonUtils.objectToJson(devList);
		}else if(userStatus != null && !"".equals(userStatus)){
			System.out.println(userStatus+"=fsafs==============================");
			List<WtDeviceInfo> devList = queryDepartDevList(userStatus);
			if(devList != null && devList.size() > 0){
				paramMap.put("devList",devList);
				paramMap.put("country", userStatus);
				List<WtBaseCount> devList2 = wtActivationInfoService.distinctData(paramMap);
				return JsonUtils.objectToJson(devList2);
			}
			
		}
		return JsonUtils.objectToJson(devList3);
	}
	
	

	/*@RequestMapping("/getSearchTable/device/{date}")
	@ResponseBody
	public String queryDeviceConditionTable(@PathVariable int date,@RequestParam(value = "null", required = false) String departName,
			@RequestParam(value = "", required = false) String country){
		List<WtBaseCount> activationDevNameList = new ArrayList<>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<WtBaseCount> resultData = new ArrayList<>();
		if(departName != null && !"".equals(departName)){
			List<WtDeviceInfo> departDevNameList = queryDepartDevList(departName);
			if(departDevNameList == null || departDevNameList.size() <= 0){
				return null;
			}
			paramMap.clear();
			if(country != null && !"".equals(country)){
				paramMap.put("countryName", country);
			}
			String todayDate = getPastDate(0);
			String	dateTime = getPastDate(date-1);
			paramMap.put("weekBegainDate", dateTime);
			paramMap.put("weekEndDate", todayDate);
			//某国家活跃的设备合集
			paramMap.put("devList",departDevNameList);
			activationDevNameList = wtActivationInfoService.countActvationCountryNum(paramMap);
			if(activationDevNameList == null || activationDevNameList.size() <= 0){
				return null;
			}
			//某事业部下所有设备在某国家的总数及计算
			for(WtBaseCount wt : activationDevNameList){
				WtBaseCount wtBaseCount = new WtBaseCount();
				paramMap.clear();
				paramMap.put("countryName",wt.getCountry());
				
				paramMap.put("devList",departDevNameList);
				int oneCountryCountSum =wtActivationInfoService.queryActivationCount(paramMap);
				
				wtBaseCount.setCountry(wt.getCountry());
				wtBaseCount.setDayActive(wt.getCount());
				wtBaseCount.setSum(oneCountryCountSum);
				resultData.add(wtBaseCount); 
			}
			//查找事业部下所有设备 N天 国家排名
			

		}else if(country != null && !"".equals(country)){
			String todayDate = getPastDate(0);
			String	dateTime = getPastDate(date-1);
			paramMap.put("weekBegainDate", dateTime);
			paramMap.put("weekEndDate", todayDate);
			paramMap.put("countryName",country);
			activationDevNameList = wtActivationInfoService.countActvationCountryNum(paramMap);
			
			if(activationDevNameList == null || activationDevNameList.size() <= 0){
				return null;
			}
			//某事业部下所有设备在某国家的总数及计算
			for(WtBaseCount wt : activationDevNameList){
				WtBaseCount wtBaseCount = new WtBaseCount();
				paramMap.clear();
				paramMap.put("countryName",wt.getCountry());
				
				//paramMap.put("devList",departDevNameList);
				int oneCountryCountSum =wtActivationInfoService.queryActivationCount(paramMap);
				
				wtBaseCount.setCountry(wt.getCountry());
				wtBaseCount.setDayActive(wt.getCount());
				wtBaseCount.setSum(oneCountryCountSum);
				resultData.add(wtBaseCount); 
			}
		}else{
			String todayDate = getPastDate(0);
			String	dateTime = getPastDate(date-1);
			paramMap.put("weekBegainDate", dateTime);
			paramMap.put("weekEndDate", todayDate);
			activationDevNameList = wtActivationInfoService.countActvationCountryNum(paramMap);
			
			if(activationDevNameList == null || activationDevNameList.size() <= 0){
				return null;
			}
			//某事业部下所有设备在某国家的总数及计算
			for(WtBaseCount wt : activationDevNameList){
				WtBaseCount wtBaseCount = new WtBaseCount();
				paramMap.clear();
				paramMap.put("countryName",wt.getCountry());
				//paramMap.put("devList",departDevNameList);
				int oneCountryCountSum =wtActivationInfoService.queryActivationCount(paramMap);
				
				wtBaseCount.setCountry(wt.getCountry());
				wtBaseCount.setDayActive(wt.getCount());
				wtBaseCount.setSum(oneCountryCountSum);
				resultData.add(wtBaseCount); 
			}
		}
		
		return JsonUtils.objectToJson(resultData);
	}*/
	
	@RequestMapping("/getSearchTable/country/{date}")
	@ResponseBody
	public String queryCountryConditionTable(@PathVariable int date,@RequestParam(value = "", required = false) String departName,
			@RequestParam(value = "", required = false) String devName){
		List<WtBaseCount> activationDevNameList = new ArrayList<>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<WtBaseCount> resultData = new ArrayList<>();
		
		String begainDate = null;
		String endDate = null;
		//List<String> dateList = new ArrayList<String>();
		if(date > 0){
			begainDate = getPastDate(date-1);
			endDate = getPastDate(0);
		//	dateList = countDate(date);
		}else{
			begainDate = getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = getPastDate(0);
			//dateList.add(getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		
		if(departName != null && !"".equals(departName)){
			List<WtDeviceInfo> departDevNameList = queryDepartDevList(departName);
			if(departDevNameList == null || departDevNameList.size() <= 0){
				return null;
			}
			paramMap.clear();
			if(devName != null && !"".equals(devName)){
				paramMap.put("devModel", devName);
				
			}
			/*String todayDate = getPastDate(0);
			String	dateTime = getPastDate(date-1);*/
			/*paramMap.put("weekBegainDate", dateTime);
			paramMap.put("weekEndDate", todayDate);*/
			
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			//某国家活跃的设备合集
			paramMap.put("devList",departDevNameList);
			activationDevNameList = wtActivationInfoService.countActvationCountryNum(paramMap);
			//for(Wt)
			
			if(activationDevNameList == null || activationDevNameList.size() <= 0){
				return null;
			}
			for(WtBaseCount wt : activationDevNameList){
				System.out.println(wt.getCountry() + ""+wt.getCount());
				
			}
			//某事业部下所有设备在某国家的总数及计算
			for(WtBaseCount wt : activationDevNameList){
				WtBaseCount wtBaseCount = new WtBaseCount();
				paramMap.clear();
				paramMap.put("countryName",wt.getCountry());
				/*paramMap.put("weekBegainDate", dateTime);
				paramMap.put("weekEndDate", todayDate);*/
				paramMap.put("devList",departDevNameList);
				int oneCountryCountSum =wtActivationInfoService.queryActivationCount(paramMap);
				
				wtBaseCount.setCountry(wt.getCountry());
				wtBaseCount.setDayActive(wt.getCount());
				wtBaseCount.setSum(oneCountryCountSum);
				resultData.add(wtBaseCount); 
			}
			//查找事业部下所有设备 N天 国家排名
			

		}else if(devName != null && !"".equals(devName)){
			/*String todayDate = getPastDate(0);
			String	dateTime = getPastDate(date-1);
			paramMap.put("weekBegainDate", dateTime);
			paramMap.put("weekEndDate", todayDate);*/
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			paramMap.put("devModel",devName);
			activationDevNameList = wtActivationInfoService.countActvationCountryNum(paramMap);
			
			if(activationDevNameList == null || activationDevNameList.size() <= 0){
				return null;
			}
			//某事业部下所有设备在某国家的总数及计算
			for(WtBaseCount wt : activationDevNameList){
				WtBaseCount wtBaseCount = new WtBaseCount();
				paramMap.clear();
				paramMap.put("devName",wt.getDev_name());
				/*paramMap.put("weekBegainDate", dateTime);
				paramMap.put("weekEndDate", todayDate);*/
				paramMap.put("BegainDate", begainDate);
				paramMap.put("EndDate", endDate);
				//paramMap.put("devList",departDevNameList);
				int oneCountryCountSum =wtActivationInfoService.queryActivationCount(paramMap);
				
				wtBaseCount.setCountry(wt.getCountry());
				wtBaseCount.setDayActive(wt.getCount());
				wtBaseCount.setSum(oneCountryCountSum);
				resultData.add(wtBaseCount); 
			}
		}else{
			/*String todayDate = getPastDate(0);
			String	dateTime = getPastDate(date-1);
			paramMap.put("weekBegainDate", dateTime);
			paramMap.put("weekEndDate", todayDate);*/
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			activationDevNameList = wtActivationInfoService.countActvationCountryNum(paramMap);
			
			if(activationDevNameList == null || activationDevNameList.size() <= 0){
				return null;
			}
			//某事业部下所有设备在某国家的总数及计算
			for(WtBaseCount wt : activationDevNameList){
				WtBaseCount wtBaseCount = new WtBaseCount();
				paramMap.clear();
				paramMap.put("countryName",wt.getCountry());
				/*paramMap.put("weekBegainDate", dateTime);
				paramMap.put("weekEndDate", todayDate);*/
				paramMap.put("BegainDate", begainDate);
				paramMap.put("EndDate", endDate);
				//paramMap.put("devList",departDevNameList);
				int oneCountryCountSum =wtActivationInfoService.queryActivationCount(paramMap);
				
				wtBaseCount.setCountry(wt.getCountry());
				wtBaseCount.setDayActive(wt.getCount());
				wtBaseCount.setSum(oneCountryCountSum);
				resultData.add(wtBaseCount); 
			}
		}
		
		return JsonUtils.objectToJson(resultData);
	}
	/**
	 * 
	 * @param date
	 * @param departName
	 * @param country
	 * @return
	 */
	@RequestMapping("/getSearchTable/device/{date}")
	@ResponseBody
	public String queryDeviceConditionTable(@PathVariable int date,@RequestParam(value ="", required = false) String departName,
			@RequestParam(value = "", required = false) String country){
		
		List<WtBaseCount> activationDevNameList = new ArrayList<>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<WtBaseCount> resultData = new ArrayList<>();
		
		String begainDate = null;
		String endDate = null;
		//List<String> dateList = new ArrayList<String>();
		if(date > 0){
			begainDate = getPastDate(date-1);
			endDate = getPastDate(0);
		//	dateList = countDate(date);
		}else{
			begainDate = getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE);
			endDate = getPastDate(0);
			//dateList.add(getFormatPastDate(1,DateUtil.PATTERN_CLASSICAL_SIMPLE));
		}
		
		
		if(departName != null && !"".equals(departName)){
			System.out.println(departName);
			List<WtDeviceInfo> departDevNameList = queryDepartDevList(departName);
			if(departDevNameList == null || departDevNameList.size() <= 0){
				return null;
			}
			for(WtDeviceInfo ww : departDevNameList){
				System.out.println(ww.getModel());
			}
			paramMap.clear();
			if(country != null && !"".equals(country)){
				paramMap.put("countryName", country);
			}
			/*String todayDate = getPastDate(0);
			String	dateTime = getPastDate(date-1);
			paramMap.put("weekBegainDate", dateTime);
			paramMap.put("weekEndDate", todayDate);*/
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			//某国家活跃的设备合集
			paramMap.put("devList",departDevNameList);
			activationDevNameList = wtActivationInfoService.countActvationDeviceNum(paramMap);
			//for(Wt)
			for(WtBaseCount wt : activationDevNameList){
				System.out.println(wt.getCountry() + ""+wt.getCount());
				
			}
			if(activationDevNameList == null || activationDevNameList.size() <= 0){
				return null;
			}
			//某事业部下所有设备在某国家的总数及计算
			for(WtBaseCount wt : activationDevNameList){
				WtBaseCount wtBaseCount = new WtBaseCount();
				paramMap.clear();
				paramMap.put("devName",wt.getDev_name());
				/*paramMap.put("weekBegainDate", dateTime);
				paramMap.put("weekEndDate", todayDate);*/
				//paramMap.put("devList",departDevNameList);
				int oneDeviceCountSum =wtActivationInfoService.queryActivationCount(paramMap);
				
				wtBaseCount.setDev_name(wt.getDev_name());
				wtBaseCount.setDayActive(wt.getCount());
				wtBaseCount.setSum(oneDeviceCountSum);
				resultData.add(wtBaseCount); 
			}
			//查找事业部下所有设备 N天 国家排名
			

		}else if(country != null && !"".equals(country)){
			String todayDate = getPastDate(0);
			String	dateTime = getPastDate(date-1);
			/*paramMap.put("weekBegainDate", dateTime);
			paramMap.put("weekEndDate", todayDate);*/
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			paramMap.put("countryName",country);
			activationDevNameList = wtActivationInfoService.countActvationDeviceNum(paramMap);
			
			if(activationDevNameList == null || activationDevNameList.size() <= 0){
				return null;
			}
			//某事业部下所有设备在某国家的总数及计算
			for(WtBaseCount wt : activationDevNameList){
				WtBaseCount wtBaseCount = new WtBaseCount();
				paramMap.clear();
				paramMap.put("devName",wt.getDev_name());
				/*paramMap.put("weekBegainDate", dateTime);
				paramMap.put("weekEndDate", todayDate);*/
				//paramMap.put("devList",departDevNameList);
				int oneDeviceCountSum =wtActivationInfoService.queryActivationCount(paramMap);
				
				wtBaseCount.setDev_name(wt.getDev_name());
				wtBaseCount.setDayActive(wt.getCount());
				wtBaseCount.setSum(oneDeviceCountSum);
				resultData.add(wtBaseCount); 
			}
		}else{
			/*String todayDate = getPastDate(0);
			String	dateTime = getPastDate(date-1);
			paramMap.put("weekBegainDate", dateTime);
			paramMap.put("weekEndDate", todayDate);*/
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			activationDevNameList = wtActivationInfoService.countActvationDeviceNum(paramMap);
			
			if(activationDevNameList == null || activationDevNameList.size() <= 0){
				return null;
			}
			//某事业部下所有设备在某国家的总数及计算
			for(WtBaseCount wt : activationDevNameList){
				WtBaseCount wtBaseCount = new WtBaseCount();
				paramMap.clear();
				paramMap.put("devName",wt.getDev_name());
				/*paramMap.put("weekBegainDate", dateTime);
				paramMap.put("weekEndDate", todayDate);*/
				//paramMap.put("devList",departDevNameList);
				int oneCountryCountSum =wtActivationInfoService.queryActivationCount(paramMap);
				
				wtBaseCount.setDev_name(wt.getDev_name());
				wtBaseCount.setDayActive(wt.getCount());
				wtBaseCount.setSum(oneCountryCountSum);
				resultData.add(wtBaseCount); 
			}
		}
		
		return JsonUtils.objectToJson(resultData);
	}
	/**
	 * 
	 * @Title:           getSearchTableDevice
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @param departName
	 * @param:           @param country
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	/*@RequestMapping("/getSearchTabl/device/{date}")
	@ResponseBody
	public String getSearchTableDevice(@PathVariable int date,@RequestParam(value = "", required = false) String departName,
			@RequestParam(value = "", required = false) String country){
		List<WtDeviceInfo> departDevNameList = new ArrayList<>();
		List<WtBaseCount> activationDevNameList = new ArrayList<>();
		List<String> departDevName = new ArrayList<>();
		List<String> activationDevName = new ArrayList<>();
		List<WtBaseCount> resultData = new ArrayList<>();
		if(departName != null && !"".equals(departName)){
			Map<String, Object> param = new HashMap<>();
			// 查找数据库中对应的设备名
			param.put("departName", departName);
			// 查找事业部下所有的设备名
			departDevNameList = wtDeviceInfoService.distanctData(param);
			if (departDevNameList != null && departDevNameList.size() > 0) {
				System.out.println("事业部下的设备数 " + departDevNameList.size());
				for (WtDeviceInfo wtDeviceInfo : departDevNameList) {
					departDevName.add(wtDeviceInfo.getModel());//取出事业部下所有设备的设备名
				}
			} else {
				return null;
			}
			
			param.clear();
			if(country != null && !"".equals(country)){
				param.put("countryName", country);
			}
			param.put("date", date);
			//某国家活跃的设备合集
			
			activationDevNameList = wtActivationInfoService.countActivationDeviceName(param);

			
			if(activationDevNameList != null && activationDevNameList.size() > 0){
				for (WtBaseCount wtBaseCount : activationDevNameList) {
					activationDevName.add(wtBaseCount.getDev_name());
				}
			}else{
				return null;
			}
			List<String> devName = new ArrayList<>();
		
			for (String departdevName : departDevName) {
				if(activationDevName.contains(departdevName)){
					devName.add(departdevName);
				}
			}
			if(devName != null && devName.size() > 0){
				System.out.println("事业部在国家中活跃的设备"+devName.size());
				Map<String, Object> devNameParam = new HashMap<>();
				Map<String, Object> devActivationParam = new HashMap<>();
				devNameParam.put("date", date);
				devNameParam.put("limit", 5);
				
				String todayDate = getPastDate(0);
				String dateTime;
				dateTime = getPastDate(date-1);

				
				for (String devname : devName) {
					WtBaseCount resultBean = new WtBaseCount();
					//int sumActivation = 0;// 累积活跃
					//int dayActivation = 0;// N天活跃
					devActivationParam.clear();
					System.out.println(devname);
					devActivationParam.put("devName", devname);
					if(country != null && !"".equals(country)){
						devActivationParam.put("countryName", country);
					}
					// 某设备总活跃
					int devSumActivationList = wtActivationInfoService
							.queryActivationCount(devActivationParam);
					if (devSumActivationList != null && devSumActivationList.size() > 0) {
						System.out.println("devsumActiveList :"
								+ devSumActivationList.size());
						sumActivation = devSumActivationList.size();
					}
					//N天活跃数
					devActivationParam.put("weekBegainDate", dateTime);
					devActivationParam.put("weekEndDate", todayDate);

					// List<WtDeviceDateCount> devActiveList =
					// wtActiveInfoService.countActiveDeviceEveryDay(devActiveParam);
					int devDateActivationList = wtActivationInfoService
							.queryActivationCount(devActivationParam);
					//dayActivation = devDateActivationList.size();


					resultBean.setDev_name(devname);
					resultBean.setDayActive(devDateActivationList);
					resultBean.setSum(devSumActivationList);

					resultData.add(resultBean);
				}
				return JsonUtils.objectToJson(resultData);
			}else{
				System.out.println("devName is null");
				return null;
			}
		}else if(country != null && !"".equals(country)){
			Map<String,Object> param = new HashMap<>();
			Map<String,Object> devActivationParam = new HashMap<>();
			String todayDate = getPastDate(0);
			String dateTime;
			dateTime = getPastDate(date-1);
			
			param.put("countryName", country);
			param.put("date", date);
			param.put("limit", 5);
			//某国家活跃的设备合集
			
			activationDevNameList = wtActivationInfoService.countActivationDeviceName(param);
			System.out.println();
			if(activationDevNameList != null && activationDevNameList.size() > 0){
				for (WtBaseCount devname : activationDevNameList) {
					WtBaseCount resultBean = new WtBaseCount();
					//int sumActivation = 0;// 累积活跃
					//int dayActivation = 0;// N天活跃
					devActivationParam.clear();
					System.out.println(devname.getDev_name());
					devActivationParam.put("devName", devname.getDev_name());
					devActivationParam.put("countryName", country);
					// 某设备总活跃
					int devSumActivationList = wtActivationInfoService
							.queryActivationCount(devActivationParam);
					if (devSumActivationList != null && devSumActivationList.size() > 0) {
						System.out.println("devsumActivationList :"
								+ devSumActivationList.size());
						sumActivation = devSumActivationList.size();
					}
					//N天活跃数
					devActivationParam.put("weekBegainDate", dateTime);
					devActivationParam.put("weekEndDate", todayDate);

					int  devDateActivationList = wtActivationInfoService
							.queryActivationCount(devActivationParam);
					//dayActivation = devDateActivationList.size();

					resultBean.setDev_name(devname.getDev_name());
					resultBean.setDayActive(devDateActivationList);
					resultBean.setSum(devSumActivationList);

					resultData.add(resultBean);
				}
				return JsonUtils.objectToJson(resultData);
			}
		}else{
			Map<String, Object> devNameParam = new HashMap<>();
			Map<String, Object> devActivationParam = new HashMap<>();
			devNameParam.put("date", date);
			devNameParam.put("limit", 5);
			String todayDate = getPastDate(0);
			String dateTime;
			dateTime = getPastDate(date-1);
			

			// String lastMothDate = getPastDate(20);
			List<WtBaseCount> devNameList = wtActivationInfoService
					.countActivationDeviceName(devNameParam);
			if (devNameList != null && devNameList.size() > 0) {
				System.out.println(devNameList.size());
				for (WtBaseCount wtBaseCount : devNameList) {
					WtBaseCount resultBean = new WtBaseCount();
					//int sumActivation = 0;// 累积活跃
					//int dayActivation = 0;// N天活跃
					devActivationParam.clear();
					System.out.println(wtBaseCount.getDev_name());
					devActivationParam.put("devName", wtBaseCount.getDev_name());
					// 某设备总活跃
					int devSumActivationList = wtActivationInfoService
							.queryActivationCount(devActivationParam);
					if (devSumActivationList != null && devSumActivationList.size() > 0) {
						System.out.println("devsumActiveList :"
								+ devSumActivationList.size());
						sumActivation = devSumActivationList.size();
					}
					devActivationParam.put("weekBegainDate", dateTime);
					devActivationParam.put("weekEndDate", todayDate);

					
					int devDateActivationList = wtActivationInfoService
							.queryActivationCount(devActivationParam);
					//dayActivation = devDateActivationList.size();

					resultBean.setDev_name(wtBaseCount.getDev_name());
					resultBean.setDayActive(devDateActivationList);
					resultBean.setSum(devSumActivationList);

					resultData.add(resultBean);
				}
			}
			return JsonUtils.objectToJson(resultData);
		}
		return null;
	}*/
	/**
	 * 
	 * @param date
	 * @param departName
	 * @param devName
	 * @return
	 *//*
	@RequestMapping("/getSearchTable/country/{date}")
	@ResponseBody
	public String getSearchCountryTable(@PathVariable int date,@RequestParam(value = "", required = false) String departName,
			@RequestParam(value = "", required = false) String devName){
		Map<String,Object> param = new HashMap<>();
		List<WtDeviceInfo> departDevNameList = new ArrayList<>();
		List<WtBaseCount> activationCountryNameList =  new ArrayList<>();
		Set<String> activationCountrySet = new HashSet<>();
		List<WtBaseCount> resultData = new ArrayList<>();
		if(departName != null && !"".equals(departName)){
			// 查询事业部下所有的设备
			// 查找数据库中对应的设备名
			param.put("departName", departName);
			// 查找事业部下所有的设备名
			departDevNameList = wtDeviceInfoService.distanctData(param);
			if (departDevNameList != null && departDevNameList.size() > 0) {
				boolean flag = false;
				if(devName != null && !"".equals(devName)){
					for (WtDeviceInfo wtDeviceInfo : departDevNameList) {
						if(wtDeviceInfo.getModel().equals(devName)){
							flag =true;
							break;
						}
					}
					if(flag){
						param.clear();
						param.put("date", date);
						param.put("devModel", devName);
						activationCountryNameList = wtActivationInfoService.countActivationCountryName(param);
						if(activationCountryNameList!=null && activationCountryNameList.size() > 0){
							for (WtBaseCount baseData : activationCountryNameList) {

								String todayDate = getPastDate(0);
								String dateTime;
								dateTime = getPastDate(date -1);
								WtBaseCount resultBean = new WtBaseCount();
								int sumActivation = 0;// 累积活跃
								int dayActivation = 0;// N天活跃
								param.clear();
								param.put("countryName",baseData.getCountry());
								
								for(WtDeviceInfo wtDeviceInfo : departDevNameList){
									param.put("devName",wtDeviceInfo.getModel());
									int countryList = wtActivationInfoService.queryActivationCount(param); 
									sumActivation +=  countryList;
									param.put("weekBegainDate", dateTime);
									param.put("weekEndDate", todayDate);
									int countryList1 = wtActivationInfoService.queryActivationCount(param); 
									dayActivation+=countryList1;
								}
								resultBean.setCountry(baseData.getCountry());
								resultBean.setDayActive(dayActivation);
								resultBean.setSum(sumActivation);

								resultData.add(resultBean);
							}
							return JsonUtils.objectToJson(resultData);
						}else{
							return null;
						}
					}else{
						return null;
					}
				}
				
				System.out.println("事业部下的设备数 " + departDevNameList.size());
				param.clear();
				param.put("date", date);
				for (WtDeviceInfo wtDeviceInfo : departDevNameList) {
					param.put("devModel", wtDeviceInfo.getModel());
					//查询设备活跃的国家
					activationCountryNameList = wtActivationInfoService.countActivationCountryName(param);
					if(activationCountryNameList != null && activationCountryNameList.size() > 0){
						for (WtBaseCount wtBaseCount : activationCountryNameList) {
							activationCountrySet.add(wtBaseCount.getCountry());
							System.out.println("事业部活跃对应的国家"+wtBaseCount.getCountry());
						}
					}
				}
				
				String todayDate = getPastDate(0);
				String dateTime;
				dateTime = getPastDate(date -1);
				if(activationCountrySet != null && activationCountrySet.size() > 0){
					

					for (String countryName : activationCountrySet) {
						WtBaseCount resultBean = new WtBaseCount();
						int sumActivation = 0;// 累积活跃
						int dayActivation = 0;// N天活跃
						param.clear();
						param.put("countryName",countryName);
						
						for(WtDeviceInfo wtDeviceInfo : departDevNameList){
							param.put("devName",wtDeviceInfo.getModel());
							int countryList = wtActivationInfoService.queryActivationCount(param); 
							sumActivation +=  countryList;
							param.put("weekBegainDate", dateTime);
							param.put("weekEndDate", todayDate);
							int countryList1 = wtActivationInfoService.queryActivationCount(param); 
							dayActivation+=countryList1;
						}
						resultBean.setCountry(countryName);
						resultBean.setDayActive(dayActivation);
						resultBean.setSum(sumActivation);

						resultData.add(resultBean);
					}
					return JsonUtils.objectToJson(resultData);
				}else{
					return null;
				}
			} else {
				return null;
			}
		}else if(devName != null && !"".equals(devName)){
			// 查询事业部下所有的设备
			// 查找数据库中对应的设备名
			

			String todayDate = getPastDate(0);
			String dateTime;
			dateTime = getPastDate(date-1);
			Map<String, Object> countryNameParam = new HashMap<>();
			Map<String, Object> countryActivationParam = new HashMap<>();
			countryNameParam.put("date", date);
			countryNameParam.put("limit", 5);
			countryNameParam.put("devModel", devName);
			
			// String lastMothDate = getPastDate(20);
			List<WtBaseCount> countryNameList = wtActivationInfoService
					.countActivationCountryName(countryNameParam);
			if (countryNameList != null && countryNameList.size() > 0) {
				System.out.println(countryNameList.size());
				for (WtBaseCount wtBaseCount : countryNameList) {
					WtBaseCount resultBean = new WtBaseCount();
					//int sumActivation = 0;// 累积活跃
					//int dayActivation = 0;// N天活跃
					countryActivationParam.clear();
					System.out.println(wtBaseCount.getCountry());
					countryActivationParam.put("countryName", wtBaseCount.getCountry());
					countryActivationParam.put("devName",devName);
					
					// 某设备总活跃
					int devSumActivationList = wtActivationInfoService
							.queryActivationCount(countryActivationParam);
					if (devSumActivationList != null && devSumActivationList.size() > 0) {
						System.out.println("devsumActivationList :"
								+ devSumActivationList.size());
						sumActivation = devSumActivationList.size();
					}
					countryActivationParam.put("weekBegainDate", dateTime);
					countryActivationParam.put("weekEndDate", todayDate);

					// List<WtDeviceDateCount> devActiveList =
					// wtActiveInfoService.countActiveDeviceEveryDay(devActiveParam);
					int devDateActivationList = wtActivationInfoService
							.queryActivationCount(countryActivationParam);
					//dayActivation = devDateActivationList.size();

					resultBean.setCountry(wtBaseCount.getCountry());
					resultBean.setDayActive(devDateActivationList);
					resultBean.setSum(devSumActivationList);

					resultData.add(resultBean);
				}
			}
			return JsonUtils.objectToJson(resultData);
		}else{
			Map<String, Object> countryNameParam = new HashMap<>();
			Map<String, Object> countryActivationParam = new HashMap<>();
			countryNameParam.put("date", date);
			countryNameParam.put("limit", 5);
			String todayDate = getPastDate(0);
			String dateTime;
			dateTime = getPastDate(date -1);
			// String lastMothDate = getPastDate(20);
			List<WtBaseCount> countryNameList = wtActivationInfoService
					.countActivationCountryName(countryNameParam);
			if (countryNameList != null && countryNameList.size() > 0) {
				System.out.println(countryNameList.size());
				for (WtBaseCount wtBaseCount : countryNameList) {
					WtBaseCount resultBean = new WtBaseCount();
					//int sumActivation = 0;// 累积活跃
					//int dayActivation = 0;// N天活跃
					countryActivationParam.clear();
					System.out.println(wtBaseCount.getCountry());
					countryActivationParam.put("countryName", wtBaseCount.getCountry());
					// 某设备总活跃
					int devSumActivationList = wtActivationInfoService
							.queryActivationCount(countryActivationParam);
					if (devSumActivationList != null && devSumActivationList.size() > 0) {
						System.out.println("devsumActiveList :"
								+ devSumActivationList.size());
						sumActivation = devSumActivationList.size();
					}
					countryActivationParam.put("weekBegainDate", dateTime);
					countryActivationParam.put("weekEndDate", todayDate);

					// List<WtDeviceDateCount> devActiveList =
					// wtActiveInfoService.countActiveDeviceEveryDay(devActiveParam);
					int  devDateActivationList = wtActivationInfoService
							.queryActivationCount(countryActivationParam);
					//dayActivation = devDateActivationList.size();

					resultBean.setCountry(wtBaseCount.getCountry());
					resultBean.setDayActive(devDateActivationList);
					resultBean.setSum(devSumActivationList);

					resultData.add(resultBean);
				}
			}
			return JsonUtils.objectToJson(resultData);
		}
	}*/
	

	@RequestMapping(value="/insert/Activation",method=RequestMethod.POST)
	@ResponseBody
	public String insertActivation(@RequestParam String data){
		JSONObject jsonObj = JSON.parseObject(data);  
		JSONArray jsondata = jsonObj.getJSONArray("data");
		Map<String,Object> paramMap = new HashMap<>();
		List<WtActivationInfo> list = JsonUtils.jsonToList(jsondata.toJSONString(), WtActivationInfo.class);
		System.out.println("insert data size"+list.size());
		if(list.size() >0 && list != null){
			int result = wtActivationInfoService.insertActivation(list);
			if(result > 0){
				paramMap.put("result", 200);
			}else{
				paramMap.put("result", -1);
			}
			
		}else{
			paramMap.put("result", -1);
		}
		
		
		return JsonUtils.objectToJson(paramMap);
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
	/**
	 * 获取过去或者未来 任意天内的日期数组
	 * 
	 * @param intervals
	 *            intervals天内
	 * @return 日期数组
	 */
	public static ArrayList<String> countDate(int intervals) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		ArrayList<String> fetureDaysList = new ArrayList<>();
		for (int i = 0; i < intervals; i++) {
			pastDaysList.add(getPastDate(i));
			fetureDaysList.add(getFutureDate(i));
		}
		return pastDaysList;
	}
	
	/**
	 * 获取未来 第 past 天的日期
	 * 
	 * @param past
	 * @return
	 */
	
	private static String getFutureDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				+ past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	/**
	 * 获取过去第几天的日期
	 * 
	 * @param past
	 * @return
	 */
	private static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				- past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	public static ArrayList<String> FormatcountDate(int intervals, String format) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		for (int i = 0; i < intervals; i++) {
			pastDaysList.add(getFormatPastDate(i, format));
		}
		return pastDaysList;
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
