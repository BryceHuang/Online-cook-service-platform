<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div id="messagebox" class="alert alert-warning" style="display:none;position: fixed;right:40px;bottom:0px;width:150px;height:200px;z-index:999">
	<a href="#" class="close" data-dismiss="alert">&times;</a>
	<h4 class="modal-title" id="messageTitle"></h4>
	<div class="modal-body"><p id="messageContent"></p></div>	
</div> 
<!-- 右侧菜单 -->
	<div class="right-nav">
		<ul class="r-with-gotop">
			<li class="r-toolbar-item">
				<a href="index2.jsp" class="r-item-hd">
					<i class="iconfont icon-user" ></i> <!-- data-badge="0" -->
					<div class="r-tip__box"><span class="r-tip-text">用户中心</span></div>
				</a>
			</li>

			<!-- <li class="r-toolbar-item">
				<a href="#" class="r-item-hd">
					<i class="iconfont icon-aixin"></i>
					<div class="r-tip__box"><span class="r-tip-text">我的收藏</span></div>
				</a>
			</li> -->
			<li class="r-toolbar-item">
				<a href="#" class="r-item-hd">
					<i class="iconfont icon-liaotian"></i>
					<div class="r-tip__box"><span class="r-tip-text">联系客服</span></div>
				</a>
			</li>
			<li class="r-toolbar-item">
				<a href="#" class="r-item-hd">
					<i class="iconfont icon-liuyan"></i>
					<div class="r-tip__box"><span class="r-tip-text">留言反馈</span></div>
				</a>
			</li>
			<li class="r-toolbar-item to-top">
				<i class="iconfont icon-top"></i>
				<div class="r-tip__box"><span class="r-tip-text">返回顶部</span></div>
			</li>
		</ul>
		<script>
			$(document).ready(function(){ $('.to-top').toTop({position:false}) });
		</script>
	</div>