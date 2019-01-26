package com.aerfa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aerfa.entity.EmpInfo;
import com.aerfa.entity.MsgInfo;
import com.aerfa.serive.MsgInfoService;
import com.aerfa.serive.imp.MsgInfoServiceImpl;
import com.google.gson.Gson;

/**
 * 
 * @author micat
 * 
 */
@WebServlet(urlPatterns= {"/msg/find/all","/msg/find/note","/msg/find/new"
	,"/msg/find/old","/msg/find/rubbish","/msg/find/send","/msg/find/star","/msg/find/topic"})
public class MsgFindServlet extends HttpServlet{
	
	private MsgInfoService msgService = new MsgInfoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/")+1);
		
		//取登录用户
		EmpInfo user = (EmpInfo)req.getSession().getAttribute("EmpInfo");
		
		switch (action) {
			case "all":
				all(req,resp,user);
				break;
			case "note":
				note(req,resp,user);
				break;
			case "new":
				news(req,resp,user);
				break;
			case "old":
				old(req,resp,user);
				break;
			case "rubbish":
				rubbish(req,resp,user);
				break;
			case "send":
				send(req,resp,user);
				break;
			case "star":
				star(req,resp,user);
				break;
			case "topic":
				topic(req,resp,user);
				break;
		}
	}
	
	private void topic(HttpServletRequest req, HttpServletResponse resp,EmpInfo user) throws ServletException, IOException {
			List<MsgInfo> list = msgService.findByQYMsg(user.getDept_id());
			if(list!=null) {
				Gson gson = new Gson();
				String json = gson.toJson(list);
				resp.getWriter().write(json);
			}else {
				resp.getWriter().write("\"state\":\"failed\"");
			}
	}
	
	private void all(HttpServletRequest req, HttpServletResponse resp,EmpInfo user) throws ServletException, IOException {
		List<MsgInfo> list = msgService.findByEidAllMsg(user.getEmp_email());
		if(list!=null) {
			Gson gson = new Gson();
			String json = gson.toJson(list);
			resp.getWriter().write(json);
		}else {
			resp.getWriter().write("\"state\":\"failed\"");
		}
	}
	private void note(HttpServletRequest req, HttpServletResponse resp,EmpInfo user) throws ServletException, IOException {
		List<MsgInfo> list = msgService.findByEidNote(user.getEmp_id());
		if(list!=null) {
			Gson gson = new Gson();
			String json = gson.toJson(list);
			resp.getWriter().write(json);
		}else {
			resp.getWriter().write("\"state\":\"failed\"");
		}
	}
	private void news(HttpServletRequest req, HttpServletResponse resp,EmpInfo user) throws ServletException, IOException {
		List<MsgInfo> list = msgService.findByEidNewMsg(user.getEmp_email());
		if(list!=null) {
			Gson gson = new Gson();
			String json = gson.toJson(list);
			resp.getWriter().write(json);
		}else {
			resp.getWriter().write("\"state\":\"failed\"");
		}
	}
	private void old(HttpServletRequest req, HttpServletResponse resp,EmpInfo user) throws ServletException, IOException {
		List<MsgInfo> list = msgService.findByEidOldMsg(user.getEmp_email());
		if(list!=null) {
			Gson gson = new Gson();
			String json = gson.toJson(list);
			resp.getWriter().write(json);
		}else {
			resp.getWriter().write("\"state\":\"failed\"");
		}
	}
	private void rubbish(HttpServletRequest req, HttpServletResponse resp,EmpInfo user) throws ServletException, IOException {
		List<MsgInfo> list = msgService.findByEidRubbishMsg(user.getEmp_email());
		if(list!=null) {
			Gson gson = new Gson();
			String json = gson.toJson(list);
			resp.getWriter().write(json);
		}else {
			resp.getWriter().write("\"state\":\"failed\"");
		}
	}
	private void send(HttpServletRequest req, HttpServletResponse resp,EmpInfo user) throws ServletException, IOException {
		List<MsgInfo> list = msgService.findByEidSendMsg(user.getEmp_id());
		if(list!=null) {
			Gson gson = new Gson();
			String json = gson.toJson(list);
			resp.getWriter().write(json);
		}else {
			resp.getWriter().write("\"state\":\"failed\"");
		}
	}
	private void star(HttpServletRequest req, HttpServletResponse resp,EmpInfo user) throws ServletException, IOException {
		List<MsgInfo> list = msgService.findByEidStarMsg(user.getEmp_email());
		if(list!=null) {
			Gson gson = new Gson();
			String json = gson.toJson(list);
			resp.getWriter().write(json);
		}else {
			resp.getWriter().write("\"state\":\"failed\"");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
