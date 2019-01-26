package com.aerfa.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aerfa.entity.EmpInfo;
import com.aerfa.serive.EmpInfoSerive;
import com.aerfa.serive.imp.EmpInfoSeriveImp;
import com.aerfa.utils.HttpClientUtil;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//用户名
	private static String Uid = "wyh668";
	//接口安全秘钥
	private static String Key = "d41d8cd98f00b204e980";
	
	EmpInfoSerive empInfoSerive = new EmpInfoSeriveImp();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		switch(action) {
			case "email":
				String emp_email = req.getParameter("email");
				EmpInfo ei = empInfoSerive.selectOneEmpInfo(emp_email);
				if(ei==null) {
					res.getWriter().println("0");
				}else {
					req.getSession().setAttribute("EmpInfoVer", ei);
					res.getWriter().println("1");
				}
				break;
			case "phone":
				String emp_tel = req.getParameter("telphone");
				EmpInfo eie =(EmpInfo) req.getSession().getAttribute("EmpInfoVer");
				String tell = eie.getEmp_tel();
				if(emp_tel.equals(tell)) {
					res.getWriter().println("1");
					req.getSession().setAttribute("tel", tell);
				}else {
					res.getWriter().println("0");
				}
				break;
			case "verification":
				String code = req.getParameter("code");
				Object telcode =String.valueOf(req.getSession().getAttribute("telcode"));
				if(code.equals(telcode)) {
					req.getSession().setAttribute("changePasswordInfo", 1);
					res.sendRedirect("signup.jsp");
				}else {
					req.getSession().setAttribute("changePasswordInfo", 0);
					res.sendRedirect("signup.jsp");
				}
				break;
			case "tel":
				String tel = req.getParameter("tel");
				Random random = new Random();
				int temp = random.nextInt(10000);
				//短信内容
				String smsText = "验证码:"+temp+"【123】";
				req.getSession().setAttribute("telcode", temp);
				String ret = this.telCode(smsText,tel, req, res);
				res.getWriter().println(ret);
				break;
			case "changepassword":
				String PasswordOne = req.getParameter("PasswordOne");
				String emailo = req.getParameter("emailo");
				int temp1 =empInfoSerive.updateEmpInfoPassword(PasswordOne,emailo);
				if(temp1>0) {
					res.sendRedirect("signin.jsp");
				}else {
					res.sendRedirect("signup.jsp");
				}
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	//调用该方法获取短信验证码
	public String telCode(String smsText,String tel,HttpServletRequest req, HttpServletResponse res) {
		//手机号码，多个号码如13800000000,13800000001,13800000002
		String smsMob = tel;
		HttpClientUtil client = HttpClientUtil.getInstance();
		//UTF发送
		int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);
		if(result>0){
			//System.out.println("UTF8成功发送条数=="+result);
			return "1";
		}else{
			//System.out.println(client.getErrorMsg(result));
			return "0";
		}	
		
	}
	
}
