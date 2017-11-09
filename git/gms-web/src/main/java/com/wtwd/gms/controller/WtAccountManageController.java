package com.wtwd.gms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtwd.gms.entity.WtAccountManage;
import com.wtwd.gms.service.WtAccountManageService;
import com.wtwd.gms.utils.JsonUtils;

@Controller
@RequestMapping("/accountManage")
public class WtAccountManageController extends BaseController{

	@Autowired
	private WtAccountManageService wtAccountManageService;
	
	
	
	
	@RequestMapping("/list")
	public String WtAccountManage(){
		return "user/account_management";
	}
	
	@RequestMapping("/queryAll")
	@ResponseBody
	public String queryAllWtAccountManageInfo(){
		List<WtAccountManage> listInfo = wtAccountManageService.queryWtAccountManagesInfo();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data",listInfo);
		return JsonUtils.objectToJson(resultMap);
	}
}
