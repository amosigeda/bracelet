<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@include file="../../common/taglib/taglib.jsp" %>
<head>

<title>机型统计</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=all" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css">
<link href="${baseURL }/common/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color">
<link href="${baseURL }/common/assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css">
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
		<%@include file = "../system/top.jsp" %>
	<!--网页头部结束-->
		<div class="clearfix"> </div>
		<!--内容开始-->
		<div class="page-container">
			<!--左侧菜单开始-->
			<div class="page-sidebar-wrapper">
				<div class="page-sidebar navbar-collapse collapse">
					<!--左侧菜单开始-->
					<ul class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 20px">
						<shiro:hasPermission name="首页">
						<li class="nav-item ">
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
								<li class="nav-item  active">
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
			<!-- 左侧菜单结束 -->
			<!-- 右侧内容开始 -->
			<div class="page-content-wrapper">
				<div class="page-content">
					<!-- 右侧内容头部开始-->
					<div class="page-bar">
						<ul class="page-breadcrumb">
							<li>
								<a href="${baseURL }/">设备</a>
								<i class="fa fa-angle-right"></i>
							</li>
							<li>
								<span>机型统计</span>
							</li>
						</ul>
					</div>
					<!-- 右侧内容头部结束 -->
					<!--中部条件开始-->
					<div class="row bg_color" style="padding-top: 20px;">
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
															<option value="">请选择</option>
															
														</select>
														</shiro:hasRole>
													</div>
												</div>
											</div>
										
											<div class="col-md-2">
												<div class="form-body">
													<div class="form-group">
														<label>机型</label>
														<select class="form-control input-small" id="device_select">
															<option value="">请选择</option>
															
														</select>
													</div>
												</div>
											</div>
											<div class="col-md-2">
												<div class="form-body">
													<div class="form-group">
														<label>国家</label>
														<select class="form-control input-small" id="country_select">
															
															<option value="">--请选择--</option>	
															
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
					<!--中部条件结束-->
					<!--tab开始-->
					<div class="row bg_color">
						<div class="col-md-12">
							<div class="portlet light portlet-fit bordered">
								<div class="portlet-body">
									<div class="tabbable-bordered" id="_tab">
										<ul class="nav nav-tabs">
											<li class="active">
												<a href="#tab_system" data-toggle="tab" aria-expanded="true" value="1"> 操作系统</a>
											</li>
											<li class="">
												<a href="#tab_brand" data-toggle="tab" aria-expanded="false" value="1"> 手机品牌</a>
											</li>
											<li class="">
												<a href="#tab_occupy" data-toggle="tab" aria-expanded="false" value="1"> 手机机型</a>
											</li>
										</ul>
										<div class="tab-content">
											<!--操作系统ab开始-->
											<div class="tab-pane  active" id="tab_system">
												<!--机型活跃趋势图开始-->
												<div class="row">
													<div class="col-md-12">
														<div class="portlet light portlet-fit bordered">
															<div class="portlet-title">
																<div class="caption">
																	<i class=" icon-bar-chart font-green"></i>
																	<span class="caption-subject font-green bold uppercase">设备手机操作系统活跃趋势图</span>
																</div>
															</div>
															<div class="portlet-body">
																<button id="btn_os_seven" type="button" class="btn btn-success" value="7">近7天</button>
																<button id="btn_os_thirty" type="button" class="btn btn-success" value="30">近30天</button>
																<div id="_os_line" style="height:500px;padding-top: 10px;"></div>
															</div>
														</div>
													</div>
												</div>
												<!--机型活跃趋势图结束-->
												<!--机型活跃排行开始-->
												<div class="row">
													<div class="col-md-12">
														<div class="portlet light portlet-fit bordered">
															<div class="portlet-title">
																<div class="caption">
																	<i class=" icon-layers font-green"></i>
																	<span class="caption-subject font-green bold uppercase">操作系统占有量</span>
																</div>
															</div>
															<div class="portlet-body">
																<table id="_system_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
																	<thead width="100%">
																		<tr>
																			<th>操作系统类型</th>
																			<th>占有量</th>
																			<th>占有率</th>
																		</tr>
																	</thead>
																	<tbody id="os_active_table_tbody">
																		
																	</tbody>
																</table>
															</div>
														</div>
													</div>
												</div>
												<!--机型活跃排行结束-->
											</div>
											<!--操作系统tab结束-->
											<!--手机品牌tab开始-->
											<div class="tab-pane" id="tab_brand">
												<!--国家活跃趋势图开始-->
												<div class="row">
													<div class="col-md-12">
														<div class="portlet light portlet-fit bordered">
															<div class="portlet-title">
																<div class="caption">
																	<i class=" icon-bar-chart font-green"></i>
																	<span class="caption-subject font-green bold uppercase">设备手机品牌趋势图</span>
																</div>
															</div>
															<div class="portlet-body">
																<button id="btn_brand_seven" type="button" class="btn btn-success" value="7">近7天</button>
																<button id="btn_brand_thirty" type="button" class="btn btn-success" value="30">近30天</button>
																<div id="_brand_line" style="height:500px;padding-top: 10px;"></div>
															</div>
														</div>
													</div>
												</div>
												<!--国家活跃趋势图结束-->
												<!--国家活跃排行表开始-->
												<div class="row">
													<div class="col-md-12">
														<div class="portlet light portlet-fit bordered">
															<div class="portlet-title">
																<div class="caption">
																	<i class=" icon-layers font-green"></i>
																	<span class="caption-subject font-green bold uppercase">手机品牌占有量</span>
																</div>
															</div>
															<div class="portlet-body">
																<table id="_brand_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
																	<thead width="100%">
																		<tr>
																			<th>手机品牌</th>
																			<th>占有量</th>
																			<th>占有率</th>
																		</tr>
																	</thead>
																</table>
															</div>
														</div>
													</div>
												</div>
												<!--国家活跃排行表结束-->
											</div>
											<!--手机品牌tab结束-->
											<!--占有量ab开始-->
											<div class="tab-pane" id="tab_occupy">
												<!--占有量趋势图开始-->
												<div class="row">
													<div class="col-md-12">
														<div class="portlet light portlet-fit bordered">
															<div class="portlet-title">
																<div class="caption">
																	<i class=" icon-bar-chart font-green"></i>
																	<span class="caption-subject font-green bold uppercase">设备手机型号趋势图</span>
																</div>
															</div>
															<div class="portlet-body">
																<button id="btn_model_seven" type="button" class="btn btn-success" value="7">近7天</button>
																<button id="btn_model_thirty" type="button" class="btn btn-success" value="30">近30天</button>
																<div id="_model_line" style="height:500px;padding-top: 10px;"></div>
															</div>
														</div>
													</div>
												</div>
												<!--占有量趋势图结束-->
												<!--占有量表开始-->
												<div class="row">
													<div class="col-md-12">
														<div class="portlet light portlet-fit bordered">
															<div class="portlet-title">
																<div class="caption">
																	<i class=" icon-layers font-green"></i>
																	<span class="caption-subject font-green bold uppercase">设备手机型号占有量</span>
																</div>
															</div>
															<div class="portlet-body">
																<table id="_model_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
																	<thead width="100%">
																		<tr>
																			<th>手机型号</th>
																			<th>占有量</th>
																			<th>占有率</th>
																		</tr>
																	</thead>
																</table>
															</div>
														</div>
													</div>
												</div>
												<!--占有量表结束-->
											</div>
											<!--占有量tab结束-->
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--tab结束-->
				</div>
			</div>
		</div>
		<!-- 右侧内容结束-->
		<!-- 底部内容开始-->
		<%@include file = "../system/foot.jsp" %>
		<!-- 底部内容结束-->
		<!--[if lt IE 9]>
		<script src="${baseURL}/common/assets/global/plugins/respond.min.js"></script>
		<script src="${baseURL}/common/assets/global/plugins/excanvas.min.js"></script> 
		<![endif]-->
		<!-- BEGIN CORE PLUGINS -->
		<script src="${baseURL}/common/assets/global/plugins/jquery.min.js" type="text/javascript">
		</script>
		<script src="${baseURL}/common/js/echarts.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/moment.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/scripts/app.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/datatables/datatables.all.min.js" type="text/javascript"></script>
		<script src="${baseURL}/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>

		<script type="text/javascript">


			var userStatus;//判断用户是否为管理员，事业部人员
			var os_line;
			var brand_line;
			var model_line;

			var os_option;
			var brand_option;
			var model_option;

			var brand_datatables;
			var model_datatables;

			var selectId = 'tab_system';
			os_option =  {
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
					data: []
				}],
				yAxis: [{
					type: 'value'
				}],
				series: []
			};
			brand_option ={
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
					data: []
				}],
				yAxis: [{
					type: 'value'
				}],
				series: []
			};
			model_option = {
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
					data: []
				}],
				yAxis: [{
					type: 'value'
				}],
				series: []
			};
			$(function () {  
				 //提示信息  
				 var lang =  {
					"sProcessing": "处理中...",
					"sLengthMenu": "显示 _MENU_ 项结果",
					"sZeroRecords": "没有匹配结果",
					"sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
					"sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
					"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
					"sInfoPostFix": "",
					"sSearch": "搜索:",
					"sUrl": "",
					"sEmptyTable": "表中数据为空",
					"sLoadingRecords": "载入中...",
					"sInfoThousands": ",",
					"oPaginate": {
						"sFirst": "首页",
						"sPrevious": "上页",
						"sNext": "下页",
						"sLast": "末页"
					},
					"oAria": {
						"sSortAscending": ": 以升序排列此列",
						"sSortDescending": ": 以降序排列此列"
					}
				};
				

			$(document).ready(function(){
				userStatus = '${sessionScope.WtUserInfo.userStatus}';
				var date =7;
				var type="";
				os_line = echarts.init(document.getElementById("_os_line"));
				
			
				brand_line = echarts.init(document.getElementById("_brand_line"));
				
				model_line = echarts.init(document.getElementById("_model_line"));
				if(userStatus == 'admin' && userStatus != null){
					
					
					requestChartData(7,"os",os_line,os_option);
					requestChartData(7,"brand",brand_line,brand_option);
					requestChartData(7,"model",model_line,model_option);
					requestOSTableData(7,"os");
					requestBrandDataTableData(7,"brand");
					requestModelDataTableData(7,"model");
					requestCountry(userStatus);
					requestDevice();
					requestDepartment();
					$("#btn_os_seven").click(function(){  
					  requestChartData($(this).val(),"os",os_line,os_option); 
					  //requestDeviceActiveTable($(this).val(),"device");

					  requestOSTableData($(this).val(),"os");
					});  
					$("#btn_os_thirty").click(function(){  
					  requestChartData($(this).val(),"os",os_line,os_option); 
					  //requestDeviceActiveTable($(this).val(),"device");
					  requestOSTableData($(this).val(),"os");
					});  
					$("#btn_brand_seven").click(function(){  
					  requestChartData($(this).val(),"brand",brand_line,brand_option); 
					  //requestDeviceActiveTable($(this).val(),"device");
					  requestBrandDataTableData($(this).val(),"brand");
					});  
					$("#btn_brand_thirty").click(function(){  
					  requestChartData($(this).val(),"brand",brand_line,brand_option); 
					  //requestDeviceActiveTable($(this).val(),"device");
					  requestBrandDataTableData($(this).val(),"brand");
					});  
					$("#btn_model_seven").click(function(){  
					  requestChartData($(this).val(),"model",model_line,model_option); 
					  //requestDeviceActiveTable($(this).val(),"device");
					  requestModelDataTableData($(this).val(),"model");
					});  
					$("#btn_model_thirty").click(function(){  
					  requestChartData($(this).val(),"model",model_line,model_option); 
					  //requestDeviceActiveTable($(this).val(),"device");
					  requestModelDataTableData($(this).val(),"model");
					}); 

				}else {
					
					requestCondition(userStatus,$('#device_select').val(),$('#country_select').val(),"os",7,os_line,os_option);
					requestOSTableCondition(userStatus,$('#device_select').val(),$('#country_select').val(),7);
					requestCondition(userStatus,$('#device_select').val(),$('#country_select').val(),"brand",7,brand_line,brand_option);
					requestBrandTableCondition(userStatus,$('#device_select').val(),$('#country_select').val(),7);
					requestCondition(userStatus,$('#device_select').val(),$('#country_select').val(),"model",7,model_line,model_option);
					requestModelTableCondition(userStatus,$('#device_select').val(),$('#country_select').val(),7);
					requestDepartDevice(userStatus);
					requestCountry(userStatus);
					$("#btn_os_seven").click(function(){  
					  requestCondition(userStatus,"","","os",7,os_line,os_option); 
					  //requestDeviceActiveTable($(this).val(),"device");

					 requestOSTableCondition(userStatus,"","",7);
					});  
					$("#btn_os_thirty").click(function(){  
					  requestCondition(userStatus,"","","os",$(this).val(),os_line,os_option); 
					  //requestDeviceActiveTable($(this).val(),"device");
					  requestOSTableCondition(userStatus,"","",$(this).val());
					});  
					$("#btn_brand_seven").click(function(){  
					  
					  requestCondition(userStatus,"","","brand",7,brand_line,brand_option);
					  //requestDeviceActiveTable($(this).val(),"device");
					  requestBrandTableCondition(userStatus,"","",7);
					});  
					$("#btn_brand_thirty").click(function(){  
					  requestCondition(userStatus,"","","brand",30,brand_line,brand_option);
					  //requestDeviceActiveTable($(this).val(),"device");
					  requestBrandTableCondition(userStatus,"","",30);
					});  
					$("#btn_model_seven").click(function(){  
					  requestCondition(userStatus,"","","model",7,model_line,model_option);
					  //requestDeviceActiveTable($(this).val(),"device");
					  requestModelTableCondition(userStatus,"","",7);
					});  
					$("#btn_model_thirty").click(function(){  
					  requestCondition(userStatus,"","","model",30,model_line,model_option);
					  //requestDeviceActiveTable($(this).val(),"device");
					  requestModelTableCondition(userStatus,"","",30);
					});
				}
				
			});
			$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
				model_line.resize();
			});
			$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
				brand_line.resize();
			});

			function requestDepartDevice(departName){
				var requestUrl = "${baseURL }/device/active/queryDepartDev";
				$.ajax({
					url:requestUrl,
					type:'get',
					data:{departName:departName},
					dataType:'json',
					success:function(jsons){
						//alert(jsons);
						var json = jsons;
						//alert(json);
						$.each(json,function(){
			            	//var tr= $("<tr>");
			            	if(this.model == undefined || this.model == 'undefined' || this.model == null || this.model == ''){
							return ;
						}
			            	$("#device_select").html($("#device_select").html() + "<option value="+this.model+">" +this.model + " </option> ");
			            });
					},
					error:function(){
						
						$("#device_select").html("请求错误，请刷新");
					}
				});
			}
			function requestChartData(date,type,ec,option){
				var requestUrl = "${baseURL }/device/active/getChartData/"+type+"/"+date;
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
			var requestUrl = "${baseURL }/device/active/getCountry";

			$.ajax({
				url:requestUrl,
				type:'get',
				data:{userStatus:userStatus},
				dataType:'json',
				success:function(jsons){
					//alert(jsons);
					var json = jsons;
					//alert(json);
					$.each(json,function(){
		            	//var tr= $("<tr>");
		            	$("#country_select").html($("#country_select").html() + "<option value="+this.country+">" +this.country + " </option> ");
		            });
				},
				error:function(){
					$("#country_select").html("请求错误，请刷新");
				}
			});
		}
			function requestOSTableData(date,type){
				var requestUrl = "${baseURL }/device/active/getTableData/"+type+"/"+date;
				$.ajax({
					url:requestUrl,  
			        type:'get',  
			        dataType:'json',  
			        success:function(jsons){  
			            var json = jsons;  
			            
			            $("#os_active_table_tbody").html("");
			           // $("#os_active_day").html("近"+ date +"天活跃");
			            $.each(json,function(){
			            	var tr= $("<tr>");
			            	$("#os_active_table_tbody").html($("#os_active_table_tbody").html() + "<tr><td>" +this.mobile_op+ " </td><td> "  +this.sum + " </td><td> "  +this.occupancy + " </td></tr> ");
			            });
			        },  
			        error:function(){  
			           // alert("数据加载失败！请检查数据链接是否正确");  
			           $("#os_active_table_tbody").html("数据获取失败");
			        }  
				});
			}
			function requestBrandDataTableData(date,type){
				var requestUrl = "${baseURL }/device/active/getTableData/"+type+"/"+date;
				if(brand_datatables == undefined || brand_datatables == "undefined" || brand_datatables == null){
						
						brand_datatables =  $("#_brand_table").dataTable({  
						 		"language":lang,
					            "ajax" : requestUrl,  
					            "columns" : [   
					                {"data" : "mobile_brand"},  
					                {"data" : "sum"},  
					                {"data" : "occupancy"}  
					                ]  
					        	});  
				}else{
					 brand_datatables.fnDestroy();
					 brand_datatables =  $("#_brand_table").dataTable({  
						 		"language":lang,
					            "ajax" : requestUrl,  
					            "columns" : [   
					                {"data" : "mobile_brand"},  
					                {"data" : "sum"},  
					                {"data" : "occupancy"}  
					                ]  
					        	});  
				}
				brand_datatables.fnDraw(); 
			}	
			function requestModelDataTableData(date,type){
				var requestUrl = "${baseURL }/device/active/getTableData/"+type+"/"+date;
				if(model_datatables == undefined || model_datatables == "undefined" || model_datatables == null){
						
						model_datatables =  $("#_model_table").dataTable({  
						 		"language":lang,
					            "ajax" : requestUrl,  
					            "columns" : [   
					                {"data" : "mobile_model"},  
					                {"data" : "sum"},  
					                {"data" : "occupancy"}  
					                ]  
					        	});  
				}else{
					 model_datatables.fnDestroy();
					 model_datatables =  $("#_model_table").dataTable({  
						 		"language":lang,
					            "ajax" : requestUrl,  
					            "columns" : [   
					                {"data" : "mobile_model"},  
					                {"data" : "sum"},  
					                {"data" : "occupancy"}  
					                ]  
					        	});  
				}
				model_datatables.fnDraw(); 
			}	
			function requestBrandTableCondition(departName,devName,country,date){
				var requestUrl = "${baseURL }/device/active/getSearchTable/brand/"+date+"?departName="+departName+"&devName="+devName+"&country="+country;
				if(brand_datatables == undefined || brand_datatables == "undefined" || brand_datatables == null){
						brand_datatables =  $("#_brand_table").dataTable({  
						 		"language":lang,
					            "ajax" : requestUrl,  
					            "columns" : [   
					                {"data" : "mobile_brand"},  
					                {"data" : "sum"},  
					                {"data" : "occupancy"}  
					                ]  
					        	});  
				}else{
					 brand_datatables.fnDestroy();
					 brand_datatables =  $("#_brand_table").dataTable({  
						 		"language":lang,
					            "ajax" : requestUrl,  
					            "columns" : [   
					                {"data" : "mobile_brand"},  
					                {"data" : "sum"},  
					                {"data" : "occupancy"}  
					                ]  
					        	});  
				}
				brand_datatables.fnDraw(); 
			}	
			function requestModelTableCondition(departName,devName,country,date){
				var requestUrl = "${baseURL }/device/active/getSearchTable/model/"+date+"?departName="+departName+"&devName="+devName+"&country="+country;
				if(model_datatables == undefined || model_datatables == "undefined" || model_datatables == null){
						
						model_datatables =  $("#_model_table").dataTable({  
						 		"language":lang,
					            "ajax" : requestUrl,  
					            "columns" : [   
					                {"data" : "mobile_model"},  
					                {"data" : "sum"},  
					                {"data" : "occupancy"}  
					                ]  
					        	});  
				}else{
					 model_datatables.fnDestroy();
					 model_datatables =  $("#_model_table").dataTable({  
						 		"language":lang,
					            "ajax" : requestUrl,  
					            "columns" : [   
					                {"data" : "mobile_model"},  
					                {"data" : "sum"},  
					                {"data" : "occupancy"}  
					                ]  
					        	});  
				}
				model_datatables.fnDraw(); 
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
			$("#sure_btn").click(function(){
				if(userStatus == 'admin' && userStatus != null){
					if('tab_system' == selectId){
						requestCondition($('#department_select').val(),$('#device_select').val(),$('#country_select').val(),"os",$('#time_select').val(),os_line,os_option);
						requestOSTableCondition($('#department_select').val(),$('#device_select').val(),$('#country_select').val(),$('#time_select').val());
					}else if(selectId =='tab_brand'){
						requestCondition($('#department_select').val(),$('#device_select').val(),$('#country_select').val(),"brand",$('#time_select').val(),brand_line,brand_option);
						requestBrandTableCondition($('#department_select').val(),$('#device_select').val(),$('#country_select').val(),$('#time_select').val());
					}else{
						requestCondition($('#department_select').val(),$('#device_select').val(),$('#country_select').val(),"model",$('#time_select').val(),model_line,model_option);
						requestModelTableCondition($('#department_select').val(),$('#device_select').val(),$('#country_select').val(),$('#time_select').val());
					}
				}else{
					if('tab_system' == selectId){
						requestCondition(userStatus,$('#device_select').val(),$('#country_select').val(),"os",$('#time_select').val(),os_line,os_option);
						requestOSTableCondition(userStatus,$('#device_select').val(),$('#country_select').val(),$('#time_select').val());
					}else if(selectId =='tab_brand'){
						requestCondition(userStatus,$('#device_select').val(),$('#country_select').val(),"brand",$('#time_select').val(),brand_line,brand_option);
						requestBrandTableCondition(userStatus,$('#device_select').val(),$('#country_select').val(),$('#time_select').val());
					}else{
						requestCondition(userStatus,$('#device_select').val(),$('#country_select').val(),"model",$('#time_select').val(),model_line,model_option);
						requestModelTableCondition(userStatus,$('#device_select').val(),$('#country_select').val(),$('#time_select').val());
					}
				}
				
			});
			$( "#_tab" ).tabs({  
		      activate: function( event, ui ){
		        selectId = ui.newPanel.attr('id');
		      }  
		    });  
		});

		function requestDepartment(){
			var requestUrl = "${baseURL }/device/active/getDepartment";
			$.ajax({
				url:requestUrl,
				type:'get',
				dataType:'json',
				success:function(jsons){
					//alert(jsons);
					var json = jsons;
					//alert(json);
					$.each(json,function(){
		            	//var tr= $("<tr>");
		            	$("#department_select").html($("#department_select").html() + "<option value="+this.departName+">" +this.departName + " </option> ");
		            });
				},
				error:function(){
					
					$("#department_select").html("请求错误，请刷新");
				}
			});
		}
		function requestDevice(){
			var requestUrl = "${baseURL }/device/active/getDevice";
			$.ajax({
				url:requestUrl,
				type:'get',
				dataType:'json',
				success:function(jsons){
					//alert(jsons);
					var json = jsons;
					//alert(json);
					$.each(json,function(){
		            	//var tr= $("<tr>");
		            	$("#device_select").html($("#device_select").html() + "<option value="+this.devName+">" +this.devName + " </option> ");
		            });
				},
				error:function(){
					
					$("#device_select").html("请求错误，请刷新");
				}
			});
		}
		function requestCondition(departName,devName,country ,type,date,ec,option){
			var requestUrl = "${baseURL }/device/active/getSearch/"+type+"/"+date;
				ec.showLoading({
					text:'数据加载中...'
				});
			$.ajax({
				url:requestUrl,  
		        type:'get',  
		        data: {departName:departName,devName:devName,country:country},
		        contentType:"application/x-www-form-urlencoded:charset=UTF-8",
		        dataType:'json',  
		        success:function(jsons){  
		           /* var json = jsons;  
		            $("#device_active_table_tbody").html("");
		            $("#device_active_day").html("近"+ date +"天活跃");
		            $.each(json,function(){
		            	var tr= $("<tr>");
		            	$("#device_active_table_tbody").html($("#device_active_table_tbody").html() + "<tr><td>" +this.dev_name + " <td> "  +this.dayActive + " <td> "  +this.sum + " <td></tr> ");
		            });*/
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
		            ec.clear();
		            ec.setOption(option);// 重新加载图表  
		            window.onresize = ec.resize;
		        },  
		        error:function(){  
		           // alert("数据加载失败！请检查数据链接是否正确");  
		           //$("#device_active_table_tbody").html("无数据");
		        	ec.hideLoading();
		        }  
			});

		}

		function requestOSTableCondition(departName,devName,country,date){
				var requestUrl = "${baseURL }/device/active/getSearchTable/os/"+date;
				
				$.ajax({
					url:requestUrl,  
			        type:'get',  
			        data: {departName:departName,devName:devName,country:country},
		        	contentType:"application/x-www-form-urlencoded:charset=UTF-8",
			        dataType:'json',  
			        success:function(jsons){  
			            var json = jsons;  
			            
			            $("#os_active_table_tbody").html("");
			           // $("#os_active_day").html("近"+ date +"天活跃");
			            $.each(json,function(){
			            	var tr= $("<tr>");
			            	$("#os_active_table_tbody").html($("#os_active_table_tbody").html() + "<tr><td>" +this.mobile_op+ " </td><td> "  +this.sum + "</td> <td> "  +this.occupancy + " </td></tr> ");
			            });
			        },  
			        error:function(){  
			           // alert("数据加载失败！请检查数据链接是否正确");  
			           $("#os_active_table_tbody").html("数据获取失败");
			        }  
				});
			}
		function requestDepartDeviceSelectData(userStatus){
			var requestUrl = "{baseURL}/device/statistics/getDepart/getDeviceSelect";
			$.ajax({
				url:requestUrl,
				type:get,
				data:{userStatus:userStatus},
				dataType:'json',
				success:function(jsons){
					//alert(jsons);
					var json = jsons;
					//alert(json);
					$.each(json,function(){
		            	//var tr= $("<tr>");
		            	$("#device_select").html($("#device_select").html() + "<option value="+this.devName+">" +this.devName + " </option> ");
		            });
				},
				error:function(){
					
					$("#device_select").html("请求错误，请刷新");
				}
			});
		}
		function requestDepartOSChartData(userStatus){
			var requestUrl ="{baseURL}/device/statistics/getDepart/getOsChartData";

		}
		</script>
	</body>

</html>