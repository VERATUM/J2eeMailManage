package com.aerfa.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("*.jsp")
public class EcodingUTF8 implements Filter {
	
	//使用过滤器拦截
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//编码格式
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");		
		response.setContentType("text/html;charset=utf-8");
		chain.doFilter(request, response);
	}

}
