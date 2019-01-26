package com.aerfa.dao.imp;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.aerfa.dao.FeedbackDao;
import com.aerfa.entity.Feedback;
import com.aerfa.utils.JDBCUntils;
import com.aerfa.utils.PageInfo;

public class FeedbackDaoImp implements FeedbackDao{

	QueryRunner qr = new QueryRunner(JDBCUntils.getDataSource());
	
	public int addFeedback(Feedback feedback) {
		int temp = 0 ;
		try {
			String sql = "INSERT INTO feedback (feed_title,feed_time,feed_content,emp_id)VALUES(?,NOW(),?,?)";
			temp = qr.update(sql,new Object[]{feedback.getFeed_title(),feedback.getFeed_content(),feedback.getEmp_id()});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public int deleteFeedback(Integer feed_id) {
		int temp = 0 ;
		try {
			String sql = "DELETE FROM feedback WHERE feed_id = ?";
			temp = qr.update(sql,feed_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public int updateFeedback(Feedback feedback) {
		int temp = 0 ;
		try {
			String sql = "UPDATE feedback SET feed_title=?,feed_time=NOW(),feed_content=? WHERE feed_id = ?";
			temp = qr.update(sql,new Object[] {feedback.getFeed_title(),feedback.getFeed_content(),feedback.getFeed_id()});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public List<Feedback> selectFeedback() {
		List<Feedback> list = null;
		try {
			String sql = "SELECT * FROM feedback LEFT JOIN empinfo ON(feedback.`emp_id`=empinfo.`emp_id`)";
			list = qr.query(sql, new BeanListHandler<>(Feedback.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Feedback selectOneFeedback(int feed_id) {
		Feedback empInfo= null;
		try {
			String sql = "SELECT * FROM feedback WHERE feed_id = ?";
			empInfo = qr.query(sql,new BeanHandler<>(Feedback.class),feed_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(empInfo);
		return empInfo;
	}

	public PageInfo<Feedback> currentFeedback(int current,int length) {		
		List<Feedback> feed = null;
		PageInfo<Feedback> pageInfo = null;		
		try {
			String sql = "SELECT feedback.*,empinfo.emp_name FROM feedback,empinfo WHERE feedback.emp_id=empinfo.emp_id ORDER BY feedback.feed_id DESC LIMIT ?,?";
			String sql1 = "SELECT COUNT(1) FROM feedback";
			pageInfo = new PageInfo<Feedback>();
			feed = qr.query(sql, new BeanListHandler<>(Feedback.class), (current-1)*length,length);
			long count = qr.query(sql1, new ScalarHandler<>());
			pageInfo.setCount((int)count);
			pageInfo.setResults(feed);
			pageInfo.setLength(length);
			pageInfo.setCurrent(current);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return pageInfo;
	}

	public int deleteFeedbackEmp_id(Integer emp_id) {
		int temp = -1 ;
		try {
			String sql = "DELETE FROM feedback WHERE emp_id = ?";
			temp = qr.update(sql,emp_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public int deleteFeedbackEmp_id(String emp_id) {
		int temp = -1 ;
		try {
			String sql = "DELETE FROM feedback WHERE emp_id IN ("+emp_id+")";
			temp = qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	/*@Test
	public void getPageInfo() {		
		PageInfo<Feedback> p =this.currentFeedback(2, 5);
		List<Feedback> list = p.getResults();
		System.out.println("总页数"+p.getTotal());
		System.out.println("页大小"+p.getLength());
		System.out.println("总记录数"+p.getCount());
		System.out.println("当前页数"+p.getCurrent());
		
		for (Feedback f : list) {
			System.out.println(f.getEmp_name());
		}
	}
	
	@Test
	public void testAdd() {
		Feedback feedback =new Feedback();
		feedback.setFeed_content("开发部的人什么时候可以休假啊");
		feedback.setFeed_title("月假");
		feedback.setEmp_id(3);
		System.out.println(this.addFeedback(feedback));
	}
	
	@Test
	public void testDel() {
		Integer feed_id=3;
		System.out.println(this.deleteFeedback(feed_id));
	}
	
	@Test
	public void testSeletOne() {
		Integer feed_id=3;
		System.out.println(this.selectOneFeedback(feed_id));
	}
	
	@Test
	public void testSelet() {
		System.out.println(this.selectFeedback());
	}
	
	@Test
	public void testUpdate() {
		Feedback feedback =new Feedback();
		feedback.setFeed_title("调休");
		feedback.setFeed_content("想要两个月的假一起放,去马尔代夫浪一波!");
		feedback.setFeed_id(3);
		System.out.println(this.updateFeedback(feedback));
	}
	@Test
	public void testSelet() {
		List<Feedback> lis= this.selectFeedback();
		System.out.println(lis.get(1).getEmp_name());
	}
	*/
	
}





