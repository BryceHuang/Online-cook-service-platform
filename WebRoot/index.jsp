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
<title>家宴帮</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/UI/mainpage/css/bootstrap.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/UI/mainpage/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/UI/mainpage/css/jquery.bxslider.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/UI/mainpage/js/jquery-1.11.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/UI/mainpage/js/jquery.bxslider.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/UI/mainpage/js/modernizr.custom.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/UI/mainpage/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/UI/mainpage/js/jquery.dlmenu.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/UI/mainpage/js/jquery.flexisel.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/UI/mainpage/js/jquery.prettyPhoto.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/UI/mainpage/js/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/UI/mainpage/js/jquery.ajax-contact-form.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/UI/mainpage/js/script.js"></script>
	
  </head>

  <body>
  
  <header class="header"> 
    <div class="container">

	  <div class="row">
 	  
	    <div class="col-md-3">
		  <div class="logo">
		    <a href="index.jsp">家宴帮</a>
		  </div>
		</div>
		
		<div class="col-md-9">
                  <div class="menu">
                    <div id="dl-menu" class="dl-menuwrapper">
                    <button class="dl-trigger">Open Menu</button>
                      <ul class="dl-menu">
                        <li class="current"><a href="index.jsp">首页</a></li>
                        <li><a href="<%=request.getContextPath()%>/user/Login.jsp">登录</a></li>
                            <li><a href="<%=request.getContextPath()%>/user/Registration.jsp">注册</a></li>
                            <li ><a href="#">关于</a></li>
                        
                       
                        <li><a href="#">联系</a></li>
                      </ul>
                    </div> 
                  </div><!-- /.menu --> 
		</div>
		
	  </div>

	</div>
  </header>	

 <!-- start slider -->
 <div style="height:450px;">
    <div class="slider">
        <ul class="bxslider" id="slider">
            <li>
                <img src="<%=request.getContextPath()%>/UI/mainpage/images/slides/slide1.jpg" style="height:450px;" /></li>
            <li>
                <img src="<%=request.getContextPath()%>/UI/mainpage/images/slides/slide2.jpg"  style="height:450px;" /></li>
            <li>
                <img src="<%=request.getContextPath()%>/UI/mainpage/images/slides/slide3.jpg" style="height:450px;"  /></li>
           
        </ul>
    </div>
</div>
    <!-- end slider -->
	
  <!-- start content -->
  <div class="content">
  
    <!-- start promobox -->		
	<div class="promobox text-center">
	  <div class="container">
	    <h1>家宴帮！ 在线私厨<br/>为您提供优质的厨师上门服务</h1>
	    <a href="javascript:void(0)" class="btn btn-transparent">更多</a>
	  </div>
	</div>
    <!-- end promobox -->	
	
  <!-- start block -->		
  <div class="block">
    <div class="container">
	
	  <div class="row">
	    
		<div class="col-md-12">
	
	      <h2 class="headline">优秀厨师</h2>
          <span class="line">
            <span class="sub-line"></span>
          </span>
	  
	      <ul id="recent-works">
	      <li>
          <img class="img-responsive" alt="image" src="<%=request.getContextPath()%>/UI/mainpage/images/work/1.jpg">
          <h3>
          <small>
          <a href="#">cook1</a>
          </small>
          </h3>
          </li>

	      <li>
          <img class="img-responsive" alt="image" src="<%=request.getContextPath()%>/UI/mainpage/images/work/2.jpg">
          <h3>
          <small>
          <a href="#">cook2</a>
          </small>
          </h3>
          </li>

	      <li>
          <img class="img-responsive" alt="image" src="<%=request.getContextPath()%>/UI/mainpage/images/work/3.jpg">
          <h3>
          <small>
          <a href="#">cook3</a>
          </small>
          </h3>
          </li>
	 
	      <li>
          <img class="img-responsive" alt="image" src="<%=request.getContextPath()%>/UI/mainpage/images/work/4.jpg">
          <h3>
          <small>
          <a href="#">cook4</a>
          </small>
          </h3>
          </li>	 
	 
	      <li>
          <img class="img-responsive" alt="image" src="<%=request.getContextPath()%>/UI/mainpage/images/work/5.jpg">
          <h3>
          <small>
          <a href="#">cook5</a>
          </small>
          </h3>
          </li>	

	      <li>
          <img class="img-responsive" alt="image" src="<%=request.getContextPath()%>/UI/mainpage/images/work/6.jpg">
          <h3>
          <small>
          <a href="#">cook6</a>
          </small>
          </h3>
          </li>	

	      <li>
          <img class="img-responsive" alt="image" src="<%=request.getContextPath()%>/UI/mainpage/images/work/7.jpg">
          <h3>
          <small>
          <a href="#">cook7</a>
          </small>
          </h3>
          </li>		 
	      </ul>
	  
	    </div>
	
	  </div>
	
	</div>
  </div>
  <!-- end block -->  
    
  <!-- start block -->		
  <div class="block">
    <div class="container">
	
	      <h2 class="headline">关于我们</h2>
          <span class="line">
            <span class="sub-line"></span>
          </span>
		
	  <div class="row">
	  
	    <div class="col-md-12">
          
          <h3>在线私厨定制系统</h3>
          <br /><br />
          <p><img class="pull-left" alt="img" src="<%=request.getContextPath()%>/UI/mainpage/images/home-about.jpg" width="450" style="padding:0 20px 20px 0;">	</p>
          
          <h4>  系统将为您提供资格认证的厨师，让您吃饱的同时更能吃好，在家就能享受星级酒店大厨级别的服务。</h4>
		</div>
		
	    <div class="col-md-6">
          	
		</div>
	
	  </div>
	  
	  
	  
	</div>
  </div>
  <!-- end block -->  
  
  
 
  
 
	
  </div>
  <!-- end content -->

 <%@include file="../template/Footer.jsp" %>
<div style="height:20px;padding-top:10px;"></div>
    <!-- JavaScript
    ================================================== -->

  </body>
</html>
