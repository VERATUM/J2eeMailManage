package com.aerfa.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aerfa.entity.DeptInfo;
import com.aerfa.entity.EmpInfo;
import com.aerfa.serive.AttentionSerive;
import com.aerfa.serive.DeptInfoService;
import com.aerfa.serive.EmpInfoSerive;
import com.aerfa.serive.FeedbackSerive;
import com.aerfa.serive.MsgInfoService;
import com.aerfa.serive.imp.AttentionSeriveImp;
import com.aerfa.serive.imp.DeptInfoServiceImpl;
import com.aerfa.serive.imp.EmpInfoSeriveImp;
import com.aerfa.serive.imp.FeedbackSeriveImp;
import com.aerfa.serive.imp.MsgInfoServiceImpl;

@WebServlet("/ManagerOperation")
public class ManagerOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EmpInfoSerive empInfoSerive = new EmpInfoSeriveImp();
	MsgInfoService msgInfoService = new MsgInfoServiceImpl();
	AttentionSerive attentionSerive =new AttentionSeriveImp();
	FeedbackSerive feedbackSerive = new FeedbackSeriveImp();
	DeptInfoService deptInfoService = new DeptInfoServiceImpl();
  
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		
		String action = req.getParameter("action");
		switch(action) {
			case "del":
				String empid = req.getParameter("emp_id");
				Integer emp_id =Integer.valueOf(empid);
				int imp_a = msgInfoService.deletemore(emp_id);
				int imp_b = attentionSerive.deleteAttentionEmp_id_a(emp_id);
				int imp_c = attentionSerive.deleteAttentionEmp_id_b(emp_id);
				int imp_d = feedbackSerive.deleteFeedbackEmp_id(emp_id);
				int imp_e = empInfoSerive.deleteEmpInfo(emp_id);
				if(imp_a>=0&&imp_b>=0&&imp_c>=0&&imp_d>=0&&imp_e>=0) {
					res.getWriter().println("1");
				}else {
					res.getWriter().println("0");
				}
				break;
			case "update":
				EmpInfo empInfo = new EmpInfo();
 				String tel = req.getParameter("tel");
				String email = req.getParameter("email");
				String states = req.getParameter("state");
				String name = req.getParameter("name");
				String a_email = req.getParameter("a_email");
				String a_empid = req.getParameter("a_empid");
				Integer a_emp_id = Integer.valueOf(a_empid);
				Integer depts_id = Integer.valueOf(states);
				empInfo.setEmp_email(a_email);
				empInfo.setEmp_email(email);
				empInfo.setEmp_name(name);
				empInfo.setEmp_tel(tel);
				empInfo.setEmp_id(a_emp_id);
				empInfo.setDept_id(depts_id);
				msgInfoService.updataMail(email, a_email);
				empInfoSerive.updateEmpInfo(empInfo);
				break;
			case "delmore":
				String all = req.getParameter("all");
				all = all.substring(0, all.length()-1);
				int tmp_a = msgInfoService.deletemore(all);
				int tmp_b = attentionSerive.deleteAttentionEmp_id_a(all);
				int tmp_c = attentionSerive.deleteAttentionEmp_id_b(all);
				int tmp_d = feedbackSerive.deleteFeedbackEmp_id(all);
				int tmp_e = empInfoSerive.deleteEmpInfo(all);
				if(tmp_a>=0&&tmp_b>=0&&tmp_c>=0&&tmp_d>=0&&tmp_e>=0) {
					res.getWriter().println("1");
				}else {
					res.getWriter().println("0");
				}
				break;
			case "insert":
				String newname = req.getParameter("newname");
				String newemail = req.getParameter("newemail");
				String newstate = req.getParameter("newstate");
				Integer new_state = Integer.valueOf(newstate);
				String newtel = req.getParameter("newtel");
				String password = "000";
				EmpInfo empadd = new EmpInfo();
				empadd.setEmp_name(newname);
				empadd.setEmp_email(newemail);
				empadd.setDept_id(new_state);
				empadd.setEmp_tel(newtel);
				empadd.setEmp_password(password);
				int temp = empInfoSerive.addEmpInfo(empadd);
				if(temp>0) {
					res.sendRedirect("ManagerFirst.jsp");
				}
				break;
			case "updata":
				String state = req.getParameter("state");
				Integer dept_id = Integer.valueOf(state);
				DeptInfo deptInfo = deptInfoService.selectDeptInfo(dept_id);
				res.getWriter().println(deptInfo.getDept_name());
				break;
			case "newmail":
				String newmail = req.getParameter("newmail");
				EmpInfo empInfos = empInfoSerive.selectOneEmpInfo(newmail);
				System.out.println(empInfos);
				if(empInfos==null) {
					res.getWriter().print("1");
				}else {
					res.getWriter().print("0");
				}
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
