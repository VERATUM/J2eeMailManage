package com.aerfa.serive;

import java.sql.SQLException;
import java.util.List;

import com.aerfa.entity.MsgInfo;

/**
 * @author micat
 */
public interface MsgInfoService {
	
	/**
	 * 保存草稿
	 * @param msgInfo
	 */
	Integer saveNote(MsgInfo msgInfo);
	
	/**
	 * 修改草稿
	 * @param msgInfo
	 */
	void editNote(MsgInfo msgInfo);
	
	/**
	 * 删除草稿
	 * @param id
	 */
	void removeNote(Integer id);
	/**
	 * 删除多个草稿
	 * @param ids
	 */
	void removeNoteBatch(int[] ids);
	
	/**
	 * 发送邮件
	 * @param msgInfo
	 * @return 
	 */
	Integer sendMsg(MsgInfo msgInfo);
	
	/**
	 * 彻底删除邮件
	 * @param id
	 */
	
	void deleteMsg(Integer id);
	/**
	 * 彻底删除多个邮件
	 * @param ids
	 */
	void deleteMsgBatch(int[] ids);
	
	/**
	 * 删除邮件（标识）
	 * @param id
	 */
	void removeMsg(Integer id);
	/**
	 * 删除邮件（标识）
	 * @param ids
	 */
	void removeMsgBatch(int[] ids);
	
	/**
	 * 恢复删除邮件
	 * @param id
	 */
	void recoverMsg(Integer id);
	
	/**
	 * 恢复多个删除邮件
	 * @param ids
	 */
	void recoverMsgBatch(int[] ids);
	
	/**
	 * 设置邮箱为已读
	 * @param id
	 */
	void readMsg(Integer id);
	
	/**
	 * 设置星标邮件
	 * @param id
	 */
	void starMsg(Integer id);
	
	/**
	 * 取消星标邮件
	 * @param id
	 */
	void unStarMsg(Integer id);
	
//	查询----------------------------------------------------
	
	/**
	 * 根据邮件ID查询草稿/邮件
	 * @param id
	 * @return MsgInfo
	 */
	MsgInfo findById(Integer id);
	
	
	/**
	 * 查询草稿列表，(当前用户emp_id)
	 * @param eid
	 * @return
	 * @throws SQLException 
	 */
	List<MsgInfo> findByEidNote(Integer eid);
	/**
	 * 查询草稿数量，(当前用户emp_id)
	 * @param eid
	 * @return
	 */
	Integer countByEidNote(Integer eid);
	
	/**
	 * 查询已发送邮件列表，(当前用户emp_id)
	 * @param eid
	 * @return 
	 */
	List<MsgInfo> findByEidSendMsg(Integer eid);
	/**
	 * 查询已发送邮件数量，(当前用户emp_id)
	 * @param eid
	 * @return
	 */
	Integer countByEidSendMsg(Integer eid);
	
	/**
	 * 查询所有邮件列表，(当前用户emp_email)
	 * @param emp_email
	 * @return
	 */
	List<MsgInfo> findByEidAllMsg(String email);
	/**
	 * 查询所有邮件数量，(当前用户emp_email)
	 * @param emp_email
	 * @return
	 */
	Integer countByEidAllMsg(String email);
	
	/**
	 * 查询群邮件列表
	 * @param did
	 * @return
	 */
	List<MsgInfo> findByQYMsg(Integer did);
	/**
	 * 查询群邮件个数
	 * @param did
	 * @return
	 */
	Integer countByQYMsg(Integer did);
	
	/**
	 * 查询未读邮件列表，(当前用户emp_email)
	 * 注意：只查询未删除的邮件
	 * @param emp_email
	 * @return
	 */
	List<MsgInfo> findByEidNewMsg(String email);
	/**
	 * 查询未读邮件数量，(当前用户emp_email)
	 * 注意：只查询未删除的邮件
	 * @param emp_email
	 * @return
	 */
	Integer countByEidNewMsg(String email);
	
	/**
	 * 查询已读邮件列表，(当前用户emp_email)
	 * 注意：只查询未删除的邮件
	 * @param emp_email
	 * @return
	 */
	List<MsgInfo> findByEidOldMsg(String email);
	/**
	 * 查询已读邮件数量，(当前用户emp_email)
	 * 注意：只查询未删除的邮件
	 * @param emp_email
	 * @return
	 */
	Integer countByEidOldMsg(String email);
	
	/**
	 * 查询废弃邮件列表，(当前用户emp_email)
	 * @param emp_email
	 * @return
	 */
	List<MsgInfo> findByEidRubbishMsg(String email);
	/**
	 * 查询废弃邮件数量，(当前用户emp_email)
	 * @param emp_email
	 * @return
	 */
	Integer countByEidRubbishMsg(String email);
	
	/**
	 * 查询星标邮件列表，(当前用户emp_email)
	 * 注意：只查询未删除的邮件
	 * @param emp_email
	 * @return
	 */
	List<MsgInfo> findByEidStarMsg(String email);
	/**
	 * 查询星标邮件数量，(当前用户emp_email)
	 * 注意：只查询未删除的邮件
	 * @param emp_email
	 * @return
	 */
	Integer countByEidStarMsg(String email);
	/**
	 * 根据员工编号删除邮件
	 * @param emp_id 员工编号
	 * @return 影响行数
	 */
	int deletemore(Integer emp_id);
	/**
	 * 根据员工编号多项删除
	 * @param emp_id 员工编号
	 * @return 返回影响行数
	 */
	int deletemore(String emp_id);
	/**
	 * 根据邮箱改邮箱
	 * @param emp_email 修改后的email
	 * @param a_emp_email 修改前的email
	 * @return 影响行数
	 */
	int updataMail(String emp_email,String a_emp_email);
}
