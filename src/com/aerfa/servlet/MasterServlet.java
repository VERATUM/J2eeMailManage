package com.aerfa.servlet;

import com.aerfa.entity.Attention;
import com.aerfa.entity.DeptInfo;
import com.aerfa.entity.EmpInfo;
import com.aerfa.serive.AttentionSerive;
import com.aerfa.serive.DeptInfoService;
import com.aerfa.serive.EmpInfoSerive;
import com.aerfa.serive.imp.AttentionSeriveImp;
import com.aerfa.serive.imp.DeptInfoServiceImpl;
import com.aerfa.serive.imp.EmpInfoSeriveImp;
import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class MasterServlet
 */
@WebServlet("/MasterServlet")
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // 处理请求进行分类
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 处理乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		// 2. 获取请求的类型
		String action = request.getParameter("action");
		switch (action) {
			case "default_query":
				// 加载事件--部门加载
				this.default_query(request, response);
				break;
			case "emp_query":
				// 通过员工id查询员工
				this.emp_query(request, response);
				break;
			case "add_contact":
				// 添加单个联系人
				this.add_contact(request, response);
				break;
			case "delete_contact":
				// 删除单个联系人
				this.delete_contact(request, response);
				break;
			case "multi_contact":
				// 模糊查询联系人
				this.multi_contact(request, response);
				break;
			case "updat_emp":
				//修改个人信息
				this.updat_emp(request, response);
				break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	// 页面初始化加载信息user,depts,curlist,m_infos
	protected void default_query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.输出对象out
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		// 2.获取当前登陆用户
		EmpInfo user = (EmpInfo) request.getSession().getAttribute("EmpInfo");
		// 3.判断
		if(user==null){
			String m_infos = "网络波动...";
			out.print(gson.toJson(m_infos));
		}else {
			// 3.1查询所有部门以及部门之下的员工
			String m_infos = "";
			DeptInfoService deptInfoService = new DeptInfoServiceImpl();
			List<DeptInfo> deptEmpLists = deptInfoService.selectDeptInfosAndEmpinfos();
			// 3.1.a 通过用户的权限，筛选出相应的部门集合，权限不为0的,没有管理员显示
			if (user.getEmp_state()!=0) {
				for (int i = 0; i < deptEmpLists.size(); i++) {
					DeptInfo deptInfo = deptEmpLists.get(i);
					// 管理员部门1剔除
					if (deptInfo.getDept_id()==1) {
						deptEmpLists.remove(i);
						break;
					}
				}
			}
			// 3.2.通过用户id查询当前用户常用联系人
			AttentionSerive attentionSerive = new AttentionSeriveImp();
			List<Attention> eidlist = attentionSerive.selectAttentionByEmp_id(user.getEmp_id());
			// 3.3获取常用联系人的实体集合
			EmpInfoSerive empInfoSerive = new EmpInfoSeriveImp();
			List<EmpInfo> curlist = new ArrayList<EmpInfo>();
			for (Attention attention : eidlist) {
				Integer emp_id_b = attention.getEmp_id_b();
				EmpInfo empInfo = empInfoSerive.selectOneEmpInfo(emp_id_b);
				if (empInfo!=null&&empInfo.getEmp_state()==1) {
					curlist.add(empInfo);
				}
			}
			for (Attention attention : eidlist) {
				Integer emp_id_b = attention.getEmp_id_b();
				EmpInfo empInfo = empInfoSerive.selectOneEmpInfo(emp_id_b);
				if (empInfo!=null&&empInfo.getEmp_state()==2) {
					curlist.add(empInfo);
				}
			}
			// 4. 返回json格式数据
			List list = new ArrayList();
			list.add(m_infos);
			list.add(user);
			list.add(deptEmpLists);
			list.add(curlist);
			String json = gson.toJson(list);
			out.print(json);
		}
	}
	// 查询员工单条
	protected void emp_query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String eid = request.getParameter("eid");
		EmpInfoSerive eis = new EmpInfoSeriveImp();
		EmpInfo ei = eis.selectOneEmpInfo(Integer.valueOf(eid));
		// 查询员工部门名称
		HttpSession session = request.getSession();
		session.setAttribute("aae", ei);
		DeptInfoService dis = new DeptInfoServiceImpl();
		DeptInfo deptInfo = dis.selectDeptInfo(ei.getDept_id());
		List list = new ArrayList();
		list.add(ei);
		list.add(deptInfo.getDept_name());
		// 返回单个员工以及员工的部门
		out.print(new Gson().toJson(list));
	}
	// 添加联系人
	protected void add_contact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		AttentionSerive attentionSerive = new AttentionSeriveImp();
		// 要添加的员工编号
		String deids = request.getParameter("deids");
		String deids_notices = request.getParameter("deids_notices");
		String[] strs = deids.split(",");
		String[] strs_notice = deids_notices.split(",");
		// 当前登录的用户
		String user_id = request.getParameter("user_id");
		// 查询用户已有的联系人
		List<Attention> user_atts = attentionSerive.selectAttentionByEmp_id(Integer.valueOf(user_id));
		Attention attens = null;
		int row = 0;
		String add_infos1 ="";
		String add_infos2 ="";
		String add_infos3 ="";
		for (int i = 0; i < strs.length; i++) {
			String s = strs[i];
			String s1 = strs_notice[i];
			int adds = Integer.valueOf(s);
			// 不能添加自己
			if (s.equals(user_id)) {
				add_infos1 = "添加用户中有登录本人，已过滤";
			}else {
				int count=0;
				// 不能添加已有的关注人
				for (Attention user_a : user_atts) {
					if(adds == user_a.getEmp_id_b()) {
						count = -1;
						break;
					}else {
						count++;
					}
				}
				//  添加
				if(count == user_atts.size()) {
					add_infos3 += s1+",";
					attens = new Attention();
					attens.setEmp_id_a(Integer.valueOf(user_id));
					attens.setEmp_id_b(adds);
					int a = attentionSerive.addAttention(attens);
					row += a;
				}
				if(count == -1) {
					add_infos2 = "添加用户含有已有的联系人，已过滤";
				}
			}
		}
		if(add_infos3.length()!=0) {
			add_infos3 = add_infos3.substring(0,add_infos3.length()-1);
		}
		List list = new ArrayList();
		list.add(add_infos1);
		list.add(add_infos2);
		list.add(add_infos3);
		list.add(row);
		out.print(new Gson().toJson(list));
	}
	// 删除联系人
	protected void delete_contact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		AttentionSerive attentionSerive1 = new AttentionSeriveImp();
		// 要添加的员工编号
		String deids = request.getParameter("deids");
		String[] strs = deids.split(",");
		String deids_notices = request.getParameter("deids_notices");
		String[] strs_notices = deids_notices.split(",");
		// 当前登录的用户
		String user_id = request.getParameter("user_id");
		Attention attens1 = null;
		int row = 0;
		String del_infos1 ="";
		for (int i = 0; i < strs.length; i++) {
			String s = strs[i];
			String s1 = strs_notices[i];
			attens1 = new Attention();
			attens1.setEmp_id_a(Integer.valueOf(user_id));
			attens1.setEmp_id_b(Integer.valueOf(s));
			int att_i = attentionSerive1.deleteEmp_id_aAttentionEmp_id_b(attens1);
			if(att_i>0) {
				del_infos1 += s1+",";
			}
			row += att_i;
		}
		if(del_infos1.length()!=0) {
			del_infos1 = del_infos1.substring(0,del_infos1.length()-1);
		}
		List list = new ArrayList();
		list.add(del_infos1);
		list.add(row);
		out.print(new Gson().toJson(list));
	}
	// 模糊查询联系人
	protected void multi_contact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// 获取查询的姓名
		String ename = request.getParameter("ename");
		// 输入只有空格的提示消息
		String notice = "";
		List<EmpInfo> listemp = null;
		// 去掉输入的所有空格
		String ename_t = ename.replace(" ", "");
		if (ename_t.length()==0) {
			notice = "请输入有效的员工姓名";
		}else {
			// 建立服务
			EmpInfoSerive eis = new EmpInfoSeriveImp();
			// 输入为空时，不查询；只查询有用的信息
			listemp = eis.selectEmps("%"+ename_t+"%");
		}
		List list = new ArrayList<>();
		list.add(notice);
		list.add(listemp);
		out.print(new Gson().toJson(list));
	}
	protected void updat_emp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		EmpInfo emp = new EmpInfo();
		EmpInfoSerive empInfoSerive = new EmpInfoSeriveImp();
		String adderss = request.getParameter("adderss");
		String birthday = request.getParameter("birthday");
		int id = Integer.parseInt(request.getParameter("id")); 
		String regex = "^[\\u4e00-\\u9fa5]{0,10}";
		if(adderss.matches(regex)) {
			emp.setEmp_id(id);
	     	emp.setEmp_address(adderss);
		    emp.setEmp_birthday(birthday);
		    int pos = empInfoSerive.updateOneEmpInfo(emp);
		    if (pos>0) {
		    	out.println(1);
			}else {
				out.println(2);	
			}
		    
		}else {
			out.println(3);
		}
	}
}
