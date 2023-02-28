package com.yy.yycomunity;


import com.yy.yycomunity.controller.AlphaController;
import com.yy.yycomunity.dao.AlphaDao;
import com.yy.yycomunity.service.AlphaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = YycomunityApplication.class)
public class YycomunityApplicationTests implements  ApplicationContextAware{

	//某个类实现了 ApplicationContextAware的setApplicationContext方法时，
	//Spring容器会在扫描组件时候自动检测到，并调用setxxx方法把他自己传进来
	//这里用一个成员变量去记录传入的application
	private ApplicationContext applicationContext;


	//传入的接口ApplicationContext 就是Spring容器-----它的顶层是继承了BeanFactory，子接口功能更多
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}


	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);

		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);//按照类型获取bean,当满足条件的bean有两个时，就会失败
		//此时可在需要的类上面加一个@Primary注解。
		//这个方法用到了面向接口的思想，传入的是接口的类型，她的实现类改变对这个方法是没有影响的
		//解耦：将调用方和实现类解耦，他们之间没有直接的关系，调用方传入的是抽象类
		System.out.println(alphaDao.select());

		alphaDao = (AlphaDao) applicationContext.getBean("alphahibernate",AlphaDao.class);//使用名字获得bean，返回的类型是一个object类型，需要强制转型
	}


	@Test
	public void testBeanManagement(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	//自动装配beam
	@Autowired
	@Qualifier("alphahibernate")
	private AlphaDao alphaDao;

	@Autowired
	private  SimpleDateFormat simpleDateFormat;
	@Autowired
	private AlphaController alphaController;

	@Test
	public void testDI(){
		System.out.println(alphaDao);
		System.out.println(alphaController);
		System.out.println(simpleDateFormat);
	}

}
