package com.keycloak.publicclient;

import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class PublicClient {

	public static void main(String[] args) {
		SpringApplication.run(PublicClient.class, args);
	}
}

@Controller
@RequestMapping("/keycloak")
class ProductController {

	
	@GetMapping(path = "/products2")
	public String getProducts(Model model){
		model.addAttribute("products2", Arrays.asList("iPad","iPhone","iPod"));
		return "products2";
	}
	@GetMapping(path = "/login")
	public void keyCloakLogin(Model model){
	}
	
	
	@CrossOrigin(origins="*",allowedHeaders="*",allowCredentials="true")
	@GetMapping("/getAjaxCallData")
	public void  getAjaxlist(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
		System.out.println("inside getAjaxCallData");
		System.out.println("springkeycloak-portal");
		System.out.println("8888 port Client 2");
		AjaxServicePublicClient ajaxService=new AjaxServicePublicClient();
		System.out.println(ajaxService.getAjaxDataCall());
		JSONObject responseJson=new JSONObject();
		JSONObject jsonObject=null;
		JSONArray jsonArray=new JSONArray();
		for (AjaxModelPublicClient ajaxdata : ajaxService.getAjaxDataCall()) {
			jsonObject=new JSONObject();
			jsonObject.put("FirstName", ajaxdata.getFirstName());
			jsonObject.put("LastName", ajaxdata.getLastName());
			jsonObject.put("UserName", ajaxdata.getUserName());
			jsonObject.put("Email", ajaxdata.getEmail());
			jsonArray.put(jsonObject);
		}
		responseJson.put("Users", jsonArray);
		response.getWriter().print(responseJson);
		}
		catch (Exception e) {
			System.out.println("Exception in getAjaxlist>>>>"+e);
		
		}
	}
	
	@GetMapping(path = "/category2")
	public String getCategory(Model model){
		model.addAttribute("category2", Arrays.asList("Mobiles and Accessories","Electronics","Cloths"));
		return "category2";
	}

	@GetMapping(path = "/logout")
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "/";
	}
	
	@Configuration
	@EnableWebSecurity
	@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
	class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter 
	{
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
			keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
			auth.authenticationProvider(keycloakAuthenticationProvider);
		}

		@Bean
		@Override
		protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
			return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
		}

		@Bean
		public KeycloakConfigResolver KeycloakConfigResolver() {return new KeycloakSpringBootConfigResolver();}
		
		
		@Bean
		public CorsFilterPublicClient getCorsEnabled()
		{
			return new CorsFilterPublicClient();
		}
		
		/*@Bean
	    public WebMvcConfigurer corsConfiguration() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/keycloak/**")
	                        .allowedMethods(HttpMethod.GET.toString(), HttpMethod.POST.toString(),
	                                HttpMethod.PUT.toString(), HttpMethod.DELETE.toString(), HttpMethod.OPTIONS.toString())
	                        .allowedOrigins("*");
	            }
	        };
	    }*/
		
		@Override
		protected void configure(HttpSecurity http) throws Exception
		{
			System.out.println("HttpSecurity calling");
			super.configure(http);
			http.cors().and()
					.authorizeRequests()
					.antMatchers("/keycloak/*").hasRole("user")
					.anyRequest().permitAll();
		}
		
	}
}
