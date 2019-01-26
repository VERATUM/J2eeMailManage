package com.aerfa.serive.imp;

import java.util.List;

import com.aerfa.dao.LogDao;
import com.aerfa.dao.imp.LogDaoImp;
import com.aerfa.entity.Log;
import com.aerfa.serive.LogSerive;

public class LogSeriveImp implements LogSerive {
    LogDao logDaoImp = new LogDaoImp();

	@Override
	/**
	 * 添加日志
	 * Log 为实体 返回影响行数 
	 * */
	public int addLog(Log log) {
		
		return logDaoImp.addLog(log);
	}

	@Override
	/**
	 * 删除日志
	 * logid 为日志主键 
	 * 返回影响行数
	 * */
	public int deleteLog(Integer logid) {
		
		return logDaoImp.deleteLog(logid);
	}

	@Override
	/**
	 * 修改日志
	 *log为实体 通过log主键 修改内容
	 * 返回影响行数
	 * */
	public int updateLog(Log log) {
		
		return logDaoImp.updateLog(log);
	}

	@Override
	/**
	 * 查询所有日志
	 * 返回日志实体集合
	 * */
	public List<Log> selectLog() {
		
		return logDaoImp.selectLog();
	}

	@Override
	/**
	 * 查单条日志
	 * log 日志主键
	 * 返回Log日志实体
	 */
	public Log selecOneLog(Integer logid) {
		
		return logDaoImp.selecOneLog(logid);
	}
	

}
