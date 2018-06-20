<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>管理员，管理订单</title>
        		<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/iconfont.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/global.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/swiper.min.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/UI/simple/css/styles.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
	    <link href="<%=request.getContextPath()%>/css/bootstrap-datetimepicker.min.css"  rel="stylesheet" media="screen" type="text/css" />
		<link href="<%=request.getContextPath()%>/css/bootstrap-table.css" rel="stylesheet" /> 
		<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.1.12.4.min.js" charset="UTF-8"></script>
		<script src="<%=request.getContextPath()%>/UI/simple/js/bootstrap.min.js" charset="UTF-8"></script>
		<script src="<%=request.getContextPath()%>/UI/simple/js/swiper.min.js" charset="UTF-8"></script>
		<script src="<%=request.getContextPath()%>/UI/simple/js/jquery.DJMask.2.1.1.js" charset="UTF-8"></script>
		<script src="<%=request.getContextPath()%>/js/bootstrap-table.js"></script>	    
		<script src="<%=request.getContextPath()%>/js/bootstrap-table-zh-CN.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker.zh-CN.js"></script>
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
       $("#order4").addClass("active");
       
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
        <table id="tb_onlineorder"></table></pre>
    </div>

            
          </div><!--/row-->
          
        </div><!--/span-->
      </div><!--/row-->

     

     
     </div>
	 <%@include file="../template/Footer.jsp" %>
	
     

  </div>
    
<!-- Modal -->
<form id="OrderForm" action="" method="post">
<input type="hidden" name="cookId" id="cookId">
<div id="myOrderModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h4 id="myModalLabel">编辑订单信息</h4>
  </div>
  <div class="modal-body"> 
 <table>
	<tr>
		<td>订单id：</td>
		<td><input id=orderId name=orderId type="text"/></td>
	</tr>
	<tr>
		<td>用户id：</td>
		<td><input id=userId name=userId type="text"/></td>
	</tr>
	<tr>
		<td>厨师id：</td>
		<td><input id=cookId name=cookId type="text"/></td>
	</tr>
	<tr>
		<td>客户名称：</td>
		<td><input id=customName name=customName type="text"/></td>
	</tr>
	<tr>
		<td>服务地点：</td>
		<td><input id=customPlace name=customPlace type="text"/></td>
	</tr>
	<tr>
		<td>客户电话：</td>
		<td><input id=customTel name=customTel type="text"/></td>
	</tr>
	<tr>
		<td>是否预定：</td>
		<td><input id=isBooked name=isBooked type="text"/></td>
	</tr>
	<tr>
		<td>订单价格：</td>
		<td><input id=orderPrice name=orderPrice type="number"/></td>
	</tr>
	<tr>
		<td>订单状态：</td>
		<td><input id=orderStatus name=orderStatus type="text"/></td>
	</tr>
	<tr>
		<td>订单类别：</td>
		<td><input id=orderKind name=orderKind type="text"/></td>
	</tr>
	<tr>
		<td>原料：</td>
		<td><input id=orderHasMeterial name=orderHasMeterial type="text"/></td>
	</tr>
	<tr>
		<td>订单菜式：</td>
		<td><input id=orderFoodlist name=orderFoodlist type="text"/></td>
	</tr>
	<tr>
		<td>订单备注：</td>
		<td><input id=orderRemark name=orderRemark type="text"/></td>
	</tr>
	<tr>
		<td>订单备注：</td>
		<td><input id=orderComment name=orderComment type="text"/></td>
	</tr>
	<tr>
		<td>订单评分：</td>
		<td><input id=orderScore name=orderScore type="number" min="0" max="100"/></td>
	</tr>
	<tr>
		<td>厨师得分：</td>
		<td><input id=orderScoreToCook name=orderScoreToCook type="number" min="0" max="100"/></td>
	</tr>
	<tr>
		<td>用户得分：</td>
		<td><input id=orderScoreToUser name=orderScoreToUser type="number" min="0" max="100"/></td>
	</tr>
	<tr>
		<td>开始时间：</td>
		<td> 
			<div class="controls input-append date form_datetime"  data-link-field="startTime">
            <input  size="16" type="text"  id="startShowTime" readonly >                   
			<span class="add-on"><i class="icon-th"></i></span>
            </div>
			<input type="hidden" id="startTime" name="startTime" value="" />
		</td>
	</tr>
	<tr>
		<td>服务时间：</td>
		<td>
			<div class="controls input-append date form_datetime"  data-link-field="serveTime">
            <input  size="16" type="text"  id="serveShowTime" readonly >                   
			<span class="add-on"><i class="icon-th"></i></span>
            </div>
			<input type="hidden" id="serveTime" name="serveTime" value="" />
		</td>
	</tr>
	<tr>
		<td>接单时间：</td>
		<td>
			<div class="controls input-append date form_datetime"  data-link-field="insureTime">
            <input  size="16" type="text"  id="insureShowTime" readonly >                   
			<span class="add-on"><i class="icon-th"></i></span>
            </div>
			<input type="hidden" id="insureTime" name="insureTime" value="" />
		</td>
	</tr>
	<tr>
		<td>变更状态：</td>
		<td><input id=isChange name=isChange type="text"/></td>
	</tr>
	<tr>
		<td>变更人员：</td>
		<td><input id=changePerson name=changePerson type="text"/></td>
	</tr>
	<tr>
		<td>变更时间：</td>
		<td>
			<div class="controls input-append date form_datetime"  data-link-field="changeTime">
            <input  size="16" type="text"  id="changeShowTime" readonly >                   
			<span class="add-on"><i class="icon-th"></i></span>
            </div>
			<input type="hidden" id="changeTime" name="changeTime" value="" />
		</td>
	</tr>
	<tr>
		<td>变更内容：</td>
		<td><input id=changeDetail name=changeDetail type="text"/></td>
	</tr>
</table> 
  
   
  </div>
    <div class="modal-footer">  
    <input id="submitDetail" type="button"  class="btn btn-primary"  onclick="applyOrder()" value="提交信息">  
    <button id="changeClose" class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
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
	 $('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1,
    });

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_onlineorder').bootstrapTable({
            url: "<%=request.getContextPath()%>/admin/getAllOrdersAction.action",         //请求后台的URL（*）
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
            columns: [{
                field: 'order_id',
                title: '订单id'
            }, {
                field: 'user_id',
                title: '发单用户'
            }, {
                field: 'cook_id',
                title: '接单厨师'
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
                field: 'order_comment',
                title: '订单评论'
            }, {
                field: 'order_score',
                title: '订单评分'
            }, {
                field: 'order_score_ToCook',
                title: '用户评分'
            },{
                field: 'order_score_ToUser',
                title: '厨师评分'
            },{
                field: 'start_time',
                title: '发布时间',
                formatter: function(value, row, index){
                return changeDateFormat(value);
                }               
            },{
                field: 'serve_time',
                title: '服务时间',
                formatter: function(value, row, index){
                return changeDateFormat(value);
                }               
            },{
                field: 'insure_time',
                title: '接单时间',
                formatter: function(value, row, index){
                return changeDateFormat(value);
                }               
            },{
                field: 'is_change',
                title: '是否变更'
            },{
                field: 'change_applicant',
                title: '申请变更'
            },{
                field: 'change_person',
                title: '变更者'
            },{
                field: 'change_time',
                title: '变更时间',
                formatter: function(value, row, index){
                return changeDateFormat(value);
                }               
            },{
                field: 'change_detail',
                title: '变更备注'
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
return ['<a href="#myOrderModal" id ="changeButton" role="button" class="btn" data-toggle="modal">编辑</a><label style="cursor: pointer" for="'+row['order_score_ToUser']+row['order_score_ToCook']+row['order_has_material']+row['change_applicant']+row['order_id']+row['user_id']+row['cook_id']+row['custom_Name']+row['custom_Place']+row['custom_Tel']+row['is_booked']+row['order_price']+row['order_status']+row['order_kind']+row['order_foodlist']+row['order_remark']+row['order_comment']+row['order_score']+row['start_time']+row['serve_time']+row['insure_time']+row['is_change']+row['change_person']+row['change_time']+row['change_detail']+'">'].join("")
}
window.operateEvents = {   
	'click #changeButton': function(e,value,row,index){
	$("#orderId").val(row['order_id']);
	$("#orderScoreToUser").val(row['order_score_ToUser']);
	$("#orderScoreToCook").val(row['order_score_ToCook']);
	$("#orderHasMaterial").val(row['order_has_material']);
	$("#userId").val(row['user_id']);
	$("#cookId").val(row['cook_id']);
	$("#customName").val(row['custom_Name']);
	$("#customPlace").val(row['custom_Place']);
	$("#customTel").val(row['custom_Tel']);
	$("#isBooked").val(row['is_booked']);
	$("#orderPrice").val(row['order_price']);
	$("#orderStatus").val(row['order_status']);
	$("#orderKind").val(row['order_kind']);
	$("#orderFoodlist").val(row['order_foodlist']);
	$("#orderRemark").val(row['order_remark']);
	$("#orderComment").val(row['order_comment']);
	$("#orderScore").val(row['order_score']);
	$("#startTime").val(row['start_time']==null ? "":row['start_time'].replace("T"," "));
	$("#startShowTime").val(row['start_time']==null ? "":row['start_time'].replace("T"," "));
	$("#serveTime").val(row['serve_time']==null ? "":row['serve_time'].replace("T"," "));
	$("#serveShowTime").val(row['serve_time']==null ? "":row['serve_time'].replace("T"," "));
	$("#insureTime").val(row['insure_time']==null ? "":row['insure_time'].replace("T"," "));
	$("#insureShowTime").val(row['insure_time']==null ? "":row['insure_time'].replace("T"," "));
	$("#isChange").val(row['is_change']);
	$("#changePerson").val(row['change_person']);
	$("#changeTime").val(row['change_time']==null? "":row['change_time'].replace("T"," "));
	$("#changeShowTime").val(row['change_time']==null? "":row['change_time'].replace("T"," "));
	$("#changeDetail").val(row['change_detail']);
	//alert(row['order_id']);
	
	}
}
function applyOrder(){
/*$("#OrderForm").submit();
alert("更新信息成功！");*/
 $.ajax({
         url:"<%=request.getContextPath()%>/admin/updateOrder",
               type:"post",
               dataType:"json",
               data:$("#OrderForm").serialize(),
               aysnc:true,
               success:function(data) {
			 $("#changeClose").click(); 
			   var opt = {
				    url: "<%=request.getContextPath()%>/admin/getAllOrdersAction.action",
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

function changeDateFormat(val){
if(val==null)
return "";
if(val!=null)
{var time=val.replace("T"," ");
return time;}
}
  
    </script>    
    </body>
</html>
