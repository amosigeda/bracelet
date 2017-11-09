<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="page-header navbar navbar-fixed-top">
	<div class="page-logo">
		<a href="index.html">
			<img src="${baseURL }/common/images/logo.png" alt="logo" class="logo-default"> </a>
		<div class="menu-toggler sidebar-toggler"> </div>
	</div>
	<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
	<!-- 网页头部右侧开始-->
	<div class="top-menu">
		<ul class="nav navbar-nav pull-right">
			<li class="dropdown dropdown-user">
				<a href="javascript:;" class="dropdown-toggle">
					<img alt="" class="img-circle" src="${baseURL }/common/images/head.png" />
					<span class="username username-hide-on-mobile"> ${sessionScope.WtUserInfo.userName}</span>
					<i></i>
				</a>
			</li>
			<li class="dropdown dropdown-user">
				<a href="${baseURL }/modify/password/list" class="dropdown-toggle">
					<span class="username username-hide-on-mobile" style="color:#79869a;">修改密码<!--<a href="modify_password.html">修改密码</a>--></span>
					<i></i>
				</a>
			</li>
			<li class="dropdown dropdown-quick-sidebar-toggler">
				<a href="${baseURL }/logout" class="dropdown-toggle">
					<i class="icon-logout"></i>
				</a>
			</li>

		</ul>
	</div>
	<!--网页头部右侧结束-->
</div>