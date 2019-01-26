package com.aerfa.entity;

//邮件表
public class MsgInfo {
	public static final int DELETE=1;
	public static final int NO_DELETE=0;
	public static final int READED=1;
	public static final int NO_READED=0;
	public static final int NOTE=1;
	public static final int EMAIL=0;
	public static final int STAR=2;
	public static final int NO_STAR=1;
	
	//邮件编号
	private Integer msg_id;
	//邮件标题
	private String msg_subject;
	//邮件内容
	private String msg_content;
	//邮件发送时间
	private String msg_sendtime;
	//发件员工编号
	private Integer emp_id;
	//接收人邮箱
	private String emp_email;
	//邮件格式(1代表草稿箱  0代表发件)
	private Integer msg_issave;
	//邮件状态(2代表星标 1代表已读 0代表未读)
	private Integer msg_isread;
	//邮件是否删除(1代表删除 0代表未删除)
	private Integer msg_isdelete;
	//邮件是否为群邮件（0表示不是 1表示是）
	private Integer msg_atten;
	
	//获取方法
	public Integer getMsg_id() {
		return msg_id;
	}
	
	public Integer getMsg_atten() {
		return msg_atten;
	}

	public void setMsg_atten(Integer msg_atten) {
		this.msg_atten = msg_atten;
	}

	public void setMsg_id(Integer msg_id) {
		this.msg_id = msg_id;
	}
	
	public String getMsg_subject() {
		return msg_subject;
	}
	
	public void setMsg_subject(String msg_subject) {
		this.msg_subject = msg_subject;
	}
	
	public String getMsg_content() {
		return msg_content;
	}
	
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	
	public String getMsg_sendtime() {
		return msg_sendtime;
	}
	
	public void setMsg_sendtime(String msg_sendtime) {
		this.msg_sendtime = msg_sendtime;
	}
	
	public Integer getEmp_id() {
		return emp_id;
	}
	
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	
	public String getEmp_email() {
		return emp_email;
	}
	
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	
	public Integer getMsg_issave() {
		return msg_issave;
	}
	
	public void setMsg_issave(Integer msg_issave) {
		this.msg_issave = msg_issave;
	}
	
	public Integer getMsg_isread() {
		return msg_isread;
	}
	
	public void setMsg_isread(Integer msg_isread) {
		this.msg_isread = msg_isread;
	}
	
	public Integer getMsg_isdelete() {
		return msg_isdelete;
	}
	
	public void setMsg_isdelete(Integer msg_isdelete) {
		this.msg_isdelete = msg_isdelete;
	}
	
}
