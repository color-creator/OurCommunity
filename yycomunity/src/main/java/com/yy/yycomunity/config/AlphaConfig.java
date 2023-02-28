package com.yy.yycomunity.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

//装配第三方bean
@Configuration
public class AlphaConfig {

    @Bean
    public SimpleDateFormat simpleDateFormat(){
        //此时方法名就是bean的名字---simpleDateFormat
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }


}
