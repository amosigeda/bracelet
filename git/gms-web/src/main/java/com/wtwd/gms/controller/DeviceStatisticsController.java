package com.wtwd.gms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


	/**
	 * 机型统计
	 * @author wtj
	 * @data 2017年6月30日
	 */
	@Controller
	@RequestMapping("/statistics")
	public class DeviceStatisticsController extends BaseController{

		@RequestMapping("list")
		public String showList(){
			return "device/device_statistics";
		}
	}


