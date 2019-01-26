package com.aerfa.serive;

import java.util.List;

import com.aerfa.entity.DeptInfo;
import com.aerfa.entity.EmpInfo;
import com.aerfa.utils.PageInfo;

public interface EmpInfoSerive {
	/**
	 * 员工信息添加
	 * @param empInfo 传一个员工信息实体
	 * @return 返回影响行数
	 */
	public int addEmpInfo(EmpInfo empInfo);
	/**
	 * 员工信息删除
	 * @param emp_id 传一个员工编号
	 * @return 返回影响行数
	 */
	public int deleteEmpInfo(Integer emp_id);
	/**
	 * 员工信息修改
	 * @param empInfo 传一个员工信息实体
	 * @return 返回影响行数
	 */
	public int updateEmpInfo(EmpInfo empInfo);
	public int updateOneEmpInfo(EmpInfo empInfo);
	/**
	 * 员工信息查询
	 * @return 返回EmpInfo实体集合
	 */
	public List<EmpInfo> selectEmpInfo();
	/**
	 * 员工信息查询单条(根据编号)
	 * @param emp_id 传一个员工编号
	 * @return 返回EmpInfo实体
	 */
	public EmpInfo selectOneEmpInfo(int emp_id);
	
	/**
	 * 通过部门编号查询所属部门
	 * @param emp_id 传一个部门编号
	 * @return 返回DeptInfo实体
	 */
    public DeptInfo getDept(Integer dept_id);
    /**
	 * 员工信息查询单条(根据邮箱)
	 * @param emp_email 传一个邮箱账号
	 * @return 返回EmpInfo实体
	 */
	public EmpInfo selectOneEmpInfo(String emp_email);
	/**
	 * 根据员工邮箱号修改密码
	 * @param emp_email 传一个员工邮箱号
	 * @param emp_password 传一个新密码
	 * @return 返回影响行数
	 */
	public int updateEmpInfoPassword(String emp_email,String emp_password);
	/**
	 * 根据部门编号查询所有员工实体
	 * @param dept_id 传一个部门编号
	 * @return	返回实体集合
	 */
	public List<EmpInfo> selectEmpInfo(Integer dept_id);
	/**
	 * 联合部门表查询
	 * @return 返回姓名,部门名称,Email,电话号
	 */
	public List<EmpInfo> selectDeptEmpInfo();
	/**
	 * 联合部门表查询
	 * @param dept_id 传一个部门编号
	 * @return 返回某部门姓名,部门名称,Email,电话号
	 */
	public List<EmpInfo> selectDeptEmpInfo(Integer dept_id); 
	/**
	 * 多项删除
	 * @param emp_id 员工编号
	 * @return 影响行数
	 */
	public int deleteEmpInfo(String emp_id);
	/**
	 * 模糊查询
	 * @param emp_name 通过员工姓名
	 * @return
	 */
	public List<EmpInfo> selectEmps(String emp_name);
	/**
	 * 模糊查询
	 * @param info 传模糊查询内容
	 * @return 返回实体集合
	 */
	public List<EmpInfo> selectEmpsSearch(String info);
	void editor(EmpInfo empInfo);
}
