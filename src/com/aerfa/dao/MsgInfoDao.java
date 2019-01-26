package com.aerfa.dao;

import java.sql.SQLException;
import java.util.List;
import com.aerfa.entity.MsgInfo;

public interface MsgInfoDao {
	
	/**
	 * 保存草稿（包括邮件，群邮件）
	 * msg_issave值（1-草稿  0-邮件）默认为1
	 * @param msgInfo
	 * @throws SQLException 
	 */
	Integer addMsg(MsgInfo msgInfo) throws SQLException;
	
	/**
	 * 修改草稿
	 * @param msgInfo
	 * @throws SQLException 
	 */
	void updateMsg(MsgInfo msgInfo) throws SQLException;
	
	/**
	 * ID查询草稿/邮件
	 * @param id
	 * @return 单条MsgInfo
	 * @throws SQLException 
	 */
	MsgInfo selectById(Integer id) throws SQLException;
	
	/**
	 * 删除草稿
	 * @param id 根据Id删除
	 * @throws SQLException 
	 */
	void deleteMsg(Integer id) throws SQLException;
	/**
	 * 删除多个草稿
	 * @param ids 根据Id删除
	 * @throws SQLException 
	 */
	void deleteMsgBatch(int[] ids) throws SQLException;
	
	/**
	 * 群邮的删除
	 * 当邮件msg_atten属性为1时删除，否则减1
	 * @throws SQLException 
	 */
	void removeQYMsg(Integer mid) throws SQLException;
	
	/**
	 * 设置草稿/邮件 是否删除的标记
	 * msg_isdelete（1代表删除 0代表未删除）默认为0
	 * @param id
	 * @throws SQLException 
	 */
	void setRemoveState(Integer id,Integer isd) throws SQLException;
	
	/**
	 * 设置草稿为邮件标识
	 * msg_issave(修改issa = 0)
	 * @param id
	 * @throws SQLException 
	 */
	void setEmailType(Integer id,Integer state) throws SQLException;
	
	/**
	 * 设置邮件为已读
	 * msg_isread（2代表星标 1代表已读 0代表未读）
	 * @param id
	 * @throws SQLException 
	 */
	void setEmailState(Integer id,Integer state) throws SQLException;
 
	//	查询----------------------------------------------------
	
	/**
	 * 根据邮件ID查询草稿/邮件
	 * @param id
	 * @return MsgInfo
	 * @throws SQLException 
	 */
	MsgInfo findById(Integer id) throws SQLException;
	
	/**
	 * 查询草稿/发件 
	 * @param eid 
	 * @param issa (1代表草稿箱  0代表发件)
	 * @param offset
	 * @param size
	 * @return
	 * @throws SQLException 
	 */
	List<MsgInfo> findByNoteOrMsg(Integer eid,Integer issa) throws SQLException;
	
	/**
	 * 查询草稿/发件 数量
	 * @param eid 
	 * @param issa (1代表草稿箱  0代表发件)
	 * @param offset
	 * @param size
	 * @return
	 * @throws SQLException 
	 */
	Integer countByNoteOrMsg(Integer eid,Integer issa) throws SQLException;
	
	/**
	 * 查询收件箱全部列表
	 * @param email 当前用户email
	 * @param offset
	 * @param size
	 * @return
	 * @throws SQLException 
	 */
	List<MsgInfo> findByMsgAll(String email) throws SQLException;
	/**
	 * 查询收件箱全部数量
	 * @param email  当前用户email
	 * @return
	 * @throws SQLException 
	 */
	Integer countByMsgAll(String email) throws SQLException;

	/**
	 * 查询群邮件列表
	 * @throws SQLException 
	 */
	List<MsgInfo> findByQYMsg(Integer did) throws SQLException;
	/**
	 * 查询群邮件数量
	 * @throws SQLException 
	 */
	Integer countByQYMsg(Integer did) throws SQLException;
	
	
	/**
	 * 查询收件箱未读/已读/星标 列表
	 * 注意：将只查询未删除的邮件
	 * @param email  当前用户email
	 * @param state msg_isread (2代表星标 1代表已读 0代表未读)
	 * @param offset
	 * @param size
	 * @return
	 * @throws SQLException 
	 */
	List<MsgInfo> findByMsgState(String email,Integer state) throws SQLException;
	/**
	 * 查询收件箱未读/已读/星标 数量
	 * 注意：将只查询未删除的邮件
	 * @param email  当前用户email
	 * @param state msg_isread (2代表星标 1代表已读 0代表未读)
	 * @return
	 * @throws SQLException 
	 */
	Integer countByMsgState(String email,Integer state) throws SQLException;

	/**
	 * 查询已删除邮件列表
	 * @param email
	 * @param isde msg_isdelete (1代表删除 0代表未删除)
	 * @param offset
	 * @param size
	 * @return
	 * @throws SQLException 
	 */
	List<MsgInfo> findByMsgDelete(String email,Integer isde) throws SQLException;
	/**
	 * 查询已删除邮件数量 msg_isdelete
	 * @param email
	 * @param offset
	 * @param size
	 * @return
	 * @throws SQLException 
	 */
	Integer countByMsgDelete(String email,Integer isde) throws SQLException;
	//根据员工编号删除邮件
	int deletemore(Integer emp_id);
	//根据员工编号多项删除
	int deletemore(String emp_id);
	//根据邮箱改邮箱
	int updataMail(String emp_email,String a_emp_email);
	
}
