<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@include file="../../common/taglib/taglib.jsp" %>
<head>
<title>设备激活</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=all" rel="stylesheet" type="text/css">
<link href="${baseURL}/common/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL}/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL}/common/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL}/common/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css">

<script src="${baseURL}/common/assets/global/plugins/bootstrap-select/css/bootstrap-select.css"></script> 
<link href="${baseURL}/common/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css">
<link href="${baseURL}/common/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL}/common/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL}/common/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color">
<link href="${baseURL}/common/assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css">
<style type="text/css">
	.jqstooltip {
		position: absolute;
		left: 0px;
		top: 0px;
		visibility: hidden;
		background: rgb(0, 0, 0) transparent;
		background-color: rgba(0, 0, 0, 0.6);
		filter: progid: DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000);
		-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";
		color: white;
		font: 10px arial, san serif;
		text-align: left;
		white-space: nowrap;
		padding: 5px;
		border: 1px solid white;
		z-index: 10000;
		}
			
	.jqsfield {
		color: white;
		font: 10px arial, san serif;
		text-align: left;
	}
</style>

</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
		<!--网页头部开始-->
		<%@include file="../system/top.jsp" %>
		<!--网页头部结束-->
		<div class="clearfix"> </div>
		<!--内容开始-->
		<div class="page-container">
			<div class="page-sidebar-wrapper">
				<div class="page-sidebar navbar-collapse collapse">
					<!--左侧菜单开始-->
					<ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
						<shiro:hasPermission name="首页">
						<li class="nav-item">
							<a href="${baseURL }/index/index" class="nav-link nav-toggle">
								<i class="icon-home"></i>
								<span class="title">首页</span>
								
							</a>
						</li> 
						</shiro:hasPermission>
						<li class="heading ">
							<h3 class="uppercase">设备</h3>
						</li>

						<li class="nav-item active open">
							<a href="javascript:;" class="nav-link nav-toggle">
								<i class="icon-grid"></i>
								<span class="title">设备统计</span>
								<span class="arrow"></span>
								<span class="selected"></span>
							</a>
							<ul class="sub-menu">
							<shiro:hasPermission name="地区分布">
								<li class="nav-item">
									<a href="${baseURL }/region/list" class="nav-link ">
										<i class="icon-map"></i>
										<span class="title">地区分布</span>
									</a>
								</li>
								</shiro:hasPermission>
							<shiro:hasPermission name="设备激活">
								<li class="nav-item   active">
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
							<!-- <shiro:hasPermission name="日志监控">
								<li class="nav-item">
									<a href="${baseURL }/log/monitoring/list" class="nav-link">
										<i class="icon-doc"></i>
										<span class="title">日志监控</span>
									</a>
								</li>
							</shiro:hasPermission> -->
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
			<!-- 右侧网页开始 -->
			<div class="page-content-wrapper">
				<div class="page-content">
					<!-- 右侧内容头部开始 -->
					<div class="page-bar">
						<ul class="page-breadcrumb">
							<li>
								<a href="index.html">设备</a>
								<i class="fa fa-angle-right"></i>
							</li>
							<li>
								<span>设备激活</span>
							</li>
						</ul>
					</div>
					<!-- 右侧内容头部结束 -->
					<!--右侧四个数据框开始-->
					<div class="row widget-row bg_color" style="padding-top: 20px">
						<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
							<div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
								<h4 class="widget-thumb-heading">今日激活</h4>
								<div class="widget-thumb-wrap">
									<div class="widget-thumb-body">
										<span class="widget-thumb-body-stat" id="todayActivation">0</span>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
							<div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
								<h4 class="widget-thumb-heading">昨日激活</h4>
								<div class="widget-thumb-wrap">
									<div class="widget-thumb-body">
										<span class="widget-thumb-body-stat"  id="yesTodayActivation">0</span>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
							<div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
								<h4 class="widget-thumb-heading">近七天激活</h4>
								<div class="widget-thumb-wrap">
									<div class="widget-thumb-body">
										<span class="widget-thumb-body-stat"  id="weekBegainActivation">0</span>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
							<div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
								<h4 class="widget-thumb-heading">累计激活</h4>
								<div class="widget-thumb-wrap">
									<div class="widget-thumb-body">
										<span class="widget-thumb-body-stat" id="allActivationData">0</span>
									</div>
								</div>
							</div>
						</div>
                    </div>
					<!--右侧四个数据框结束-->
					<!--中部条件选择开始-->
					<div class="row bg_color">
						<div class="col-md-12">
							<div class="portlet light portlet-fit bordered">
								<div class="portlet-body ">
									<div class="portlet-body form ">
										<div class="row">
											
												<div class="col-md-offset-1 col-md-2 ">
													<div class="form-body ">
														<div class="form-group">
														<shiro:hasRole name="admin">
															<label>事业部</label>
															<select class="form-control input-small" id="department_select">
																<option value="">--请选择-- </option>
															</select>
														</shiro:hasRole>
														</div>
													</div>
												</div>
												<div class="col-md-2">
													<div class="form-body">
														<div class="form-group">
															<label>机型</label>
															<select class=" form-control input-small"  id="device_select">
																<option value="">--请选择-- </option>
															</select>
														</div>
													</div>
												</div>
												<div class="col-md-2">
													<div class="form-body">
														<div class="form-group">
															<label>国家</label>
															<select class="form-control input-small" id="country_select">
																<option value="">--请选择-- </option>
																
															</select>
														</div>
													</div>
												</div>
												<div class="col-md-2">
													<div class="form-body">
														<div class="form-group">
															<label>时间</label>
															<select class="form-control input-small" id="time_select">
																<option value="1">今日</option>
																<option value="-1">昨日</option>
																<option value="7">近七天</option>
																<option value="30">近三十天</option>
																
															</select>
														</div>
													</div>
												</div>
												<div class="col-md-2">
													<div class="form-body">
														<div class="form-group">
															
															<label> </label>
															<label> </label>
															<div class="right " style="padding-top: 5px;">
																<button id="sure_btn" class="btn green">确定</button>
																<button id="reset_btn" class="btn green">重置</button>
															</div>
														</div>
													</div>
												</div>
												
											
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--中部条件选择结束-->
					<!--底部tab开始-->
					<div class="row bg_color">
						<div class="col-md-12">
							<div class="portlet light portlet-fit bordered">
								<div class="portlet-body">
									<div class="tabbable-bordered" id="_tab">
										<ul class="nav nav-tabs">
											<li class="active" id="device_tab">
												<a href="#tab_add" data-toggle="tab" aria-expanded="true"> 机型</a>
											</li>
											<li class="" id="country_tab">
												<a href="#tab_activation" data-toggle="tab" aria-expanded="false"> 国家</a>
											</li>
										</ul>
										<!--tab开始-->
										<div class="tab-content">
											<!--激活机型tab开始-->
											<div class="tab-pane  active" id="tab_add">
												<!--激活机型趋势图开始-->
												<div class="row">
													<div class="col-md-12">
														<div class="portlet light portlet-fit bordered">
															<div class="portlet-title">
																<div class="caption">
																	<i class=" icon-bar-chart font-green"></i>
																	<span class="caption-subject font-green bold uppercase">设备激活机型趋势图</span>
																</div>
															</div>
															<div class="portlet-body">
																<button id="btn_device_seven" type="button" class="btn btn-success" value="7">近7天</button>
																<button id="btn_device_thirty" type="button" class="btn btn-success" value="30">近30天</button>
																<div id="_device_activation_line" style="height:500px;padding-top: 10px;-webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);"></div>
															</div>
														</div>
													</div>
												</div>
												<!--激活机型趋势图结束-->
												<!--激活机型排行表开始-->
												<div class="row">
													<div class="col-md-12">
														<div class="portlet light portlet-fit bordered">
															<div class="portlet-title">
																<div class="caption">
																	<i class=" icon-bar-chart font-green"></i>
																	<span class="caption-subject font-green bold uppercase">设备激活机型TOP5</span>
																</div>
															</div>
															<div class="portlet-body">
																<table id="add_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
																	<thead width="100%">
																		<tr>
																			<th>机型</th>
																			<th id="device_activation_day">近七天激活</th>
																			<th>累计激活</th>
																		</tr>
																	</thead>
																	<tbody id="device_activation_table_tbody">
																	
																	</tbody>
																	
																</table>
															</div>
														</div>
													</div>
												</div>
												<!--激活机型排行榜结束-->
											</div>
											<!--激活机型tab结束-->
											<!--激活国家tab开始-->
											<div class="tab-pane" id="tab_activation">
												<div class="row">
													<div class="col-md-12">
														<div class="portlet light portlet-fit bordered">
															<div class="portlet-title">
																<div class="caption">
																	<i class=" icon-layers font-green"></i>
																	<span class="caption-subject font-green bold uppercase">设备激活国家趋势图</span>
																</div>

															</div>
															<div class="portlet-body">
																<button id="btn_country_seven" type="button" class="btn btn-success" value="7">近7天</button>
																<button id="btn_country_thirty" type="button" class="btn btn-success" value="30">近30天</button>
																<div id="_country_activation_line" style="height:500px;padding-top: 10px;"></div>
															</div>
														</div>
													</div>
												</div>
												<!--激活国家表格开始-->
												<div class="row">
													<div class="col-md-12">
														<div class="portlet light portlet-fit bordered">
															<div class="portlet-title">
																<div class="caption">
																	<i class=" icon-layers font-green"></i>
																	<span class="caption-subject font-green bold uppercase">设备激活国家TOP5</span>
																</div>

															</div>
															<div class="portlet-body">
																<table id="add_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
																	<thead width="100%">
																		<tr>
																			<th>国家</th>
																			<th id="country_activation_day" ></th>
																			<th>累计激活</th>
																		</tr>
																	</thead>
																	<tbody id="country_activation_table_tbody">
																		
																	</tbody>
																	
																	
																</table>
															</div>
														</div>
													</div>
												</div>
												<!--激活国家表格结束-->
											</div>
										</div>
										<!--tab结束-->
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--底部tab结束-->
				</div>
			</div>
			<!--右侧网页结束-->
		</div>
		<!--内容结束-->
		<!-- 底部内容开始-->
		<%@include file="../system/foot.jsp" %>
		<!-- 底部内容结束-->
		<!--[if lt IE 9]>
		<script src="${baseURL}/common/assets/global/plugins/respond.min.js"></script>
		<script src="${baseURL}/common/assets/global/plugins/excanvas.min.js"></script> 
		<![endif]-->
		<script src="${baseURL}/common/assets/global/plugins/jquery.min.js" type="text/javascript">
		</script>
		<script src="${baseURL}/common/js/echarts.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/scripts/app.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
		<!-- 数字动画 开始-->
		<script src="${baseURL}/common/assets/global/plugins/bootstrap-select/js/bootstrap-select.js"></script> 
        <script src="${baseURL}/common/assets/global/plugins/counterup/jquery.waypoints.min.js" type="text/javascript"></script>
        <script src="${baseURL}/common/assets/global/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>
        <!-- 数字动画结束-->
		<script type="text/javascript">
			//机型激活趋势图
			var _device_activation_line;
			var _country_activation_line;
			var device_option ;
			var country_option;
			var userStatus;
			var dev_select = false;
			
			device_option = {
				tooltip: {
					trigger: 'axis'
				},
				legend: {
					data: []
				},
				toolbox: {
					show: true,
					feature: {
						mark: {
							show: true
						},
						dataView: {
							show: true,
							readOnly: false
						},
						magicType: {
							show: true,
							type: ['line', 'bar', 'stack', 'tiled']
						},
						restore: {
							show: true
						},
						saveAsImage: {
							show: true
						}
					}
				},
				calculable: true,
				xAxis: [{
					type: 'category',
					boundaryGap: false,
					data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
				}],
				yAxis: [{
					type: 'value'
				}],
				series: [{
					name: '机型一',
					type: 'line',
					stack: '总量',
					data: [120, 132, 201, 234, 190, 130, 210]
				}]
			};
			
			
			
			country_option = {
				tooltip: {
					trigger: 'axis'
				},
				legend: {
					data: []
				},
				toolbox: {
					show: true,
					feature: {
						mark: {
							show: true
						},
						dataView: {
							show: true,
							readOnly: false
						},
						magicType: {
							show: true,
							type: ['line', 'bar', 'stack', 'tiled']
						},
						restore: {
							show: true
						},
						saveAsImage: {
							show: true
						}
					}
				},
				calculable: true,
				xAxis: [{
					type: 'category',
					boundaryGap: false,
					data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
				}],
				yAxis: [{
					type: 'value'
				}],
				series: [{
					name: '机型一',
					type: 'line',
					stack: '总量',
					data: [120, 132, 101, 134, 90, 230, 210]
				}]
			};
			
			
			
			$(document).ready(function(){
			var date =7;
			var type = "device";
			_device_activation_line = echarts.init(document.getElementById("_device_activation_line"));
			_country_activation_line = echarts.init(document.getElementById("_country_activation_line"));
            userStatus = '${sessionScope.WtUserInfo.userStatus}';
            var userType= '${sessionScope.WtUserInfo.userType}';
             _country_activation_line.setOption(country_option);
            _device_activation_line.setOption(device_option); 
            requestBaseNum(userType,userStatus);
            if(userStatus == 'admin' && userStatus != null){
				requestActivationData(date,"country",_country_activation_line,country_option);
				requestActivationData(date,"device",_device_activation_line,device_option);
				requestDeviceActivationTable(date,"device");
				requestCountryActivationTable(date,"country");
				requestDepartment();
				requestDevice();
				requestCountry(userStatus);
				$("#btn_device_seven").click(function(){  
					requestActivationData($(this).val(),"device",_device_activation_line,device_option); 
					requestDeviceActivationTable($(this).val(),"device");
				});  
				$("#btn_device_thirty").click(function(){  
					requestActivationData($(this).val(),"device",_device_activation_line,device_option); 
					requestDeviceActivationTable($(this).val(),"device");
				}); 
				$("#btn_country_seven").click(function(){
					requestActivationData($(this).val(),"country",_country_activation_line,country_option);
					requestCountryActivationTable($(this).val(),"country");
				});
				$("#btn_country_thirty").click(function(){
					requestActivationData($(this).val(),"country",_country_activation_line,country_option);
					requestCountryActivationTable($(this).val(),"country");
				});
				$("#sure_btn").click(function(){
						if(dev_select){
							requestCountryCondition($('#department_select').val(),$('#device_select').val(),$('#time_select').val(),_country_activation_line,country_option);
							requestCountryTableCondition($('#department_select').val(),$('#device_select').val(),$('#time_select').val());
						}else{
							requestDeviceCondition($('#department_select').val(),$('#country_select').val(),$('#time_select').val(),_device_activation_line,device_option);
							requestDeviceTableCondition($('#department_select').val(),$('#country_select').val(),$('#time_select').val());
						}
				});
				}else{
					requestDeviceCondition(userStatus,"",7,_device_activation_line,device_option);
					requestDeviceTableCondition(userStatus,"",7);
					requestCountryCondition(userStatus,"",7,_country_activation_line,country_option);
					requestCountryTableCondition(userStatus,"",7);
					requestDepartDevice(userStatus);
					requestCountry(userStatus);
					$("#btn_device_seven").click(function(){  
						requestDeviceCondition(userStatus,"",7,_device_activation_line,device_option);
						requestDeviceTableCondition(userStatus,"",7);
					});  
					$("#btn_device_thirty").click(function(){  
						requestDeviceCondition(userStatus,"",30,_device_activation_line,device_option);
						requestDeviceTableCondition(userStatus,"",30);
					}); 
					$("#btn_country_seven").click(function(){
						requestCountryCondition(userStatus,"",7,_country_activation_line,country_option);
						requestCountryTableCondition(userStatus,"",7);
					});
					$("#btn_country_thirty").click(function(){
						requestCountryCondition(userStatus,"",30,_country_activation_line,country_option);
						requestCountryTableCondition(userStatus,"",30);
						
					});
					$("#sure_btn").click(function(){
						if(dev_select){
							requestCountryCondition(userStatus,$('#device_select').val(),$('#time_select').val(),_country_activation_line,country_option);
							requestCountryTableCondition(userStatus,$('#device_select').val(),$('#time_select').val());
						}else{
							requestDeviceCondition(userStatus,$('#country_select').val(),$('#time_select').val(),_device_activation_line,device_option);
							requestDeviceTableCondition(userStatus,$('#country_select').val(),$('#time_select').val());
						}
					});
				}
			$("#reset_btn").click(function(){
				var a = document.getElementById("device_select");
				var b = document.getElementById("department_select");
				var c = document.getElementById("country_select");
				var d = document.getElementById("time_select");
				//$("device_select").val("");
				a.options[0].selected = true;
				b.options[0].selected = true;
				c.options[0].selected = true;
				d.options[0].selected = true;
			});
            $("#device_select").each(function () {
				$("#" + this.id).attr("disabled", true);
			});
			$( "#_tab" ).tabs({  
		      activate: function( event, ui ){
		      	
		        if(dev_select){
		        	$("#device_select").each(function () {
					 $("#" + this.id).attr("disabled", true);
					});
					$("#country_select").each(function () {
					 $("#" + this.id).removeAttr("disabled");
					});
		          }else{
		        	$("#country_select").each(function () {
					 $("#" + this.id).attr("disabled", true);
					});
					$("#device_select").each(function () {
					 $("#" + this.id).removeAttr("disabled");
					});
				}
		        dev_select = !dev_select;
		        }  
		      });   
		    });
			
			function requestActivationData(date,type,ec,option){
			var requestUrl = "${baseURL }/device/activation/getChartData/"+type+"/"+date;
			ec.showLoading({
				text:'数据加载中...'
			});
			$.ajax({
				url:requestUrl,  
		        type:'get',  
		        dataType:'json',  
		        success:function(jsons){  
		            ec.hideLoading();

		            var Item = function(){  
		                return {  
		                    name:'',  
		                    type:'line',  
		                    data:[]  
		                    }  
		                };  
		            var legends = [];  
		            var Series = [];  
		            var json = jsons.itemData;  
		            for(var i=0;i < json.length;i++){  
		                var it = new Item();  
		                it.name = json[i].name;  
		                legends.push(json[i].name);  
		                it.data = json[i].data;  
		                Series.push(it);  
		                }  
		            option.xAxis[0].data = jsons.xAxisData;   
		            option.legend.data = legends;  
		            option.series = Series; // 设置图表  
		            ec.setOption(option);// 重新加载图表  
		            window.onresize = ec.resize;
		        },  
		        error:function(){  
		            ec.hideLoading();

		            alert("数据加载失败！请检查数据链接是否正确");  
		        }  
			});
		}
		function requestCountry(userStatus){
			var requestUrl = "${baseURL }/device/activation/getCountry";
			$.ajax({
				url:requestUrl,
				type:'get',
				data:{userStatus:userStatus},
				dataType:'json',
				success:function(jsons){
					//alert(jsons);
					var json = jsons;
					//alert(json);
					console.log("国家"+json);
					$.each(json,function(){
		            	//var tr= $("<tr>");
		            	if(this.country == undefined || this.country == 'undefined' || this.country == null){
							return ;
						}
		            	$("#country_select").html($("#country_select").html() + "<option value="+this.country+">" +this.country + " </option> ");
		            });
				},
				error:function(){
					console.log("国家失败");
					$("#country_select").html("fasfa");
				}
			});
		}
		function requestDeviceActivationTable(date,type){
			var requestUrl = "${baseURL }/device/activation/getTableData/"+type+"/"+date;
			$.ajax({
				url:requestUrl,  
		        type:'get',  
		        dataType:'json',  
		        success:function(jsons){  
		            var json = jsons;  
		            $("#device_activation_table_tbody").html("");
		            $("#device_activation_day").html("近"+ date +"天活跃");
		            $.each(json,function(){
		            	var tr= $("<tr>");
		            	$("#device_activation_table_tbody").html($("#device_activation_table_tbody").html() + "<tr><td>" +this.dev_name + "</td> <td> "  +this.dayActive + "</td> <td> "  +this.sum + " </td></tr> ");
		            });
		        },  
		        error:function(){  
		           // alert("数据加载失败！请检查数据链接是否正确");  
		           $("#device_activation_table_tbody").html("数据获取失败");
		        }  
			});
		}
		
		function requestCountryActivationTable(date,type){
			var requestUrl = "${baseURL }/device/activation/getTableData/"+type+"/"+date;
			$.ajax({
				url:requestUrl,  
		        type:'get',  
		        dataType:'json',  
		        success:function(jsons){  
		            var json = jsons;  
		            $("#country_activation_table_tbody").html("");
		            $("#country_activation_day").html("近"+ date +"天激活");
		            $.each(json,function(){
		            	var tr= $("<tr>");
		            	$("#country_activation_table_tbody").html($("#country_activation_table_tbody").html() + "<tr><td>" +this.country + "</td> <td> "  +this.dayActive + " </td><td> "  +this.sum + " </td></tr> ");
		            });
		        },  
		        error:function(){  
		           // alert("数据加载失败！请检查数据链接是否正确");  
		           $("#country_activation_table_tbody").html("数据获取失败");
		        }  
			});
		}
		
		function requestDepartment(){
			var requestUrl = "${baseURL }/device/activation/getDepartment";
			$.ajax({
				url:requestUrl,
				type:'get',
				dataType:'json',
				success:function(jsons){
					//alert(jsons);
					var json = jsons;
					//alert(json);
					/*if(json.departName == undefined || json.departName == 'undefined' || json.departName == null){
						return ;
					}*/
					//console.log(this.departName);
					$.each(json,function(){
		            	//var tr= $("<tr>");
		            	if(this.departName == undefined || this.departName == 'undefined' || this.departName == null){
		            		return ;
		            	}
		            	$("#department_select").html($("#department_select").html() + "<option value="+this.departName+">" +this.departName + " </option> ");
		            });
				},
				error:function(){
					$("#department_select").html("请求错误，请刷新");
				}
			});
		}
		function requestDepartDevice(departName){
			var requestUrl = "${baseURL }/device/activation/queryDepartDev";
			$.ajax({
				url:requestUrl,
				type:'get',
				data:{departName:departName},
				dataType:'json',
				success:function(jsons){
					//alert(jsons);
					console.log(jsons);
					var json = jsons;
					//alert(json);

				
					$.each(json,function(){
						if(this.model == undefined || this.model == 'undefined' || this.model == null || this.model == ''){
							return ;
						}
		            	//var tr= $("<tr>");
		            	$("#device_select").html($("#device_select").html() + "<option value="+this.model+">" +this.model + " </option> ");
		            });
				},
				error:function(){
					$("#device_select").html("请求错误，请刷新");
				}
			});
		}
		function requestDevice(){
			var requestUrl = "${baseURL }/device/activation/getDevice";
			$.ajax({
				url:requestUrl,
				type:'get',
				dataType:'json',
				success:function(jsons){
					//alert(jsons);
					var json = jsons;
					console.log("fasfdsfasdf"+json);
					//alert(json);
					//alert(json);
					$.each(json,function(){
		            	//var tr= $("<tr>");
		            	console.log("fasdf"+this.devName);
		            	/*if(this.devName == undefined || this.devName == 'undefined' || this.devName == null || this.devName == ''){
							return ;
						}*/
		            	$("#device_select").html($("#device_select").html() + "<option value="+this.devName+">" +this.devName + " </option> ");
		            });
				},
				error:function(){
					
					$("#device_select").html("请求错误，请刷新");
				}
			});
		}
		
		function requestDeviceCondition(departName,typedata,time,ec,option){
			var requestUrl = "${baseURL }/device/activation/getSearch/device/" + time;
			ec.showLoading({
				text:'数据加载中...'
			});
			$.ajax({
				url:requestUrl,
				type:'get',
				data: {country:typedata,departName:departName},
				contentType:"application/x-www-form-urlencoded:charset=UTF-8",
				dataType:'json',
				success:function(jsons){
					//alert(jsons);
					var json = jsons;
					ec.hideLoading();
					$.each(json,function(){
		            	
		            	var Item = function(){  
		                return {  
		                    name:'',  
		                    type:'line',  
		                    data:[]  
		                    }  
		                };  
		                var legends = [];  
			            var Series = [];  
			            var json = jsons.itemData;  
			            for(var i=0;i < json.length;i++){  
			                var it = new Item();  
			                it.name = json[i].name;  
			                legends.push(json[i].name);  
			                it.data = json[i].data;  
			                Series.push(it);  
			            }  
			            option.xAxis[0].data = jsons.xAxisData;   
			            option.legend.data = legends;  
			            option.series = Series; // 设置图表  
			            ec.clear();
			            ec.setOption(option);// 重新加载图表  
			            window.onresize = ec.resize;
			            });
				},
				error:function(){
					ec.hideLoading();
					//$("#device_select").html("请求错误，请刷新");
				}
			});
		}
		
		function requestCountryCondition(departName,typedata,time,ec,option){
			var requestUrl = "${baseURL }/device/activation/getSearch/country/"+time;
			ec.showLoading({
				text:'数据加载中...'
			});
			$.ajax({
				url:requestUrl,
				type:'get',
				data: {devName:typedata,departName:departName},
				contentType:"application/x-www-form-urlencoded:charset=UTF-8",
				dataType:'json',
				success:function(jsons){
					//alert(jsons);
					var json = jsons;
					//alert(json);
					ec.hideLoading();
					$.each(json,function(){
		            	
		           
		            	var Item = function(){  
		                return {  
		                    name:'',  
		                    type:'line',  
		                    data:[]  
		                    }  
		                };  
		                var legends = [];  
			            var Series = [];  
			            var json = jsons.itemData;  
			            for(var i=0;i < json.length;i++){  
			                var it = new Item();  
			                it.name = json[i].name;  
			                legends.push(json[i].name);  
			                it.data = json[i].data;  
			                Series.push(it);  
			            }  
			            option.xAxis[0].data = jsons.xAxisData;   
			            option.legend.data = legends;  
			            option.series = Series; // 设置图表  
			            ec.clear();
			            ec.setOption(option);// 重新加载图表  
			            window.onresize = ec.resize;
			            });
		            
				},
				error:function(){
					ec.hideLoading();
					//$("#device_select").html("请求错误，请刷新");
				}
			});
		}
		
		function requestDeviceTableCondition(departName,typedata,date){
			var requestUrl = "${baseURL }/device/activation/getSearchTable/device/"+date;
			$.ajax({
				url:requestUrl,  
		        type:'get',  
		        data: {country:typedata,departName:departName},
		        contentType:"application/x-www-form-urlencoded:charset=UTF-8",
		        dataType:'json',  
		        success:function(jsons){  
		            var json = jsons;  
		            $("#device_activation_table_tbody").html("");
		            $("#device_activation_day").html("近"+ date +"天活跃");
		            $.each(json,function(){
		            	var tr= $("<tr>");
		            	$("#device_activation_table_tbody").html($("#device_activation_table_tbody").html() + "<tr><td>" +this.dev_name + " </td><td> "  +this.dayActive + "</td> <td> "  +this.sum + " </td></tr> ");
		            });
		        },  
		        error:function(){  
		           // alert("数据加载失败！请检查数据链接是否正确");  
		           $("#device_activation_table_tbody").html("无数据");
		        }  
			});
		}
		
		function requestCountryTableCondition(departName,typedata,date){
			var requestUrl = "${baseURL }/device/activation/getSearchTable/country/"+date;
			$.ajax({
				url:requestUrl,  
		        type:'get',  
		        data: {devName:typedata,departName:departName},
		        contentType:"application/x-www-form-urlencoded:charset=UTF-8",
		        dataType:'json',  
		        success:function(jsons){  
		            var json = jsons;  
		            $("#country_activation_table_tbody").html("");
		            $("#country_activation_day").html("近"+ date +"天活跃");
		            $.each(json,function(){
		            	var tr= $("<tr>");
		            	$("#country_activation_table_tbody").html($("#country_activation_table_tbody").html() + "<tr><td>" +this.country + " <td> "  +this.dayActive + " <td> "  +this.sum + " <td></tr> ");
		            });
		        },  
			error:function(){  
		           // alert("数据加载失败！请检查数据链接是否正确");  
		           $("#country_activation_table_tbody").html("加载错误，请刷新");
		        }  
			});
		}
		function requestBaseNum(userType,userStatus){
			var requestUrl = "${baseURL }/device/activation/baseData"+"?userType="+userType+"&userStatus="+userStatus;
			$.ajax({
				url:requestUrl,
				dataType:'json',
				type:'get',
				success:function(jsons){
					//$("#department_select").html()
					//alert(todayInputNum+""+yestodayInputNum);
					var todayActivation = jsons.todayActivation;
					var yesTodayActivation = jsons.yesTodayActivation;
					var weekBegainActivation = jsons.weekBegainActivation;
					var allData  = jsons.allData;
					console.log(todayActivation+" "+yesTodayActivation+" "+weekBegainActivation+" "+allData);
					  
					 // $("#todayInput").value(allInputNum);
					// $("#todayInput").data("value")===allInputNum;
					  //alert();
					 //session.setAtt
					//$.session.set("todayInput", today)
					$("#todayActivation").html(todayActivation);
					$("#yesTodayActivation").html(yesTodayActivation);
					$("#weekBegainActivation").html(weekBegainActivation);
					$("#allActivationData").html(allData);
					/*$("#yestodayInput").value(yestodayInputNum);
					$("#allInput").html(allInputNum);*/
					//$("#todayInput").attr("data-value",11); 
					//jQuery("#todayInput").text(today);
					//$("#todayInput").html(today);
					/*$("#yestodayInput").data(12);
					alert($("#allInput").data("value"));
					$("#allInput").data("value",1);*/
					//alert($("#allInput").data("value"));
				}
			});
		}
		//重绘
		$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
			_country_activation_line.resize();
		});
		</script>
	</body>

</html>