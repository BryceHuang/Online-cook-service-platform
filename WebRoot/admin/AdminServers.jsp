<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>管理员，欢迎您！————您的主页</title>
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
		<script src="<%=request.getContextPath()%>/UI/simple/js/global.js" charset="UTF-8"></script>
		<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.DJMask.2.1.1.js" charset="UTF-8"></script>
		<script src="<%=request.getContextPath()%>/js/bootstrap-table.js"></script>	    
		<script src="<%=request.getContextPath()%>/js/bootstrap-table-zh-CN.js"></script>
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
   <div class="panel-body" style="padding-bottom:0px;">
             

        
                
       <pre>
        <table id="tb_onlineorder"></table></pre>
    </div>

            
          </div><!--/row-->
          
        </div><!--/span-->
      </div><!--/row-->

     

     
     </div>
	 <%@include file="../template/Footer.jsp" %>
	
     

  </div>

 <!-- Modal -->
<form id="ServerForm">
<input id="adminId" name="adminId" type="hidden">
<div id="myServerModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4 id="myModalLabel">编辑订单信息</h4>
  </div>
  <div class="modal-body">   
   <table>
	<tr>
		<td>用户名：</td>
		<td><input id=adminName name=adminName type="text"/></td>
	</tr>
	<tr>
		<td>用户密码：</td>
		<td><input id=adminPassword name=adminPassword type="text"/></td>
	</tr>
	<tr>
		<td>用户昵称：</td>
		<td><input id=adminRname name=adminRname type="text"/></td>
	</tr>
	<tr>
		<td>用户状态：</td>
		<td><input id=adminStatus name=adminStatus type="text"/></td>
	</tr>
	<tr>
		<td>所在组别：</td>
		<td><input id=adminGroup name=adminGroup type="text"/></td>
	</tr>

	<tr>
		<td>用户级别：</td>
		<td><input id=adminLevel name=adminLevel type="text"/></td>
	</tr>
</table>
  </div>
    <div class="modal-footer">    
   	<input id="submitDetail" type="button"  class="btn btn-primary"  onclick="applyOrder()" value="提交信息">
    <button class="btn" data-dismiss="modal" aria-hidden="true" id="changeClose">关闭</button>
  </div>
  </div>
  </form>      
 <script>
    
    $(function () {
	 $("#order2").addClass("active");
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();
      $(".form-control").css("height","30px");
   	$(".dropdown-toggle").css("height","25px");

});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_onlineorder').bootstrapTable({
            url: "<%=request.getContextPath()%>/admin/getAllServersAction.action",         //请求后台的URL（*）
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
            height: 700,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [ {
                field: 'admin_id',
                title: '用户id',
                visible:false,
            },{
                field: 'admin_name',
                title: '用户账号'
            }, {
                field: 'admin_rname',
                title: '用户姓名'
            }, {
                field: 'admin_password',
                title: '用户密码'
            }, {
                field: 'admin_level',
                title: '用户级别'
            }, {
                field: 'admin_status',
                title: '用户状态',
                formatter :function (value,row,index){
                	if(value=="1")
                	return "活动";
                	else if(value=="0")
                	return "禁用";
                }
            }, {
                field: 'admin_group',
                title: '所在组'
            }, 
            {
                field: 'reg_time',
                title: '添加时间',
                formatter: function (value, row, index) {
                return value.replace("T"," ");}
            }, {
                field: 'Button',
                title: '操作',
                events:operateEvents,
                formatter:AddFunctionAlty,
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

    return oInit;
};
function AddFunctionAlty(value,row,index){
return ['<a href="#myServerModal" id ="changeButton" role="button" class="btn" data-toggle="modal">编辑</a><label style="cursor: pointer" for="'+row['admin_group']+row['admin_id']+row['admin_name']+row['admin_password']+row['admin_rname']+row['admin_status']+row['admin_level']+'">'].join("")
}
window.operateEvents = {   
	'click #changeButton': function(e,value,row,index){
	$("#adminId").val(row['admin_id']);
	$("#adminName").val(row['admin_name']);
	$("#adminPassword").val(row['admin_password']);
	$("#adminRname").val(row['admin_rname']);
	$("#adminStatus").val(row['admin_status']);
	$("#adminLevel").val(row['admin_level']);	
	$("#adminGroup").val(row['admin_group']);
	}
}

function applyOrder(){
  $.ajax({
					          url:"<%=request.getContextPath()%>/admin/updateServerAction.action",
					                type:"post",
					                dataType:"json",
					                data:$("#ServerForm").serialize(),
					                aysnc:true,
					                success:function(data) {
									 $("#changeClose").click(); 
									   var opt = {
										    url: "<%=request.getContextPath()%>/admin/getAllServersAction.action",
										    silent: true,
										    query:{
										      type:1,
										      level:2
										    }
										  }; 
									  $("#tb_onlineorder").bootstrapTable('refresh', opt);	
									 var strArray=eval(data);
					                   var content=strArray.message_content;
					                   var title=strArray.message_title;
					                   $("#messageTitle").text(title);
					                   $("#messageContent").text(content);
									   $("#messagebox").slideDown("slow");
									   setTimeout("hideMessageBox()",4000);							                   						                   
					                },
					                error:function(ajax) {
					                    alert("用户数据更新失败！");
					                }
					            }) ; 
					            }
    </script>    
    </body>
</html>
