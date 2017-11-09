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
		<link href="${baseURL }/common/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css">
		<!-- modal样式-->
		<link href="${baseURL }/common/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
		<link href="${baseURL }/common/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />

		<!--datatable样式-->
		<link href="${baseURL }/common/assets/global/plugins/datatables/datatables.css" rel="stylesheet" type="text/css">
		<link href="${baseURL }/common/assets/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css">
		<link href="${baseURL }/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css">

		<link href="${baseURL }/common/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css">
		<link href="${baseURL }/common/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css">
		<link href="${baseURL }/common/assets/pages/css/login.min.css" rel="stylesheet" type="text/css" />
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
					
					<!--账号详情表格开始-->
					<div class="row bg_color" style="padding-top: 10px;">
						<div class="col-md-12">
							<div class="portlet light portlet-fit bordered">
								<div class="portlet-title">
									<div class="caption">
										<i class=" icon-layers font-green"></i>
										<span class="caption-subject font-green bold uppercase">账号详情</span>
										
									</div>
								</div>
								<div class="portlet-body">
								<shiro:hasPermission name="账号添加">
								<button class="btn green" data-target='#_add_account' data-toggle='modal'>添加账号</button>
								</shiro:hasPermission>
									<div style="margin-top:20px ;">
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
					<!--账号详情表格结束-->
				</div>
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

						</div>
					</div>
				</div>
			</div>
		</div>
		<!--模态框结束-->
		
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
		<!--模态框开始-->
		<div id="_add_account" class="modal fade modal-scroll" tabindex="-1" data-replace="true" aria-hidden="true" style="display: none; margin-top: 0px;">
			<div class="modal-header">
			
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h3 class="form-title font-green" style="text-align: center;">添加账号</h3>

			</div>
			<div class="modal-body">
				
					<div class="form-group">
						<div class="col-md-2" style="margin-top: 5px;">
							<strong>用户名</strong>
						</div>
						<div class="col-md-10">
							<input id="username_input" class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" name="username" style="width: 350px;" />
						</div>
					</div>
					<label class="control-label "></label>
					<div class="form-group">
						<div class="col-md-2" style="margin-top: 5px;">
							<strong>密码</strong>
						</div>
						<div class="col-md-10">
							<input id="password_input" class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="userpassword" style="width: 350px;" />
						</div>
					</div>
					<label class="control-label "></label>
					<div class="form-group">
						<div class="col-md-2" style="margin-top: 5px;">
							<strong>身份</strong>
						</div>
						<div class="col-md-10">
							<select id="identify_select" name="country" class="form-control" style="width: 350px;" onChange="GaiBian(this)">
								<option value="manager">管理员</option>
								<option value="department">事业部</option>
								<option value="worker">工作人员</option>
							</select>
						</div>
					</div>
					<label class="control-label "></label>
					<div id="permission_department_select" class="form-group">
						<div class="col-md-2" style="margin-top: 5px;">
							<strong>事业部</strong>
						</div>
						<div class="col-md-10">
							<select id="department_select" name="country" class="form-control" style="width: 350px;">
								<option value="">--请选择--</option>
								
							</select>
						</div>
					</div>
					<div id="permission_web" class="form-group">
						<div class="col-md-4" style="margin-top: 25px;">
							<div class="  col-md-12">
								<div class="checkbox">
									<label>
								        <input type="checkbox" name="web_select" value="1">首页
								    </label>
								</div>
							</div>
							<div class="col-md-12">
								<div class="checkbox">
									<label>
								        <input type="checkbox" name="web_select" value="4">设备活跃
								    </label>
								</div>
							</div>
							<div class="col-md-12">
								<div class="checkbox">
									<label>
								          <input type="checkbox" name="web_select" value="7">机型统计
								        </label>
								</div>
							</div>
							<div class="col-md-12">
								
							</div>
						</div>
						<div class="col-md-4" style="margin-top: 25px;">
							<div class=" col-md-12">
								<div class="checkbox">
									<label>
								        <input type="checkbox" name="web_select" value="2">地区分布
								    </label>
								</div>
							</div>
							<div class=" col-md-12">
								<div class="checkbox">
									<label>
							            <input type="checkbox" name="web_select" value="5">设备入库
							        </label>
								</div>
							</div>
							<div class=" col-md-12">
								<div class="checkbox">
									<label>
								          <input type="checkbox" name="web_select" value="11">后台版本
								    </label>
								</div>
							</div>
							<div class=" col-md-12">
								
							</div>
						</div>
						<div class="col-md-4" style="margin-top: 25px;">
							<div class=" col-md-12">
								<div class="checkbox">
									<label>
								        <input type="checkbox" name="web_select" value="3">设备激活
								    </label>
								</div>
							</div>
							<div class=" col-md-12">
								<div class="checkbox">
									<label>
							            <input type="checkbox" name="web_select" value="6">报表统计
							        </label>
								</div>
							</div>
							<div class=" col-md-12">
								<div class="checkbox">
									<label>
							            <input type="checkbox" name="web_select" value="8">设备统计
							        </label>
								</div>
							</div>
						</div>
					</div>
					<div id="permission_sniput" class="form-group">
						<div class="col-md-6" style="margin-top: 25px;">
							<div class=" col-md-12">
								<div class="checkbox">
									<label>
								        <input type="checkbox" name="web_select" value="13">导入SN码权限
								    </label>
								</div>
							</div>
						</div>
					</div>
					<label class="control-label "></label>
					<div  style="margin-top: 10px;">
						<button  id="adduser" class="btn green btn-block uppercase" style="width: 240px;margin: 0 auto;padding-top: 10px;">添加</button>
					</div>
				
			</div>
			
		</div>
		<!-- 底部内容开始-->
		<%@include file="../system/foot.jsp" %>
		<!-- 底部内容结束-->
		<!--模态框结束-->
		<!--[if lt IE 9]>
		<script src="../assets/global/plugins/respond.min.js"></script>
		<script src="../assets/global/plugins/excanvas.min.js"></script> 
		<![endif]-->
		<script src="${baseURL }/common/assets/global/plugins/jquery.min.js" type="text/javascript">
		</script>
		<script src="${baseURL }/common/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/moment.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/scripts/datatable.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/datatables/datatables.all.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/scripts/app.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
		<script type="text/javascript">
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
			var pessmission_datatables;
			$(document).ready(function() {
				
					
				
				requestAllDataTableData();

				//新增表格点击事件
				requestDepartment();

				$('#permission_sniput').hide();
				$('#permission_web').hide();
				$('#permission_department_select').hide();
				$('#permission_sniput').hide();

			});
			function requestAllDataTableData(){
					var requestUrl = "${baseURL }/permission/assignment/account";
					
					if(pessmission_datatables == undefined || pessmission_datatables == "undefined" || pessmission_datatables == null){
						
						pessmission_datatables =  $("#_all_account_table").dataTable({  
						 		"language":lang,
					            "ajax" : requestUrl,  
					            "columns" : [   
					                {"data" : "id"},  
					                {"data" : "userType"},  
					                {"data" : "userName"},
					                {"data" : "userPwd"},
					                {"defaultContent": "<button id='delete_user' class='btn btn-success'>删除</button>"}
					                ]  
					        	});  
							$('#_all_account_table tbody').on( 'click', '#delete_user', function () {
				               var data = $('#_all_account_table').DataTable().row($(this).parents('tr')).data();
				                var deluserUrl = "${baseURL }/permission/assignment/delUser?userName="+data.userName+"&userType="+data.userType;
				               // alert(data.userType );
				                $.ajax({
									url:deluserUrl,
									type:'get',
									dataType:'json',
									success:function(jsons){
										//alert(jsons);
										//var json = jsons;
										//alert(json);
										//alert
										var json = jsons.result;
										if(json == 200){
											$("#_add_account").modal('hide');
											alert("删除成功");
											window.location.reload();
										}
										
									},
									error:function(){
										$("#_add_account").modal('hide');
										alert("删除失败，请刷新后再次尝试"); 
										//$("#department_select").html("请求错误，请刷新");
									}
								});
            				} );

					}else{
						 pessmission_datatables.fnDestroy();
						 pessmission_datatables =  $("#_all_account_table").dataTable({  
							 		"language":lang,
						            "ajax" : requestUrl,  
						            "columns" : [   
						                {"data" : "id"},  
						                {"data" : "userType"},  
						                {"data" : "userName"},
						                {"data" : "userPwd"},
						                {"defaultContent": "<button id='delete_user' class='btn btn-success'>删除</button>"}
						                ]  
						        	});  
						 $('#_all_account_table tbody').on( 'click', '#delete_user', function () {
				                var data = $('#_all_account_table').DataTable().row($(this).parents('tr')).data();
				                var deluserUrl = "${baseURL }/permission/assignment/delUser?userName="+data.userName+"&userType="+data.userType;
				                //alert(data.userType );
				                $.ajax({
									url:deluserUrl,
									type:'get',
									dataType:'json',
									success:function(jsons){
										//alert(jsons);
										//var json = jsons;
										//alert(json);
										//alert
										var json = jsons.result;
										if(json == 200){
											$("#_add_account").modal('hide');
											alert("删除成功");
											window.location.reload();
										}
									},
									error:function(){
										$("#_add_account").modal('hide');
										alert("删除失败，请刷新后再次尝试"); 
										//$("#department_select").html("请求错误，请刷新");
									}
								});
            				} );

					}
					pessmission_datatables.fnDraw(); 
				}
			function GaiBian(dat) {
				if(dat.options[dat.selectedIndex].text == '事业部') {
					//alert(dat.options[dat.selectedIndex].text);
					$('#permission_department_select').show();
					$('#permission_sniput').hide();
					$('#permission_sniput').hide();
					$('#permission_web').hide();
				}
				if(dat.options[dat.selectedIndex].text == '管理员') {
					$('#permission_web').hide();
					$('#permission_department_select').hide();
					$('#permission_sniput').hide();
				}
				if(dat.options[dat.selectedIndex].text == '工作人员') {
					$('#permission_sniput').show();
					$('#permission_web').show();
					$('#permission_department_select').show();
				}
			}
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


			$("#adduser").click(function(){
				var identify_select = $('#identify_select').val();
				var username = $('#username_input').val();
				var password = $('#password_input').val();
				var requestUrl ="${baseURL }/permission/assignment/adduser";
				var department_select = $('#department_select').val();
				if(username == null || username == ''){
					alert("请输入用户名");
						return ;
				}
				if(password == null || password ==''){
					alert("请输入密码");
						return ;
				}
				if(identify_select == 'manager'){
					requestUrl = requestUrl +"?username="+username+"&password="+password+"&department=admin"+"&role=ADMIN"+"&boxdata=";
					$.ajax({
						url:requestUrl,  
				        type:'get',  

				        dataType:'json',  
				        success:function(jsons){  
				        	var result = jsons.result;
				           if(result == 200){
				           	$("#_add_account").modal('hide');
								alert("添加成功");
				        	}else if(result == 300){
				        		alert("用户名已存在");
				        	}
				        	window.location.reload();
				        },  
				        error:function(){  
				            alert("添加失败，请刷新后再次尝试"); 
				            window.location.reload();
				        }  
					});

				}else  if(identify_select == 'department'){
					if(department_select == null || department_select == ''){
						alert("请选择部门");
						return;
					}
					requestUrl = requestUrl +"?username="+username+"&password="+password+"&role=department"+"&department="+department_select+"&boxdata=";
					$.ajax({
						url:requestUrl,  
				        type:'get',  

				        dataType:'json',  
				        success:function(jsons){  
				        	var result = jsons.result;
				        	if(result == 200){
				        		$("#_add_account").modal('hide');
								alert("添加成功");
				        	}else if(result == 300){
				        		alert("用户名已存在");
				        	}
				        	window.location.reload();
				            
				        },  
				        error:function(){  
				            alert("添加失败，请刷新后再次尝试"); 
				            window.location.reload();
				        }  
					});
				}else if(identify_select == 'worker'){
					if(department_select == null || department_select == ''){
						alert("请选择部门");
						return;
					}
					var boxdata = new Array();
					boxdata = checkData();
					if(boxdata == '' || boxdata == null){
						alert("请分配权限");
						return ;
					}
					requestUrl = requestUrl +"?username="+username+"&password="+password+"&role=worker"+"&department="+department_select+"&boxdata="+boxdata;
					console.log(requestUrl);
					$.ajax({
						url:requestUrl,  
				        type:'get',    
				        dataType:'json',
				        success:function(jsons){ 
				        	var result = jsons.result;
				        	if(result == 200){
				        		$("#_add_account").modal('hide');
								alert("添加成功");
				        	}else if(result == 300){
				        		alert("用户名已存在");
				        	} 
				            window.location.reload();
				        },  
				        error:function(){  
				            alert("添加失败，请刷新后再次尝试"); 
				            window.location.reload();
				        }  
					});
					
				}
			});
			
			 function checkData(){    
			  var obj=document.getElementsByName('web_select');  //选择所有name="'test'"的对象，返回数组    
			  //取到对象数组后，我们来循环检测它是不是被选中    
			  var boxIds = new Array();    
			  for(var i=0; i<obj.length; i++){    
			    if(obj[i].checked) boxIds.push(obj[i].value);  //如果选中，将value添加到变量s中    
			  }    
			  //那么现在来检测s的值就知道选中的复选框的值了    
			  return boxIds;
			}    
		</script>
	</body>

</html>