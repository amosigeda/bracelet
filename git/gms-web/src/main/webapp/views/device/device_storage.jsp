<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@include file="../../common/taglib/taglib.jsp" %>
<head>
<title>设备入库</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<link href="${baseURL }/common/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />

<!-- BEGIN PAGE LEVEL PLUGINS -->
<link href="${baseURL }/common/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />


<!-- END PAGE LEVEL PLUGINS -->
<link href="${baseURL }/common/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css">
<!-- BEGIN THEME GLOBAL STYLES -->
<link href="${baseURL }/common/assets/global/plugins/datatables/datatables.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
<link href="${baseURL }/common/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
<!-- END THEME GLOBAL STYLES -->
<!-- BEGIN THEME LAYOUT STYLES -->
<link href="${baseURL }/common/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="${baseURL }/common/assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css" />
<!-- END THEME LAYOUT STYLES -->

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
	<!--网页主体内容开始-->
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
							<li class="nav-item active ">
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
		<!--右侧内容开始-->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- 右侧头部开始-->
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li>
							<a href="${baseURL }">设备</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span>设备入库</span>
						</li>
					</ul>
				</div>
				<!-- 右侧头部结束 -->
				<!-- 右侧网页四个数据框开始-->
				<div class="row widget-row bg_color" style="padding-top:20px;">
					<div class="col-md-3">
						<div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
							<h4 class="widget-thumb-heading">今日入库</h4>
							<div class="widget-thumb-wrap">
								<div class="widget-thumb-body" >
									<span class="widget-thumb-body-stat"  id="todayInput">0</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
							<h4 class="widget-thumb-heading">昨日入库</h4>
							<div class="widget-thumb-wrap">
								<div class="widget-thumb-body">
									<span class="widget-thumb-body-stat"   id="yestodayInput">0</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
							<h4 class="widget-thumb-heading">累计入库</h4>
							<div class="widget-thumb-wrap">
								<div class="widget-thumb-body">
									<span class="widget-thumb-body-stat"  id="allInput">0</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
							<h4 class="widget-thumb-heading">存货明细</h4>
							<div class="widget-thumb-wrap">
								<div class="widget-thumb-body">
									<span class="widget-thumb-body-stat"  id="sroageInput">0</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--右侧网页四个数据框结束-->
				<!--设备入库表格开始-->
				<div class="row bg_color">
					<div class="col-md-12">
						<div class="portlet light portlet-fit bordered">
							<div class="portlet-title">
								<div class="caption">
									<i class=" icon-layers font-green"></i>
									<span class="caption-subject font-green bold uppercase">设备入库记录</span>
								</div>
								<!--条件选择开始-->
								<div class="col-md-12" style="margin-top: 30px;">
									<div class="portlet-body form">
											<div class="form-body">
												<div class="form-group">
													<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12" >
														<label>起始时间</label>
														<div class="input-group input-medium date date-picker" data-date="" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
															<input id="beginDate" name="beginDate" type="text" placeholder="请输入起始时间" class="form-control" readonly>
															<span class="input-group-btn" >
                                                   			<button class="btn default"  type="button">
                                                       		<i class="fa fa-calendar " style="height: 20px"></i>
                                                   			</button>
                                             				</span>
														</div>
													</div>
													<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
														<label>结束时间</label>
														<div class="input-group input-medium date date-picker" data-date="" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
															<input id="endDate" name="endDate" type="text" placeholder="请输入结束时间" class="form-control" readonly>
															<span class="input-group-btn">
                                                   			<button class="btn default" type="button">
                                                       		<i class="fa fa-calendar" style="height: 20px"></i>
                                                   			</button>
                                               				</span>
														</div>
													</div>
													<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
														<div class="right " style="margin-top: 25px;">
															<button id="searchSure"  class="btn green">确定</button>
														</div>
													</div>
													<div class="col-md-1">
														<div class="right" style="margin-top: 25px;">
														<shiro:hasPermission name="入库操作">
															<button type="submit" class="btn green" data-target='#_file' data-toggle='modal'>上传Excel</button>
														</shiro:hasPermission>
														</div>
													</div>
												</div>
											</div>
										
									</div>
								</div>
							</div>
							<!--条件选择结束-->
						</div>
						<!--表格开始-->
						<div class="portlet-body">
							<div class="row">
								<div class="col-md-12">
									<div class="portlet light form-fit bordered" style="padding: 20px;">
										<table id="deviceTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
											<thead width="100%">
												<tr>
													<th>日期</th>
													<th>设备名称</th>
													<th>新增数</th>
													<th>设备累计数</th>
													<th>导入状态</th>
													<th>操作员</th>
													<th>操作</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
							</div>
						</div>
						<!--表格结束-->
					</div>
				</div>
				<!--设备入库表格结束-->
			</div>
		</div>
		<!--右侧内容结束-->
	</div>
	<!--网页主题内容结束 -->
	<!-- 底部内容开始-->
	<%@include file="../system/foot.jsp" %>
	<!-- 底部内容结束-->
	<!--模态框开始-->
	<div class="modal fade " id="_file" tabindex="-1" aria-hidden="true">
		<div class="modal-content">
			<div class="modal-header">
			
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h4 class="modal-title">上传Excel</h4>
			
			</div>
			<div class="modal-body">
				<div class="portlet-body form">
					<form action="${baseURL }/device/storage/import" enctype="multipart/form-data" method="post" class="form-horizontal">
						<div class="form-body">
							<div class="form-group">
								<div class="col-md-offset-2 col-lg-10">
									<div class="fileinput fileinput-new" data-provides="fileinput">
										<div class="input-group input-large">
											<div class="form-control uneditable-input input-fixed input-medium" data-trigger="fileinput">
												<i class="fa fa-file fileinput-exists"></i>&nbsp;
												<span class="fileinput-filename"> </span>
											</div>
											<span class="input-group-addon btn default btn-file" style="width:240px;">
	                                            <span class="fileinput-new"> 选择文件 </span>
												<span class="fileinput-exists"> 更换文件</span>
												<input type="file" name="filename" id="filename" > 
											</span>
											<a href="javascript:;" class="input-group-addon btn red fileinput-exists" data-dismiss="fileinput"> 删除</a>
										</div>
									</div>
									
										<div class="col-md-offset-3 col-md-9" style="margin-top: 20px ">
											<button type="submit" class="btn green" id="excelBtn"><i class="fa fa-check"></i> 上传</button>
										</div>
									
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!--模态框结束-->
	
	<!--模态框开始-->
	<div id="_device_add_detail" class="modal container fade" tabindex="-1">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">SN号导入详情</h4>
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
						<table id="sn_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead width="100%">
								<tr>
									<th>序号</th>
									<th>SN号</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--模态框结束-->
	
	<!--[if lt IE 9]>
	<script src="${baseURL }/common/assets/global/plugins/respond.min.js"></script>
	<script src="${baseURL }/common/assets/global/plugins/excanvas.min.js"></script> 
	<!--[endif]-->
	<script src="${baseURL }/common/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>

	<script src="${baseURL }/common/assets/global/plugins/moment.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/scripts/app.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/pages/scripts/components-date-time-pickers.min.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>

	<script src="${baseURL }/common/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>

	<!-- 数字动画 开始-->
	<script src="${baseURL }/common/assets/global/plugins/counterup/jquery.waypoints.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>
	<!-- 数字动画结束-->

</body>
</html>

<script>
/*    $("#deviceTable").DataTable({
        //开启本地保存功能
        stateSave: true,
        //保存状态操作
        "stateSaveParams": function (settings, data) {
            console.log("stateSaveParams");

            //这里可以操作保存的数据，写上自己特定的逻辑
            //data.search.search = "";
        },
        "stateSaveCallback": function (settings, data) {
            console.log("stateSaveCallback");

            //DT默认保存的key值为DataTables_+表格id+页面名称
            localStorage.setItem('DataTables_' + settings.sInstance, JSON.stringify(data));

            // 你可以把这些数据保存在服务器上，上面的代码标识使用本地储存来存储这些数据
            $.ajax( {
                    "url": "/state_save",
                    "data": data,
                    "dataType": "json",
                    "type": "POST",
                    "success": function () {}
                } );
             

        },

        //读取状态操作
        "stateLoadParams": function (settings, data) {
            console.log("stateLoadParams");

            //在读取数据的时候可以改变数据，根据自己逻辑来处理
            //data.search.search = "";

            //或者你可以直接禁用从缓存里读取数据，只要直接返回false即可
            //return false;
        },
        "stateLoadCallback": function (settings) {
            console.log("stateLoadCallback");
            return JSON.parse(localStorage.getItem('DataTables_' + settings.sInstance));

            //同样你还可以从服务器取数，采用同步的方式获取到保存在服务器里的数据
            *var o;
             $.ajax( {
                "url": "/state_load",
                "async": false,
                "dataType": "json",
                "success": function (json) {
                    o = json;
                }
            } );
             return o;*

        },
        //状态加载完后执行的回调函数
        "stateLoaded": function (settings, data) {
            console.log("stateLoaded");

            //在这里你可以打印出保存的缓存数据
            //alert( 'Saved filter was: '+data.search.search );

        },
        "ajax": {
			"url": "${baseURL}/device/storage/queryDataInfo"
        },
        "columns": [
            {"data": "editTime"},
            {"data": "deviceName"},
            {"data": "num"},
            {"data": "total"},
            {"data": "status"},
            {"data": "inventory"},
            {"data": "operator"},
            {"data": "<button class='btn btn-default' type='button' id='reload' data-toggle='modal' data-target='#employeeModal'>刷新表格</button>"}
        ],
        "language": {
            "lengthMenu": "每页_MENU_ 条记录",
            "zeroRecords": "没有找到记录",
            "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
            "infoEmpty": "无记录",
            "search": "搜索：",
            "infoFiltered": "(从 _MAX_ 条记录过滤)",
            "paginate": {
                "previous": "上一页",
                "next": "下一页"
            }
        },
        "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>" +
        "t" +
        "<'row'<'col-xs-6'i><'col-xs-6'p>>"
    });
});*/
		var userType;
		var userName;
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
	var deviceTable;
	var snTable;
	function requestWorkerDataTableData(userType,userName){
		var requestUrl = "${baseURL }/device/storage/queryDataInfo"+"?userName="+userName+"&userType="+userType;
		
		if(deviceTable == undefined || deviceTable == "undefined" || deviceTable == null){
			
			deviceTable =  $("#deviceTable").dataTable({  
			 		"language":lang,
		            "ajax" : requestUrl,  
		            "columns" : [   
		                {"data": function(obj){ return getMyDate(obj.editTime)}},
			            {"data": "deviceName"},
			            {"data": "num"},
			            {"data": "total"},
			            {"data": "status"},
			            
			            {"data": "operator"},
			            {"defaultContent": "<button id='query_sn' data-target='#_device_add_detail' data-toggle='modal' class='btn btn-success'>查看</button><button id='delete_input' class='btn red-mint'>删除</button>"}
		                ]  
		        	});  
				$('#deviceTable tbody').on( 'click', '#delete_input', function () {
					var data = $('#deviceTable').DataTable().row($(this).parents('tr')).data();
	              requestDeleteSn(data.deviceName,data.editTime);
				} );
				$('#deviceTable tbody').on( 'click', '#query_sn', function () {

					var data = $('#deviceTable').DataTable().row($(this).parents('tr')).data();

	              requestQuerySN(data.deviceName,data.editTime);
				} );

		}else{
			 deviceTable.fnDestroy();
			 deviceTable =  $("#deviceTable").dataTable({  
				 		"language":lang,
			            "ajax" : requestUrl,  
			            "columns" : [   
			                {"data": function(obj){ return getMyDate(obj.editTime)}},
				            {"data": "deviceName"},
				            {"data": "num"},
				            {"data": "total"},
				            {"data": "status"},
				            
				            {"data": "operator"},
				            {"defaultContent": "<button id='query_sn' data-target='#_device_add_detail' data-toggle='modal' class='btn btn-success'>查看</button><button id='delete_input' class='btn red-mint'>删除</button>"}
			                ]  
			        	});  
				 $('#deviceTable tbody').on( 'click', '#delete_input', function () {
				 	var data = $('#deviceTable').DataTable().row($(this).parents('tr')).data();
	              	requestDeleteSn(data.deviceName,data.editTime);
				});
				 $('#deviceTable tbody').on( 'click', '#query_sn', function () {
				 	var data = $('#deviceTable').DataTable().row($(this).parents('tr')).data();
				 	
	              	requestQuerySN(data.deviceName,data.editTime);
				});
		}
		deviceTable.fnDraw(); 
	}
	function requestDataTableData(userType,userName){
		var requestUrl = "${baseURL }/device/storage/queryDataInfo"+"?userName="+userName+"&userType="+userType;
		
		if(deviceTable == undefined || deviceTable == "undefined" || deviceTable == null){
			
			deviceTable =  $("#deviceTable").dataTable({  
			 		"language":lang,
		            "ajax" : requestUrl,  
		            "columns" : [   
		                {"data": function(obj){ return getMyDate(obj.editTime)}},
			            {"data": "deviceName"},
			            {"data": "num"},
			            {"data": "total"},
			            {"data": "status"},
			            
			            {"data": "operator"},
			            {"defaultContent": "<button id='query_sn' data-target='#_device_add_detail' data-toggle='modal' class='btn btn-success'>查看</button>"}
		                ]  
		        	});  
				$('#deviceTable tbody').on( 'click', '#query_sn', function () {
					var data = $('#deviceTable').DataTable().row($(this).parents('tr')).data();

	              requestQuerySN(data.deviceName,data.editTime);
	              //alert(data.editTime);
				} );

		}else{
			 deviceTable.fnDestroy();
			 deviceTable =  $("#deviceTable").dataTable({  
				 		"language":lang,
			            "ajax" : requestUrl,  
			            "columns" : [   
			                {"data": function(obj){ return getMyDate(obj.editTime)}},
				            {"data": "deviceName"},
				            {"data": "num"},
				            {"data": "total"},
				            {"data": "status"},
				           
				            {"data": "operator"},
				            {"defaultContent": "<button id='query_sn' data-target='#_device_add_detail' data-toggle='modal' class='btn btn-success'>查看</button>"}
			                ]  
			        	});  
				 $('#deviceTable tbody').on( 'click', '#query_sn', function () {
	              var data = $('#deviceTable').DataTable().row($(this).parents('tr')).data();
	              //alert(data.editTime);
	              requestQuerySN(data.deviceName,data.editTime);
				});
		}
		deviceTable.fnDraw(); 
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
	function requestInputNum(userType,userName){
		var requestUrl = "${baseURL }/device/storage/inputData"+"?userType="+userType+"&userName="+userName+"&userStatus="+userStatus;
		$.ajax({
			url:requestUrl,
			dataType:'json',
			type:'get',
			success:function(jsons){
				//$("#department_select").html()
				//alert(todayInputNum+""+yestodayInputNum);
				var today = jsons.todayInputNum;
				var yestodayInputNum = jsons.yestodayInputNum;
				var allInputNum = jsons.allInputNum;
				var store = jsons.storeNum;
				console.log(today+" "+yestodayInputNum+" "+allInputNum);
				  
				 // $("#todayInput").value(allInputNum);
				// $("#todayInput").data("value")===allInputNum;
				  //alert();
				 //session.setAtt
				//$.session.set("todayInput", today)
				$("#todayInput").html(today);
				$("#yestodayInput").html(yestodayInputNum);
				$("#allInput").html(allInputNum);
				$("#sroageInput").html(store);
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
	function requestCondition(beginDate,endDate,userType,userName){
		var requestUrl = "${baseURL }/device/storage/queryCondition"+"?beginDate="+beginDate+"&endDate="+endDate+"&userName="+userName+"&userType="+userType;
		if(deviceTable == undefined || deviceTable == "undefined" || deviceTable == null){
			
			deviceTable =  $("#deviceTable").dataTable({  
			 		"language":lang,
		            "ajax" : requestUrl,  
		            "columns" : [   
		                {"data": function(obj){ return getMyDate(obj.editTime)}},
			            {"data": "deviceName"},
			            {"data": "num"},
			            {"data": "total"},
			            {"data": "status"},
			            {"data": "operator"},
			            {"defaultContent": "<button id='query_sn' data-target='#_device_add_detail' data-toggle='modal' class='btn btn-success'>查看</button>"}
		                ]  
		        	});  
				$('#deviceTable tbody').on( 'click', '#query_sn', function () {
					var data = $('#deviceTable').DataTable().row($(this).parents('tr')).data();

	              requestQuerySN(data.deviceName,data.editTime);
	              //alert(data.editTime);
				} );

		}else{
			 deviceTable.fnDestroy();
			 deviceTable =  $("#deviceTable").dataTable({  
				 		"language":lang,
			            "ajax" : requestUrl,  
			            "columns" : [   
			                {"data": function(obj){ return getMyDate(obj.editTime)}},
				            {"data": "deviceName"},
				            {"data": "num"},
				            {"data": "total"},
				            {"data": "status"},
				            
				            {"data": "operator"},
				            {"defaultContent": "<button id='query_sn' data-target='#_device_add_detail' data-toggle='modal' class='btn btn-success'>查看</button>"}
			                ]  
			        	});  
				 $('#deviceTable tbody').on( 'click', '#query_sn', function () {
	              var data = $('#deviceTable').DataTable().row($(this).parents('tr')).data();
	              //alert(data.editTime);
	              requestQuerySN(data.deviceName,data.editTime);
				});
		}
		deviceTable.fnDraw(); 
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
    function fomatDate(editTime){
    	var time = new Date(editTime.replace("-","-"));	
    	return time.Format("yyyy-MM-dd HH:mm:ss"); 
    }
    function requestQuerySN(devName,dateTime){
    	 dateTime =getMyDate(dateTime);
    	var requestUrl = "${baseURL}/device/storage/querySn"+"?devName="+devName+"&editTime="+dateTime;
    	//alert(requestUrl);
    	if(snTable == undefined || snTable == "undefined" || snTable == null){
			//$('#sn_table').DataTable();
			snTable =  $("#sn_table").dataTable({  
			 		"language":lang,
		            "ajax" : requestUrl,  
		            "columns" : [   
			            {"data": "id"},
			            {"data": "sn"}
		                ]  
		        	});  
		}else{
			 snTable.fnDestroy();
			// $('#sn_table').DataTable();
			 snTable =  $("#sn_table").dataTable({  
				 		"language":lang,
			            "ajax" : requestUrl,  
			            "columns" : [   
			                {"data": "id"},
			            	{"data": "sn"}
			                ]  
			        	});  
		}
		snTable.fnDraw(); 
    }
    function requestDeleteSn(devName,dateTime){
    	dateTime =getMyDate(dateTime);
    	var requestUrl = "${baseURL }/device/storage/delectInput"+"?devName="+devName+"&editTime="+dateTime;
			$.ajax({
				url:requestUrl,
				type:'get',
				dataType:'json',
				success:function(jsons){
					//alert(jsons);
					var result = jsons.result;
					if(result == 200){
						alert("删除成功");
						window.location.reload();
					}else if(result == 300){
						alert("删除失败");
						window.location.reload();
					}
					//alert(json);
					
				},
				error:function(){
					
					alert("请求错误");
				}
			});
    }
    $("#searchSure").click(function(){

    	var ckdateResult = ckdate();
    	if(ckdateResult){
    		var beginDate = $("#beginDate").val();
    		var endDate = $("#endDate").val();
    		//alert(beginDate);
    		requestCondition(beginDate,endDate,userType,userName);
    	}
    });
	$(document).ready(function() {
		userType ='${sessionScope.WtUserInfo.userType}';
		userStatus='${sessionScope.WtUserInfo.userStatus}';
		userName='${sessionScope.WtUserInfo.userName}';
		if(userType == 'worker' && userType != null){
			requestWorkerDataTableData(userType,userName);
		}else if(userType == 'department' && userType != null){
			requestDataTableData(userType,userName);
		}else if(userType == 'ADMIN' && userType !=null ){
			requestDataTableData(userType,userName);
		}
		
		requestInputNum(userType,userName,userStatus);
		ckdate();
	});
	function today() {
			var today = new Date();
			var h = today.getFullYear();
			var m = today.getMonth() + 1;
			var d = today.getDate();
			return h + "-" + m + "-" + d;
	}

	document.getElementById("beginDate").value = today();
	document.getElementById("endDate").value = today();
</script>