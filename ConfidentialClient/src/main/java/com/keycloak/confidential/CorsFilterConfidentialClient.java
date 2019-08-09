package com.keycloak.confidential;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CorsFilterConfidentialClient implements Filter
{
	
   @Override
     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
	  System.out.println("inside doFilter of 7777 domain>>>>");
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:9882");
      httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
      httpResponse.setHeader("Access-Control-Allow-Headers", "X-Auth-Token, Content-Type");
      httpResponse.setHeader("Access-Control-Expose-Headers", "*");
      httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
      httpResponse.setHeader("Access-Control-Max-Age", "4800");
      System.out.println("---CORS Configuration Completed for domain 7777---");
      chain.doFilter(request, response);
      }
   
	    @Override
		public void init(FilterConfig filterConfig) throws ServletException {
			System.out.println("inside init>>>>");
		}
		@Override
		public void destroy() {
			System.out.println("inside destroy>>>>");
		}
}