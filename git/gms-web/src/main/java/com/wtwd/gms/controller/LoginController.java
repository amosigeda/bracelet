package com.wtwd.gms.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wtwd.gms.entity.WtAccountManage;
import com.wtwd.gms.entity.WtUserInfo;
import com.wtwd.gms.excel.DateUtil;
import com.wtwd.gms.exception.PermissionException;
import com.wtwd.gms.service.WtAccountManageService;

/**
 * 登录
 * @author liufeng
 * @date 2017-6-29
 */
@Controller
public class LoginController extends BaseController {

	private static final Log LOG = LogFactory.getLog(LoginController.class);
	@Autowired
	private WtAccountManageService wtAccountManageService;
//	@Autowired
//	private PmsOperatorRoleService pmsOperatorRoleService;
//	@Autowired
//	private PmsMenuService pmsMenuService;

	/**
	 * 函数功能说明 ： 进入后台登陆页面.
	 * 
	 * @参数： @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest req, Model model) {

		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误";
		} else if (PermissionException.class.getName().equals(exceptionClassName)) {
			error = "网络异常,请联系管理员";
		} else if (exceptionClassName != null) {
			error = "错误提示：" + exceptionClassName;
		}
		model.addAttribute("message", error);
		return "system/login";
	}

	/**
	 * 函数功能说明 ： 登陆后台管理系统. 修改者名字： 修改日期： 修改内容：
	 * 
	 * @参数： @param request
	 * @参数： @param model
	 * @参数： @return
	 * @return String
	 * @throws PermissionException
	 */
	@RequestMapping("/")
	public String index(HttpServletRequest req, Model model) {
		WtUserInfo wtUserInfo = (WtUserInfo) this.getSession().getAttribute("WtUserInfo");
		System.out.println("WtUserInfo:"+wtUserInfo);
		try {
//			String tree = this.buildOperatorPermissionMenu(pmsOperator);
//			model.addAttribute("tree", tree);
			
			/*WtAccountManage wtAccountManage = new WtAccountManage();
			wtAccountManage.setUserName(wtUserInfo.getUserName());
			wtAccountManage.setRole(wtUserInfo.getUserType());
			wtAccountManage.setAssignmentDate(wtUserInfo.getCreateTime());
			String	date = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL);
			wtAccountManage.setLoginDate(DateUtil.parse(date));
			wtAccountManageService.insertWtAccountManage(wtAccountManage);*/
			//session.setAttribute("WtAccounManage", wtAccountManage1);
		} catch (Exception e) {
			LOG.error("登录异常:" + e.getMessage());
			model.addAttribute("message", e.getMessage());
			return "system/login";
		}
		return "redirect:index/index";//system/index
	}
	
	@RequestMapping("/logout")
	public String loginOut(HttpServletRequest request,Model model){
		 Subject currentUser = SecurityUtils.getSubject();  
	     currentUser.logout();  
		return "redirect:system/loginout";
	}
	
}
