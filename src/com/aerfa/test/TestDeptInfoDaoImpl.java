package com.aerfa.test;

import java.util.List;

import org.junit.Test;

import com.aerfa.dao.imp.DeptInfoDaoImpl;
import com.aerfa.entity.DeptInfo;
import com.google.gson.Gson;

public class TestDeptInfoDaoImpl {
	DeptInfoDaoImpl di = new DeptInfoDaoImpl();
	Gson gson = new Gson();
	/**
	 * 查询所有测试
	 */
	@Test
	public void selectDeptInfos() {
		List<DeptInfo> list = di.selectDeptInfos();
		for (DeptInfo d : list) {
			System.out.println(gson.toJson(d));
		}
	}
	/**
	 * 查询单条测试
	 */
	@Test
	public void selectDeptInfo() {
		DeptInfo deptInfo = di.selectDeptInfo(8);
		System.out.println(gson.toJson(deptInfo));
	}
	@Test
	public void addDeptInfo() {
		/*int row = di.addDeptInfo("测试部test");
		System.out.println(row);*/
		DeptInfo t = new DeptInfo();
		t.setDept_id(8);
		t.setDept_name("测试部ttt");
		int row = di.addDeptInfo(t);
		System.out.println(row);
	}
	@Test
	public void updateDeptInfo() {
		DeptInfo t = new DeptInfo();
		t.setDept_id(8);
		t.setDept_name("测试部tdd");
		int row = di.updateDeptInfo(t);
		System.out.println(row);
	}
	@Test
	public void deleteDeptInfo() {
		int row = di.deleteDeptInfo(8);
		System.out.println(row);
	}
}
