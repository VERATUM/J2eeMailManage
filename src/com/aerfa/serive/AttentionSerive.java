package com.aerfa.serive;

import java.util.List;

import com.aerfa.entity.Attention;

public interface AttentionSerive {
		/**
		 * 星标朋友添加
		 * @param attention 传一个星标朋友实体
		 * @return 返回影响行数
		 */
		public int addAttention(Attention attention);
		/**
		 * 星标朋友删除
		 * @param atten_id 传一个星标朋友的编号
		 * @return 返回影响行数
		 */
		public int deleteAttention(Integer atten_id);
		/**
		 * 星标朋友修改
		 * @param attention 传一个星标朋友实体
		 * @return 返回影响行数
		 */
		public int updateAttention(Attention attention);
		/**
		 * 星标朋友查询
		 * @return 返回Attention实体集合
		 */
		public List<Attention> selectAttention();
		/**
		 * 星标朋友单条查询
		 * @param atten_id 传一个星标朋友的编号
		 * @return 返回Attention实体
		 */
		public Attention selectOneAttention(int atten_id);
		/**
		 * 通过员工编号，所有该员工关注的联系人
		 * @param emp_id 员工编号
		 * @return 返回关注的集合
		 */
		public List<Attention> selectAttentionByEmp_id(Integer emp_id);
		/**
		 * 根据关心员工编号删除
		 * @param emp_id_a 关心员工编号
		 * @return 影响行数
		 */
		public int deleteAttentionEmp_id_a(Integer emp_id_a);
		/**
		 * 根据被关心员工编号删除
		 * @param emp_id_b 被关心员工编号
		 * @return 影响行数
		 */
		public int deleteAttentionEmp_id_b(Integer emp_id_b);
		/**
		 * 多项关心员工编号删除
		 * @param emp_id_a  关心员工编号
		 * @return 影响行数
		 */
		public int deleteAttentionEmp_id_a(String emp_id_a);
		/**
		 * 多项被关心员工编号删除
		 * @param emp_id_b 被关心员工编号
		 * @return 影响行数
		 */
		public int deleteAttentionEmp_id_b(String emp_id_b);
		/**
		 * 修改信息管理a
		 * @param emp_id_a 员工修改后的编号
		 * @param emp_id 员工修改前的编号
		 * @return 影响行数
		 */
		public int updateAttentionEmp_id_a(Integer emp_id_a,Integer emp_id);
		/**
		 * 修改信息管理b
		 * @param emp_id_b 员工修改后的编号
		 * @param emp_id 员工修改前的编号
		 * @return 影响行数
		 */
		public int updateAttentionEmp_id_b(Integer emp_id_b,Integer emp_id);
		/**
		 * 删除特定员工的关注员工
		 * @return 返回影响行数
		 */
		public int deleteEmp_id_aAttentionEmp_id_b(Attention attention);
		/**
		 * 关心人新表
		 * @param emp_id_a 本人的id
		 * @return 被关心人的集合包括姓名和id
		 */
		public List<Attention> findAttLis(Integer emp_id_a);
}
