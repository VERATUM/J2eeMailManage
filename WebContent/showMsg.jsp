<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="app">
<head>
<meta charset="utf-8" />
<title>阅读邮件</title>
<link rel="stylesheet" href="css/app.v2.css" type="text/css" />
<link rel="stylesheet" href="js/fuelux/fuelux.css" type="text/css">
<!-- jQuery -->
<script src="js/jquery-1.9.1.min.js"></script>
<!-- Bootstrap -->
<!-- App -->
<script src="js/app.v2.js"></script>
<!-- Bootstrap -->
<script src="js/charts/easypiechart/jquery.easy-pie-chart.js"></script>
<script src="js/charts/sparkline/jquery.sparkline.min.js"></script>
<script src="js/charts/flot/jquery.flot.min.js"></script>
<script src="js/charts/flot/jquery.flot.tooltip.min.js"></script>
<script src="js/charts/flot/jquery.flot.resize.js"></script>
<script src="js/charts/flot/jquery.flot.grow.js"></script>
<script src="js/charts/flot/demo.js"></script>
<script src="js/calendar/bootstrap_calendar.js"></script>
<script src="js/calendar/demo.js"></script>
<script src="js/sortable/jquery.sortable.js"></script>

<style>
a {
	text-decoration: none;
}

label {
	margin-right: 10px;
}

input {
	border: none;
	height: 20px;
}

.tttt {
	margin: 0 10px;
	font-size: 18px;
}
</style>
</head>
<body>
	<section class="vbox">

		<section>
			<section class="hbox stretch">

				<!-- 主体页面 -->

				<!-- 列表 -->
				<section id="content">
				
				<c:if test="${!empty showMsg}">
					<section class="vbox">
						<header class="header bg-white b-b b-light">
							<p class="tttt">
								<i class="fa fa-columns"></i> 阅读邮件
							<p>
							<p>
								<a id="emailSend" href="javascript:void(0)"
									class="btn btn-sm btn-default" onclick="back()"><i
									class="fa fa-location-arrow"></i>&nbsp;返回</a>
							</p>
							<p>
								<a href="#" class="btn btn-sm btn-default" onclick="resend(${showMsg.msg_id})"><i
									class="fa fa-clock-o"></i>&nbsp;转发</a>
								
							</p>
							
								<label>主题:</label>
								<input type="text" name="title" value="${showMsg.msg_subject}" readonly="readonly" style="height: 30px">
								
								<label>发件人:</label>
								<input id="receiver" type="text" name="receiver" value="${origin.emp_name}" readonly="readonly" style="height: 30px">
								
								<label>时间:</label>
								<input id="receiver" type="text" name="receiverTime" value="${showMsg.msg_sendtime}" readonly="readonly" style="height: 30px">
								
								<label>附件：</label>
								<span><a class="btn btn-icon " href="${attachment.file}">${attachment.file_name}</a></span>
								
								
								<script>
									function resend(mid){
										$.get("uf",{"mid":mid},function(data){
											   $(window.parent.document).find("#iframe").attr("src","writemail.jsp");	
										});
									}
									
									function back(){
										window.location.href="cententindex.jsp"
									}
								</script>
								
						</header>
						<section class="scrollable wrapper">
							<!-- 邮件内容 -->
							<p>${showMsg.msg_content}</p>
						</section>
						
					</c:if>
						
					</section>
					<a href="#" class="hide nav-off-screen-block"
						data-toggle="class:nav-off-screen" data-target="#nav"></a>
				</section>

				<aside class="bg-light lter b-l aside-md hide" id="notes">
					<div class="wrapper">Notification</div>
				</aside>
			</section>
		</section>
	</section>

</body>
</html>
