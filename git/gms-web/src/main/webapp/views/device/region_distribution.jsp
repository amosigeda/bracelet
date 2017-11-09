<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@include file="../../common/taglib/taglib.jsp" %>
<head>
<title>地区分布</title>

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
<link href="${baseURL }/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css" />

<link href="${baseURL }/common/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
<link href="${baseURL }/common/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }/common/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="${baseURL }/common/assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css" />

</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
	<!--网页头部开始-->
	<%@include file="../system/top.jsp" %>
	<!--网页头部结束-->
	<div class="clearfix"> </div>
	<!-- 内容开始 -->
	<div class="page-container">
		<!-- 左侧菜单开始-->
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
							<li class="nav-item active">
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
			<!-- 主体内容开始 -->
			<div class="page-content">
				<!-- 主体头部开始-->
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li>
							<a href="index.html">设备</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span>地区分布</span>
						</li>
					</ul>
				</div>
				<!-- 主体头部结束-->
				<!--tab内容开始-->
				<div class="row bg_color">
					<div class="col-md-12">
						<div class="portlet">
							<div class="portlet-body">
								<div class="tabbable-bordered" id="_tab">
									<ul class="nav nav-tabs">
										<li class="active">
											<a href="#tab_add" data-toggle="tab" aria-expanded="true"> 激活</a>
										</li>
										<li class="">
											<a href="#tab_active" data-toggle="tab" aria-expanded="false"> 活跃</a>
										</li>
									</ul>
									<!--内容部分开始-->
									<div class="tab-content ">
										<!--新增tab开始-->
										<div class="tab-pane  active" id="tab_add">
											<!--地区分布地图开始-->
											<div class="row ">
												<div class="col-md-12">
													<div class="portlet light portlet-fit bordered">
														<div class="portlet-title">
															<div class="caption">
																<i class=" icon-globe font-green"></i>
																<span class="caption-subject font-green bold uppercase">设备激活全球分布图</span>
															</div>
														</div>
														<div class="portlet-body">
															<button id="btn_today_activation"  type="button" class="btn btn-success">今日</button>
															<!-- <button id="btn_yestoday_activation" type="button" class="btn btn-success">昨日</button> -->
															<button id="btn_sevenday_activation" type="button" class="btn btn-success">近7天</button>
															<button id="btn_thirty_activation" type="button" class="btn btn-success">近30天</button>
															<div id="_add_map" style="height:500px;padding-top: 10px;"></div>
														</div>
													</div>
												</div>
											</div>
											<!--地区分布地图结束-->
											<!--底部表格开始-->
											<div class="row ">
												<div class="col-md-12">
													<div class="portlet light portlet-fit bordered">
														<div class="portlet-title">
															<div class="caption">
																<i class=" icon-layers font-green"></i>
																<span class="caption-subject font-green bold uppercase">设备激活地区分布统计表</span>
															</div>
														</div>
														<div class="portlet-body">
															<table id="activation_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
																<thead width="100%">
																	<tr>
																		
																		<th>国家</th>
																		<th>新增量</th>
																		<th>百分比</th>
																		<th>机型详情</th>
																	</tr>
																</thead>
															</table>
														</div>
													</div>
												</div>
											</div>
											<!--底部表格结束-->
										</div>
										<!--新增tab技术-->
										<!--活跃tab开始-->
										<div class="tab-pane" id="tab_active">
											<!--地区分布地图开始-->
											<div class="row">
												<div class="col-md-12">
													<div class="portlet light portlet-fit bordered">
														<div class="portlet-title">
															<div class="caption">
																<i class=" icon-globe font-green"></i>
																<span class="caption-subject font-green bold uppercase">设备活跃地区分布图</span>
															</div>
														</div>
														<div class="portlet-body">
															<button id="btn_today" type="button" class="btn btn-success">今日</button>
															<!-- <button id="btn_yestoday" type="button" class="btn btn-success">昨日</button> -->
															<button id="btn_sevenday" ype="button" class="btn btn-success">近7天</button>
															<button id="btn_thirty" type="button" class="btn btn-success">近30天</button>
															 <div id="_active_map" style="height:500px;padding-top: 10px;"></div> 
														</div>
													</div>
												</div>
											</div>
											<!--地区分布地图结束-->
											<!--底部表格开始-->
											<div class="row">
												<div class="col-md-12">
													<div class="portlet light portlet-fit bordered">
														<div class="portlet-title">
															<div class="caption">
																<i class=" icon-layers font-green"></i>
																<span class="caption-subject font-green bold uppercase">设备活跃地区分布统计表</span>
															</div>
															
														</div>
														<div class="portlet-body">
															<table id="active_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
																<thead width="100%">
																	<tr>
																		
																		<th>国家</th>
																		<th>活跃量</th>
																		<th>百分比</th>
																		<th>机型详情</th>
																	</tr>
																</thead>
															</table>
														</div>
													</div>
												</div>
											</div>
											<!--底部表格结束-->
										</div>
										<!--活跃tab结束-->
									</div>
									<!--内容部分结束-->
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--tab内容结束-->
			</div>
			<!-- 主体内容结束-->
		</div>
		<!-- 右侧内容结束 -->
		<a href="javascript:;" class="page-quick-sidebar-toggler">
			<i class="icon-login"></i>
		</a>
	</div>
	<!-- 内容结束-->
	<!-- 底部内容开始-->
	<%@include file="../system/foot.jsp" %>
	<!-- 底部内容结束-->
	<!--模态框开始-->
	<div id="_country_add_detail" class="modal container fade" tabindex="-1">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">国家新增详情</h4>
		</div>
		<div class="modal-body">
			<div class="col-md-12">
				<div class="portlet light portlet-fit bordered">
					<div class="portlet-title">
						<div class="caption">
							<i class=" icon-layers font-green"></i>
							<span class="caption-subject font-green bold uppercase">国家新增机型详情</span>
						</div>
					</div>
					<div class="portlet-body">
						<table id="queryActivationDev_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead width="100%">
								<tr>
								
									<th>机型</th>
									<th>活跃量</th>
									<th>百分比</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--模态框结束-->
	<!--模态框开始-->
	<div id="_country_active_detail" class="modal container fade" tabindex="-1">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">国家活跃详情</h4>
		</div>
		<div class="modal-body">
			<div class="col-md-12">
				<div class="portlet light portlet-fit bordered">
					<div class="portlet-title">
						<div class="caption">
							<i class=" icon-layers font-green"></i>
							<span class="caption-subject font-green bold uppercase">XX国家活跃机型详情</span>
						</div>
					</div>
					<div class="portlet-body">
						<table id="queryDev_table" class="table table-striped table-bordered" cellspacing="0" width="100%">
							<thead width="100%">
								<tr>
									
									<th>机型</th>
									<th>活跃量</th>
									<th>百分比</th>
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
	<script src="../assets/global/plugins/respond.min.js"></script>
	<script src="../assets/global/plugins/excanvas.min.js"></script> 
	<![endif]-->
	<!-- BEGIN CORE PLUGINS -->
	<script src="${baseURL }/common/js/echarts_.js" type="text/javascript"></script>
	<script src="${baseURL }/common/js/world.js" type="text/javascript"></script>
	<!--<script src="${baseURL }/common/js/echarts-map.js" type="text/javascript"></script>-->
	<script src="${baseURL }/common/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="${baseURL }/common/assets/global/plugins/datatables/datatables.all.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN THEME GLOBAL SCRIPTS -->
	<script src="${baseURL }/common/assets/global/scripts/app.min.js" type="text/javascript"></script>
	<!-- END THEME GLOBAL SCRIPTS -->
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
	<!-- END PAGE LEVEL SCRIPTS -->
	<!-- BEGIN THEME LAYOUT SCRIPTS -->
	<script src="${baseURL }/common/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
	<script type="text/javascript">

		//新增表格请求后台数据
		var option;
		var active_select = false;
		var value3 = [
			{name:'美国',value:87},
			{name:'中国',value:38},
			{name:'英国',value:40},
			{name:'瑞士',value:21},
			{name:'秘鲁',value:21},
			{name:'德国',value:15},
			{name:'意大利',value:13},
			{name:'澳大利亚',value:11},
			{name:'玻利维亚',value:11},
			{name:'西班牙',value:9},
			{name:'印度',value:7},
			{name:'日本',value:7},
			{name:'阿根廷',value:6},
			{name:'荷兰',value:6},
			{name:'法国',value:5},
			{name:'土耳其',value:4},
			{name:'以色列',value:4},
			{name:'比利时',value:4},
			{name:'吉尔吉斯斯坦',value:4},
			{name:'墨西哥',value:4},
			{name:'哥斯达黎加',value:3},
			{name:'奥地利',value:3},
			{name:'瑞典',value:3},
			{name:'丹麦',value:3},
			{name:'尼加拉瓜',value:2},
			{name:'芬兰',value:2},
			{name:'尼日利亚',value:2},
			{name:'捷克共和国',value:2},
			{name:'韩国',value:2},
			{name:'挪威',value:2},
			{name:'新加坡',value:2},
			{name:'新西兰',value:1},
			{name:'科威特',value:1},
			{name:'塞尔维亚',value:1},
			{name:'尼泊尔',value:1},
			{name:'菲律宾',value:1},
			{name:'葡萄牙',value:1},
			{name:'希腊',value:1},
			{name:'巴基斯坦',value:1},
			{name:'古巴',value:1},
			{name:'巴西',value:1},
			{name:'冰岛',value:1},
			{name:'埃及',value:1},
			{name:'乌兹别克斯坦',value:1},
			{name:'约旦',value:1},
			{name:'波兰',value:1},
			{name:'哥伦比亚',value:1},
			{name:'萨尔瓦多',value:1},
			{name:'塞浦路斯',value:1},
			{name:'俄罗斯',value:1},
			{name:'沙特阿拉伯',value:1},
			{name:'坦桑尼亚',value:1},
			];
			var values=[];
			var values_activation=[];
			var values2;
			var nameMap = {
				'United States':'美国',
			    'Afghanistan':'阿富汗',
				'Singapore':'新加坡',
			    'Angola':'安哥拉',
			    'Albania':'阿尔巴尼亚',
			    'United Arab Emirates':'阿联酋',
			    'Argentina':'阿根廷',
			    'Armenia':'亚美尼亚',
			    'French Southern and Antarctic Lands':'法属南半球和南极领地',
			    'Australia':'澳大利亚',
			    'Austria':'奥地利',
			    'Azerbaijan':'阿塞拜疆',
			    'Burundi':'布隆迪',
			    'Belgium':'比利时',
			    'Benin':'贝宁',
			    'Burkina Faso':'布基纳法索',
			    'Bangladesh':'孟加拉国',
			    'Bulgaria':'保加利亚',
			    'The Bahamas':'巴哈马',
			    'Bosnia and Herzegovina':'波斯尼亚和黑塞哥维那',
			    'Belarus':'白俄罗斯',
			    'Belize':'伯利兹',
			    'Bermuda':'百慕大',
			    'Bolivia':'玻利维亚',
			    'Brazil':'巴西',
			    'Brunei':'文莱',
			    'Bhutan':'不丹',
			    'Botswana':'博茨瓦纳',
			    'Central African Republic':'中非共和国',
			    'Canada':'加拿大',
			    'Switzerland':'瑞士',
			    'Chile':'智利',
			    'China':'中国',
			    'Ivory Coast':'象牙海岸',
			    'Cameroon':'喀麦隆',
			    'Democratic Republic of the Congo':'刚果民主共和国',
			    'Republic of the Congo':'刚果共和国',
			    'Colombia':'哥伦比亚',
			    'Costa Rica':'哥斯达黎加',
			    'Cuba':'古巴',
			    'Northern Cyprus':'北塞浦路斯',
			    'Cyprus':'塞浦路斯',
			    'Czech Republic':'捷克共和国',
			    'Germany':'德国',
			    'Djibouti':'吉布提',
			    'Denmark':'丹麦',
			    'Dominican Republic':'多明尼加共和国',
			    'Algeria':'阿尔及利亚',
			    'Ecuador':'厄瓜多尔',
			    'Egypt':'埃及',
			    'Eritrea':'厄立特里亚',
			    'Spain':'西班牙',
			    'Estonia':'爱沙尼亚',
			    'Ethiopia':'埃塞俄比亚',
			    'Finland':'芬兰',
			    'Fiji':'斐',
			    'Falkland Islands':'福克兰群岛',
			    'France':'法国',
			    'Gabon':'加蓬',
			    'United Kingdom':'英国',
			    'Georgia':'格鲁吉亚',
			    'Ghana':'加纳',
			    'Guinea':'几内亚',
			    'Gambia':'冈比亚',
			    'Guinea Bissau':'几内亚比绍',
			    'Equatorial Guinea':'赤道几内亚',
			    'Greece':'希腊',
			    'Greenland':'格陵兰',
			    'Guatemala':'危地马拉',
			    'French Guiana':'法属圭亚那',
			    'Guyana':'圭亚那',
			    'Honduras':'洪都拉斯',
			    'Croatia':'克罗地亚',
			    'Haiti':'海地',
			    'Hungary':'匈牙利',
			    'Indonesia':'印尼',
			    'India':'印度',
			    'Ireland':'爱尔兰',
			    'Iran':'伊朗',
			    'Iraq':'伊拉克',
			    'Iceland':'冰岛',
			    'Israel':'以色列',
			    'Italy':'意大利',
			    'Jamaica':'牙买加',
			    'Jordan':'约旦',
			    'Japan':'日本',
			    'Kazakhstan':'哈萨克斯坦',
			    'Kenya':'肯尼亚',
			    'Kyrgyzstan':'吉尔吉斯斯坦',
			    'Cambodia':'柬埔寨',
			    'South Korea':'韩国',
			    'Kosovo':'科索沃',
			    'Kuwait':'科威特',
			    'Laos':'老挝',
			    'Lebanon':'黎巴嫩',
			    'Liberia':'利比里亚',
			    'Libya':'利比亚',
			    'Sri Lanka':'斯里兰卡',
			    'Lesotho':'莱索托',
			    'Lithuania':'立陶宛',
			    'Luxembourg':'卢森堡',
			    'Latvia':'拉脱维亚',
			    'Morocco':'摩洛哥',
			    'Moldova':'摩尔多瓦',
			    'Madagascar':'马达加斯加',
			    'Mexico':'墨西哥',
			    'Macedonia':'马其顿',
			    'Mali':'马里',
			    'Myanmar':'缅甸',
			    'Montenegro':'黑山',
			    'Mongolia':'蒙古',
			    'Mozambique':'莫桑比克',
			    'Mauritania':'毛里塔尼亚',
			    'Malawi':'马拉维',
			    'Malaysia':'马来西亚',
			    'Namibia':'纳米比亚',
			    'New Caledonia':'新喀里多尼亚',
			    'Niger':'尼日尔',
			    'Nigeria':'尼日利亚',
			    'Nicaragua':'尼加拉瓜',
			    'Netherlands':'荷兰',
			    'Norway':'挪威',
			    'Nepal':'尼泊尔',
			    'New Zealand':'新西兰',
			    'Oman':'阿曼',
			    'Pakistan':'巴基斯坦',
			    'Panama':'巴拿马',
			    'Peru':'秘鲁',
			    'Philippines':'菲律宾',
			    'Papua New Guinea':'巴布亚新几内亚',
			    'Poland':'波兰',
			    'Puerto Rico':'波多黎各',
			    'North Korea':'北朝鲜',
			    'Portugal':'葡萄牙',
			    'Paraguay':'巴拉圭',
			    'Qatar':'卡塔尔',
			    'Romania':'罗马尼亚',
			    'Russia':'俄罗斯',
			    'Rwanda':'卢旺达',
			    'Western Sahara':'西撒哈拉',
			    'Saudi Arabia':'沙特阿拉伯',
			    'Sudan':'苏丹',
			    'South Sudan':'南苏丹',
			    'Senegal':'塞内加尔',
			    'Solomon Islands':'所罗门群岛',
			    'Sierra Leone':'塞拉利昂',
			    'El Salvador':'萨尔瓦多',
			    'Somaliland':'索马里兰',
			    'Somalia':'索马里',
			    'Republic of Serbia':'塞尔维亚',
			    'Suriname':'苏里南',
			    'Slovakia':'斯洛伐克',
			    'Slovenia':'斯洛文尼亚',
			    'Sweden':'瑞典',
			    'Swaziland':'斯威士兰',
			    'Syria':'叙利亚',
			    'Chad':'乍得',
			    'Togo':'多哥',
			    'Thailand':'泰国',
			    'Tajikistan':'塔吉克斯坦',
			    'Turkmenistan':'土库曼斯坦',
			    'East Timor':'东帝汶',
			    'Trinidad and Tobago':'特里尼达和多巴哥',
			    'Tunisia':'突尼斯',
			    'Turkey':'土耳其',
			    'United Republic of Tanzania':'坦桑尼亚',
			    'Uganda':'乌干达',
			    'Ukraine':'乌克兰',
			    'Uruguay':'乌拉圭',
			    'United States of America':'美国',
			    'Uzbekistan':'乌兹别克斯坦',
			    'Venezuela':'委内瑞拉',
			    'Vietnam':'越南',
			    'Vanuatu':'瓦努阿图',
			    'West Bank':'西岸',
			    'Yemen':'也门',
			    'South Africa':'南非',
			    'Zambia':'赞比亚',
			    'Zimbabwe':'津巴布韦'
			};

			option = {
			    timeline: {
			        axisType: 'category',
			            orient: 'vertical',
			            autoPlay: true,
			            inverse: true,
			            playInterval: 5000,
			            left: null,
			            right: -105,
			            top: 20,
			            bottom: 20,
			            width: 46,
			        data: ['2016',]  
			    },
			    baseOption: {
			        visualMap: {
			            max: 100,
			            calculable: true,
			            inRange: {
			                color: ['#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
			            }
			        },
			        series: [{
			            type: 'map',
			            map: 'world',
			            roam: true,
			            nameMap: nameMap
			        }]
			    },
			   options: [{
			        title: {
			            text: 'SCI国家数据频率',
			            subtext: '世界分布图',
			            left: 'center',
			            top: 'top',
			        },
			        series: {
			            data: value3
			        } 
			    }, ]
			};
			option2 = {
			    timeline: {
			        axisType: 'category',
			            orient: 'vertical',
			            autoPlay: true,
			            inverse: true,
			            playInterval: 5000,
			            left: null,
			            right: -105,
			            top: 20,
			            bottom: 20,
			            width: 46,
			        data: ['2016',]  
			    },
			    baseOption: {
			       visualMap: {
			            max: 100,
			            calculable: true,
			            inRange: {
			                color: ['#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
			            }
			        },
			        series: [{
			            type: 'map',
			            map: 'world',
			            roam: true,
			            nameMap: nameMap
			        }]
			    },
			    options: [{
			        title: {
			           
			        },
			        series: {
			            data: value3
			        } 
			    }, ]
			};
			option4 = {
			    timeline: {
			        axisType: 'category',
			            orient: 'vertical',
			            autoPlay: true,
			            inverse: true,
			            playInterval: 5000,
			            left: null,
			            right: -105,
			            top: 20,
			            bottom: 20,
			            width: 46,
			        data: ['2016',]  
			    },
			    baseOption: {
			       visualMap: {
			            max: 100,
			            calculable: true,
			            inRange: {
			                color: ['#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
			            }
			        },
			        series: [{
			            type: 'map',
			            map: 'world',
			            roam: true,
			            nameMap: nameMap
			        }]
			    },
			    options: [{
			        title: {
			           
			        },
			        series: {
			            data: values_activation
			        } 
			    }, ]
			};
			option3 = {
			    visualMap: {
			        max: 10000,
			        calculable: true,
			        inRange: {
			            color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
			        }
			    },
			    series: [{
			        type: 'map',
			        map: 'world',
			        data: values,
			        nameMap: nameMap
			    }]
			};
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
			var userType;
			var userName;
			var userStatus;
			var map_char_activition;
			var map_char_active;
			var active_map =false;
		$(document).ready(function() {
			userType ='${sessionScope.WtUserInfo.userType}';
			
			userName='${sessionScope.WtUserInfo.userName}';
			userStatus='${sessionScope.WtUserInfo.userStatus}';
			
			map_char_active = echarts.init(document.getElementById('_active_map'));
			//map_char_active.setOption(option2);
			$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
				map_char_active.resize();
			});
			 map_char_activition = echarts.init(document.getElementById('_add_map'));
			 $("#btn_today").click(function(){  
			 	values=[];
					  //requestMapData("active",1,option3,map_char_active,userType,userStatus,values);
					 // requestActivationMapData("activation",1,option4,map_char_activition,userType,userStatus,values);
					  requestMapData2("active",1,option2,map_char_active,userType,userStatus,values);
					requestTableData("active",1,userType,userStatus);
					});
			/* $("#btn_yestoday").click(function(){  
			 	values=[];
					 // requestMapData("active",0,option3,map_char_active,userType,userStatus,values);
					 
					// requestActivationMapData("activation",1,option2,map_char_activition,userType,userStatus,values);
					 requestMapData2("active",1,option2,map_char_active,userType,userStatus,values);
					requestTableData("active",1,userType,userStatus);
					});*/
			 $("#btn_sevenday").click(function(){  
			 	values=[];
					 // requestMapData("active",7,option3,map_char_active,userType,userStatus,values);
					 //requestActivationMapData("activation",7,option4,map_char_activition,userType,userStatus,values);
					 requestMapData2("active",7,option2,map_char_active,userType,userStatus,values);
					requestTableData("active",7,userType,userStatus);
					}); 
			 $("#btn_thirty").click(function(){  
			 	values=[];
			 	//requestActivationMapData("activation",30,option4,map_char_activition,userType,userStatus,values);
					 // requestMapData("active",30,option3,map_char_active,userType,userStatus,values);
					 requestMapData2("active",30,option2,map_char_active,userType,userStatus,values);
					requestTableData("active",30,userType,userStatus);
					});
			  $("#btn_today_activation").click(function(){  
			 	values_activation=[];
					  //requestMapData("active",1,option3,map_char_active,userType,userStatus,values);
					  requestActivationMapData("activation",1,option4,map_char_activition,userType,userStatus,values_activation);
					 requestActivationTableData("activation",1,userType,userStatus);
					});
			/* $("#btn_yestoday_activation").click(function(){  
			 	values_activation=[];
					 // requestMapData("active",0,option3,map_char_active,userType,userStatus,values);
					 
					 requestActivationMapData("activation",1,option4,map_char_activition,userType,userStatus,values_activation);
					requestActivationTableData("activation",1,userType,userStatus);
					});*/
			 $("#btn_sevenday_activation").click(function(){  
			 	values_activation=[];
					 // requestMapData("active",7,option3,map_char_active,userType,userStatus,values);
					 requestActivationMapData("activation",7,option4,map_char_activition,userType,userStatus,values_activation);
					 requestActivationTableData("activation",7,userType,userStatus);
					}); 
			 $("#btn_thirty_activation").click(function(){  
			 	values_activation=[];
			 	requestActivationMapData("activation",30,option4,map_char_activition,userType,userStatus,values_activation);
					 // requestMapData("active",30,option3,map_char_active,userType,userStatus,values);
					requestActivationTableData("activation",30,userType,userStatus);
					});
			/*map_char_active.setOption(option);//
			map_char_activition.setOption(option3);*/
			//select();
			//requestMapData("active",30,option3,map_char_active,userType,userStatus,values);
			requestActivationMapData("activation",7,option4,map_char_activition,userType,userStatus,values_activation);
			requestMapData2("active",7,option2,map_char_active,userType,userStatus,values);
			requestTableData("active",7,userType,userStatus);
			requestActivationTableData("activation",7,userType,userStatus);

		});
		
		/*$("#_tab").tabs({ 
		      activate: function( event, ui ){
	        active_select = !active_select;
	        alert(active_select);
	      }  
	    }); */
		
		function requestMapData(type,date,options,ec,userType,userStatus,values){
			var requestUrl = "${baseURL}/region/Map/"+type+"/"+date+"/"+userType+"/"+userStatus;
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
		                    value:0 
		                    }  
		                };  

		           var Series = []; 
		           var data1 =[];
		           
		            var json = jsons.data; 

		            for(var i=0;i < json.length;i++){  
		                var it = new Item();  
		                it.name = json[i].country;  
		                it.value = json[i].count;  
		                values.push(it);  
		            }  
		       
		           console.log(values);
		         	options.series.data=[];
		         	ec.setOption(options);
		            options.series.data=values; // 设置图表 
		            //ec.clear(); 
		            ec.setOption(options);// 重新加载图表  
		            values=[];
		            window.onresize = ec.resize;
		        },  
		        error:function(){  
		        		ec.hideLoading();
		           console.log("show map error");
		           // alert("数据加载失败！请检查数据链接是否正确");  
		        }  
			});
		}
		function requestMapData2(type,date,options,ec,userType,userStatus,values){
			var requestUrl = "${baseURL}/region/Map/"+type+"/"+date+"/"+userType+"/"+userStatus;
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
		                    value:0 
		                    }  
		                };  
		           var Series = []; 
		           var data1 =[];
		           
		            var json = jsons.data; 
		            for(var i=0;i < json.length;i++){  
		                var it = new Item();  
		                it.name = json[i].country;  
		                it.value = json[i].count;  
		                values.push(it);  
		            }
		       
		           console.log(values);
		         	optionk={
			    timeline: {
			        axisType: 'category',
			            orient: 'vertical',
			            autoPlay: true,
			            inverse: true,
			            playInterval: 5000,
			            left: null,
			            right: -105,
			            top: 20,
			            bottom: 20,
			            width: 46,
			        data: ['2016',]  
			    },
			    baseOption: {
			       visualMap: {
			            max: 10000,
			            calculable: true,
			            inRange: {
			                color: ['#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
			            }
			        },
			        series: [{
			            type: 'map',
			            map: 'world',
			            roam: true,
			            nameMap: nameMap
			        }]
			    },
			    options: [{
			        title: {
			            
			        },
			        series: {
			            data: values
			        } 
			    }, ]
			};
		         	ec.setOption(optionk);
		            
		            //ec.clear(); 
		          //  ec.setOption(options);// 重新加载图表  
		            //values=[];
		            window.onresize = ec.resize;
		        },  
		        error:function(){  
		        		ec.hideLoading();

		            console.log("show map2 error");
		            //alert("数据加载失败！请检查数据链接是否正确");  
		        }  
			});
		}
		function requestActivationMapData(type,date,options,ec,userType,userStatus,values){
			var requestUrl = "${baseURL}/region/Map/"+type+"/"+date+"/"+userType+"/"+userStatus;
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
		                    value:0 
		                    }  
		                };  
		           var Series = []; 
		           var data1 =[];
		           
		            var json = jsons.data; 

		            for(var i=0;i < json.length;i++){  
		                var it = new Item();  
		                it.name = json[i].country;  
		                it.value = json[i].count;  
		                values.push(it);  
		            }
		       
		           console.log(values);
		         	optionk={
					    timeline: {
					        axisType: 'category',
					            orient: 'vertical',
					            autoPlay: true,
					            inverse: true,
					            playInterval: 5000,
					            left: null,
					            right: -105,
					            top: 20,
					            bottom: 20,
					            width: 46,
					        data: ['2016',]  
					    },
					    baseOption: {
					       visualMap: {
					            max: 10000,
					            calculable: true,
					            inRange: {
					                color: ['#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
					            }
					        },
					        series: [{
					            type: 'map',
					            map: 'world',
					            roam: true,
					            nameMap: nameMap
					        }]
					    },
					    options: [{
					        title: {
					            
					        },
					        series: {
					            data: values
					        } 
					    }, ]
					};
		         	ec.setOption(optionk);
		            
		            //ec.clear(); 
		          //  ec.setOption(options);// 重新加载图表  
		            //values=[];
		            window.onresize = ec.resize;
		        },  
		        error:function(){  
		        		ec.hideLoading();

		           console.log("show requestData error");
		           // alert("数据加载失败！请检查数据链接是否正确");  
		        }  
			});
		}
		var activeTable;
		function requestTableData(type,date,userType,userName){
		var requestUrl = "${baseURL }/region/Map/"+type+"/table/"+date+"/"+userType+"/"+userStatus;
		
		if(activeTable == undefined || activeTable == "undefined" || activeTable == null){
			
			activeTable =  $("#active_table").dataTable({  
			 		"language":lang,
		            "ajax" : requestUrl,  
		            "columns" : [   
		               
			            {"data": "country"},
			            {"data": "count"},
			            {"data": "occupancy"},
			        
			            {"defaultContent": "<button id='query_sn' data-target='#_country_active_detail' data-toggle='modal' class='btn btn-success'>查看</button>"}
		                ]  
		        	});  
				
				$('#active_table tbody').on( 'click', '#query_sn', function () {

					var data = $('#active_table').DataTable().row($(this).parents('tr')).data();

	              requestQueryDev(date,data.country,userType,userStatus);
				} );
			}else{
				 activeTable.fnDestroy();
				 activeTable =  $("#active_table").dataTable({  
					 		"language":lang,
				            "ajax" : requestUrl,  
				            "columns" : [   
				               
					            {"data": "country"},
					            {"data": "count"},
					            {"data": "occupancy"},
					        
					            {"defaultContent": "<button id='query_sn' data-target='#_country_active_detail' data-toggle='modal' class='btn btn-success'>查看</button>"}
				                ]  
				        	});  
					
					 $('#active_table tbody').on( 'click', '#query_sn', function () {
					 	var data = $('#active_table').DataTable().row($(this).parents('tr')).data();
					 	
		              	requestQueryDev(date,data.country,userType,userStatus);
					});
			}
			activeTable.fnDraw(); 
		}
		var activation_table;
		function requestActivationTableData(type,date,userType,userName){
		var requestUrl = "${baseURL }/region/Map/"+type+"/table/"+date+"/"+userType+"/"+userStatus;
		
		if(activation_table == undefined || activation_table == "undefined" || activation_table == null){
			
			activation_table =  $("#activation_table").dataTable({  
			 		"language":lang,
		            "ajax" : requestUrl,  
		            "columns" : [   
		               
			            {"data": "country"},
			            {"data": "count"},
			            {"data": "occupancy"},
			        
			            {"defaultContent": "<button id='query_sn_activation' data-target='#_country_add_detail' data-toggle='modal' class='btn btn-success'>查看</button>"}
		                ]  
		        	});  
				
				$('#activation_table tbody').on( 'click', '#query_sn_activation', function () {

					var data = $('#activation_table').DataTable().row($(this).parents('tr')).data();

	              requestQueryActivationDev(date,data.country,userType,userStatus);
				} );
			}else{
				 activation_table.fnDestroy();
				 activation_table =  $("#activation_table").dataTable({  
					 		"language":lang,
				            "ajax" : requestUrl,  
				            "columns" : [   
				               
					            {"data": "country"},
					            {"data": "count"},
					            {"data": "occupancy"},
					        
					            {"defaultContent": "<button id='query_sn_activation' data-target='#_country_add_detail' data-toggle='modal' class='btn btn-success'>查看</button>"}
				                ]  
				        	});  
					
					 $('#activation_table tbody').on( 'click', '#query_sn_activation', function () {
					 	var data = $('#activation_table').DataTable().row($(this).parents('tr')).data();
					 	
		              	requestQueryActivationDev(date,data.country,userType,userStatus);
					});
			}
			activation_table.fnDraw(); 
		}

		var queryDevTable;
		function requestQueryDev(date,country,userType,userStatus){
			var requestUrl = "${baseURL }/region/Map/table/dev/"+date+"/"+userType+"/"+userStatus+"/"+country;
			if(queryDevTable == undefined || queryDevTable == "undefined" || queryDevTable == null){
				queryDevTable =  $("#queryDev_table").dataTable({  
				 		"language":lang,
			            "ajax" : requestUrl,  
			            "columns" : [        
				            {"data": "dev_name"},
				            {"data": "count"},
				            {"data": "occupancy"},
			                ]  
			        	});  
					
					
			}else{
				 queryDevTable.fnDestroy();
				 queryDevTable =  $("#queryDev_table").dataTable({  
					 		"language":lang,
				            "ajax" : requestUrl,  
				            "columns" : [   
				               
					            {"data": "dev_name"},
					            {"data": "count"},
					            {"data": "occupancy"}
				                ]  
				        	}); 
			}
			queryDevTable.fnDraw();
		}
		var quertActivationDev;
		function requestQueryActivationDev(date,country,userType,userStatus){

			var requestUrl = "${baseURL }/region/Map/table/dev/activation/"+date+"/"+userType+"/"+userStatus+"/"+country;
			if(quertActivationDev == undefined || quertActivationDev == "undefined" || quertActivationDev == null){
				quertActivationDev =  $("#queryActivationDev_table").dataTable({  
				 		"language":lang,
			            "ajax" : requestUrl,  
			            "columns" : [        
				            {"data": "dev_name"},
				            {"data": "count"},
				            {"data": "occupancy"},
			                ]  
			        	});  
					
					
			}else{
				 quertActivationDev.fnDestroy();
				 quertActivationDev =  $("#queryActivationDev_table").dataTable({  
					 		"language":lang,
				            "ajax" : requestUrl,  
				            "columns" : [   
				               
					            {"data": "dev_name"},
					            {"data": "count"},
					            {"data": "occupancy"}
				                ]  
				        	}); 
			}
			quertActivationDev.fnDraw();
		}
			/*var table = $('#add_table').DataTable({
				"ajax": "../data/1.txt",
				"columnDefs": [{
					"targets": -1,
					"data": null,
					"defaultContent": "<button class='btn btn-success' data-target='#_country_add_detail' data-toggle='modal' >查看</button>"
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
			
			$('#add_table tbody').on('click', 'button', function() {
				var data = table.row($(this).parents('tr')).data();
				  var table1 = $('#ad_table').DataTable({
					"ajax": "../data/1.txt"
				});
			});
			$('#_country_add_detail').on('hide.bs.modal', function () {
				//table1.reload.draw();
				//$('#ad_table').DataTable().reload;
				
				window.location.reload();
			});
		});
		$(document).ready(function() {
			var table = $('#active_table').DataTable({
				"ajax": "../data/1.txt",
				"columnDefs": [{
					"targets": -1,
					"data": null,
					"defaultContent": "<button class='btn btn-success' data-target='#_country_active_detail' data-toggle='modal'>查看</button>"
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

			$('#active_table tbody').on('click', 'button', function() {
				var data = table.row($(this).parents('tr')).data();
				$('#add_table').DataTable();
				alert(data[0] + "'s salary is: " + data[5]);
			});
		});*/
		//全球分布新增图表
/*		var map_cahr = echarts.init(document.getElementById('_add_map'));
		option = {
			title: {
					text: '设备新增全球分布图',
					subtext: 'from United Nations, Total device(thousands)',
				x: 'center',
				y: 'top'
			},
			tooltip: {
				trigger: 'item',
				formatter: function(params) {
					var value = (params.value + '').split('.');
					value = value[0].replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,') +
						'.' + value[1];
					return params.seriesName + '<br/>' + params.name + ' : ' + value;
				}
			},
			toolbox: {
				show: true,
				orient: 'vertical',
				x: 'right',
				y: 'center',
				feature: {
					mark: {
						show: true
					},
					dataView: {
						show: true,
						readOnly: false
					},
					restore: {
						show: true
					},
					saveAsImage: {
						show: true
					}
				}
			},
			dataRange: {
				min: 0,
				max: 1000000,
				text: ['High', 'Low'],
				realtime: false,
				calculable: true,
				color: ['orangered', 'yellow', 'lightskyblue']
			},
			series: [{
				name: 'World Population (2010)',
				type: 'map',
				mapType: 'world',
				roam: true,
				mapLocation: {
					y: 60
				},
				itemStyle: {
					emphasis: {
						label: {
							show: true
						}
					}
				},
				data: [{
					name: 'Afghanistan',
					value: 28397.812
				}, {
					name: 'Angola',
					value: 19549.124
				}, {
					name: 'Albania',
					value: 3150.143
				}, {
					name: 'United Arab Emirates',
					value: 8441.537
				}, {
					name: 'Argentina',
					value: 40374.224
				}, {
					name: 'Armenia',
					value: 2963.496
				}, {
					name: 'French Southern and Antarctic Lands',
					value: 268.065
				}, {
					name: 'Australia',
					value: 22404.488
				}, {
					name: 'Austria',
					value: 8401.924
				}, {
					name: 'Azerbaijan',
					value: 9094.718
				}, {
					name: 'Burundi',
					value: 9232.753
				}, {
					name: 'Belgium',
					value: 10941.288
				}, {
					name: 'Benin',
					value: 9509.798
				}, {
					name: 'Burkina Faso',
					value: 15540.284
				}, {
					name: 'Bangladesh',
					value: 151125.475
				}, {
					name: 'Bulgaria',
					value: 7389.175
				}, {
					name: 'The Bahamas',
					value: 66402.316
				}, {
					name: 'Bosnia and Herzegovina',
					value: 3845.929
				}, {
					name: 'Belarus',
					value: 9491.07
				}, {
					name: 'Belize',
					value: 308.595
				}, {
					name: 'Bermuda',
					value: 64.951
				}, {
					name: 'Bolivia',
					value: 716.939
				}, {
					name: 'Brazil',
					value: 195210.154
				}, {
					name: 'Brunei',
					value: 27.223
				}, {
					name: 'Bhutan',
					value: 716.939
				}, {
					name: 'Botswana',
					value: 1969.341
				}, {
					name: 'Central African Republic',
					value: 4349.921
				}, {
					name: 'Canada',
					value: 34126.24
				}, {
					name: 'Switzerland',
					value: 7830.534
				}, {
					name: 'Chile',
					value: 17150.76
				}, {
					name: 'China',
					value: 1359821.465
				}, {
					name: 'Ivory Coast',
					value: 60508.978
				}, {
					name: 'Cameroon',
					value: 20624.343
				}, {
					name: 'Democratic Republic of the Congo',
					value: 62191.161
				}, {
					name: 'Republic of the Congo',
					value: 3573.024
				}, {
					name: 'Colombia',
					value: 46444.798
				}, {
					name: 'Costa Rica',
					value: 4669.685
				}, {
					name: 'Cuba',
					value: 11281.768
				}, {
					name: 'Northern Cyprus',
					value: 1.468
				}, {
					name: 'Cyprus',
					value: 1103.685
				}, {
					name: 'Czech Republic',
					value: 10553.701
				}, {
					name: 'Germany',
					value: 83017.404
				}, {
					name: 'Djibouti',
					value: 834.036
				}, {
					name: 'Denmark',
					value: 5550.959
				}, {
					name: 'Dominican Republic',
					value: 10016.797
				}, {
					name: 'Algeria',
					value: 37062.82
				}, {
					name: 'Ecuador',
					value: 15001.072
				}, {
					name: 'Egypt',
					value: 78075.705
				}, {
					name: 'Eritrea',
					value: 5741.159
				}, {
					name: 'Spain',
					value: 46182.038
				}, {
					name: 'Estonia',
					value: 1298.533
				}, {
					name: 'Ethiopia',
					value: 87095.281
				}, {
					name: 'Finland',
					value: 5367.693
				}, {
					name: 'Fiji',
					value: 860.559
				}, {
					name: 'Falkland Islands',
					value: 49.581
				}, {
					name: 'France',
					value: 63230.866
				}, {
					name: 'Gabon',
					value: 1556.222
				}, {
					name: 'United Kingdom',
					value: 62066.35
				}, {
					name: 'Georgia',
					value: 4388.674
				}, {
					name: 'Ghana',
					value: 24262.901
				}, {
					name: 'Guinea',
					value: 10876.033
				}, {
					name: 'Gambia',
					value: 1680.64
				}, {
					name: 'Guinea Bissau',
					value: 10876.033
				}, {
					name: 'Equatorial Guinea',
					value: 696.167
				}, {
					name: 'Greece',
					value: 11109.999
				}, {
					name: 'Greenland',
					value: 56.546
				}, {
					name: 'Guatemala',
					value: 14341.576
				}, {
					name: 'French Guiana',
					value: 231.169
				}, {
					name: 'Guyana',
					value: 786.126
				}, {
					name: 'Honduras',
					value: 7621.204
				}, {
					name: 'Croatia',
					value: 4338.027
				}, {
					name: 'Haiti',
					value: 9896.4
				}, {
					name: 'Hungary',
					value: 10014.633
				}, {
					name: 'Indonesia',
					value: 240676.485
				}, {
					name: 'India',
					value: 1205624.648
				}, {
					name: 'Ireland',
					value: 4467.561
				}, {
					name: 'Iran',
					value: 240676.485
				}, {
					name: 'Iraq',
					value: 30962.38
				}, {
					name: 'Iceland',
					value: 318.042
				}, {
					name: 'Israel',
					value: 7420.368
				}, {
					name: 'Italy',
					value: 60508.978
				}, {
					name: 'Jamaica',
					value: 2741.485
				}, {
					name: 'Jordan',
					value: 6454.554
				}, {
					name: 'Japan',
					value: 127352.833
				}, {
					name: 'Kazakhstan',
					value: 15921.127
				}, {
					name: 'Kenya',
					value: 40909.194
				}, {
					name: 'Kyrgyzstan',
					value: 5334.223
				}, {
					name: 'Cambodia',
					value: 14364.931
				}, {
					name: 'South Korea',
					value: 51452.352
				}, {
					name: 'Kosovo',
					value: 97.743
				}, {
					name: 'Kuwait',
					value: 2991.58
				}, {
					name: 'Laos',
					value: 6395.713
				}, {
					name: 'Lebanon',
					value: 4341.092
				}, {
					name: 'Liberia',
					value: 3957.99
				}, {
					name: 'Libya',
					value: 6040.612
				}, {
					name: 'Sri Lanka',
					value: 20758.779
				}, {
					name: 'Lesotho',
					value: 2008.921
				}, {
					name: 'Lithuania',
					value: 3068.457
				}, {
					name: 'Luxembourg',
					value: 507.885
				}, {
					name: 'Latvia',
					value: 2090.519
				}, {
					name: 'Morocco',
					value: 31642.36
				}, {
					name: 'Moldova',
					value: 103.619
				}, {
					name: 'Madagascar',
					value: 21079.532
				}, {
					name: 'Mexico',
					value: 117886.404
				}, {
					name: 'Macedonia',
					value: 507.885
				}, {
					name: 'Mali',
					value: 13985.961
				}, {
					name: 'Myanmar',
					value: 51931.231
				}, {
					name: 'Montenegro',
					value: 620.078
				}, {
					name: 'Mongolia',
					value: 2712.738
				}, {
					name: 'Mozambique',
					value: 23967.265
				}, {
					name: 'Mauritania',
					value: 3609.42
				}, {
					name: 'Malawi',
					value: 15013.694
				}, {
					name: 'Malaysia',
					value: 28275.835
				}, {
					name: 'Namibia',
					value: 2178.967
				}, {
					name: 'New Caledonia',
					value: 246.379
				}, {
					name: 'Niger',
					value: 15893.746
				}, {
					name: 'Nigeria',
					value: 159707.78
				}, {
					name: 'Nicaragua',
					value: 5822.209
				}, {
					name: 'Netherlands',
					value: 16615.243
				}, {
					name: 'Norway',
					value: 4891.251
				}, {
					name: 'Nepal',
					value: 26846.016
				}, {
					name: 'New Zealand',
					value: 4368.136
				}, {
					name: 'Oman',
					value: 2802.768
				}, {
					name: 'Pakistan',
					value: 173149.306
				}, {
					name: 'Panama',
					value: 3678.128
				}, {
					name: 'Peru',
					value: 29262.83
				}, {
					name: 'Philippines',
					value: 93444.322
				}, {
					name: 'Papua New Guinea',
					value: 6858.945
				}, {
					name: 'Poland',
					value: 38198.754
				}, {
					name: 'Puerto Rico',
					value: 3709.671
				}, {
					name: 'North Korea',
					value: 1.468
				}, {
					name: 'Portugal',
					value: 10589.792
				}, {
					name: 'Paraguay',
					value: 6459.721
				}, {
					name: 'Qatar',
					value: 1749.713
				}, {
					name: 'Romania',
					value: 21861.476
				}, {
					name: 'Russia',
					value: 21861.476
				}, {
					name: 'Rwanda',
					value: 10836.732
				}, {
					name: 'Western Sahara',
					value: 514.648
				}, {
					name: 'Saudi Arabia',
					value: 27258.387
				}, {
					name: 'Sudan',
					value: 35652.002
				}, {
					name: 'South Sudan',
					value: 9940.929
				}, {
					name: 'Senegal',
					value: 12950.564
				}, {
					name: 'Solomon Islands',
					value: 526.447
				}, {
					name: 'Sierra Leone',
					value: 5751.976
				}, {
					name: 'El Salvador',
					value: 6218.195
				}, {
					name: 'Somaliland',
					value: 9636.173
				}, {
					name: 'Somalia',
					value: 9636.173
				}, {
					name: 'Republic of Serbia',
					value: 3573.024
				}, {
					name: 'Suriname',
					value: 524.96
				}, {
					name: 'Slovakia',
					value: 5433.437
				}, {
					name: 'Slovenia',
					value: 2054.232
				}, {
					name: 'Sweden',
					value: 9382.297
				}, {
					name: 'Swaziland',
					value: 1193.148
				}, {
					name: 'Syria',
					value: 7830.534
				}, {
					name: 'Chad',
					value: 11720.781
				}, {
					name: 'Togo',
					value: 6306.014
				}, {
					name: 'Thailand',
					value: 66402.316
				}, {
					name: 'Tajikistan',
					value: 7627.326
				}, {
					name: 'Turkmenistan',
					value: 5041.995
				}, {
					name: 'East Timor',
					value: 10016.797
				}, {
					name: 'Trinidad and Tobago',
					value: 1328.095
				}, {
					name: 'Tunisia',
					value: 10631.83
				}, {
					name: 'Turkey',
					value: 72137.546
				}, {
					name: 'United Republic of Tanzania',
					value: 44973.33
				}, {
					name: 'Uganda',
					value: 33987.213
				}, {
					name: 'Ukraine',
					value: 46050.22
				}, {
					name: 'Uruguay',
					value: 3371.982
				}, {
					name: 'United States of America',
					value: 312247.116
				}, {
					name: 'Uzbekistan',
					value: 27769.27
				}, {
					name: 'Venezuela',
					value: 236.299
				}, {
					name: 'Vietnam',
					value: 89047.397
				}, {
					name: 'Vanuatu',
					value: 236.299
				}, {
					name: 'West Bank',
					value: 13.565
				}, {
					name: 'Yemen',
					value: 22763.008
				}, {
					name: 'South Africa',
					value: 51452.352
				}, {
					name: 'Zambia',
					value: 13216.985
				}, {
					name: 'Zimbabwe',
					value: 13076.978
				}]
			}]
		};
		map_cahr.setOption(option);*/
		//全球分布活跃图表
		
		//重绘
		/*$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
			map_char_active.resize();
		});*/
		/*option2 = {
			title: {
				text: '设备活跃全球分布图',
				subtext: 'from United Nations, Total device(thousands)',
				x: 'center',
				y: 'top'
			},
			tooltip: {
				trigger: 'item',
				formatter: function(params) {
					var value = (params.value + '').split('.');
					value = value[0].replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,') +
						'.' + value[1];
					return params.seriesName + '<br/>' + params.name + ' : ' + value;
				}
			},
			toolbox: {
				show: true,
				orient: 'vertical',
				x: 'right',
				y: 'center',
				feature: {
					mark: {
						show: true
					},
					dataView: {
						show: true,
						readOnly: false
					},
					restore: {
						show: true
					},
					saveAsImage: {
						show: true
					}
				}
			},
			dataRange: {
				min: 0,
				max: 1000000,
				text: ['High', 'Low'],
				realtime: false,
				calculable: true,
				color: ['orangered', 'yellow', 'lightskyblue']
			},
			series: [{
				name: 'World Population (2010)',
				type: 'map',
				mapType: 'world',
				roam: true,
				mapLocation: {
					y: 60
				},
				itemStyle: {
					emphasis: {
						label: {
							show: true
						}
					}
				},
				nameMap: nameMap,
				data: value3
			}]
		};*/
		
	



	</script>
	<!-- END THEME LAYOUT SCRIPTS -->
</body>
</html>
