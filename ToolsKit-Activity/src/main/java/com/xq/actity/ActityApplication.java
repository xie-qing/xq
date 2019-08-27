package com.xq.actity;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 〈〉
 * 功能描述: <br>
 * 〈/〉
 * @author XQ
 * @date 2019/8/19 11:15
 */
@SpringBootApplication(exclude={org.activiti.spring.boot.SecurityAutoConfiguration.class})
@ComponentScan(basePackages = "com.xq*")
public class ActityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActityApplication.class, args);

    }

}
