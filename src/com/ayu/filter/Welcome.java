package com.ayu.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html/>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Application To Be Protected</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<p>Help Us To Be Safe</p>");
			out.println("</body>");
			out.println("</html>");
	}

}
