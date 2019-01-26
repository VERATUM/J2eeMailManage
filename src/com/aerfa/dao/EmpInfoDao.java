package com.aerfa.dao;

import java.sql.SQLException;
import java.util.List;

import com.aerfa.entity.DeptInfo;
import com.aerfa.entity.EmpInfo;
import com.aerfa.utils.PageInfo;

public interface EmpInfoDao {
	//添加
	public int addEmpInfo(EmpInfo empInfo);
	//删除
	public int deleteEmpInfo(Integer emp_id);
	//修改
	public int updateEmpInfo(EmpInfo empInfo);
	public int updateOneEmpInfo(EmpInfo empInfo);
	//查所有
	public List<EmpInfo> selectEmpInfo();
	//查单条
	public EmpInfo selectOneEmpInfo(int emp_id);
	//通过员工部门编号查所属部门
	public DeptInfo getDept(Integer dept_id);
	//查邮箱
	public EmpInfo selectOneEmpInfo(String emp_email);
	// 通过部门编号查询所有员工
	public List<EmpInfo> selectEmpInfo(Integer dept_id);
	//通过邮箱号改变密码
	public int updateEmpInfoPassword(String emp_email,String emp_password);
	//多表查询所有
	public List<EmpInfo> selectDeptEmpInfo();
	//多表查询特定部门
	public List<EmpInfo> selectDeptEmpInfo(Integer dept_id);
	//多项删除
	public int deleteEmpInfo(String emp_id);
	// 通过员工名查询相应的员工
	public List<EmpInfo> selectEmps(String emp_name);
	//模糊查询
	public List<EmpInfo> selectEmpsSearch(String info);

	public void editor(int id, String purl) throws SQLException;

}
