package com.aerfa.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.aerfa.entity.EmpInfo;
import com.aerfa.entity.FileInfo;
import com.aerfa.entity.MsgInfo;
import com.aerfa.serive.FileInfoSerive;
import com.aerfa.serive.MsgInfoService;
import com.aerfa.serive.imp.FileInfoSeriveImp;
import com.aerfa.serive.imp.MsgInfoServiceImpl;
import com.aerfa.utils.FileUploadUtil;

/**
 * 草稿的新增 /msg/save
 * 1）邮件form接收，保存草稿
 *)2）含邮件的附件，保存草稿
 * 
 * 邮件的新增 /msg/add
 * 1）邮件form接收，保存邮件
 *)2）含邮件的附件，保存邮件
 * 
 * 草稿/邮件的删除 /msg/delete
 * 
 * 草稿/的更新 /msg/update
 * 
 * 邮件的群发
 * @author micat
 */
@WebServlet(urlPatterns={"/msg/add","/msg/delete","/msg/update","/msg/save","/msg/updatesave","/msg/star","/msg/receiver"})
public class FileInfoServlet extends HttpServlet{
	private FileInfoSerive fileInfoService = new FileInfoSeriveImp();
	private MsgInfoService msgInfoService = new MsgInfoServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		req.getSession().removeAttribute("resendMsg");
		req.getSession().removeAttribute("updateMsg");
		
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/")+1);
		switch (action) {
			case "add":
				add(req,resp);
				break;
			case "save":
				save(req,resp);
				break;
			case "delete":
				delete(req,resp);
				break;
			case "update":
				update(req,resp);
				break;
			case "star":
				star(req,resp);
				break;
			case "receiver":
				receiver(req,resp);
				break;
			case "updatesave":
				updatesave(req,resp);
				break;
		}
	}
	/**
	 * 恢复删除
	 */
	public void receiver(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if(!"".equals(id)) {
			msgInfoService.recoverMsg(Integer.valueOf(id));
		}
		
		resp.getWriter().write("");
	}
	
	/**
	 * 星标设置
	 */
	public void star(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cc = req.getParameter("cc");
		String id = req.getParameter("id");
		if((!"".equals(cc)) && (!"".equals(id))) {
			if(cc.equals("2"))
				msgInfoService.starMsg(Integer.valueOf(id));
			else if(cc.equals("1"))
				msgInfoService.unStarMsg(Integer.valueOf(id));
		}
		
		resp.getWriter().write("");
	}
	
	/**
	 * 草稿的新增 /msg/save
	 */
	public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//取登录用户
		EmpInfo user = (EmpInfo)req.getSession().getAttribute("EmpInfo");
		
		//取表单值
		Map<String, String> map = FileUploadUtil.uploadFile(req, resp);
		
		//邮件信息入库
		String[] strs = msginfo(map,user,1);
		
		if(strs!=null)
			//附件信息入库
			attention( strs[0],strs[1],Integer.valueOf(strs[2]));
		
		//返回首页
		resp.sendRedirect(req.getContextPath()+"/cententindex.jsp");
	}
	
	/**
	 * 邮件的新增 /msg/add
	 */
	public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//取登录用户
		EmpInfo user = (EmpInfo)req.getSession().getAttribute("EmpInfo");
		
		//取表单值
		Map<String, String> map = FileUploadUtil.uploadFile(req, resp);
		
		//邮件信息入库
		String[] strs = msginfo(map,user,0);
		//附件信息入库
		attention( strs[0],strs[1],Integer.valueOf(strs[2]));
		
		//返回首页
		resp.sendRedirect(req.getContextPath()+"/cententindex.jsp");
	}
	//邮件信息入库
	private String[] msginfo(Map<String, String> map,EmpInfo user,int state) {
		String mid = map.get("mid");
		String title = map.get("title");
		String content = map.get("content");
		String time = map.get("time");
		String fileUrl = map.get(FileUploadUtil.FILE_URI);
		String fileName = map.get(FileUploadUtil.FILE_NAME);
		
		//邮件接收者
		//值为一个邮箱或部门ID
		String[] sp = map.get("receiver").split("-");
		String receiver = sp[0];
		
		Integer num = 0;
		if(sp.length>1) {
			num = Integer.valueOf(sp[1]);
		}
		
		//邮件信息入库
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//自定义格式
		MsgInfo msg = new MsgInfo();
		msg.setMsg_id(mid==null?null:Integer.valueOf(mid));	//邮件ID
		msg.setEmp_email(receiver.equals("")?null:receiver);	//收件人
		msg.setEmp_id(user.getEmp_id());	//发件人员工ID
		msg.setMsg_content(content);	//文件内容
		msg.setMsg_isdelete(MsgInfo.NO_DELETE);	//默认
		msg.setMsg_isread(MsgInfo.NO_READED);	//默认
		msg.setMsg_sendtime(format.format(new Date()));	//发送时间
		msg.setMsg_subject(title);	//主题
		msg.setMsg_issave(state);	//1草稿,0邮件
		msg.setMsg_atten(num);	//群邮件群发人数
		
		//保存
		if(state==1)
			mid = msgInfoService.saveNote(msg)+"";
		else
			mid = msgInfoService.sendMsg(msg)+"";
		return new String[] {fileName,fileUrl,mid};
	}
	
	//附件信息入库
	private void attention(String fileName,String fileUrl,Integer mid) {
		if(fileName!=null) {
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFile_name(fileName);
			fileInfo.setFile_type(fileName.substring(fileName.lastIndexOf(".")+1));
			fileInfo.setFile(fileUrl);
			fileInfo.setMsg_id(mid);
			//保存
			fileInfoService.addFileInfo(fileInfo);
		}
	}
	
	/**
	 * 草稿/的更新 /msg/updatesave
	 */
	public void updatesave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//取登录用户
		EmpInfo user = (EmpInfo)req.getSession().getAttribute("EmpInfo");
		
		//取表单值
		Map<String, String> map = FileUploadUtil.uploadFile(req, resp);
		
		//邮件信息入库
		String[] strs = msginfo(map,user,1);
		//附件信息入库
		attention( strs[0],strs[1],Integer.valueOf(strs[2]));
		
		//返回首页
		resp.sendRedirect(req.getContextPath()+"/cententindex.jsp");
	}
	
	/**
	 * 草稿/的更新并发送 /msg/update
	 */
	public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//取登录用户
		EmpInfo user = (EmpInfo)req.getSession().getAttribute("EmpInfo");
		
		//取表单值
		Map<String, String> map = FileUploadUtil.uploadFile(req, resp);
		
		//邮件信息入库
		String[] strs = msginfo(map,user,0);
		//附件信息入库
		attention( strs[0],strs[1],Integer.valueOf(strs[2]));
		
		//返回首页
		resp.sendRedirect(req.getContextPath()+"/cententindex.jsp");
	}
	
	/**
	 * 草稿/的删除 /msg/delete
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getParameter("mid");
		String ids = req.getParameter("mids");
		
		//根据Email的Id删除
		if(id!=null && !id.equals("")) {
			msgInfoService.deleteMsg(Integer.valueOf(id));
		}else if(ids!=null||ids.equals("")){
			String[] sps = ids.split(",");
			int[] idarr = new int[sps.length];
			
			for(int i=0;i<sps.length;i++) {
				idarr[i] = Integer.valueOf(sps[i]);
			}
			msgInfoService.deleteMsgBatch(idarr);
		}
		
		resp.getWriter().write("");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
