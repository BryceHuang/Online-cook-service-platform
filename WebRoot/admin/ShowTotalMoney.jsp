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
		<script src="./../UI/echarts/dark.js"></script>
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
$("#order43").addClass("active");
getTotalMoney();

});
function GetDateStr(AddDayCount) { 
    var dd = new Date(); 
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期 
    var y = dd.getFullYear(); 
    var m = dd.getMonth()+1;//获取当前月份的日期 
    var d = dd.getDate(); 
    return y+"-"+m+"-"+d; 
}

function getTotalMoney(){
 $.ajax({
         url:"./../admin/getTotalMoneyAction",
               type:"post",
               dataType:"json",
               data:"",
               aysnc:true,
               success:function(data) {	
					   
				var dom = document.getElementById("container");
				var myChart = echarts.init(dom,"dark");
				var app = {};
				option = null;
				option = {
				    title: {
				        text: '系统订单总成交额过去七天走势',
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['总成交额']
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    toolbox: {
				        feature: {
				            saveAsImage: {}
				        }
				    },
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        data: [GetDateStr(-7),GetDateStr(-6),GetDateStr(-5),GetDateStr(-4),GetDateStr(-3),GetDateStr(-2),GetDateStr(-1)]
				    },
				    yAxis: {
				        type: 'value'
				    },
				    series: [
				     
				        {
				            name:'总成交额',
				            type:'line',
				            stack: '总量',
				            data:data
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
