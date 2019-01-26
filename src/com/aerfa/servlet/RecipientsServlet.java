package com.aerfa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aerfa.entity.DeptInfo;
import com.aerfa.serive.DeptInfoService;
import com.aerfa.serive.imp.DeptInfoServiceImpl;
import com.google.gson.Gson;

@WebServlet("/recipients")
public class RecipientsServlet extends HttpServlet{
	private DeptInfoService service = new DeptInfoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		
		
		List<DeptInfo> list = service.selectDeptInfosAndEmpinfos();
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		System.out.println(json);
		
		resp.getWriter().write(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
}
