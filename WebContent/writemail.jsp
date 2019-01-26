<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="app">
<head>
<meta charset="utf-8" />
<title>写邮件</title>
<link rel="stylesheet" href="css/app.v2.css" type="text/css" />
<link rel="stylesheet" href="js/fuelux/fuelux.css" type="text/css">
<!-- jQuery -->
<script src="js/jquery-1.9.1.min.js"></script>
<!-- 富文本编辑器 -->
<script src="ckeditor5/ckeditor.js"></script>
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
a:hover{
	text-decoration: underline;
	cursor: pointer;
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
					<section class="vbox">
					<c:if test="${empty updateMsg && empty resendMsg}">
						<header class="header bg-white b-b b-light">
							<p class="tttt">
								<i class="fa fa-columns"></i> 写邮件
							<p>
							<p>
								<a id="emailSend" href="javascript:void(0)"
									class="btn btn-sm btn-default" onclick="kk('msg/add')"><i
									class="fa fa-location-arrow"></i>&nbsp;发送</a>
							</p>
							<p>
								<a href="#" class="btn btn-sm btn-default" onclick="kk('')"><i
									class="fa fa-clock-o"></i>&nbsp;定时发送</a>
								日期<input for="msgForm" type="date" value="">
							</p>
							<p>
								<a href="#" class="btn btn-sm btn-default" onclick="kk('msg/save')"><i
									class="fa fa-columns"></i>&nbsp;保存草稿</a>
							</p>
							<p>
								<a href="javascript:void(0)" onclick="back()" class="btn btn-sm btn-default"><i
									class="fa fa-mail-reply-all"></i>&nbsp;放弃</a>
							</p>
						</header>
						
						<section class="scrollable wrapper">
							<!-- 邮件form -->
							<form id="msgForm" method="post"
								enctype="multipart/form-data">
							<!-- 富文本编辑器 -->
								<div class="row">
									<div class="col-lg-12">
										<section class="panel panel-default">
											<div class="panel-body">
											
												<label>主题</label>
												<input type="text" name="title" value="" placeholder="邮件主题" style="height: 30px">
												<span class="btn fileinput-button">
													<span class="btn btn-success" onclick="attaC()">附件</span>
													<input id="attaFile" type="file" style="display: none" id="attachment" name="attachment"/>
												</span>
												<script>
													function attaC(){
														return $("#attaFile").click();
													}
												</script>
												
												<c:if test="${empty aae}">
													<label>收件人</label>
													<input id="receiver" type="text" name="receiver" value="" placeholder="<***@123.com>" style="height: 30px">
												</c:if>
												<c:if test="${!empty aae}">
													<label>收件人</label>
													<input id="receiver" type="text" name="receiver" value="${aae.emp_email}" placeholder="<***@123.com>" style="height: 30px">
												</c:if>
												
												<div class="pull-right">
	                                            <label>快捷选择</label>
	                                            	<!-- 下拉列表 -->
	                                            	<div id="sediv">
                                                    </div>
                                                </div>
                                                 <script>
                                                 var datalist;
                                                 
                                                 $.ajax({
                                              		async:false,
                                              		url:"recipients",
                                              		type:"get",
     												dataType:"json",
                                              		success:function(data){
                                              			datalist = data
                                              			var html="";
                                                   
                                               			html = html+"<select id='select2-opt' style='width:220px' onchange='changeOne(this)'>"
                                           				html=html+"<option value='-1' >-----选择部门---</option>";

                                               			for(var i=0;i<data.length;i++){
                                               				html=html+"<option name='tag' value='"+i+"' >"+data[i].dept_name+"</option>";
                                               			}
                                               			html=html+"</select>"
                                               			
                                               			
                                               			html = html+"<select id='select3-opt' style='width:220px' onchange='changeTwo(this)'>"
                                           				html=html+"<option value='-1' >-----选择联系人---</option>";
                                               			html=html+"</select>"
                                               			
                                               			$("#sediv").append(html);
                                              		}
                                                 
                                              	})
                                              	
                                              		//发送给部门下的人
													function changeOne(e){
													    var k = $(e).val()
													    
													    $("#select3-opt").empty().append("<option value='-1' >-----选择联系人---</option>")
													
													    var flag = false
													    
													    if(k!=-1){
	                                               			var ddd = datalist[k].empInfos

	                                               			//存入收件人input
	                                               			$("#receiver").val(datalist[k].dept_id+"-"+ddd.length+"-"+datalist[k].dept_name)
	                                               			
	                                               			for(var i=0;i<ddd.length;i++){
	                                           					$("#select3-opt").append("<option name='emp' value='"+ddd[i].emp_email+"'>"+ddd[i].emp_name+"</option>")
													        	flag = true
	                                               			}
                                               			}
														
													}
													
                                                 	//发送给单人
													function changeTwo(e){
														var v = $(e).val()
														
														if(v==-1){
															v="";
														}
	                                                	 //存入收件人input
	                                                	 $("#receiver").val(v)
													}
                                                 </script>
											</div>
										</section>
									</div>
								</div>
								<textarea name="content" id="editor">
									<p>&nbsp;&nbsp;</p>
								</textarea>
							</form>
							<!-- 提交表单,发送邮件 -->
							<script>
								function kk(path) {
									if(path == ""||path == null){
										alert("path无效")
										return;
									}
									$('#msgForm').attr("action", path).submit();
								}
							</script>
							<script>
								var myEditor = null;
								ClassicEditor .create(document.querySelector("#editor"),{
									ckfinder: {
							            uploadUrl: 'http://localhost:8080/J2eeEIIEP/fileUpload'
							        }
								
								})
								.then(editor => {
									myEditor = editor;
									height: 800;
									/* editor.plugins.get('FileRepository').createUploadAdapter  = (loader) => {
								        return new UploadAdapter(loader);
								    }; */
								})
								.catch(error => {
									console.log(error);
								})
								
							 </script>
						</section>
					</c:if>
					
					<!-- 修改草稿 -->
					<c:if test="${!empty updateMsg}">
						<header class="header bg-white b-b b-light">
							<p class="tttt">
								<i class="fa fa-columns"></i> 写邮件
							<p>
							<p>
								<a id="emailSend" href="javascript:void(0)"
									class="btn btn-sm btn-default" onclick="kk('msg/update')"><i
									class="fa fa-location-arrow"></i>&nbsp;发送</a>
							</p>
							<p>
								<a href="#" class="btn btn-sm btn-default" onclick="kk('')"><i
									class="fa fa-clock-o"></i>&nbsp;定时发送</a>
								日期<input for="msgForm" type="date" value="">
							</p>
							<p>
								<a href="#" class="btn btn-sm btn-default" onclick="kk('msg/updatesave')"><i
									class="fa fa-columns"></i>&nbsp;保存草稿</a>
							</p>
							<p>
								<a href="javascript:void(0)" onclick="back()" class="btn btn-sm btn-default"><i
									class="fa fa-mail-reply-all"></i>&nbsp;放弃</a>
							</p>
						</header>
						<section class="scrollable wrapper">
							<!-- 邮件form -->
							<form id="msgForm" method="post"
								enctype="multipart/form-data">
							<!-- 富文本编辑器 -->
								<div class="row">
									<div class="col-lg-12">
										<section class="panel panel-default">
											<div class="panel-body">
											
												<label>主题</label>
												<input type="hidden" name="mid" value="${updateMsg.msg_id}">
												<input type="text" name="title" value="${updateMsg.msg_subject}" placeholder="邮件主题" style="height: 30px">
												<span class="btn btn-success fileinput-button">
													<span class="btn btn-success" onclick="attaC()">附件</span>
													<input id="attaFile" type="file" style="display: none" id="attachment" name="attachment"/>
												</span>
												<script>
													function attaC(){
														return $("#attaFile").click();
													}
												</script>
												
												
												
												<label>收件人</label>
												<input id="receiver" type="text" name="receiver" value="" placeholder="<***@qq.com>" style="height: 30px">
												<div class="pull-right">
	                                            <label>快捷选择</label>
	                                            	<!-- 下拉列表 -->
	                                            	<div id="sediv">
                                                    </div>
                                                </div>
                                                 <script>
                                                 var datalist;
                                                 
                                                 $.ajax({
                                              		async:true,
                                              		url:"recipients",
                                              		type:"get",
     												dataType:"json",
                                              		success:function(data){
                                              			datalist = data
                                              			var html="";
                                                   
                                               			html = html+"<select id='select2-opt' style='width:220px' onchange='changeOne(this)'>"
                                           				html=html+"<option value='-1' >-----选择部门---</option>";

                                               			for(var i=0;i<data.length;i++){
                                               				html=html+"<option name='tag' value='"+i+"' >"+data[i].dept_name+"</option>";
                                               			}
                                               			html=html+"</select>"
                                               			
                                               			
                                               			html = html+"<select id='select3-opt' style='width:220px' onchange='changeTwo(this)'>"
                                           				html=html+"<option value='-1' >-----选择联系人---</option>";
                                               			html=html+"</select>"
                                               			
                                               			$("#sediv").append(html);
                                              		}
                                                 
                                              	})
                                              	
                                              		//发送给部门下的人
													function changeOne(e){
													    var k = $(e).val()
													    
													    $("#select3-opt").empty().append("<option value='-1' >-----选择联系人---</option>")
													
													    var flag = false
													    
													    if(k!=-1){
	                                               			var ddd = datalist[k].empInfos

	                                               			//存入收件人input
	                                               			$("#receiver").val(datalist[k].dept_id+"-"+ddd.length+"-"+datalist[k].dept_name)
	                                               			
	                                               			for(var i=0;i<ddd.length;i++){
	                                           					$("#select3-opt").append("<option name='emp' value='"+ddd[i].emp_email+"'>"+ddd[i].emp_name+"</option>")
													        	flag = true
	                                               			}
                                               			}
														
													}
													
                                                 	//发送给单人
													function changeTwo(e){
														var v = $(e).val()
														
														if(v==-1){
															v="";
														}
	                                                	 //存入收件人input
	                                                	 $("#receiver").val(v)
													}
                                                 </script>
											</div>
										</section>
									</div>
								</div>
								<textarea name="content" id="editor">
									${updateMsg.msg_content}
								</textarea>
							</form>
							<!-- 提交表单,发送邮件 -->
							<script>
								function kk(path) {
									if(path == ""||path == null){
										alert("path无效")
										return;
									}
									$('#msgForm').attr("action", path).submit();
								}
							</script>
							<script>
								var myEditor = null;
								ClassicEditor .create(document.querySelector("#editor"),{
									ckfinder: {
							            uploadUrl: 'http://localhost:8080/J2eeEIIEP/fileUpload'
							        }
								
								})
								.then(editor => {
									myEditor = editor;
									height: 800;
									/* editor.plugins.get('FileRepository').createUploadAdapter  = (loader) => {
								        return new UploadAdapter(loader);
								    }; */
								})
								.catch(error => {
									console.log(error);
								})
								
							 </script>
						</section>
					</c:if>	
					
					
					<!-- resend邮件 -->
					<c:if test="${!empty resendMsg}">
						<header class="header bg-white b-b b-light">
							<p class="tttt">
								<i class="fa fa-columns"></i> 写邮件
							<p>
							<p>
								<a id="emailSend" href="javascript:void(0)"
									class="btn btn-sm btn-default" onclick="kk('msg/add')"><i
									class="fa fa-location-arrow"></i>&nbsp;发送</a>
							</p>
							<p>
								<a href="#" class="btn btn-sm btn-default" onclick="kk('')"><i
									class="fa fa-clock-o"></i>&nbsp;定时发送</a>
								日期<input for="msgForm" type="date" value="">
							</p>
							<p>
								<a href="#" class="btn btn-sm btn-default" onclick="kk('msg/save')"><i
									class="fa fa-columns"></i>&nbsp;保存草稿</a>
							</p>
							<p>
								<a href="javascript:void(0)" onclick="back()" class="btn btn-sm btn-default"><i
									class="fa fa-mail-reply-all"></i>&nbsp;放弃</a>
							</p>
						</header>
						<section class="scrollable wrapper">
							<!-- 邮件form -->
							<form id="msgForm" method="post"
								enctype="multipart/form-data">
							<!-- 富文本编辑器 -->
								<div class="row">
									<div class="col-lg-12">
										<section class="panel panel-default">
											<div class="panel-body">
											
												<label>主题</label>
												<input type="text" name="title" value="${updateMsg.msg_subject}" placeholder="邮件主题" style="height: 30px">
												<span class="btn btn-success fileinput-button">
													<span class="btn btn-success" onclick="attaC()">附件</span>
													<input id="attaFile" type="file" style="display: none" id="attachment" name="attachment"/>
												</span>
												<script>
													function attaC(){
														return $("#attaFile").click();
													}
												</script>
												
												<label>收件人</label>
												<input id="receiver" type="text" name="receiver" value="" placeholder="<***@qq.com>" style="height: 30px">
												
												<div class="pull-right">
	                                            <label>快捷选择</label>
	                                            	<!-- 下拉列表 -->
	                                            	<div id="sediv">
                                                    </div>
                                                </div>
                                                 <script>
                                                 var datalist;
                                                 
                                                 $.ajax({
                                              		async:true,
                                              		url:"recipients",
                                              		type:"get",
     												dataType:"json",
                                              		success:function(data){
                                              			datalist = data
                                              			var html="";
                                                   
                                               			html = html+"<select id='select2-opt' style='width:220px' onchange='changeOne(this)'>"
                                           				html=html+"<option value='-1' >-----选择部门---</option>";

                                               			for(var i=0;i<data.length;i++){
                                               				html=html+"<option name='tag' value='"+i+"' >"+data[i].dept_name+"</option>";
                                               			}
                                               			html=html+"</select>"
                                               			
                                               			
                                               			html = html+"<select id='select3-opt' style='width:220px' onchange='changeTwo(this)'>"
                                           				html=html+"<option value='-1' >-----选择联系人---</option>";
                                               			html=html+"</select>"
                                               			
                                               			$("#sediv").append(html);
                                              		}
                                                 
                                              	})
                                              	
                                              		//发送给部门下的人
													function changeOne(e){
													    var k = $(e).val()
													    
													    $("#select3-opt").empty().append("<option value='-1' >-----选择联系人---</option>")
													
													    var flag = false
													    
													    if(k!=-1){
	                                               			var ddd = datalist[k].empInfos

	                                               			//存入收件人input
	                                               			$("#receiver").val(datalist[k].dept_id+"-"+ddd.length+"-"+datalist[k].dept_name)
	                                               			
	                                               			for(var i=0;i<ddd.length;i++){
	                                           					$("#select3-opt").append("<option name='emp' value='"+ddd[i].emp_email+"'>"+ddd[i].emp_name+"</option>")
													        	flag = true
	                                               			}
                                               			}
														
													}
													
                                                 	//发送给单人
													function changeTwo(e){
														var v = $(e).val()
														
														if(v==-1){
															v="";
														}
	                                                	 //存入收件人input
	                                                	 $("#receiver").val(v)
													}
                                                 </script>
											</div>
										</section>
									</div>
								</div>
								<textarea name="content" id="editor">
									${resendMsg.msg_content}
								</textarea>
							</form>
							<!-- 提交表单,发送邮件 -->
							<script>
								function back(){
									$.ajax({
										async:true,
                                  		url:"clear",
                                  		type:"get",
                                  		success:function(data){
                                  		}
									})
									window.location.href="cententindex.jsp"
								}
							
								function kk(path) {
									if(path == ""||path == null){
										alert("path无效")
										return;
									}
									$('#msgForm').attr("action", path).submit();
								}
							</script>
							<script>
								var myEditor = null;
								ClassicEditor .create(document.querySelector("#editor"),{
									ckfinder: {
							            uploadUrl: 'http://localhost:8080/J2eeEIIEP/fileUpload'
							        }
								
								})
								.then(editor => {
									myEditor = editor;
									height: 800;
									/* editor.plugins.get('FileRepository').createUploadAdapter  = (loader) => {
								        return new UploadAdapter(loader);
								    }; */
								})
								.catch(error => {
									console.log(error);
								})
								
							 </script>
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
