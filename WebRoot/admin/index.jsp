<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>管理员，欢迎您！————您的主页</title>
          <!--  <link href="request.getContextPath()/css/style.css" rel="stylesheet" type="text/css" /> -->
        <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" type="text/css" />  
        <link href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />  
        <script type="text/javascript"	src="<%=request.getContextPath()%>/UI/simple/js/jquery.1.12.4.min.js"></script>
        <script type="text/javascript"	src="<%=request.getContextPath()%>/UI/echarts/echarts.js"></script>
        <script type="text/javascript"	src="<%=request.getContextPath()%>/UI/echarts/china.js"></script>
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
       $("#order1").addClass("active");
    }
     
     
    </script>
    </head>

    <body>
       <%@include file="../template/AdminHeader.jsp" %>
    <div class="container-fluid">
      <div class="row-fluid">
        <%@include file="../template/AdminLeft.jsp" %>
        <div class="span9">
        
         
          <div class="row-fluid">
 <div >
   <pre>
		<font size="6">欢迎！管理员可以做的………… </font>			
  
	<font size="3">
         
         
         – 用户管理用于查询现有注册用户信息，以及启用、禁用用户账户。 
         
         – 订单管理用于管理系统已生成的订单。 



</font>
						</pre>
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
