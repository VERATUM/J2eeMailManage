package com.aerfa.entity;

//反馈意见表
public class Feedback {

	//反馈意见编号(主键)
	private Integer feed_id;
	//反馈标题
	private String feed_title;
	//反馈时间
	private String feed_time;
	//反馈内容
	private String feed_content;
	//员工编号
	private Integer emp_id;
	//员工对象
	private String emp_name;
	//获取方法


	public Integer getFeed_id() {
		return feed_id;
	}
	
	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public void setFeed_id(Integer feed_id) {
		this.feed_id = feed_id;
	}
	
	public String getFeed_title() {
		return feed_title;
	}
	
	public void setFeed_title(String feed_title) {
		this.feed_title = feed_title;
	}
	
	public String getFeed_time() {
		return feed_time;
	}
	
	public void setFeed_time(String feed_time) {
		this.feed_time = feed_time;
	}
	
	public String getFeed_content() {
		return feed_content;
	}
	
	public void setFeed_content(String feed_content) {
		this.feed_content = feed_content;
	}
	
	public Integer getEmp_id() {
		return emp_id;
	}
	
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	
}
