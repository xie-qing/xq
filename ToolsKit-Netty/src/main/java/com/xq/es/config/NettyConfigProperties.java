package com.xq.es.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author admin3
 */
@Data
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyConfigProperties {

    /**
     * 地址
     */
    private String url;

    /**
     * 端口
     */
    private int port;
}
