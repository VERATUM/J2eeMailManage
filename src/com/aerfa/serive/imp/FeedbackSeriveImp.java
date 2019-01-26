package com.aerfa.serive.imp;

import java.util.List;

import com.aerfa.dao.FeedbackDao;
import com.aerfa.dao.imp.FeedbackDaoImp;
import com.aerfa.entity.Feedback;
import com.aerfa.serive.FeedbackSerive;
import com.aerfa.utils.PageInfo;

public class FeedbackSeriveImp implements FeedbackSerive{

	FeedbackDao feedbackDao = new FeedbackDaoImp();
	
	public int addFeedback(Feedback feedback) {
		return feedbackDao.addFeedback(feedback);
	}

	public int deleteFeedback(Integer feed_id) {
		return feedbackDao.deleteFeedback(feed_id);
	}

	public int updateFeedback(Feedback feedback) {
		return feedbackDao.updateFeedback(feedback);
	}

	public List<Feedback> selectFeedback() {
		return feedbackDao.selectFeedback();
	}

	public Feedback selectOneFeedback(int feed_id) {
		return feedbackDao.selectOneFeedback(feed_id);
	}

	public PageInfo<Feedback> currentFeedback(int current, int length) {
		return feedbackDao.currentFeedback(current, length);
	}

	public int deleteFeedbackEmp_id(Integer emp_id) {
		return feedbackDao.deleteFeedbackEmp_id(emp_id);
	}

	@Override
	public int deleteFeedbackEmp_id(String emp_id) {
		return feedbackDao.deleteFeedbackEmp_id(emp_id);
	}

}
