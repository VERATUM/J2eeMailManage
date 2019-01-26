package com.aerfa.serive.imp;

import java.sql.SQLException;
import java.util.List;


import com.aerfa.dao.EmpInfoDao;
import com.aerfa.dao.imp.EmpInfoDaoImp;
import com.aerfa.entity.DeptInfo;
import com.aerfa.entity.EmpInfo;
import com.aerfa.serive.EmpInfoSerive;
import com.aerfa.utils.PageInfo;

public class EmpInfoSeriveImp implements EmpInfoSerive {

	EmpInfoDao empInfoDaoImp = new EmpInfoDaoImp();
	
	@Override
	public void editor(EmpInfo empInfo) {
		try {
			int id = empInfo.getEmp_id();
			String purl = empInfo.getEmp_photo();
			empInfoDaoImp.editor(id,purl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int addEmpInfo(EmpInfo empInfo) {
		return empInfoDaoImp.addEmpInfo(empInfo);
	}

	public int deleteEmpInfo(Integer emp_id) {
		return empInfoDaoImp.deleteEmpInfo(emp_id);
	}

	public int updateEmpInfo(EmpInfo empInfo) {
		return empInfoDaoImp.updateEmpInfo(empInfo);
	}

	public List<EmpInfo> selectEmpInfo() {
		return empInfoDaoImp.selectEmpInfo();
	}

	public EmpInfo selectOneEmpInfo(int emp_id) {
		return empInfoDaoImp.selectOneEmpInfo(emp_id);
	}
	
	public DeptInfo getDept(Integer dept_id) {
		return empInfoDaoImp.getDept(dept_id);
	}

	public EmpInfo selectOneEmpInfo(String emp_email) {
		return empInfoDaoImp.selectOneEmpInfo(emp_email);
	}

	public int updateEmpInfoPassword(String emp_email,String emp_password) {
		return empInfoDaoImp.updateEmpInfoPassword(emp_password,emp_email);
	}

	public List<EmpInfo> selectEmpInfo(Integer dept_id) {
		return empInfoDaoImp.selectEmpInfo(dept_id);
	}

	public List<EmpInfo> selectDeptEmpInfo() {
		return empInfoDaoImp.selectDeptEmpInfo();
	}

	public List<EmpInfo> selectDeptEmpInfo(Integer dept_id) {
		return empInfoDaoImp.selectDeptEmpInfo(dept_id);
	}

	public int deleteEmpInfo(String emp_id) {
		return empInfoDaoImp.deleteEmpInfo(emp_id);
	}

	@Override
	public List<EmpInfo> selectEmps(String emp_name) {
		return empInfoDaoImp.selectEmps(emp_name);
	}

	@Override
	public int updateOneEmpInfo(EmpInfo empInfo) {
		
		return empInfoDaoImp.updateOneEmpInfo(empInfo);
	}

	@Override
	public List<EmpInfo> selectEmpsSearch(String info) {
		return empInfoDaoImp.selectEmpsSearch(info);
	}
	
	
	/*
	@Test
	public void testEmail() {
		System.out.println(this.selectOneEmpInfo("cm"));
	}
	*/
}
