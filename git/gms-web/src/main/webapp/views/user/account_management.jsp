<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@include file="../../common/taglib/taglib.jsp" %>

	<head>
		<title>账号分配</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=all" rel="stylesheet" type="text/css">
		<link href="${baseURL }/common/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="${baseURL }/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css">
		<link href="${baseURL }/common/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	
		<link href="${baseURL }/common/assets/global/plugins/datatables/datatables.css" rel="stylesheet" type="text/css">
		<link href="${baseURL }/common/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css">
		<link href="${baseURL }/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css">

		<link href="${baseURL }/common/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css">
		<link href="${baseURL }/common/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css">
		<link href="${baseURL }/common/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css">
		<link href="${baseURL }/common/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color">
		<link href="${baseURL }/common/assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css">
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
					<li class="nav-item active open">
						<a href="" class="nav-link nav-toggle">
							<i class="icon-users"></i>
							<span class="title">用戶管理</span>
							<span class="arrow"></span>
							<span class="selected"></span>
						</a>
						<ul class="sub-menu">
						<shiro:hasPermission name="账号管理">
							<li class="nav-item active">
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
							<li class="nav-item ">
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
					<div class="page-bar">
						<ul class="page-breadcrumb">
							<li>
								<a href="index.html">用户管理</a>
								<i class="fa fa-angle-right"></i>
							</li>
							<li>
								<span>账号管理</span>
							</li>
						</ul>
					</div>
					<div class="row bg_color" style="padding-top: 10px;">
						<div class="col-md-12">
							<div class="portlet light portlet-fit bordered">
								<div class="portlet-title">
									<div class="caption">
										<i class=" icon-layers font-green"></i>
										<span class="caption-subject font-green bold uppercase">账号登录详情</span>
									</div>
								</div>
								<div class="portlet-body">
									<table id="_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
										<thead width="100%">
											<tr>
												<th>账号</th>
												<th>类型</th>
												<th>分配日期</th>
												<th>最后登录时间</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
					<!-- END DASHBOARD STATS 1-->
				</div>
				<!-- END CONTENT BODY -->
			</div>
		</div>
		<!-- 底部内容开始-->
		<%@include file="../system/foot.jsp" %>
		<!-- 底部内容结束-->
		<!--[if lt IE 9]>
		<script src="../assets/global/plugins/respond.min.js"></script>
		<script src="../assets/global/plugins/excanvas.min.js"></script> 
		<![endif]-->
		<!-- BEGIN CORE PLUGINS -->
		<script src="${baseURL }/common/assets/global/plugins/jquery.min.js" type="text/javascript">
		</script>
		<script src="${baseURL }/common/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
		<!-- END CORE PLUGINS -->
		<!-- BEGIN PAGE LEVEL PLUGINS -->
		<script src="${baseURL }/common/assets/global/plugins/moment.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
		<!-- END PAGE LEVEL PLUGINS -->
		<script src="${baseURL }/common/assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/scripts/datatable.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/scripts/datatable.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/datatables/datatables.all.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/scripts/app.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
		<script type="text/javascript">
		var account_manage_datatables;
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
			function requestAllDataTableData(){
					var requestUrl = "${baseURL }/accountManage/queryAll";
					
					if(account_manage_datatables == undefined || account_manage_datatables == "undefined" || account_manage_datatables == null){
						
						account_manage_datatables =  $("#_table").dataTable({  
						 		"language":lang,
					            "ajax" : requestUrl,  
					            "columns" : [   
					                {"data" : "userName"},  
					                {"data" : "role"},  
					                {"data" : function(obj){ return getMyDate(obj.assignmentDate)}},
					                {"data" : function(obj){ return getMyDate(obj.loginDate)}},
					                
					                ]  
					        	});  
							

					}else{
						 account_manage_datatables.fnDestroy();
						 account_manage_datatables =  $("#_table").dataTable({  
							 		"language":lang,
						            "ajax" : requestUrl,  
						           "columns" : [   
					                {"data" : "userName"},  
					                {"data" : "role"},  
					                {"data" : function(obj){ return getMyDate(obj.assignmentDate)}},
					                {"data" : function(obj){ return getMyDate(obj.loginDate)}},
					                
					                ]  
					        	});  
						 

					}
					account_manage_datatables.fnDraw(); 
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
	};
	$(document).ready(function() {
		requestAllDataTableData();
	});

			
		</script>
	</body>

</html>