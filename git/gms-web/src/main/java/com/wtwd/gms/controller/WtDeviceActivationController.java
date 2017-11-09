package com.wtwd.gms.controller;

import java.text.ParseException;
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

import com.wtwd.gms.entity.EchartJson;
import com.wtwd.gms.entity.WtBaseCount;
import com.wtwd.gms.entity.WtDeviceDateCount;
import com.wtwd.gms.entity.EchartJson.Item;
import com.wtwd.gms.excel.DateUtil;
import com.wtwd.gms.service.WtActivationInfoService;
import com.wtwd.gms.utils.JsonUtils;

@Controller
@RequestMapping("/device/activationw")
public class WtDeviceActivationController extends BaseController {

	@Autowired
	private WtActivationInfoService wtActivationInfoService;
	@RequestMapping("/getChartData/device/{date}")
	@ResponseBody
	public String countActiveDevice(@PathVariable int date){
		Map<String, Object> paramMap = new HashMap<>();
		Map<String, Object> paramMap2 = new HashMap<>();
		paramMap.put("date", date);

		// paramMap.put("countryName", "美国");
		// 获取N天活跃最多的五个设备名称
		List<WtBaseCount> devNameList = wtActivationInfoService
				.countActivationDeviceName(paramMap);
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
				paramMap2.put("date", date);
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
}
