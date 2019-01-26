package com.aerfa.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.aerfa.dao.AttentionDao;
import com.aerfa.entity.Attention;
import com.aerfa.utils.JDBCUntils;

public class AttentionDaoImp implements AttentionDao{
	
	QueryRunner qr = new QueryRunner(JDBCUntils.getDataSource());

	public int addAttention(Attention attention) {
		int temp = 0 ;
		try {
			String sql = "INSERT INTO attention (emp_id_a,emp_id_b)VALUES(?,?);";
			temp = qr.update(sql,new Object[]{attention.getEmp_id_a(),attention.getEmp_id_b()});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public int deleteAttention(Integer atten_id) {
		int temp = 0 ;
		try {
			String sql = "DELETE FROM attention WHERE atten_id = ?";
			temp = qr.update(sql,atten_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public int updateAttention(Attention attention) {
		int temp = 0 ;
		try {
			String sql = "UPDATE attention SET emp_id_a=?,emp_id_b=? WHERE atten_id = ?";
			temp = qr.update(sql,new Object[] {attention.getEmp_id_a(),attention.getEmp_id_b(),attention.getAtten_id()});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public List<Attention> selectAttention() {
		List<Attention> list = null;
		try {
			String sql = "SELECT * FROM attention";
			list = qr.query(sql, new BeanListHandler<>(Attention.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Attention selectOneAttention(int atten_id) {
		Attention empInfo= null;
		try {
			String sql = "SELECT * FROM attention WHERE atten_id = ?";
			empInfo = qr.query(sql,new BeanHandler<>(Attention.class),atten_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(empInfo);
		return empInfo;
	}
	
	public List<Attention> selectAttentionByEmp_id(Integer emp_id) {
		List<Attention> list = null;
		try {
			String sql = "SELECT * FROM attention WHERE emp_id_a = ?";
			list = qr.query(sql, new BeanListHandler<>(Attention.class),emp_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int deleteAttentionEmp_id_a(Integer emp_id_a) {
		int temp = -1 ;
		try {
			String sql = "DELETE FROM attention WHERE emp_id_a = ?";
			temp = qr.update(sql,emp_id_a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public int deleteAttentionEmp_id_b(Integer emp_id_b) {
		int temp = -1 ;
		try {
			String sql = "DELETE FROM attention WHERE emp_id_b = ?";
			temp = qr.update(sql,emp_id_b);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public int deleteAttentionEmp_id_a(String emp_id_a) {
		int temp = -1 ;
		try {
			String sql = "DELETE FROM attention WHERE emp_id_b IN ("+emp_id_a+")";
			temp = qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public int deleteAttentionEmp_id_b(String emp_id_b) {
		int temp = -1 ;
		try {
			String sql = "DELETE FROM attention WHERE emp_id_b IN ("+emp_id_b+")";
			temp = qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int updateAttentionEmp_id_a(Integer emp_id_a,Integer emp_id) {
		int temp = -1 ;
		try {
			String sql = "UPDATE attention SET emp_id_a=? WHERE emp_id_a=?";
			temp = qr.update(sql,emp_id_a,emp_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
		
	@Override
	public int updateAttentionEmp_id_b(Integer emp_id_b,Integer emp_id) {
		int temp = -1 ;
		try {
			String sql = "UPDATE attention SET emp_id_b=? WHERE emp_id_b=?";
			temp = qr.update(sql,emp_id_b,emp_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}
	
	@Override
	public int deleteEmp_id_aAttentionEmp_id_b(Attention a) {
		int temp = 0 ;
		try {
			String sql = "DELETE FROM attention WHERE emp_id_a = ? AND emp_id_b = ?";
			temp = qr.update(sql,a.getEmp_id_a(),a.getEmp_id_b());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public List<Attention> findAttLis(Integer emp_id_a) {
		List<Attention> lists= null;
		try {
			String sql = "SELECT empinfo.`emp_id`,empinfo.`emp_name`,empinfo.`emp_email` FROM attention  LEFT JOIN empinfo ON (attention.`emp_id_b`=empinfo.`emp_id`)WHERE emp_id_a = ?";
			List<Object[]> list = qr.query(sql, new ArrayListHandler(),emp_id_a);
			lists = new ArrayList<Attention>();
			for (Object[] objects : list) {
				Attention attention = new Attention();
				attention.setEmp_id_b((Integer)objects[0]);
				attention.setB_emp_name((String)objects[1]);
				attention.setEmp_email((String)objects[2]);
				lists.add(attention);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}
}
