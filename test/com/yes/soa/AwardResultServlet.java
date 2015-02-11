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
		
		try {
		    /** post方式 */
		    HttpClient client = new HttpClient();
		    PostMethod postMethod = new PostMethod(
		            "http://localhost:9080/SOA/services/UaoService/downloadImg");
		    // 参数设置
		    postMethod.setParameter("id", password);
		    // 执行postMethod
		    client.getParams().setParameter(
		            HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		    // 执行并返回状态
		    int status = client.executeMethod(postMethod);
		    String getUrl = postMethod.getResponseBodyAsString();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
