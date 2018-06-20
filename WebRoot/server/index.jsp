<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>客服，欢迎您！————您的主页</title>
          <!--  <link href="request.getContextPath()/css/style.css" rel="stylesheet" type="text/css" /> -->
        <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" type="text/css" />  
        <link href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />  
        <script type="text/javascript"	src="<%=request.getContextPath()%>/js/jquery-1.8.2.js"></script>
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
       $("#order1").addClass("active");
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
   <pre>
		<font size="6">欢迎！客服可以做的………… </font>			
  
	<font size="3">
         
         
         – 管理订单变更 
         
         – 厨师信息审核
         
         - 系统通知发布



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
