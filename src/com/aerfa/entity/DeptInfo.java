package com.aerfa.entity;

import java.util.List;

//部门类型
public class DeptInfo {

	//部门编号(主键)
	private Integer dept_id;
	//部门名称
	private String dept_name;
	// 一个部门对应多个员工
	private List<EmpInfo> empInfos;
	
	//获取方法
	public List<EmpInfo> getEmpInfos() {
		return empInfos;
	}

	public void setEmpInfos(List<EmpInfo> empInfos) {
		this.empInfos = empInfos;
	}
	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getDept_name() {
		return dept_name;
	}
	
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
}
