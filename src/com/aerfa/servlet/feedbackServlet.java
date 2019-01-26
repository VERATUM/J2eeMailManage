package com.aerfa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aerfa.entity.DeptInfo;
import com.aerfa.entity.EmpInfo;
import com.aerfa.entity.Feedback;
import com.aerfa.serive.DeptInfoService;
import com.aerfa.serive.EmpInfoSerive;
import com.aerfa.serive.FeedbackSerive;
import com.aerfa.serive.imp.DeptInfoServiceImpl;
import com.aerfa.serive.imp.EmpInfoSeriveImp;
import com.aerfa.serive.imp.FeedbackSeriveImp;
import com.aerfa.utils.PageInfo;

/**
 * Servlet implementation class feedbackServlet
 */
@WebServlet("/feedbackServlet")
public class feedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    FeedbackSerive fs = new FeedbackSeriveImp();
    EmpInfoSerive es = new EmpInfoSeriveImp();
    DeptInfoService ds = new DeptInfoServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	List<Feedback> feed = fs.selectFeedback();
	 	//取出员工编号
	 	int empid = Integer.parseInt(request.getParameter("empid"));
	 	//查询该员工部门
	 	String deptname =request.getParameter("deptname");
	 	//通过编号取出员工实体
	 	EmpInfo emp = es.selectOneEmpInfo(empid);
	 	//查询该员工部门
	 	//DeptInfo dept = ds.selectDeptInfo(emp.getDept_id());
	 	//String deptname = dept.getDept_name();
	 	//当前页数据
	 	int current =Integer.parseInt(request.getParameter("current")==null?"1":request.getParameter("current"));
	 	PageInfo<Feedback> p = fs.currentFeedback(current,6);
	 	request.setAttribute("emp", emp);
	 	request.setAttribute("feed", feed);
	 	request.setAttribute("deptname", deptname);
	 	//分页数据
	 	request.setAttribute("page", p);
	 	request.getRequestDispatcher("feedback.jsp").forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
