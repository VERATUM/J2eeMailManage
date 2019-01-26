package com.aerfa.entity;

//附件表
public class FileInfo {
	
	//附件标号(主键)
	private Integer file_id;
	//附件名称
	private String file_name;
	//附件类型
	private String file_type;
	//附件路径
	private String file;
	//邮件编号
	private Integer msg_id;

	//获取方法
	
	public Integer getFile_id() {
		return file_id;
	}
	
	public void setFile_id(Integer file_id) {
		this.file_id = file_id;
	}
	
	public String getFile_name() {
		return file_name;
	}
	
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	public String getFile_type() {
		return file_type;
	}
	
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	
	public String getFile() {
		return file;
	}
	
	public void setFile(String file) {
		this.file = file;
	}
	
	public Integer getMsg_id() {
		return msg_id;
	}
	
	public void setMsg_id(Integer msg_id) {
		this.msg_id = msg_id;
	}
	
}
