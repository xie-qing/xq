package com.xq.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 《〉
 * 功能描述: redis分布式锁<br>
 * 〈〉
 *
 * @author XQ
 * @date 2019/8/8 16:24
 */
@Component
@Data
@ConfigurationProperties(prefix = "spring.redis")
public class RedissionConfig {

    private String password;

    private int port;

    private String host;


    public RedissonClient getRedisClient(){
        Config config = new Config();
        //单机模式
        config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password);
        //从redis设置
        //config.useMasterSlaveServers().addSlaveAddress()
        return Redisson.create(config);
    }
}
