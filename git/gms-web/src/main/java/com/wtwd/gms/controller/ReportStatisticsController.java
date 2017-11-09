package com.wtwd.gms.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtwd.gms.entity.WtActivationInfo;
import com.wtwd.gms.entity.WtBaseCount;
import com.wtwd.gms.entity.WtDeviceInfo;
import com.wtwd.gms.entity.WtReportInfo;
import com.wtwd.gms.excel.DateUtil;
import com.wtwd.gms.excel.ExcelHelper;
import com.wtwd.gms.excel.XssfExcelHelper;
import com.wtwd.gms.service.WtActivationInfoService;
import com.wtwd.gms.service.WtDeviceInfoService;
import com.wtwd.gms.service.WtReportInfoService;
import com.wtwd.gms.utils.ConstantClass;
import com.wtwd.gms.utils.JsonUtils;


	/**
	 * 报表统计
	 * @author huangzhe
	 * @date 2017-8-17
	 */
	@Controller
	@RequestMapping("/report")
	public class ReportStatisticsController extends BaseController {

		@Autowired
		private WtReportInfoService wtReportInfoService;
		@Autowired
		private WtActivationInfoService wtActivationInfoService;
		@Autowired
		private WtDeviceInfoService wtDeviceInfoService;
		/**
		 * 数据展示
		 * @return
		 */
		@RequestMapping("/list")
		public String list(){
			return "device/report_statistics";
		}
		/**
		 * 获取数据
		 */
	    //@RequestMapping("/queryDataInfo")
	    public void queryDataInfo(HttpServletResponse response){
	    	/*try {
				PrintWriter out = response.getWriter();
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=UTF-8");
				JSONObject json = new JSONObject();
				List<WtReportInfo> list = wtReportInfoService.queryDataInfo();
				JSONArray jsonArray = (JSONArray) JSONObject.toJSON(list);
				json.put("data", jsonArray);
				System.out.println("json:"+json);
				out.print(json);
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}*/
	    }
	    
	    @RequestMapping("/searchReportInfo")
	    @ResponseBody
	    public String reportTable(HttpServletRequest request,@RequestParam String departName,@RequestParam String devName,@RequestParam String country,@RequestParam String beginDate,@RequestParam String endDate,@RequestParam String userType,@RequestParam String userStatus){
	    	Map<String,Object> paramMap = new HashMap<>();
	    	Map<String,Object> resultMap = new HashMap<>();
	    	if(userType != null && !"".equals(userType)&& "ADMIN".equals(userType)){
	    		//事业部条件
	    		WtReportInfo wtReportInfo = new WtReportInfo();
	    		if(departName != null && !"".equals(departName) ){
	    			wtReportInfo.setDepartname(departName);
	    			List<String> devNameList = queryDepartDevNameList(departName);
	    			if (devNameList != null &&devNameList.size() >0 ) {
						paramMap.put("devList", devNameList);
					}
	    			//机型条件
	    			if(devName != null && !"".equals(devName)){
	    				if (devNameList.contains(devName)) {
							paramMap.clear();
							paramMap.put("devName", devName);
							wtReportInfo.setDevname(devName);
						}else{
							resultMap.put("data","");
							return JsonUtils.objectToJson(resultMap);
						}
	    			}
	    			//国家条件
	    			if(country != null && !"".equals(country)){
	    				paramMap.put("country", country);
	    				wtReportInfo.setCountry(country);
	    			}
	    			//起始时间
	    			if(beginDate != null && !"".equals(beginDate) && endDate != null && !"".equals(endDate) ){
	    				paramMap.put("beginDate", beginDate);
	    				paramMap.put("endDate", endDate);
	    				wtReportInfo.setBegindate(DateUtil.parse(beginDate, DateUtil.PATTERN_CLASSICAL_SIMPLE));
	    				wtReportInfo.setEnddate(DateUtil.parse(endDate, DateUtil.PATTERN_CLASSICAL_SIMPLE));
	    			}
	    			//查询sn并生成文件，插入report表
	    		}else if(devName != null &&!"".equals(devName) ){
	    			paramMap.put("devName", devName);
	    			wtReportInfo.setDevname(devName);
	    			if(country != null && !"".equals(country)){
	    				paramMap.put("country", country);
	    				wtReportInfo.setCountry(country);
	    			}
	    			//起始时间
	    			if(beginDate != null && !"".equals(beginDate) && endDate != null && !"".equals(endDate) ){
	    				paramMap.put("beginDate", beginDate);
	    				paramMap.put("endDate", endDate);
	    				wtReportInfo.setBegindate(DateUtil.parse(beginDate, DateUtil.PATTERN_CLASSICAL_SIMPLE));
	    				wtReportInfo.setEnddate(DateUtil.parse(endDate, DateUtil.PATTERN_CLASSICAL_SIMPLE));
	    			}
	    			
	    			//查询sn并生成文件，插入report表
	    		}else if(country !=null && !"".equals(country) ){
	    			paramMap.put("country",country);
	    			wtReportInfo.setCountry(country);
	    			//起始时间
	    			if(beginDate != null && !"".equals(beginDate) && endDate != null && !"".equals(endDate) ){
	    				paramMap.put("beginDate", beginDate);
	    				paramMap.put("endDate", endDate);
	    				wtReportInfo.setBegindate(DateUtil.parse(beginDate, DateUtil.PATTERN_CLASSICAL_SIMPLE));
	    				wtReportInfo.setEnddate(DateUtil.parse(endDate, DateUtil.PATTERN_CLASSICAL_SIMPLE));
	    			}
	    			//查询sn并生成文件，插入report表
	    		}else{
	    			//起始时间
	    			if(beginDate != null && !"".equals(beginDate) && endDate != null && !"".equals(endDate) ){
	    				paramMap.put("beginDate", beginDate);
	    				paramMap.put("endDate", endDate);
	    				wtReportInfo.setBegindate(DateUtil.parse(beginDate, DateUtil.PATTERN_CLASSICAL_SIMPLE));
	    				wtReportInfo.setEnddate(DateUtil.parse(endDate, DateUtil.PATTERN_CLASSICAL_SIMPLE));
	    			}
	    		}
	    		
	    		
	    		//查询sn并生成文件，插入report表
    			List<WtActivationInfo> snList = wtActivationInfoService.queryReportSN(paramMap);
    			String path = request.getSession().getServletContext().getRealPath(ConstantClass.OUTPUT_PATH);
    			String date = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL);
    			System.out.println(date);
    			String date2 = new Date().getTime()+"";
    			path = path + "/" + date2 + ".xlsx";
    			File file3 = new File(path);
    			System.out.println(path);
    			ExcelHelper eh3 = XssfExcelHelper.getInstance(file3);
    			try {
    				String[] titles = new String[]{"sn号"};
    				String[] fieldNames = new String[]{"sn"};
					eh3.writeExcel(WtActivationInfo.class, snList,fieldNames, titles);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			if(snList.size() <= 0 || snList == null){
    				wtReportInfo.setStatus("失败");
    				wtReportInfo.setSnSum(0L);
    			}else{
    				wtReportInfo.setStatus("成功");
    				wtReportInfo.setSnSum(Long.valueOf(snList.size()));
    			}
    			wtReportInfo.setCreateTime(DateUtil.parse(date, DateUtil.PATTERN_CLASSICAL));
    			wtReportInfo.setReportBelong("admin");
    			wtReportInfo.setFilename(date2 + ".xlsx");
    			wtReportInfoService.insertWtReportInfo(wtReportInfo);
    			paramMap.clear();
    			//查询表中的数据
    			List<WtReportInfo> reportList= wtReportInfoService.queryReportInfo(paramMap);
	    		resultMap.put("data", reportList);
	    		return JsonUtils.objectToJson(resultMap);

	    	}else if(userType != null && !"".equals(userType)&&("department".equals(userType) || "worker".equals(userType))){
	    		WtReportInfo wtReportInfo = new WtReportInfo();
	    		wtReportInfo.setReportBelong(userStatus);
    			List<String> devNameList = queryDepartDevNameList(userStatus);
	    		
	    		//机型条件
    			if(devName != null && !"".equals(devName)){
    				if (devNameList.contains(devName)) {
						paramMap.clear();
						paramMap.put("devName", devName);
						wtReportInfo.setDevname(devName);
					}else{
						resultMap.put("data","");
						return JsonUtils.objectToJson(resultMap);
					}
    				
    				
    				if(country != null && !"".equals(country)){
    					paramMap.put("country", country);
    					wtReportInfo.setCountry(country);
    				}
    				//起始时间
	    			if(beginDate != null && !"".equals(beginDate) && endDate != null && !"".equals(endDate) ){
	    				paramMap.put("beginDate", beginDate);
	    				paramMap.put("endDate", endDate);
	    				wtReportInfo.setBegindate(DateUtil.parse(beginDate, DateUtil.PATTERN_CLASSICAL_SIMPLE));
	    				wtReportInfo.setEnddate(DateUtil.parse(endDate, DateUtil.PATTERN_CLASSICAL_SIMPLE));
	    			}
    			}else if(country != null && !"".equals(country)){
    				paramMap.put("country",country);
	    			wtReportInfo.setCountry(country);
	    			//起始时间
	    			if(beginDate != null && !"".equals(beginDate) && endDate != null && !"".equals(endDate) ){
	    				paramMap.put("beginDate", beginDate);
	    				paramMap.put("endDate", endDate);
	    				wtReportInfo.setBegindate(DateUtil.parse(beginDate, DateUtil.PATTERN_CLASSICAL_SIMPLE));
	    				wtReportInfo.setEnddate(DateUtil.parse(endDate, DateUtil.PATTERN_CLASSICAL_SIMPLE));
	    			}
    			}else{
    				//起始时间
	    			if(beginDate != null && !"".equals(beginDate) && endDate != null && !"".equals(endDate) ){
	    				paramMap.put("beginDate", beginDate);
	    				paramMap.put("endDate", endDate);
	    				wtReportInfo.setBegindate(DateUtil.parse(beginDate, DateUtil.PATTERN_CLASSICAL_SIMPLE));
	    				wtReportInfo.setEnddate(DateUtil.parse(endDate, DateUtil.PATTERN_CLASSICAL_SIMPLE));
	    			}
    			}
	    		
	    		
	    		
    			List<WtActivationInfo> snList = wtActivationInfoService.queryReportSN(paramMap);
    			String path = request.getSession().getServletContext().getRealPath(ConstantClass.OUTPUT_PATH);
    			String date = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL);
    			System.out.println(date);
    			String date2 = new Date().getTime()+"";
    			path = path + "/" + date2 + ".xlsx";
    			File file3 = new File(path);
    			System.out.println(path);
    			ExcelHelper eh3 = XssfExcelHelper.getInstance(file3);
    			try {
    				String[] titles = new String[]{"sn号"};
    				String[] fieldNames = new String[]{"sn"};
					eh3.writeExcel(WtActivationInfo.class, snList,fieldNames, titles);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			if(snList.size() <= 0 || snList == null){
    				wtReportInfo.setStatus("失败");
    				wtReportInfo.setSnSum(0L);
    			}else{
    				wtReportInfo.setStatus("成功");
    				wtReportInfo.setSnSum(Long.valueOf(snList.size()));
    			}
    			wtReportInfo.setCreateTime(DateUtil.parse(date, DateUtil.PATTERN_CLASSICAL));
    			//wtReportInfo.setReportBelong("admin");
    			wtReportInfo.setFilename(date2 + ".xlsx");
    			wtReportInfoService.insertWtReportInfo(wtReportInfo);
    			paramMap.clear();
    			paramMap.put("report_belong", userStatus);
    			//查询表中的数据
    			List<WtReportInfo> reportList= wtReportInfoService.queryReportInfo(paramMap);
	    		resultMap.put("data", reportList);
	    		return JsonUtils.objectToJson(resultMap);
	    	}
	    	return null;
	    }
	    
	    @RequestMapping("/queryReport")
	    @ResponseBody
	    public String queryTable(@RequestParam String userType,@RequestParam String userStatus){
	    	Map<String,Object> paramMap = new HashMap<>();
	    	Map<String,Object> resultMap = new HashMap<>();
	    	if(userType != null && !"".equals(userType)&& "ADMIN".equals(userType)){
	    		paramMap.clear();
    			//查询表中的数据
	    		paramMap.put("report_belong","admin");
    			List<WtReportInfo> reportList= wtReportInfoService.queryReportInfo(paramMap);
	    		resultMap.put("data", reportList);
	    	}else if(userType != null && !"".equals(userType)&&("department".equals(userType) || "worker".equals(userType))){
	    		paramMap.clear();
	    		paramMap.put("report_belong",userStatus);
    			//查询表中的数据
    			List<WtReportInfo> reportList= wtReportInfoService.queryReportInfo(paramMap);
	    		resultMap.put("data", reportList);
	    	}
	    	
	    	return JsonUtils.objectToJson(resultMap);
	    }
	    /**
	     * 
	     * @Title:           download
	     * @Description:     TODO
	     * @param:           @param request
	     * @param:           @param filename
	     * @param:           @return
	     * @param:           @throws IOException   
	     * @return:          ResponseEntity<byte[]>   
	     * @throws
	     */
	    @RequestMapping("/download")
	    public ResponseEntity<byte[]> download(HttpServletRequest request,  
	            @RequestParam("filename") String filename) throws IOException { 
	    	String path = request.getSession().getServletContext().getRealPath(ConstantClass.OUTPUT_PATH)+"\\" + filename;
	    	System.out.println(path);
	        File file = new File(path);  
	        HttpHeaders headers = new HttpHeaders();  
	        String fileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题  
	        headers.setContentDispositionFormData("attachment", fileName);  
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),  
	               headers, HttpStatus.CREATED);  
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
			List<WtBaseCount> devList3 =new ArrayList<>();
			//System.out.println(userStatus+"=======================================================================================");
			if(userStatus != null && (!"".equals(userStatus)) && "admin".equals(userStatus)){
				//查询所有国家
				paramMap.put("country", userStatus);
				List<WtBaseCount> devList = wtActivationInfoService.distinctData(paramMap);
				return JsonUtils.objectToJson(devList);
			}else if(userStatus != null && !"".equals(userStatus)){
				System.out.println(userStatus+"=userStatus==============================");
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
		@RequestMapping("/queryCondition")
		@ResponseBody
		public String queryCondition(@RequestParam String filename){
			Map<String, Object> paramMap = new HashMap<>();
			Map<String, Object> resultMap  = new HashMap<>();
			paramMap.put("filename", filename);
			List<WtReportInfo> reportList= wtReportInfoService.queryReportInfo(paramMap);
			resultMap.put("data",reportList);
			return JsonUtils.objectToJson(resultMap);
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
		 * 获取事业部下所有的设备
		 * @Title:           queryDepartDevList
		 * @Description:     TODO
		 * @param:           @param departName
		 * @param:           @return   
		 * @return:          List<WtDeviceInfo>   
		 * @throws
		 */
		public List<String> queryDepartDevNameList(String departName){
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
	}

