package com.wtwd.gms.controller;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wtwd.gms.entity.EchartJson;
import com.wtwd.gms.entity.EchartJson.Item;
import com.wtwd.gms.entity.WtActivationInfo;
import com.wtwd.gms.entity.WtActiveInfo;
import com.wtwd.gms.entity.WtBaseCount;
import com.wtwd.gms.entity.WtDepartmentInfo;
import com.wtwd.gms.entity.WtDevice;
import com.wtwd.gms.entity.WtDeviceDateCount;
import com.wtwd.gms.entity.WtDeviceInfo;
import com.wtwd.gms.excel.DateUtil;
import com.wtwd.gms.excel.StringUtil;
import com.wtwd.gms.service.WtActiveInfoService;
import com.wtwd.gms.service.WtDepartmentInfoService;
import com.wtwd.gms.service.WtDeviceInfoService;
import com.wtwd.gms.service.WtDeviceService;
import com.wtwd.gms.utils.JsonUtils;

@Controller
@RequestMapping("/device/active")
public class DeviceActiveController extends BaseController {

	@Autowired
	private WtActiveInfoService wtActiveInfoService;

	@Autowired
	private WtDeviceService wtDeviceService;

	@Autowired
	private WtDepartmentInfoService wtDepartmentInfoService;
	// private static final ObjectMapper MAPPER = new ObjectMapper();
	@Autowired
	private WtDeviceInfoService wtDeviceInfoService;

	@RequestMapping("/list")
	public String list(Model model) {
		return "device/device_active";
	}

	/*
	 * @RequestMapping("/views") public StringBindingResult
	 */
	@RequestMapping("/baseData")
	@ResponseBody
	public String baseData(@RequestParam String  userStatus,@RequestParam String userType){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		JSONObject jsonObject = new JSONObject();
		if(userType != null && !"".equals(userType)&& "ADMIN".equals(userType)){
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(DateUtil.format(new Date(),
					DateUtil.PATTERN_CLASSICAL_SIMPLE));
			map.put("active_time",
					DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE));
			// map.put("i",1 );
			int todayActivation = wtActiveInfoService.queryActiveCount(map);
			System.out.println("今日活跃" + todayActivation);
			//model.addAttribute("todayActivationData", todayActivation.size());
			map.clear();
			map.put("yesTodayDate",
					getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			System.out.println(getFormatPastDate(1,
					DateUtil.PATTERN_CLASSICAL_SIMPLE));
			
			int yesTodayActivation = wtActiveInfoService.queryActiveCount(map);
			System.out.println("昨日活跃" + yesTodayActivation);
			//model.addAttribute("yesterdayActivationData", yesTodayActivation.size());
			map.clear();
			map.put("BegainDate",
					getFormatPastDate(6, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			map.put("EndDate",
					DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE));
			System.out.println(DateUtil.format(new Date(),
					DateUtil.PATTERN_CLASSICAL_SIMPLE)
					+ " "
					+ getFormatPastDate(6, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			
			int weekBegainActivation = wtActiveInfoService.queryActiveCount(map);
			System.out.println("七日活跃" + weekBegainActivation);
			//model.addAttribute("sevenActivationData", weekBegainActivation.size());
			map.clear();
			map.put("allData",
					getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			int allData = wtActiveInfoService.queryActiveCount(map);
			
			System.out.println("累积活跃" + allData);
			//model.addAttribute("allActivationData", allData.size());
			jsonObject.put("todayActive",todayActivation);
			jsonObject.put("yesTodayActive", yesTodayActivation);
			jsonObject.put("weekBegainActive", weekBegainActivation);
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
			map.put("active_time",
					DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE));
			// map.put("i",1 );
			map.put("devList", devNameList);
			int todayActivation = wtActiveInfoService.queryActiveCount(map);
			System.out.println("今日活跃" + todayActivation);
			//model.addAttribute("todayActivationData", todayActivation.size());
			map.clear();
			map.put("yesTodayDate",
					getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			System.out.println(getFormatPastDate(1,
					DateUtil.PATTERN_CLASSICAL_SIMPLE));
			map.put("devList", devNameList);
			int yesTodayActivation = wtActiveInfoService.queryActiveCount(map);
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
					+ getFormatPastDate(6, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			map.put("devList", devNameList);
			int weekBegainActivation = wtActiveInfoService.queryActiveCount(map);
			System.out.println("七日活跃" + weekBegainActivation);
			//model.addAttribute("sevenActivationData", weekBegainActivation.size());
			map.clear();
			map.put("allData",
					getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));
			map.put("devList", devNameList);
			int allData = wtActiveInfoService.queryActiveCount(map);
			
			System.out.println("累积活跃" + allData);
			//model.addAttribute("allActivationData", allData.size());
			jsonObject.put("todayActive",todayActivation);
			jsonObject.put("yesTodayActive", yesTodayActivation);
			jsonObject.put("weekBegainActive", weekBegainActivation);
			jsonObject.put("allData", allData);
		}
		return jsonObject.toString();
	}
	
	
	/**
	 * 
	 * @Title: countActiveDeviceName
	 * @Description:
	 * @param: @param date
	 * @param: @return
	 * @return: String
	 * @throws
	 */
	@RequestMapping("/getChartData/device/{date}")
	@ResponseBody
	public String countActiveDevice(@PathVariable int date) {
		Map<String, Object> paramMap = new HashMap<>();
		Map<String, Object> paramMap2 = new HashMap<>();
		String begainDate = null;
		String endDate = null;
		
		begainDate = getPastDate(date-1);//传入 7 ，过去6天，包括今天
		endDate = getPastDate(0);
		paramMap.put("BegainDate", begainDate);
		paramMap.put("EndDate", endDate);
		
		//paramMap.put("date", date);

		// paramMap.put("countryName", "美国");
		// 获取N天活跃最多的五个设备名称
		List<WtBaseCount> devNameList = wtActiveInfoService
				.coutActiveDeviceName(paramMap);
		List<String> dateList = countDate(date);
		// List<Double> countList = new ArrayList<>();
		for (String string : dateList) {
			System.out.println(string + "fsadf");
		}
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
				
				paramMap.put("BegainDate", begainDate);
				paramMap.put("EndDate", endDate);
				
				item.setName(wtBaseCount.getDev_name());
				// 获取某个设备名称N天的活跃记录
				List<WtDeviceDateCount> devDateCountList = wtActiveInfoService
						.countActiveDeviceEveryDay(paramMap2);

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
		//countryNameParamMap.put("date", date);
		String begainDate = null;
		String endDate = null;
		
		begainDate = getPastDate(date-1);
		endDate = getPastDate(0);
		countryNameParamMap.put("BegainDate", begainDate);
		countryNameParamMap.put("EndDate", endDate);
		
		List<WtBaseCount> countryNameList = wtActiveInfoService
				.countActiveCountryName(countryNameParamMap);
		List<String> dateList = countDate(date);
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
				countryNameParamMap.put("BegainDate", begainDate);
				countryNameParamMap.put("EndDate", endDate);
				List<WtDeviceDateCount> countryDateCountList = wtActiveInfoService
						.countActiveCountryEveryDay(countryEveryParamMap);
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
	 * @Title:           countOsData
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping("/getChartData/os/{date}")
	@ResponseBody
	public String countOsData(@PathVariable int date) {
		Map<String, Object> osParam = new HashMap<>();
		List<String> dateList = countDate(date);
		int osCountSize;
		EchartJson echartJson = new EchartJson();
		// System.out.println("devNameList :" + devNameList.size());
		echartJson.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
		String[] osType = { "Android", "IOS" };
		String begainDate = null;
		String endDate = null;
		begainDate = getPastDate(date-1);
		endDate = getPastDate(0);
		for (int i = 0; i < osType.length; i++) {
			Item item = echartJson.new Item();

			item.setName(osType[i]);
			osParam.put("mobile_op", osType[i]);
			osParam.put("BegainDate", begainDate);
			osParam.put("EndDate", endDate);
			List<WtDeviceDateCount> osData = wtActiveInfoService
					.countsOsEveryDay(osParam);

			if (osData != null && osData.size() > 0) {
				List<String> osDataDateList = new ArrayList<>();
				for (WtDeviceDateCount wtDeviceDateCount : osData) {
					osDataDateList.add(DateUtil.format(
							wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
				}
				osCountSize = osData.size() - 1;
				int index = 0;
				List<Long> countData = new ArrayList<>();
				for (int x = 0; x < dateList.size(); x++) {
					if (osDataDateList.contains(dateList.get(x))) {
						countData.add(osData.get(index).getCount());
						if (index >= 0 && index < osCountSize) {
							index++;
						}
					} else {
						countData.add(0L);
					}
				}
				item.setData(countData);
				echartJson.getItemData().add(item);
			} else {
				item.setName(osType[i]);
				List<Long> countData = new ArrayList<>();
				for (int j = 0; j < dateList.size(); j++) {
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
	 * @Title:           countBrandData
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping(value = "/getChartData/brand/{date}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String countBrandData(@PathVariable int date) {
		Map<String, Object> brandNameParam = new HashMap<>();
		Map<String, Object> brandEveryParam = new HashMap<>();
		String begainDate = getPastDate(date-1);
		String endDate = getPastDate(0);
		
		//brandNameParam.put("date", date);
		brandNameParam.put("BegainDate", begainDate);
		brandNameParam.put("EndDate", endDate);
		//brandNameParam.put("limit", 5);

		List<WtBaseCount> brandNameList = wtActiveInfoService
				.countBrandName(brandNameParam);
		for (WtBaseCount wtBaseCount : brandNameList) {
			System.out.println(wtBaseCount.getMobile_brand());
		}
		List<String> dateList = countDate(date);
		// List<Double> countList = new ArrayList<>();
		for (String string : dateList) {
			System.out.println(string + "fsadf");
		}
		int brandCountSize;
		EchartJson echartJson = new EchartJson();
		System.out.println("devNameList :" + brandNameList.size());
		echartJson.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
		if (brandNameList != null && brandNameList.size() > 0) {
			for (WtBaseCount wtBaseCount : brandNameList) {
				Item item = echartJson.new Item();
				System.out.println(wtBaseCount.getCountry());
				item.setName(wtBaseCount.getMobile_brand());
				brandEveryParam.clear();
				brandEveryParam.put("mobile_brand",
						wtBaseCount.getMobile_brand());
				//brandEveryParam.put("date", date);
				brandEveryParam.put("BegainDate", begainDate);
				brandEveryParam.put("EndDate", endDate);
				List<WtDeviceDateCount> brandEveryDayList = wtActiveInfoService
						.countBrandEveryDay(brandEveryParam);
				if (brandEveryDayList != null && brandEveryDayList.size() > 0) {
					List<String> brandCountDateList = new ArrayList<>();
					brandCountSize = brandEveryDayList.size() - 1;
					for (WtDeviceDateCount wtDeviceDateCount : brandEveryDayList) {
						brandCountDateList.add(DateUtil.format(
								wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
					}
					int index = 0;
					List<Long> countData = new ArrayList<>();
					for (WtDeviceDateCount wtDeviceDateCount : brandEveryDayList) {
						System.out.println(wtDeviceDateCount.getDate());
					}
					for (int i = 0; i < dateList.size(); i++) {
						if (brandCountDateList.contains(dateList.get(i))) {
							countData.add(brandEveryDayList.get(index)
									.getCount());
							if (index >= 0 && index < brandCountSize) {
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
	 * @Title:           countModelData
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping("/getChartData/model/{date}")
	@ResponseBody
	public String countModelData(@PathVariable int date) {

		Map<String, Object> modelNameParam = new HashMap<>();
		Map<String, Object> modelEveryDayParam = new HashMap<>();

		String begainDate = getPastDate(date-1);
		String endDate = getPastDate(0);
		//modelNameParam.put("date", date);
		//modelNameParam.put("limit", 5);
		modelNameParam.put("BegainDate", begainDate);
		modelNameParam.put("EndDate", endDate);
		List<WtBaseCount> modelNameList = wtActiveInfoService
				.countModelName(modelNameParam);
		EchartJson echartJson = new EchartJson();
		List<String> dateList = countDate(date);
		echartJson.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
		if (modelNameList != null && modelNameList.size() > 0) {

			int modelCountSize;

			System.out.println("devNameList :" + modelNameList.size());
			for (WtBaseCount wtBaseCount : modelNameList) {
				System.out.println(wtBaseCount.getMobile_model());
				Item item = echartJson.new Item();
				item.setName(wtBaseCount.getMobile_model());
				modelEveryDayParam.clear();
				modelEveryDayParam.put("mobile_model",
						wtBaseCount.getMobile_model());
				//modelEveryDayParam.put("date", date);
				modelEveryDayParam.put("BegainDate", begainDate);
				modelEveryDayParam.put("EndDate", endDate);
				List<WtDeviceDateCount> modelEveryDayList = wtActiveInfoService
						.countBrandEveryDay(modelEveryDayParam);
				if (modelEveryDayList != null && modelEveryDayList.size() > 0) {
					List<String> modelCountDateList = new ArrayList<>();
					modelCountSize = modelEveryDayList.size() - 1;
					for (WtDeviceDateCount wtDeviceDateCount : modelEveryDayList) {
						modelCountDateList.add(DateUtil.format(
								wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
					}
					int index = 0;
					List<Long> countData = new ArrayList<>();
					for (WtDeviceDateCount wtDeviceDateCount : modelEveryDayList) {
						System.out.println(wtDeviceDateCount.getDate());
					}
					for (int i = 0; i < dateList.size(); i++) {
						if (modelCountDateList.contains(dateList.get(i))) {
							countData.add(modelEveryDayList.get(index)
									.getCount());
							if (index >= 0 && index < modelCountSize) {
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
		String begainDate = getPastDate(date-1);
		String endDate = getPastDate(0);
		devNameParam.put("BegainDate", begainDate);
		devNameParam.put("EndDate", endDate);
		//devNameParam.put("limit", 5);
		/*String todayDate = getPastDate(0);
		String dateTime ;
		if (date == 7) {
			dateTime = getPastDate(7);
		} else {
			dateTime = getPastDate(30);
		}*/

		// String lastMothDate = getPastDate(20);
		List<WtBaseCount> devNameList = wtActiveInfoService
				.coutActiveDeviceName(devNameParam);
		List<WtBaseCount> resultData = new ArrayList<>();
		if (devNameList != null && devNameList.size() > 0) {
			System.out.println(devNameList.size());
			for (WtBaseCount wtBaseCount : devNameList) {
				WtBaseCount resultBean = new WtBaseCount();
				//int sumActive = 0;// 累积活跃
				//int dayActive = 0;// N天活跃
				devActiveParam.clear();
				System.out.println(wtBaseCount.getDev_name());
				devActiveParam.put("devName", wtBaseCount.getDev_name());
				// 某设备总活跃
				int devSumActiveList = wtActiveInfoService
						.queryActiveCount(devActiveParam);
				/*if (devSumActiveList != null && devSumActiveList.size() > 0) {
					System.out.println("devsumActiveList :"
							+ devSumActiveList.size());
					sumActive = devSumActiveList.size();
				}*/
				//devActiveParam.put("weekBegainDate", dateTime);
				//devActiveParam.put("weekEndDate", todayDate);
				devActiveParam.put("BegainDate", begainDate);
				devActiveParam.put("EndDate", endDate);
				// List<WtDeviceDateCount> devActiveList =
				// wtActiveInfoService.countActiveDeviceEveryDay(devActiveParam);
				int devDateActiveList = wtActiveInfoService
						.queryActiveCount(devActiveParam);
				//dayActive = devDateActiveList.size();

				resultBean.setDev_name(wtBaseCount.getDev_name());
				resultBean.setDayActive(devDateActiveList);
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
		
		String begainDate = getPastDate(date-1);
		String endDate = getPastDate(0);
		//countryNameParam.put("date", date);
		
		countryNameParam.put("BegainDate", begainDate);
		countryNameParam.put("EndDate", endDate);
		
		//countryNameParam.put("limit", 5);
		/*String todayDate = getPastDate(0);
		String dateTime;
		if (date == 7) {
			dateTime = getPastDate(7);
		} else {
			dateTime = getPastDate(30);
		}*/
		// String lastMothDate = getPastDate(20);
		List<WtBaseCount> countryNameList = wtActiveInfoService
				.countActiveCountryName(countryNameParam);
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
				int  devSumActiveList = wtActiveInfoService
						.queryActiveCount(countryActiveParam);
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
			int devDateActiveList = wtActiveInfoService
						.queryActiveCount(countryActiveParam);
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
	 * @Title:           getOsTable
	 * @Description:     TODO
	 * @param:           @param type
	 * @param:           @param date
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping(value = "/getTableData/{type}/{date}", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getOsTable(@PathVariable String type, @PathVariable int date) {
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> resultMap = new HashMap<>();
		//param.put("date", date);
		String begainDate = getPastDate(date-1);
		String endDate = getPastDate(0);
		//countryNameParam.put("date", date);
		
		param.put("BegainDate", begainDate);
		param.put("EndDate", endDate);
		
		int totalList = wtActiveInfoService
				.countActiveNumber(param);
		List<WtBaseCount> resultData = new ArrayList<>();
		if (totalList > 0) {
			int totalNum = totalList;
			
			if ("model".equals(type)) {
				// param.put("model", type)
				param.clear();
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				param.put("mobile_model", type);
				List<WtBaseCount> modelNameList = wtActiveInfoService
						.distinctData(param);
				for (WtBaseCount wtBaseCount : modelNameList) {
					int modelNum = 0;
					param.put("mobile_model", wtBaseCount.getMobile_model());
					int modelList = wtActiveInfoService
							.countActiveNumber(param);
					/*if (modelList != null && modelList.size() > 0) {
						modelNum = modelList.size();
					}*/
					modelNum = modelList;
					WtBaseCount modelBean = new WtBaseCount();
					modelBean.setMobile_model(wtBaseCount.getMobile_model());
					modelBean.setSum(modelNum);
					float modelOccupancy = (float) ((float) modelNum / (float) totalNum) * 100;
					modelBean.setOccupancy(modelOccupancy + "%");
					resultData.add(modelBean);
				}
				resultMap.put("data", resultData);
			} else if ("os".equals(type)) {
				param.clear();
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				param.put("mobile_op", "Android");
				int androidNum = 0;
				int iosNum = 0;

				int androidList = wtActiveInfoService
						.countActiveNumber(param);
				/*if (androidList != null && androidList.size() > 0) {
					androidNum = androidList.size();
				}*/
				androidNum = androidList;
				WtBaseCount androidData = new WtBaseCount();
				androidData.setMobile_op("Android");
				androidData.setSum(androidNum);
				float androidOccupancy = (float) ((float) androidNum / (float) totalNum) * 100;
				androidData.setOccupancy(androidOccupancy + "%");
				System.out
						.println((float) ((float) androidNum / (float) totalNum)
								* 100 + "%");
				resultData.add(androidData);
				param.put("mobile_op", "IOS");
				int IosList = wtActiveInfoService
						.countActiveNumber(param);
				//if (IosList != null && IosList.size() > 0) {
					iosNum = IosList;
				//}
				WtBaseCount IosData = new WtBaseCount();
				IosData.setMobile_op("IOS");
				IosData.setSum(iosNum);
				float iosOccupancy = (float) ((float) iosNum / (float) totalNum) * 100;
				IosData.setOccupancy(iosOccupancy + "%");
				resultData.add(IosData);
				// return JsonresultData;
				return JsonUtils.objectToJson(resultData);
			} else if ("brand".equals(type)) {
				param.clear();
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				param.put("mobile_brand", type);
				List<WtBaseCount> brandNameList = wtActiveInfoService
						.distinctData(param);
				for (WtBaseCount wtBaseCount : brandNameList) {
					int brandNum = 0;
					param.put("mobile_brand", wtBaseCount.getMobile_brand());
					int brandList = wtActiveInfoService
							.countActiveNumber(param);
					//if (brandList != null && brandList.size() > 0) {
						brandNum = brandList;
					//}
					WtBaseCount brandBean = new WtBaseCount();
					brandBean.setMobile_brand(wtBaseCount.getMobile_brand());
					brandBean.setSum(brandNum);
					float brandOccupancy = (float) ((float) brandNum / (float) totalNum) * 100;
					brandBean.setOccupancy(brandOccupancy + "%");
					resultData.add(brandBean);
				}
				resultMap.put("data", resultData);
			}
		} else {

		}
		return JsonUtils.objectToJson(resultMap);
	}
	/**
	 * 
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
	/**
	 * 
	 * @Title:           getDevice
	 * @Description:     TODO
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
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
		List<WtBaseCount> activeDevNameList = new ArrayList<>();
		System.out.println(departName);
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
		
		EchartJson echartJson = new EchartJson();
		 
		echartJson.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
		if (departName != null && !"".equals(departName)) {
			// 查找数据库中对应的设备名
			param.put("departName", departName);
			// 查找事业部下所有的设备名
			departDevNameList = queryDepartDevList(departName);
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
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			param.put("devList", departDevNameList);
			activeDevNameList = wtActiveInfoService.coutActiveDeviceName(param);
			System.out.println("N天活跃的设备数" + activeDevNameList.size());
			

			if (activeDevNameList != null && activeDevNameList.size() > 0) {
				/*// 事业部N天活跃的设备名
				List<String> devNameList = new ArrayList<>();*/
				// 获取活跃的设备列表
				//List<String> activeDevName = new ArrayList<>();
				/*for (WtBaseCount baseBean : activeDevNameList) {
					activeDevName.add(baseBean.getDev_name());
				}*/
				/*for (WtDeviceInfo devInfo : departDevNameList) {
					devNameList.add(devInfo.getModel());
				}*/
				//List<String> devName = new ArrayList<>();
				// 得到N天某事业部和某国家活跃的全部设备
				/*for (String departDevName : activeDevName) {
					if (devNameList.contains(departDevName)) {
						devName.add(departDevName);
						System.out.println("某事业部、在某国家N天活跃的全部设备" + departDevName);
					}
				}*/
				//if (devName != null && devName.size() > 0) {
					int devDateCountSize;
					echartJson.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
					Map<String, Object> paramMap = new HashMap<>();
					for (WtBaseCount devname : activeDevNameList) {
						Item item = echartJson.new Item();
						if (country != null && !"".equals(country)) {
							paramMap.put("countryName", country);
							// System.out.println("国家"+country);
						}
						paramMap.put("devName", devname.getDev_name());
						paramMap.put("BegainDate", begainDate);
						paramMap.put("EndDate", endDate);
						item.setName(devname.getDev_name());
						// 获取某个设备名称N天的活跃记录
						List<WtDeviceDateCount> devDateCountList = wtActiveInfoService
								.countActiveDeviceEveryDay(paramMap);

						if (devDateCountList != null
								&& devDateCountList.size() > 0) {
							List<String> devDateCountDateList = new ArrayList<>();
							// 获取某设备N天活跃的记录日期
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
				/*} else {
					Item item = echartJson.new Item();
					item.setName("");
					List<Long> countData = new ArrayList<>();
					for (int i = 0; i < dateList.size(); i++) {
						countData.add(0L);
					}
					item.setData(countData);
					echartJson.getItemData().add(item);
				}*/
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
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			paramMap.put("countryName", country);
			List<WtBaseCount> devNameList = new ArrayList<>();
			devNameList = wtActiveInfoService.coutActiveDeviceName(paramMap);
			// List<Double> countList = new ArrayList<>();
			int devDateCountSize;
			echartJson.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
			if (devNameList != null && devNameList.size() > 0) {
				Map<String, Object> paramMap2 = new HashMap<>();
				for (WtBaseCount wtBaseCount : devNameList) {
					Item item = echartJson.new Item();
					if (country != null && !"".equals(country)) {
						paramMap2.put("countryName", country);
						// System.out.println("国家"+country);
					}
					paramMap2.put("devName", wtBaseCount.getDev_name());
					//paramMap2.put("date", date);
					paramMap2.put("BegainDate", begainDate);
					paramMap2.put("EndDate", endDate);
					item.setName(wtBaseCount.getDev_name());
					// 获取某个设备名称N天的活跃记录
					List<WtDeviceDateCount> devDateCountList = wtActiveInfoService
							.countActiveDeviceEveryDay(paramMap2);

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
			List<WtBaseCount> devNameList = wtActiveInfoService
					.coutActiveDeviceName(paramMap);
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
					List<WtDeviceDateCount> devDateCountList = wtActiveInfoService
							.countActiveDeviceEveryDay(paramMap2);

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
		List<WtBaseCount> activeDevNameList = new ArrayList<>();
		System.out.println(departName);
		EchartJson echartJson = new EchartJson();
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
		//List<String> dateList = countDate(date);
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
					List<WtBaseCount> countryNameList = wtActiveInfoService
							.countActiveCountryName(countryNameParamMap);
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
							List<WtDeviceDateCount> countryDateCountList = wtActiveInfoService
									.countActiveCountryEveryDay(countryEveryParamMap);
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
			
			activeDevNameList = wtActiveInfoService.coutActiveDeviceName(param);
			System.out.println("N天活跃的设备数" + activeDevNameList.size());
			if (activeDevNameList != null && activeDevNameList.size() > 0) {
				// 事业部N天活跃的设备名
				List<String> devNameList = new ArrayList<>();
				// 获取活跃的设备列表
				List<String> activeDevName = new ArrayList<>();
				for (WtBaseCount baseBean : activeDevNameList) {
					activeDevName.add(baseBean.getDev_name());
				}
				for (WtDeviceInfo devInfo : departDevNameList) {
					devNameList.add(devInfo.getModel());
				}
				List<String> devNameList2 = new ArrayList<>();
				// 得到N天某事业部和某国家活跃的全部设备
				for (String departDevName : activeDevName) {
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
					param.put("date", date);
					// 查询设备名对应活跃的国家数
					countryName = wtActiveInfoService
							.countActiveCountryName(param);
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
						List<Long> activeDateSum = new ArrayList<>();
						for (int i = 0; i < dateList.size(); i++) {
							activeDateSum.add(0L);
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
							List<WtDeviceDateCount> countryDateList = wtActiveInfoService
									.countActiveDeviceEveryDay(param);
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
										long sum = activeDateSum.get(i)
												+ countryDateList.get(index)
														.getCount();
										activeDateSum.set(i, sum);
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
			List<WtBaseCount> countryNameList = wtActiveInfoService
					.countActiveCountryName(countryNameParamMap);
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
					List<WtDeviceDateCount> countryDateCountList = wtActiveInfoService
							.countActiveCountryEveryDay(countryEveryParamMap);
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
			List<WtBaseCount> countryNameList = wtActiveInfoService
					.countActiveCountryName(countryNameParamMap);
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
					List<WtDeviceDateCount> countryDateCountList = wtActiveInfoService
							.countActiveCountryEveryDay(countryEveryParamMap);
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
	
	
	@RequestMapping("/getSearchTable/device/{date}")
	@ResponseBody
	public String queryDeviceConditionTable(@PathVariable int date,@RequestParam(value = "", required = false) String departName,
			@RequestParam(value = "", required = false) String country){
		
		List<WtBaseCount> activationDevNameList = new ArrayList<>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<WtBaseCount> resultData = new ArrayList<>();
		
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
		
		if(departName != null && !"".equals(departName)){
			List<WtDeviceInfo> departDevNameList = queryDepartDevList(departName);
			for(WtDeviceInfo w : departDevNameList){
				System.out.println(w.getModel());
			}
			
			if(departDevNameList == null || departDevNameList.size() <= 0){
				return null;
			}
			paramMap.clear();
			if(country != null && !"".equals(country)){
				paramMap.put("countryName", country);
			}
			//String todayDate = getPastDate(0);
			//String	dateTime = getPastDate(date-1);
			//paramMap.put("weekBegainDate", dateTime);
			//paramMap.put("weekEndDate", todayDate);
			
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			//某国家活跃的设备合集
			paramMap.put("devList",departDevNameList);
			activationDevNameList = wtActiveInfoService.countActiveDeviceNum(paramMap);
			
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
				paramMap.put("devModel",wt.getDev_name());
				/*paramMap.put("weekBegainDate", dateTime);
				paramMap.put("weekEndDate", todayDate);*/
				//paramMap.put("devList",departDevNameList);
				int oneDeviceCountSum =wtActiveInfoService.queryActiveCount(paramMap);
				
				wtBaseCount.setDev_name(wt.getDev_name());
				wtBaseCount.setDayActive(wt.getCount());
				wtBaseCount.setSum(oneDeviceCountSum);
				resultData.add(wtBaseCount); 
			}
			//查找事业部下所有设备 N天 国家排名
			

		}else if(country != null && !"".equals(country)){
			//String todayDate = getPastDate(0);
			//String	dateTime = getPastDate(date-1);
			/*paramMap.put("weekBegainDate", dateTime);
			paramMap.put("weekEndDate", todayDate);*/
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			paramMap.put("countryName",country);
			activationDevNameList = wtActiveInfoService.countActiveDeviceNum(paramMap);
			
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
				int oneDeviceCountSum =wtActiveInfoService.queryActiveCount(paramMap);
				
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
			activationDevNameList = wtActiveInfoService.countActiveDeviceNum(paramMap);
			
			if(activationDevNameList == null || activationDevNameList.size() <= 0){
				return null;
			}
			//某事业部下所有设备在某国家的总数及计算
			for(WtBaseCount wt : activationDevNameList){
				WtBaseCount wtBaseCount = new WtBaseCount();
				paramMap.clear();
				paramMap.put("devModel",wt.getDev_name());
				/*paramMap.put("weekBegainDate", dateTime);
				paramMap.put("weekEndDate", todayDate);*/
				//paramMap.put("devList",departDevNameList);
				int oneCountryCountSum =wtActiveInfoService.queryActiveCount(paramMap);
				
				wtBaseCount.setDev_name(wt.getDev_name());
				wtBaseCount.setDayActive(wt.getCount());
				wtBaseCount.setSum(oneCountryCountSum);
				resultData.add(wtBaseCount); 
			}
		}
		
		return JsonUtils.objectToJson(resultData);
	}
	
	/**
	 * 已废弃
	 * @Title:           getSearchTableDevice
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	/*@RequestMapping("/getSearchTabe/device/{date}")
	@ResponseBody
	public String getSearchTableDevice(@PathVariable int date,@RequestParam(value = "null", required = false) String departName,
			@RequestParam(value = "", required = false) String country){
		List<WtDeviceInfo> departDevNameList = new ArrayList<>();
		List<WtBaseCount> activeDevNameList = new ArrayList<>();
		List<String> departDevName = new ArrayList<>();
		List<String> activeDevName = new ArrayList<>();
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
			
			activeDevNameList = wtActiveInfoService.coutActiveDeviceName(param);
			
			
			if(activeDevNameList != null && activeDevNameList.size() > 0){
				for (WtBaseCount wtBaseCount : activeDevNameList) {
					activeDevName.add(wtBaseCount.getDev_name());
				}
			}else{
				return null;
			}
			List<String> devName = new ArrayList<>();
			//boolean ret = activeDevName.retainAll(departDevName);
			for (String departdevName : departDevName) {
				if(activeDevName.contains(departdevName)){
					devName.add(departdevName);
				}
			}
			if(devName != null && devName.size() > 0){
				System.out.println("事业部在国家中活跃的设备"+devName.size());
				Map<String, Object> devNameParam = new HashMap<>();
				Map<String, Object> devActiveParam = new HashMap<>();
				devNameParam.put("date", date);
				devNameParam.put("limit", 5);
				
				String todayDate = getPastDate(0);
				String dateTime;
				dateTime = getPastDate(date-1);

				// String lastMothDate = getPastDate(20);
				List<WtBaseCount> devNameList = wtActiveInfoService
						.coutActiveDeviceName(devNameParam);
				
				for (String devname : devName) {
					WtBaseCount resultBean = new WtBaseCount();
					//int sumActive = 0;// 累积活跃
					//int dayActive = 0;// N天活跃
					devActiveParam.clear();
					System.out.println(devname);
					devActiveParam.put("devName", devname);
					if(country != null && !"".equals(country)){
						devActiveParam.put("countryName", country);
					}
					// 某设备总活跃
					int devSumActiveList = wtActiveInfoService
							.queryActiveCount(devActiveParam);
					if (devSumActiveList != null && devSumActiveList.size() > 0) {
						System.out.println("devsumActiveList :"
								+ devSumActiveList.size());
						sumActive = devSumActiveList.size();
					}
					//N天活跃数
					devActiveParam.put("weekBegainDate", dateTime);
					devActiveParam.put("weekEndDate", todayDate);

					// List<WtDeviceDateCount> devActiveList =
					// wtActiveInfoService.countActiveDeviceEveryDay(devActiveParam);
					int devDateActiveList = wtActiveInfoService
							.queryActiveCount(devActiveParam);
					//dayActive = devDateActiveList.size();

					resultBean.setDev_name(devname);
					resultBean.setDayActive(devDateActiveList);
					resultBean.setSum(devSumActiveList);

					resultData.add(resultBean);
				}
				return JsonUtils.objectToJson(resultData);
			}else{
				System.out.println("devName is null");
				return null;
			}
		}else if(country != null && !"".equals(country)){
			Map<String,Object> param = new HashMap<>();
			Map<String,Object> devActiveParam = new HashMap<>();
			String todayDate = getPastDate(0);
			String dateTime;
			dateTime = getPastDate(date-1);
			
			param.put("countryName", country);
			param.put("date", date);
			param.put("limit", 5);
			//某国家活跃的设备合集
			
			activeDevNameList = wtActiveInfoService.coutActiveDeviceName(param);
			System.out.println();
			if(activeDevNameList != null && activeDevNameList.size() > 0){
				for (WtBaseCount devname : activeDevNameList) {
					WtBaseCount resultBean = new WtBaseCount();
					//int sumActive = 0;// 累积活跃
					//int dayActive = 0;// N天活跃
					devActiveParam.clear();
					System.out.println(devname.getDev_name());
					devActiveParam.put("devName", devname.getDev_name());
					devActiveParam.put("countryName", country);
					// 某设备总活跃
					int devSumActiveList = wtActiveInfoService
							.queryActiveCount(devActiveParam);
					
					//sumActive = devSumActiveList;
					
					//N天活跃数
					devActiveParam.put("weekBegainDate", dateTime);
					devActiveParam.put("weekEndDate", todayDate);

					// List<WtDeviceDateCount> devActiveList =
					// wtActiveInfoService.countActiveDeviceEveryDay(devActiveParam);
					int devDateActiveList = wtActiveInfoService
							.queryActiveCount(devActiveParam);
					//dayActive = devDateActiveList.size();

					resultBean.setDev_name(devname.getDev_name());
					resultBean.setDayActive(devDateActiveList);
					resultBean.setSum(devSumActiveList);

					resultData.add(resultBean);
				}
				return JsonUtils.objectToJson(resultData);
			}
		}else{
			Map<String, Object> devNameParam = new HashMap<>();
			Map<String, Object> devActiveParam = new HashMap<>();
			devNameParam.put("date", date);
			devNameParam.put("limit", 5);
			String todayDate = getPastDate(0);
			String dateTime;
			dateTime = getPastDate(date-1);
			

			// String lastMothDate = getPastDate(20);
			List<WtBaseCount> devNameList = wtActiveInfoService
					.coutActiveDeviceName(devNameParam);
			if (devNameList != null && devNameList.size() > 0) {
				System.out.println(devNameList.size());
				for (WtBaseCount wtBaseCount : devNameList) {
					WtBaseCount resultBean = new WtBaseCount();
					//int sumActive = 0;// 累积活跃
					//int dayActive = 0;// N天活跃
					devActiveParam.clear();
					System.out.println(wtBaseCount.getDev_name());
					devActiveParam.put("devName", wtBaseCount.getDev_name());
					// 某设备总活跃
					int devSumActiveList = wtActiveInfoService
							.queryActiveCount(devActiveParam);
					if (devSumActiveList != null && devSumActiveList.size() > 0) {
						System.out.println("devsumActiveList :"
								+ devSumActiveList.size());
						sumActive = devSumActiveList.size();
					}
					devActiveParam.put("weekBegainDate", dateTime);
					devActiveParam.put("weekEndDate", todayDate);

					// List<WtDeviceDateCount> devActiveList =
					// wtActiveInfoService.countActiveDeviceEveryDay(devActiveParam);
				int  devDateActiveList = wtActiveInfoService
							.queryActiveCount(devActiveParam);
					//dayActive = devDateActiveList.size();

					resultBean.setDev_name(wtBaseCount.getDev_name());
					resultBean.setDayActive(devDateActiveList);
					resultBean.setSum(devSumActiveList);

					resultData.add(resultBean);
				}
			}
			return JsonUtils.objectToJson(resultData);
		}
		return null;
	}
	*/
	/**
	 * 
	 * @param date
	 * @param departName
	 * @param devName
	 * @return
	 */
	@RequestMapping("/getSearchCountryTable/country/{date}")
	@ResponseBody
	public String queryCountryConditionTable(@PathVariable int date,@RequestParam(value = "", required = false) String departName,
			@RequestParam(value = "", required = false) String devName){
		List<WtBaseCount> activationDevNameList = new ArrayList<>();
		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<WtBaseCount> resultData = new ArrayList<>();
		
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
		
		if(departName != null && !"".equals(departName)){
			List<WtDeviceInfo> departDevNameList = queryDepartDevList(departName);
			if(departDevNameList == null || departDevNameList.size() <= 0){
				return null;
			}
			paramMap.clear();
			if(devName != null && !"".equals(devName)){
				paramMap.put("devModel", devName);
			}
/*			String todayDate = getPastDate(0);
			String	dateTime = getPastDate(date-1);
			paramMap.put("weekBegainDate", dateTime);
			paramMap.put("weekEndDate", todayDate);*/
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			//某国家活跃的设备合集
			paramMap.put("devList",departDevNameList);
			activationDevNameList = wtActiveInfoService.countActiveCountryNum(paramMap);
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
				paramMap.put("countryName",wt.getCountry());
				/*paramMap.put("weekBegainDate", dateTime);
				paramMap.put("weekEndDate", todayDate);*/
				paramMap.put("devList",departDevNameList);
				int oneCountryCountSum =wtActiveInfoService.queryActiveCount(paramMap);
				
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
			paramMap.put("weekEndDate", todayDate);
			paramMap.put("devModel",devName);*/
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			activationDevNameList = wtActiveInfoService.countActiveCountryNum(paramMap);
			
			if(activationDevNameList == null || activationDevNameList.size() <= 0){
				return null;
			}
			//某事业部下所有设备在某国家的总数及计算
			for(WtBaseCount wt : activationDevNameList){
				WtBaseCount wtBaseCount = new WtBaseCount();
				paramMap.clear();
				paramMap.put("devName",wt.getDev_name());
/*				paramMap.put("weekBegainDate", dateTime);
				paramMap.put("weekEndDate", todayDate);*/
				paramMap.put("BegainDate", begainDate);
				paramMap.put("EndDate", endDate);
				//paramMap.put("devList",departDevNameList);
				int oneCountryCountSum =wtActiveInfoService.queryActiveCount(paramMap);
				
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
			activationDevNameList = wtActiveInfoService.countActiveCountryNum(paramMap);
			
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
				int oneCountryCountSum =wtActiveInfoService.queryActiveCount(paramMap);
				
				wtBaseCount.setCountry(wt.getCountry());
				wtBaseCount.setDayActive(wt.getCount());
				wtBaseCount.setSum(oneCountryCountSum);
				resultData.add(wtBaseCount); 
			}
		}
		
		return JsonUtils.objectToJson(resultData);
	}
	
	
	/**
	 * 已废弃
	 * @Title:           getSearchCountryTable
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @param departName
	 * @param:           @param country
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	/*@RequestMapping("/getSearchCountryTabl/country/{date}")
	@ResponseBody
	public String getSearchCountryTable(@PathVariable int date,@RequestParam(value = "", required = false) String departName,
			@RequestParam(value = "", required = false) String devName){
		Map<String,Object> param = new HashMap<>();
		List<WtDeviceInfo> departDevNameList = new ArrayList<>();
		List<WtBaseCount> activeCountryNameList =  new ArrayList<>();
		Set<String> activeCountrySet = new HashSet<>();
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
						activeCountryNameList = wtActiveInfoService.countActiveCountryName(param);
						if(activeCountryNameList!=null && activeCountryNameList.size() > 0){
							for (WtBaseCount baseData : activeCountryNameList) {

								String todayDate = getPastDate(0);
								String dateTime;
								dateTime = getPastDate(date -1);
								WtBaseCount resultBean = new WtBaseCount();
								int sumActive = 0;// 累积活跃
								int dayActive = 0;// N天活跃
								param.clear();
								param.put("countryName",baseData.getCountry());
								
								for(WtDeviceInfo wtDeviceInfo : departDevNameList){
									param.put("devName",wtDeviceInfo.getModel());
									int countryList = wtActiveInfoService.queryActiveCount(param); 
									sumActive +=  countryList;
									param.put("weekBegainDate", dateTime);
									param.put("weekEndDate", todayDate);
									int countryList1 = wtActiveInfoService.queryActiveCount(param); 
									dayActive+=countryList1;
								}
								resultBean.setCountry(baseData.getCountry());
								resultBean.setDayActive(dayActive);
								resultBean.setSum(sumActive);
								
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
					activeCountryNameList = wtActiveInfoService.countActiveCountryName(param);
					if(activeCountryNameList != null && activeCountryNameList.size() > 0){
						for (WtBaseCount wtBaseCount : activeCountryNameList) {
							activeCountrySet.add(wtBaseCount.getCountry());
							System.out.println("事业部活跃对应的国家"+wtBaseCount.getCountry());
						}
					}
				}
				
				String todayDate = getPastDate(0);
				String dateTime;
				dateTime = getPastDate(date -1);
				if(activeCountrySet != null && activeCountrySet.size() > 0){
					
				
					for (String countryName : activeCountrySet) {
						WtBaseCount resultBean = new WtBaseCount();
						int sumActive = 0;// 累积活跃
						int dayActive = 0;// N天活跃
						param.clear();
						param.put("countryName",countryName);
						
						for(WtDeviceInfo wtDeviceInfo : departDevNameList){
							param.put("devName",wtDeviceInfo.getModel());
							int countryList = wtActiveInfoService.queryActiveCount(param); 
							sumActive +=  countryList;
							param.put("weekBegainDate", dateTime);
							param.put("weekEndDate", todayDate);
							int countryList1 = wtActiveInfoService.queryActiveCount(param); 
							dayActive+=countryList1;
						}
						resultBean.setCountry(countryName);
						resultBean.setDayActive(dayActive);
						resultBean.setSum(sumActive);
						
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
			Map<String, Object> countryActiveParam = new HashMap<>();
			countryNameParam.put("date", date);
			countryNameParam.put("limit", 5);
			countryNameParam.put("devModel", devName);
			
			// String lastMothDate = getPastDate(20);
			List<WtBaseCount> countryNameList = wtActiveInfoService
					.countActiveCountryName(countryNameParam);
			if (countryNameList != null && countryNameList.size() > 0) {
				System.out.println(countryNameList.size());
				for (WtBaseCount wtBaseCount : countryNameList) {
					WtBaseCount resultBean = new WtBaseCount();
					//int sumActive = 0;// 累积活跃
					//int dayActive = 0;// N天活跃
					countryActiveParam.clear();
					System.out.println(wtBaseCount.getCountry());
					countryActiveParam.put("countryName", wtBaseCount.getCountry());
					countryActiveParam.put("devName",devName);
					
					// 某设备总活跃
					int devSumActiveList = wtActiveInfoService
							.queryActiveCount(countryActiveParam);
					if (devSumActiveList != null && devSumActiveList.size() > 0) {
						System.out.println("devsumActiveList :"
								+ devSumActiveList.size());
						sumActive = devSumActiveList.size();
					}
					countryActiveParam.put("weekBegainDate", dateTime);
					countryActiveParam.put("weekEndDate", todayDate);

					// List<WtDeviceDateCount> devActiveList =
					// wtActiveInfoService.countActiveDeviceEveryDay(devActiveParam);
					int devDateActiveList = wtActiveInfoService
							.queryActiveCount(countryActiveParam);
					//dayActive = devDateActiveList.size();

					resultBean.setCountry(wtBaseCount.getCountry());
					resultBean.setDayActive(devDateActiveList);
					resultBean.setSum(devSumActiveList);

					resultData.add(resultBean);
				}
			}
			return JsonUtils.objectToJson(resultData);
		}else{
			Map<String, Object> countryNameParam = new HashMap<>();
			Map<String, Object> countryActiveParam = new HashMap<>();
			countryNameParam.put("date", date);
			countryNameParam.put("limit", 5);
			String todayDate = getPastDate(0);
			String dateTime;
			dateTime = getPastDate(date -1);
			// String lastMothDate = getPastDate(20);
			List<WtBaseCount> countryNameList = wtActiveInfoService
					.countActiveCountryName(countryNameParam);
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
					int devSumActiveList = wtActiveInfoService
							.queryActiveCount(countryActiveParam);
					if (devSumActiveList != null && devSumActiveList.size() > 0) {
						System.out.println("devsumActiveList :"
								+ devSumActiveList.size());
						sumActive = devSumActiveList.size();
					}
					countryActiveParam.put("weekBegainDate", dateTime);
					countryActiveParam.put("weekEndDate", todayDate);

					// List<WtDeviceDateCount> devActiveList =
					// wtActiveInfoService.countActiveDeviceEveryDay(devActiveParam);
					int devDateActiveList = wtActiveInfoService
							.queryActiveCount(countryActiveParam);
				//	dayActive = devDateActiveList.size();

					resultBean.setCountry(wtBaseCount.getCountry());
					resultBean.setDayActive(devDateActiveList);
					resultBean.setSum(devSumActiveList);

					resultData.add(resultBean);
				}
			}
			return JsonUtils.objectToJson(resultData);
		}
	}
	*/
	/**
	 * 
	 * @Title:           getSearchOs
	 * @Description:     TODO
	 * @param:           @param dte
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping("/getSearch/os/{date}")
	@ResponseBody
	public String getSearchOs(@PathVariable int date,@RequestParam(value="",required=false)String departName,@RequestParam(value="",required=false)String devName,@RequestParam(value="",required=false)String country){
		
		EchartJson echartJson = new EchartJson();
		echartJson.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
		System.out.println(FormatcountDate(date, "yyyy-MM-dd"));
//		List<String> dateList = countDate(date+1);
		List<String> devNameList = new ArrayList<>();
		
		List<String> osList = new ArrayList<>();
		osList.add("Android");
		osList.add("IOS");
		
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
		if(departName !=null && !"".equals(departName)){
			List<WtDeviceInfo> departDevNameList = queryDepartDevList(departName);
			if(departDevNameList == null || "".equals(departDevNameList)){
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
			for (WtDeviceInfo wtDeviceInfo : departDevNameList) {
				devNameList.add(wtDeviceInfo.getModel());
			}
			Map<String,Object> param = new HashMap<>();
			if(devName != null && !"".equals(devName)){
				//param.put("devName", devName);
				if(!devNameList.contains(devName)){
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
				
				//查询这个机型的os,品牌，手机机型数据
				
				param.put("devName",devName);
				if(country !=null && !"".equals(country)){
					param.put("countryName", country);
				}
				
				
				for(String os:osList){
					param.put("mobile_op",os);
					//param.put("date", date);
					param.put("BegainDate", begainDate);
					param.put("EndDate", endDate);
					Item item = echartJson.new Item();
					item.setName(os);
					List<Long> sumOsList = new ArrayList<>();
					for(int i =0;i<dateList.size();i++){
						sumOsList.add(0L);
					}
					int osCountSize =0;
					param.put("devModel", devName);
					List<WtDeviceDateCount> osData = wtActiveInfoService
							.countsOsEveryDay(param);
					if (osData != null && osData.size() > 0) {
						List<String> osDataDateList = new ArrayList<>();
						for (WtDeviceDateCount wtDeviceDateCount : osData) {
							osDataDateList.add(DateUtil.format(
									wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
						}
						osCountSize = osData.size() - 1;
						int index = 0;
						for (int x = 0; x < dateList.size(); x++) {
							if (osDataDateList.contains(dateList.get(x))) {
								long sum = 0L;
								sum = osData.get(index).getCount()+sumOsList.get(index);
								sumOsList.add(x, sum);
								if (index >= 0 && index < osCountSize) {
									index++;
								}
							} 
						}
					}
					item.setData(sumOsList);
					echartJson.getItemData().add(item);
				}
				System.out.println(JsonUtils.objectToJson(echartJson));
				return JsonUtils.objectToJson(echartJson);
			}
			if(country !=null && !"".equals(country)){
				param.put("countryName", country);
			}
			for(String os:osList){
				param.put("mobile_op",os);
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				Item item = echartJson.new Item();
				item.setName(os);
				List<Long> sumOsList = new ArrayList<>();
				for(int i =0;i<dateList.size();i++){
					sumOsList.add(0L);
				}
				for(String devname  : devNameList){
					int osCountSize =0;
					param.put("devModel", devname);
					List<WtDeviceDateCount> osData = wtActiveInfoService
							.countsOsEveryDay(param);
					
					if (osData != null && osData.size() > 0) {
						List<String> osDataDateList = new ArrayList<>();
						for (WtDeviceDateCount wtDeviceDateCount : osData) {
							osDataDateList.add(DateUtil.format(
									wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
						}
						osCountSize = osData.size() - 1;
						int index = 0;
						for (int x = 0; x < dateList.size(); x++) {
							if (osDataDateList.contains(dateList.get(x))) {
								long sum = 0L;
								sum = osData.get(index).getCount()+sumOsList.get(index);
								sumOsList.add(x, sum);
								if (index >= 0 && index < osCountSize) {
									index++;
								}
							} 
						}
						
					}
				}
				item.setData(sumOsList);
				echartJson.getItemData().add(item);
			}
			System.out.println(JsonUtils.objectToJson(echartJson));
			return JsonUtils.objectToJson(echartJson);
			
			
			//
		}else if(devName != null && !"".equals(devName)){
			Map<String,Object> param = new HashMap<>();
			if(country !=null && !"".equals(country)){
				param.put("countryName", country);
			}
			for(String os:osList){
				param.put("mobile_op",os);
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				Item item = echartJson.new Item();
				item.setName(os);
				List<Long> sumOsList = new ArrayList<>();
				for(int i =0;i<dateList.size();i++){
					sumOsList.add(0L);
				}
				int osCountSize =0;
				param.put("devModel", devName);
				List<WtDeviceDateCount> osData = wtActiveInfoService
						.countsOsEveryDay(param);
				if (osData != null && osData.size() > 0) {
					List<String> osDataDateList = new ArrayList<>();
					for (WtDeviceDateCount wtDeviceDateCount : osData) {
						osDataDateList.add(DateUtil.format(
								wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
					}
					osCountSize = osData.size() - 1;
					int index = 0;
					for (int x = 0; x < dateList.size(); x++) {
						if (osDataDateList.contains(dateList.get(x))) {
							long sum = 0L;
							sum = osData.get(index).getCount()+sumOsList.get(index);
							sumOsList.add(x, sum);
							if (index >= 0 && index < osCountSize) {
								index++;
							}
						} 
					}
				}
				item.setData(sumOsList);
				echartJson.getItemData().add(item);
			}
			System.out.println(JsonUtils.objectToJson(echartJson));
			return JsonUtils.objectToJson(echartJson);
		}else if(country != null && !"".equals(country)){
			Map<String,Object> param = new HashMap<>();
			param.put("countryName", country);
			for(String os:osList){
				param.put("mobile_op",os);
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				Item item = echartJson.new Item();
				item.setName(os);
				List<Long> sumOsList = new ArrayList<>();
				for(int i =0;i<dateList.size();i++){
					sumOsList.add(0L);
				}
				int osCountSize =0;
				List<WtDeviceDateCount> osData = wtActiveInfoService
						.countsOsEveryDay(param);
				if (osData != null && osData.size() > 0) {
					List<String> osDataDateList = new ArrayList<>();
					for (WtDeviceDateCount wtDeviceDateCount : osData) {
						osDataDateList.add(DateUtil.format(
								wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
					}
					osCountSize = osData.size() - 1;
					int index = 0;
					for (int x = 0; x < dateList.size(); x++) {
						if (osDataDateList.contains(dateList.get(x))) {
							long sum = 0L;
							sum = osData.get(index).getCount()+sumOsList.get(index);
							sumOsList.add(x, sum);
							if (index >= 0 && index < osCountSize) {
								index++;
							}
						} 
					}
				}
				item.setData(sumOsList);
				echartJson.getItemData().add(item);
			}
			System.out.println(JsonUtils.objectToJson(echartJson));
			return JsonUtils.objectToJson(echartJson);
		}else{
			Map<String,Object> param = new HashMap<>();
			for(String os:osList){
				param.put("mobile_op",os);
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				Item item = echartJson.new Item();
				item.setName(os);
				List<Long> sumOsList = new ArrayList<>();
				for(int i =0;i<dateList.size();i++){
					sumOsList.add(0L);
				}
				int osCountSize =0;
				List<WtDeviceDateCount> osData = wtActiveInfoService
						.countsOsEveryDay(param);
				if (osData != null && osData.size() > 0) {
					List<String> osDataDateList = new ArrayList<>();
					for (WtDeviceDateCount wtDeviceDateCount : osData) {
						osDataDateList.add(DateUtil.format(
								wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
					}
					osCountSize = osData.size() - 1;
					int index = 0;
					for (int x = 0; x < dateList.size(); x++) {
						if (osDataDateList.contains(dateList.get(x))) {
							long sum = 0L;
							sum = osData.get(index).getCount()+sumOsList.get(index);
							sumOsList.add(x, sum);
							if (index >= 0 && index < osCountSize) {
								index++;
							}
						} 
					}
				}
				item.setData(sumOsList);
				echartJson.getItemData().add(item);
			}
			System.out.println(JsonUtils.objectToJson(echartJson));
			return JsonUtils.objectToJson(echartJson);
		}
	}
	
	
	
	/**
	 * 
	 * @Title:           getSearchBrand
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @param departName
	 * @param:           @param devName
	 * @param:           @param country
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping("/getSearch/brand/{date}")
	@ResponseBody
	public String getSearchBrand(@PathVariable int date,@RequestParam(value="",required=false)String departName,@RequestParam(value="",required=false)String devName,@RequestParam(value="",required=false)String country){
		EchartJson echartJson = new EchartJson();
		echartJson.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
		//List<String> dateList = countDate(date+1);
		List<String> devNameList = new ArrayList<>();
		

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
		
		if(departName != null && !"".equals(departName)){
			//事业部下所有的设备
			List<WtDeviceInfo> departDevNameList = queryDepartDevList(departName);
			if(departDevNameList == null || "".equals(departDevNameList)){
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
			for (WtDeviceInfo wtDeviceInfo : departDevNameList) {
				devNameList.add(wtDeviceInfo.getModel());
			}
			Map<String,Object> param = new HashMap<>();
			if(devName !=null && !"".equals(devName)){
				if(!devNameList.contains(devName)){
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
				//查询此设备的数据
				/*if(country != null && !"".equals(country)){
					param.put("countryName", country);
				}*/
				param.put("devModel", devName);
				
				/*
				//获取手机品牌
				Set<String> brandNameSet = new HashSet<>();
				//param.put("date", date);
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				param.put("devName", devName);
				List<WtBaseCount> brandNameList = wtActiveInfoService.countBrandName(param);
				if(brandNameList != null && brandNameList.size() > 0){
					for (WtBaseCount brandBean : brandNameList) {
						brandNameSet.add(brandBean.getMobile_brand());
					}
				}
				
				if(brandNameSet== null || brandNameSet.size() <= 0){
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
				for(String brandname:brandNameSet){
					param.put("mobile_brand",brandname);
					//param.put("date", date);
					param.put("BegainDate", begainDate);
					param.put("EndDate", endDate);
					Item item = echartJson.new Item();
					item.setName(brandname);
					List<Long> sumbrandList = new ArrayList<>();
					for(int i =0;i<dateList.size();i++){
						sumbrandList.add(0L);
					}
					int brandCountSize =0;
					param.put("devModel", devName);
					List<WtDeviceDateCount> brandData = wtActiveInfoService
							.countBrandEveryDay(param);
					if (brandData != null && brandData.size() > 0) {
						List<String> brandDataDateList = new ArrayList<>();
						for (WtDeviceDateCount wtDeviceDateCount : brandData) {
							brandDataDateList.add(DateUtil.format(
									wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
						}
						brandCountSize = brandData.size() - 1;
						int index = 0;
						for (int x = 0; x < dateList.size(); x++) {
							if (brandDataDateList.contains(dateList.get(x))) {
								long sum = 0L;
								sum = brandData.get(index).getCount()+sumbrandList.get(index);
								sumbrandList.add(x, sum);
								if (index >= 0 && index < brandCountSize) {
									index++;
								}
							} 
						}
					}
					item.setData(sumbrandList);
					echartJson.getItemData().add(item);
				}
				System.out.println(JsonUtils.objectToJson(echartJson));
				return JsonUtils.objectToJson(echartJson);
				*/
			}
			if(country != null && !"".equals(country)){
				param.put("countryName", country);
			}
			//查询事业部下的活跃手机品牌
			Set<String> brandNameSet = new HashSet<>();
			for (String devname : devNameList) {
				param.put("devName", devname);
				List<WtBaseCount> brandNameList = wtActiveInfoService.countBrandName(param);
				if(brandNameList != null && brandNameList.size() > 0){
					for (WtBaseCount brandBean : brandNameList) {
						brandNameSet.add(brandBean.getMobile_brand());
					}
					
				}
			}
			if(brandNameSet== null || brandNameSet.size() <= 0){
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
			
			for (String brandname : brandNameSet) {
				Item item = echartJson.new Item();
				item.setName(brandname);
				List<Long> sumBrandList = new ArrayList<>();
				for(int i =0;i<dateList.size();i++){
					sumBrandList.add(0L);
				}
				
				param.put("mobile_brand",brandname);
				for (String devname : devNameList) {
					int brandCountSize =0;
					param.put("devModel",devname);
					List<WtDeviceDateCount> brandData = wtActiveInfoService
							.countBrandEveryDay(param);
					if(brandData != null && brandData.size() > 0){
						List<String> brandDataDateList = new ArrayList<>();
						for (WtDeviceDateCount wtDeviceDateCount : brandData) {
							brandDataDateList.add(DateUtil.format(
									wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
						}
						brandCountSize = brandData.size() - 1;
						int index = 0;
						for (int x = 0; x < dateList.size(); x++) {
							if (brandDataDateList.contains(dateList.get(x))) {
								long sum = 0L;
								sum = brandData.get(index).getCount()+sumBrandList.get(index);
								sumBrandList.add(x, sum);
								if (index >= 0 && index < brandCountSize) {
									index++;
								}
							} 
						}
						
					}
				}
				item.setData(sumBrandList);
				echartJson.getItemData().add(item);
			}
			return JsonUtils.objectToJson(echartJson);
		}else if(devName  != null && !"".equals(devName)){
			
			Map<String,Object> param = new HashMap<>();
			if(country !=null && !"".equals(country)){
				param.put("countryName", country);
			}
			//获取手机品牌
			Set<String> brandNameSet = new HashSet<>();
			//param.put("date", date);
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			param.put("devName", devName);
			List<WtBaseCount> brandNameList = wtActiveInfoService.countBrandName(param);
			if(brandNameList != null && brandNameList.size() > 0){
				for (WtBaseCount brandBean : brandNameList) {
					brandNameSet.add(brandBean.getMobile_brand());
				}
			}
			
			if(brandNameSet== null || brandNameSet.size() <= 0){
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
			for(String brandname:brandNameSet){
				param.put("mobile_brand",brandname);
				//param.put("date", date);
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				Item item = echartJson.new Item();
				item.setName(brandname);
				List<Long> sumbrandList = new ArrayList<>();
				for(int i =0;i<dateList.size();i++){
					sumbrandList.add(0L);
				}
				int brandCountSize =0;
				param.put("devModel", devName);
				List<WtDeviceDateCount> brandData = wtActiveInfoService
						.countBrandEveryDay(param);
				if (brandData != null && brandData.size() > 0) {
					List<String> brandDataDateList = new ArrayList<>();
					for (WtDeviceDateCount wtDeviceDateCount : brandData) {
						brandDataDateList.add(DateUtil.format(
								wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
					}
					brandCountSize = brandData.size() - 1;
					int index = 0;
					for (int x = 0; x < dateList.size(); x++) {
						if (brandDataDateList.contains(dateList.get(x))) {
							long sum = 0L;
							sum = brandData.get(index).getCount()+sumbrandList.get(index);
							sumbrandList.add(x, sum);
							if (index >= 0 && index < brandCountSize) {
								index++;
							}
						} 
					}
				}
				item.setData(sumbrandList);
				echartJson.getItemData().add(item);
			}
			System.out.println(JsonUtils.objectToJson(echartJson));
			return JsonUtils.objectToJson(echartJson);
		}else if(country != null && !"".equals(country)){
			Map<String,Object> param = new HashMap<>();
			param.put("countryName", country);
			//获取手机品牌
			Set<String> brandNameSet = new HashSet<>();
			//param.put("date", date);
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			List<WtBaseCount> brandNameList = wtActiveInfoService.countBrandName(param);
			if(brandNameList != null && brandNameList.size() > 0){
				for (WtBaseCount brandBean : brandNameList) {
					brandNameSet.add(brandBean.getMobile_brand());
				}
			}
			
			if(brandNameSet== null || brandNameSet.size() <= 0){
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
			for(String brandname:brandNameSet){
				param.put("countryName", country);
				param.put("mobile_brand",brandname);
				//param.put("date", date);
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				Item item = echartJson.new Item();
				System.out.println("手机品牌"+brandname);
				item.setName(brandname);
				List<Long> sumbrandList = new ArrayList<>();
				for(int i =0;i<dateList.size();i++){
					sumbrandList.add(0L);
				}
				int brandCountSize =0;
				List<WtDeviceDateCount> brandData = wtActiveInfoService
						.countBrandEveryDay(param);
				if (brandData != null && brandData.size() > 0) {
					List<String> brandDataDateList = new ArrayList<>();
					for (WtDeviceDateCount wtDeviceDateCount : brandData) {
						brandDataDateList.add(DateUtil.format(
								wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
					}
					brandCountSize = brandData.size() - 1;
					int index = 0;
					for (int x = 0; x < dateList.size(); x++) {
						if (brandDataDateList.contains(dateList.get(x))) {
							long sum = 0L;
							sum = brandData.get(index).getCount()+sumbrandList.get(index);
							sumbrandList.add(x, sum);
							if (index >= 0 && index < brandCountSize) {
								index++;
							}
						} 
					}
				}
				item.setData(sumbrandList);
				echartJson.getItemData().add(item);
			}
			System.out.println(JsonUtils.objectToJson(echartJson));
			return JsonUtils.objectToJson(echartJson);
		}else{
			Map<String, Object> brandNameParam = new HashMap<>();
			Map<String, Object> brandEveryParam = new HashMap<>();

			//brandNameParam.put("date", date);
			brandNameParam.put("BegainDate", begainDate);
			brandNameParam.put("EndDate", endDate);
			//brandNameParam.put("limit", 5);

			List<WtBaseCount> brandNameList = wtActiveInfoService
					.countBrandName(brandNameParam);
			for (WtBaseCount wtBaseCount : brandNameList) {
				System.out.println(wtBaseCount.getMobile_brand());
			}
			// List<Double> countList = new ArrayList<>();
			for (String string : dateList) {
				System.out.println(string + "fsadf");
			}
			int brandCountSize;
			System.out.println("devNameList :" + brandNameList.size());
			if (brandNameList != null && brandNameList.size() > 0) {
				for (WtBaseCount wtBaseCount : brandNameList) {
					Item item = echartJson.new Item();
					System.out.println(wtBaseCount.getCountry());
					item.setName(wtBaseCount.getMobile_brand());
					brandEveryParam.clear();
					brandEveryParam.put("mobile_brand",
							wtBaseCount.getMobile_brand());
					//brandEveryParam.put("date", date);
					brandEveryParam.put("BegainDate", begainDate);
					brandEveryParam.put("EndDate", endDate);
					List<WtDeviceDateCount> brandEveryDayList = wtActiveInfoService
							.countBrandEveryDay(brandEveryParam);
					if (brandEveryDayList != null && brandEveryDayList.size() > 0) {
						List<String> brandCountDateList = new ArrayList<>();
						brandCountSize = brandEveryDayList.size() - 1;
						for (WtDeviceDateCount wtDeviceDateCount : brandEveryDayList) {
							brandCountDateList.add(DateUtil.format(
									wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
						}
						int index = 0;
						List<Long> countData = new ArrayList<>();
						for (WtDeviceDateCount wtDeviceDateCount : brandEveryDayList) {
							System.out.println(wtDeviceDateCount.getDate());
						}
						for (int i = 0; i < dateList.size(); i++) {
							if (brandCountDateList.contains(dateList.get(i))) {
								countData.add(brandEveryDayList.get(index)
										.getCount());
								if (index >= 0 && index < brandCountSize) {
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
		
	}
	
	/**
	 * 
	 * @Title:           getSearchModel
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @param departName
	 * @param:           @param devName
	 * @param:           @param country
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping("/getSearch/model/{date}")
	@ResponseBody
	public String getSearchModel(@PathVariable int date,@RequestParam(value="",required=false)String departName,@RequestParam(value="",required=false)String devName,@RequestParam(value="",required=false)String country){
		EchartJson echartJson = new EchartJson();
		echartJson.setxAxisData(FormatcountDate(date, "yyyy-MM-dd"));
		//List<String> dateList = countDate(date);
		List<String> devNameList = new ArrayList<>();
		
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
		
		
		if(departName != null && !"".equals(departName)){
			//事业部下所有的设备
			List<WtDeviceInfo> departDevNameList = queryDepartDevList(departName);
			if(departDevNameList == null || "".equals(departDevNameList)){
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
			for (WtDeviceInfo wtDeviceInfo : departDevNameList) {
				devNameList.add(wtDeviceInfo.getModel());
			}
			Map<String, Object> param = new HashMap<>();
			
			if(devName != null && !"".equals(devName)){
				if(!devNameList.contains(devName)){
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
				
				//
			}
			Set<String> modelNameSet = new HashSet<>();
			//获得某事业部设备活跃的手机品牌名
			for (String devname : devNameList) {
				param.put("devName", devname);
				//param.put("date",date);
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				param.put("limit", 5);
				List<WtBaseCount> brandNameList = wtActiveInfoService.countModelName(param);
				if(brandNameList != null && brandNameList.size() > 0){
					for (WtBaseCount brandBean : brandNameList) {
						modelNameSet.add(brandBean.getMobile_model());
					}
					
				}
			}
			
			if(modelNameSet== null || modelNameSet.size() <= 0){
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
			if(country !=  null && !"".equals(country)){
				param.put("countryName", country);
			}
			for (String modelName : modelNameSet) {
				Item item = echartJson.new Item();
				item.setName(modelName);
				List<Long> sumModelList = new ArrayList<>();
				for(int i =0;i<dateList.size();i++){
					sumModelList.add(0L);
				}
				param.put("mobile_model",modelName);
				for (String devname : devNameList) {
					int brandCountSize =0;
					param.put("devModel",devname);
					List<WtDeviceDateCount> modelData = wtActiveInfoService
							.countBrandEveryDay(param);
					if(modelData != null && modelData.size() > 0){
						List<String> modelDataDateList = new ArrayList<>();
						for (WtDeviceDateCount wtDeviceDateCount : modelData) {
							modelDataDateList.add(DateUtil.format(
									wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
						}
						brandCountSize = modelData.size() - 1;
						int index = 0;
						for (int x = 0; x < dateList.size(); x++) {
							if (modelDataDateList.contains(dateList.get(x))) {
								long sum = 0L;
								sum = modelData.get(index).getCount()+sumModelList.get(index);
								sumModelList.add(x, sum);
								if (index >= 0 && index < brandCountSize) {
									index++;
								}
							} 
						}
					}
				}
				item.setData(sumModelList);
				echartJson.getItemData().add(item);
			}
			return JsonUtils.objectToJson(echartJson);
				
				
		}else if(devName != null && !"".equals(devName)){
			
			Map<String, Object> param = new HashMap<>();
			Set<String> modelNameSet = new HashSet<>();
			//获得某事业部设备活跃的手机品牌名
				param.put("devName", devName);
				//param.put("date",date);
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				//param.put("limit", 5);
				List<WtBaseCount> brandNameList = wtActiveInfoService.countModelName(param);
				if(brandNameList != null && brandNameList.size() > 0){
					for (WtBaseCount brandBean : brandNameList) {
						modelNameSet.add(brandBean.getMobile_model());
					}
					
				}
			
			if(modelNameSet== null || modelNameSet.size() < 0){
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
			if(country != null && !"".equals(country)){
				param.put("countryName", country);
			}
			
			for (String modelName : modelNameSet) {
				Item item = echartJson.new Item();
				item.setName(modelName);
				List<Long> sumModelList = new ArrayList<>();
				for(int i =0;i<dateList.size();i++){
					sumModelList.add(0L);
				}
				param.put("mobile_model",modelName);
				int brandCountSize =0;
				param.put("devModel",devName);
				//param.put("date",date);
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				List<WtDeviceDateCount> modelData = wtActiveInfoService
						.countBrandEveryDay(param);
				if(modelData != null && modelData.size() > 0){
					List<String> modelDataDateList = new ArrayList<>();
					for (WtDeviceDateCount wtDeviceDateCount : modelData) {
						modelDataDateList.add(DateUtil.format(
								wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
					}
					brandCountSize = modelData.size() - 1;
					int index = 0;
					for (int x = 0; x < dateList.size(); x++) {
						if (modelDataDateList.contains(dateList.get(x))) {
							long sum = 0L;
							sum = modelData.get(index).getCount()+sumModelList.get(index);
							sumModelList.add(x, sum);
							if (index >= 0 && index < brandCountSize) {
								index++;
							}
						} 
					}
				}
				item.setData(sumModelList);
				echartJson.getItemData().add(item);
			}
			return JsonUtils.objectToJson(echartJson);
			
		}else if(country != null && !"".equals(country)){
			Map<String,Object> param = new HashMap<>();
			param.put("countryName", country);
			//获取手机品牌
			Set<String> modelNameSet = new HashSet<>();
			//param.put("date", date);
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			List<WtBaseCount> modelNameList = wtActiveInfoService.countModelName(param);
			if(modelNameList != null && modelNameList.size() > 0){
				for (WtBaseCount brandBean : modelNameList) {
					modelNameSet.add(brandBean.getMobile_model());
				}
			}
			
			if(modelNameSet== null || modelNameSet.size() < 0){
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
			for(String modeldname:modelNameSet){
				param.put("countryName", country);
				param.put("mobile_model",modeldname);
				//param.put("date", date);
				param.put("BegainDate", begainDate);
				param.put("EndDate", endDate);
				Item item = echartJson.new Item();
				System.out.println("手机型号"+modeldname);
				item.setName(modeldname);
				List<Long> summodelList = new ArrayList<>();
				for(int i =0;i<dateList.size();i++){
					summodelList.add(0L);
				}
				int modelCountSize =0;
				List<WtDeviceDateCount> modelData = wtActiveInfoService
						.countBrandEveryDay(param);
				if (modelData != null && modelData.size() > 0) {
					List<String> modelDataDateList = new ArrayList<>();
					for (WtDeviceDateCount wtDeviceDateCount : modelData) {
						modelDataDateList.add(DateUtil.format(
								wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
					}
					modelCountSize = modelData.size() - 1;
					int index = 0;
					for (int x = 0; x < dateList.size(); x++) {
						if (modelDataDateList.contains(dateList.get(x))) {
							long sum = 0L;
							sum = modelData.get(index).getCount()+summodelList.get(index);
							summodelList.add(x, sum);
							if (index >= 0 && index < modelCountSize) {
								index++;
							}
						} 
					}
				}
				item.setData(summodelList);
				echartJson.getItemData().add(item);
			}
			System.out.println(JsonUtils.objectToJson(echartJson));
			return JsonUtils.objectToJson(echartJson);
		}else{
			Map<String, Object> modelNameParam = new HashMap<>();
			Map<String, Object> modelEveryDayParam = new HashMap<>();

			//modelNameParam.put("date", date);
			modelNameParam.put("BegainDate", begainDate);
			modelNameParam.put("EndDate", endDate);
			//modelNameParam.put("limit", 5);

			List<WtBaseCount> modelNameList = wtActiveInfoService
					.countModelName(modelNameParam);
			if (modelNameList != null && modelNameList.size() > 0) {

				int modelCountSize;

				System.out.println("devNameList :" + modelNameList.size());
				for (WtBaseCount wtBaseCount : modelNameList) {
					System.out.println(wtBaseCount.getMobile_model());
					Item item = echartJson.new Item();
					item.setName(wtBaseCount.getMobile_model());
					modelEveryDayParam.clear();
					modelEveryDayParam.put("mobile_model",
							wtBaseCount.getMobile_model());
					//modelEveryDayParam.put("date", date);
					modelNameParam.put("BegainDate", begainDate);
					modelNameParam.put("EndDate", endDate);
					List<WtDeviceDateCount> modelEveryDayList = wtActiveInfoService
							.countBrandEveryDay(modelEveryDayParam);
					if (modelEveryDayList != null && modelEveryDayList.size() > 0) {
						List<String> modelCountDateList = new ArrayList<>();
						modelCountSize = modelEveryDayList.size() - 1;
						for (WtDeviceDateCount wtDeviceDateCount : modelEveryDayList) {
							modelCountDateList.add(DateUtil.format(
									wtDeviceDateCount.getDate(), "yyyy-MM-dd"));
						}
						int index = 0;
						List<Long> countData = new ArrayList<>();
						for (WtDeviceDateCount wtDeviceDateCount : modelEveryDayList) {
							System.out.println(wtDeviceDateCount.getDate());
						}
						for (int i = 0; i < dateList.size(); i++) {
							if (modelCountDateList.contains(dateList.get(i))) {
								countData.add(modelEveryDayList.get(index)
										.getCount());
								if (index >= 0 && index < modelCountSize) {
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
		
	}
	/**
	 * 
	 * @Title:           getSearchTableOs
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @param departName
	 * @param:           @param devName
	 * @param:           @param country
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping("/getSearchTable/os/{date}")
	@ResponseBody
	public String getSearchTableOs(@PathVariable int date,@RequestParam(value="",required=false)String departName,@RequestParam(value="",required=false)String devName,@RequestParam(value="",required=false)String country){
		List<WtBaseCount> resultData = new ArrayList<>();
		List<String> devNameList = new ArrayList<>();
		
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
		
		
		if(departName != null && !"".equals(departName)){
			//事业部下所有的设备
			List<WtDeviceInfo> departDevNameList = queryDepartDevList(departName);
			if(departDevNameList == null || "".equals(departDevNameList)){
				
				return JsonUtils.objectToJson(resultData);
			}
			for (WtDeviceInfo wtDeviceInfo : departDevNameList) {
				devNameList.add(wtDeviceInfo.getModel());
			}
			//Android活跃数
			Map<String,Object> paramMap = new HashMap<>();
			if(devName != null &&!"".equals(devName)){
				if(!devNameList.contains(devName)){
					return JsonUtils.objectToJson(resultData);
				}
				devNameList.clear();
				devNameList.add(devName);
				
			}
			int androidSum = 0;
			if(country != null && !"".equals(country)){
				paramMap.put("countryName", country);
			}
			paramMap.put("mobile_op","Android");
			//paramMap.put("date", date);
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			for(String devname : devNameList){
				paramMap.put("devModel",devname);
				int countDevAndroidList = wtActiveInfoService.countActiveNumber(paramMap);
				androidSum +=countDevAndroidList;
			}
			//IOS活跃数
			paramMap.clear();
			int iosSum = 0;
			if(country != null && !"".equals(country)){
				paramMap.put("countryName", country);
			}
			paramMap.put("mobile_op","IOS");
			//paramMap.put("date", date);
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			for(String devname : devNameList){
				paramMap.put("devModel",devname);
				int countIOSList = wtActiveInfoService.countActiveNumber(paramMap);
				iosSum +=countIOSList;
			}
			System.out.println("IOS size"+iosSum);
			System.out.println("Android size"+androidSum);
			int totalNum = iosSum + androidSum;
			if(totalNum > 0 ){
				WtBaseCount androidData = new WtBaseCount();
				androidData.setMobile_op("Android");
				androidData.setSum(androidSum);
				float androidOccupancy = (float) ((float) androidSum / (float) totalNum) * 100;
				androidData.setOccupancy(androidOccupancy + "%");
				System.out.println((float) ((float) androidSum / (float) totalNum)* 100 + "%");
				resultData.add(androidData);
				
				WtBaseCount iosData = new WtBaseCount();
				iosData.setMobile_op("IOS");
				iosData.setSum(iosSum);
				float iosOccupancy = (float) ((float) iosSum / (float) totalNum) * 100;
				iosData.setOccupancy(iosOccupancy + "%");
				System.out.println((float) ((float) iosSum / (float) totalNum)* 100 + "%");
				resultData.add(iosData);
			}
			return JsonUtils.objectToJson(resultData);
		}else if(devName != null && !"".equals(devName)){
			
			
			Map<String, Object> paramMap = new HashMap<>();
			if(country != null && !"".equals(country)){
				paramMap.put("countryName", country);
			}
			paramMap.put("devModel",devName);
			paramMap.put("mobile_op", "Android");
			//paramMap.put("date",date);
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			int countDevAndroidList = wtActiveInfoService.countActiveNumber(paramMap);
			int androidSum = countDevAndroidList;
			paramMap.put("mobile_op", "IOS");
			int countIOSList = wtActiveInfoService.countActiveNumber(paramMap);
			
			int iosSum = countIOSList;
			int totalNum = androidSum+iosSum;
			if(totalNum > 0){
				WtBaseCount androidData = new WtBaseCount();
				androidData.setMobile_op("Android");
				androidData.setSum(androidSum);
				float androidOccupancy = (float) ((float) androidSum / (float) totalNum) * 100;
				androidData.setOccupancy(androidOccupancy + "%");
				System.out.println((float) ((float) androidSum / (float) totalNum)* 100 + "%");
				resultData.add(androidData);
				
				WtBaseCount iosData = new WtBaseCount();
				iosData.setMobile_op("IOS");
				iosData.setSum(iosSum);
				float iosOccupancy = (float) ((float) iosSum / (float) totalNum) * 100;
				iosData.setOccupancy(iosOccupancy + "%");
				System.out.println((float) ((float) iosSum / (float) totalNum)* 100 + "%");
				resultData.add(iosData);
			}
			return JsonUtils.objectToJson(resultData);
			
		}else if(country != null && !"".equals(country)){
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("countryName",country);
			//paramMap.put("date", date);
			paramMap.put("BegainDate", begainDate);
			paramMap.put("EndDate", endDate);
			int countDevAndroidList = wtActiveInfoService.countActiveNumber(paramMap);
			int androidSum = countDevAndroidList;
			paramMap.put("mobile_op", "IOS");
			int countIOSList = wtActiveInfoService.countActiveNumber(paramMap);
			
			int iosSum = countIOSList;
			int totalNum = androidSum+iosSum;
			if(totalNum > 0){
				WtBaseCount androidData = new WtBaseCount();
				androidData.setMobile_op("Android");
				androidData.setSum(androidSum);
				float androidOccupancy = (float) ((float) androidSum / (float) totalNum) * 100;
				androidData.setOccupancy(androidOccupancy + "%");
				System.out.println((float) ((float) androidSum / (float) totalNum)* 100 + "%");
				resultData.add(androidData);
				
				WtBaseCount iosData = new WtBaseCount();
				iosData.setMobile_op("IOS");
				iosData.setSum(iosSum);
				float iosOccupancy = (float) ((float) iosSum / (float) totalNum) * 100;
				iosData.setOccupancy(iosOccupancy + "%");
				System.out.println((float) ((float) iosSum / (float) totalNum)* 100 + "%");
				resultData.add(iosData);
			}
			return JsonUtils.objectToJson(resultData);
		}else{
			Map<String, Object> param = new HashMap<>();
			//param.put("date", date);
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			param.put("mobile_op", "Android");
			int androidNum = 0;
			int iosNum = 0;
			int totalNum = 0;
			int androidList = wtActiveInfoService
					.countActiveNumber(param);
			//if (androidList != null && androidList.size() > 0) {
				androidNum = androidList;
			//}
			param.put("mobile_op", "IOS");
			int IosList = wtActiveInfoService
					.countActiveNumber(param);
			//if (IosList != null && IosList.size() > 0) {
				iosNum = IosList;
			//}
			
			totalNum = iosNum + androidNum;
			if(totalNum > 0){
				WtBaseCount androidData = new WtBaseCount();
				androidData.setMobile_op("Android");
				androidData.setSum(androidNum);
				float androidOccupancy = (float) ((float) androidNum / (float) totalNum) * 100;
				androidData.setOccupancy(androidOccupancy + "%");
				System.out
						.println((float) ((float) androidNum / (float) totalNum)
								* 100 + "%");
				resultData.add(androidData);
				
				WtBaseCount IosData = new WtBaseCount();
				IosData.setMobile_op("IOS");
				IosData.setSum(iosNum);
				float iosOccupancy = (float) ((float) iosNum / (float) totalNum) * 100;
				IosData.setOccupancy(iosOccupancy + "%");
				resultData.add(IosData);
			}
			// return JsonresultData;
			return JsonUtils.objectToJson(resultData);
		}
	}
	/**
	 * 
	 * @Title:           getSearchTableBrand
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @param departName
	 * @param:           @param devName
	 * @param:           @param country
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping("/getSearchTable/brand/{date}")
	@ResponseBody
	public String getSearchTableBrand(@PathVariable int date,@RequestParam(value="",required=false)String departName,@RequestParam(value="",required=false)String devName,@RequestParam(value="",required=false)String country){
		Map<String, Object> param = new HashMap<>();
		List<WtBaseCount> resultData = new ArrayList<>();
		List<String> devNameList = new ArrayList<>();
		Set<String> brandSet = new HashSet<>();
		Map<String,Object> resultMap = new HashMap<>();
		WtBaseCount baseCount = new WtBaseCount();
		baseCount.setMobile_brand("");
		baseCount.setOccupancy("");
		baseCount.setSum(0);
		resultMap.put("data",baseCount);
		
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
		if(departName != null && !"".equals(departName)){
			//事业部下所有的设备
			List<WtDeviceInfo> departDevNameList = queryDepartDevList(departName);
			if(departDevNameList == null || "".equals(departDevNameList)){
				
				return JsonUtils.objectToJson(resultMap);
			}
			for (WtDeviceInfo wtDeviceInfo : departDevNameList) {
				devNameList.add(wtDeviceInfo.getModel());
			}
			
			if(devName != null  && !"".equals(devName)){
				if(!devNameList.contains(devName)){
					return JsonUtils.objectToJson(resultMap);
				}
				
				param.put("devModel", devName);
			}
			//param.put("date", date);
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			if(country != null && !"".equals(country)){
				param.put("countryName", country);
			}
			for (String devname : devNameList) {
				param.put("devModel", devname);
				List<WtBaseCount> brandNameList = wtActiveInfoService.countBrandName(param);
				if(brandNameList != null && brandNameList.size() > 0){
					for (WtBaseCount wtBaseCount : brandNameList) {
						brandSet.add(wtBaseCount.getMobile_brand());
					}
				}
			}
			int sum =0;
			if(brandSet != null && brandSet.size()  >0 ){
				
				for (String brandName : brandSet) {
					param.put("mobile_brand",brandName);
					//int brandSum = 0;
					for (String devname : devNameList) {
						param.put("devModel",devname);
						
						int brandList = wtActiveInfoService.countActiveNumber(param);
						//if(brandList != null && brandList.size() >0){
							sum += brandList;
						//}
					}
					//brandBase.setSum(brandSum);
				}
				if(sum > 0){
					for (String brandName : brandSet) {
						param.put("mobile_brand",brandName);
						WtBaseCount brandBean = new WtBaseCount();
						brandBean.setMobile_brand(brandName);
						int brandSum = 0;
						for (String devname : devNameList) {
							param.put("devModel",devname);
							
							int brandList = wtActiveInfoService.countActiveNumber(param);
							//if(brandList != null && brandList.size() >0){
								brandSum += brandList;
							//}
						}
						float brandOccupancy = (float) ((float) brandSum / (float) sum) * 100;
						brandBean.setOccupancy(brandOccupancy + "%");
						brandBean.setSum(brandSum);
						resultData.add(brandBean);
					}
					resultMap.put("data",resultData);
					return JsonUtils.objectToJson(resultMap);
				}
				
			}
			
			return JsonUtils.objectToJson(resultMap);
			
		}else if(devName != null && !"".equals(devName)){
			param.put("devModel",devName);
			//param.put("date",date);
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			if(country != null && !"".equals(country)){
				param.put("countryName",country);
			}
			int sum = 0;
			int allData = wtActiveInfoService.countActiveNumber(param);
			if(allData <= 0){
				return JsonUtils.objectToJson(resultMap);
			}else{
				sum = allData;
			}
			List<WtBaseCount> brandList = wtActiveInfoService.countBrandName(param);
			if(brandList == null || brandList.size() <= 0){
				return JsonUtils.objectToJson(resultMap);
			}
			for (WtBaseCount wtBaseCount : brandList) {
				WtBaseCount wtBase = new WtBaseCount();
				param.put("mobile_brand", wtBaseCount.getMobile_brand());
				int brandNameList = wtActiveInfoService.countActiveNumber(param);
				if( brandNameList > 0){
					wtBase.setMobile_brand(wtBaseCount.getMobile_brand());
					int brandSum = brandNameList;
					wtBase.setSum(brandSum);
					float brandOccupancy = (float) ((float) brandSum / (float) sum) * 100;
					wtBase.setOccupancy(brandOccupancy + "%");
				}
				resultData.add(wtBase);
			}
			resultMap.put("data",resultData);
			return JsonUtils.objectToJson(resultMap);
		}else if(country != null && !"".equals(country)){
			param.put("countryName",country);
			//param.put("date",date);
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			int sum = 0;
			int allData = wtActiveInfoService.countActiveNumber(param);
			if( allData <= 0){
				return JsonUtils.objectToJson(resultMap);
			}else{
				sum = allData;
			}
			List<WtBaseCount> brandList = wtActiveInfoService.countBrandName(param);
			if(brandList == null || brandList.size() <= 0){
				return JsonUtils.objectToJson(resultMap);
			}
			for (WtBaseCount wtBaseCount : brandList) {
				WtBaseCount wtBase = new WtBaseCount();
				param.put("mobile_brand", wtBaseCount.getMobile_brand());
				int brandNameList = wtActiveInfoService.countActiveNumber(param);
				if(  brandNameList > 0){
					wtBase.setMobile_brand(wtBaseCount.getMobile_brand());
					int brandSum = brandNameList;
					wtBase.setSum(brandSum);
					float brandOccupancy = (float) ((float) brandSum / (float) sum) * 100;
					wtBase.setOccupancy(brandOccupancy + "%");
				}
				resultData.add(wtBase);
			}
			resultMap.put("data",resultData);
			return JsonUtils.objectToJson(resultMap);
		}else{
			//param.put("date", date);
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			List<WtBaseCount> totalList = wtActiveInfoService
					.countActiveData(param);
			System.out.println("totalList : " + totalList.size());
			if (totalList == null || totalList.size() <= 0) {
				return JsonUtils.objectToJson(resultMap);
			}
			int totalNum = totalList.size();
			param.clear();
			//param.put("date", date);
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			param.put("mobile_brand", "brand");
			List<WtBaseCount> brandNameList = wtActiveInfoService
					.distinctData(param);
			if(brandNameList == null || brandNameList.size() <= 0){
				return JsonUtils.objectToJson(resultMap);
			}
			for (WtBaseCount wtBaseCount : brandNameList) {
				int brandNum = 0;
				param.put("mobile_brand", wtBaseCount.getMobile_brand());
				List<WtBaseCount> brandList = wtActiveInfoService
						.countActiveData(param);
				if (brandList != null && brandList.size() > 0) {
					brandNum = brandList.size();
				}
				WtBaseCount brandBean = new WtBaseCount();
				brandBean.setMobile_brand(wtBaseCount.getMobile_brand());
				brandBean.setSum(brandNum);
				float brandOccupancy = (float) ((float) brandNum / (float) totalNum) * 100;
				brandBean.setOccupancy(brandOccupancy + "%");
				resultData.add(brandBean);
			}
			resultMap.put("data", resultData);
			return JsonUtils.objectToJson(resultMap);
		}
	}
	/**
	 * 
	 * @Title:           getSearchModelTable
	 * @Description:     TODO
	 * @param:           @param date
	 * @param:           @param departName
	 * @param:           @param devName
	 * @param:           @param country
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping("/getSearchTable/model/{date}")
	@ResponseBody
	public String getSearchModelTable(@PathVariable int date,@RequestParam(value="",required=false)String departName,@RequestParam(value="",required=false)String devName,@RequestParam(value="",required=false)String country){
		Map<String, Object> param = new HashMap<>();
		List<WtBaseCount> resultData = new ArrayList<>();
		List<String> devNameList = new ArrayList<>();
		Set<String> modelSet = new HashSet<>();
		Map<String,Object> resultMap = new HashMap<>();
		WtBaseCount baseCount = new WtBaseCount();
		baseCount.setMobile_model("");
		baseCount.setOccupancy("");
		baseCount.setSum(0);
		resultMap.put("data",baseCount);
		
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
		
		if(departName != null && !"".equals(departName)){
			//事业部下所有的设备
			List<WtDeviceInfo> departDevNameList = queryDepartDevList(departName);
			if(departDevNameList == null || "".equals(departDevNameList)){
				
				return JsonUtils.objectToJson(resultMap);
			}
			for (WtDeviceInfo wtDeviceInfo : departDevNameList) {
				devNameList.add(wtDeviceInfo.getModel());
			}
			
			if(devName != null  && !"".equals(devName)){
				if(!devNameList.contains(devName)){
					return JsonUtils.objectToJson(resultMap);
				}
				
				param.put("devModel", devName);
			}
			//param.put("date", date);
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			if(country != null && !"".equals(country)){
				param.put("countryName", country);
			}
			for (String devname : devNameList) {
				param.put("devModel", devname);
				List<WtBaseCount> brandNameList = wtActiveInfoService.countModelName(param);
				if(brandNameList != null && brandNameList.size() > 0){
					for (WtBaseCount wtBaseCount : brandNameList) {
						modelSet.add(wtBaseCount.getMobile_model());
					}
				}
			}
			int sum =0;
			if(modelSet != null && modelSet.size()  >0 ){
				
				for (String modelName : modelSet) {
					param.put("mobile_model",modelName);
					//int brandSum = 0;
					for (String devname : devNameList) {
						param.put("devModel",devname);
						
						int modelList = wtActiveInfoService.countActiveNumber(param);
						//if(modelList != null && modelList.size() >0){
							sum += modelList;
						//}
					}
					//brandBase.setSum(brandSum);
				}
				if(sum > 0){
					for (String modelName : modelSet) {
						param.put("mobile_model",modelName);
						WtBaseCount brandBean = new WtBaseCount();
						brandBean.setMobile_model(modelName);
						int modelSum = 0;
						for (String devname : devNameList) {
							param.put("devModel",devname);
							
						int modelList = wtActiveInfoService.countActiveNumber(param);
							//if(modelList != null && modelList.size() >0){
								modelSum += modelList;
							//}
						}
						float brandOccupancy = (float) ((float) modelSum / (float) sum) * 100;
						brandBean.setOccupancy(brandOccupancy + "%");
						brandBean.setSum(modelSum);
						resultData.add(brandBean);
					}
					resultMap.put("data",resultData);
					return JsonUtils.objectToJson(resultMap);
				}
				
			}
			
			return JsonUtils.objectToJson(resultMap);
		}else if( devName != null && !"".equals(devName)){
			param.put("devModel",devName);
			//param.put("date",date);
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			if(country != null && !"".equals(country)){
				param.put("countryName",country);
			}
			int sum = 0;
			int allData = wtActiveInfoService.countActiveNumber(param);
			if( allData <= 0){
				return JsonUtils.objectToJson(resultMap);
			}else{
				sum = allData;
			}
			List<WtBaseCount> modelList = wtActiveInfoService.countModelName(param);
			if(modelList == null || modelList.size() <= 0){
				return JsonUtils.objectToJson(resultMap);
			}
			for (WtBaseCount wtBaseCount : modelList) {
				WtBaseCount wtBase = new WtBaseCount();
				param.put("mobile_model", wtBaseCount.getMobile_model());
				int modelNameList = wtActiveInfoService.countActiveNumber(param);
				if( modelNameList> 0){
					wtBase.setMobile_model(wtBaseCount.getMobile_model());
					int modelSum = modelNameList;
					wtBase.setSum(modelSum);
					float modelOccupancy = (float) ((float) modelSum / (float) sum) * 100;
					wtBase.setOccupancy(modelOccupancy + "%");
				}
				resultData.add(wtBase);
			}
			resultMap.put("data",resultData);
			return JsonUtils.objectToJson(resultMap);
		}else if(country != null && !"".equals(country)){
			param.put("countryName",country);
			//param.put("date",date);
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			int sum = 0;
			int allData = wtActiveInfoService.countActiveNumber(param);
			if(allData <= 0){
				return JsonUtils.objectToJson(resultMap);
			}else{
				sum = allData;
			}
			List<WtBaseCount> modelList = wtActiveInfoService.countModelName(param);
			if(modelList == null || modelList.size() <= 0){
				return JsonUtils.objectToJson(resultMap);
			}
			for (WtBaseCount wtBaseCount : modelList) {
				WtBaseCount wtBase = new WtBaseCount();
				param.put("mobile_model", wtBaseCount.getMobile_model());
				int modelNameList = wtActiveInfoService.countActiveNumber(param);
				if( modelNameList> 0){
					wtBase.setMobile_model(wtBaseCount.getMobile_model());
					int modelSum = modelNameList;
					wtBase.setSum(modelSum);
					float brandOccupancy = (float) ((float) modelSum / (float) sum) * 100;
					wtBase.setOccupancy(brandOccupancy + "%");
				}
				resultData.add(wtBase);
			}
			resultMap.put("data",resultData);
			return JsonUtils.objectToJson(resultMap);
		}else{
			//param.put("date", date);
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			List<WtBaseCount> totalList = wtActiveInfoService
					.countActiveData(param);
			System.out.println("totalList : " + totalList.size());
			if (totalList == null || totalList.size() <= 0) {
				return JsonUtils.objectToJson(resultMap);
			}
			int totalNum = totalList.size();
			param.clear();
			//param.put("date", date);
			param.put("BegainDate", begainDate);
			param.put("EndDate", endDate);
			param.put("mobile_model", "model");
			List<WtBaseCount> modelNameList = wtActiveInfoService
					.distinctData(param);
			for (WtBaseCount wtBaseCount : modelNameList) {
				int modelNum = 0;
				param.put("mobile_model", wtBaseCount.getMobile_model());
				List<WtBaseCount> modelList = wtActiveInfoService
						.countActiveData(param);
				if (modelList != null && modelList.size() > 0) {
					modelNum = modelList.size();
				}
				WtBaseCount modelBean = new WtBaseCount();
				modelBean.setMobile_model(wtBaseCount.getMobile_model());
				modelBean.setSum(modelNum);
				float modelOccupancy = (float) ((float) modelNum / (float) totalNum) * 100;
				modelBean.setOccupancy(modelOccupancy + "%");
				resultData.add(modelBean);
			}
			resultMap.put("data", resultData);
			return JsonUtils.objectToJson(resultMap);
		}
		
		
	}
	/**
	 * 获取事业部下的设备名称
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
			/*for (WtDeviceInfo wtDeviceInfo : devList) {
				devNameList.add(wtDeviceInfo.getModel());
			}*/
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
		//JSONObject jsonObject = new JSONObject();
		List<WtBaseCount> devList3 = new ArrayList<>();
		//System.out.println(userStatus+"=======================================================================================");
		if(userStatus != null && (!"".equals(userStatus)) && "admin".equals(userStatus)){
			//查询所有国家
			paramMap.put("country", userStatus);
			List<WtBaseCount> devList = wtActiveInfoService.distinctData(paramMap);
			return JsonUtils.objectToJson(devList);
		}else if(userStatus != null && !"".equals(userStatus)){
			System.out.println(userStatus+"=fsafs==============================");
			List<WtDeviceInfo> devList = queryDepartDevList(userStatus);
			if(devList != null && devList.size() > 0){
				paramMap.put("devList",devList);
				paramMap.put("country", userStatus);
				List<WtBaseCount> devList2 = wtActiveInfoService.distinctData(paramMap);
				return JsonUtils.objectToJson(devList2);
			}
			
		}
		return JsonUtils.objectToJson(devList3);
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
	 * 获得指定日期的前一天
	 * 
	 * @param specifiedDay
	 * @return
	 * @throws Exception
	 */
	public static String getSpecifiedDayBefore(String specifiedDay) {// 可以用new
																		// Date().toLocalString()传递参数
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
				.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 7);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
				.format(c.getTime());
		return dayAfter;
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
			fetureDaysList.add(getFetureDate(i));
		}
		return pastDaysList;
	}

	public static ArrayList<String> FormatcountDate(int intervals, String format) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		for (int i = 0; i < intervals; i++) {
			pastDaysList.add(getFormatPastDate(i, format));
		}
		return pastDaysList;
	}

	public static String getFormatPastDate(int past, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				- past);
		Date today = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String result = simpleDateFormat.format(today);
		return result;
	}

	/**
	 * 获取过去第几天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				- past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	/**
	 * 获取未来 第 past 天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getFetureDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				+ past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}
	
	@RequestMapping("/insertActive")
	@ResponseBody
	public String insertActive(@RequestParam String data){
		//JSONObject jsonData = 
		JSONObject jsonObj = JSON.parseObject(data);  
		JSONArray jsondata = jsonObj.getJSONArray("data");
		List<WtActiveInfo> list = JsonUtils.jsonToList(jsondata.toJSONString(), WtActiveInfo.class);
		Map<String,Object> resultMap = new HashMap<>();
		int result = wtActiveInfoService.insertActive(list);
		if(result > 0){
			resultMap.put("result", 200);
		}else{
			resultMap.put("result", -1);
		}
		return JsonUtils.objectToJson(resultMap);
	}

}
