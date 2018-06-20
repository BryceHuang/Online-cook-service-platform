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
<%@include file="../UI/simple/template/CookHeader.jsp" %>

	<div class="content clearfix bgf5">
		<%@include file="../UI/simple/template/CookLeft.jsp" %>
		<div class="pull-right">
		<div class="order-list__div bgf">
								<div class="title" style="color: #666;line-height: 36px;border-bottom: 1px solid #e0e0e0;">
						订单中心-我的订单
					</div>
					<div class="order-panel">
						<ul class="nav user-nav__title" style="height:15px;" role="tablist">
							<li role="presentation" class="nav-item active"><a href="#all" aria-controls="all" role="tab" data-toggle="tab">所有订单</a></li>
							<li role="presentation" class="nav-item "><a href="#emit"  onclick="Table2()" aria-controls="emit" role="tab" data-toggle="tab" >待服务 <span class="cr" id="count2">0</span></a></li>
							<li role="presentation" class="nav-item "><a href="#take" onclick="Table3()" aria-controls="take" role="tab" data-toggle="tab" >正服务<span class="cr" id="count3">0</span></a></li>
							<li role="presentation" class="nav-item "><a href="#used" onclick="Table4()" aria-controls="used" role="tab" data-toggle="tab" >待评分 <span class="cr" id="count4">0</span></a></li>
							
						</ul>

						<div class="tab-content">
							<div role="tabpanel" class="tab-pane fade in active" id="all">
								<table id="tb_onlineorder" class=""></table> 
							</div>
							<div role="tabpanel" class="tab-pane fade" id="emit">
									<div id="toolbar2" class="btn-group">
							            <a  id ="start" role="button" class="btn" onclick="startOrder()" data-toggle="modal">
							                <i class="icon-play"></i>开始
							            </a>
							           <a  id ="biangeng" role="button" class="btn" data-toggle="modal" onclick="submitChange()" style="margin-left:10px;">
							           <i class="icon-retweet"></i>变更
							           </a>
							           <a  id ="uploadMenu" role="button" class="btn" data-toggle="modal" onclick="uploadMenu()" style="margin-left:10px;">
							           <i class="icon-chevron-up"></i>上传菜单
							           </a>
							           
							        </div>
								<table id="tb_onlineorder2" class=""></table> 
						    </div>
							<div role="tabpanel" class="tab-pane fade" id="take">
								<table id="tb_onlineorder3" class=""></table>							 
						    </div>
						    <div role="tabpanel" class="tab-pane fade" id="used">
								<table id="tb_onlineorder4" class=""></table> 
						    </div>	
						</div>
					</div>
    </div>
	</div>
	</div>
		<%@include file="../UI/simple/template/CookRight.jsp" %>
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
    <textarea rows="4" cols="10" style="width:500px;height:85px;" id="changeDetail" name="changedetail"></textarea>
  </div>
  <div class="modal-footer">    
    <input  type="button" class="btn btn-danger" id="submitDetail" onclick="applyChange()" value="提交申请"></input>
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</form>

<!-- Modal -->
<form id="submitMenuForm"  >
<input type="hidden" name="oid" id="menuOrderId">
<div id="myMenuModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4 id="myModalLabel">订单菜单</h4>
  </div>
  <div class="modal-body">
    <textarea rows="4" cols="10" style="width:500px;height:85px;" id="orderMenu" name="orderMenu"></textarea>
  </div>
  <div class="modal-footer">    
    <input  type="button" class="btn btn-danger" id="submitMenuButton" onclick="submitMenu()" value="提交菜单"></input>
    <button class="btn" data-dismiss="modal" aria-hidden="true" id="menuClose">关闭</button>
  </div>
</div>
</form>
<!-- Modal -->
<form id="scoreOrderForm" >
<input type="hidden" name="scoreOrderId" id="scoreOrderId">
<div id="myScoreModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4 id="myModalLabel">评分</h4>
  </div>
  <div class="modal-body">
    <input  id="score" name="score" type="number"  min="0" max="100" placeholder="请输入0-100的数字" style="height:30px;">
    
  </div>
  <div class="modal-footer">    

      	<input  type="button" value="提交评分" onclick="scoreOrder()" class="btn btn-danger">
    <button class="btn" data-dismiss="modal" aria-hidden="true" id="scoreClose">关闭</button>
  </div>
</div>
</form>


	
<script>
	  
    $(function () {

			
	$("#myOrders").addClass("active");
	$("#footer1").css("margin-left","15px");
	$("#footer2").css("margin-left","10px");
	$("#footer3").css("margin-left","10px"); 
	$("#logosidename").html("订单中心");
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
            url: "<%=request.getContextPath()%>/cook/getAllOrderAction.action",         //请求后台的URL（*）
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
            pageList: [10, 25, 50, 100],         //可供选择的每页的行数（*）
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
            columns: [ {
                field: 'order_id',
                title: '订单号码'
            }, {
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
            }, 
            {
                field: 'serve_time',
                title: '服务时间',
                 formatter: function (value, row, index) {
                 return value.replace("T"," ");  
                 }
                
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

    return oInit;
};

/*------------------------------cook的待服务订单-----------------------------------------------------------------*/
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
    $($(".setTdDisplay").parent()).css("display","block");
	};

var TableInit2 = function () {
    var oTableInit2 = new Object();
    //初始化Table
    oTableInit2.Init = function () {
        $('#tb_onlineorder2').bootstrapTable({
            url: "<%=request.getContextPath()%>/cook/getMyRecievedOrder.action",         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar2',                //工具按钮用哪个容器
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
            }, {
                field: 'order_remark',
                title: '订单备注'
            }, {
                field: 'serve_time',
                title: '服务时间',
                 formatter: function (value, row, index) {
                 return value.replace("T"," ");  
                 }
            },{
                field: 'start_time',
                title: '发布时间',
                 formatter: function (value, row, index) {
                 return value.replace("T"," ");  
                 }
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

/*-------------------------正服务的订单--------------------------------------------------------*/
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
            url: "<%=request.getContextPath()%>/cook/getMyServingOrder.action",         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
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
            },  {
                field: 'order_remark',
                title: '订单备注'
            }, {
                field: 'serve_time',
                title: '服务时间',
                 formatter: function (value, row, index) {
                 return value.replace("T"," ");  
                 }
            },{
                field: 'start_time',
                title: '发布时间',
                 formatter: function (value, row, index) {
                 return value.replace("T"," ");  
                 }
            },{
                field: 'Button',
                title: '操作',
                events:operateEvents,
                formatter:AddFunctionAlty2,
                width:75,
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
            url: "<%=request.getContextPath()%>/cook/getMyScoringOrder.action",         //请求后台的URL（*）
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
            }, {
                field: 'serve_time',
                title: '服务时间',
                 formatter: function (value, row, index) {
                 return value.replace("T"," ");  
                 }
            },{
                field: 'start_time',
                title: '发布时间',
                 formatter: function (value, row, index) {
                 return value.replace("T"," ");  
                 }
            },{
                field: 'Button',
                title: '操作',
                events:operateEvents,
                formatter:AddFunctionAlty3,
                 width:75,
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




function AddFunctionAlty(value,row,index){
return ['<div style="display:block" class="setTdDisplay"><button id="TableEditor" type="button" class="btn " >开始</button><label style="cursor: pointer" for="'+row['order_id']+'"></div><div style="display:block" class="setTdDisplay"><a href="#myModal" id ="biangeng" role="button" class="btn " data-toggle="modal">变更</a><label style="cursor: pointer" for="'+row['order_id']+'"></div><a href="#myMenuModal" id ="submitOrderMenu" role="button" class="btn " data-toggle="modal">上传菜单</a><label style="cursor: pointer" for="'+row['order_id']+'">'].join("")
}
function AddFunctionAlty2(value,row,index){
return ['<button id="OrderChange" type="button" class="btn">结束</button><label style="cursor: pointer" for="'+row['order_id']+'">'].join("")
}
function AddFunctionAlty3(value,row,index){
return ['<a href="#myScoreModal" id ="scoreOrder" role="button" class="btn " data-toggle="modal">评分</a><label style="cursor: pointer" for="'+row['order_id']+'">'].join("")
}



var applyChangeOrderId=0;  //申请变更的order的id
var scoreOrerId=0;
var menuOrderId=0;
window.operateEvents = {   
	'click #TableEditor': function(e,value,row,index){
 	 var orderId=row['order_id'];
	 var param = "oid="+orderId;
	 			$.ajax({
				   type: "POST",
				   url: "<%=request.getContextPath()%>/cook/StartServeAction",
				   data: param,
				   dataType :"text",
				   success: function(data){
				   		if(data == "开始失败") {
				   			alert(data);
				   		}
	        			else
	        			alert("成功开始！");
	        			location.reload();
					}
			   }); 
					},
	'click #biangeng':function(e,value,row,index){
	 applyChangeOrderId=row['order_id'];
	 //var param = "oid="+orderId;
	 			//alert(param);
					},
	'click #submitOrderMenu':function(e,value,row,index){
	menuOrderId=row['order_id'];
	},
	'click #OrderChange': function(e,value,row,index){
 	var orderId=row['order_id'];
	 var param = "oid="+orderId;
	 			$.ajax({
				   type: "POST",
				   url: "<%=request.getContextPath()%>/cook/StopServeAction",
				   data: param,
				   dataType :"text",
				   success: function(data){
				   		if(data == "结束失败") {
				   			alert(data);
				   		}
	        			else
	        			alert("成功结束！");
	        			location.reload();
					}
			   }); 
					},
			'click #scoreOrder':function(e,value,row,index){
	 scoreOrerId=row['order_id'];
					},					
}
		function uploadMenu(){
			var getSelectRows = $("#tb_onlineorder2").bootstrapTable('getSelections', function (row) {
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
			menuOrderId=getSelectRows['0'].order_id;
			$("#myMenuModal").modal("show");
		}
		function submitChange(){
			var getSelectRows = $("#tb_onlineorder2").bootstrapTable('getSelections', function (row) {
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
		
		function startOrder(){
			var getSelectRows = $("#tb_onlineorder2").bootstrapTable('getSelections', function (row) {
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
			if(getSelectRows.length==1)
			{
			var param = "oid="+getSelectRows['0'].order_id;
	 			$.ajax({
				   type: "POST",
				   url: "<%=request.getContextPath()%>/cook/StartServeAction",
				   data: param,
				   dataType :"text",
				   success: function(data){
				   		if(data == "开始失败") {
				   			alert(data);
				   		}
	        			else
	        			alert("成功开始！");
	        			location.reload();
					}
			   }); 
			}
		}
function applyChange(){ 
$("#oid").val(applyChangeOrderId);
$.ajax({
				   type: "POST",
				   url: "<%=request.getContextPath()%>/cook/changeOrderAction",
				   data: $("#applyChangeForm").serialize(),
				   dataType :"text",
				   success: function(data){
				   		if(data == "提交变更信息成功") {
				   			alert("提交变更信息成功");
				   		}
	        			else
	        			alert("提交变更信息成功!");
	        			location.reload();
					}
			   }); 
}  
function scoreOrder(){ 
$("#scoreOrderId").val(scoreOrerId);
  $.ajax({
					          url:"<%=request.getContextPath()%>/cook/scoreOrderAction",
					                type:"post",
					                dataType:"json",
					                data:$("#scoreOrderForm").serialize(),
					                aysnc:true,
					                success:function(data) {
					                  // $("#myform")[0].reset();
					                  $("#scoreClose").click();
					                    var opt = {
    url: "<%=request.getContextPath()%>/cook/getMyScoringOrder.action",
    silent: true,
    query:{
      type:1,
      level:2
    }
  };
 									 $("#tb_onlineorder4").bootstrapTable('refresh', opt);
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

function submitMenu(){ 
$("#menuOrderId").val(menuOrderId);
  $.ajax({
					          url:"<%=request.getContextPath()%>/cook/submitMenuAction",
					                type:"post",
					                dataType:"json",
					                data:$("#submitMenuForm").serialize(),
					                aysnc:true,
					                success:function(data) {
					                  // $("#myform")[0].reset();
					                  $("#menuClose").click();
					                    var opt = {
									    url: "<%=request.getContextPath()%>/cook/getMyRecievedOrder.action",
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
					                    alert("上传菜单失败！");
					                }
					            }) ; 
}  
function getOrderCount(){

	 $.ajax({
         url:"./../cook/getOrderCountAction",
               type:"post",
               dataType:"json",
               data:"",
               aysnc:true,
               success:function(data) {
               var count4=data[4]+data[5]+data[6];
               $("#count2").text(data[2]);
               $("#count3").text(data[3]);
               $("#count4").text(count4);                        	   						                   						                   
               },
               error:function(ajax) {
                   alert("获取订单数量信息失败！");
               }
           }) ; 


}
	</script>	
</body>
</html>