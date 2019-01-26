package com.aerfa.entity;

//邮件回复表
public class SendInfo {
	
	//回复件编号(主键)
	private Integer send_id;
	//回复件内容
	private String send_content;
	//回复件时间
	private String send_time;
	//回复员工编号
	private Integer sender;
	//对应发件编号
	private Integer msg_id;
	
	//获取方法
	
	public Integer getSend_id() {
		return send_id;
	}
	
	public void setSend_id(Integer send_id) {
		this.send_id = send_id;
	}
	
	public String getSend_content() {
		return send_content;
	}
	
	public void setSend_content(String send_content) {
		this.send_content = send_content;
	}
	
	public String getSend_time() {
		return send_time;
	}
	
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	
	public Integer getSender() {
		return sender;
	}
	
	public void setSender(Integer sender) {
		this.sender = sender;
	}
	
	public Integer getMsg_id() {
		return msg_id;
	}
	
	public void setMsg_id(Integer msg_id) {
		this.msg_id = msg_id;
	}
	
}
