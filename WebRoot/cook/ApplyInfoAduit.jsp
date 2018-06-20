<%@ page language="java" import="java.util.*" import=" ofs.javabean.Cook" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/UI/simple/favicon.ico">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/iconfont.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/global.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/swiper.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/styles.css">
	<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.1.12.4.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/bootstrap.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/swiper.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/global.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.DJMask.2.1.1.js" charset="UTF-8"></script>
	<%
    Cook cook=(Cook)session.getAttribute("cook");
    String cookPicture=cook.getCook_picture();
    %>
	<script>
    
	$(function() {
		
        $("#aduitPage").addClass("active");
        var cookPicture="<%=cookPicture%>";
        var context="<%=request.getContextPath()%>/";
       	var cookPicURL=context+cookPicture;
        if(cookPicture!="")
        $("#cookHeaderPictureCon").attr('src',cookPicURL);
        
	});
	
	
        	//jQuery(changeAge);
        	function check() {
        		
        		
        		
        		var haserror=false;
        		var id=$("#idnumber").val();
        		var id2=$("#idPicture").val();
        		var cook1=$("#license").val();
        		var cook2=$("#licensePicture").val();
        		var health1=$("#healthid").val();
        		var health2=$("#healthPicture").val();
        		if(id == "") {
        			$("#idInfo > font").text("请输入身份证号码！");
        			haserror=true;
        		}
        		else {
        			$("#idInfo > font").text("");
        		}
        		if(id2 == "") {
        			$("#idInfo2 > font").text("请选择身份证照片");
        			haserror=true;
        		}
        		else {
        			$("#idInfo2 > font").text("");
        		}
        		if(cook1 == "") {
        			$("#licenseInfo > font").text("请输入厨师证号码！");
        			haserror=true;
        		}
        		else {
        			$("#licenseInfo > font").text("");
        		}
        		if(cook2 == "") {
        			$("#licenseInfo2 > font").text("请选择厨师证照片");
        			haserror=true;
        		}
        		else {
        			$("#licenseInfo2 > font").text("");
        		}
        		if(health1 == "") {
        			$("#healthidInfo > font").text("请输入健康证号码！");
        			haserror=true;
        		}
        		else {
        			$("#healthidInfo > font").text("");
        		}
        		if(health2 == "") {
        			$("#healthidInfo2 > font").text("请选择健康证照片");
        			haserror=true;
        		}
        		else {
        			$("#healthidInfo2 > font").text("");
        		}
        		
        		if(!haserror)
        			{
        				
        				$("#myform").submit();
        				alert("您的厨师信息提交审核成功！");
        				 var content="";
					     var title="系统消息";
					     $("#messageTitle").text(title);
					     $("#messageContent").text(content);
						 $("#messagebox").slideDown("slow");
						 setTimeout("hideMessageBox()",4000); 
        				 
        				
        			}
        	 	else 
        	 		return;
        	}
</script>
	<title>家宴帮</title>
</head>
<body>
<%@include file="../UI/simple/template/CookHeader.jsp" %>

	<div class="content clearfix bgf5">
		<%@include file="../UI/simple/template/CookLeft.jsp" %>
<div class="pull-right">
				<div class="user-content__box clearfix bgf">
					<div class="title">账户信息-个人资料</div>
					<div class="port b-r50" id="crop-avatar">
						<div class="img"><img id="cookHeaderPictureCon" src="" class="cover b-r50"></div>
					</div>
					<form  action="<%=request.getContextPath()%>/cook/applyAduit" class="user-setting__form"   id="myform" role="form" enctype="multipart/form-data"  method="post">											
						<div class="user-form-group">
							<label>身份证号码：</label>
							<input type="text" id="idnumber"  name="idnumber"  placeholder="请输入您的身份证号码">
							<span id="idInfo"><font color="red"></font></span>
						</div>
						<div class="user-form-group">
							<label>身份证照片：</label>
							<input type="file" id="idPicture" name="idPicture" />
							<span id="idInfo2"><font color="red"></font></span>
						</div>
						<div class="user-form-group">
							<label>厨师证号码：</label>
							<input type="text" id="license"  name="license"  placeholder="请输入您的厨师证号码">
							<span id="licenseInfo"><font color="red"></font></span>
						</div>
						<div class="user-form-group">
							<label>厨师证照片：</label>
							<input type="file" id="cookPicture" name="cookPicture" />
							<span id="licenseInfo2"><font color="red"></font></span>
						</div>
						<div class="user-form-group">
							<label>健康证号码：</label>
							<input type="text" id="healthid"  name="healthid"  placeholder="请输入您的健康证号码">
							<span id="healthidInfo"><font color="red"></font></span>
						</div>
						<div class="user-form-group">
							<label>健康证照片：</label>
							<input type="file" id="healthPicture" name="healthPicture" />
							<span id="healthidInfo2"><font color="red"></font></span>
						</div>
						<div class="user-form-group">
							<input class="btn" type="button" value="提交审核" onclick="check()" >
							
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
	

	<div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
	
		<%@include file="../UI/simple/template/CookRight.jsp" %>
		<%@include file="../UI/simple/template/Footer.jsp" %>
	
	
</body>
</html>