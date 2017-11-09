package com.wtwd.gms.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.wtwd.gms.entity.WtDeviceInfo;
import com.wtwd.gms.entity.WtStorageInfo;
import com.wtwd.gms.entity.WtUserInfo;
import com.wtwd.gms.excel.DateUtil;
import com.wtwd.gms.excel.ExcelHelper;
import com.wtwd.gms.excel.JxlExcelHelper;
import com.wtwd.gms.excel.XssfExcelHelper;
import com.wtwd.gms.service.WtDeviceInfoService;
import com.wtwd.gms.service.WtDeviceService;
import com.wtwd.gms.service.WtStorageInfoService;
import com.wtwd.gms.service.WtUserInfoService;
import com.wtwd.gms.utils.ConstantClass;
import com.wtwd.gms.utils.JsonUtils;

/**
 * 设备入库
 * @author liufeng
 * @date 2017-7-3
 */
@Controller
@RequestMapping("/device/storage")
public class DeviceStorageController extends BaseController {

	private static final Log log = LogFactory.getLog(DeviceStorageController.class);
	@Autowired
	private WtDeviceInfoService wtDeviceInfoService;
	@Autowired
	private WtStorageInfoService wtStorageInfoService;
	@Autowired
	private WtUserInfoService wtUserInfoService;
	@Autowired 
	private WtDeviceService wtDeviceService;
	/**
	 * 数据展示
	 */
//	@RequiresPermissions("device:storage:view")
	@RequestMapping("/list")
	public String list(Model model){
		/*//Model
		Map<String,Object> paramMap = new HashMap<String, Object>();
		//今日入库
		paramMap.put("create_time",DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE));
		//int todayList = wtDeviceInfoService.
		int todayInputNum = wtDeviceInfoService.queryInputData(DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE));
		//昨日入库
		int yestodayInputNum = wtDeviceInfoService.queryInputData(DateUtil.getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));
		//累积入库
		paramMap.clear();
		int allInputNum = wtDeviceInfoService.queryDeviceNum(paramMap);
		
		//存货明细
		model.addAttribute("todayInputNum", todayInputNum);
		
		model.addAttribute("yestodayInputNum", yestodayInputNum);
		model.addAttribute("allInputNum", allInputNum);
		model.addAttribute("yestodayInputNum", yestodayInputNum);*/
		return "device/device_storage";
	}
	
	/**
	 * 获取数据
	 */
	@RequestMapping(value="/queryDataInfo",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String queryDataInfo(@RequestParam(value="",required=false) String userName,@RequestParam(value="",required=false) String userType){
		try {
			/*PrintWriter out = response.getWriter();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");*/
			JSONObject json = new JSONObject();
			if(userType != null && "worker".equals(userType)){
				List<WtStorageInfo> list = wtStorageInfoService.queryStorageInfoByWorkerName(userName);
				
				JSONArray jsonArray = (JSONArray) JSONObject.toJSON(list);
				json.put("data", jsonArray);
				System.out.println("json:"+json);
				
			}else if(userType != null && "ADMIN".equals(userType)){
				List<WtStorageInfo> list = wtStorageInfoService.queryDataInfo();
				
				JSONArray jsonArray = (JSONArray) JSONObject.toJSON(list);
				json.put("data", jsonArray);
				System.out.println("json:"+json);
			}else if(userType != null && "department".equals(userType)){
				String worker = wtUserInfoService.queryWorkerByUserName(userName);
				List<WtStorageInfo> list = wtStorageInfoService.queryStorageInfoByWorkerName(worker);
				
				JSONArray jsonArray = (JSONArray) JSONObject.toJSON(list);
				json.put("data", jsonArray);
				System.out.println("json:"+json);
			}
			return json.toString();
			/*out.print(json);
			out.close();*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/inputData")
	@ResponseBody
	public String queryInputData(@RequestParam String userType,@RequestParam String userName,@RequestParam String userStatus) {
		//Model
		Map<String,Object> paramMap = new HashMap<String, Object>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		JSONObject jsonObject = new JSONObject();
		//PrintWriter out = response.getWriter();
		//今日入库
		
			//PrintWriter outPrintWriter = response.getWriter();
			if(userType != null && "ADMIN".equals(userType)){
				paramMap.put("createTime",DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE));
				//int todayList = wtDeviceInfoService.
				int todayInputNum = wtDeviceInfoService.queryInputDataCount(paramMap);
				//昨日入库
				paramMap.clear();
				paramMap.put("createTime",DateUtil.getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));
				int yestodayInputNum = wtDeviceInfoService.queryInputDataCount(paramMap);
				//累积入库
				paramMap.clear();
				int allInputNum = wtDeviceInfoService.queryDeviceNum(paramMap);
				//存货明细
				paramMap.clear();
				paramMap.put("no_isActive", 1);
				int storeList = wtDeviceInfoService.queryStroeCount(paramMap);
				
				jsonObject.put("todayInputNum",todayInputNum);
				jsonObject.put("yestodayInputNum", yestodayInputNum);
				jsonObject.put("allInputNum", allInputNum);
				jsonObject.put("storeNum", storeList);
				//outPrin
			}else if(userType != null && "department".equals(userType)){
				String worker = wtUserInfoService.queryWorkerByUserName(userName);
				//paramMap.put("operator",worker);
				paramMap.put("departName", userStatus);
				paramMap.put("createTime",DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE));
				int todayList = wtDeviceInfoService.queryDeviceNum(paramMap);
				paramMap.put("createTime", DateUtil.getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));
				int yestodayList = wtDeviceInfoService.queryDeviceNum(paramMap);
				paramMap.clear();
				//paramMap.put("operator",worker);
				paramMap.put("departName", userStatus);
				int alldata = wtDeviceInfoService.queryDeviceNum(paramMap);
				//存货明细
				paramMap.clear();
				paramMap.put("department", userStatus);
				paramMap.put("no_isActive", 1);
				int storeList = wtDeviceInfoService.queryStroeCount(paramMap);
				/*paramMap.clear();
				paramMap.put("departName", userStatus);
				paramMap.put("isActive", 0);
				int store = wtDeviceInfoService.queryDeviceNum(paramMap);*/
				
				jsonObject.put("todayInputNum",todayList);
				jsonObject.put("yestodayInputNum", yestodayList);
				jsonObject.put("allInputNum", alldata);
				jsonObject.put("storeNum", storeList);
			}else if(userType !=null && "worker".equals(userType)){
				//paramMap.put("operator",worker);
				paramMap.put("departName", userStatus);
				paramMap.put("createTime",DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL_SIMPLE));
				int todayList = wtDeviceInfoService.queryDeviceNum(paramMap);
				paramMap.put("createTime", DateUtil.getFormatPastDate(1, DateUtil.PATTERN_CLASSICAL_SIMPLE));
				int yestodayList = wtDeviceInfoService.queryDeviceNum(paramMap);
				paramMap.clear();
				//paramMap.put("operator",worker);
				paramMap.put("departName", userStatus);
				int alldata = wtDeviceInfoService.queryDeviceNum(paramMap);
				/*paramMap.clear();
				paramMap.put("no_isActive", 1);
				List<WtDeviceInfo> storeList = wtDeviceInfoService.queryStore(paramMap);*/
				paramMap.clear();
				paramMap.put("department", userStatus);
				paramMap.put("no_isActive", 1);
				int storeList = wtDeviceInfoService.queryStroeCount(paramMap);
				
				jsonObject.put("todayInputNum",todayList);
				jsonObject.put("yestodayInputNum", yestodayList);
				jsonObject.put("allInputNum", alldata);
				jsonObject.put("storeNum", storeList);
			}
	
		//存货明细
		//model.addAttribute("todayInputNum", todayInputNum);
		/*out.print(todayInputNum);
		out.print(yestodayInputNum);
		out.print(allInputNum);
		out.close();*/
		//model.addAttribute("yestodayInputNum", yestodayInputNum);
		//model.addAttribute("allInputNum", allInputNum);
		//model.addAttribute("yesterdayAcNum", yesterdayAcNum);
		return jsonObject.toString();
	}
	@RequestMapping(value="/querySn",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String querySn(@RequestParam String editTime,@RequestParam String devName){
		Map<String,Object> param = new HashMap<String,Object>();
		Map<String,Object> resultMap = new HashMap<>();
		/*Date datefomat= DateUtil.parse(editTime, DateUtil.PATTERN_CLASSICAL);
		editTime = DateUtil.format(datefomat, DateUtil.PATTERN_CLASSICAL);*/
		
		param.put("devName",devName);
		param.put("createTime",editTime);
		List<WtDeviceInfo> snList = wtDeviceInfoService.querySn(param);
		resultMap.put("data", snList);
		return JsonUtils.objectToJson(resultMap);
	}
	/**
	 * 
	 * @Title:           delectInput
	 * @Description:     TODO
	 * @param:           @param editTime
	 * @param:           @param devName
	 * @param:           @return   
	 * @return:          String   
	 * @throws
	 */
	@RequestMapping(value="/delectInput",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delectInput(@RequestParam String editTime,@RequestParam String devName){
		Map<String,Object> param = new HashMap<>();
		param.put("createTime",editTime);
		param.put("devName", devName);
		int result = wtDeviceInfoService.deleteStorageInfoAndInputDataInfo(param);
		if(result ==1){
			param.put("result",200);
			return JsonUtils.objectToJson(param);
		}
		param.put("result",300);
		return JsonUtils.objectToJson(param);
	}
	@RequestMapping(value="/queryCondition")
	@ResponseBody
	public String queryCondition(@RequestParam String beginDate,@RequestParam String endDate,@RequestParam String userType,@RequestParam String userName){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("begainDate",beginDate);
		paramMap.put("endDate",endDate);
		JSONObject json = new JSONObject();
		if(userType != null && "ADMIN".equals(userType) ){
			List<WtStorageInfo> storageList = wtStorageInfoService.queryStorageInfoByCondition(paramMap);
			JSONArray jsonArray = (JSONArray) JSONObject.toJSON(storageList);
			json.put("data", jsonArray);
		}else if(userType != null && "department".equals(userType)){
			String worker = wtUserInfoService.queryWorkerByUserName(userName);
			paramMap.put("operator",worker);
			List<WtStorageInfo> storageList = wtStorageInfoService.queryStorageInfoByCondition(paramMap);
			JSONArray jsonArray = (JSONArray) JSONObject.toJSON(storageList);
			json.put("data", jsonArray);
		}else if(userType != null && "worker".equals(userType)){
			paramMap.put("operator",userName);
			List<WtStorageInfo> storageList = wtStorageInfoService.queryStorageInfoByCondition(paramMap);
			JSONArray jsonArray = (JSONArray) JSONObject.toJSON(storageList);
			json.put("data", jsonArray);
		}
		//List<WtStorageInfo> 
		return json.toString();
	}
	/**
	 * 报表导入
	 */
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public String importExcel(@RequestParam(value="filename")MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		try {
			//判断是否为空
			if(file == null) return null;
			//获取文件名字
			String name = file.getOriginalFilename();
			//进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
			long size = file.getSize();
			if(name == null || ("".equals(name) && size == 0)) return null;
			//获取文件后缀名
			if(name.indexOf(".") == -1) return null;
			String suffix = name.substring(name.lastIndexOf(".")+1);
			if(!("xls".equals(suffix) || "xlsx".equals(suffix))){
				return null;
			}
			String path = request.getSession().getServletContext().getRealPath(ConstantClass.EXCEL_PATH);
			path = path + "/" + new Date().getTime() + "." + suffix;
			File excelFile = new File(path);
			file.transferTo(excelFile);
			String[] fieldNames = new String[]{"company", "department", "model", "sn"};
			WtDeviceInfo wdi = null;
			WtUserInfo wui = getPmsOperator();
			String	dateString = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL);
			Date date = DateUtil.parse(dateString);
			if("xls".equals(suffix)){
				ExcelHelper eh = JxlExcelHelper.getInstance(excelFile);
				List<WtDeviceInfo> list = eh.readExcel(WtDeviceInfo.class, fieldNames, true);
				if(list.size() < 1){
					return null;
				}
				wdi = list.get(0);
				String devName = wdi.getModel();
				for (int i = 0; i< list.size();i++) {
					list.get(i).setCreateTime(date);
				}
				wtDeviceService.insertDeviceIfNoExist(devName);
				wtDeviceInfoService.saveBatchDevice(list,wdi,wui.getUserName());
			}else if("xlsx".equals(suffix)){
				ExcelHelper eh = XssfExcelHelper.getInstance(excelFile);
				List<WtDeviceInfo> list = eh.readExcel(WtDeviceInfo.class, fieldNames, true);
				if(list.size() < 1){
					return null;
				}
				wdi = list.get(0);
				String devName = wdi.getModel();
				for (int i = 0; i< list.size();i++) {
					list.get(i).setCreateTime(date);
				}
				wtDeviceService.insertDeviceIfNoExist(devName);
				wtDeviceInfoService.saveBatchDevice(list,wdi,wui.getUserName());
			}else{
				//请导入后缀名为xls或xlsx的excel
				return null;
			}
		} catch (Exception e) {
			log.error("====DeviceStorageController-->importExcel error:"+e.getMessage());
		}
		return "redirect:/device/storage/list";
	}
	
}
