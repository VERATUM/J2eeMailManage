package com.aerfa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aerfa.entity.EmpInfo;
import com.aerfa.entity.FileInfo;
import com.aerfa.entity.MsgInfo;
import com.aerfa.serive.EmpInfoSerive;
import com.aerfa.serive.FileInfoSerive;
import com.aerfa.serive.MsgInfoService;
import com.aerfa.serive.imp.EmpInfoSeriveImp;
import com.aerfa.serive.imp.FileInfoSeriveImp;
import com.aerfa.serive.imp.MsgInfoServiceImpl;

@WebServlet(urlPatterns= {"/page","/uu","/show","/uf","/clear"})
public class PageServlet extends HttpServlet{
	private MsgInfoService msgService = new MsgInfoServiceImpl();
	private FileInfoSerive fileService = new FileInfoSeriveImp();
	private EmpInfoSerive empService = new EmpInfoSeriveImp();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		if(uri.contains("page")) {
			String lis = (String)req.getSession().getAttribute("lis");
			resp.getWriter().write("\"lis\":\""+lis+"\"");
		}else if(uri.contains("uu")){
			String mid = req.getParameter("mid");
			MsgInfo updateMsg = msgService.findById(Integer.valueOf(mid));
			req.getSession().removeAttribute("resendMsg");
			req.getSession().setAttribute("updateMsg", updateMsg);
			resp.getWriter().write("");
		}else if(uri.contains("uf")){
			String mid = req.getParameter("mid");
			MsgInfo resendMsg = msgService.findById(Integer.valueOf(mid));
			req.getSession().removeAttribute("updateMsg");
			req.getSession().setAttribute("resendMsg", resendMsg);
			resp.getWriter().write("");
		}else if(uri.contains("show")) {
			String mid = req.getParameter("mid");
			MsgInfo showMsg = msgService.findById(Integer.valueOf(mid));
			//设置为已读
			msgService.readMsg(Integer.valueOf(mid));
			//获取附件
			List<FileInfo> fileinfo = fileService.selectFileByMsg(Integer.parseInt(mid));
			EmpInfo emp = empService.selectOneEmpInfo(showMsg.getEmp_id());
			
			System.out.println(fileinfo);
			
			FileInfo fi = new FileInfo();
			fi.setFile_name("空");
			fi.setFile("#");
			
			req.getSession().setAttribute("showMsg", showMsg);
			req.getSession().setAttribute("attachment",fileinfo.size()<1?fi:fileinfo.get(0));
			req.getSession().setAttribute("origin", emp);
			resp.getWriter().write("");
		}else if(uri.contains("clear")) {
			req.getSession().removeAttribute("updateMsg");
			req.getSession().removeAttribute("resendMsg");
			resp.getWriter().write("");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
