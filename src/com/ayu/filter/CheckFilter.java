package com.ayu.filter;

import java.io.IOException;
import java.util.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CheckFilter implements Filter {
	SimpleCache lruCache;
	long time;
	Timer t;
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			String str = req.getRemoteAddr();
			if(lruCache.map.isEmpty())
			{
				//System.out.println("It is empty");
				lruCache.map.put(str, new Timer());
				chain.doFilter(request, response);
			}
			for (Map.Entry<String,Timer> e : lruCache.getAll()){
				if(e.getKey().equals(str))
				{	
					t = e.getValue();
					e.getValue().count++;
					lruCache.map.put(str,t);
					
					
				}
				else
				{
					//lruCache.map.remove(e.getKey());
					lruCache.map.put(str,new Timer());
				}
				
			}
			 if(t.count>10)
			{	
				time = System.currentTimeMillis();
				//System.out.println(t.check(time)+"1");	
				if(t.check(time)==true)
				{
					req.getServletContext().setAttribute(str, str);
					//System.out.println((String) req.getServletContext().getAttribute("IP"));
					//System.out.println(t.check(time)+"2");	
				res.sendError(HttpServletResponse.SC_FORBIDDEN, "You Are Perceived as a Threat");
				}
				else if(t.check(time)==false)
				{
					//System.out.println(t.check(time)+"3");	
					lruCache.map.put(str,new Timer());
					//System.out.println(new Timer().count);
					chain.doFilter(request, response);
				}
				
			}
			else
			{
				//System.out.println(t.check(time)+"4");	
				// pass the request along the filter chain
				chain.doFilter(request, response);
			}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		try {
			lruCache = new SimpleCache(100);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
