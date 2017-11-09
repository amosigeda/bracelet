<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@include file="../../common/taglib/taglib.jsp" %>
<head>
<title>日志监控</title>
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
			<%@include file="../system/left.jsp" %>
			<div class="page-content-wrapper">
				<div class="page-content">
					<!-- 右侧头部开始 -->
					<div class="page-bar">
						<ul class="page-breadcrumb">
							<li>
								<a href="index.html">用户管理</a>
								<i class="fa fa-angle-right"></i>
							</li>
							<li>
								<span>日志监控</span>
							</li>
						</ul>
					</div>
					<!-- 右侧头部结束 -->
					<!-- 实时监控开始-->
					<div class="row bg_color" style="padding-top: 10px;">
						<div class="col-md-12">
							<div class="portlet light portlet-fit bordered">
								<div class="portlet-title">
									<div class="caption">
										<i class=" icon-layers font-green"></i>
										<span class="caption-subject font-green bold uppercase">实时监控</span>
									</div>
								</div>
								<div class="portlet-body">
									<div style="height: 500px;width: 100%;border: #010100 1px solid;"></div>
								</div>
							</div>
						</div>
					</div>
					<!--实时监控结束-->
					<!--登录详情开始-->
					<div class="row bg_color">
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
					<!-- 登录详情结束-->
				</div>
			</div>
		</div>
		<!-- 底部内容开始-->
		<%@include file="../system/foot.jsp" %>
		<!-- 底部内容结束-->
		<!--[if lt IE 9]>
		<script src="${baseURL }/common/assets/global/plugins/respond.min.js"></script>
		<script src="${baseURL }/common/assets/global/plugins/excanvas.min.js"></script> 
		<![endif]-->
		<!-- BEGIN CORE PLUGINS -->
		<script src="${baseURL }/common/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/moment.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
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
				/*var table = $('#_table').DataTable({
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
						}
					}
				});
				$('#_table tbody').on('click', 'button', function() {
					var data = table.row($(this).parents('tr')).data();
					$('#_table').DataTable();
					alert(data[0] + "'s salary is: " + data[5]);
				});*/
				requestLog();
			});
			function requestLog(){
		    	//dateTime =getMyDate(dateTime);
		    	var requestUrl = "${baseURL }/log/monitoring/log_monitoring";
					$.ajax({
						url:requestUrl,
						type:'get',
						
						success:function(jsons){
							//alert(jsons);
							/*var result = jsons.data;*/
							console.log(jsons);
							//alert(json);
							
						},
						error:function(){
							
							alert("请求错误");
						}
					});
		    }
		</script>
	</body>

</html>