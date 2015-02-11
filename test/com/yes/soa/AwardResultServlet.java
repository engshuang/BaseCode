package com.yes.soa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class AwardResultServlet extends HttpServlet {

	/** 
     *  
     */
	private static final long serialVersionUID = -7068817837964223972L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("username").trim();   
        String password = request.getParameter("password");   
        if ("yuan".equals(username) && "123456".equals(password))   
            response.sendRedirect("welcome.jsp");   
        else  
            response.sendRedirect("error.jsp");   
	}
}
