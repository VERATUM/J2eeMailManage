package com.aerfa.dao;

import java.util.List;
import com.aerfa.entity.Attention;

public interface AttentionDao {
	//添加
	public int addAttention(Attention attention);
	//删除
	public int deleteAttention(Integer atten_id);
	//修改
	public int updateAttention(Attention attention);
	//查所有
	public List<Attention> selectAttention();
	//查单条
	public Attention selectOneAttention(int atten_id);
	/**
	 * 通过员工编号，所有该员工关注的联系人
	 * @param emp_id 员工编号
	 * @return 返回关注的集合
	 */
	public List<Attention> selectAttentionByEmp_id(Integer emp_id);
	//根据关心员工编号删除
	public int deleteAttentionEmp_id_a(Integer emp_id_a);
	//根据被关心员工编号删除
	public int deleteAttentionEmp_id_b(Integer emp_id_b);
	//多项关心员工编号删除
	public int deleteAttentionEmp_id_a(String emp_id_a);
	//多项被关心员工编号删除
	public int deleteAttentionEmp_id_b(String emp_id_b);
	//修改信息管理a
	public int updateAttentionEmp_id_a(Integer emp_id_a,Integer emp_id);
	//修改信息管理b
	public int updateAttentionEmp_id_b(Integer emp_id_b,Integer emp_id);
	/**
	 * 删除特定员工的关注员工
	 * @return 返回影响行数
	 */
	public int deleteEmp_id_aAttentionEmp_id_b(Attention attention);
	//关心人新表
	public List<Attention> findAttLis(Integer emp_id_a);

}
