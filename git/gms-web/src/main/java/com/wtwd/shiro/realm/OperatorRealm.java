package com.wtwd.shiro.realm;

import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.wtwd.gms.common.core.PublicStatusEnum;
import com.wtwd.gms.entity.WtAccountManage;
import com.wtwd.gms.entity.WtUserInfo;
import com.wtwd.gms.excel.DateUtil;
import com.wtwd.gms.service.WtAccountManageService;
import com.wtwd.gms.service.WtPermissionService;
import com.wtwd.gms.service.WtRolePermissionService;
import com.wtwd.gms.service.WtUserInfoService;
import com.wtwd.gms.service.WtUserRoleService;

/**
 * 自定义realm .
 * 
 * @author: liufeng
 * @date:2017年6月6日
 */
public class OperatorRealm extends AuthorizingRealm {

	private static final Log log = LogFactory.getLog(OperatorRealm.class);
	@Autowired
	private WtUserInfoService wtUserInfoService;
	@Autowired
	private WtUserRoleService wtUserRoleService;
	@Autowired
	private WtRolePermissionService wtRolePermissionService;
	@Autowired
	private WtPermissionService wtPermissionService;
	@Autowired
	private WtAccountManageService wtAccountManageService;
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String loginName = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		
		WtUserInfo userInfo = (WtUserInfo) session.getAttribute("WtUserInfo");
		if (userInfo == null) {
			userInfo = wtUserInfoService.findOperatorByLoginName(loginName);
			session.setAttribute("WtUserInfo", userInfo);
		}
		// 根据登录名查询操作员
		Long operatorId = userInfo.getId();
		System.out.println("opt"+operatorId);
		WtAccountManage wtAccountManage1 = (WtAccountManage)session.getAttribute("WtAccounManage");
		if(wtAccountManage1 == null ){
			WtAccountManage wtAccountManage = new WtAccountManage();
			wtAccountManage.setUserName(userInfo.getUserName());
			wtAccountManage.setRole(userInfo.getUserType());
			wtAccountManage.setAssignmentDate(userInfo.getCreateTime());
			String	date = DateUtil.format(new Date(), DateUtil.PATTERN_CLASSICAL);
			wtAccountManage.setLoginDate(DateUtil.parse(date));
			int result  = wtAccountManageService.insertWtAccountManage(wtAccountManage);
			log.error(result+"");
			session.setAttribute("WtAccounManage", wtAccountManage);
		}
			
		Set<String> roles = (Set<String>) session.getAttribute("ROLES");
		
		if (roles == null || roles.isEmpty()) {
			roles = wtUserRoleService.listByOperatorIdRoleName(operatorId);
			session.setAttribute("ROLES", roles);
		}
		// 查询角色信息
		authorizationInfo.setRoles(roles);

		Set<String> permisstions = (Set<String>) session.getAttribute("PERMISSIONS");
		if (permisstions == null || permisstions.isEmpty()) {
			permisstions = wtPermissionService.listByOperatorIdPermissionName(operatorId);
			session.setAttribute("PERMISSIONS", permisstions);
		}
		// 根据用户名查询权限
		authorizationInfo.setStringPermissions(permisstions);
		return authorizationInfo;
	}

	@Override
	// 验证的核心方法
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String loginName = (String) token.getPrincipal();
		if (StringUtils.isEmpty(loginName.trim())) {
			throw new UnknownAccountException();// 没找到帐号
		}

		// 根据登录名查询操作员
		WtUserInfo userInfo = wtUserInfoService.findOperatorByLoginName(loginName);

		if (userInfo == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		if (PublicStatusEnum.UNACTIVE.equals(userInfo.getUserStatus())) {
			throw new LockedAccountException(); // 帐号锁定
		}

		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userInfo.getUserName(), // 登录名
				userInfo.getUserPwd(), // 密码
				ByteSource.Util.bytes(userInfo.getCredentialsSalt()), // salt=username+salt
				getName() // realm name
		);

		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
