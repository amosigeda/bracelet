package com.wtwd.gms.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wtwd.gms.websocket.LogView;

/**
 * 日志监控
 * @author wtj
 * @data 2017年7月3日
 */
@Controller
@RequestMapping("/log/monitoring")
public class LogMonitoringController extends BaseController{

	@RequestMapping("list")
	public String list(HttpServletResponse response){
		
		return "user/log_monitoring";
	}
	
	@RequestMapping("/log_monitoring")
	public void logMonitoring(HttpServletResponse response){
		/*LogView view = new LogView();     
        final File tmpLogFile = new File("logs/ssm-shiro.log");
       
        try {
        	 PrintWriter out = response.getWriter(); 
			view.realtimeShowLog(tmpLogFile,out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
		/*response.setContentType("text/html; charset=UTF-8");  
		PrintWriter out;
		try {
			out = response.getWriter();
			while(true){  
		        try {  
		            out.println("<script>");  
		            out.println("document.write('<div>输出内容</div>');");  
		            out.println("</script>");  
		            out.flush();  
		            Thread.sleep(10000);  
		        } catch (Exception e) {  
		        e.printStackTrace();  
		        }  
		}  
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  */
		
	}
}
