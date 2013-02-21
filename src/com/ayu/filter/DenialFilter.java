package com.ayu.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DenialFilter implements Filter {

  FilterConfig config;

  public String IP_RANGE;

  public DenialFilter() {
  }

  public void init(FilterConfig filterConfig) throws ServletException {

    this.config = filterConfig;

  }

  public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
	HttpServletRequest req = (HttpServletRequest) request;
    String ip = request.getRemoteAddr();
	this.IP_RANGE = (String) req.getServletContext().getAttribute(ip);
    HttpServletResponse httpResp = null;

    if (response instanceof HttpServletResponse)
      httpResp = (HttpServletResponse) response;
   
    if(IP_RANGE!=null){
    if (IP_RANGE.equals(ip)) {

      httpResp.sendError(HttpServletResponse.SC_FORBIDDEN,"You are in our DataBase as an attacker and blocked as a result.To be removed please mail your reason to ayushman999@gmail.com ");

    } else {

      chain.doFilter(request, response);
    }
    }
    else
    {
    	chain.doFilter(request, response);
    }
  }// doFilter

  public void destroy() {
    
  }

}