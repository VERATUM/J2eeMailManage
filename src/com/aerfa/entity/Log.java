package com.aerfa.entity;

//个人日志表
public class Log {
	
	//个人日志编号
	private Integer log_id;
	//个人日志内容
	private String log_content;
	//个人日志时间
	private String log_time;
	//员工边号
	private Integer emp_id;
	
	//获取方法
	
	public Integer getLog_id() {
		return log_id;
	}
	
	public void setLog_id(Integer log_id) {
		this.log_id = log_id;
	}
	
	public String getLog_content() {
		return log_content;
	}
	
	public void setLog_content(String log_content) {
		this.log_content = log_content;
	}
	
	public String getLog_time() {
		return log_time;
	}
	
	public void setLog_time(String log_time) {
		this.log_time = log_time;
	}
	
	public Integer getEmp_id() {
		return emp_id;
	}
	
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	
}
