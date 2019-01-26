package com.aerfa.dao.imp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.aerfa.dao.DeptInfoDao;
import com.aerfa.dao.EmpInfoDao;
import com.aerfa.entity.DeptInfo;
import com.aerfa.entity.EmpInfo;
import com.aerfa.utils.JDBCUntils;
/**
 * 部门接口的实现类
 * @author Ver
 *
 */
public class DeptInfoDaoImpl implements DeptInfoDao {
	QueryRunner qr = new QueryRunner(JDBCUntils.getDataSource());
	@Override
	public int addDeptInfo(String dept_name) {
		String sql = "INSERT INTO deptInfo(dept_name) VALUES(?)";
		int row = 0;
		try {
			row = qr.update(sql, dept_name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int deleteDeptInfo(Integer dept_id) {
		String sql = "DELETE FROM deptInfo where dept_id = ?";
		int row = 0;
		try {
			row = qr.update(sql, dept_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int updateDeptInfo(DeptInfo deptInfo) {
		String sql = "UPDATE deptInfo SET dept_name = ? WHERE dept_id = ?";
		int row = 0;
		try {
			row = qr.update(sql,deptInfo.getDept_name(),deptInfo.getDept_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public List<DeptInfo> selectDeptInfos() {
		String sql = "SELECT * FROM deptInfo";
		List<DeptInfo> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<DeptInfo>(DeptInfo.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public DeptInfo selectDeptInfo(Integer dept_id) {
		String sql = "SELECT * FROM deptInfo where dept_id = ?";
		DeptInfo deptInfo = null; 
		try {
			deptInfo = qr.query(sql, new BeanHandler<>(DeptInfo.class),dept_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deptInfo;
	}

	@Override
	public int addDeptInfo(DeptInfo deptInfo) {
		String sql = "INSERT INTO deptInfo VALUES(?,?)";
		int row = 0;
		try {
			row = qr.update(sql, deptInfo.getDept_id(),deptInfo.getDept_name());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public List<DeptInfo> selectDeptInfosAndEmpinfos() {
		String sql = "SELECT * FROM deptInfo";
		EmpInfoDao empInfoDao = new EmpInfoDaoImp();
		List<DeptInfo> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<DeptInfo>(DeptInfo.class));
			for (DeptInfo deptInfo : list) {
				List<EmpInfo> list1 = empInfoDao.selectEmpInfo(deptInfo.getDept_id());
				deptInfo.setEmpInfos(list1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}	
}
