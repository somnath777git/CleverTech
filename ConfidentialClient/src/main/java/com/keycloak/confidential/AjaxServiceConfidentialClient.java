package com.keycloak.confidential;

import java.util.ArrayList;
import java.util.List;

public class AjaxServiceConfidentialClient {

	
	public List<AjaxModelConfidentialClient> getAjaxDataCall()
	{
		List<AjaxModelConfidentialClient> ajaxList=new ArrayList<AjaxModelConfidentialClient>();
		
		AjaxModelConfidentialClient ajModel1=new AjaxModelConfidentialClient();
		ajModel1.setFirstName("somnath");
		ajModel1.setLastName("shirsat");
		ajModel1.setUserName("somnath777");
		ajModel1.setEmail("somnath8383@gmail.com");

		AjaxModelConfidentialClient ajModel2=new AjaxModelConfidentialClient();
		ajModel2.setFirstName("rahul");
		ajModel2.setLastName("singh");
		ajModel2.setUserName("rahulsingh9999");
		ajModel2.setEmail("rahulsingh@gmail.com");
		
		ajaxList.add(ajModel1);
		ajaxList.add(ajModel2);
		
		return  ajaxList;
		
	}
	
}
