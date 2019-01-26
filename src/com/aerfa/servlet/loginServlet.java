package com.aerfa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aerfa.entity.Attention;
import com.aerfa.entity.CountEmail;
import com.aerfa.entity.DeptInfo;
import com.aerfa.entity.EmpInfo;
import com.aerfa.serive.AttentionSerive;
import com.aerfa.serive.EmpInfoSerive;
import com.aerfa.serive.MsgInfoService;
import com.aerfa.serive.imp.AttentionSeriveImp;
import com.aerfa.serive.imp.EmpInfoSeriveImp;
import com.aerfa.serive.imp.MsgInfoServiceImpl;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EmpInfoSerive empInfoSerive = new EmpInfoSeriveImp();
	MsgInfoService msgInfoService =new MsgInfoServiceImpl();
	AttentionSerive attentionSerive =new AttentionSeriveImp();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		switch(action) {
			case "email":
				String emp_email = req.getParameter("emailNum");
				EmpInfo ei = empInfoSerive.selectOneEmpInfo(emp_email);
				if(ei==null) {
					res.getWriter().println("0");
				}else {
					req.getSession().setAttribute("EmpInfo", ei);
					res.getWriter().println("1");
				}
				break;
			case "code":
				String codeNum = req.getParameter("codeNum");
				String vcode =(String) req.getSession().getAttribute("vcode");
				if(codeNum.equalsIgnoreCase(vcode)) {
					res.getWriter().println("1");
				}else {
					res.getWriter().println("0");
				}
				break;
			case "submit":
				String inputPassword = req.getParameter("inputPassword");
				EmpInfo empInfo = (EmpInfo) req.getSession().getAttribute("EmpInfo");
				String password = empInfo.getEmp_password();
				if(inputPassword.equals(password)) {
					DeptInfo deptInfo = empInfoSerive.getDept(empInfo.getDept_id());
					req.getSession().setAttribute("DeptInfo", deptInfo);
					if(empInfo.getEmp_state()==0) {
						res.sendRedirect("ManagerServlet?action=0");
					}else {
						String email = empInfo.getEmp_email();
						Integer eid =  empInfo.getEmp_id();
						Integer did = deptInfo.getDept_id();
						CountEmail countEmail =new CountEmail();
 						//全部邮件的数量
						countEmail.setAllMsg(msgInfoService.countByEidAllMsg(email));
						//未读邮件数量
						countEmail.setNewMsg(msgInfoService.countByEidNewMsg(email));
						//草稿邮件数量
						countEmail.setNote(msgInfoService.countByEidNote(eid));
						//已读邮件数量
						countEmail.setOldMsg(msgInfoService.countByEidOldMsg(email));
						//垃圾邮件数量
						countEmail.setRubbishMsg(msgInfoService.countByEidRubbishMsg(email));
						//已发送邮件数量
						countEmail.setSendMsg(msgInfoService.countByEidSendMsg(eid));
						//星标邮件数量
						countEmail.setStarMsg(msgInfoService.countByEidStarMsg(email));
						//群邮件数量
						countEmail.setqYMsg(msgInfoService.countByQYMsg(did));
						//获取关心人集合
						List<Attention> attLis = attentionSerive.findAttLis(eid);
						req.getSession().setAttribute("attLis", attLis);
						req.getSession().setAttribute("countEmail", countEmail);
						res.sendRedirect("index.jsp");
					}
				}else {
					req.getSession().setAttribute("passwordError", "密码错误!");
					res.sendRedirect("signin.jsp");
				}
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}