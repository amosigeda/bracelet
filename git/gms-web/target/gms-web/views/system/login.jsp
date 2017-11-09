<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@include file="../common/taglib/taglib.jsp"%>
<head>
<title>GMS|Login</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" />
<link href="${baseURL }common/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }common/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }common/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }common/assets/global/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }common/assets/global/plugins/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }common/assets/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
<link href="${baseURL }common/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
<link href="${baseURL }common/assets/pages/css/login.min.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="favicon.ico" />
</head>

<body class=" login">
    <div class="logo">
        <a href="index.html">
            <img src="${baseURL }common/images/logo.png" alt="" /> </a>
    </div>
    <div class="content">
        <form class="login-form" action="" method="post">
            <h3 class="form-title font-green">登录</h3>
            <div class="alert alert-danger display-hide">
                <button class="close" data-close="alert"></button>
                <span> 请输入用户名或密码！ </span>
            </div>
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">用户名</label>
                <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" name="username" /> </div>
            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">密码</label>
                <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="password" /> </div>
            <div class="form-actions">
                <button type="submit" class="btn green btn-block uppercase"><a href="index.html">Login</a></button>
            </div>
            <div class="form-actions">
                <div class="pull-left">
                    <label class="rememberme check">
                        <input type="checkbox" name="remember" value="1" />记住密码</label>
                </div>
                <div class="pull-right forget-password-block">
                    <a href="javascript:;" id="forget-password" class="forget-password">忘记密码?</a>
                </div>
            </div>
        </form>
    </div>
    <div class="copyright"> 2017 © WaterWorld </div>
    <!--[if lt IE 9]>
<script src="${baseURL }common/assets/global/plugins/respond.min.js"></script>
<script src="${baseURL }common/assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
    <!-- BEGIN CORE PLUGINS -->
    <script src="${baseURL }common/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script src="${baseURL }common/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${baseURL }common/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
    <script src="${baseURL }common/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
    <script src="${baseURL }common/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src="${baseURL }common/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
    <script src="${baseURL }common/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
    <script src="${baseURL }common/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
    <script src="${baseURL }common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${baseURL }common/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
    <script src="${baseURL }common/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
    <script src="${baseURL }common/assets/global/scripts/app.min.js" type="text/javascript"></script>
    <script src="${baseURL }common/assets/pages/scripts/login.min.js" type="text/javascript"></script>
</body>
</html>
