package com.yes.soa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Assert;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResultServletTest {

	private AwardResultServlet servlet;
	private HttpServletRequest mockRequest;
	private HttpServletResponse mockResponse;

//	@Before
	public void setUp() {
		servlet = new AwardResultServlet();

		mockRequest = EasyMock.createMock(HttpServletRequest.class); // 加载
		mockResponse = EasyMock.createMock(HttpServletResponse.class);
	}

//	@After
	public void tearDown() {
		EasyMock.verify(mockRequest); // 验证
		EasyMock.verify(mockResponse);
	}

	@Test
	public void testDoPost() throws ServletException, IOException {

		// 录制request和response的动作
		mockRequest.getParameter("username");
		EasyMock.expectLastCall().andReturn("yuan");// 设置前一方法被调用时的返回值

		mockRequest.getParameter("password");
		EasyMock.expectLastCall().andReturn("123456");

		mockResponse.sendRedirect("welcome.jsp");

		// 回放
		EasyMock.replay(mockRequest);
		EasyMock.replay(mockResponse);

		// 开始测试Servlet的doPost方法
		servlet.doPost(mockRequest, mockResponse);

	}

	@Test
	public void testSoa() {
		try {
			/** post方式 */
			HttpClient client = new HttpClient();
			PostMethod postMethod = new PostMethod(
					"http://localhost:9080/SOA/services/UaoService/downloadImg");
			// 参数设置
			postMethod.setParameter("id", "2000");
			// 执行postMethod
			client.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			// 执行并返回状态
			int status = client.executeMethod(postMethod);
			if (status == 200) {
				String getUrl = postMethod.getResponseBodyAsString();
				Assert.assertNotNull(getUrl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
