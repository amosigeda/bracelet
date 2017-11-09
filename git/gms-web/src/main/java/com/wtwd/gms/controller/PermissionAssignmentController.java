package com.wtwd.gms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wtwd.gms.entity.WtRoleInfo;
import com.wtwd.gms.entity.WtRolePermission;
import com.wtwd.gms.entity.WtUserInfo;
import com.wtwd.gms.entity.WtUserRole;
import com.wtwd.gms.excel.DateUtil;
import com.wtwd.gms.service.WtRoleInfoService;
import com.wtwd.gms.service.WtRolePermissionService;
import com.wtwd.gms.service.WtUserInfoService;
import com.wtwd.gms.service.WtUserRoleService;
import com.wtwd.gms.utils.JsonUtils;
import com.wtwd.gms.utils.PasswordHelper;


@Controller
@RequestMapping("/permission/assignment")
public class PermissionAssignmentController {
	@Autowired
	public WtUserInfoService wtUserInfoService;
	@Autowired
	public WtUserRoleService wtUserRoleService;
	@Autowired
	public WtRoleInfoService wtRoleInfoService;
	@Autowired
	public WtRolePermissionService wtRolePermissionService;
	@RequestMapping("list")
	public String list(){
		
		return "user/permission_assignment";
	}
	
	
	@RequestMapping("/account")
	@ResponseBody
	public String queryAllAcount(){
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<WtUserInfo> userList = wtUserInfoService.findAllUser();
		
		if(userList != null && userList.size() > 0){
			result.put("data", userList);
		}
		return JsonUtils.objectToJson(result);
	}
	
	@RequestMapping("/adduser")
	@ResponseBody
	public String addUser(@RequestParam String username,@RequestParam String password,@RequestParam String role,@RequestParam String department,@RequestParam String[] boxdata){
		System.out.println(username +password );
		Map<String,Object> result = new HashMap<>();
		WtUserInfo wtuser = new WtUserInfo();
		WtUserRole wtUserRole = new WtUserRole();
		/*wtuser.setId(1L);
		wtuser.setUserName("fadsf");
		wtuser.setUserPwd("123");
		wtuser.setUserStatus("admin");
		wtuser.setUserType("fasdfa");
		PasswordHelper.encryptPassword(wtuser);
		System.out.println(wtuser.getUserPwd());*/
		result.put("result", 200);
		WtUserInfo user = wtUserInfoService.findOperatorByLoginName(username);
		if(user != null){
			return JsonUtils.objectToJson(result.put("result", 300));
		}
		
		if(role.equals("ADMIN")){
			wtuser.setUserName(username);
			wtuser.setUserPwd(password);
			wtuser.setUserStatus(department);
			wtuser.setUserType("ADMIN");
			String	date = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL);
			wtuser.setEditTime(DateUtil.parse(date));
			wtuser.setCreateTime(DateUtil.parse(date));
			
			System.out.println(date);
			System.out.println(DateUtil.parse(date));
			wtuser.setRealName(username);
			PasswordHelper.encryptPassword(wtuser);
			wtUserInfoService.insertUserInfo(wtuser);
			
			wtUserRole.setRoleId(1L);
			WtUserInfo user1 = wtUserInfoService.findOperatorByLoginName(username);
			wtUserRole.setUserId(user1.getId());
			wtUserRoleService.insertUserRoleInfo(wtUserRole);
		
			
		}else if(role.equals("department")){
			
			wtuser.setUserName(username);
			wtuser.setUserPwd(password);
			wtuser.setUserStatus(department);
			wtuser.setUserType("department");
			String	date = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL);
			wtuser.setEditTime(DateUtil.parse(date));
			wtuser.setCreateTime(DateUtil.parse(date));
			
			System.out.println(date);
			System.out.println(DateUtil.parse(date));
			wtuser.setRealName(username);
			PasswordHelper.encryptPassword(wtuser);
			wtUserInfoService.insertUserInfo(wtuser);
			
			wtUserRole.setRoleId(2L);
			WtUserInfo user1 = wtUserInfoService.findOperatorByLoginName(username);
			wtUserRole.setUserId(user1.getId());
			wtUserRoleService.insertUserRoleInfo(wtUserRole);
		}else if(role.equals("worker")){
			WtRoleInfo wtRoleInfo = new WtRoleInfo();
			String	date = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL);
			wtRoleInfo.setRoleName(department+":worker"+date);
			wtRoleInfoService.insertRoleInfo(wtRoleInfo);
			
			wtuser.setUserName(username);
			wtuser.setUserPwd(password);
			wtuser.setUserStatus(department);
			wtuser.setUserType("worker");
			//String	date = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL);
			wtuser.setEditTime(DateUtil.parse(date));
			wtuser.setCreateTime(DateUtil.parse(date));
			
			System.out.println(date);
			System.out.println(DateUtil.parse(date));
			wtuser.setRealName(username);
			PasswordHelper.encryptPassword(wtuser);
			wtUserInfoService.insertUserInfo(wtuser);
			//查询userid,roleid
			WtUserInfo wtUserInfo = wtUserInfoService.findOperatorByLoginName(username);
			wtUserRole.setUserId(wtUserInfo.getId());
			WtRoleInfo wtRoleInfo1 = wtRoleInfoService.findRoleInfoByRoleName(department+":worker"+date);
			wtUserRole.setRoleId(wtRoleInfo1.getId());
			
			//插入userrole
			wtUserRoleService.insertUserRoleInfo(wtUserRole);
			//插入role permission
			for (String boxid : boxdata) {
				System.out.println(boxid);
				WtRolePermission wtRolePermission = new WtRolePermission();
				wtRolePermission.setRoleId(wtRoleInfo1.getId());
				wtRolePermission.setPermissionId(Long.valueOf(boxid));
				wtRolePermissionService.insertRolePermissionInfo(wtRolePermission);
			}
		}
		return JsonUtils.objectToJson(result);
	}
	
	
	@RequestMapping(value="/delUser")
	@ResponseBody
	public String delUser(@RequestParam String userType,@RequestParam String userName){
		WtUserInfo wtUserInfo = wtUserInfoService.findOperatorByLoginName(userName);
		Map<String,Object> resultData = new HashMap<>();
		resultData.put("result", 200);
		if("worker".equals(userType)){
			//删除user_info表中的数据
			wtUserInfoService.deleteUserInfo(wtUserInfo.getId());
			
			//查找所有的role角色
			Set<Long> userRolesId = wtUserRoleService.findRoleIdByOperatorId(wtUserInfo.getId());
			for (Long roleId : userRolesId) {
				wtRoleInfoService.delRoleInfo(roleId);
				wtRolePermissionService.delRolePermissionInfo(roleId);
			}
			wtUserRoleService.delUserRoleInfo(wtUserInfo.getId());
		}else if("ADMIN".equals(userType)||"department".equals(userType)){
			 wtUserInfoService.deleteUserInfo(wtUserInfo.getId());
			wtUserRoleService.delUserRoleInfo(wtUserInfo.getId());
			
		}
		return JsonUtils.objectToJson(resultData);
	}
	
	
}
