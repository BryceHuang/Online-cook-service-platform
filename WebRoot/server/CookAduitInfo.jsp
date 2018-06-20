<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>">    
    <title>客服-厨师信息审核</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/iconfont.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/global.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/swiper.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/styles.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
		<link href="<%=request.getContextPath()%>/css/bootstrap-table.css" rel="stylesheet" /> 
		<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.1.12.4.min.js" charset="UTF-8"></script>
		<script src="<%=request.getContextPath()%>/UI/simple/js/bootstrap.min.js" charset="UTF-8"></script>
		<script src="<%=request.getContextPath()%>/UI/simple/js/swiper.min.js" charset="UTF-8"></script>
		<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.DJMask.2.1.1.js" charset="UTF-8"></script>
		<script src="<%=request.getContextPath()%>/js/bootstrap-table.js"></script>	    
		<script src="<%=request.getContextPath()%>/js/bootstrap-table-zh-CN.js"></script>
		<script src="<%=request.getContextPath()%>/UI/simple/js/global.js" charset="UTF-8"></script>
 <%
	//获得取过来的参数
	String cookName = request.getParameter("cookName");
	String cookRname = request.getParameter("cookRname");
	String cookPassword = request.getParameter("cookPassword");
	String cookLocation = request.getParameter("cookLocation");
	Integer cookGender = request.getParameter("cookGender").isEmpty() ?null : Integer.parseInt(request.getParameter("cookGender"));
	String cookTel = request.getParameter("cookTel");
	Integer cookWorkedtime = request.getParameter("cookWorkedtime").isEmpty() ? null:Integer.parseInt(request.getParameter("cookWorkedtime"));
	String cookIdnumber = request.getParameter("cookIdnumber");
	String cookLicense = request.getParameter("cookLicense");
	String cookHealthid = request.getParameter("cookHealthid");
	String cookStatus = request.getParameter("cookStatus");
	String cookDesc = request.getParameter("cookDesc");
	String regTime = request.getParameter("regTime");
	Integer cookId=Integer.parseInt(request.getParameter("cookId"));
	String cookIdurl = request.getParameter("cookIdurl");
	String cookLicenseurl = request.getParameter("cookLicenseurl");
	String cookHealthurl = request.getParameter("cookHealthurl");
	String cookIdurl2 = request.getContextPath()+"/"+request.getParameter("cookIdurl");
	String cookLicenseurl2 = request.getContextPath()+"/"+request.getParameter("cookLicenseurl");
	String cookHealthurl2 = request.getContextPath()+"/"+request.getParameter("cookHealthurl");
 %>  
 <script type="text/javascript">
 	$(function(){
 	var cookIdurl1="<%=cookIdurl2%>";
 	var cookLicenseurl1="<%=cookLicenseurl2%>";
 	var cookHealthurl1="<%=cookHealthurl2%>";
 	if(cookIdurl1!=""){
 	$("#idPicture").attr('src',cookIdurl1);
 	}
 	if(cookLicenseurl1!=""){
 	$("#cookPicture").attr('src',cookLicenseurl1);
 	}
 	if(cookHealthurl1!=""){
 	$("#healthPicture").attr('src',cookHealthurl1);
 	}	
 	});
 </script>
  </head>
  
  <body>
      <%@include file="../template/ServerHeader.jsp" %>
    
      
      
      <div class="content clearfix bgf5">
		
		<!-- 顶部标题 -->
	
	<div class="content clearfix bgf5">
	<form  id="aduitCook" action="<%=request.getContextPath()%>/server/AduitAction" method="post">
	<input type="hidden" name="cid" value="<%=cookId%>">
	
		<section class="user-center inner clearfix">
			<div class="user-content__box clearfix bgf">
				<div class="title">客服-审核厨师信息</div>
				
					<div class="shop-title2">厨师信息</div>
					<div class="shop-order__detail">
						<table class="table">
							<tr>
								<td>厨师账号：</td>
								<td><input id=cookName name=cookName type="text" readonly="readonly" value=<%=cookName %>></td>
							</tr>
							<tr>
								<td>厨师姓名：</td>
								<td><input id=cookRname name=cookRname type="text" readonly="readonly" value=<%=cookRname %>></td>
							</tr>						
							<tr>
								<td>厨师位置：</td>
								<td><input id=cookLocation name=cookLocation type="text" readonly="readonly" value=<%=cookLocation %>></td>
							</tr>
							<tr>
								<td>性别：</td>
								<td><input id=cookGender name=cookGender type="text" readonly="readonly" value=<%=cookGender %>></td>
							</tr>
							<tr>
								<td>手机：</td>
								<td><input id=cookTel name=cookTel type="text" readonly="readonly" value=<%=cookTel %>></td>
							</tr>
							<tr>
								<td>工作年限：</td>
								<td><input id=cookWorkedtime name=cookWorkedtime type="text" readonly="readonly" value=<%=cookWorkedtime %>></td>
							</tr>
							<tr>
								<td>身份证号：</td>
								<td><input id=cookIdnumber name=cookIdnumber type="text" readonly="readonly" value=<%=cookIdnumber %>></td>
							</tr>
							<tr>
								<td>厨师证号：</td>
								<td><input id=cookLicense name=cookLicense type="text" readonly="readonly" value=<%=cookLicense %>></td>
							</tr>
							<tr>
								<td>健康证号：</td>
								<td><input id=cookHealthid name=cookHealthid type="text" readonly="readonly" value=<%=cookHealthid %>></td>
							</tr>
							<tr>
								<td>厨师状态：</td>
								<td><input id=cookStatus name=cookStatus type="text" readonly="readonly" value=<%=cookStatus %>></td>
							</tr>
							<tr>
								<td>厨师描述：</td>
								<td><input id=cookDesc name=cookDesc type="text" readonly="readonly" value=<%=cookDesc %>></td>
							</tr>
							<tr>
								<td>注册时间：</td>
								<td><input id=regTime name=regTime type="text" readonly="readonly" value=<%=regTime %>></td>
							</tr>
						</table>
		<div class="row-fluid">
		<ul class="thumbnails">
			<li class="span4">
			  <div class="thumbnail">
                <img  id="idPicture"  alt="身份证照片" style="width: 300px; height: 150px;">
              <div class="caption"><h5 align="center">身份证照片</h5></div>
              </div>
			</li>
			<li class="span4">
			  <div class="thumbnail">
                <img id="cookPicture" alt="厨师证照片" style="width: 300px; height: 150px;">
                 <div class="caption"><h5 align="center">厨师证照片</h5></div>
              </div>
			</li>
			<li class="span4">
			  <div class="thumbnail">
                <img id="healthPicture" alt="健康证照片" style="width: 300px; height: 150px;">
                <div class="caption"><h5 align="center">健康证照片</h5></div>
              </div>
			</li>
			
		</ul>	
		</div>					
					</div>
					<div class="shop-title2">审核结果</div>
					<div class="pay-mode__box2">
					
					<div class="shop-order__detail">
						<span class="label label-default" style="font-size:16px;">审核状态结果：</span>
						<select id="aduitStatus" name="aduitStatus">
						<option></option>
						<option value="1">审核通过</option>
						<option value="0">审核不通过</option>
						</select>
						<br>
						<span class="label label-default" style="font-size:16px;">审核备注：</span>
						<textarea  id="aduitDetail" name="aduitDetail" rows="4" cols="10" style="width:500px;height:85px;margin-left: 30px;"></textarea>					</div>
					<div align="center">
						<input name="" type="button" class="btn btn-default" value="提交审核" onclick="check()" /> <a style="margin-left:10px;" href="<%=request.getContextPath()%>/server/AduitCooks.jsp" role="button" class="btn">返回</a>
					</div>
					</div>

				
			</div>
		</section>
		</form>
	</div>
    </div>
    <%@include file="../template/Footer.jsp" %>  
<script type="text/javascript">
	function check(){
	var aduitStatus=$("#aduitStatus").val();
	var aduitDetail=$("#aduitDetail").val();
		 if(aduitStatus == "")
		 {
		 	alert("请选择一个审核结果！");
		 	return;
		 }
		 if(aduitDetail == "")
		 {
		 	alert("审核备注不能为空！");
		 	return;
		 }
		 $("#aduitCook").submit();	
		 alert("提交审核成功！");
	}
</script>      
      
      
  </body>
</html>
