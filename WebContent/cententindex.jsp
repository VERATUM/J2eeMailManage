<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="app">
<head>
<meta charset="utf-8" />
<title>Notebook | Web Application</title>
<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="css/app.v2.css" type="text/css" />
<link rel="stylesheet" href="js/calendar/bootstrap_calendar.css" type="text/css" cache="false" />
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<style type="text/css">
	#main{
		background-image: url("images/5.png"); 
		background-repeat: no-repeat;
		background-position: center;
	}
	.maintext{
		font-size: 50px;
		text-align: right;
	}
</style>
</head>
<body>
<section class="vbox" id=main >
      <section id="content">
        <section class="vbox">
          <section class="scrollable padder">
          	<!--右侧内容第一行-->
            <ul class="breadcrumb no-border no-radius b-b b-light pull-in">
              <li><a href="#"><i class="fa fa-home"></i>主页</a></li>
              
            </ul>
  	 	<!--右侧中间主体-->
            <!--文字-->
            <div class="m-b-md">
              <h3 class="m-b-none maintext">欢迎使用员工内部交流系统 </h3>
              <h3 class="m-b-none maintext">${EmpInfo.emp_name }</h3>
              <small>${emp.emp_email } </small><br> 
              <span id="data"></span><br>
              <span id="time"></span>
            </div>
            
           
            
            <div class="row">
              <!--生日板块
              <div class="col-md-4">
                <section class="panel panel-default">
                  
                  <div class="bg-light dk wrapper">
                    <div class="text-center m-b-n m-t-sm">
                     生日板块
                    </div>
                  </div>
                  <div class="panel-body">
                    
                    <div class="row m-t-sm">
                      	发送祝福
                    </div>
                  </div>
                </section>
              </div>
            </div>
            -->
             </div>
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
