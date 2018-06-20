<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>客服，欢迎您！————您的主页</title>
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
<!-- 	<div class="panel-body" style="padding-bottom:0px;">  </div> -->
      <table id="tb_onlineorder"></table>
						</pre>
</div>
            
          </div><!--/row-->
          
        </div><!--/span-->
      </div><!--/row-->

     

     
     </div>
	 <%@include file="../template/Footer.jsp" %>
	</div>
     

    </div><!--/.fluid-container-->
    
      
<script>


	$(function() {
		
        
        
        $("#myMessagePage").addClass("active");
         
	//1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init(); 
	});
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_onlineorder').bootstrapTable({
            url: "<%=request.getContextPath()%>/server/getMessage.action",         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: false,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）queryParams: oTableInit.queryParams,
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                field: 'message_sender',
                title: '发送者'
            }, {
                field: 'message_reciever',
                title: '接收人'
            }, 
            {
                field: 'message_title',
                title: '标题'
            }, {
                field: 'message_content',
                title: '信息内容'
            }, {
                field: 'send_time',
                title: '发送时间',
                 formatter: function (value, row, index) {
                return value.replace("T"," ");}
            },]
        });
    };

    //得到查询的参数
    //oTableInit.queryParams = function (params) {
    oTableInit.queryParams = function (params) {
         var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: (params.offset / params.limit) + 1,  //页码
            search: params.search,
            sort:   params.sort,      //排序列名  
            sortOrder: params.order //排位命令（desc，asc） 
           
        };
        return temp;
        
    };
    return oTableInit;
};
 
var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    };
    $(".form-control").css("height","30px");
   	$(".dropdown-toggle").css("height","25px");
    return oInit;
};

</script>    
    </body>
</html>
