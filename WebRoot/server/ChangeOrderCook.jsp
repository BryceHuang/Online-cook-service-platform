<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>客服-处理厨师订单变更申请</title>
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
       $("#order30").addClass("active");
       
    }
     
     
    </script>
    </head>

    <body>
       <%@include file="../template/ServerHeader.jsp" %>
    <div class="container-fluid">
      <div class="row-fluid">
        <%@include file="../template/ServerLeft.jsp" %>
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
<form id="applyChangeForm" action="" method="post">
<input type="hidden" name="oid" id="oid">
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4 id="myModalLabel">变更管理</h4>
  </div>
  <div class="modal-body">
	<div>
	    变更状态：<select id="isChange" name="isChange">
    		<option></option>
    		<option value=1>变更通过</option>
    		<option value=2>变更不通过</option>
    	</select>
	</div>
 	<div>
 	   变更详细：<textarea rows="4" cols="10" style="width:500px;height:85px;" id="changeDetail" name="changeDetail"></textarea>
 	</div>
  </div>
  <div class="modal-footer">    
    <input  class="btn btn-primary"  type="button" class="btn btn-default" value="提交变更" onclick="applyChange()" />
    <button class="btn" data-dismiss="modal" aria-hidden="true" id="closeForm">关闭</button>
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
            url: "<%=request.getContextPath()%>/server/getCookChangeOrder.action",         //请求后台的URL（*）
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
            pageList: [10,25,50,100],         //可供选择的每页的行数（*）
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
                title: '发单用户'
            }, {
                field: 'cook_id',
                title: '接单厨师'
            },{
                field: 'order_id',
                title: '订单号'
            }, {
                field: 'custom_Name',
                title: '客户姓名'
            }, {
                field: 'custom_Place',
                title: '服务地点'
            }, {
                field: 'custom_Tel',
                title: '客户联系方式'
            }, 
            {
                field: 'is_booked',
                title: '是否预订'
            }, {
                field: 'order_price',
                title: '订单价格'
            }, {
                field: 'order_status',
                title: '订单状态'
            }, {
                field: 'order_has_material',
                title: '原料'
            }, 
            {
                field: 'order_kind',
                title: '订单类别'
            },{
                field: 'order_foodlist',
                title: '菜式'
            }, {
                field: 'order_remark',
                title: '订单备注'
            }, {
                field: 'start_time',
                title: '发布时间',
                formatter: function (value, row, index) {
                if(value!=null) 
                return  value.replace("T"," ");
                else
                return value;}
            },{
                field: 'serve_time',
                title: '服务时间',
                formatter: function (value, row, index) {
                if(value!=null) 
                return  value.replace("T"," ");
                else
                return value;}
            },{
                field: 'insure_time',
                title: '接单时间',
                formatter: function (value, row, index) {
                if(value!=null) 
                return  value.replace("T"," ");
                else
                return value;}
            },{
                field: 'is_change',
                title: '是否变更'
            },{
            	field:'change_applicant',
            	title:'申请人'
            },{
                field: 'change_person',
                title: '变更者'
            },{
                field: 'change_time',
                title: '变更时间',
                
               formatter: function (value, row, index) {
                if(value!=null) 
                return  value.replace("T"," ");
                else
                return value;}
            },{
                field: 'change_detail',
                title: '申请备注'
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
return ['<a href="#myModal" id="TableEditor"  role="button" class="btn" data-toggle="modal">管理</a><label style="cursor: pointer" for="'+row['order_id']+'">'].join("")
}
window.operateEvents = {   
	'click #TableEditor': function(e,value,row,index){
	 $("#oid").val(row['order_id']);
	}
}
function applyChange(){
	var hasError=false;
	var isChange=$("#isChange").val();
	var changeDetail=$("#changeDetail").val();
	if(isChange == "")
	{
		alert("请选择一个变更状态！");
		hasError=true;
	}
	if(changeDetail == "")
	{
		alert("请输入变更详情！");
		hasError=true;
	}
	if(!hasError)
	{
		$.ajax({
		       url:"<%=request.getContextPath()%>/server/ChangeOrder",
               type:"post",
               dataType:"json",
               data:$("#applyChangeForm").serialize(),
               aysnc:false,
               success:function(data) { 
               $("#closeForm").click();
	           var opt = {
				    url: "<%=request.getContextPath()%>/server/getCookChangeOrder.action",
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
                   alert("失败！");
               }
           }) ; 
	}
	else
		return;
}
    </script>    
    </body>
</html>
