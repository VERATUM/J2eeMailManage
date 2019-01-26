package com.aerfa.serive.imp;

import java.util.List;

import com.aerfa.dao.SendInfoDao;
import com.aerfa.dao.imp.SendInfoDaoImpl;
import com.aerfa.entity.SendInfo;
import com.aerfa.serive.SendInfoService;

public class SendInfoServiceImpl implements SendInfoService{
	SendInfoDao sendInfoDao = new SendInfoDaoImpl();
	@Override
	public int addSendInfo(SendInfo sendInfo) {
		return sendInfoDao.addSendInfo(sendInfo);
	}

	@Override
	public int deleteSendInfo(Integer send_id) {
		return sendInfoDao.deleteSendInfo(send_id);
	}

	@Override
	public int updateSendInfo(SendInfo sendInfo) {
		return sendInfoDao.updateSendInfo(sendInfo);
	}

	@Override
	public List<SendInfo> selectSendInfos() {
		return sendInfoDao.selectSendInfos();
	}

	@Override
	public SendInfo selectSendInfo(Integer dept_id) {
		return sendInfoDao.selectSendInfo(dept_id);
	}

}
