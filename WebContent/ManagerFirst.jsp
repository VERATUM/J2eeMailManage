<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en" class="app">

<head>
<meta charset="utf-8" />
<link rel="shortcut icon" href="images/1.ico" type="image/x-icon">
<title>APEX | 后台管理</title>
<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="css/app.v2.css" type="text/css" />
<link rel="stylesheet" href="js/fuelux/fuelux.css" type="text/css" cache="false">
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	//姓名框newname  newtel  newstate  newmail
	//errorname  errormail  errorstate errortel
	function names(){
		var newnames = $.trim($("#newname").val());
		var regname=/^[\u0391-\uFFE5]+$/;
		if(newnames==""){
			$("#errorname").html("姓名不能为空");
			return false;
		}else if(!regname.test(newnames)){
			$("#errorname").html("姓名只能是汉字");
			return false;
		}else{
			$("#errorname").html("");
			return true;
		}
	}
	//邮箱框
	function emails(){
		var newmail = $.trim($("#newmail").val());
		var regmail=/^[a-z]{1,20}@123.com$/;
		if(newmail==""){
			$("#errormail").html("邮箱不能为空");
			return false;
		}else if(!regmail.test(newmail)){
			$("#errormail").html("邮箱格式不正确");
			return false;
		}else{
			$.post("ManagerOperation",{newmail:newmail,action:"newmail"},function(data){
				if(data==1){
					$("#errormail").html("");
					a = 1 ;
				}else{
					$("#errormail").html("邮箱已存在");
					a = 0 ;
				}
			});
			if(a==0){
				return false;
			}else{
				return true;
			}
		}
	}
	//部门框
	function states(){
		var newmail = $("#newstate").val();
		if(newmail==0){
			$("#errorstate").html("请选择一个部门");
			return false ;
		}else{
			$("#errorstate").html("");
			return true ;
		}
	}
	//电话框
	function tels(){
		var newtel = $.trim($("#newtel").val());
		var regtel=/^1\d{10}$/;
		if(newtel==""){
			$("#errortel").html("电话号不能为空");
			return false;
		}else if(!regtel.test(newtel)){
			$("#errortel").html("电话号格式不正确");
			return false;
		}else{
			$("#errortel").html("");
			return true;
		}
	}
	$(function(){
		//默认加载Ajax初始化数据
		$.get("ManagerServlet?action=0", function(data){
		});
		//下拉框改变时
		$("#selectChange").change( function() {
			  var option = $(this).val();
			  window.location.href="ManagerServlet?action="+option;
		});
		//点击搜索searchlist
		$("#searchlist").click(function(){
			var st = $("#searchtext").val();
			 window.location.href="SearchMoreServlet?searchtext="+st;
		});
		//全选
		$("#a").click(function(){
			$(".b").prop("checked",this.checked);
		});
		//单条删除ManagerOperation?action=del  alert($(this).parent().prev().html())
		$(".c").click(function(){
			if(confirm('确定删除?')){
				var emp_id = $(this).parent().prev().html();
				var delrow = $(this).parent().parent();
				$.post("ManagerOperation",{emp_id:emp_id,action:"del"},function(data){
					if(data==1){
						delrow.remove();
					}else{
						alert('程序正在维护中!请稍后再来!');
					}
				});
			}
		})
		//多选删除
		$("#buttonDel").click(function(){
			var all = "";
			$("input:checkbox[name=b]:checked").each(function(){
				var curr = $(this).parent().next().next().next().next().next().html();
				all=curr+","+all;
				$(this).parent().parent().remove();
			});
			$.post("ManagerOperation",{all:all,action:"delmore"},function(data){
				if(data!=1){
					alert('程序正在维护中!请稍后再来!');
				}
			});
		});
		//新增点击事件
		$("#buttoninsert").click(function(){
			$("#persion").css("display","block");
		});
		//修改点击事件 
		var a_email ;
		var a_empid ;
		$(".d").click(function(){
			if($(this).text()=="修改"){
				$(this).text("保存");
				var tel = $(this).parent().prev().prev();
				var email = $(this).parent().prev().prev().prev();
				var state = $(this).parent().prev().prev().prev().prev();
				var statetext = state.text();
				var name = $(this).parent().prev().prev().prev().prev().prev();
				a_email = email.text();
				a_empid = $(this).parent().prev().text();
				$(this).parent().prev().prev().html('<input type="text" value='+tel.text()+'>');
				$(this).parent().prev().prev().prev().html('<input type="text" value='+email.text()+'>');
				$(this).parent().prev().prev().prev().prev().html('<select id="selectChanges" name="selectbumen"><c:forEach items="${sessionScope.deptInfo }" var="li"><c:if test="${li.dept_name }"><option value="${li.dept_id }" selected="selected">${li.dept_name }</option></c:if><c:if test="${li.dept_id!=sessionScope.flag }"><option value="${li.dept_id }">${li.dept_name }</option></c:if></c:forEach></select>');
				$("#selectChanges option").each(function(){
					var option = $(this);
					var scs = $(this).text();
					if(scs==statetext){
						option.attr("selected","selected");
					}
				});
				$(this).parent().prev().prev().prev().prev().prev().html('<input type="text" value='+name.text()+'>');
			}else{
				$(this).text("修改");
				var upemp_id = $(this).parent().prev().text();
				var tel = $(this).parent().prev().prev().children(1).val();
				var email = $(this).parent().prev().prev().prev().children(1).val();
				var state = $(this).parent().prev().prev().prev().prev().children().children(":selected").val();
				var name = $(this).parent().prev().prev().prev().prev().prev().children(1).val();
				var dept_name = $(this).parent().prev().prev().prev().prev();
				$.post("ManagerOperation",{action:"updata",state:state},function(data){
					dept_name.html(data);
				});
				$(this).parent().prev().prev().html(tel);
				$(this).parent().prev().prev().prev().html(email);
				$(this).parent().prev().prev().prev().prev().prev().html(name);
				$.post("ManagerOperation",{action:"update",tel:tel,email:email,state:state,name:name,a_email:a_email,a_empid:a_empid},function(data){
				});
			}
		});
		//点击x隐藏模态框
		$("#x").click(function(){
			$("#persion").css("display","none");
		});
		//focusout 事件
		$("#newname").focusout(function(){
			names();
		});
		$("#newtel").focusout(function(){
			tels();
		});
		$("#newstate").change(function(){
			states();
		});
		$("#newmail").focusout(function(){
			emails();
		});
		//提交表单
		$("#from").submit(function(){
			if(names()&&tels()&&states()&&emails()){
				return true;
			}else{
				return false;
			}
		});
	});
	
</script>
<style type="text/css">
#persion{
	position: fixed; 
	right:500px;
	width:500px;
	top: 200px;
	z-index:1000;
	display: none;
}
</style>
</head>
<body>
<div class="modal-body" id="persion">
	<form id="from" action="ManagerOperation?action=insert" method="post" >
		<section class="panel panel-default m-l-n-md m-r-n-md m-b-none">
					<header class="panel-heading" id="x"> <a href="#"
						class="label bg-danger pull-right">X</a><span id="emp_dept"></span></header>
					<table class="table table-striped m-b-none text-sm">
						<tbody>
							<tr>
								<td>员工姓名:</td>
								<td><input type="text" name="newname" id="newname" /></td>
								<td id="errorname"></td>
							</tr>
							<tr>
								<td>员工邮箱:</td>
								<td><input type="text" name="newemail" id="newmail" /></td>
								<td id="errormail"></td>
							</tr>
							<tr>
								<td>员工部门:</td>
								<td>
									<select id="newstate" name="newstate">
									<option  value="0" selected="selected">请选择部门</option>
                              		<c:forEach items="${sessionScope.deptInfo }" var="li">
										<option value="${li.dept_id }">${li.dept_name }</option>
                              		</c:forEach>
                              		</select>
								</td>
								<td id="errorstate"></td>
							</tr>
							<tr>
								<td>员工电话:</td>
								<td><input type="text" name="newtel" id="newtel" /></td>
								<td id="errortel"></td>
							</tr>
							<tr>
								<td><input type="submit" value="注册"  /></td>
								<td><input type="reset" value="重置"  /></td>
							</tr>
						</tbody>
					</table>
					</section>
	</form>				
</div>
<section class="vbox">
  <section>
    <section class="hbox stretch"> 
      <section id="content">
        <section class="vbox">
          <section class="scrollable padder">
            <ul class="breadcrumb no-border no-radius b-b b-light pull-in">
              <li><a href="#"><i class="fa fa-home"></i> 管理员系统</a></li>
            </ul>
            <div class="m-b-md">
              <h3 class="m-b-none">${sessionScope.EmpInfo.emp_name }管理员,你好</h3>
            </div>
            <section class="panel panel-default">
              <header class="panel-heading"> 欢迎使用管理系统 <i class="fa fa-info-sign text-muted" data-toggle="tooltip" data-placement="bottom" data-title="ajax to load the data."></i> </header>
              <div class="table-responsive">
                <table id="MyStretchGrid" class="table table-striped datagrid m-b-sm">
                  <thead>
                    <tr> 
                      <th colspan="6"><div class="row">
                          <div class="col-sm-8 m-t-xs m-b-xs">
                            <div class="select filter" data-resize="auto">
                              <select id="selectChange" name="selectbumen">
                              	<option value="0">所有人员</option>
                              	<c:forEach items="${sessionScope.deptInfo }" var="li">
									<c:if test="${li.dept_id==sessionScope.flag }">
										<option value="${li.dept_id }" selected="selected">${li.dept_name }</option>
									</c:if>
									<c:if test="${li.dept_id!=sessionScope.flag }">
										<option value="${li.dept_id }">${li.dept_name }</option>
									</c:if>
                              	</c:forEach>
                              </select>
                              <input type="button" id="buttonDel" name="deleteall" value="删除" />
                              <input type="button" id="buttoninsert" name="insertnew" value="新增" />
                            </div>
                          </div>
                          <div class="col-sm-4 m-t-xs m-b-xs">
                            <div class="input-group search datagrid-search">
                              <input type="text" id="searchtext" class="input-sm form-control" placeholder="Search">
                              <div class="input-group-btn">
                                <button id="searchlist" class="btn btn-default btn-sm"><i class="fa fa-search"></i></button>
                              </div>
                            </div>
                          </div>
                        </div>
                      </th>
                    </tr>
                    <tr><td><input type="checkbox" name="a" id="a" /></td><td>姓名</td><td>部门</td><td>邮箱</td><td>电话</td><td>操作</td></tr>
                    <c:forEach items="${sessionScope.listEmpInfo }" var="lis">
                    <tr><td><input type="checkbox" name="b" class="b" /></td><td>${lis.emp_name }</td><td>${lis.dept_name }</td><td>${lis.emp_email }</td><td>${lis.emp_tel }</td><td style="display: none;">${lis.emp_id }</td><td><a href="#" class="c">删除</a>/<a href="#" class="d">修改</a></td></tr>
                    </c:forEach>
                  </thead>	
                </table>
              </div>
            </section>
          </section>
        </section>
       </section>
    </section>
  </section>
</section>
</body>
</html>
