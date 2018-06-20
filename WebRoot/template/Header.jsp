<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

   <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">OFS</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              当前用户： <a href="#" class="navbar-link"> ${user.user_name}</a>
            </p>
            <ul class="nav">
              <li style="margin-left:20px;"><a><b><font color="white" size="4">家宴帮</font></b></a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>