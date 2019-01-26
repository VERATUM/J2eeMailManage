package com.aerfa.serive;

import java.util.List;

import com.aerfa.entity.Feedback;
import com.aerfa.utils.PageInfo;

public interface FeedbackSerive {
		/**
		 * 反馈意见添加
		 * @param feedback 传一个反馈意见的实体类
		 * @return 返回影响行数
		 */
		public int addFeedback(Feedback feedback);
		/**
		 * 反馈意见删除
		 * @param feed_id 传一个反馈实体的编号
		 * @return 返回影响行数
		 */
		public int deleteFeedback(Integer feed_id);
		/**
		 * 反馈意见修改
		 * @param feedback 传一个反馈意见的实体类
		 * @return 返回影响行数
		 */
		public int updateFeedback(Feedback feedback);
		/**
		 * 反馈意见查询
		 * @return 返回Feedback实体类集合
		 */
		public List<Feedback> selectFeedback();
		/**
		 * 反馈意见查询单条
		 * @param feed_id 传一个反馈意见编号
		 * @return 返回Feedback实体
		 */
		public Feedback selectOneFeedback(int feed_id);
		/**
		 * 反馈意见分页查询
		 * @param current 当页页码 length 页码大小
		 * @return PageInfo<Feedback>当页数据及页码数据
		 */
		public PageInfo<Feedback> currentFeedback(int current,int length);
		/**
		 * 删除员工编号
		 * @param emp_id 传员工编号
		 * @return 影响行数
		 */
		public int deleteFeedbackEmp_id(Integer emp_id);
		/**
		 * 多项删除员工编号
		 * @param emp_id 传员工编号
		 * @return 影响行数
		 */
		public int deleteFeedbackEmp_id(String emp_id);
	
}
