package com.aerfa.dao.imp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.aerfa.dao.LogDao;
import com.aerfa.entity.DeptInfo;
import com.aerfa.entity.Log;
import com.aerfa.utils.JDBCUntils;

public class LogDaoImp implements LogDao {

	QueryRunner qr = new QueryRunner(JDBCUntils.getDataSource());
	/**
	 * 添加日志
	 * Log 为实体 返回影响行数 
	 * */
	public int addLog(Log log) {
		
		int tmp =0;
		try {
		String sql = "INSERT INTO LOG (log_content,log_time,emp_id)VALUES(?,NOW(),?)";		 
			 tmp = qr.update(sql, log.getLog_content(),log.getEmp_id());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return tmp;
	}

	@Override
	/**
	 * 删除日志
	 * logid 为日志主键 
	 * 返回影响行数
	 * */
	public int deleteLog(Integer logid) {
		int tmpe=0;
		
		try {
			String sql = "DELETE FROM LOG WHERE log_id=?";
			tmpe=qr.update(sql, logid);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return tmpe;
	}

	@Override
	/**
	 * 修改日志
	 *log为实体 通过log主键 修改内容
	 * 返回影响行数
	 * */
	public int updateLog(Log log) {
		int temp = 0;		
		try {
			String sql = "UPDATE LOG SET  log_content=?,log_time=NOW() WHERE log_id=?;";
			temp = qr.update(sql, log.getLog_content(),log.getLog_id());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	/**
	 * 查询所有日志
	 * 返回日志实体集合
	 * */
	public List<Log> selectLog() {
		String sql = "SELECT * FROM LOG";
		List<Log> list= null ;
		try {
			list = qr.query(sql, new BeanListHandler<>(Log.class));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	/**
	 * 查单条日志
	 * log 日志主键
	 * 返回Log日志实体
	 */
	public Log selecOneLog(Integer logid) {
		String sql = "SELECT * FROM LOG WHERE log_id = ?";
		Log log=null;
		try {
			log = qr.query(sql, new BeanHandler<>(Log.class),logid);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return log;
	}
	

}
