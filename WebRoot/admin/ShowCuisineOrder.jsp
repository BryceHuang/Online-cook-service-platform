<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>管理员，查看菜系订单数量</title>
        <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" type="text/css" />  
        <link href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css" />  
        <script src="./../UI/simple/js/jquery.1.12.4.min.js"></script>
		<script src="./../UI/echarts/echarts.js"></script>
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
    </head>

    <body>
       <%@include file="../template/AdminHeader.jsp" %>
    <div class="container-fluid">
      <div class="row-fluid">
        <%@include file="../template/AdminLeft.jsp" %>
        <div class="span9">
        
         
          <div class="row-fluid">
 <div >
   <pre> <div id="container" style="width:1000px; height: 700px;margin: auto;"></div></pre>
</div>
            
          </div><!--/row-->
          
        </div><!--/span-->
      </div><!--/row-->

     

     
     </div>
	 <%@include file="../template/Footer.jsp" %>
	</div>
     

    </div><!--/.fluid-container-->
    
      
<script>
$(function () {
$("#order42").addClass("active");
getOrderCount();

});

function getOrderCount(){
 $.ajax({
         url:"./../admin/getCuisineOrderCountAction",
               type:"post",
               dataType:"json",
               data:"",
               aysnc:true,
               success:function(data) {	
                var dom = document.getElementById("container");
				var myChart = echarts.init(dom);
				var app = {};
				option = null;
				option = {
				    title : {
				        text: '八大菜系订单数量',
				        subtext: '',
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        left: 'left',
				        data: ['川菜','鲁菜','粤菜','苏菜','浙菜','闽菜','湘菜','徽菜']
				    },
				    series : [
				        {
				            name: '订单数量',
				            type: 'pie',
				            radius : '55%',
				            center: ['50%', '60%'],
				            data:data,
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        }
				    ]
				};
				;
				if (option && typeof option === "object") {
				    myChart.setOption(option, true);
				}
               
               
               	},
                error:function(ajax) {
                   alert("获取订单数量数据失败！");
               }
           }) ; 

}
</script>   
    </body>
</html>
