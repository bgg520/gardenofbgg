package com.bgg.timer;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class HelloTimer extends QuartzJobBean{

	static Logger logger = Logger.getLogger(HelloTimer.class.getName());
	
	private static int counter = 0;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		logger.info("--------------job start-------------");
		long ms = System.currentTimeMillis();
		logger.info("\t\t" + new Date(ms));
		logger.info(ms);
		logger.info("("+counter+")");
		String s = (String) context.getMergedJobDataMap().get("service");  
        System.out.println("sysout"+s);  
        System.out.println(); 
	}

}
