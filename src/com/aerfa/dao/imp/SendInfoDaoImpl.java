package com.aerfa.dao.imp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.aerfa.dao.SendInfoDao;
import com.aerfa.entity.SendInfo;
import com.aerfa.utils.JDBCUntils;
/**
 * 邮件回复接口的实现类
 * @author Ver
 *
 */
public class SendInfoDaoImpl implements SendInfoDao{
	QueryRunner qr = new QueryRunner(JDBCUntils.getDataSource());
	@Override
	public int addSendInfo(SendInfo s) {
		String sql = "INSERT INTO sendInfo(send_content,send_time,sender,msg_id) VALUES(?,?,?,?)";
		int row = 0;
		try {
			row = qr.update(sql, s.getSend_content(),s.getSend_time(),s.getSender(),s.getMsg_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int deleteSendInfo(Integer send_id) {
		String sql = "DELETE FROM sendInfo where send_id = ?";
		int row = 0;
		try {
			row = qr.update(sql, send_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public int updateSendInfo(SendInfo s) {
		String sql = "UPDATE sendInfo SET send_content=?,send_time=?,sender=?,msg_id=? WHERE send_id = ?";
		int row = 0;
		try {
			row = qr.update(sql,s.getSend_content(),s.getSend_time(),s.getSender(),s.getMsg_id(),s.getSend_id());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public List<SendInfo> selectSendInfos() {
		String sql = "SELECT * FROM sendInfo";
		List<SendInfo> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<SendInfo>(SendInfo.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public SendInfo selectSendInfo(Integer send_id) {
		String sql = "SELECT * FROM sendInfo where send_id = ?";
		SendInfo sendInfo = null; 
		try {
			sendInfo = qr.query(sql, new BeanHandler<>(SendInfo.class),send_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sendInfo;
	}
}
