<%@ page import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>管理员请登录</title>
        <link href="<%=request.getContextPath()%>/css/login.css" rel="stylesheet" type="text/css"></link>
            <link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css" />
                 <style type="text/css">
      body {
        padding-top: 0px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 0px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,    
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.2.js"></script>
        <script type="text/javascript">
        	
        	function check() {
        		//获取用户名和密码
        		var name = $("#username").val();
        		var pwd = $("#password").val();
        		
        		
        		if(name == "") {
        			$("#nameInfo > font").text("请输入用户名");
        		}
        		else {
        			$("#nameInfo > font").text("");
        		}
        		if(pwd == "") {
        			$("#passInfo > font").text("请输入密码");        			
        		} 
        		else {
        			$("#passInfo > font").text("");
        		}       		
        		
        		if(name != "" && pwd != "") {
        			document.myform.submit();        			
        		}
        		
        	}
        	
        	
        </script>
    </head>

    <body> <!--  background="../images/bg.jpg"-->
    
      <div id="login" class="container" style="background:none;margin-top: 90px;">

                     <form class="form-signin" action="<%=request.getContextPath()%>/admin/loginAction" method="post" name="myform" style="background-color: transparent;border:none;">
                       <h5 class="form-signin-heading" style="font-size:30px;">家宴帮-admin</h5>
                       <input name="username" id="username" type="text" class="input-block-level" placeholder="Username"  />
                       <br/> <span id="nameInfo"><font color="red"></font></span> <br/>    
                       <input name="password" id="password" type="password"  class="input-block-level" placeholder="Password"  />
                       <br/><span id="passInfo"><font color="red"></font></span><br/>
                       <input name="" type="button"  class="btn btn-default"  value="登&nbsp;&nbsp;录" value="登&nbsp;&nbsp;录" onclick="check()" />
                       &nbsp;&nbsp;<input name="" type="reset" class="btn btn-default" value="取&nbsp;&nbsp;消" />
                       
                    </form>
                     <font color="red"><s:actionerror /></font>
                </div>
                 <div align="center">
    	<br/>
	<h5 style="color: white">Copyright © 2018, 北京信息科技大学信息管理学院<br/>
	信息管理与信息系统信管1401<br/>
	All Rights Reserved</h5>
    </div>
    </body>
</html>
