//package com.xq.es.config;
//
//import javafx.scene.NodeBuilder;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.Node;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//import javax.annotation.PostConstruct;
//import java.net.InetAddress;
//import java.util.List;
//import java.util.Properties;
//
///**
// * @author xq
// */
//@Configuration
//@Slf4j
//@EnableElasticsearchRepositories(basePackages = "com.tools.es.dao")
//@EnableConfigurationProperties(ElasticsearchProperties.class)
//public class EsConfig {
//    private static TransportClient transport = null;
//
//    private final ElasticsearchProperties properties;
//
//    public EsConfig(ElasticsearchProperties properties) {
//        this.properties = properties;
//    }
//
//    /**
//     *  解决启动报错Error creating bean with name 'elasticsearchClient', AvailableProcessors is already set to [4]
//     *  原因：redis与es引入冲突
//     */
//    @PostConstruct
//    void init() {
//        System.setProperty("es.set.netty.runtime.available.processors", "false");
//    }
//
////    @Bean(
////            name = {"elasticsearchTemplate"}
////    )
////    public ElasticsearchTemplate elasticsearchTemplate() {
////
////    }
//
////    @Bean
//    public TransportClient elasticsearchClient() throws Exception {
//        TransportClientFactoryBean factory = new TransportClientFactoryBean();
//        factory.setClusterNodes(this.properties.getClusterNodes());
//        factory.setProperties(createProperties());
//        factory.afterPropertiesSet();
//        return factory.getObject();
//    }
//
//
//
//    private Properties createProperties() {
//        Properties properties = new Properties();
//        properties.put("cluster.name", this.properties.getClusterName());
//        properties.putAll(this.properties.getProperties());
//        return properties;
//    }
//}
