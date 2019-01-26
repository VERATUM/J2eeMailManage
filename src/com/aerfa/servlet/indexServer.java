package com.aerfa.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/indexServer")
public class indexServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		switch(action) {
		case "cg":
			String cg =  req.getParameter("mail");
			req.getSession().setAttribute("lis", cg );
			res.getWriter().print("1");
			break;
		case "all":
			String all =  req.getParameter("mail");
			req.getSession().setAttribute("lis", all );
			res.getWriter().println("1");
			break;
		case "lj":
			String lj =  req.getParameter("mail");
			req.getSession().setAttribute("lis", lj );
			res.getWriter().println("1");
			break;
		case "qy":
			String qy =  req.getParameter("mail");
			req.getSession().setAttribute("lis", qy );
			res.getWriter().println("1");
			break;
		case "xb":
			String xb =  req.getParameter("mail");
			req.getSession().setAttribute("lis", xb );
			res.getWriter().println("1");
			break;
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
