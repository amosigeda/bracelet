package com.wtwd.gms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtwd.gms.entity.WtActivationInfo;
import com.wtwd.gms.entity.WtDevice;
import com.wtwd.gms.entity.WtDeviceInfo;
import com.wtwd.gms.service.WtActivationInfoService;
import com.wtwd.gms.service.WtDeviceInfoService;
import com.wtwd.gms.service.WtDeviceService;
import com.wtwd.gms.utils.JsonUtils;

/**
 * 设备统计
 * @author wuhaihui
 * @data 2017年7月3日
 */
@Controller
@RequestMapping("/equipment/statistics")
public class EquipmentStatisticsController extends BaseController{

	@Autowired
	private WtDeviceInfoService wtDeviceInfoService;
	@Autowired
	private WtDeviceService wtDeviceService;
	@Autowired
	private WtActivationInfoService wtActivationInfoService;
	@RequestMapping("/list")
	public String list(){
		return "device/equipment_statistics";
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
	
	@RequestMapping("/getEquipmentStatistics/table")
	@ResponseBody
	public String getEquipmentStatisticsTable(@RequestParam String userStatus,@RequestParam String userType,@RequestParam(defaultValue="",required=false) String devName){
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		if(userType != null && !"".equals(userType)&&"ADMIN".equals(userType)){
			//查询所有的设备
			List<WtDevice> devNameList = new ArrayList<>();
			Map<String,Object> map = new HashMap<>();
			
			if(devName != null && !"".equals(devName)){
				WtDevice wtDevice = new WtDevice();
				wtDevice.setDevName(devName);
				devNameList.add(wtDevice);
			}else{
				devNameList = wtDeviceService.queryAllDepartmentInfo();
			}
			if(devNameList == null || devNameList.size() <=0){
				Map<String,Object> dataInfoMap = new HashMap<>();
				dataInfoMap.put("dev_name", "无");
				dataInfoMap.put("firstdata100", "无");
				dataInfoMap.put("firstdata", "无");
				dataInfoMap.put("firstdata1", "无");
				dataInfoMap.put("saledata100", "无");
				resultList.add(dataInfoMap);
				map.put("data",resultList);
				return JsonUtils.objectToJson(map);
			}
			Map<String, Object> paramMap = new HashMap<>();
			
			for (WtDevice wtDevice : devNameList) {
				Map<String,Object> dataInfoMap = new HashMap<>();
				dataInfoMap.put("dev_name", wtDevice.getDevName());
				paramMap.put("deviceName", wtDevice.getDevName());
				
				//首次出现日期
				List<WtActivationInfo> list = wtActivationInfoService.queryEquipment(paramMap);
				if(list !=null && list.size() > 0){
					dataInfoMap.put("firstdata", list.get(0).getActivationTime());
				}else{
					dataInfoMap.put("firstdata", "无");
				}
				//海外首次出现日期
				paramMap.put("countryName","中国");
				List<WtActivationInfo> list2 = wtActivationInfoService.queryEquipment(paramMap);
				if(list2 !=null && list2.size() > 0){
					dataInfoMap.put("firstdata1", list2.get(0).getActivationTime());
				}else{
					dataInfoMap.put("firstdata1", "无");
				}
				paramMap.clear();
				paramMap.put("deviceName", wtDevice.getDevName());
				
				//首次破百日期
				List<WtActivationInfo> list3 = wtActivationInfoService.queryEquipmentLimit(paramMap);
				if(list3 !=null && list3.size() > 0){
					dataInfoMap.put("firstdata100", list3.get(0).getActivationTime());
				}else{
					dataInfoMap.put("firstdata100", "无");
				}
				//海外销量首次破百日期
				paramMap.put("countryName","中国");
				List<WtActivationInfo> list4 = wtActivationInfoService.queryEquipmentLimit(paramMap);
				if(list4 !=null && list4.size() > 0){
					dataInfoMap.put("saledata100", list4.get(0).getActivationTime());
				}else{
					dataInfoMap.put("saledata100", "无");
				}
				resultList.add(dataInfoMap);
			}
			
			map.put("data",resultList);
			System.out.println(JsonUtils.objectToJson(map));
			return JsonUtils.objectToJson(map);
		}else if(userType != null && !"".equals(userType) && ("department".equals(userType)||"worker".equals(userType))){
			//自己部门下的设备
			List<WtDeviceInfo> devNameList= new ArrayList<>();
			Map<String, Object> paramMap = new HashMap<>();
			Map<String,Object> map = new HashMap<>();
			
			if(devName != null && !"".equals(devName)){
				WtDeviceInfo wtDeviceInfo = new WtDeviceInfo();
				wtDeviceInfo.setModel(devName);
				devNameList.add(wtDeviceInfo);
			}else{
				devNameList = queryDepartDevList(userStatus);
			}
			if(devNameList == null || devNameList.size() <=0){
				Map<String,Object> dataInfoMap = new HashMap<>();
				dataInfoMap.put("dev_name", "无");
				dataInfoMap.put("firstdata100", "无");
				dataInfoMap.put("firstdata", "无");
				dataInfoMap.put("firstdata1", "无");
				dataInfoMap.put("saledata100", "无");
				resultList.add(dataInfoMap);
				map.put("data",resultList);
				return JsonUtils.objectToJson(map);
			}
			for (WtDeviceInfo wtDeviceInfo : devNameList) {
				Map<String,Object> dataInfoMap = new HashMap<>();
				dataInfoMap.put("dev_name", wtDeviceInfo.getModel());
				paramMap.put("deviceName", wtDeviceInfo.getModel());
				
				//首次出现日期
				List<WtActivationInfo> list = wtActivationInfoService.queryEquipment(paramMap);
				if(list !=null && list.size() > 0){
					dataInfoMap.put("firstdata", list.get(0).getActivationTime());
				}else{
					dataInfoMap.put("firstdata", "无");
				}
				//海外首次出现日期
				paramMap.put("countryName","中国");
				List<WtActivationInfo> list2 = wtActivationInfoService.queryEquipment(paramMap);
				if(list2 !=null && list2.size() > 0){
					dataInfoMap.put("firstdata1", list2.get(0).getActivationTime());
				}else{
					dataInfoMap.put("firstdata1", "无");
				}
				paramMap.clear();
				paramMap.put("deviceName", wtDeviceInfo.getModel());
				
				//首次破百日期
				List<WtActivationInfo> list3 = wtActivationInfoService.queryEquipmentLimit(paramMap);
				if(list3 !=null && list3.size() > 0){
					dataInfoMap.put("firstdata100", list3.get(0).getActivationTime());
				}else{
					dataInfoMap.put("firstdata100", "无");
				}
				//海外销量首次破百日期
				paramMap.put("countryName","中国");
				List<WtActivationInfo> list4 = wtActivationInfoService.queryEquipmentLimit(paramMap);
				if(list4 !=null && list4.size() > 0){
					dataInfoMap.put("saledata100", list4.get(0).getActivationTime());
				}else{
					dataInfoMap.put("saledata100", "无");
				}
				resultList.add(dataInfoMap);
			}
			map.put("data",resultList);
			System.out.println(JsonUtils.objectToJson(map));
			return JsonUtils.objectToJson(map);
		}else {
			
		}
		
		//首次出现日期
		//海外首次出现日期
		
		//首次破百日期
		
		//海外销量首次破百日期
		return null;
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
}
