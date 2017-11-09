<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@include file="../../common/taglib/taglib.jsp" %>
<head>
<title>账户管理</title>

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
<link href="${baseURL }/common/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />

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
						<span class="username username-hide-on-mobile"> 超级管理员</span>
						<i></i>
					</a>
				</li>
				<li class="dropdown dropdown-user">
					<a href="modify_password.html" class="dropdown-toggle">
						<span class="username username-hide-on-mobile" style="color: #79869a;"> 修改密码</span>
						<i></i>
					</a>
				</li>
				<li class="dropdown dropdown-quick-sidebar-toggler">
					<a href="login.html" class="dropdown-toggle">
						<i class="icon-logout"></i>
					</a>
				</li>
			</ul>
		</div>
		<!--网页头部右侧结束-->
	</div>
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
							<li class="nav-item ">
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
							<li class="nav-item active">
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
	<!--模态框开始-->
	<div id="_all_account" class="modal container fade" tabindex="-1">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title"></h4>
		</div>
		<div class="modal-body">
			<div class="col-md-12">
				<div class="portlet light portlet-fit bordered">
					<div class="portlet-title">
						<div class="caption">
							<i class=" icon-layers font-green"></i>
							<span class="caption-subject font-green bold uppercase">账号详情</span>
						</div>
					</div>
					<div class="portlet-body">
						<table id="_all_account_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead width="100%">
								<tr>
									<th>序号</th>
									<th>权限</th>
									<th>账号</th>
									<th>密码</th>
									<th>操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--模态框结束-->
	<!-- /.modal -->
	<div class="modal fade " id="_add_account" tabindex="-1"  aria-hidden="true" >
			<div class="modal-content" >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body"> 
				<!-- BEGIN LOGIN FORM -->
				<div class="content" >
						<form class="login-form" action="" method="post">
							<h3 class="form-title font-green" style="text-align: center;">添加账号</h3>
							<div class="alert alert-danger display-hide">
								<button class="close" data-close="alert"></button>
								<span> 请输入用户名或密码！ </span>
							</div>
							<div class="form-group">
								<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
								<label class="control-label visible-ie8 visible-ie9">用户名</label>
								<input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" name="username" /> </div>
							<div class="form-group">
								<label class="control-label visible-ie8 visible-ie9">密码</label>
								<input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="password" /> </div>
							<div class="form-group">
								<label class="control-label visible-ie8 visible-ie9">登录身份</label>
								<select name="country" class="form-control">
									<option value="">事业部</option>
									<option value="AF">工作人员</option>
									<option value="AL">管理员</option>
								</select>
							</div>
							<div class="form-actions">
								<button type="submit" class="btn green btn-block uppercase">添加</button>
							</div>
							
						</form>
						<!-- END LOGIN FORM -->
					</div>
				</div>
				
			</div>
			<!-- /.modal-content -->
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!--模态框开始-->
	<div id="" class="modal container fade " tabindex="-1">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title"></h4>
		</div>
		<div class="modal-body">
			<div class="col-md-12">
				<div class="portlet light portlet-fit bordered">
					<div class="portlet-title">
						<div class="caption">
							<i class=" icon-layers font-green"></i>
							<span class="caption-subject font-green bold uppercase">账号详情</span>
						</div>
					</div>
					<div class="portlet-body">
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--模态框结束-->
	<!-- 底部内容开始-->
	<div class="page-footer">
		<div class="page-footer-inner"> 2017 &copy; Water World.
			<a href="" title="" target="_blank">Water World</a>			
		</div>
		<div class="scroll-to-top">
			<i class="icon-arrow-up"></i>
		</div>
	</div>
	<!-- 底部内容结束-->
	<!--[if lt IE 9]>
	<script src="${baseURL }/common/assets/global/plugins/respond.min.js"></script>
	<script src="${baseURL }/common/assets/global/plugins/excanvas.min.js"></script> 
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
		$(document).ready(function() {
			var table = $('#_table').DataTable({
				"ajax": "../data/2.txt",
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

			$('#_table tbody').on('click', 'button', function() {
				var data = table.row($(this).parents('tr')).data();
				$('#_table').DataTable();
				alert(data[0] + "'s salary is: " + data[5]);
			});

			var table1 = $('#_all_account_table').DataTable({
				"ajax": "../data/1.txt",
				"columnDefs": [{
					"targets": -1,
					"data": null,
					"defaultContent": "<button class='btn btn-success'>删除</button>"
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

			//新增表格点击事件

			$('#_all_account_table tbody').on('click', 'button', function() {
				var data = table.row($(this).parents('tr')).data();

			});

		});
	</script>
</body>
</html>
