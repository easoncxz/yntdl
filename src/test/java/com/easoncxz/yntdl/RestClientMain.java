package com.easoncxz.yntdl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.easoncxz.yntdl.client.RestClient;
import com.easoncxz.yntdl.domain.User;

public class RestClientMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("test-context.xml");
		RestClient client = context.getBean("restClient", RestClient.class);
		
		client.login("johndoe", "1234");
		
		User u = new User();
		u.setName("Jack Johnson");
		client.save(u);
		
		((GenericApplicationContext) context).close();
	}

}
