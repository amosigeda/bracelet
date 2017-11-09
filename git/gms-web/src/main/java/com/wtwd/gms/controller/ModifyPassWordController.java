package com.wtwd.gms.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wtwd.gms.entity.WtUserInfo;
import com.wtwd.gms.service.WtUserInfoService;
import com.wtwd.gms.utils.PasswordHelper;

@Controller
@RequestMapping("/modify/password")
public class ModifyPassWordController {

	@Autowired
	private WtUserInfoService wtUserInfoService;
	@RequestMapping("/list")
	public String list(){
		
		return "user/modify_password";
	}
	
	@RequestMapping("/modify")
	public String modifyPassWord(@RequestParam String password){
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		
		WtUserInfo userInfo = (WtUserInfo) session.getAttribute("WtUserInfo");
		WtUserInfo modifyUserInfo = new WtUserInfo();
		modifyUserInfo.setUserName(userInfo.getUserName());
		modifyUserInfo.setUserPwd(password);
		session.removeAttribute("WtUserInfo");
		PasswordHelper.encryptPassword(modifyUserInfo);
		//更新数据库
		//subject.logout();
		int result = wtUserInfoService.modifyPassword(modifyUserInfo);
		System.out.println(result);
		if(result > 0){
			 Subject currentUser = SecurityUtils.getSubject();  
			 
			 currentUser.logout();  
			
			System.out.println("login");
			return "redirect:system/loginout";
		}else{
			return "user/modify_password";
		}  
		//return "redirect:/";
	}
}
