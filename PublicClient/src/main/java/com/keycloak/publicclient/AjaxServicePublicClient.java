package com.keycloak.publicclient;

import java.util.ArrayList;
import java.util.List;

public class AjaxServicePublicClient {

	
	public List<AjaxModelPublicClient> getAjaxDataCall()
	{
		List<AjaxModelPublicClient> ajaxList=new ArrayList<AjaxModelPublicClient>();
		
		AjaxModelPublicClient ajModel1=new AjaxModelPublicClient();
		ajModel1.setFirstName("Amol");
		ajModel1.setLastName("Zambre");
		ajModel1.setUserName("Amol1111");
		ajModel1.setEmail("amol.zambre@gmail.com");

		AjaxModelPublicClient ajModel2=new AjaxModelPublicClient();
		ajModel2.setFirstName("suraj");
		ajModel2.setLastName("singh");
		ajModel2.setUserName("Suraj2222");
		ajModel2.setEmail("suraj.singh@gmail.com");

		ajaxList.add(ajModel1);
		ajaxList.add(ajModel2);
		
		return  ajaxList;
		
	}
	
}
