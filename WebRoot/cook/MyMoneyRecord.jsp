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
						用户中心-金额操作记录
					</div>
					<table id="tb_moneyhistroy" class=""></table> 
			</div>
		</div>
    </div>
	
		<%@include file="../UI/simple/template/CookRight.jsp" %>
		<%@include file="../UI/simple/template/Footer.jsp" %>
	
<script>
   
    $(function () {
   
            $("#myMoneyRecordPage").addClass("active");
        $("#logosidename").html("用户中心");
  var oTable = new TableInit();
    	oTable.Init();

  		var oButtonInit = new ButtonInit();
   	oButtonInit.Init();
    $(".fixed-table-pagination").css("margin-top","35");
 	$(".form-control").css("height","30px");
    $(".dropdown-toggle").css("height","25px");
    oButtonInit.Init();

});

 
 
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_moneyhistroy').bootstrapTable({
            url: "<%=request.getContextPath()%>/cook/getMoneyRecordAction.action",         //请求后台的URL（*）
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
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                field: 'operate_kind',
                title: '操作类型',
                formatter: function (value, row, index) {
                if (value == 1) {
                    return "充值";
                } else if(value == 2) {
                    return "提现";
                }
                else if(value== 3){
                	return "支付";
                }
            }
            }, {
                field: 'original_money',
                title: '原金额'
            }, {
                field: 'left_money',
                title: '剩余金额'
            }, {
                field: 'operate_money',
                title: '操作金额'
            }, 
            {
                field: 'bank_kind',
                title: '银行类型'
            }, {
                field: 'card_id',
                title: '银行卡号'
            
            }, {
                field: 'operate_time',
                title: '操作时间',
                formatter: function (value, row, index) {
                return value.replace("T"," ");}
            }, ]
        });
    }
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

    return oInit;
};

</script>	
</body>
</html>