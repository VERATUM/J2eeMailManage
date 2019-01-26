package com.aerfa.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aerfa.entity.Feedback;
import com.aerfa.serive.FeedbackSerive;
import com.aerfa.serive.imp.FeedbackSeriveImp;

/**
 * Servlet implementation class FeedbackAjaxServlet
 */
@WebServlet("/FeedbackAjaxServlet")
public class FeedbackAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
         FeedbackSerive fs = new FeedbackSeriveImp(); 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		 
		
		Feedback feedback = new Feedback();		
		int emp_id = Integer.parseInt(request.getParameter("empid"));
		String feed_content = request.getParameter("feedcontent");
		feedback.setEmp_id(emp_id);
		feedback.setFeed_content(feed_content);
		int pos = fs.addFeedback(feedback);
		response.getWriter().println(pos);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
