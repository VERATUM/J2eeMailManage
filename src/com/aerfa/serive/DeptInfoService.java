package com.aerfa.serive;

import java.util.List;

import com.aerfa.entity.DeptInfo;

public interface DeptInfoService {
	/**
	 * 增加
	 * @param dept_name 部门名称
	 * @return 返回影响行数
	 */
	public int addDeptInfo(String dept_name);
	/**
	 * 删除
	 * @param dept_id 部门编号
	 * @return 返回影响行数
	 */
	public int deleteDeptInfo(Integer dept_id);
	/**
	 * 修改
	 * @param deptInfo 部门实体
	 * @return 返回影响行数
	 */
	public int updateDeptInfo(DeptInfo deptInfo);
	/**
	 * 查询所有部门
	 * @return 返回部门实体集合
	 */
	public List<DeptInfo> selectDeptInfos();
	/**
	 * 查询单个部门
	 * @param dept_id 部门编号
	 * @return 返回单个部门实体
	 */
	public DeptInfo selectDeptInfo(Integer dept_id);
	
	/**
	 * 增加
	 * @param deptInfo 部门实体
	 * @return 返回影响行数
	 */
	public int addDeptInfo(DeptInfo deptInfo);
	
	/**
	 * 查询所有部门，以及部门的员工
	 * @return 返回部门集合
	 */
	public List<DeptInfo> selectDeptInfosAndEmpinfos();
}
