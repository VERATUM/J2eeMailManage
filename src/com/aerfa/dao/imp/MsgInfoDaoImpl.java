package com.aerfa.dao.imp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.aerfa.dao.MsgInfoDao;
import com.aerfa.entity.MsgInfo;
import com.aerfa.utils.JDBCUntils;

public class MsgInfoDaoImpl implements MsgInfoDao{

	private DataSource dataSource = JDBCUntils.getDataSource();

	@Override
	public Integer addMsg(MsgInfo msgInfo) throws SQLException {
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		
		QueryRunner runner = new QueryRunner();

		String sql1 = "insert into msginfo values(?,?,?,?,?,?,?,?,?,?)";
		String sql2 = "select LAST_INSERT_ID()";
		Object[] params = {null,msgInfo.getMsg_subject(),msgInfo.getMsg_content()
				,msgInfo.getMsg_sendtime(),msgInfo.getEmp_id(),msgInfo.getEmp_email()
				,msgInfo.getMsg_issave(),msgInfo.getMsg_isread()
				,msgInfo.getMsg_isdelete(),msgInfo.getMsg_atten()};
		
		runner.update(conn,sql1, params);
		Long id = runner.query(conn,sql2,new ScalarHandler<>());
		
		conn.commit();
		conn.setAutoCommit(true);
		return id.intValue();
	}
	
	@Override
	public void updateMsg(MsgInfo msgInfo) throws SQLException {
		if(msgInfo==null || msgInfo.getMsg_id()==null)
			return;
		
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "update msginfo set msg_subject=?,msg_content=?,msg_sendtime=?"
				+ ",emp_id=?,emp_email=?,msg_issave=?,msg_isread=?,msg_isdelete=?,msg_atten=?"
				+ " where msg_id=?";
		Object[] params = {msgInfo.getMsg_subject(),msgInfo.getMsg_content()
				,msgInfo.getMsg_sendtime(),msgInfo.getEmp_id(),msgInfo.getEmp_email()
				,msgInfo.getMsg_issave(),msgInfo.getMsg_isread(),msgInfo.getMsg_isdelete()
				,msgInfo.getMsg_atten(),msgInfo.getMsg_id()};
		
		runner.update(sql, params);
	}

	@Override
	public MsgInfo selectById(Integer id) throws SQLException {
		if(id==null)
			return null;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "select *from msginfo where msg_id=?";
		
		MsgInfo msg = runner.query(sql,new BeanHandler<>(MsgInfo.class),id);
		return msg;
	}

	@Override
	public void deleteMsg(Integer id) throws SQLException {
		if(id==null)
			return;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "delete from msginfo where msg_id=?";
		runner.update(sql,id);
	}
	
	@Override
	public void deleteMsgBatch(int[] ids) throws SQLException {
		if(ids==null)
			return;
		
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		
		QueryRunner runner = new QueryRunner();
		String sql = "delete from msginfo where msg_id=?";
		
		Object[][] params = new Object[ids.length][1];
		for(int i=0;i<ids.length;i++) {
			params[i] = new Object[] {ids[i]};
		}
		runner.batch(conn,sql, params);
		conn.commit();
		conn.setAutoCommit(true);
	}

	@Override
	public void setRemoveState(Integer id, Integer isd) throws SQLException {
		if(id==null||isd==null)
			return;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "update msginfo set msg_isdelete=? where msg_id=?";
		
		runner.update(sql,isd,id);
	}

	@Override
	public void setEmailType(Integer id,Integer state) throws SQLException {
		if(id==null)
			return;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "update msginfo set msg_issave=? where msg_id=?";
		
		runner.update(sql,state,id);
	}

	@Override
	public void setEmailState(Integer id,Integer state) throws SQLException {
		if(id==null)
			return;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "update msginfo set msg_isread=? where msg_id=?";
		
		runner.update(sql,state,id);
	}

	@Override
	public MsgInfo findById(Integer id) throws SQLException {
		if(id==null)
			return null;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "select *from msginfo where msg_id=?";
		
		MsgInfo msg = runner.query(sql,new BeanHandler<>(MsgInfo.class),id);
		return msg;
	}

	@Override
	public List<MsgInfo> findByNoteOrMsg(Integer eid, Integer issa) throws SQLException {
		if(eid==null)
			return null;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "select *from msginfo where emp_id=? and msg_issave=?";
		
		List<MsgInfo> list = runner.query(sql,new BeanListHandler<>(MsgInfo.class),eid,issa);
		return list;
	}

	@Override
	public List<MsgInfo> findByMsgAll(String email) throws SQLException {
		if(email==null)
			return null;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "select *from msginfo where emp_email=?";
		
		List<MsgInfo> list = runner.query(sql,new BeanListHandler<>(MsgInfo.class),email);
		return list;
	}

	@Override
	public Integer countByMsgAll(String email) throws SQLException {
		if(email==null)
			return null;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "select count(*) from msginfo where emp_email=?";
		Long count = runner.query(sql,new ScalarHandler<>(),email);
		return count.intValue();
	}

	@Override
	public List<MsgInfo> findByMsgState(String email, Integer state) throws SQLException {
		if(email==null)
			return null;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "select *from msginfo where emp_email=? and msg_isread=?";
		
		List<MsgInfo> list = runner.query(sql,new BeanListHandler<>(MsgInfo.class),email,state);
		return list;
	}

	@Override
	public Integer countByMsgState(String email, Integer state) throws SQLException {
		if(email==null)
			return null;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "select count(*) from msginfo where emp_email=? and msg_isread=?";
		Long count = runner.query(sql,new ScalarHandler<>(),email,state);
		return count.intValue();
	}

	@Override
	public List<MsgInfo> findByMsgDelete(String email,Integer isde) throws SQLException {
		if(email==null)
			return null;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "select *from msginfo where emp_email=? and msg_isdelete=?";
		
		List<MsgInfo> list = runner.query(sql,new BeanListHandler<>(MsgInfo.class),email,isde);
		return list;
	}

	@Override
	public Integer countByMsgDelete(String email,Integer isde) throws SQLException {
		if(email==null)
			return null;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "select count(*) from msginfo where emp_email=? and msg_isdelete=?";
		Long count = runner.query(sql,new ScalarHandler<>(),email,isde);
		return count.intValue();
	}

	@Override
	public Integer countByNoteOrMsg(Integer eid, Integer issa) throws SQLException {
		if(eid==null)
			return null;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "select count(*) from msginfo where emp_id=? and msg_issave=?";
		Long count = runner.query(sql,new ScalarHandler<>(),eid,issa);
		return count.intValue();
	}

	@Override
	public int deletemore(Integer emp_id) {
		QueryRunner qr = new QueryRunner(dataSource);
		int temp = -1 ;
		try {
			String sql = "DELETE FROM msginfo WHERE emp_id=?";
			temp = qr.update(sql,emp_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public int deletemore(String emp_id) { 
		QueryRunner qr = new QueryRunner(JDBCUntils.getDataSource());
		int temp = -1 ;
		try {
			String sql = "DELETE FROM msginfo WHERE emp_id IN ("+emp_id+")";
			temp = qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int updataMail(String emp_email, String a_emp_email) {
		QueryRunner qr = new QueryRunner(JDBCUntils.getDataSource());
		int temp = -1 ;
		try {
			String sql = "UPDATE empinfo SET emp_email=? WHERE emp_email=?";
			temp = qr.update(sql,emp_email,a_emp_email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public void removeQYMsg(Integer mid) throws SQLException {
		MsgInfo msg = findById(mid);
		if(msg.getMsg_atten()>1) {
			QueryRunner runner = new QueryRunner(dataSource);
			String sql = "update msginfo set msg_atten=msg_atten-1  where msg_id=?";
			runner.update(sql,mid);
		}else {
			deleteMsg(mid);
		}
	}
	
	@Override
	public List<MsgInfo> findByQYMsg(Integer did) throws SQLException {
		if(did==null)
			return null;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "select *from msginfo where emp_email=?";
		
		List<MsgInfo> list = runner.query(sql,new BeanListHandler<>(MsgInfo.class),did);
		return list;
	}
	
	@Override
	public Integer countByQYMsg(Integer did) throws SQLException {
		if(did==null)
			return null;
		QueryRunner runner = new QueryRunner(dataSource);
		String sql = "select count(*) from msginfo where emp_email=?";
		Long count = runner.query(sql,new ScalarHandler<>(),did);
		return count.intValue();
	}

}
