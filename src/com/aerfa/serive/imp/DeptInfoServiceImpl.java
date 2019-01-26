package com.aerfa.serive.imp;

import java.util.List;

import com.aerfa.dao.DeptInfoDao;
import com.aerfa.dao.imp.DeptInfoDaoImpl;
import com.aerfa.entity.DeptInfo;
import com.aerfa.serive.DeptInfoService;

public class DeptInfoServiceImpl implements DeptInfoService {
	DeptInfoDao deptInfoDao = new DeptInfoDaoImpl();
	@Override
	public int addDeptInfo(String dept_name) {
		return deptInfoDao.addDeptInfo(dept_name);
	}

	@Override
	public int deleteDeptInfo(Integer dept_id) {
		return deptInfoDao.deleteDeptInfo(dept_id);
	}

	@Override
	public int updateDeptInfo(DeptInfo deptInfo) {
		return deptInfoDao.updateDeptInfo(deptInfo);
	}

	@Override
	public List<DeptInfo> selectDeptInfos() {
		return deptInfoDao.selectDeptInfos();
	}

	@Override
	public DeptInfo selectDeptInfo(Integer dept_id) {
		return deptInfoDao.selectDeptInfo(dept_id);
	}

	@Override
	public int addDeptInfo(DeptInfo deptInfo) {
		return deptInfoDao.addDeptInfo(deptInfo);
	}

	@Override
	public List<DeptInfo> selectDeptInfosAndEmpinfos() {
		return deptInfoDao.selectDeptInfosAndEmpinfos();
	}

}
