package com.bgg.hello.service;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldService implements IHelloWorldService {

	public HelloWorldService() {
		// TODO Auto-generated constructor stub
		System.out.println("chushihua");
	}
	
	public String getNewName(String userName) {
		// TODO Auto-generated method stub
		System.out.println("进来了");
		return "hello spring!"+userName;
	}

}
