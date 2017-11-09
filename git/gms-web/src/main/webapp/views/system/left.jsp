<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/taglib/taglib.jsp" %>
<div class="page-sidebar-wrapper">
	<div class="page-sidebar navbar-collapse collapse">
		<!--左侧菜单开始-->
		<ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
			<shiro:hasPermission name="首页">
			<li class="nav-item active open">
				<a href="${baseURL }/index/index" class="nav-link nav-toggle">
					<i class="icon-home"></i>
					<span class="title">首页</span>
					<span class="selected"></span>
				</a>
			</li> 
			</shiro:hasPermission>
			<li class="heading ">
				<h3 class="uppercase">设备</h3>
			</li>

			<li class="nav-item">
				<a href="javascript:;" class="nav-link nav-toggle">
					<i class="icon-grid"></i>
					<span class="title">设备统计</span>
					<span class="arrow"></span>
				</a>
				<ul class="sub-menu">
				<shiro:hasPermission name="地区分布">
					<li class="nav-item ">
						<a href="${baseURL }/region/list" class="nav-link ">
							<i class="icon-map"></i>
							<span class="title">地区分布</span>
						</a>
					</li>
					</shiro:hasPermission>
				<shiro:hasPermission name="设备激活">
					<li class="nav-item  ">
						<a href="${baseURL }/device/activation/list" class="nav-link ">
							<i class="icon-cloud-upload"></i>
							<span class="title">设备激活</span>
						</a>
					</li>
				</shiro:hasPermission>
				<shiro:hasPermission name="设备活跃">
					<li class="nav-item  ">
						<a href="${baseURL }/device/active/list" class="nav-link ">
							<i class="icon-graph"></i>
							<span class="title">设备活跃</span>
						</a>
					</li>
				</shiro:hasPermission>
				<shiro:hasPermission name="设备入库">
					<li class="nav-item  ">
						<a href="${baseURL }/device/storage/list" class="nav-link ">
							<i class="icon-note"></i>
							<span class="title">设备入库</span>
						</a>
					</li>
					</shiro:hasPermission>
				<shiro:hasPermission name="报表统计">
					<li class="nav-item  ">
						<a href="${baseURL }/report/list" class="nav-link ">
							<i class="icon-notebook"></i>
							<span class="title">报表统计</span>
						</a>
					</li>
					</shiro:hasPermission>
					<shiro:hasPermission name="机型统计">
					<li class="nav-item  ">
						<a href="${baseURL }/statistics/list" class="nav-link">
							<i class="icon-screen-tablet"></i>
							<span class="title">机型统计</span>
						</a>
					</li>
					</shiro:hasPermission>
					<!-- 新增设备统计页面 -->
					<shiro:hasPermission name="设备统计">
					<li class="nav-item">
						<a href="${baseURL }/equipment/statistics/list" class="nav-link">
							<i class="icon-screen-smartphone"></i>
							<span class="title">设备统计</span>
						</a>
					</li>
					</shiro:hasPermission>
				</ul>
			</li>
			<li class="nav-item">
				<a href="" class="nav-link nav-toggle">
					<i class="icon-users"></i>
					<span class="title">用戶管理</span>
					<span class="arrow"></span>
				</a>
				<ul class="sub-menu">
				<shiro:hasPermission name="账号管理">
					<li class="nav-item">
						<a href="${baseURL }/accountManage/list" class="nav-link">
							<i class="icon-user"></i>
							<span class="title">账号管理</span>
						</a>
					</li>
				</shiro:hasPermission>
				<shiro:hasPermission name="日志监控">
					<li class="nav-item">
						<a href="${baseURL }/log/monitoring/list" class="nav-link">
							<i class="icon-doc"></i>
							<span class="title">日志监控</span>
						</a>
					</li>
				</shiro:hasPermission>
				<shiro:hasPermission name="后台版本">
					<li class="nav-item">
						<a href="${baseURL }/version/list" class="nav-link">
							<i class="icon-info"></i>
							<span class="title">后台版本</span>
						</a>
					</li>
					</shiro:hasPermission>
				<shiro:hasPermission name="账号分配">
					<li class="nav-item">
						<a href="${baseURL }/permission/assignment/list" class="nav-link">
							<i class="icon-user-follow"></i>
							<span class="title">账号分配</span>
						</a>
					</li>
				</shiro:hasPermission>
				</ul>
			</li>
		</ul>
		<!--左侧菜单结束-->
	</div>
</div>