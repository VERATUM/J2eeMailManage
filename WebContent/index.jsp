<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="app">
<link rel="icon" href="hz.ico" type="image/x-icon">
<link rel="shortcut icon" href="images/1.ico" type="image/x-icon">
<head>
<meta charset="utf-8" />
<title>APEX | 主页</title>
<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="css/app.v2.css" type="text/css" />
<link rel="stylesheet" href="js/calendar/bootstrap_calendar.css" type="text/css" cache="false" />
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(function(){
	
	var EmpInfoId = "${sessionScope.EmpInfo.emp_id}";
	var DeptId ="${sessionScope.EmpInfo.dept_id}";
	var EmpInfoEmail = "${sessionScope.EmpInfo.emp_email}";
	
	//平台首页
	$("#mainbutton").click(function(){
			$("#iframe").attr("src","cententindex.jsp?");	
	});
//写邮箱
	//点击 写邮件
	$("#writeemail").click(function () {
			$("#iframe").attr("src","writemail.jsp");
	})
   //点击 显示群发邮件
   $("#massmail").click(function () {
			$("#iframe").attr("src","writemail.jsp");	
	})
   //点击 显示草稿箱
   $("#draftmail").click(function () {
	   var cg = "cg";
	   $.post("indexServer?action=cg",{"mail":cg},function(data){
		   if(data==1){
			   $("#iframe").attr("src","inboxmail.jsp");	
		   }
	   });
   })
//收件箱
   //点击 所有收件箱
   
   $("#showemail").click(function () {
	   var all = "all";
	   $.post("indexServer?action=all",{"mail":all},function(data){
		   if(data==1){
			   $("#iframe").attr("src","inboxmail.jsp");	
		   }
	   });
   })
   //点击 显示群邮件
	$("#showmass").click(function () {
		   var qy = "qy";
		   $.post("indexServer?action=qy",{"mail":qy},function(data){
			   if(data==1){
				   $("#iframe").attr("src","inboxmail.jsp");	
			   }
		   });
	
	})
   //点击 显示垃圾箱
	$("#dustbin").click(function () {
		var lj = "lj";
		   $.post("indexServer?action=lj",{"mail":lj},function(data){
			   if(data==1){
				   $("#iframe").attr("src","inboxmail.jsp");	
			   }
		   });
	})
   //点击 显示星级
	$("#starmail").click(function () {
		var xb = "xb";
		   $.post("indexServer?action=xb",{"mail":xb},function(data){
			   if(data==1){
				   $("#iframe").attr("src","inboxmail.jsp");	
			   }
		});
	})
	$(".liStar").click(function () {
		var values = $(this).children().first().text();
		$.post("StarWriteServlet",{values:values},function(data){
			if(data==1){
				$("#iframe").attr("src","writemail.jsp");
			}else{
				alert("该员工不存在");
			}
		});
	})
	
  //点击 显示通讯录
    $("#communication").click(function () {
			$("#iframe").attr("src","master.jsp?");	
	})
  
  //点击 写个人日志
	$("#writelog").click(function () {
			$("#iframe").attr("src","notebook.jsp");	
	})
  //鼠标移上事件
	$("#write").hover(
		function () {
			$(this).css({"background":"#1bb398"});
		},
		function () {
			$(this).css({"background":"#0f9d84"});
		}
	);
	$("#read").hover(
		function () {
			$(this).css({"background":"#1bb398"});
		},
		function () {
			$(this).css({"background":"#0f9d84"});
		}
	);
  //点击事件
	$("#write").click(function(){
		$("#iframe").attr("src","writemail.jsp");
	});
	$("#read").click(function(){
		 var all = "all";
		   $.post("indexServer?action=all",{"mail":all},function(data){
			   if(data==1){
				   $("#iframe").attr("src","inboxmail.jsp");	
			   }
		   });
	});
})

</script>
<style type="text/css">
	#write{
	width: 110px; /* 宽度 */
	height: 50px; /* 高度 */
	border-width: 0px; /* 边框宽度 */
	/* border-radius: 3px; */ /* 边框半径 */
	background: #0f9d84; /* 背景颜色 */
	cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
	outline: none; /* 不显示轮廓线 */
	font-family:; /* 设置字体 */
	color: #fff; /* 字体颜色 */
	font-size: 16px; /* 字体大小 */
	}
	#read{
	float:right;
	right:0px;
	width: 110px; /* 宽度 */
	height: 50px; /* 高度 */
	border-width: 0px; /* 边框宽度 */
	/* border-radius: 3px;  *//* 边框半径 */
	background: #0f9d84; /* 背景颜色 */
	cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
	outline: none; /* 不显示轮廓线 */
	font-family: Microsoft YaHei; /* 设置字体 */
	color: #fff; /* 字体颜色 */
	font-size: 16px; /* 字体大小 */
	}
	#header123{
		height:50px;
	}
	#logo{
		width: 30px;
	}
</style>
</head>
<body>
<section class="vbox">
  <header class="bg-dark dk header navbar navbar-fixed-top-xs">
	<!--头部左边(可以放公司LOGO) -->
    <div class="navbar-header aside-md"> 
    	<a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen" data-target="#nav"> 
        <i class="fa fa-bars"></i> 
        </a> 
        <a href="#" class="navbar-brand" data-toggle="fullscreen">
      <span>  <img src="images/1.png" id="logo"></span>APEX
        </a> 
        <a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".nav-user"> 		        <i class="fa fa-cog"></i> 
        </a> 			    
    </div>
    <!--头部左边(LOGO隔壁下拉框) -->
    <ul class="nav navbar-nav hidden-xs">
    	<li class="dropdown"> 
          <a href="#" class="dropdown-toggle dker" data-toggle="dropdown"> 
           <i class="fa fa-building-o"></i> <span class="font-bold">Activity</span> 
          </a>
          <section class="dropdown-menu aside-xl  animated fadeInLeft no-borders lt">
          <div class="wrapper lter m-t-n-xs"> 
          	<a href="#" class="thumb pull-left m-r"> 
            <!--更改照片路径-->
            <img src="images/avatar.jpg" class="img-circle"> 
            </a>
            <div class="clear"> 
            <a href="#"><span class="text-white font-bold">${sessionScope.EmpInfo.emp_email } </span></a> <small class="block">部门:${DeptInfo.dept_name }</small> <a href="personal.jsp" class="btn btn-xs btn-success m-t-xs" id="showinfo">详情</a> </div>
          </div>
          <div class="row m-l-none m-r-none m-b-n-xs text-center">
            <div class="col-xs-4">
              <div class="padder-v"> <span class="m-b-xs h4 block text-white">${EmpInfo.emp_name }</span> <small class="text-muted">姓名</small> </div>
            </div>
            <div class="col-xs-4 dk">
              <div class="padder-v"> <span class="m-b-xs h4 block text-white" id="empid">${EmpInfo.emp_id }</span> <small class="text-muted">编号</small> </div>
            </div>
            <div class="col-xs-4">
              <div class="padder-v"> <span class="m-b-xs h4 block text-white">${EmpInfo.emp_sex }</span> <small class="text-muted">性别</small> </div>
            </div>
          </div>
        </section>
      </li>
      
    </ul>
    <!--个人信息-->
    <ul class="nav navbar-nav navbar-right hidden-xs nav-user">
      <li class="dropdown"> 
      	<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
      	<span class="thumb-sm avatar pull-left">
	      	<!--用户头像路径--> 
	      	<c:if test="${empty EmpInfo.emp_photo}">
           		<img src="images/avatar.jpg">
           	</c:if>
           	<c:if test="${!empty EmpInfo.emp_photo}">
           		<img src="${EmpInfo.emp_photo}" >
           	</c:if>
      	</span>${EmpInfo.emp_name } <b class="caret"></b> </a>
      <!--个人信息下拉框-->
        <ul class="dropdown-menu animated fadeInRight">
          <span class="arrow top"></span>
          <li> <a href="personal.jsp">个人信息</a> </li>
          <li id="disclose"> <a target="_blank" href="feedbackServlet?empid=${EmpInfo.emp_id }&deptname=${DeptInfo.dept_name }">反馈意见 </a> </li>
        </ul>
      </li>
    </ul>
  </header>
  <section>
    <section class="hbox stretch">
      <aside class="bg-dark lter aside-md hidden-print" id="nav">
        <section class="vbox">
         <!--左侧下拉框绿色部分-->
          <header id="header123" class="bg-primary lter  clearfix">
               	<input id="write" type="button" value="发邮件">
               	<input id="read" type="button" value="收邮件">
          </header>
          
          <section class="w-f scrollable">
            <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="5px" data-color="#333333">
              <nav class="nav-primary hidden-xs">
                <!--左侧导航栏-->
                <ul class="nav">
                  <!--左侧导航栏第一个选项-->
                  <li id="mainbutton"> <a href="#"> <i class="fa fa-dashboard icon"> <b class="bg-danger"></b> </i> <span id="texto">平台首页</span> </a> </li>
                  <!--左侧导航栏第二个选项-->  
                  <li > <a href="#layout" > <i class="fa fa-columns icon"> <b class="bg-warning"></b> </i> <span class="pull-right"> <i class="fa fa-angle-down text"></i> <i class="fa fa-angle-up text-active"></i> </span> <span>写邮件</span> </a>
                    <!--该选项下拉框-->
                    <ul class="nav lt">
                      <li id="writeemail"> <a href="#" > <i class="fa fa-angle-right"></i> <span>写邮件</span> </a> </li>
                      <li id="massmail"> <a href="#" > <i class="fa fa-angle-right"></i> <span>群发邮件</span> </a> </li>
                      <li id="draftmail"> <a href="#" > <i class="fa fa-angle-right"></i> <span>草稿箱</span> </a> </li>
                    </ul>
                  </li>
                  <!--左侧导航栏第三个选项-->
                  <li > <a href="#" > <i class="fa fa-flask icon"> <b class="bg-success"></b> </i> <span class="pull-right"> <i class="fa fa-angle-down text"></i> <i class="fa fa-angle-up text-active"></i> </span> <span>收邮件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><b class="badge bg-danger">${sessionScope.countEmail.newMsg }</b> </a>
                    <!--该选项下拉框-->
                    <ul class="nav lt">
                    
                      <li id="showemail"> <a href="#" ><b class="badge bg-info pull-right">${sessionScope.countEmail.allMsg }</b><i class="fa fa-angle-right"></i> <span>所有邮件</span> </a> </li>                      
                      <li id="showmass"> <a href="#" > <b class="badge bg-info pull-right">${sessionScope.countEmail.qYMsg }</b> <i class="fa fa-angle-right"></i> <span>群邮件</span> </a> </li>
                      <li id="dustbin"> <a href="#" > <b class="badge pull-right">${sessionScope.countEmail.rubbishMsg }</b> <i class="fa fa-angle-right"></i> <span>垃圾箱</span> </a> </li>
                      <li id="starmail"> <a href="#" ><b class="badge pull-right">${sessionScope.countEmail.starMsg }</b> <i class="fa fa-angle-right"></i> <span>星标邮件</span> </a> </li>
                    </ul>
                  </li>
                  <!--左侧导航栏第四个选项-->
                  <li> <a href="#" > <i class="fa fa-file-text icon"> <b class="bg-primary"></b> </i> <span class="pull-right"> <i class="fa fa-angle-down text"></i> <i class="fa fa-angle-up text-active"></i> </span> <span>星标朋友</span> </a>
                   <!--该选项下拉框--> 
                    <ul class="nav lt">
                      <c:forEach items="${sessionScope.attLis }" var="attli">
                      <li class="liStar"><div style="display: none;">${attli.emp_id_b }</div> <a href="#" > <i class="fa fa-angle-right"></i> <span>${attli.b_emp_name }</span> </a> </li>
                      </c:forEach>
                    </ul>
                  </li>
                  <!--左侧导航栏第五个选项-->
                  <li id="communication"><a href="#"><i class="fa fa-envelope-o icon"><b class="bg-primary dker"></b></i><span>通讯录</span></a> </li>
                  <!--左侧导航栏第六个选项-->
                  <li id="writelog"><a href="#"><i class="fa fa-pencil icon"><b class="bg-info"></b> </i> <span>个人日志</span> </a> </li>
                </ul>
              </nav>
             </div>
          </section>
          <!--左侧导航栏底部-->
          <footer class="footer lt hidden-xs b-t b-dark">
            <div id="chat" class="dropup">
              <section class="dropdown-menu aside-md m-l-n">
                <section class="panel bg-white">
                  <header class="panel-heading b-b b-light">Active chats</header>
                  <div class="panel-body animated fadeInRight">
                    <p class="text-sm">No active chats.</p>
                    <p><a href="#" class="btn btn-sm btn-default">Start a chat</a></p>
                  </div>
                </section>
              </section>
            </div>
            <div id="invite" class="dropup">
              <section class="dropdown-menu aside-md m-l-n">
                <section class="panel bg-white">
                  <header class="panel-heading b-b b-light"> John <i class="fa fa-circle text-success"></i> </header>
                  <div class="panel-body animated fadeInRight">
                    <p class="text-sm">No contacts in your lists.</p>
                    <p><a href="#" class="btn btn-sm btn-facebook"><i class="fa fa-fw fa-facebook"></i> Invite from Facebook</a></p>
                  </div>
                </section>
              </section>
            </div>
            <a href="#nav" data-toggle="class:nav-xs" class="pull-right btn btn-sm btn-dark btn-icon"> <i class="fa fa-angle-left text"></i> <i class="fa fa-angle-right text-active"></i> </a>
            <div class="btn-group hidden-nav-xs">
              <button type="button" title="Chats" class="btn btn-icon btn-sm btn-dark" data-toggle="dropdown" data-target="#chat"><i class="fa fa-comment-o"></i></button>
              <button type="button" title="Contacts" class="btn btn-icon btn-sm btn-dark" data-toggle="dropdown" data-target="#invite"><i class="fa fa-facebook"></i></button>
            </div>
          </footer>
          
          
        </section>
      </aside>
      <!-- 左侧导航栏结束 -->
      <!--包含网页-->
      
      <section id="content">
            <iframe id="iframe" name="toppage" width=100% height=100% marginwidth=0 marginheight=0 frameborder="no" border="0" src="cententindex.jsp" ></iframe>
        </section>
       </section>
    </section>
  </section>

<script src="js/app.v2.js"></script><script src="js/charts/easypiechart/jquery.easy-pie-chart.js" cache="false"></script> 
<script src="js/charts/sparkline/jquery.sparkline.min.js" cache="false"></script> 
<script src="js/charts/flot/jquery.flot.min.js" cache="false"></script> 
<script src="js/charts/flot/jquery.flot.tooltip.min.js" cache="false"></script> <script src="js/charts/flot/jquery.flot.resize.js" cache="false"></script> <script src="js/charts/flot/jquery.flot.grow.js" cache="false"></script> <script src="js/charts/flot/demo.js" cache="false"></script> <script src="js/calendar/bootstrap_calendar.js" cache="false"></script> <script src="js/calendar/demo.js" cache="false"></script> <script src="js/sortable/jquery.sortable.js" cache="false"></script>
</body>
</html>
