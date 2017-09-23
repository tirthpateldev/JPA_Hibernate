package com.gjj.igden.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gjj.igden.dao.daoimpl.AccountDaoImpl;

public class RunClass {
	
	@Autowired
	AccountDaoImpl accountDaoImpl;
	
	public static void main(String[] args) {
		System.out.println("hello");
		ApplicationContext ctx = new AnnotationConfigApplicationContext("classpath:beans-cp.xml");
		AccountDaoImpl accountDaoImpl = ctx.getBean(AccountDaoImpl.class);
		AccountEnt accountEnt = ctx.getBean(AccountEnt.class);
		accountEnt.setAccount_name("test");
		accountEnt.setAdditional_info("nsdklnsdfksd");
		accountEnt.setEmail("ddksdnk@fnkdf.com");
		accountEnt.setPassword("dfnwjkfwefwnfn");
		accountEnt.setCreation_date("22/11/2016");
		accountDaoImpl.createAcount(accountEnt);
		
	}
}
