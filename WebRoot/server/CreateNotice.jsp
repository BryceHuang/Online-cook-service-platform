<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>客服-发布通知消息</title>
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
         <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
    
      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
      }
    </style>
 
   <script type="text/javascript">
	window.onload = function(){
		$("#order4").addClass("active");
	   
	}
	function check(){
	var title=$("#titile").val();
	var reciever=$("#reciever").val();
	var content=$("#content").val();	
	var haserror=false;
	if(title=="")
		{
			alert("请输入标题！");
			haserror=true;	
		}
	if(reciever=="")
		{
			alert("请选择接收人！");
			haserror=true;	
		}
	if(content=="")
		{
			alert("请输入内容！");
			haserror=true;	
		}
	if(!haserror)
		{
		 $.ajax({
		       url:"<%=request.getContextPath()%>/server/createNotice",
               type:"post",
               dataType:"json",
               data:$("#noticeForm").serialize(),
               aysnc:false,
               success:function(data) {              
               $("#noticeForm")[0].reset();					                
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
	}
     
    
     
    </script>
    </head>

<body>
	<%@include file="../template/ServerHeader.jsp" %>
	<div class="container-fluid">
	<div class="row-fluid">
	<%@include file="../template/ServerLeft.jsp" %>
	<div class="span9">
	<div class="row-fluid">
	
	<div >

	 <form id="noticeForm" class="form-horizontal">
        <div class="shop-title2">发布通知</div>	
					<div class="pay-mode__box2">
					<div class="shop-order__detail">
					
					<table class="table">
						<tr>
							<td><span class="label label-default" style="font-size:16px;">接收人：</span></td>
							<td>
								<select id="reciever" name="reciever" >
								<option></option>
								<option value="1">普通用户</option>
								<option value="2">厨师</option>
								<option value="3">客服</option>
								</select>
							</td>
						</tr>
						<tr>
							<td><span class="label label-default" style="font-size:16px;">通知标题：</span></td>
							<td><input type="text" id="title" name="title" style="height:28px;width:220px;"></td>
						</tr>
						<tr>
							<td><span class="label label-default" style="font-size:16px;">通知内容：</span></td>
							<td><textarea  id="content" name="content" rows="4" cols="10" style="width:500px;height:85px;"></textarea>	</td>
						</tr>
						<tr>
							<td colspan="2">	
								<div align="center" style="margin-top:10px;">
								<input name="" type="button" class="btn btn-default" value="发布通知" onclick="check()" /> 
								<input type="reset" value="取消"  class="btn btn-default"  >
								</div>
							</td>
						</tr>
					</table>					
        </form>
	

	</div>

	</div><!--/row-->

	</div><!--/span-->
	</div><!--/row-->
	</div>
	<%@include file="../template/Footer.jsp" %>
	</div>
	</div><!--/.fluid-container-->



</body>
</html>
