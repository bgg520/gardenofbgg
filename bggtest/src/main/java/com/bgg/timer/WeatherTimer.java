package com.bgg.timer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.bgg.timer.bean.Weather;
import com.bgg.timer.dao.WeatherInfoDao;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherTimer extends QuartzJobBean {
	
	private static SqlSessionFactory sessionFactory = null;
	private static Map<String, String> city;
	private static int requestTimes = 0;
	private static int maxRequestTimes = 10;
	// 读取配置文件
	static {
		try {
			Reader is = Resources.getResourceAsReader("sqlMapConfig.xml");
			SqlSessionFactoryBuilder ss = new SqlSessionFactoryBuilder();
			sessionFactory = ss.build(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		InputStream ii = WeatherTimer.class.getResourceAsStream("citycode.properties");
		Properties pp = new Properties();
		try {
			pp.load(ii);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		city = new HashMap<String, String>((Map)pp);
		while (true) {
			Iterator<String> it = city.keySet().iterator();
			while(it.hasNext()){
				String citycode = city.get(it.next());
				SqlSession session = sessionFactory.openSession();
				WeatherInfoDao wiDao = session.getMapper(WeatherInfoDao.class);
				boolean is = getWeatherInfo(citycode,wiDao);
				session.commit();
				session.close();
				try {
					if (is) {
						Thread.sleep(10 * 1000l);
					} else {
						Thread.sleep(20 * 1000l);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(600 * 1000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean getWeatherInfo(String cityCode, WeatherInfoDao wiDao) {
		boolean isSuccess;
		// 101280101 广州编号
		try {
			URL url = new URL("http://www.weather.com.cn/adat/cityinfo/"+cityCode+".html");
			InputStream is = url.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));// 指定编码格式，否则容易乱码
			StringBuilder sb = new StringBuilder();
			while (reader.ready()) {
				sb.append(reader.readLine());
			}
			String result = sb.toString();
			System.out.println(result);
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			Weather wi = objectMapper.readValue(result, Weather.class);
			wi.getWeatherinfo().setId(UUID.randomUUID().toString());
			wi.getWeatherinfo().setCreated(new Date());
			
			wiDao.insertWeatherInfo(wi);
			
			System.out.println();
			isSuccess = true;
			requestTimes = 0;
		} catch (Exception e) {
			e.printStackTrace();
			requestTimes++;
			System.out.println("city\t\t"+cityCode+"\t\ttryTimes:"+requestTimes);
			if(requestTimes>maxRequestTimes){
				isSuccess = true;
			} else {
				isSuccess = getWeatherInfo(cityCode, wiDao);
				//isSuccess = false;
			}
		}
		return isSuccess;
	}
}
