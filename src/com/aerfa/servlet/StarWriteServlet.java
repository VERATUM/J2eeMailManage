package com.aerfa.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aerfa.entity.EmpInfo;
import com.aerfa.serive.EmpInfoSerive;
import com.aerfa.serive.imp.EmpInfoSeriveImp;

@WebServlet("/StarWriteServlet")
public class StarWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpInfoSerive empInfoSerive = new EmpInfoSeriveImp();
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String eid = req.getParameter("values");
		Integer e_id = Integer.valueOf(eid);
		EmpInfo ei = empInfoSerive.selectOneEmpInfo(e_id);
		req.getSession().setAttribute("aae", ei);
		res.getWriter().println("1");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
