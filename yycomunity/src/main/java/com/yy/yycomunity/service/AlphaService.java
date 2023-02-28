package com.yy.yycomunity.service;

import com.yy.yycomunity.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Scope("singleton")
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;


    public String find(){
        return alphaDao.select();//serv 依赖dao
    }


//容器管理他的初始化和销毁的方法
    public AlphaService(){
        System.out.println("AlphaService构造方法运行完成");
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化AlphaService");

    }

    @PreDestroy
    public void destroy(){
        System.out.println("销毁bean之前的工作已经完成");
    }
}
