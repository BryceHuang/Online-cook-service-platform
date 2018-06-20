<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>用户注册</title>
       <!--  <link href="request.getContextPath()/css/style.css" rel="stylesheet" type="text/css" /> -->
        <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" type="text/css" />  
        <link href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />  
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
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.2.js"></script>
        <script type="text/javascript">
       
	
        	function check() {
        		var username = $("#username").val();
        		var password = $("#password").val();
        		var confirmPass = $("#confirmPass").val();       	
        		var haserror=false;
        		
        		if(password == "") {
        			$("#passwordInfo > font").text("请输入密码");
        			haserror=true;
        		}
        		else {
        			$("#passwordInfo > font").text("");
        		}
        		if(confirmPass == "") {
        			$("#confirmPassInfo > font").text("请输入确认密码");
        			haserror=true;
        		}
        		else {
        			if(password != confirmPass) {
        				$("#confirmPassInfo > font").text("确认密码与密码不一致");
        				haserror=true;
        			}
        			else {
        				$("#confirmPassInfo > font").text("");
        			}
        		}        		
        		if(!haserror)
        			document.myform.submit();
        	 	else 
        	 		return;
        
        	}
        	
        	/*
        	//计算年龄
        	function changeAge() {
        		
        		var idCard = $("#idCard").val();        		
        		var icYear = idCard.substr(6 , 4);        		
        		
        		var d = new Date();
        		var cYear = d.getFullYear();        		
        		
        		var age = parseInt(cYear) - parseInt(icYear);
        		$("#age").text(age);
        		
        	}
        	
        	function isCardNo(card) {   
        	
        	var pattern = /(^\d{18}$)|(^\d{17}(\d|X|x)$)/;   
        	return pattern.test(card);  
        	}    
        	*/
        	
        </script>
    </head>

    <body>
   <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid" >
          <a class="brand" href="#" align="center"> 普通用户注册页面</a>
          
        </div>
      </div>
    </div>
    
    
    
        <div class="container-fluid">
      <div class="row-fluid">
       
        
        
        <div class="row-fluid">
          <div id="main_page">          
            <div id="body">
              
                <div id="content">
                   
                    <div class="main">
                    	
                    	<font color="darkblue" size=3 style="margin-left:20%"><b>请填写以下信息：</b></font>
                    	<form action="<%=request.getContextPath()%>/user/registrationAction" method="post" name="myform">
                    		<div style="text-align:center">
                    		<table class="table table-bordered" style="width: 60%;margin:auto">
                    			<tr>
                    				<td>用户名：</td>
                    				<td>
                    					<input type="text" name="username" id="username" style="height:25px;">
                    				</td>
                    				<td>
                    					<span id="usernameInfo"><font color="red"></font></span>
                    				</td>
                    			</tr>
                    			<tr>
                    				<td>密&nbsp;&nbsp;码：</td>
                    				<td>
                    					<input type="password" name="password" id="password" style="height:25px;">
                    				</td>
                    				<td>
                    					<span id="passwordInfo"><font color="red" ></font></span>
                    				</td>
                    			</tr>
                    			<tr>
                    				<td>密码确认：</td>
                    				<td>
                    					<input type="password" name="confirmPass" id="confirmPass" style="height:25px;">
                    				</td>
                    				<td>
                    					<span id="confirmPassInfo"><font color="red"></font></span>
                    				</td>
                    			</tr>
                    			<tr align="center">
                    				<td colspan="3" >
                    					<div align="center">
                    					<input type="button" value="注册" onclick="check()" class="btn">
                    					<input type="reset" value="重置"  class="btn" style="margin-left:15px;">
                    					</div>
                    				</td>
                    			</tr>
                    		</table>
                    		</div>
                    	</form>
					
					   
                </div>  <!--content-->
            </div>  <!--body-->
        </div> 
            
           
            
            
          </div><!--/row-->
          <div class="row-fluid">
           
         
          </div><!--/row-->
        </div><!--/span-->
      </div><!--/row-->



     
       <%@include file="../template/Footer.jsp" %>
     

    </div><!--/.fluid-container-->
    
    
    
    
      
    </body>
</html>

