package com.xq.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xq
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.xq*")
public class ToolskitRabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolskitRabbitMqApplication.class, args);
    }

}
