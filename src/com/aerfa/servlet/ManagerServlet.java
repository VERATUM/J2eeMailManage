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
import com.aerfa.serive.DeptInfoService;
import com.aerfa.serive.EmpInfoSerive;
import com.aerfa.serive.imp.DeptInfoServiceImpl;
import com.aerfa.serive.imp.EmpInfoSeriveImp;

@WebServlet("/ManagerServlet")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpInfoSerive empInfoSerive = new EmpInfoSeriveImp();
	DeptInfoService deptInfos = new DeptInfoServiceImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		Integer act = Integer.valueOf(action);
		if(act==0) {
			List<EmpInfo> listEmpInfo = empInfoSerive.selectDeptEmpInfo();
			List<DeptInfo> deptInfo = deptInfos.selectDeptInfos();
			req.getSession().setAttribute("flag", act);
			req.getSession().setAttribute("listEmpInfo", listEmpInfo);
			req.getSession().setAttribute("deptInfo", deptInfo);
			res.sendRedirect("ManagerFirst.jsp");
		}else if(act>0) {
			List<EmpInfo> listEmpInfo1 = empInfoSerive.selectDeptEmpInfo(act);
			List<DeptInfo> deptInfo = deptInfos.selectDeptInfos();
			req.getSession().setAttribute("flag", act);
			req.getSession().setAttribute("listEmpInfo", listEmpInfo1);
			req.getSession().setAttribute("deptInfo", deptInfo);
			res.sendRedirect("ManagerFirst.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
