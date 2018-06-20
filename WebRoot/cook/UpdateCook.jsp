<%@ page language="java" import="java.util.*" import=" ofs.javabean.Cook" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
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
	<script src="<%=request.getContextPath()%>/UI/simple/js/getLocation.js" charset="UTF-8"></script>
	<%
    Cook cook=(Cook)session.getAttribute("cook");
    String cookPicture=cook.getCook_picture();
    %>
	<script>

	$(function() {
		
        $("#personInfo").addClass("active");
        $("#gender").val(${cook.cook_gender});
        var cookPicture="<%=cookPicture%>";
        var context="<%=request.getContextPath()%>/";
       	var cookPicURL=context+cookPicture;
        if(cookPicture!="")
        $("#cookHeaderPictureCon").attr('src',cookPicURL);
        getProvince();
   		

	});
	function checkHeaderPicture(){
		var headerPicture=$("#headerPicture").val();
	if (headerPicture=="")
	alert("请选择照片");
	if(headerPicture!="")
	{
	$("#headerForm").submit();
	$("#headerPictureClose").click();
	}
	};
	
	
        	//jQuery(changeAge);
        	function check() {
        		
        		
        		var name = $("#rName").val();
        		var gender = $("#gender").val();
        		//var gender0 = $("#gender0").val();
        		
        		var address = $("#location").val();
        		var telno = $("#tel").val();
        		var age=$("#age").val();
        		var workedTime=$("#workedTime").val();
        		var desc=$("#desc").val();
        		var haserror=false;
        		
        		
        		if(name == "") {
        			$("#nameInfo > font").text("请输入真实姓名");
        			haserror=true;
        		}
        		else {
        			$("#nameInfo > font").text("");
        		}
        		if(gender == "") {
        			$("#genderInfo > font").text("请选择性别");
        			haserror=true;
        		}
        		else {
        			$("#genderInfo > font").text("");
        		}
        		if(age == "") {
        			$("#ageInfo > font").text("请输入年龄");
        			haserror=true;
        		}
        		else {
        			$("#ageInfo > font").text("");
        		}
        		if(address == "") {
        			$("#addressInfo > font").text("请输入地址");
        			haserror=true;
        		}
        		else {
        			$("#addressInfo > font").text("");
        		}
        		if(telno == "") {
        			$("#telInfo > font").text("请输入联系电话");
        			haserror=true;
        		}
        		else {
        			$("#telInfo > font").text("");
        		}
        		if(workedTime == "") {
        			$("#workedTimeInfo > font").text("请输入工龄");
        			haserror=true;
        		}
        		else {
        			$("#workedTimeInfo > font").text("");
        		}
        		if(desc == "") {
        			$("#descInfo > font").text("请输入备注");
        			haserror=true;
        		}
        		else {
        			$("#descInfo > font").text("");
        		}
        		
        		if(!haserror)
        			{
        				 $.ajax({
					          url:"<%=request.getContextPath()%>/cook/updateCookAction",
					                type:"post",
					                dataType:"json",
					                data:$("#myform").serialize(),
					                aysnc:true,
					                success:function(data) {
					                   //alert("成功！"); 
					                   //showMessageBox();
					                   //$("#myform")[0].reset();
									   var strArray=eval(data);
					                   var content=strArray.message_content;
					                   var title=strArray.message_title;
					                   $("#messageTitle").text(title);
					                   $("#messageContent").text(content);
									   $("#messagebox").slideDown("slow");
									   setTimeout("hideMessageBox()",4000); 									                   						                   
					                },
					                error:function(ajax) {
					                    alert("失败！");
					                }
					            }) ;       
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
					<form  class="user-setting__form"  style="width:600px;" name="myform"  id="myform" role="form">
					<input type="hidden" id="cookPicture" value="${cook.cook_picture}">	
						<div class="form-group" >
							<label for="name" class="col-sm-2 control-label">昵称：</label>
							<div class="col-sm-6">
								<input class="form-control" type="text" id="rName"  name="rName" value="${cook.cook_rname}" placeholder="请输入您的昵称">
							</div>
							
							<span id="nameInfo"><font color="red"></font></span>
						</div>
						<br>
						<div class="form-group" style="margin-top:10px;" >
							<label for="name" class="col-sm-2 control-label">等级：</label>
							<div class="col-sm-6">
							普通厨师 <a href="javascript:void(0)">提升</a>
							</div>
						</div>
						<br>
						<div class="form-group" style="margin-top:10px;">
							<label for="name" class="col-sm-2 control-label">性别：</label>
							<div class="col-sm-6">
							<select class="form-control" id="gender" name="gender"  style="display:inline;width:100px;height:30px;">
							<option  value="1">男</option>
							<option value="0">女</option>
							</select>
							</div>
							
						</div>
						<br>
						<div class="form-group" style="margin-top:10px;padding-top:10px;">
							<label for="details" class="col-sm-2 control-label">地址：</label>
							<div class="col-sm-10">
								<div class="addr-linkage">
									<select id="province" onchange="getCity()" ></select>
									<select id="city" onchange="getArea()" ></select>
									<select id="area" name="area" ></select> 
								</div>
								
							</div>							
							<input class="form-control"  style="margin-left:110px;width:78%" type="text" id="location"  name="location" value="${cook.cook_location}" placeholder="请输入您的地址">
                            	<span id="addressInfo"><font color="red"></font></span>	
                            				
						</div>
						<div class="form-group" style="margin-top:10px;">
							<label for="name" class="col-sm-2 control-label">年龄：</label>
							<div class="col-sm-6">
							<input class="form-control" type="text" id="age"  name="age" value="${cook.cook_age}" placeholder="请输入您的年龄">
							<span id="ageInfo"><font color="red"></font></span>
							</div>
						</div>
						<br>
						<br>
						<div class="form-group" style="margin-top:10px;">
							<label for="name" class="col-sm-2 control-label">工龄：</label>
							<div class="col-sm-6">
							<input class="form-control" type="text" id="workedTime"  name="workedTime" value="${cook.cook_workedtime}" placeholder="请输入您的工龄">
							<span id="telnoInfo"><font color="red"></font></span>
							</div>
						</div>
						<br>
						<div class="form-group" style="margin-top:10px;padding-top:10px;">
							<label for="name" class="col-sm-2 control-label">手机：</label>
							<div class="col-sm-6">
							<input class="form-control" type="text" id="tel"  name="tel" value="${cook.cook_tel}" placeholder="请输入您的联系方式">
							<span id="telnoInfo"><font color="red"></font></span>
							</div>
						</div>
						<br>
						<div class="form-group" style="margin-top:10px;padding-top:10px;">
							<label for="name" class="col-sm-2 control-label">备注：</label>
							<div class="col-sm-6">
							<input class="form-control" type="text" id="desc"  name="desc" value="${cook.cook_desc}" placeholder="请输入您的备注">
							<span id="descInfo"><font color="red"></font></span>
							</div>
						</div>
						<br>
						<br>		
						<div class="user-form-group" align="center">
							<button type="button" class="btn" onclick="check()">确认修改</button>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
	<!-- 头像选择模态框 -->
	<link href="<%=request.getContextPath()%>/UI/simple/css/cropper/cropper.min.css" rel="stylesheet"  type="text/css" >
	<link href="<%=request.getContextPath()%>/UI/simple/css/cropper/sitelogo.css" rel="stylesheet"  type="text/css" >
	<script src="<%=request.getContextPath()%>/UI/simple/js/cropper/cropper.min.js"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/cropper/sitelogo.js"></script>
	<div class="modal fade" id="avatar-modal" aria-hidden="true" aria-labelledby="avatar-modal-label" role="dialog" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<form action="<%=request.getContextPath()%>/cook/updateCookHeaderPictureAction" id="headerForm" enctype="multipart/form-data" role="form" method="post" class="avatar-form" >
					<div class="modal-header">
						<button class="close" data-dismiss="modal" type="button" id="headerPictureClose">&times;</button>
						<h4 class="modal-title" id="avatar-modal-label">更改头像</h4>
					</div>
					<div class="modal-body">
						<div class="avatar-body">
							<div class="avatar-upload">
								<input class="avatar-src" name="avatar_src" type="hidden">
								<input class="avatar-data" name="avatar_data" type="hidden">
								<label for="avatarInput">图片上传</label>
								<input class="avatar-input"  type="file" id="headerPicture" name="headerPicture"></div>
							<div class="row">
								<div class="col-md-9">
									<div class="avatar-wrapper"></div>
								</div>
								<div class="col-md-3">
									<div class="avatar-preview preview-lg"></div>
									<div class="avatar-preview preview-md"></div>
									<div class="avatar-preview preview-sm"></div>
								</div>
							</div>
							<div class="row avatar-btns">
								<div class="col-md-9">
									<div class="btn-group">
										<button class="btn" data-method="rotate" data-option="-90" type="button" title="Rotate -90 degrees"><i class="fa fa-undo"></i> 向左旋转</button>
									</div>
									<div class="btn-group">
										
										<button class="btn" data-method="rotate" data-option="90" type="button" title="Rotate 90 degrees"><i class="fa fa-repeat"></i> 向右旋转</button>
									</div>
								</div>
								<div class="col-md-3">
									<input class="btn btn-success btn-block avatar-save" type="button" value="保存修改" onclick="checkHeaderPicture()" ><i class="fa fa-save"></i>
									
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="loading" aria-label="Loading" role="img" tabindex="-1"></div>
	
		<%@include file="../UI/simple/template/CookRight.jsp" %>
		<%@include file="../UI/simple/template/Footer.jsp" %>
	
	
</body>
</html>