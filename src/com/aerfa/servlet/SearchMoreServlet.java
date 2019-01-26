package com.aerfa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aerfa.entity.EmpInfo;
import com.aerfa.serive.EmpInfoSerive;
import com.aerfa.serive.imp.EmpInfoSeriveImp;

@WebServlet("/SearchMoreServlet")
public class SearchMoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpInfoSerive empInfoSerive = new EmpInfoSeriveImp();
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String searchtext = req.getParameter("searchtext");
		EmpInfoSerive empInfoSerive = new EmpInfoSeriveImp();
		List<EmpInfo> listEmpInfo =  empInfoSerive.selectEmpsSearch(searchtext);
		req.getSession().setAttribute("listEmpInfo", listEmpInfo);
		res.sendRedirect("ManagerFirst.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
