package com.aerfa.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;
import com.aerfa.entity.DeptInfo;
import com.aerfa.utils.JDBCUntils;
import com.google.gson.Gson;

@WebServlet("/testData")
public class TestData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建调用方法的对象(类似BaseDao对象)
		QueryRunner qr = new QueryRunner(JDBCUntils.getDataSource());
		String sql = "SELECT * FROM deptinfo";
		List<DeptInfo> query= null ;
		try {
			/**
			 * 调用query方法,进行查询
			 * sql SQL语句
			 * new BeanListHandler<>(DeptInfo.class)
			 * 返回该实体类类型数组,固定格式
			 * DeptInfo.class 所查询的实体类的.class
			 */
			query = qr.query(sql,new BeanListHandler<>(DeptInfo.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(query);
		request.setAttribute("query", query);
		request.getRequestDispatcher("Test.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	@Test
	public void testGson() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(5);
		list.add(0);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(6);
		list.add(6);
		list.add(1);
		list.add(5);
		list.add(9);
		Gson goson = new Gson();
		String gosonStr = goson.toJson(list);
		System.out.println(gosonStr);
	}
	
   /**
    * dbuntils使用方法
    * 1.创建对象(固定写法)
	* QueryRunner qr = new QueryRunner(JDBCUntils.getDataSource());
	*	1.1 增,删,改
	*			int temp = qr.update(String SQL,Object...params);
	*	1.2 查
	*		1.2.1查数据数量
	*			long rs  = qr.query(String sql,newScalarHandler<>());
	*		1.2.2查询所有
	*			List<user> rs = qr.query(String sql,new BeanListHandler<>(user.class));
	*		1.2.3查询单条
	*			user rs = qr.query(String sql,new BeanHandler<>(user.class));
	**/
	
	
	
}
