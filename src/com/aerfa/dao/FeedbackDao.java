package com.aerfa.dao;

import java.util.List;
import com.aerfa.entity.Feedback;
import com.aerfa.utils.PageInfo;

public interface FeedbackDao {
		//添加
		public int addFeedback(Feedback feedback);
		//删除
		public int deleteFeedback(Integer feed_id);
		//修改
		public int updateFeedback(Feedback feedback);
		//查所有
		public List<Feedback> selectFeedback();
		//查单条
		public Feedback selectOneFeedback(int feed_id);
		//分页查询
		public PageInfo<Feedback> currentFeedback(int current,int length);
		//删除员工编号
		public int deleteFeedbackEmp_id(Integer emp_id);
		//多项删除员工编号
		public int deleteFeedbackEmp_id(String emp_id);
}
