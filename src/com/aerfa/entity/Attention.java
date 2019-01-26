package com.aerfa.entity;

//特别关心
public class Attention {
	
	//关注编号(主键)
	private Integer atten_id;
	//关注人
	private Integer emp_id_a;
	//被关注人
	private Integer emp_id_b;
	//关注人姓名
	private String a_emp_name;
	//被关注人姓名
	private String b_emp_name;
	//被关注人的邮箱
	private String emp_email;
	
	//获取方法
	
	/**
	 * @return the emp_email
	 */
	public String getEmp_email() {
		return emp_email;
	}

	/**
	 * @param emp_email the emp_email to set
	 */
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}

	public Integer getAtten_id() {
		return atten_id;
	}
	
	public String getA_emp_name() {
		return a_emp_name;
	}

	public void setA_emp_name(String a_emp_name) {
		this.a_emp_name = a_emp_name;
	}

	public String getB_emp_name() {
		return b_emp_name;
	}

	public void setB_emp_name(String b_emp_name) {
		this.b_emp_name = b_emp_name;
	}

	public void setAtten_id(Integer atten_id) {
		this.atten_id = atten_id;
	}
		public Integer getEmp_id_a() {
		return emp_id_a;
	}
	
	public void setEmp_id_a(Integer emp_id_a) {
		this.emp_id_a = emp_id_a;
	}
	
	public Integer getEmp_id_b() {
		return emp_id_b;
	}
	
	public void setEmp_id_b(Integer emp_id_b) {
		this.emp_id_b = emp_id_b;
	}
	
}
