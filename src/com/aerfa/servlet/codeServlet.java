package com.aerfa.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aerfa.utils.Code;

@WebServlet("/codeServlet")
public class codeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//验证码不能缓存
        response.setHeader("Expires", "-1");
        response.setHeader("cache-control", "no-cahce");
        response.setHeader("pragma", "no-cache");
        
        Code vcg = new Code();
        //取得验证码
        String vcode = vcg.generatorVCode();
        //获取验证码图片
        //BufferedImage vcodeImage = vcg.generatorRotateVCodeImage(vcode, true);
        BufferedImage vcodeImage = vcg.generatorVCodeImage(vcode, true);
        //将验证码保存到session域对象
        request.getSession().setAttribute("vcode", vcode);
        //输出验证码图片
        ImageIO.write(vcodeImage, "gif", response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
