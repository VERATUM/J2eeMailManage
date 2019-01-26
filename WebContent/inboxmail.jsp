﻿<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="app">
<head>
<meta charset="utf-8" />
<title>我的收邮件Test</title>
<link rel="stylesheet" href="css/app.v2.css" type="text/css" />
<link rel="stylesheet" href="js/nestable/nestable.css" type="text/css" />

<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/charts/easypiechart/jquery.easy-pie-chart.js"></script>
<script src="js/charts/sparkline/jquery.sparkline.min.js"></script>
<script src="js/charts/flot/jquery.flot.min.js"></script>
<script src="js/charts/flot/jquery.flot.tooltip.min.js"></script>
<script src="js/charts/flot/jquery.flot.resize.js"></script>
<script src="js/charts/flot/jquery.flot.grow.js"></script>
<!-- <script src="js/charts/flot/demo.js"></script> -->
<!-- <script src="js/calendar/bootstrap_calendar.js"></script>
<script src="js/calendar/demo.js"></script> -->
<script src="js/sortable/jquery.sortable.js"></script>
<style type="text/css">

.email_time {
	color: gray;
}

.pull-left {
	margin-right: 10px;
}


.app, .app body {
width:100%;
height:100%;
overflow-y:scroll;
}

</style>
</head>
<body>

	<section class="vbox">
		<section>
			<section class="hbox stretch">

				<section id="content">
					<section class="hbox stretch">

						<aside class="bg-light lter b-l" id="email-list">
							<input type="hidden" id="lis" value="${lis}">
							<section class="vbox">
							
								<!-- 头部栏 -->
								<!-- <header class="bg-light dk header clearfix">
									
									<p>
										<a id="selectEmailDelete" href="javascript:void(0)" onclick="submitIds()" class="btn btn-sm btn-default">
											<i class="fa fa-trash-o"></i>&nbsp;删除</a>
									</p>
									<div class="col-sm-5 pull-right">
										<form id="searchForm" class="m-t-sm">
											<div class="input-group">
												<input id="searchKey" type="text" value="" class="input-sm form-control input-s-sm"
													placeholder="Search">
												<div class="input-group-btn">
													<button onclick="" class="btn btn-sm btn-default">
														<i class="fa fa-search"></i>
													</button>
												</div>
											</div>
										</form>
									</div>
								</header> -->

								
								<!-- 邮件列表展示 -->
								
								<div class="table-responsive">
									<form id="selectForm">
									<table class="table table-striped b-t b-light text-sm">
										<thead>
											<tr>
												<th width="40"><input type="checkbox">全选</th>
												<th width="200" class="th-sortable" data-toggle="class">Project 
												<span class="th-sort">
													<i class="fa fa-sort-down text"></i>
													<i class="fa fa-sort-up text-active"></i>
													<i class="fa fa-sort"></i>
												</span>
												</th>
												<th width="300">Task</th>
												<th width="100">Date</th>
												<th width="40"></th>
											</tr>
										</thead>
										<tbody>
											<!-- 邮件项循环 -->
												
											
										</tbody>
									</table>
									</form>
									
									<script>
										var page = $("#lis").val();
										
										if(page=="cg"){
											//请求草稿箱数据
											$.ajax({
												async:false,
										    	url:"msg/find/note",
												type:"POST",
												dataType:"json",
												success:function(data){
													//成功后刷新
													//location.reload()
													var html = "";
													var len = data.length;
													for(var i=0;i<len;i++){
													 	html = html+ "<tr><td><input type='checkbox' name='mids' value='"+data[i].msg_id+"'></td>"
																	+"<td><a href='#' class='thumb-xs pull-left m-r-sm'>"
																		+"<img src='images/avatar_default.jpg' class='img-circle'></a>"
																		+"<a href='javascript:void(0)'><strong>"+data[i].msg_subject+"</strong></a>"
																	+"<td><a href='javascript:void(0)' onclick='待完成'><span>"+data[i].msg_content.substring(0,20)+"...</span></a></td>"
																	+"<td>"+data[i].msg_sendtime+"</td>"
																	+"<td>"
																		+"<div class='row'>"
																			+"<a href='javascript:void(0)' onclick=\"updateMail("+data[i].msg_id+")\"> <i class='fa fa-pencil icon-muted fa-fw m-r-xs'></i> </a>"
																			+"<a href='javascript:void(0)' onclick=\"deleteBymid('"+data[i].msg_id+"')\"> <i class='fa fa-times icon-muted fa-fw'></i> </a>"
																		+"</div>"
																	+"</td>"
																+"</tr>"
														//拼接成list表单
														$("tbody").append(html)
													}
												}
										    })
											
										    
										}else if(page=="all"){
											//请求所有收件箱数据
											$.ajax({
												async:false,
										    	url:"msg/find/all",
												type:"POST",
												dataType:"json",
												success:function(data){
													//成功后刷新
													//location.reload()
													var html = ""
													var len = data.length;
													for(var i=0;i<len;i++){
														var startC = "";
														var state = parseInt(data[i].msg_isread);
														//2为红，1为灰
														if(state==2){
															starC="fa fa-heart"
														}else{
															starC="fa fa-heart-o"
														}
																
													 	html = html+ "<tr><td><input type='checkbox' name='mids' value='"+data[i].msg_id+"'></td>"
																	+"<td><a href='#' class='thumb-xs pull-left m-r-sm'>"
																		+"<img src='images/avatar_default.jpg' class='img-circle'></a>"
																		+"<strong>"+data[i].msg_subject+"</strong>"
																	+"<td><a href='javascript:void(0)' onclick='showMail("+data[i].msg_id+")'><span>"+data[i].msg_content.substring(0,30)+" ...</span></a></td>"
																	+"<td>"+data[i].msg_sendtime+"</td>"
																	+"<td>"
																		+"<div class='row'>"
																			+"<a href='javascript:void(0)' onclick='star(this,"+data[i].msg_id+","+state+")'> <i class='emailHeart "+starC+" icon-muted fa-fw'></i> </a>"
																			+"<a href='javascript:void(0)' onclick=\"deleteBymid('"+data[i].msg_id+"')\"> <i class='fa fa-times icon-muted fa-fw'></i> </a>"
																		+"</div>"
																	+"</td>"
																+"</tr>"
														//拼接成list表单
														$("tbody").append(html)
													}
												}
										    })
											
										}else if(page=="lj"){
											//请求垃圾箱数据
											$.ajax({
												async:false,
										    	url:"msg/find/rubbish",
												type:"POST",
												dataType:"json",
												success:function(data){
													//成功后刷新
													//location.reload()
													var html = "";
													var len = data.length;
													for(var i=0;i<len;i++){
																
													 	html = html+ "<tr><td><input type='checkbox' name='mids' value='"+data[i].msg_id+"'></td>"
																	+"<td><a href='#' class='thumb-xs pull-left m-r-sm'>"
																		+"<img src='images/avatar_default.jpg' class='img-circle'></a>"
																		+"<strong>"+data[i].msg_subject+"</strong>"
																	+"<td><span>"+data[i].msg_content.substring(0,30)+" ...</span></td>"
																	+"<td>"+data[i].msg_sendtime+"</td>"
																	+"<td>"
																		+"<div class='row'>"
																			+"<a href='javascript:void(0)' onclick=\"receiver('"+data[i].msg_id+"')\"> <i class='fa fa-repeat'></i> </a>"
																			+"<a href='javascript:void(0)' onclick=\"deleteBymid('"+data[i].msg_id+"')\"> <i class='fa fa-times icon-muted fa-fw'></i> </a>"
																		+"</div>"
																	+"</td>"
																+"</tr>"
														//拼接成list表单
														$("tbody").append(html)
													}
												}
										    })
											
										}else if(page=="qy"){
											//请求群邮箱数据
											$.ajax({
												async:false,
										    	url:"msg/find/topic",
												type:"get",
												dataType:"json",
												success:function(data){
													//成功后刷新
													//location.reload()
													var html = ""
													var len = data.length;
													for(var i=0;i<len;i++){
														var startC = "";
														var state = parseInt(data[i].msg_isread);
														//2为红，1为灰
														if(state==2){
															starC="fa fa-heart"
														}else{
															starC="fa fa-heart-o"
														}
														
														
													 	html = html+ "<tr><td><input type='checkbox' name='mids' value='"+data[i].msg_id+"'></td>"
																	+"<td><a href='#' class='thumb-xs pull-left m-r-sm'>"
																		+"<img src='images/avatar_default.jpg' class='img-circle'></a>"
																		+"<strong>"+data[i].msg_subject+"</strong>"
																		+"<td><a href='javascript:void(0)' onclick='showMail("+data[i].msg_id+")'><span>"+data[i].msg_content.substring(0,30)+" ...</span></a></td>"
																		+"<td>"+data[i].msg_sendtime+"</td>"
																	+"<td>"
																		+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
																	+"</td>"
																+"</tr>"
														//拼接成list表单
														$("tbody").append(html)
													}
												}
										    })
										}else if(page=="xb"){
											//请求星标邮箱数据
											$.ajax({
												async:false,
										    	url:"msg/find/star",
												type:"POST",
												dataType:"json",
												success:function(data){
													//成功后刷新
													//location.reload()
													var html = ""
													var len = data.length;
													for(var i=0;i<len;i++){
														var startC = "";
														var state = parseInt(data[i].msg_isread);
														//2为红，1为灰
														if(state==2){
															starC="fa fa-heart"
														}else{
															starC="fa fa-heart-o"
														}
																
													 	html = html+ "<tr><td><input type='checkbox' name='mids' value='"+data[i].msg_id+"'></td>"
																	+"<td><a href='#' class='thumb-xs pull-left m-r-sm'>"
																		+"<img src='images/avatar_default.jpg' class='img-circle'></a>"
																		+"<strong>"+data[i].msg_subject+"</strong>"
																	+"<td><a href='javascript:void(0)' onclick='showMail("+data[i].msg_id+")'><span>"+data[i].msg_content.substring(0,30)+" ...</span></a></td>"
																	+"<td>"+data[i].msg_sendtime+"</td>"
																	+"<td>"
																		+"<div class='row'>"
																			+"<a href='javascript:void(0)' onclick='star(this,"+data[i].msg_id+","+state+")'> <i class='emailHeart "+starC+" icon-muted fa-fw'></i> </a>"
																			+"<a href='javascript:void(0)' onclick=\"deleteBymid('"+data[i].msg_id+"')\"> <i class='fa fa-times icon-muted fa-fw'></i> </a>"
																		+"</div>"
																	+"</td>"
																+"</tr>"
														//拼接成list表单
														$("tbody").append(html)
													}
												}
										    })
										
										}
										
									</script>
									
									
									<!-- 全选删除的操作 -->
									<script>
										function submitIds(){
											var ids = ""
											
											//选择被选中的checkbox
								            $.each($('input:checkbox:checked'),function(i,item){
								            	ids = ids + $(item).val()+","
								            })
								            
								            if(ids.length>1){
									            $.ajax({
									            	url:"msg/delete",
								        			type:"POST",
								        			data:{"mids":ids},
								        			success:function(data){
								        				//成功后刷新
								        				location.reload()
								        			}
									            })
									            
								            }
										}
										
										//选中单个删除
										function deleteBymid(mid){
											$.ajax({
								            	url:"msg/delete",
							        			type:"POST",
							        			data:{"mid":mid},
							        			success:function(data){
							        				//成功后刷新
							        				location.reload()
							        			}
								            })
										}

										
										//设置是否星标
										//2为红，1为灰
										function star(e,mid,state){
											
											if(state==1){
												//若状态为灰，则设为红
												$(e).children().attr("class","emailHeart fa fa-heart icon-muted fa-fw");
												state=2;
											}else{
												//若状态为红，则设为灰
												$(e).children().attr("emailHeart fa fa-heart-o icon-muted fa-fw");
												state=1;
											}
											
											$.ajax({
												async:false,
												url:"msg/star",
												type:"get",
												data:{"cc":state,"id":mid},
												dataType:"json",
												success:function(data){
													//成功后刷新
													location.reload()
												}
											})
										}
										
										//恢复删除
										function receiver(id){
											$.jax({
												url:"msg/receiver",
												type:"get",
												data:{"id":id},
												dataType:"json",
												success:function(data){
													//成功后刷新
							        				location.reload()
												}
											})
										}
										
										//修改草稿
										function updateMail(mid){
											   $.get("uu",{"mid":mid},function(data){
												   $(window.parent.document).find("#iframe").attr("src","writemail.jsp");	
											   });
										}
										
										
										//展示内容
										function showMail(mid){
											   $.get("show",{"mid":mid},function(data){
												   $(window.parent.document).find("#iframe").attr("src","showMsg.jsp");	
											   });
										}
									</script>
								</div>
								
								<footer class="panel-footer">
									<div class="row">
										<div class="col-lg-1 text-center">
											<a id="selectEmailDelete" href="javascript:void(0)" onclick="submitIds()" class="btn btn-sm btn-default">
												<i class="fa fa-trash-o"></i>&nbsp;删除</a>
										</div>
										<div class="col-lg-11 text-center">
											<small class="text-muted inline m-t-sm m-b-sm">奇尔科技有限公司（武汉）</small>
										</div>
										
									</div>
								</footer>
							</section>
						</aside>
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
	<script src="js/app.v2.js"></script>
</body>
</html>