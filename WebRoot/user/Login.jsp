<%@ page import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎您！请先登录</title>
<link href="<%=request.getContextPath()%>/css/login.css"
	rel="stylesheet" type="text/css"></link>
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />


<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.8.2.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

<style type="text/css">
body {
	padding-top: 0px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 300px;
	padding: 0px 10px 10px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading,.form-signin input[type="text"],.form-signin input[type="password"]
	{
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>
<script type="text/javascript">

window.onload=function()
{
  //·document.body.style.background="url(../images/bg.jpg) no-repeat 30% 40%";
}
	function check() {
		//获取用户名和密码
		var name = $("#username").val();
		var pwd = $("#password").val();
		
		var type=$("#usertype").val();

		if (name == "") {
			$("#nameInfo > font").text("请输入用户名");
		} else {
			$("#nameInfo > font").text("");
		}
		if (pwd == "") {
			$("#passInfo > font").text("请输入密码");
		} else {
			$("#passInfo > font").text("");
		}
		if(type=='0'){
			$("#myform").attr('action','<%=request.getContextPath()%>/user/loginAction');
		}
		else if(type=='1'){
			$("#myform").attr('action','<%=request.getContextPath()%>/admin/loginAction');
		}
		else if(type=='2'){
			$("#myform").attr('action','<%=request.getContextPath()%>/cook/loginAction');
		}
		else if(type=='3'){
			$("#myform").attr('action','<%=request.getContextPath()%>/server/loginAction');
		}
		
		

		if (name != "" && pwd != "") {
			document.myform.submit();
		}

	}
</script>
</head>

<body >

	<div id="login" class="container"
		style="background:none;margin-top: 90px;">

		<form class="form-signin" id="myform"
			action="" method="post"
			name="myform" style="background-color: transparent;border:none;">


			<h5 class="form-signin-heading" style="font-size:30px;">家宴帮</h5>
			<input name="username" id="username" type="text"
				class="input-block-level" placeholder="Username" value="${username}"/> <br /> 
				<span id="nameInfo"><font color="red"></font> </span> <br /> 
			<input name="password" id="password" type="password"
				class="input-block-level" placeholder="Password" value="${password}"/> <br /> 
				<span id="passInfo"><font color="red"></font></span><br />
			<input type="text" name="inputcheckcode" placeholder="验证码" class="input-block-level" value="${checkcode}"/>
			&nbsp;&nbsp;<img src="<%=basePath%>checkCode.jpg"  class="img-rounded" style="width:150px;height:40px;"/>
			<br />
			 <input name="" type="button" class="btn btn-default"
				value="登&nbsp;&nbsp;录" onclick="check()" />
			&nbsp;&nbsp;
			<select class="form-control" id="usertype" style=" width: 100px;margin-top: 10px">
				<option value="0">普通用户</option>
				<option value="1">管理员</option>
				<option value="2">厨师</option>
				<option value="3">客服</option>
			</select>
			&nbsp;&nbsp;
			<input name="" type="button" class="btn btn-default"
				value="注&nbsp;&nbsp;册"
				onclick="window.location.href='<%=request.getContextPath()%>/user/Registration.jsp'" />
			
		</form>
		<a href="./../cook/Registration.jsp">注册成为厨师</a>
		<b><font color="red" size="4"><s:actionerror /></font></b>
		
	</div>
	 <%@include file="../template/Footer.jsp" %>
	</div>

</body>
</html>
