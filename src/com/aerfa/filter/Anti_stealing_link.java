package com.aerfa.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class Anti_stealing_link implements Filter {
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest reqs= (HttpServletRequest) req;
		HttpServletResponse ress=(HttpServletResponse) res;
		String path=reqs.getRequestURI();
		if(path.indexOf("signin.jsp")>-1){//登录页面不过滤
			chain.doFilter(req, res);//递交给下一个过滤器
            return;
        }else if(path.indexOf("signup.jsp")>-1) {
        	chain.doFilter(req, res);//递交给下一个过滤器
            return;
        }else if(path.indexOf("codeServlet")>-1) {
        	chain.doFilter(req, res);//递交给下一个过滤器
            return;
        }
		
        if (reqs.getHeader("Referer") != null) {
        	chain.doFilter(req, res);  
        } else {
            ress.getWriter().print("<script>parent.window.location.href='signin.jsp'</script>");
        }
	}
}
