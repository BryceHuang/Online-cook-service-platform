<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/UI/simple/favicon.ico">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/iconfont.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/global.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/swiper.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css2/styles.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css2/bootstrap.min.css">
	<link href="<%=request.getContextPath()%>/css/bootstrap-table.css" rel="stylesheet" /> 
	<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.1.12.4.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/bootstrap.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/swiper.min.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/global.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.DJMask.2.1.1.js" charset="UTF-8"></script>
	<script src="<%=request.getContextPath()%>/js/bootstrap-table.js"></script>	    
	<script src="<%=request.getContextPath()%>/js/bootstrap-table-zh-CN.js"></script>
	<style>
	a:link{text-decoration:none ;}
	a:hover{color:#666;}
	</style>
	<title>家宴帮</title>
</head>
<body>
<%@include file="../UI/simple/template/UsualHeader.jsp" %>

	<div class="content clearfix bgf5">
		<%@include file="../UI/simple/template/UserWelcomeLeft.jsp" %>
		<div class="pull-right">
		
						<div class="order-list__div bgf">
					<div class="title" style="color: #666;line-height: 36px;border-bottom: 1px solid #e0e0e0;">
						订单中心-我的订单
					</div>
					<div class="order-panel">
						<ul class="nav user-nav__title" style="height:15px;" role="tablist">
							<li role="presentation" class="nav-item active"><a href="#all" aria-controls="all" role="tab" data-toggle="tab">所有订单</a></li>
							<li role="presentation" class="nav-item "><a href="#emit"  onclick="Table2()" aria-controls="emit" role="tab" data-toggle="tab">待接收 <span class="cr" id="count1">0</span></a></li>
							<li role="presentation" class="nav-item "><a href="#take" onclick="Table3()" aria-controls="take" role="tab" data-toggle="tab">待服务 <span class="cr" id="count2">0</span></a></li>
							<li role="presentation" class="nav-item "><a href="#used" onclick="Table6()" aria-controls="used" role="tab" data-toggle="tab">正服务 <span class="cr" id="count3">0</span></a></li>
							<li role="presentation" class="nav-item "><a href="#pay" onclick="Table4()" aria-controls="pay" role="tab" data-toggle="tab">待结款 <span class="cr" id="count4">0</span></a></li>
							<li role="presentation" class="nav-item "><a href="#eval" onclick="Table5()" aria-controls="eval" role="tab" data-toggle="tab">待评价 <span class="cr" id="count5">0</span></a></li>
						</ul>

						<div class="tab-content">
							<div role="tabpanel" class="tab-pane fade in active" id="all">
								<table id="tb_onlineorder" class=""></table> 
							</div>
							<div role="tabpanel" class="tab-pane fade" id="emit">
								<table id="tb_onlineorder2" class=""></table> 
						    </div>
							<div role="tabpanel" class="tab-pane fade" id="take">
							        <div id="toolbar3" class="btn-group">
							            <a  id ="biangeng" role="button" class="btn" onclick="changeOrderStatus()" data-toggle="modal">
							                <i class="icon-retweet"></i>变更  
							            </a>
							           <a  id ="menu" role="button" class="btn" data-toggle="modal" onclick="showMenu()" style="margin-left:10px;">
							           <i class="icon-zoom-in"></i>查看菜单
							            </a>
							           
							        </div>
								<table id="tb_onlineorder3" class=""></table>							 
						    </div>
						    <div role="tabpanel" class="tab-pane fade" id="used">
								<table id="tb_onlineorder6" class=""></table> 
						    </div>
							<div role="tabpanel" class="tab-pane fade" id="pay">
								<table id="tb_onlineorder4" class=""></table> 
							</div>							
							<div role="tabpanel" class="tab-pane fade" id="eval">
								<table id="tb_onlineorder5" class=""></table> 
							</div>
						</div>
					</div>
				</div>
		</div>
    </div>



		<%@include file="../UI/simple/template/UserRight.jsp" %>
		<%@include file="../UI/simple/template/Footer.jsp" %>
   
 
<!-- Modal -->
<form id="applyChangeForm" >
<input type="hidden" name="oid" id="oid">
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4 id="myModalLabel">变更原因</h4>
  </div>
  <div class="modal-body">
    <textarea rows="4" cols="10" style="width:500px;height:85px;" id="changedetail" name="changedetail"></textarea>
  </div>
  <div class="modal-footer">    
	 <input  type="button" value="提交申请" onclick="applyChange()" class="btn btn-danger">
	 <button id="changeClose" class="btn" data-dismiss="modal" aria-hidden="true" style="margin-left:10px">关闭</button>
  </div>
</div>
</form>


<!-- Modal -->
<form id="showMenuForm" >
<input type="hidden" name="oid" id="oid">
<div id="myMenuModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4 id="myModalLabel">订单的菜单</h4>
  </div>
  <div class="modal-body">
    <textarea rows="4" cols="10" style="width:500px;height:85px;" id="menuDetail" readonly="readonly"  ></textarea>
  </div>
  <div class="modal-footer">    

    
    							
								 <button id="changeClose" class="btn" data-dismiss="modal" aria-hidden="true" style="margin-left:10px">关闭</button>
  </div>
</div>
</form>


<!-- Modal -->
<form id="scoreOrderForm">
<input type="hidden" name="scoreOrderId" id="scoreOrderId">
<div id="myScoreModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4 id="myModalLabel">给订单评分</h4>
  </div>
  <div class="modal-body">
   订单评分：<input  id="orderScore" name="orderscore" type="number"  min="0" max="100" placeholder="请输入0-100的数字" style="height:30px;"><br>
   厨师评分： <input  id="cookScore" name="cookscore" type="number"  min="0" max="100" placeholder="请输入0-100的数字" style="height:30px;"><br>
  订单评论： <textarea rows="4" cols="10" style="width:300px;height:50px;" id="orderComment" name="orderComment"></textarea>
  </div>
  <div class="modal-footer">    
  	<input  type="button" value="提交评分" onclick="applyScore()" class="btn btn-danger">
    <button class="btn" data-dismiss="modal" aria-hidden="true" id="scoreClose">关闭</button>
  </div>
</div>
</form>
<form id="payOrderInfoForm" action="<%=request.getContextPath()%>/user/PayOrders.jsp" method="post">
<input type="hidden" id="payOrderId" name="payOrderId" >
<input type="hidden" id="payOrderCustom" name="payOrderCustom" >
<input type="hidden" id="payOrderFood" name="payOrderFood" >
<input type="hidden" id="payOrderPrice" name="payOrderPrice" >
<input type="hidden" id="payOrderPlace" name="payOrderPlace" >
<input type="hidden" id="payOrderStartTime" name="payOrderStartTime" >
</form>

	<script>
	$(function () {
	
		$("#footer1").css("margin-left","15px");
		$("#footer2").css("margin-left","10px");
		$("#footer3").css("margin-left","10px"); 
	  	$("#logosidename").html("订单中心");
	/*----------------------我的所有订单--------------------------------*/
	  	$("#myOrders").addClass("active");
	//1.初始化Table
   		var oTable = new TableInit();
    	oTable.Init();

    //2.初始化Button的点击事件
  		var oButtonInit = new ButtonInit();
   	oButtonInit.Init(); 
   	$(".form-control").css("height","30px");
   	$(".dropdown-toggle").css("height","25px");
   	getOrderCount();
});
   
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_onlineorder').bootstrapTable({
            url: "<%=request.getContextPath()%>/user/getMyGoingOrderAction.action",         //请求后台的URL（*）
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
            strictSearch: false,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 700,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                field: 'custom_name',
                title: '客户姓名'
            }, {
                field: 'custom_place',
                title: '服务地点'
            }, {
                field: 'custom_tel',
                title: '联系方式'
            }, {
                field: 'is_booked',
                title: '是否预定',
                 formatter: function (value, row, index) {
                if(value==1)
                return "是";
                else  if(value==0)
                return "否";
                }
            }, 
            {
                field: 'order_price',
                title: '订单价格'
            }, {
                field: 'order_status',
                title: '订单状态',
                 formatter: function (value, row, index) {
                if(value==1)
                return "刚发布";
                else  if(value==2)
                return "已接单";
                else  if(value==3)
                return "正服务";
                 else  if(value==4)
                return "待支付";
                 else  if(value==5)
                return "待评分";
                 else  if(value==6)
                return "已结束";                
                }
            },{
                field: 'order_remark',
                title: '订单备注'
            }, {
                field: 'serve_time',
                title: '服务时间',
                 formatter: function (value, row, index) {
                return value.replace("T"," ");}
            },{
                field: 'start_time',
                title: '发布时间',
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
            getOrderKind:"1",
            search: params.search,
            sort:   params.sort,      //排序列名  
            sortOrder: params.order//排位命令（desc，asc） 
            
           
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

    return oInit;
};

/*------------------------------user的待接收订单-----------------------------------------------------------------*/
function Table2(){
	//alert("table2");
	//1.初始化Table
	
    var oTable2 = new TableInit2();
    oTable2.Init();
	
	 //2.初始化Button的点击事件
    var oButtonInit2 = new ButtonInit2();
    oButtonInit2.Init(); 
    $(".fixed-table-pagination").css("margin-top","35");
 	$(".form-control").css("height","30px");
    $(".dropdown-toggle").css("height","25px");
	};

var TableInit2 = function () {
    var oTableInit2 = new Object();
    //初始化Table
    oTableInit2.Init = function () {
        $('#tb_onlineorder2').bootstrapTable({
            url: "<%=request.getContextPath()%>/user/getMyNotRecievedOrderAction.action",         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: false,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit2.queryParams,//传递参数（*）queryParams: oTableInit.queryParams,
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
            height: 700,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                field: 'custom_Name',
                title: '客户姓名'
            }, {
                field: 'custom_Place',
                title: '服务地点'
            }, {
                field: 'custom_Tel',
                title: '联系方式'
            }, {
                field: 'is_booked',
                title: '是否预定',
                 formatter: function (value, row, index) {
                if(value==1)
                return "是";
                else  if(value==0)
                return "否";
                }
            }, 
            {
                field: 'order_price',
                title: '订单价格'
            }, {
                field: 'order_status',
                title: '订单状态',
                 formatter: function (value, row, index) {
                if(value==1)
                return "刚发布";
                else  if(value==2)
                return "已接单";
                else  if(value==3)
                return "正服务";
                 else  if(value==4)
                return "待支付";
                 else  if(value==5)
                return "待评分";
                 else  if(value==6)
                return "已结束";                
                }
            }, {
                field: 'order_remark',
                title: '订单备注'
            }, {
                field: 'serve_time',
                title: '服务时间',
                 formatter: function (value, row, index) {
                return value.replace("T"," ");}
            },{
                field: 'start_time',
                title: '发布时间',
                 formatter: function (value, row, index) {
                return value.replace("T"," ");}
            },{
                field: 'Button',
                title: '操作',
                events:operateEvents,
                formatter:AddFunctionAlty,
               
            },]
        });
    };

    //得到查询的参数
    //oTableInit.queryParams = function (params) {
    oTableInit2.queryParams = function (params) {
         var temp2 = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: (params.offset / params.limit) + 1,  //页码
            getOrderKind:"1",
            search: params.search,
            sort:   params.sort,      //排序列名  
            sortOrder: params.order //排位命令（desc，asc） 
           
        };
        return temp2;
        
    };
    return oTableInit2;
};
 
var ButtonInit2 = function () {
    var oInit2 = new Object();
    var postdata = {};

    oInit2.Init = function () {
        //初始化页面上面的按钮事件
    };

    return oInit2;
};

/*-------------------------待服务的订单--------------------------------------------------------*/
function Table3(){
	//alert("table3");
	//1.初始化Table
    var oTable3 = new TableInit3();
    oTable3.Init();
	
	 //3.初始化Button的点击事件
    var oButtonInit3 = new ButtonInit3();
    oButtonInit3.Init(); 
     $(".fixed-table-pagination").css("margin-top","35");
	};

var TableInit3 = function () {
    var oTableInit3 = new Object();
    //初始化Table
    oTableInit3.Init = function () {
        $('#tb_onlineorder3').bootstrapTable({
            url: "<%=request.getContextPath()%>/user/getMyNotServedOrderAction.action",         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar3',                //工具按钮用哪个容器
            striped: false,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit3.queryParams,//传递参数（*）queryParams: oTableInit.queryParams,
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 3,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 700,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            },{
                field: 'custom_Name',
                title: '客户姓名'
            }, {
                field: 'custom_Place',
                title: '服务地点'
            }, {
                field: 'custom_Tel',
                title: '联系方式'
            }, {
                field: 'is_booked',
                title: '是否预定',
                 formatter: function (value, row, index) {
                if(value==1)
                return "是";
                else  if(value==0)
                return "否";
                }
            }, 
            {
                field: 'order_price',
                title: '订单价格'
            }, {
                field: 'order_status',
                title: '订单状态',
                 formatter: function (value, row, index) {
                if(value==1)
                return "刚发布";
                else  if(value==2)
                return "已接单";
                else  if(value==3)
                return "正服务";
                 else  if(value==4)
                return "待支付";
                 else  if(value==5)
                return "待评分";
                 else  if(value==6)
                return "已结束";                
                }
            },{
                field: 'order_remark',
                title: '订单备注'
            },{
                field: 'cook_id',
                title: '厨师id',
            }, {
                field: 'serve_time',
                title: '服务时间',
                 formatter: function (value, row, index) {
                return value.replace("T"," ");}
            },{
                field: 'start_time',
                title: '发布时间',
                 formatter: function (value, row, index) {
                return value.replace("T"," ");}
            },]
        });
    };

    //得到查询的参数
    //oTableInit.queryParams = function (params) {
    oTableInit3.queryParams = function (params) {
         var temp3 = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: (params.offset / params.limit) + 1,  //页码
            getOrderKind:"1",
            search: params.search,
            sort:   params.sort,      //排序列名  
            sortOrder: params.order //排位命令（desc，asc） 
           
        };
        return temp3;
        
    };
    return oTableInit3;
};
 
var ButtonInit3 = function () {
    var oInit3 = new Object();
    var postdata = {};

    oInit3.Init = function () {
        //初始化页面上面的按钮事件
    };
 	$(".form-control").css("height","30px");
   	$(".dropdown-toggle").css("height","25px");
    return oInit3;
};
/*--------------------------------需要支付尾款的订单-------------------------------------------------*/
function Table4(){
	//alert("table4");
	//1.初始化Table
    var oTable4 = new TableInit4();
    oTable4.Init();
	
	 //4.初始化Button的点击事件
    var oButtonInit4 = new ButtonInit4();
    oButtonInit4.Init(); 
     $(".fixed-table-pagination").css("margin-top","35");
	};

var TableInit4 = function () {
    var oTableInit4 = new Object();
    //初始化Table
    oTableInit4.Init = function () {
        $('#tb_onlineorder4').bootstrapTable({
            url: "<%=request.getContextPath()%>/user/getMyNeedPayOrderAction.action",         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: false,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit4.queryParams,//传递参数（*）queryParams: oTableInit.queryParams,
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 4,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 700,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                field: 'custom_Name',
                title: '客户姓名'
            }, {
                field: 'custom_Place',
                title: '服务地点'
            }, {
                field: 'custom_Tel',
                title: '联系方式'
            }, {
                field: 'is_booked',
                title: '是否预定',
                 formatter: function (value, row, index) {
                if(value==1)
                return "是";
                else  if(value==0)
                return "否";
                }
            }, 
            {
                field: 'order_price',
                title: '订单价格'
            }, {
                field: 'order_status',
                title: '订单状态',
                 formatter: function (value, row, index) {
                if(value==1)
                return "刚发布";
                else  if(value==2)
                return "已接单";
                else  if(value==3)
                return "正服务";
                 else  if(value==4)
                return "待支付";
                 else  if(value==5)
                return "待评分";
                 else  if(value==6)
                return "已结束";                
                }
            },{
                field: 'order_remark',
                title: '订单备注'
            },{
                field: 'cook_id',
                title: '厨师id',
            }, {
                field: 'serve_time',
                title: '服务时间',
                 formatter: function (value, row, index) {
                return value.replace("T"," ");}
            },{
                field: 'start_time',
                title: '发布时间',
                 formatter: function (value, row, index) {
                return value.replace("T"," ");}
            },{
                field: 'Button',
                title: '操作',
                events:operateEvents,
                formatter:AddFunctionAlty3,
            },]
        });
    };

    //得到查询的参数
    //oTableInit.queryParams = function (params) {
    oTableInit4.queryParams = function (params) {
         var temp4 = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: (params.offset / params.limit) + 1,  //页码
            getOrderKind:"1",
            search: params.search,
            sort:   params.sort,      //排序列名  
            sortOrder: params.order //排位命令（desc，asc） 
           
        };
        return temp4;
        
    };
    return oTableInit4;
};
 
var ButtonInit4 = function () {
    var oInit4 = new Object();
    var postdata = {};

    oInit4.Init = function () {
        //初始化页面上面的按钮事件
    };
 	$(".form-control").css("height","30px");
   	$(".dropdown-toggle").css("height","25px");
    return oInit4;
};

/*-------------------------------user的待评价订单数据--------------------------------------------------*/
function Table5(){
	//alert("table5");
	//1.初始化Table
    var oTable5 = new TableInit5();
    oTable5.Init();
	
	 //5.初始化Button的点击事件
    var oButtonInit5 = new ButtonInit5();
    oButtonInit5.Init(); 
     $(".fixed-table-pagination").css("margin-top","35");
	};

var TableInit5 = function () {
    var oTableInit5 = new Object();
    //初始化Table
    oTableInit5.Init = function () {
        $('#tb_onlineorder5').bootstrapTable({
            url: "<%=request.getContextPath()%>/user/getMyScoringOrderAction.action",         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: false,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit5.queryParams,//传递参数（*）queryParams: oTableInit.queryParams,
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 5,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 700,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                field: 'custom_Name',
                title: '客户姓名'
            }, {
                field: 'custom_Place',
                title: '服务地点'
            }, {
                field: 'custom_Tel',
                title: '联系方式'
            }, {
                field: 'is_booked',
                title: '是否预定',
                 formatter: function (value, row, index) {
                if(value==1)
                return "是";
                else  if(value==0)
                return "否";
                }
            }, 
            {
                field: 'order_price',
                title: '订单价格'
            }, {
                field: 'order_status',
                title: '订单状态',
                 formatter: function (value, row, index) {
                if(value==1)
                return "刚发布";
                else  if(value==2)
                return "已接单";
                else  if(value==3)
                return "正服务";
                 else  if(value==4)
                return "待支付";
                 else  if(value==5)
                return "待评分";
                 else  if(value==6)
                return "已结束";                
                }
            },{
                field: 'order_remark',
                title: '订单备注'
            },{
                field: 'cook_id',
                title: '厨师id',
            }, {
                field: 'serve_time',
                title: '服务时间',
                 formatter: function (value, row, index) {
                return value.replace("T"," ");}
            },{
                field: 'start_time',
                title: '发布时间',
                 formatter: function (value, row, index) {
                return value.replace("T"," ");}
            },{
                field: 'Button',
                title: '操作',
                events:operateEvents,
                formatter:AddFunctionAlty4,
            },]
        });
    };

    //得到查询的参数
    //oTableInit.queryParams = function (params) {
    oTableInit5.queryParams = function (params) {
         var temp5 = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: (params.offset / params.limit) + 1,  //页码
            getOrderKind:"1",
            search: params.search,
            sort:   params.sort,      //排序列名  
            sortOrder: params.order //排位命令（desc，asc） 
           
        };
        return temp5;
        
    };
    return oTableInit5;
};
 
var ButtonInit5 = function () {
    var oInit5 = new Object();
    var postdata = {};

    oInit5.Init = function () {
        //初始化页面上面的按钮事件
    };
 	$(".form-control").css("height","30px");
   	$(".dropdown-toggle").css("height","25px");
    return oInit5;
};

    
/*-------------------------------user的正服务的数据获取--------------------------------------------------*/
function Table6(){
	//alert("table6");
	//1.初始化Table
    var oTable6 = new TableInit6();
    oTable6.Init();
	
	 //6.初始化Button的点击事件
    var oButtonInit6 = new ButtonInit6();
    oButtonInit6.Init(); 
     $(".fixed-table-pagination").css("margin-top","35");
	};

var TableInit6 = function () {
    var oTableInit6 = new Object();
    //初始化Table
    oTableInit6.Init = function () {
        $('#tb_onlineorder6').bootstrapTable({
            url: "<%=request.getContextPath()%>/user/getMyServingOrderAction.action",         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: false,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit6.queryParams,//传递参数（*）queryParams: oTableInit.queryParams,
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
            height: 700,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                field: 'custom_Name',
                title: '客户姓名'
            }, {
                field: 'custom_Place',
                title: '服务地点'
            }, {
                field: 'custom_Tel',
                title: '联系方式'
            }, {
                field: 'is_booked',
                title: '是否预定',
                 formatter: function (value, row, index) {
                if(value==1)
                return "是";
                else  if(value==0)
                return "否";
                }
            }, 
            {
                field: 'order_price',
                title: '订单价格'
            }, {
                field: 'order_status',
                title: '订单状态',
                 formatter: function (value, row, index) {
                if(value==1)
                return "刚发布";
                else  if(value==2)
                return "已接单";
                else  if(value==3)
                return "正服务";
                 else  if(value==4)
                return "待支付";
                 else  if(value==5)
                return "待评分";
                 else  if(value==6)
                return "已结束";                
                }
            }, {
                field: 'order_remark',
                title: '订单备注'
            },{
                field: 'cook_id',
                title: '厨师id',
            }, {
                field: 'serve_time',
                title: '服务时间',
                 formatter: function (value, row, index) {
                return value.replace("T"," ");}
            },{
                field: 'start_time',
                title: '发布时间',
                 formatter: function (value, row, index) {
                return value.replace("T"," ");}
            },]
        });
    };

    //得到查询的参数
    //oTableInit.queryParams = function (params) {
    oTableInit6.queryParams = function (params) {
         var temp6 = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: (params.offset / params.limit) + 1,  //页码
            getOrderKind:"1",
            search: params.search,
            sort:   params.sort,      //排序列名  
            sortOrder: params.order //排位命令（desc，asc） 
           
        };
        return temp6;
        
    };
    return oTableInit6;
};
 
var ButtonInit6 = function () {
    var oInit6 = new Object();
    var postdata = {};

    oInit6.Init = function () {
        //初始化页面上面的按钮事件
    };
 	$(".form-control").css("height","30px");
   	$(".dropdown-toggle").css("height","25px");
    return oInit6;
};



function AddFunctionAlty(value,row,index){
return ['<input id="TableEditor" type="button" value="取消"  class="btn"><label style="cursor: pointer" for="'+row['order_id']+'">'].join("")
}
function AddFunctionAlty2(value,row,index){
return ['<a href="#myModal" id ="biangeng" role="button" class="btn" data-toggle="modal">变更</a><label style="cursor: pointer" for="'+row['order_id']+'"><a href="#myMenuModal" id ="menu" role="button" class="btn" data-toggle="modal">查看菜单</a><label style="cursor: pointer" for="'+row['order_Menu']+'">'].join("")
}
function AddFunctionAlty3(value,row,index){
return ['<button id="TableEditorPay" type="button" class="btn">支付</button><label style="cursor: pointer" for="'+row['order_id']+row['custom_Name']+row['custom_Place']+row['order_foodlist']+row['order_price']+row['start_time']+'">'].join("")
}
function AddFunctionAlty4(value,row,index){
return ['<a href="#myScoreModal" id ="scoreButton" role="button" class="btn" data-toggle="modal">评分</a><label style="cursor: pointer" for="'+row['order_id']+'">'].join("")
}


var applyChangeOrderId=0;  //申请变更的order的id
var scoreOrderId=0;

window.operateEvents = {   
	'click #TableEditor': function(e,value,row,index){
	 var orderId=row['order_id'];
	 var param = "oid="+orderId;
	 			$.ajax({
				   type: "POST",
				   url: "<%=request.getContextPath()%>/user/cancelOrderAction",
				   data: param,
				   dataType :"json",
					                success:function(data) {
					                
					                  // $("#myform")[0].reset();
  var opt = {
    url: "<%=request.getContextPath()%>/user/getMyNotRecievedOrderAction.action",
    silent: true,
    query:{
      type:1,
      level:2
    }
  };
  $("#tb_onlineorder2").bootstrapTable('refresh', opt);										
									   var strArray=eval(data);
					                   var content=strArray.message_content;
					                   var title=strArray.message_title;					                
					                   $("#messageTitle").text(title);
					                   $("#messageContent").text(content);					               
									   $("#messagebox").slideDown("slow");
									   setTimeout("hideMessageBox()",4000); 									                   						                   
					                },
					                error:function(ajax) {
					                    alert("取消订单失败！");
					                
					}}); 
					},
	'click #biangeng':function(e,value,row,index){
	 applyChangeOrderId=row['order_id'];
	 //var param = "oid="+orderId;
	 			//alert(param);
					},
    'click #menu':function(e,value,row,index){
	 $("#menuDetail").val(row['order_Menu']);
	 //var param = "oid="+orderId;
	 			//alert(param);
					},
	'click #TableEditorPay':function(e,value,row,index){
	$("#payOrderId").val(row['order_id']);
	$("#payOrderCustom").val(row['custom_Name']);
	$("#payOrderFood").val(row['order_foodlist']);
	$("#payOrderPrice").val(row['order_price']);
	$("#payOrderPlace").val(row['custom_Place']);
	$("#payOrderStartTime").val(row['start_time']);
	$("#payOrderInfoForm").submit();
	
	//alert("test	");
	//document.payOrderInfoForm.submit();
					},
	'click #scoreButton':function(e,value,row,index){
	 scoreOrderId=row['order_id'];
	 //var param = "oid="+orderId;
	 			//alert(param);
					},		
}

function changeOrderStatus(){

//使用getSelections即可获得，row是json格式的数据
var getSelectRows = $("#tb_onlineorder3").bootstrapTable('getSelections', function (row) {
          return row;
});
if(getSelectRows.length<1)
{
alert("请至少选择一条记录！");
return;
}
if(getSelectRows.length>1)
{
alert("只能选择一条记录！");
return;
}
applyChangeOrderId=getSelectRows['0'].order_id;
$("#myModal").modal("show");

}

function showMenu(){

//使用getSelections即可获得，row是json格式的数据
var getSelectRows = $("#tb_onlineorder3").bootstrapTable('getSelections', function (row) {
          return row;
});
if(getSelectRows.length<1)
{
alert("请至少选择一条记录！");
return;
}
if(getSelectRows.length>1)
{
alert("只能选择一条记录！");
return;
}
//$("#menu").attr("href","#myMenuModal");
$("#myMenuModal").modal("show");
$("#menuDetail").val(getSelectRows['0'].order_Menu);
}
function applyScore(){
$("#scoreOrderId").val(scoreOrderId);
  $.ajax({
					          url:"<%=request.getContextPath()%>/user/scoreOrderAction",
					                type:"post",
					                dataType:"json",
					                data:$("#scoreOrderForm").serialize(),
					                aysnc:true,
					                success:function(data) {
					                  // $("#myform")[0].reset();
					                  $("#scoreClose").click();
					                  var opt = {
									    url: "<%=request.getContextPath()%>/user/getMyScoringOrderAction.action",
									    silent: true,
									    query:{
									      type:1,
									      level:2
									    }
									  };
									  $("#tb_onlineorder5").bootstrapTable('refresh', opt);
					               
									   var strArray=eval(data);
					                   var content=strArray.message_content;
					                   var title=strArray.message_title;
					                   $("#messageTitle").text(title);
					                   $("#messageContent").text(content);
									   $("#messagebox").slideDown("slow");
									   setTimeout("hideMessageBox()",4000); 									                   						                   
					                },
					                error:function(ajax) {
					                    alert("评分失败！");
					                }
					            }) ; 
//$("#scoreOrderForm").submit();
}
function applyChange(){ 
$("#oid").val(applyChangeOrderId);
  $.ajax({
         url:"<%=request.getContextPath()%>/user/changeOrderAction",
               type:"post",
               dataType:"json",
               data:$("#applyChangeForm").serialize(),
               aysnc:true,
               success:function(data) {
                 // $("#myform")[0].reset();
                 $("#changeClose").click();
			   var strArray=eval(data);
                  var content=strArray.message_content;
                  var title=strArray.message_title;
                  $("#messageTitle").text(title);
                  $("#messageContent").text(content);
			   $("#messagebox").slideDown("slow");
			   setTimeout("hideMessageBox()",4000); 									                   						                   
               },
               error:function(ajax) {
                   alert("变更申请失败！");
               }
           }) ; 
}  
function getOrderCount(){

	 $.ajax({
         url:"./../user/getOrderCountAction",
               type:"post",
               dataType:"json",
               data:"",
               aysnc:true,
               success:function(data) {
               $("#count1").text(data[1]);
               $("#count2").text(data[2]);
               $("#count3").text(data[3]);
               $("#count4").text(data[4]);
               $("#count5").text(data[5]);
                        	   						                   						                   
               },
               error:function(ajax) {
                   alert("获取订单数量信息失败！");
               }
           }) ; 
}


	</script>
</body>
</html>