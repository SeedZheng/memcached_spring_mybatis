package com.test.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.bean.User;
import com.test.server.IUserServer;

public class JunitTest {

	@Test
	public void test() {
		ApplicationContext context = new ClassPathXmlApplicationContext(  
                "applicationContext.xml");  
        IUserServer userService = (IUserServer) context.getBean("userServer");  
        User user = userService.testMethod("aa"); 
        System.out.println(user.getName());
	}

}
