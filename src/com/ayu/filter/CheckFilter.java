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
	HashMap<String,Date>d;
	SimpleCache lruCache;
	int count =1;
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
			for (Map.Entry<String,Integer> e : lruCache.getAll()){
				if(e.getKey().equals(str))
				{
					count++;
					lruCache.map.put(str, count);
					
				}
				else
				{	int counter=1;
					lruCache.map.put(str, counter);
				}
				
			}
			if(count>10)
			{
				res.sendError(HttpServletResponse.SC_FORBIDDEN, "You Are Perceived as a Threat");
			}
			else
			{
			// pass the request along the filter chain
			chain.doFilter(request, response);
			}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		try {
			d = new HashMap<String,Date>();
			lruCache = new SimpleCache("10.0.0.1",count,100);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
