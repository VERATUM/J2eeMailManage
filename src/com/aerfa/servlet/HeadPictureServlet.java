package com.aerfa.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aerfa.entity.EmpInfo;
import com.aerfa.serive.EmpInfoSerive;
import com.aerfa.serive.imp.EmpInfoSeriveImp;
import com.aerfa.utils.FileUploadUtil;

@WebServlet("/headpiture")
public class HeadPictureServlet extends HttpServlet{
	private EmpInfoSerive empService = new EmpInfoSeriveImp();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Map<String, String> map = FileUploadUtil.uploadFile(req, resp);
		String url = map.get(FileUploadUtil.FILE_URI);
		
		EmpInfo user = (EmpInfo)req.getSession().getAttribute("EmpInfo");
		
		user.setEmp_photo(url);
		
		empService.editor(user);
		
		req.getSession().setAttribute("EmpInfo",user);
		
		
		resp.getWriter().write("");
	}
}
