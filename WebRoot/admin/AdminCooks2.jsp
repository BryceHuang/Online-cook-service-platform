<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>管理员，欢迎您！————您的主页</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/bootstrap/css/bootstrap.min.css">
		<link href="<%=request.getContextPath()%>/css/bootstrap-table.css" rel="stylesheet" /> 
		<script src="<%=request.getContextPath()%>/UI/jquery/jquery.min.js" charset="UTF-8"></script>
		<script src="<%=request.getContextPath()%>/UI/bootstrap/js/bootstrap.min.js" charset="UTF-8"></script>
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
       <%@include file="../template/AdminHeaderNew.jsp" %>
    <div class="container-fluid">
       <div class="row">
        <%@include file="../template/AdminLeftNew.jsp" %>
        
         <pre>
        <table id="tb_onlineorder"></table>
        </pre>
      </div>
      
	</div>
     

     

	 <%@include file="../template/Footer.jsp" %>
<!-- Modal -->
<form id="scoreOrderForm" action="" method="post">
<input type="hidden" name="cookId" id="cookId">
<div id="myScoreModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4 id="myModalLabel">编辑厨师信息</h4>
  </div>
  <div class="modal-body">  
<table>
	<tr>
		<td>用户名：</td>
		<td><input id=cookName name=cookName type="text"/></td>
	</tr>
	<tr>
		<td>昵称：</td>
		<td><input id=cookRname name=cookRname type="text"/></td>
	</tr>
	<tr>
		<td>密码：</td>
		<td><input id=cookPassword name=cookPassword type="text"/></td>
	</tr>
	<tr>
		<td>地点：</td>
		<td><input id=cookLocation name=cookLocation type="text"/></td>
	</tr>
	<tr>
		<td>性别</td>
		<td><select id=cookGender name=cookGender><option value="0">女</option><option value="1">男</option></select></td>
	</tr>
	<tr>
		<td>联系方式：</td>
		<td><input id=cookTel name=cookTel type="text"/></td>
	</tr>
	<tr>
		<td>厨师评分：</td>
		<td><input id=cookScore name=cookScore type="number" min="0" max="100"/></td>
	</tr>
	<tr>
		<td>工作年限</td>
		<td><input id=cookWorkedtime name=cookWorkedtime type="number" min="0" max="100"/></td>
	</tr>
	<tr>
		<td>年龄</td>
		<td><input id=cookAge name=cookAge type="number" min="18" max="100"/></td>
	</tr>
	<tr>
		<td>身份证号：</td>
		<td><input id=cookIdnumber name=cookIdnumber type="text"/></td>
	</tr>
	<tr>
		<td>厨师证：</td>
		<td><input id=cookLicense name=cookLicense type="text"/></td>
	</tr>
	<tr>
		<td>健康证：</td>
		<td><input id=cookHealthid name=cookHealthid type="text"/></td>
	</tr>
	<tr>
		<td>厨师状态：</td>
		<td><input id=cookStatus name=cookStatus type="text"/></td>
	</tr>
	<tr>
		<td>厨师描述：</td>
		<td><input id=cookDesc name=cookDesc type="text"/></td>
	</tr>
	<tr>
		<td>剩余金额：</td>
		<td><input id=cookMoney name=cookMoney type="text"/></td>
	</tr>
	<tr>
		<td>注册时间：</td>
		<td><input id=regTime name=regTime type="text"/></td>
	</tr>
	
</table> 
  </div>
  <div class="modal-footer">    
    <input id="submitDetail" type="button"  class="btn btn-primary"  onclick="applyCook()" value="提交信息">
    <button class="btn" data-dismiss="modal" aria-hidden="true" id="changeClose">关闭</button>
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
            url: "<%=request.getContextPath()%>/admin/getAllCooksAction.action",         //请求后台的URL（*）
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
            columns: [  {
                field: 'cook_id',
                title: '厨师号'
            },{
                field: 'cook_name',
                title: '厨师账号'
            }, {
                field: 'cook_rname',
                title: '厨师姓名'
            }, {
                field: 'cook_password',
                title: '用户密码'
            }, {
                field: 'cook_location',
                title: '地区'
            }, {
                field: 'cook_gender',
                title: '性别'
            },  {
                field: 'cook_age',
                title: '年龄'
            }, 
            {
                field: 'cook_tel',
                title: '电话'
            }, {
                field: 'cook_score',
                title: '信用分'
            }, {
                field: 'cook_workedtime',
                title: '工作年限'
            }, {
                field: 'cook_idnumber',
                title: '身份证号码'
            }, 
            {
                field: 'cook_license',
                title: '厨师证号码'
            },{
                field: 'cook_healthid',
                title: '健康证号码'
            }, {
                field: 'cook_status',
                title: '厨师状态'
            },  {
                field: 'aduit_person',
                title: '审核人'
            }, {
                field: 'aduit_detail',
                title: '审核备注'
            },{
                field: 'cook_desc',
                title: '描述'
            }, {
                field: 'cook_money',
                title: '系统钱包'
            },{
                field: 'reg_time',
                title: '注册时间',
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
return ['<a href="#myScoreModal" id ="changeButton" role="button" class="btn" data-toggle="modal">编辑</a><label style="cursor: pointer" for="'+row['cook_age']+row['cook_id']+row['cook_name']+row['cook_rname']+row['cook_password']+row['cook_location']+row['cook_gender']+row['cook_tel']+row['cook_score']+row['cook_workedtime']+row['cook_idnumber']+row['cook_license']+row['cook_healthid']+row['cook_status']+row['cook_desc']+row['cook_money']+'">'].join("")
}
window.operateEvents = {   
	'click #changeButton': function(e,value,row,index){
	$("#cookId").val(row['cook_id']);
	$("#cookName").val(row['cook_name']);
	$("#cookRname").val(row['cook_rname']);
	$("#cookPassword").val(row['cook_password']);
	$("#cookLocation").val(row['cook_location']);
	$("#cookGender").val(row['cook_gender']);
	$("#cookTel").val(row['cook_tel']);
	$("#cookScore").val(row['cook_score']);
	$("#cookWorkedtime").val(row['cook_workedtime']);
	$("#cookIdnumber").val(row['cook_idnumber']);
	$("#cookLicense").val(row['cook_license']);
	$("#cookHealthid").val(row['cook_healthid']);
	$("#cookStatus").val(row['cook_status']);
	$("#cookDesc").val(row['cook_desc']);
	$("#cookMoney").val(row['cook_money']);
	$("#regTime").val(row['reg_time']);
	$("#cookAge").val(row['cook_age']);
	//alert(row['order_id']);
	}
}


function applyCook(){
/*$("#scoreOrderForm").submit();
alert("更新信息成功！");*/
 $.ajax({
         url:"<%=request.getContextPath()%>/admin/updateCook",
               type:"post",
               dataType:"json",
               data:$("#scoreOrderForm").serialize(),
               aysnc:true,
               success:function(data) {
			 $("#changeClose").click(); 
			   var opt = {
				    url: "<%=request.getContextPath()%>/admin/getAllCooksAction.action",
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
