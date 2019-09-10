package com.xq.rabbitmq;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author xq
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.xq*")
@EnableSwaggerBootstrapUI
public class ToolskitRabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolskitRabbitMqApplication.class, args);
    }

}
