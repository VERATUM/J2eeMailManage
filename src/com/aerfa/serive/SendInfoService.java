package com.aerfa.serive;

import java.util.List;

import com.aerfa.entity.SendInfo;

public interface SendInfoService {
	/**
	 * 增加
	 * @param sendInfo 邮件回复实体
	 * @return 返回影响行数
	 */
	public int addSendInfo(SendInfo sendInfo);
	/**
	 * 删除
	 * @param send_id 邮件回复编号
	 * @return 返回影响行数
	 */
	public int deleteSendInfo(Integer send_id);
	/**
	 * 修改
	 * @param sendInfo 邮件回复实体
	 * @return 返回影响行数
	 */
	public int updateSendInfo(SendInfo sendInfo);
	/**
	 * 查询所有邮件回复
	 * @return 返回邮件回复实体集合
	 */
	public List<SendInfo> selectSendInfos();
	/**
	 * 查询单个部门
	 * @param dept_id 部门编号
	 * @return 返回单个部门实体
	 */
	public SendInfo selectSendInfo(Integer dept_id);
}
