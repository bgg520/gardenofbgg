package com.bgg.hello.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bgg.hello.service.HelloWorldService;

@Controller
public class HelloWorldController {
	
	static Logger logger = Logger.getLogger(HelloWorldController.class.getName());
	
	 @Autowired
     private HelloWorldService helloWorldService;

	 public HelloWorldController() {
		// TODO Auto-generated constructor stub
		 System.out.println("chushihua:Controller");
	}
     
     @RequestMapping("helloworld")
     @ResponseBody
     public String getNewName(String userName){
            String newUserName = helloWorldService.getNewName(userName);
            logger.info("aadddd");
            return newUserName;
     }

}
