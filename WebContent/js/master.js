/**
 * master.jsp
 */
$(function() {
	// 加载master.jsp时异步访问数据,显示页面内容
	$.post("MasterServlet", {"action":"default_query"}, function(data) {
		// 1. 获取数据
		// 1.1 判断用户登录
		if (data[0].length==0) {
			var user = data[1]; 
			var depts = data[2];
			var user_c = data[3];
			// 1.2 显示用户
			/*$("#user_notice").html("("+user.emp_name+",您好!)").css({"color":"blue","font-weight":"bold"});*/
			
			// 2.加载通讯录的菜单栏
			var ul_other = '<ul class="nav">';
			var dept_mine;
			for (var i = 0; i < depts.length; i++) {
				var dept = depts[i];
				if(user.dept_id==dept.dept_id){
					// 2.1 获取用户的部门
					dept_mine = dept;
					// 2.2 我的部门
					var ul_mine = '<ul class="nav">'+
						'<li class="b-b b-light"><a href="#"><i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i>'+dept.dept_name+
						'('+dept.empInfos.length+')</a><input type="hidden" value="'+dept.dept_id+'" /></li>'+
						'</ul>';
					$(".master_dept_mine").html(ul_mine);
				}else{
					// 2.3 其他部门
					ul_other += '<li class="b-b b-light"><a href="#"><i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i>'+dept.dept_name+
					'('+dept.empInfos.length+')</a><input type="hidden" value="'+dept.dept_id+'" /></li>';
				}
			}
			ul_other += '</ul>';
			$(".master_dept_other").html(ul_other);
			
			// 3. 点击相应的部门，显示具体的员工信息
			$("aside#subNav .dept_title li a").each(function(i,obj) {
				// 获取部门编号dept_id
				var deid = $(this).next().val();
				$(obj).click(function() {
					// 通过选中的部门编号找到对应的部门
					var edept ='';
					for (var j = 0; j < depts.length; j++) {
						if(deid==depts[j].dept_id){
							edept = depts[j];
							break;
						}
					}
					// 部门里的员工实体
					var emps = edept.empInfos;
					var emp_tr ='';
					$("#emp_table_show table caption").html(edept.dept_name+'('+edept.empInfos.length+')').css({"background-color":"#25313e","color":"white","padding":"9px","font-weight":"bold","font-size":"18px"});
					var emp_htr = '<tr><th width="20"><input type="checkbox" value="0"><input type="hidden" value="'+edept.dept_id+'"></th>'+
						'<th width="20"></th><th class="th-sortable" data-toggle="class">姓名</th>'+
						'<th>邮箱</th><th>电话</th><th>部门</th><th>职位</th><th>性别</th></tr>';
					$("#emp_table_show table thead").html(emp_htr);
					for (var k = 0; k < emps.length; k++) {
						var emp = emps[k];
						var user_name = '';
						if(emp.emp_name == user.emp_name){
							user_name = '<span style="color:red;">'+emp.emp_name+'(我)</sapn>';
						}else{
							user_name = '<span>'+emp.emp_name+'</sapn>';
						}
						emp_tr += '<tr>'+
						'<td><input type="hidden" value="'+emp.emp_name+'"/><input type="checkbox" name="post[]" value="'+emp.emp_id+'"><input type="hidden" value="'+deid+'"/></td>'+
						'<td><a href="#modal" data-toggle="modal" title="查看个人资料"><i class="fa fa-search-plus"></i></a><input type="hidden" value="'+emp.emp_id+'"><input type="hidden" value="'+emp.dept_id+'"></td>'+
						'<td>'+user_name+'</td><td>'+emp.emp_email+'</td><td>'+emp.emp_tel+'</td>'+
						'<td>'+edept.dept_name+'</td><td>'+switch_emp(emp.emp_state)+'</td>'+
						'<td>'+emp.emp_sex+'</td>'+
						'</tr>';
					}
					$("#emp_table_show table tbody").html(emp_tr);
					$("#contact_query").val('');
				});
			});
            // 3.1 进入页面默认显示用户部门信息
            var emp_mine = dept_mine.empInfos;
            var emp_mine_tr ='';
            // 表头
            var emp_htr = '<tr><th width="20"><input type="checkbox" value="0"><input type="hidden" value="'+dept_mine.dept_id+'"></th>'+
			'<th width="20"></th><th class="th-sortable" data-toggle="class">姓名</th>'+
			'<th>邮箱</th><th>电话</th><th>部门</th><th>职位</th><th>性别</th></tr>';
            $("#emp_table_show table thead").html(emp_htr);
            $("#emp_table_show table caption").html(dept_mine.dept_name+'('+dept_mine.empInfos.length+')').css({"background-color":"#25313e","color":"white","padding":"9px","font-weight":"bold","font-size":"18px"});
            for (var i = 0; i < emp_mine.length; i++) {
                var emp = emp_mine[i];
                var user_name = '';
				if(emp.emp_name == user.emp_name){
					user_name = '<span style="color:red;">'+emp.emp_name+'(我)</sapn>';
				}else{
					user_name = '<span>'+emp.emp_name+'</sapn>';
				}
                emp_mine_tr += '<tr>'+
                    '<td><input type="hidden" value="'+emp.emp_name+'"/><input type="checkbox" name="post[]" value="'+emp.emp_id+'"><input type="hidden" value="'+dept_mine.dept_id+'"/></td>'+
                    '<td><a href="#modal" data-toggle="modal" title="查看个人资料"><i class="fa fa-search-plus"></i></a><input type="hidden" value="'+emp.emp_id+'"></td>'+
                    '<td>'+user_name+'</td><td>'+emp.emp_email+'</td><td>'+emp.emp_tel+'</td>'+
                    '<td>'+dept_mine.dept_name+'</td><td>'+switch_emp(emp.emp_state)+'</td>'+
                    '<td>'+emp.emp_sex+'</td>'+
                    '</tr>';
            }
            $("#emp_table_show tbody").html(emp_mine_tr);

			// 4. 菜单栏显示常用联系人,user_c为当前用户的常用联系人集合
			var contacts_mine = '<ul class="nav">'+
			'<li class="b-b b-light"><a href="#"><i class="fa fa-chevron-right pull-right m-t-xs text-xs icon-muted"></i>常用联系人'+
			'('+user_c.length+')</a><input type="hidden" value="'+user.emp_id+'" /></li>'+
			'</ul>';
			$(".master_contact_mine").html(contacts_mine);
			
			// 5 点击常用联系人，显示具体的员工信息
			$("aside#subNav .master_contact_mine li a").click(function() {
				// 显示员工
				var emp_tr ='';
                $("#emp_table_show table caption").html("常用联系人"+'('+user_c.length+')').css({"background-color":"#25313e","color":"white","padding":"9px","font-weight":"bold","font-size":"18px"});
                var emp_htr = '<tr><th width="20"><input type="checkbox" value="0"><input type="hidden" value="-5"></th>'+
				'<th width="20"></th><th class="th-sortable" data-toggle="class">姓名</th>'+
				'<th>邮箱</th><th>电话</th><th>部门</th><th>职位</th><th>性别</th></tr>';
	            $("#emp_table_show table thead").html(emp_htr);
				for (var i = 0; i < user_c.length; i++) {
					var emp = user_c[i];
					// 单个员工的部门名称判断
					var emp_dept_name ='';
					for (var j = 0; j < depts.length; j++) {
						var edept = depts[j];
						if (emp.dept_id==edept.dept_id) {
							emp_dept_name = edept.dept_name;
						}
					}
					emp_tr += '<tr>'+
					'<td><input type="hidden" value="'+emp.emp_name+'"/><input type="checkbox" name="post[]" value="'+emp.emp_id+'"><input type="hidden" value="-5"/></td>'+
					'<td><a href="#modal" data-toggle="modal" title="查看个人资料"><i class="fa fa-search-plus"></i></a><input type="hidden" value="'+emp.emp_id+'"></td>'+
					'<td>'+emp.emp_name+'</td><td>'+emp.emp_email+'</td><td>'+emp.emp_tel+'</td>'+
					'<td>'+emp_dept_name+'</td><td>'+switch_emp(emp.emp_state)+'</td>'+
					'<td>'+emp.emp_sex+'</td>'+
					'</tr>';
				}
				$("#emp_table_show tbody").html(emp_tr);
				$("#contact_query").val('');
				if(user_c.length==0){
					emp_tr = "还没有联系人，去<a href='master.jsp' style='text-decoration:underline;color:red'>添加</a>一个吧"
						$("#emp_table_show .login_no").html(emp_tr).css({"padding":"20px","text-align":"center","font-size":"18px","font-weight":"bold","color":"blue"});
				}
			});
            // 6.查看个人资料
            $("#emp_table_show tbody").on("click","td a[href='#modal']",function () {
                var eid = $(this).next().val();
                $.post("MasterServlet", {"action":"emp_query","eid":eid},function (data_emp) {
                	var emp = data_emp[0];
                	var user_name = '';
    				if(emp.emp_name == user.emp_name){
    					user_name = '<span style="color:red;">'+emp.emp_name+'(我)</sapn>';
    					user_mail = '<a href="writemail.jsp" style="text-decoration: underline" title="写邮件">'+emp.emp_email+'</a>';
    				}else{
    					user_name = '<span>'+emp.emp_name+'</sapn>';
    					user_mail = '<a href="writemail.jsp" style="text-decoration: underline" title="给他写邮件">'+emp.emp_email+'</a>';
    				}
                	var emp_dept = data_emp[1];
                    $(".modal-body header span").html("部门");
                    $(".modal-body header #emp_dept").html(emp_dept);
                    $(".modal-body table thead th:eq(1)").html(switch_emp(emp.emp_state));
                    $(".modal-body table tbody tr:eq(0) td:eq(1)").html(emp.emp_id);
                    $(".modal-body table tbody tr:eq(0) td:eq(2)").html('<img src="images/avatar.jpg" width="150" height="150" alt="x" title=默认"/>');
                    $(".modal-body table tbody tr:eq(1) td:eq(1)").html(user_name);
                    $(".modal-body table tbody tr:eq(2) td:eq(1)").html(emp.emp_sex);
                    $(".modal-body table tbody tr:eq(3) td:eq(1)").html(user_mail);
                    $(".modal-body table tbody tr:eq(4) td:eq(1)").html(emp.emp_tel);
                    $(".modal-body table tbody tr:eq(5) td:eq(1)").html(emp.emp_address);
                   /* $(".modal-body table tbody tr:eq(6) td:eq(1)").html('<a href="#" style="text-decoration: underline">查看</a>');*/
                },"json");
            });
			// 7.1a添加自定义联系人
            $('.btn-group button[title="添加到联系人"]').click(function () {
            	var checkbox ='';
            	var checks = '';
                $("#emp_table_show tbody input[type='checkbox']").each(function (i,obj) {
                    if($(this).prop("checked")){
                        checkbox += $(obj).val()+',';
                        checks += $(obj).prev().val()+'(员工号:'+$(obj).val()+'),';
                    }
                });
                if(checks.length!=0){
	                var flag = confirm("确认要添加以下的员工吗?\n"+checks.substring(0,checks.length-1));
	                if(flag){
	                	// 处理数据
	                    $.post("MasterServlet",{"action":"add_contact","deids":checkbox,"deids_notices":checks,"user_id":user.emp_id},function (adddata) {
	                    	if(adddata[2].length==0){
	                    		// 添加的均为重复的
	                    		alert(adddata[0]+"\n"+adddata[1]+"\n");
	                    	}else{
	                    		alert("共添加:"+adddata[3]+"个联系人\n添加联系人为:"+adddata[2]+"\n"+adddata[0]+"\n"+adddata[1]);
	                    	}
	                    	window.location.href="master.jsp";
	                    },"json");
	                }else{
	                    /*$("#emp_table_show tbody input[type='checkbox']").each(function (i,obj) {
	                        if(this.checked==true) {
	                            this.checked = false;
	                        }
	                    });*/
	                    /*window.location.href="master.jsp";*/
	                }
                }else{
                	alert("没有添加的联系人");
                }
            });
            // 7.1b删除自定义联系人
            $('.btn-group button[title="移除此联系人"]').click(function () {
            	var checkbox2 ='';
            	var checks2 = '';
                $("#emp_table_show tbody input[type='checkbox']").each(function (i,obj) {
                    if($(this).prop("checked")){
                        checkbox2 += $(obj).val()+',';
                        checks2 += $(obj).prev().val()+'(员工号:'+$(obj).val()+'),';
                    }
                });
                if(checks2.length!=0){
	                var flag = confirm("确认要删除以下的员工吗?\n"+checks2.substring(0,checks2.length-1));
	                if(flag){
	                	// 处理数据
	                    $.post("MasterServlet",{"action":"delete_contact","deids":checkbox2,"deids_notices":checks2,"user_id":user.emp_id},function (deldata) {
	                    	alert("共删除:"+deldata[1]+"个联系人\n"+"删除的联系人为:"+deldata[0]);
	                    	window.location.href="master.jsp";
	                    },"json");
	                }
                }else{
                	alert("没有删除的联系人");
                }
            });
            // 7.1.3 点击邮件跳写邮件页面
            $('.btn-group button[title="写邮件"]').click(function () {
            	window.location.href="writemail.jsp";
            });
            // 7.2 部门与自定义联系人界面分类
            $("#emp_table_show").on("click","table input[type='checkbox']",function(){
                // 参数识别，部门或联系人
            	var ui_id = $(this).next().val();
               // alert(ui_id);
                var flag= 0; // 默认部门
                var count = 0;
                for (var i = 0; i < depts.length; i++) {
					if(ui_id==depts[i].dept_id){
						flag = 1; //点击的部门
						break;
					}else{
						count++;
					}
				}
                if(count==depts.length){
                	flag = 2; // 联系人
                }
                if($(this).prop("checked")){
                	$("header .input-group").hide();
                	if(flag==2){
                		$(".btn-group").css("display","block");
                		$(".btn-group button#show").css("display","block");
                		$(".btn-group button#mail_show").css("display","block");
                		$(".btn-group button#show1").css("display","none");
                	}else if(flag==1){
                		$(".btn-group").css("display","block");
                		$(".btn-group button#show").css("display","none");
                		$(".btn-group button#mail_show").css("display","block");
                		$(".btn-group button#show1").css("display","block");
                	}else{
                		$(".btn-group").css("display","none");
                	}
                }else{
                	$("header .input-group").show();
                	$(".btn-group").css("display","none");
                }
            });
            // 7.3 默认显示搜索界面
            $("aside#subNav div li").on("click","a",function(){
            	$("#emp_table_show table input[type='checkbox']").prop("checked",false);
            	$(".btn-group").fadeOut();
            	$("header .input-group").fadeIn();
            });
            // 8 搜索联系人
            $(".input-group #emps_query").click(function() {
            	var empname_q = $("#contact_query").val();
            	if (empname_q.length==0){
            		alert("请输入要查找的员工名字");
            	}else{
            		$.post("MasterServlet",{"action":"multi_contact","ename":empname_q},function(q_data){
            			var notice = q_data[0];
            			var emps_query = q_data[1];
            			if (notice.length!=0){
            				alert(notice);
            				$("#contact_query").val('');
            			}else{
            				var emp_tr ='';
                            $("#emp_table_show table caption").html("查询结果"+'('+emps_query.length+')').css({"background-color":"#25313e","color":"white","padding":"9px","font-weight":"bold","font-size":"18px"});
                            var emp_htr = '<tr><th width="20"><input type="checkbox" value="0"><input type="hidden" value="2"></th>'+
            				'<th width="20"></th><th class="th-sortable" data-toggle="class">姓名</th>'+
            				'<th>邮箱</th><th>电话</th><th>部门</th><th>职位</th><th>性别</th></tr>';
            	            $("#emp_table_show table thead").html(emp_htr);
            				for (var i = 0; i < emps_query.length; i++) {
            					var emp = emps_query[i];
            					// 单个员工的部门名称判断
            					var emp_dept_name ='';
            					for (var j = 0; j < depts.length; j++) {
            						var edept = depts[j];
            						if (emp.dept_id==edept.dept_id) {
            							emp_dept_name = edept.dept_name;
            						}
            					}
            					emp_tr += '<tr>'+
            					'<td><input type="hidden" value="'+emp.emp_name+'"/><input type="checkbox" name="post[]" value="'+emp.emp_id+'"><input type="hidden" value="2"/></td>'+
            					'<td><a href="#modal" data-toggle="modal" title="查看个人资料"><i class="fa fa-search-plus"></i></a><input type="hidden" value="'+emp.emp_id+'"></td>'+
            					'<td>'+emp.emp_name+'</td><td>'+emp.emp_email+'</td><td>'+emp.emp_tel+'</td>'+
            					'<td>'+emp_dept_name+'</td><td>'+switch_emp(emp.emp_state)+'</td>'+
            					'<td>'+emp.emp_sex+'</td>'+
            					'</tr>';
            				}
            				$("#emp_table_show tbody").html(emp_tr);
            				if(emps_query.length==0){
            					$("#emp_table_show table thead").html('');
            					emp_tr = "还没有找到相关联系人，重新搜索一下吧";
        						$("#emp_table_show .login_no").html(emp_tr).css({"padding":"20px","text-align":"center","font-size":"18px","font-weight":"bold","color":"blue"}).fadeIn();
        						$("#contact_query").val('');
        						setTimeout(function() {
        							$("#emp_table_show .login_no").hide(600);
        						}, 3000);
            				}
            			}
            		},"json");
            	}
			});
		}else{
			$("#emp_table_show .login_no").html(data).css({"padding":"20px","text-align":"center","font-size":"18px","font-weight":"bold","color":"red"}).fadeIn();
		}
	}, "json");
});

$(function(){
    //搜索给出默认提示
    $(".vbox .header .input-group #contact_query").bind({"focus":function(){
            if(this.placeholder == '(搜索联系人姓名)')
                this.placeholder = '';
        },"blur":function(){
            if(this.value == '')
                this.placeholder = '(搜索联系人姓名)';
        }
    });
   /* $(".vbox .header .input-group #contact_query2").bind({"focus":function(){
	        if(this.placeholder == '(员工姓名)')
	            this.placeholder = '';
	    },"blur":function(){
	        if(this.value == '')
	            this.placeholder = '(员工姓名)';
	    }
    });*/
    // 通讯录头颜色设置
    $(".contact_title").each(function(i,obj){
        if(i==0){
            $(obj).css({"background-color":"#89cc97","color":"white","font-size":"16px"});
        }else{
            $(obj).css({"background-color":"#25313e","color":"white"});
        }
    });
    $(".vbox header.header").css("background-color","#f1f1f1");
    // 个人信息弹框头颜色
    //$(".modal-header").css({"background-color":"#25313e","color":"white"});
    $(".modal-header").css({"background-color":"#89cc97","color":"white","font-size":"16px"});
	$(".modal-dialog .emp_infos").css("width","450px");
	$(".modal-footer button[type='button']").click(function () {
		alert("helo");
    });
});
// 定义筛选职位的函数
function switch_emp(x) {
	var y = "unknow";
	switch (x) {
		case 0:
			y = "管理员";
			break;
        case 1:
            y = "经理";
            break;
        case 2:
            y = "职员";
            break;
		default:
            y = "unknow";
            break;
    }
    return y;
}