package com.aerfa.entity;

//员工信息表
public class EmpInfo {
	
	//员工编号(主键)
	private Integer emp_id;
	//员工名称
	private String emp_name;
	//E-mail号码
	private String emp_email;
	//账户密码
	private String emp_password;
	//手机号
	private String emp_tel;
	//性别
	private String emp_sex;
	//生日
	private String emp_birthday;
	//住址ַ
	private String emp_address;
	//部门编号
	private Integer dept_id;
	//管理权限(0为管理)
	private Integer emp_state;
	//员工照片
	private String emp_photo;
	//员工部门名称
	private String dept_name;

	//获取方法
	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	public Integer getEmp_id() {
		return emp_id;
	}
	
	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}
	
	public String getEmp_name() {
		return emp_name;
	}
	
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	
	public String getEmp_email() {
		return emp_email;
	}
	
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	
	public String getEmp_password() {
		return emp_password;
	}
	
	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}
	
	public String getEmp_tel() {
		return emp_tel;
	}
	
	public void setEmp_tel(String emp_tel) {
		this.emp_tel = emp_tel;
	}
	
	public String getEmp_sex() {
		return emp_sex;
	}
	
	public void setEmp_sex(String emp_sex) {
		this.emp_sex = emp_sex;
	}
	
	public String getEmp_birthday() {
		return emp_birthday;
	}
	
	public void setEmp_birthday(String emp_birthday) {
		this.emp_birthday = emp_birthday;
	}
	
	public String getEmp_address() {
		return emp_address;
	}
	
	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}
	
	public Integer getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}
	
	public Integer getEmp_state() {
		return emp_state;
	}
	
	public void setEmp_state(Integer emp_state) {
		this.emp_state = emp_state;
	}
	
	public String getEmp_photo() {
		return emp_photo;
	}
	
	public void setEmp_photo(String emp_photo) {
		this.emp_photo = emp_photo;
	}
	
	
	
}
