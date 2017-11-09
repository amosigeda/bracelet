<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@include file="../../common/taglib/taglib.jsp" %>
<head>
<title>首页</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=all" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">

<link href="${baseURL }/common/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css">
<link href="${baseURL }/common/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css">
<link href="${baseURL }/common/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color">
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
	<!--网页头部开始-->
	<%@include file="top.jsp" %>
	<!--网页头部结束-->
	<div class="clearfix"> </div>
	<!--右侧网页内容开始-->
	<div class="page-container">
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
		<div class="page-content-wrapper ">
			<!-- 右侧BODY开始 -->
			<div class="page-content ">
				<!-- 右侧内容头部开始-->
				<div class="page-bar">
					<ul class="page-breadcrumb">
						<li>
							<a href="${baseURL }/">Home</a>
							<i class="fa fa-angle-right"></i>
						</li>
						<li>
							<span>首页</span>
						</li>
					</ul>
				</div>
				<!-- 右侧内容头部结束-->
				
				<!-- 右侧顶部四个数据显示框开始-->
				<div class="row widget-row bg_color" style="padding-top: 20px">
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
							<h4 class="widget-thumb-heading">入库新增</h4>
							<div class="widget-thumb-wrap">
								<div class="widget-thumb-body">
									<span class="widget-thumb-body-stat" id="todayInputNum">0</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
							<h4 class="widget-thumb-heading">累积激活</h4>
							<div class="widget-thumb-wrap">
								<div class="widget-thumb-body">
									<span class="widget-thumb-body-stat" id="allActivationNum">0</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
							<h4 class="widget-thumb-heading">存货明细</h4>
							<div class="widget-thumb-wrap">
								<div class="widget-thumb-body">
									<span class="widget-thumb-body-stat" id="storeNum">0</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
						<div class="widget-thumb widget-bg-color-white text-uppercase margin-bottom-20 bordered">
							<h4 class="widget-thumb-heading">昨日激活</h4>
							<div class="widget-thumb-wrap">
								<div class="widget-thumb-body">
									<span class="widget-thumb-body-stat" id="yesTodayActivation">0</span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!--右侧顶部四个数据显示框结束-->
				<!--右侧图表开始-->
				<div class="row bg_color">
					<div class="col-md-12">
						<div class="portlet light portlet-fit bordered">
							<div class="portlet-title">
								<div class="caption">
									<i class=" icon-bar-chart font-green"></i>
									<span class="caption-subject font-green bold uppercase">新增趋势图</span>
								</div>
							</div>
							<div class="portlet-body">
								<button type="button" class="btn btn-success" id ="activation"style="bockground-color: rgba(89,0,127,1)">新增</button>
								<button type="button" id="active" class="btn red-mint">活跃</button>
								<div id="_line" class="main" style="height: 250px;-webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);padding-top: 10px">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="portlet light portlet-fit bordered">
							<div class="portlet-title">
								<div class="caption">
									<i class=" icon-layers font-green"></i>
									<span class="caption-subject font-green bold uppercase">库存图</span>
								</div>
							</div>
							<div class="portlet-body">
								<div class="row" style="padding-top: 20px">
									<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<div id="_bar" style="height: 500px;  -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);padding-top: 15px;">
										</div>
									</div>
									<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
										<div id="_pie" style="height: 500px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; cursor: default; background-color: rgba(0, 0, 0, 0);padding-top: 20px; ">
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>

				</div>
				<!--右侧图表结束-->
			</div>
			<!-- 右侧BODY结束-->
		</div>
		<!--右侧内容结束-->
	</div>
	<!--右侧网页内容结束-->
	<!-- 底部内容开始-->
	<%@include file="foot.jsp" %>
	<!-- 底部内容结束-->
	<!--[if lt IE 9]>
	<script src="${baseURL }/common/assets/global/plugins/respond.min.js"></script>
	<script src="${baseURL }/common/assets/global/plugins/excanvas.min.js"></script> 
	<![endif]-->
	<script src="${baseURL }/common/assets/pages/scripts/dashboard.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/js/echarts.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/scripts/app.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
	<!-- 数字动画 开始-->
	<script src="${baseURL }/common/assets/global/plugins/counterup/jquery.waypoints.min.js" type="text/javascript"></script>
	<script src="${baseURL }/common/assets/global/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>
	<!-- 数字动画结束-->
	<script type="text/javascript">
		//新增趋势图标数据
		//var line_cahr = echarts.init(document.getElementById("_line"));
		activation_option = {
			title: {
				text: "新增趋势图"
			},
			tooltip: {
				trigger: "axis"
			},
			legend: {
				data: ["今日新增", "昨日新增"]
			},
			grid: {
				left: "3%",
				right: "4%",
				bottom: "3%",
				containLabel: true
			},
			toolbox: { //可视化的工具箱
				show: true,
				x: "right",
				feature: {
					dataView: {
						show: true,
						readOnly: false
					},
					magicType: {
						show: true,
						type: ["line", "bar", "stack", "tiled"]
					},
					restore: {
						show: true
					},
					saveAsImage: {
						show: true
					}
				}
			},
			xAxis: {
				type: "category",
				boundaryGap: false,
				data: ['0:00','1:00','2:00','3:00','4:00','5:00','6:00','7:00','8:00','9:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00']
			},
			yAxis: [{
				type: "value",
				boundaryGap: false,
				name: "新增数",
			}, {
				type: "value",
				oundaryGap: false,
				max: 1,
				min: 0,
			}],
			series: [{
					name: "今日新增",
					type: "line",
					stack: "总量",
					data: []
				}, {
					name: "昨日新增",
					type: "line",
					stack: "总量",
					data: []
				}
			]
		};
		active_option = {
			title: {
				text: "活跃趋势图"
			},
			tooltip: {
				trigger: "axis"
			},
			legend: {
				data: ["今日活跃", "昨日活跃"]
			},
			grid: {
				left: "3%",
				right: "4%",
				bottom: "3%",
				containLabel: true
			},
			toolbox: { //可视化的工具箱
				show: true,
				x: "right",
				feature: {
					dataView: {
						show: true,
						readOnly: false
					},
					magicType: {
						show: true,
						type: ["line", "bar", "stack", "tiled"]
					},
					restore: {
						show: true
					},
					saveAsImage: {
						show: true
					}
				}
			},
			xAxis: {
				type: "category",
				boundaryGap: false,
				data: ['0:00','1:00','2:00','3:00','4:00','5:00','6:00','7:00','8:00','9:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00']
			},
			yAxis: [{
				type: "value",
				boundaryGap: false,
				name: "活跃数",
			}, {
				type: "value",
				oundaryGap: false,
				max: 1,
				min: 0,
			}],
			series: [{
					name: "今日活跃",
					type: "line",
					stack: "总量",
					data: []
				}, {
					name: "昨日活跃",
					type: "line",
					stack: "总量",
					data: []
				}
			]
		};
		//line_cahr.setOption(option);
		//库存，销量数据柱状图
		var bar_echarts;
		var pie_echarts;
		bar_option = {
			title: {
				text: "设备数",
			},
			tooltip: {
				trigger: "axis"
			},
			legend: {
				data: ["库存","销售"]
			},
			toolbox: {
				show: true,
				x: "right",
				feature: {
					dataView: {
						show: true,
						readOnly: false
					},
					magicType: {
						show: true,
						type: ["line", "bar", ]
					},
					"restore": {
						"show": true
					},
					"saveAsImage": {
						"show": true
					}
				}
			},
			calculable: true,
			xAxis: [{
				type: "category",
				data: []
			}],
			yAxis: [{
				type: "value"
			}],
			series: []
		};
		//bar_echarts.setOption(bar_option);
		//饼状图数据
		
		pie_option = {
			title: {
				text: "库存占总数比",				
				x: "center"
			},
			tooltip: {
				trigger: "item",
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient: 'vertical',
				x: 'left',
				data: []
			},
			toolbox: {
				show: true,
				feature: {
					dataView: {
						show: true,
						readOnly: false
					},
					magicType: {
						show: true,
						type: ["pie", "funnel"],
						option: {
							funnel: {
								x: "25%",
								width: "50%",
								funnelAlign: "left",
								max: 1548
							}
						}
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
			series: [{
				
				type: "pie",
				radius: "55%",
				center: ["50%", "60%"],
				data: [],
				itemStyle: {
					normal: {
						label: {
							show: true,
							formatter: "{b}:({d}%)"
						},
						labelLine: {
							show: true
						}
					}
				}
			}]
		};
		//pie_echarts.setOption(option2);
		var userStatus;
		var userType;
		$(document).ready(function(){
			userStatus = '${sessionScope.WtUserInfo.userStatus}';
			userType = '${sessionScope.WtUserInfo.userType}';
			var bar_echarts = echarts.init(document.getElementById("_bar"));
			var pie_echarts = echarts.init(document.getElementById('_pie'));
			var line_echarts = echarts.init(document.getElementById("_line"));
			requestStore(bar_echarts,userStatus,userType,bar_option);
			requestPie(pie_echarts,userStatus,userType,pie_option);
			requestActivationLine(line_echarts,userStatus,userType,activation_option);
			requestBaseData(userStatus,userType);
			$("#activation").click(function(){
				requestActivationLine(line_echarts,userStatus,userType,activation_option);
			});
			$("#active").click(function(){
				requestActiveLine(line_echarts,userStatus,userType,active_option);
			});
		});	
		function requestStore(ec,userStatus,userType,option){
			var requestUrl = "${baseURL}/index/showStore"+"?userStatus="+userStatus+"&userType="+userType;
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
		                    type:'bar',  
		                    data:[]  
		                    }  
		                };  
		            var legends = [];  
		            var Series = [];  
		            var json = jsons;  
		           console.log("store:"+jsons);
	                var it = new Item();  
	                it.data = json.Ydata1;  
	                it.name = "库存"; 
	                Series.push(it); 

	                  var it2 = new Item();  
	                it2.data = json.Ydata2;  
	                it2.name = "销售"; 
	                Series.push(it2); 
		                
		            option.xAxis[0].data = jsons.Xdata;   
		             
		            option.series = Series; // 设置图表  
		            ec.setOption(option);// 重新加载图表  
		            window.onresize = ec.resize;
		        },  
		        error:function(){  
		        	ec.hideLoading();
		        	console.log("requestStore error");
		           // alert("数据加载失败！请检查数据链接是否正确");  
		        }  
			});
		}
		function requestPie(ec,userStatus,userType,option){
			var requestUrl = "${baseURL}/index/showPie"+"?userStatus="+userStatus+"&userType="+userType;
			ec.showLoading({
				text:'数据加载中...'
			});
			$.ajax({
				url:requestUrl,  
		        type:'get',  
		        dataType:'json',  
		        success:function(jsons){  
		            ec.hideLoading();
		            var legends = [];  
		            var Series = [];  
		            var json = jsons;  
	                 console.log(json.legends)
		             option.legend.data = json.legends;
		           // option.xAxis[0].data = jsons.Xdata;   
		             console.log(json.series);
		            option.series = {
							name: "库存占比",
							type: "pie",
							radius: "55%",
							center: ["50%", "60%"],
							data: json.series,
							itemStyle: {
								normal: {
									label: {
										show: true,
										formatter: "{b}:({d}%)"
									},
									labelLine: {
										show: true
									}
								}
							}
						} // 设置图表  
		            ec.setOption(option);// 重新加载图表  
		            window.onresize = ec.resize;
		        },  
		        error:function(){  
		        	ec.hideLoading();
		        	console.log("requestPie error");
		          //  alert("数据加载失败！请检查数据链接是否正确");  
		        }  
			});
		}
		function requestActivationLine(ec,userStatus,userType,option){
			var requestUrl = "${baseURL}/index/showActivation?userStatus="+userStatus+"&userType="+userType;
			ec.showLoading({
				text:'数据加载中...'
			});
			$.ajax({
				url:requestUrl,  
		        type:'get',  
		        dataType:'json',  
		        success:function(jsons){  
		            ec.hideLoading();
		            var legends = [];  
		            var Series = [];  
		            var json = jsons;  
	                 console.log(json.data1);
	                 console.log(json.data2);
		            // option.legend.data = json.legends;
		          //  option.xAxis.data = jsons.XData;   
		            // console.log(json.series);
		            option.series = [{
								name: "今日新增",
								type: "line",
								stack: "总量",
								data: json.data1
							}, {
								name: "昨日新增",
								type: "line",
								stack: "总量",
								data: json.data2
							 
						}];
		            ec.setOption(option);// 重新加载图表  
		            window.onresize = ec.resize;
		        },  
		        error:function(){  
		        	ec.hideLoading();
		        	console.log("requestActivationLine error");
		           // alert("数据加载失败！请检查数据链接是否正确");  
		        }  
			});
		}
		function requestActiveLine(ec,userStatus,userType,option){
			var requestUrl = "${baseURL}/index/showActive?userStatus="+userStatus+"&userType="+userType;
			ec.showLoading({
				text:'数据加载中...'
			});
			$.ajax({
				url:requestUrl,  
		        type:'get',  
		        dataType:'json',  
		        success:function(jsons){  
		             ec.hideLoading();
		            var legends = [];  
		            var Series = [];  
		            var json = jsons;  
	                 console.log(json.data1);
	                 console.log(json.data2);
		            // option.legend.data = json.legends;
		          //  option.xAxis.data = jsons.XData;   
		            // console.log(json.series);
		            option.series = [{
								name: "今日活跃",
								type: "line",
								stack: "总量",
								data: json.data1
							}, {
								name: "昨日活跃",
								type: "line",
								stack: "总量",
								data: json.data2
							 
						}];
		            ec.setOption(option);// 重新加载图表  
		            window.onresize = ec.resize;
		        },  
		        error:function(){  
		        	ec.hideLoading();
		        	console.log("requestActiveLine error");
		            //alert("数据加载失败！请检查数据链接是否正确");  
		        }  
			});
		}
		function requestBaseData(userStatus,userType){
			var requestUrl = "${baseURL }/index/baseData"+"?userType="+userType+"&userStatus="+userStatus;
			$.ajax({
				url:requestUrl,
				dataType:'json',
				type:'get',
				success:function(jsons){
					//$("#department_select").html()
					//alert(todayInputNum+""+yestodayInputNum);
					var todayInputNum = jsons.todayInputNum;
					var allActivationNum = jsons.allActivationNum;
					var storeNum = jsons.storeNum;
					var yesTodayActivation  = jsons.yesTodayActivation;
					console.log(todayInputNum+" "+allActivationNum+" "+storeNum+" "+yesTodayActivation);
					  
					 // $("#todayInput").value(allInputNum);
					// $("#todayInput").data("value")===allInputNum;
					  //alert();
					 //session.setAtt
					//$.session.set("todayInput", today)
					$("#todayInputNum").html(todayInputNum);
					$("#allActivationNum").html(allActivationNum);
					$("#storeNum").html(storeNum);
					$("#yesTodayActivation").html(yesTodayActivation);
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
	</script>
</body>
</html>
