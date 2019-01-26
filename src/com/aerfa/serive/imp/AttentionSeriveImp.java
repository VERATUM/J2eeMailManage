package com.aerfa.serive.imp;

import java.util.List;
import com.aerfa.dao.AttentionDao;
import com.aerfa.dao.imp.AttentionDaoImp;
import com.aerfa.entity.Attention;
import com.aerfa.serive.AttentionSerive;

public class AttentionSeriveImp implements AttentionSerive{
	
	AttentionDao attentionDao = new AttentionDaoImp();

	public int addAttention(Attention attention) {
		return attentionDao.addAttention(attention);
	}

	public int deleteAttention(Integer atten_id) {
		return attentionDao.deleteAttention(atten_id);
	}

	public int updateAttention(Attention attention) {
		return attentionDao.updateAttention(attention);
	}

	public List<Attention> selectAttention() {
		return attentionDao.selectAttention();
	}

	public Attention selectOneAttention(int atten_id) {
		return attentionDao.selectOneAttention(atten_id);
	}

	public List<Attention> selectAttentionByEmp_id(Integer emp_id) {
		return attentionDao.selectAttentionByEmp_id(emp_id);
	}

	public int deleteAttentionEmp_id_a(Integer emp_id_a) {
		return attentionDao.deleteAttentionEmp_id_a(emp_id_a);
	}

	public int deleteAttentionEmp_id_b(Integer emp_id_b) {
		return attentionDao.deleteAttentionEmp_id_b(emp_id_b);
	}

	public int deleteAttentionEmp_id_a(String emp_id_a) {
		return attentionDao.deleteAttentionEmp_id_a(emp_id_a);
	}

	public int deleteAttentionEmp_id_b(String emp_id_b) {
		return attentionDao.deleteAttentionEmp_id_b(emp_id_b);
	}

	public int updateAttentionEmp_id_a(Integer emp_id_a, Integer emp_id) {
		return attentionDao.updateAttentionEmp_id_a(emp_id_a, emp_id);
	}

	public int updateAttentionEmp_id_b(Integer emp_id_b, Integer emp_id) {
		return attentionDao.updateAttentionEmp_id_b(emp_id_b, emp_id);
	}

	public int deleteEmp_id_aAttentionEmp_id_b(Attention attention) {
		return attentionDao.deleteEmp_id_aAttentionEmp_id_b(attention);
	}

	public List<Attention> findAttLis(Integer emp_id_a) {
		return attentionDao.findAttLis(emp_id_a);
	}
}
