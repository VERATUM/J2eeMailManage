package com.aerfa.test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.aerfa.dao.MsgInfoDao;
import com.aerfa.dao.imp.MsgInfoDaoImpl;
import com.aerfa.entity.MsgInfo;

public class TestMsginfo {
	
	private static final MsgInfoDao MsgInfoDaoImpl = null;
	private MsgInfoDao dao = new MsgInfoDaoImpl();

	/**
	 * 测试添加
	 * @throws SQLException
	 */
	@Test
	public void addMsg() throws SQLException {
		MsgInfo msgInfo = new MsgInfo();
		
		msgInfo.setEmp_email("aaa");
		msgInfo.setEmp_id(1);
		msgInfo.setMsg_content("测试邮件");
		msgInfo.setMsg_id(null);
		msgInfo.setMsg_isdelete(MsgInfo.NO_DELETE);
		msgInfo.setMsg_isread(MsgInfo.NO_DELETE);
		msgInfo.setMsg_issave(MsgInfo.NOTE);
		msgInfo.setMsg_sendtime("2017-10-10");
		msgInfo.setMsg_subject("生日快乐");
		
		Integer id = dao.addMsg(msgInfo);
		System.out.println("id = "+id);
	}
	
}
