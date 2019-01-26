package com.aerfa.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.aerfa.dao.SendInfoDao;
import com.aerfa.dao.imp.SendInfoDaoImpl;
import com.aerfa.entity.SendInfo;
import com.google.gson.Gson;

/**
 * 测试类
 * @author Administrator
 *
 */
public class TestSendInfoDaoImpl {
	SendInfoDao si = new SendInfoDaoImpl();
	Gson gson = new Gson();
	// 查询所有
	@Test
	public void selectSendInfos() {
		List<SendInfo> list = si.selectSendInfos();
		for (SendInfo sendInfo : list) {
			System.out.println(gson.toJson(sendInfo));
		}
	}
	// 查询单条
	@Test
	public void selectSendInfo() {
		SendInfo sendInfo = si.selectSendInfo(1);
		System.out.println(gson.toJson(sendInfo));
	}
	// 添加单条
	@Test
	public void addSendInfo() {
		SendInfo s = new SendInfo();
		s.setSend_content("hello java");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = sdf.format(date);
		s.setSend_time(t);
		s.setSender(10);
		s.setMsg_id(1003);
		int row = si.addSendInfo(s);
		System.out.println(row);
		System.out.println(t);
	}
	//修改单条
	@Test
	public void updateSendInfo() {
		SendInfo s = new SendInfo();
		s.setSend_id(1);
		s.setSend_content("hello java update");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String t = sdf.format(date);
		s.setSend_time(t);
		s.setSender(11);
		s.setMsg_id(1013);
		int row = si.updateSendInfo(s);
		System.out.println(row);
		System.out.println(t);
	}
	// 删除
	@Test
	public void deleteSendInfo() {
		int row = si.deleteSendInfo(1);
		System.out.println(row);
	}
}
