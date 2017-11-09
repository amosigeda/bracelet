<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@include file="../../common/taglib/taglib.jsp" %>
<head>
<title>报表统计</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/global/plugins/datatables/datatables.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
<link href="${baseURL }/common/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="${baseURL }/common/assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css" />
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
	<!--网页主体开始-->
	<div class="page-container">
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
							<li class="nav-item active ">
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
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- 右侧头部开始-->
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li>
							<a href="index.html">设备</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span>报表统计</span>
						</li>
					</ul>
					
				</div>
				<!--右侧头部结束-->
				<!-- 条件开始 -->
				<div class="row bg_color" style="padding-top: 20px;">
					<div class="col-md-12">
						<div class="portlet light portlet-fit bordered">
							<div class="portlet-body ">
								<div class="portlet-body form ">
									<div class="row">
										<div class="col-md-2">
											<div class="form-body ">
												<div class="form-group">
												<shiro:hasRole name="admin">
													<label>事业部</label>
													<select class="form-control input-small" id="department_select">
														<option value="">--请选择--</option>
														
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
														<option value="">--请选择--</option>
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
													<label style=""> 开始时间</label>
													<div class="input-group input-small date date-picker" data-date="" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
														<input id="beginDate" type="text" class="form-control" readonly>
														<span class="input-group-btn">
	                                        			<button class="btn default" type="button">
	                                            		<i class="fa fa-calendar" style="height: 20px"></i>
	                                        			</button>
	                                  					</span>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-body">
												<div class="form-group">
													<label> 结束时间</label>
													<div class="input-group input-small date date-picker" data-date="" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
														<input id="endDate" type="text" class="form-control" readonly>
														<span class="input-group-btn">
                                       			<button class="btn default" type="button">
                                           		<i class="fa fa-calendar" style="height: 20px"></i>
                                       			</button>
                                   				</span>
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-body">
												<div class="form-group">
												<label> </label>
													<label> </label>
													<div class="right " style="padding-top: 5px;">
														<button id="sure" type="submit" class="btn green">确定</button>
														<button id="reset" type="submit" class="btn green">重置</button>
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
				<!--条件结束-->
					
				<!--表格开始-->
				<div class="row bg_color">
					<div class="col-md-12">
						<div class="portlet light portlet-fit bordered" style="padding: 20px;">
							<table id="conditionTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
								<thead width="100%">
									<tr>
										<th>文件名</th>
										<th>SN码</th>
										<th>查询条件</th>
										<th>查询状态</th>
										<th>操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<!--表格结束-->
			</div>
		</div>
	</div>
	<!--网页主体结束-->
	<!-- 底部内容开始-->
	<%@include file="../system/foot.jsp" %>
	<!-- 底部内容结束-->
	
	<!--模态框开始-->
	<div id="s" class="modal container fade" tabindex="-1">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">查询条件详情</h4>
		</div>
		<div class="modal-body">
			<div class="col-md-12">
				<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
						<div class="caption">
							<i class=" icon-layers font-green"></i>
							<span class="caption-subject font-green bold uppercase">导入设备SN号详细</span>
						</div>
					</div>
					<div class="portlet-body">
						<table id="model_cson" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead width="100%">
								<tr>
									<th>事业部</th>
									<th>设备名</th>
									<th>国家</th>
									<th>起始时间</th>
									<th>结束时间</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--模态框结束-->

	<!--模态框结束-->
	<div id="model_condition" class="modal container fade" tabindex="-1">
		
		<div class="modal-body">
			<div class="col-md-12">
				<div class="portlet light portlet-fit bordered">
					<div class="portlet-title">
						<div class="caption">
							<i class=" icon-layers font-green"></i>
							<span class="caption-subject font-green bold uppercase">导入设备SN号详细</span>
						</div>
					</div>
					<div class="portlet-body">
						<table id="_model_condition" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead width="100%">
								<tr>
									<th>事业部</th>
									<th>设备名</th>
									<th>国家</th>
									<th>起始时间</th>
									<th>结束时间</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${baseURL }/common/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/moment.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/scripts/app.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/datatables/datatables.all.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/pages/scripts/components-date-time-pickers.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	var userStatus;
	var userType;
	var lang ={
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
		$(document).ready(function() {
			
			/*var table = $('#_table').DataTable({
				"processing": true,
				"ajax": "../data/2.txt",
				"contentType": "application/x-www-form-urlencoded; charset=utf-8",
				"columnDefs": [{
					"targets": -3,
					"data": null,
					"defaultContent": "<button id='condition' class='btn btn-success'>查看条件</button>"
				}, {
					"targets": -1,
					"data": null,
					"defaultContent": "<button id='output' class='btn btn-success'>导出</button>"
				}],
				language: {
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
				}
			});

			$('#_table tbody').on('click', '#condition', function() {
				var data = table.row($(this).parents('tr')).data();
				$('#_table').DataTable();
				alert(data[0] + "'s salary is: " + data[6]);
			});
			$('#_table tbody').on('click', '#output', function() {
				var data = table.row($(this).parents('tr')).data();
				$('#_table').DataTable();
				alert(data[0] + "'delete is: " + data[6]);
			});*/
			userStatus = '${sessionScope.WtUserInfo.userStatus}';
            userType= '${sessionScope.WtUserInfo.userType}';
            ckdate();
 			if(userStatus == 'admin' && userStatus != null){
				requestCountry(userStatus);
				requestDepartment();
				requestDevice();
				requestTableData(userType,userStatus);
				$("#sure").click(function(){
					var ckdateResult = ckdate();
				    	if(ckdateResult){
	        		requestConditionTableData($('#department_select').val(),$('#device_select').val(),$('#country_select').val(),$('#beginDate').val(),$('#endDate').val(),userType,userStatus);
	        	}
        		});
        	}else{
        		requestCountry(userStatus);
        		requestDepartDevice(userStatus);
        		requestTableData(userType,userStatus);
        		$("#sure").click(function(){
        				var ckdateResult = ckdate();
				    	if(ckdateResult){
				    		
				    		requestConditionTableData("",$('#device_select').val(),$('#country_select').val(),$('#beginDate').val(),$('#endDate').val(),userType,userStatus);
				    	}
        		
        		});
        	}


		});
		function ckdate() {
	        var endtime = $('#endDate').val();
	        var starttime = $('#beginDate').val();
	        var start = new Date(starttime.replace("-", "-").replace("-", "-"));
	        var end = new Date(endtime.replace("-", "-").replace("-", "-"));
	        if (end < start) {
	            alert('结束日期不能小于开始日期！');
	            return false;
	        }
	        else {
	            return true;
	        }
	    }
		var conditionTable;
		var requestUrl;
		function requestConditionTableData(departName,devName,country,beginDate,endDate,userType,userStatus){

		 requestUrl = "${baseURL }/report/searchReportInfo?userStatus="+userStatus+"&userType="+userType+"&departName="+departName+"&devName="+devName+"&country="+country+"&beginDate="+beginDate+"&endDate="+endDate;
		
			if(conditionTable == undefined || conditionTable == "undefined" || conditionTable == null){
				
				conditionTable =  $("#conditionTable").dataTable({  
				 		"language":lang,
			            "ajax" : requestUrl,  
			            "columns" :  [   
			                /*{"data": function(obj){ return getMyDate(obj.createTime)}},*/
				            {"data": "filename"},
				            {"data": "snSum"},
				            {"defaultContent": "<button id='query_condition' data-target='#model_condition' data-toggle='modal' class='btn btn-success'>查看</button>"},
				            {"data": "status"},
				            {"defaultContent": "<button id='download_sn' class='btn red-mint'>导出</button>"}
			                ]  
			        	});  
					$('#conditionTable tbody').on( 'click', '#query_condition', function () {
						var data = $('#conditionTable').DataTable().row($(this).parents('tr')).data();
		              	requestQueryCondition(data.filename);
					} );
					$('#conditionTable tbody').on( 'click', '#download_sn', function () {

						var data = $('#conditionTable').DataTable().row($(this).parents('tr')).data();

		              	requestDownLoad(data.filename);
					} );

			}else{
				 conditionTable.fnDestroy();
				 conditionTable =  $("#conditionTable").dataTable({  
					 		"language":lang,
				            "ajax" : requestUrl,  
				            "columns" : [   
			              /*  {"data": function(obj){ return getMyDate(obj.createTime)}},*/
				            {"data": "filename"},
				            {"data": "snSum"},
				            {"defaultContent": "<button id='query_condition' data-target='#model_condition' data-toggle='modal' class='btn btn-success'>查看</button>"},
				            {"data": "status"},
				            {"defaultContent": "<button id='download_sn' class='btn red-mint'>导出</button>"}
			                ]  
				        	});  
					 $('#conditionTable tbody').on( 'click', '#query_condition', function () {
					 	var data = $('#conditionTable').DataTable().row($(this).parents('tr')).data();
		              	requestQueryCondition(data.filename);
					});
					 $('#conditionTable tbody').on( 'click', '#download_sn', function () {
					 	var data = $('#conditionTable').DataTable().row($(this).parents('tr')).data();
					 	requestDownLoad(data.filename);
					});
			}
			conditionTable.fnDraw(); 
		}
		function requestTableData(userType,userStatus){

		 requestUrl = "${baseURL }/report/queryReport?userStatus="+userStatus+"&userType="+userType;
		
			if(conditionTable == undefined || conditionTable == "undefined" || conditionTable == null){
				
				conditionTable =  $("#conditionTable").dataTable({  
				 		"language":lang,
			            "ajax" : requestUrl,  
			            "columns" : [   
			               /* {"data": function(obj){ return getMyDate(obj.createTime)}},*/
				            {"data": "filename"},
				            {"data": "snSum"},
				            {"defaultContent": "<button id='query_condition' data-target='#model_condition' data-toggle='modal' class='btn btn-success'>查看</button>"},
				            {"data": "status"},
				            {"defaultContent": "<button id='_output' class='btn red-mint'>导出</button>"}
			                ]  
			        	});  
					$('#conditionTable tbody').on( 'click', '#query_condition', function () {
						var data = $('#conditionTable').DataTable().row($(this).parents('tr')).data();
		              	requestQueryCondition(data.filename);
					} );
					$('#conditionTable tbody').on( 'click', '#_output', function () {

						var data = $('#conditionTable').DataTable().row($(this).parents('tr')).data();
						requestDownLoad(data.filename);
		              
					} );

			}else{
				 conditionTable.fnDestroy();
				 conditionTable =  $("#conditionTable").dataTable({  
					 		"language":lang,
				            "ajax" : requestUrl,  
				            "columns" :  [   
			             /*   {"data": function(obj){ return getMyDate(obj.createTime)}},*/
				            {"data": "filename"},
				            {"data": "snSum"},
				            {"defaultContent": "<button id='query_condition' data-target='#model_condition' data-toggle='modal' class='btn btn-success'>查看</button>"},
				            {"data": "status"},
				            {"defaultContent": "<button id='_output' class='btn red-mint'>导出</button>"}
			                ]  
				        	});  
					 $('#conditionTable tbody').on( 'click', '#query_condition', function () {
					 	var data = $('#conditionTable').DataTable().row($(this).parents('tr')).data();
		              	requestQueryCondition(data.filename);
					});
					 $('#conditionTable tbody').on( 'click', '#_output', function () {
					 	var data = $('#conditionTable').DataTable().row($(this).parents('tr')).data();
					 	requestDownLoad(data.filename);
					});
			}
			conditionTable.fnDraw(); 
		}
		function requestCountry(userStatus){
			var requestUrl = "${baseURL }/report/getCountry";
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
		            	if(this.country == undefined || this.country == 'undefined' || this.country == null){
							return ;
						}
		            	$("#country_select").html($("#country_select").html() + "<option value="+this.country+">" +this.country + " </option> ");
		            });
				},
				error:function(){
					$("#country_select").html("请求错误，请刷新");
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
		function requestDepartDevice(departName){
			var requestUrl = "${baseURL }/device/activation/queryDepartDev";
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
		function requestDevice(){
			var requestUrl = "${baseURL }/device/activation/getDevice";
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
		function ckdate() {
	        var endtime = $('#endDate').val();
	        var starttime = $('#beginDate').val();
	        var start = new Date(starttime.replace("-", "-").replace("-", "-"));
	        var end = new Date(endtime.replace("-", "-").replace("-", "-"));
	        if (end < start) {
	            alert('结束日期不能小于开始日期！');
	            return false;
	        }
	        else {
	            return true;
	        }
	    }
		function today() {
			var today = new Date();
			var h = today.getFullYear();
			var m = today.getMonth() + 1;
			var d = today.getDate();
			return h + "-" + m + "-" + d;
		}
		function getMyDate(time){  
		
	    var oDate = new Date(time),  
	        oYear = oDate.getFullYear(),  
	        oMonth = oDate.getMonth()+1,  
	        oDay = oDate.getDate(),  
	        oHour = oDate.getHours(),  
	        oMin = oDate.getMinutes(),  
	        oSen = oDate.getSeconds(),  
	        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间  
		    return oTime;  
		};  
		  
		//补0操作,当时间数据小于10的时候，给该数据前面加一个0  
		function getzf(num){  
		    if(parseInt(num) < 10){  
		        num = '0'+num;  
		    }  
		    return num;  
		}
		document.getElementById("beginDate").value = today();
		document.getElementById("endDate").value = today();

		function requestDownLoad(filename){
			var requestUrl = "${baseURL }/report/download?filename="+filename;
			/*$.ajax({
				url:requestUrl,
				type:'post',
				
				success:function(){
					console.log("成功");
				},
				error:function(){
					alert("下载出错");
				}
			});*/
			window.location.href = requestUrl;
		}
		$("#reset").click(function(){
				var a = document.getElementById("device_select");
				var b = document.getElementById("department_select");
				var c = document.getElementById("country_select");
				//var d = document.getElementById("time_select");
				//$("device_select").val("");
				a.options[0].selected = true;
				b.options[0].selected = true;
				c.options[0].selected = true;
				//d.options[0].selected = true;
				document.getElementById("beginDate").value = today();
		document.getElementById("endDate").value = today();
			});
		var model_condition;
		function requestQueryCondition(filename){
			var requestUrl = "${baseURL }/report/queryCondition?filename="+filename;

			if(model_condition == undefined || model_condition == "undefined" || model_condition == null){
				
				model_condition =  $("#_model_condition").dataTable({  
				 		"language":lang,
			            "ajax" : requestUrl,  
			            "columns" :   [   
			                {"data": "departname"},
				            {"data": "devname"},
				            {"data": "country"},
				            {"data": function(obj){ return getMyDate(obj.begindate)}},
				            {"data": function(obj){ return getMyDate(obj.enddate)}},
				            ]
			        	});  
					

			}else{
				 model_condition.fnDestroy();
				 model_condition =  $("#_model_condition").dataTable({  
					 		"language":lang,
				            "ajax" : requestUrl,  
				            "columns" : [   
			                {"data": "departname"},
				            {"data": "devname"},
				            {"data": "country"},
				            {"data": function(obj){ return getMyDate(obj.begindate)}},
				            {"data": function(obj){ return getMyDate(obj.enddate)}},
				            ]
				        	});  
					
			}
			model_condition.fnDraw(); 

		}
	</script>
</body>
</html>
