<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
	<!--<![endif]-->
	<!-- BEGIN HEAD -->
<%@include file="../../common/taglib/taglib.jsp" %>
	<head>
		<title>GMS|修改密码</title>

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
		<link href="${baseURL }/common/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
		<link href="${baseURL }/common/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${baseURL }/common/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
		<link href="${baseURL }/common/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
		<link href="${baseURL }/common/assets/pages/css/login.min.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="favicon.ico" /> </head>

	<body class=" login">
		<div class="logo">
			<a href="index.html">
				<img src="${baseURL }/common/images/logo.png" alt="" /> </a>
		</div>
		<div class="content">
			
				<h3 class="form-title font-green">修改密码</h3>
				<div class="form-group">
					<input id="password1" class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="请输入新密码" /> </div>
				<div class="form-group">
					<input id="password2" class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="确认新密码"  /> </div>
				<div class="form-actions">
					<button type="submit" id="sure" class="btn green btn-block uppercase">确认</button>
				</div>

			
		</div>
		<div class="copyright"> 2017 © WaterWorld </div>
		<!--[if lt IE 9]>
<script src="../assets/global/plugins/respond.min.js"></script>
<script src="../assets/global/plugins/excanvas.min.js"></script> 
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
	
		<script src="${baseURL }/common/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
		<script src="${baseURL }/common/assets/global/scripts/app.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			
			$(document).ready(function() {
				password1
			});
			$("#sure").click(function(){
				var password1 = $('#password1').val();
			var password2 = $('#password2').val();
				//alert(password1);
				if(password1 == null || password1 =='' || password2 == null || password2 ==''){
					alert("请输入新密码");
					return ;
				}
				if(password1 != password2){
					alert("两次密码不一致");
					return;
				}
				var requestUrl = "${baseURL }/modify/password/modify"+"?password="+password1;
				$.ajax({
					url:requestUrl,
					 
				    type:'post',  
				    success:function(){
				    	alert("修改成功");
						location.href='${baseURL }/logout';
				    },
				    error:function(){

				    }
				});
			});
		</script>
	</body>

</html>