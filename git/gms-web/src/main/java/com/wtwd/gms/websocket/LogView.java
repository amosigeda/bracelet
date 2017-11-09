package com.wtwd.gms.websocket;
import java.io.File;     
import java.io.IOException;     
import java.io.PrintWriter;
import java.io.RandomAccessFile;     
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;     
import java.util.concurrent.ScheduledExecutorService;     
import java.util.concurrent.TimeUnit;     

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.wtwd.gms.utils.JsonUtils;
    
public class LogView {     
    private long lastTimeFileSize = 0;  //上次文件大小     
    /**   
     * 实时输出日志信息   
     * @param logFile 日志文件   
     * @throws IOException   
     */    
    public void realtimeShowLog(File logFile,final PrintWriter out) throws IOException{     
        //指定文件可读可写     
        final RandomAccessFile randomFile = new RandomAccessFile(logFile,"rw");     
        //启动一个线程每10秒钟读取新增的日志信息     
        ScheduledExecutorService exec =      
            Executors.newScheduledThreadPool(1); 
        
        exec.scheduleWithFixedDelay(new Runnable(){     
            public void run() {     
                try {     
                    //获得变化部分的     
                    randomFile.seek(lastTimeFileSize);     
                    String tmp = "";  
                    String result = "";
                    while( (tmp = randomFile.readLine())!= null) {     
                        System.out.println(result);
                        result = new String(tmp.getBytes("ISO8859-1"));
                    }     
                   // PrintWriter out = response.getWriter();
        			/*response.setCharacterEncoding("UTF-8");
        			response.setContentType("text/html;charset=UTF-8");*/
                    Map<String,Object> resultMap = new HashMap<String, Object>();
                    resultMap.put("data",result);
        			//JSONObject json = new JSONObject();
        			//List<WtStorageInfo> list = wtStorageInfoService.queryDataInfo();
        			//JSONArray jsonArray = (JSONArray) JSONObject.toJSON(list);
        			//json.put("data", result);
        			//System.out.println("json:"+json);
        			/*out.print(json);
        			out.close();*/
                    lastTimeFileSize = randomFile.length(); 
//                    System.out.println(JsonUtils.objectToJson(resultMap));
                    out.write(JsonUtils.objectToJson(resultMap));
//                    out.flush();
                   /* out.write("<script>");  
                    out.write("document.write('<div>fasfa</div>');");  
                    out.write("</script>");  */
                    out.flush();  
                    
                } catch (IOException e) {     
                    throw new RuntimeException(e);     
                }   
                
            }     
        }, 0, 1, TimeUnit.SECONDS);     
    }     
         
    public static void main(String[] args) throws Exception {     
       /* LogView view = new LogView();     
        final File tmpLogFile = new File("logs/ssm-shiro.log");     
        view.realtimeShowLog(tmpLogFile); */    
    }     
    
}    