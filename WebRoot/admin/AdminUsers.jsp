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
 
   <script type="text/javascript">
      window.onload = function(){
       $("#order3").addClass("active");
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
   <div class="panel-body" style="padding-bottom:0px;">
             

        
              
       <pre>
        <table id="tb_onlineorder" class="table table-hover"></table></pre>
    </div>

            
          </div><!--/row-->
          
        </div><!--/span-->
      </div><!--/row-->

     

     
     </div>
	 <%@include file="../template/Footer.jsp" %>
	
     

  </div>
<!-- Modal -->
<form id="changeUserForm" action="" method="post">
<input type="hidden" name="userId" id="userId">
<div id="myUserModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4 id="myModalLabel">编辑用户信息</h4>
  </div>
  <div class="modal-body"> 
  <table>
	<tr>
		<td>用户名：</td>
		<td><input id=userName name=userName type="text"/></td>
	</tr>
	<tr>
		<td>昵称：</td>
		<td><input id=userRname name=userRname type="text"/></td>
	</tr>
	<tr>
		<td>密码：</td>
		<td><input id=userPassword name=userPassword type="text"/></td>
	</tr>
	<tr>
		<td>性别：</td>
		<td><select id=userGender name=userGender><option value="0">女</option><option value="1">男</option></select></td>
	</tr>
	<tr>
		<td>年龄：</td>
		<td><input id=userAge name=userAge type="number" min="0" max="100"/></td>
	</tr>
	<tr>
		<td>位置：</td>
		<td><input id=userLocation name=userLocation type="text"/></td>
	</tr>
	<tr>
		<td>邮箱：</td>
		<td><input id=userEmail name=userEmail type="text"/></td>
	</tr>
	<tr>
		<td>电话：</td>
		<td><input id=userTel name=userTel type="text"/></td>
	</tr>
	<tr>
		<td>状态：</td>
		<td><select id=userStatus name=userStatus><option value="1">活动</option><option value="0">禁用</option></td>
	</tr>
	<tr>
		<td>身份证号：</td>
		<td><input id=userSpecific name=userSpecific type="text"/></td>
	</tr>
	<tr>
		<td>用户评分：</td>
		<td><input id=userScore name=userScore type="number" min="0" max="100"/></td>
	</tr>
	<tr>
		<td>剩余金额：</td>
		<td><input id=userMoney name=userMoney type="number"/></td>
	</tr>
	<tr>
		<td>注册时间：</td>
		<td><input id=regTime name=regTime type="text"/></td>
	</tr>
</table> 
  </div>
  <div class="modal-footer"> 
  <input id="submitDetail" type="button"  class="btn btn-primary"  onclick="applyUser()" value="提交信息">   
    <button class="btn" data-dismiss="modal" aria-hidden="true" id="changeClose">关闭</button>
  </div>
  </div>
  </div>
  </form>    
      
 <script>
    
    $(function () {

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
            url: "<%=request.getContextPath()%>/admin/getAllUsersAction.action",         //请求后台的URL（*）
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
                field: 'user_id',
                title: '用户id'
            },{
                field: 'user_name',
                title: '用户账号'
            }, {
                field: 'user_rname',
                title: '用户姓名'
            }, {
                field: 'user_password',
                title: '用户密码'
            }, {
                field: 'user_gender',
                title: '性别'
            }, {
                field: 'user_age',
                title: '年龄'
            }, 
            {
                field: 'user_location',
                title: '地点'
            }, {
                field: 'user_level',
                title: '用户级别'
            }, {
                field: 'user_email',
                title: '用户邮箱'
            }, {
                field: 'user_tel',
                title: '电话'
            }, 
            {
                field: 'user_status',
                title: '状态'
            },{
                field: 'user_specific',
                title: '身份证号码'
            }, {
                field: 'user_score',
                title: '信用分'
            }, {
                field: 'user_money',
                title: '余额'
            }, {
                field: 'reg_time',
                title: '注册时间',
                formatter: function (value, row, index) {
                	if(value!=null)
                	return value.replace("T"," ");
                	else 
                	return value;
                }
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
return ['<a href="#myUserModal" id ="changeButton" role="button" class="btn" data-toggle="modal">编辑</a><label style="cursor: pointer" for="'+row['user_id']+row['user_name']+row['user_rname']+row['user_password']+row['user_gender']+row['user_age']+row['user_location']+row['user_email']+row['user_tel']+row['user_status']+row['user_specific']+row['user_score']+row['user_money']+row['reg_time']+'">'].join("")
}
window.operateEvents = {   
	'click #changeButton': function(e,value,row,index){
	$("#userId").val(row['user_id']);
	$("#userName").val(row['user_name']);
	$("#userRname").val(row['user_rname']);
	$("#userPassword").val(row['user_password']);
	$("#userGender").val(row['user_gender']);
	$("#userAge").val(row['user_age']);
	$("#userLocation").val(row['user_location']);
	$("#userEmail").val(row['user_email']);
	$("#userTel").val(row['user_tel']);
	$("#userStatus").val(row['user_status']);
	$("#userSpecific").val(row['user_specific']);
	$("#userScore").val(row['user_score']);
	$("#userMoney").val(row['user_money']);
	$("#regTime").val(row['reg_time']);	
	}
}
function applyUser(){
/*$("#changeUserForm").submit();
alert("更新信息成功！");*/
 $.ajax({
         url:"<%=request.getContextPath()%>/admin/updateUser",
               type:"post",
               dataType:"json",
               data:$("#changeUserForm").serialize(),
               aysnc:true,
               success:function(data) {
			 $("#changeClose").click(); 
			   var opt = {
				    url: "<%=request.getContextPath()%>/admin/getAllUsersAction.action",
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
