package com.aerfa.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.aerfa.dao.EmpInfoDao;
import com.aerfa.entity.DeptInfo;
import com.aerfa.entity.EmpInfo;
import com.aerfa.utils.JDBCUntils;
import com.aerfa.utils.PageInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class EmpInfoDaoImp implements EmpInfoDao{

	QueryRunner qr = new QueryRunner(JDBCUntils.getDataSource());

	//添加信息
	public int addEmpInfo(EmpInfo empInfo) {
		int temp = 0 ;
		try {
			String sql = "INSERT INTO empinfo (emp_name,emp_email,emp_password,emp_tel,dept_id) VALUES(?,?,?,?,?)";
			temp = qr.update(sql,new Object[]{empInfo.getEmp_name(),empInfo.getEmp_email(),empInfo.getEmp_password(),empInfo.getEmp_tel(),empInfo.getDept_id()});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	
	//删除单条
	public int deleteEmpInfo(Integer emp_id) {
		int temp = -1 ;
		try {
			String sql = "DELETE FROM empinfo WHERE emp_id = ?";
			temp = qr.update(sql,emp_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	//修改信息
	public int updateEmpInfo(EmpInfo empInfo) {
		int temp = 0 ;
		try {
			String sql = "UPDATE empinfo SET emp_name=?,dept_id=?,emp_email=?,emp_tel=? WHERE emp_id=?";
			temp = qr.update(sql,new Object[] {empInfo.getEmp_name(),empInfo.getDept_id(),empInfo.getEmp_email(),empInfo.getEmp_tel(),empInfo.getEmp_id()});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	public int updateOneEmpInfo(EmpInfo empInfo) {
		int temp = 0 ;
		try {
			String sql = "UPDATE empinfo SET emp_birthday=?,emp_address=? WHERE emp_id=?";
			temp = qr.update(sql,empInfo.getEmp_birthday(),empInfo.getEmp_address(),empInfo.getEmp_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	//查询所有
	public List<EmpInfo> selectEmpInfo() {
		List<EmpInfo> list = null;
		try {
			String sql = "SELECT * FROM feedback LEFT JOIN empinfo ON(feedback.`emp_id`=empinfo.`emp_id`)";
			list = qr.query(sql, new BeanListHandler<>(EmpInfo.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//查询单条
	public EmpInfo selectOneEmpInfo(int emp_id) {
		EmpInfo empInfo= null;
		try {
			String sql = "SELECT * FROM empInfo WHERE emp_id=?";
			empInfo = qr.query(sql,new BeanHandler<>(EmpInfo.class),emp_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empInfo;
	}
	//通过员工所属部门查所属部门名称
	public DeptInfo getDept(Integer dept_id) {
		String sql = "SELECT * FROM deptinfo WHERE dept_id=?"; 
		DeptInfo deptInfo = null;
		
		try {
			deptInfo = qr.query(sql, new BeanHandler<>(DeptInfo.class),dept_id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return deptInfo;
	}
	
	
	
	public EmpInfo selectOneEmpInfo(String emp_email) {
		EmpInfo empInfo= null;
		try {
			String sql = "SELECT * FROM empInfo WHERE emp_email=?";
			empInfo = qr.query(sql,new BeanHandler<>(EmpInfo.class),emp_email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empInfo;
	}
	@Test
	public void s() {
		EmpInfo ei = new EmpInfo();	
		ei.setEmp_address("湖北");
		ei.setEmp_birthday("1995-08-20");
		ei.setEmp_id(6);
		System.out.println(this.updateOneEmpInfo(ei));
	}
	/*
	@Test
	public void tesgetDept() {
		DeptInfo a = this.getDept(6);
		System.out.println(a.getDept_id()+a.getDept_name());
	}
	@Test
	public void testAdd() {
		EmpInfo ei = new EmpInfo();
		ei.setEmp_name("张三");
		ei.setEmp_email("zhangsan@123.com");
		ei.setEmp_password("zhangsan");
		ei.setEmp_tel("13111111842");
		ei.setEmp_sex("男");
		ei.setEmp_address("湖南");
		ei.setDept_id(2);
		ei.setEmp_state(2);
		System.out.println(this.addEmpInfo(ei));
	}
	@Test
	public void testUpdata() {
		EmpInfo ei = new EmpInfo();
		ei.setEmp_password("zhangsan");
		ei.setEmp_tel("13111111842");
		ei.setEmp_birthday("2018-8-27");
		ei.setEmp_address("湖北");
		ei.setDept_id(2);
		ei.setEmp_photo("132132165.5");
		ei.setEmp_id(20);
		System.out.println(this.updateEmpInfo(ei));
	}
	@Test
	public void testSel() {
		System.out.println(this.selectEmpInfo());
	}
	@Test
	public void testSelOne() {
		Integer emp_id = 20;
		System.out.println(this.selectOneEmpInfo(emp_id));
	}
	@Test
	public void testDel() {
		Integer emp_id = 20;
		System.out.println(this.deleteEmpInfo(emp_id));
	}
	*/

	public List<EmpInfo> selectEmpInfo(Integer dept_id) {
		String sql = "SELECT * FROM empinfo where dept_id = ?";
		List<EmpInfo> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<>(EmpInfo.class),dept_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int updateEmpInfoPassword(String emp_email,String emp_password) {
		int temp = 0 ;
		try {
			String sql = "UPDATE empinfo SET emp_password=? WHERE emp_email=?";
			temp = qr.update(sql,new Object[] {emp_password,emp_email});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	public List<EmpInfo> selectDeptEmpInfo() {
		List<EmpInfo> lists= null;
		try {
			String sql = "SELECT empinfo.`emp_name`,deptinfo.`dept_name`,empinfo.`emp_email`,empinfo.`emp_tel`,empinfo.`emp_id` FROM empinfo LEFT JOIN deptinfo ON(empinfo.`dept_id`=deptinfo.`dept_id`)";
			List<Object[]> list = qr.query(sql, new ArrayListHandler());
			lists = new ArrayList<EmpInfo>();
			for (Object[] objects : list) {
				EmpInfo empInfo = new EmpInfo();
				empInfo.setEmp_name((String)objects[0]);
				empInfo.setDept_name((String)objects[1]);
				empInfo.setEmp_email((String)objects[2]);
				empInfo.setEmp_tel((String)objects[3]);
				empInfo.setEmp_id((Integer)objects[4]);
				lists.add(empInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	public List<EmpInfo> selectDeptEmpInfo(Integer dept_id) {
		List<EmpInfo> lists= null;
		try {
			String sql = "SELECT empinfo.`emp_name`,deptinfo.`dept_name`,empinfo.`emp_email`,empinfo.`emp_tel`,empinfo.`emp_id` FROM empinfo LEFT JOIN deptinfo ON(empinfo.`dept_id`=deptinfo.`dept_id`)WHERE empinfo.`dept_id`=?";
			List<Object[]> list = qr.query(sql, new ArrayListHandler(),dept_id);
			lists = new ArrayList<EmpInfo>();
			for (Object[] objects : list) {
				EmpInfo empInfo = new EmpInfo();
				empInfo.setEmp_name((String)objects[0]);
				empInfo.setDept_name((String)objects[1]);
				empInfo.setEmp_email((String)objects[2]);
				empInfo.setEmp_tel((String)objects[3]);
				empInfo.setEmp_id((Integer)objects[4]);
				lists.add(empInfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}
	/*
	@Test
	public void tes() {
		System.out.println(this.selectDeptEmpInfo(2));
	}
	*/

	@Override
	public int deleteEmpInfo(String emp_id) {
		int temp = -1 ;
		try {
			String sql = "DELETE FROM empinfo WHERE emp_id IN ("+emp_id+")";
			temp = qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public List<EmpInfo> selectEmps(String emp_name) {
		List<EmpInfo> list =null;
		String sql = "SELECT * FROM empinfo WHERE EMP_NAME LIKE ?";
		try {
			list = qr.query(sql, new BeanListHandler<EmpInfo>(EmpInfo.class), emp_name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List<EmpInfo> selectEmpsSearch(String info) {
		List<EmpInfo> lists= null;
		try {
			String sql = "SELECT empinfo.`emp_name`,deptinfo.`dept_name`,empinfo.`emp_email`,empinfo.`emp_tel`,empinfo.`emp_id` FROM empinfo LEFT JOIN deptinfo ON (empinfo.`dept_id`=deptinfo.`dept_id`)WHERE empinfo.`emp_name`LIKE ? OR deptinfo.`dept_name`LIKE ? OR empinfo.`emp_email`LIKE ? OR empinfo.`emp_tel`LIKE ?";
			List<Object[]> list = qr.query(sql, new ArrayListHandler(),"%"+info+"%","%"+info+"%","%"+info+"%","%"+info+"%");
			lists = new ArrayList<EmpInfo>();
			for (Object[] objects : list) {
				EmpInfo empInfo = new EmpInfo();
				empInfo.setEmp_name((String) objects[0]);
				empInfo.setDept_name((String) objects[1]);
				empInfo.setEmp_email((String) objects[2]);
				empInfo.setEmp_tel((String) objects[3]);
				empInfo.setEmp_id((Integer) objects[4]);
				lists.add(empInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	@Override
	public void editor(int id, String purl) throws SQLException {
		String sql = "update empinfo set emp_photo=? where emp_id=?";
		qr.update(sql,purl,id);
	}
}
