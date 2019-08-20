package com.tools.activiti.config;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/**
 * $〉
 * 功能描述: <br>
 * 〈/$〉
 *
 * @author XQ
 * @date 2019/8/19 14:35
 */
@Configuration
public class ActivtiyConfig {
    /**
     * 〈〉
     * 功能描述: 配置<br>
     * 〈/〉
     * @author XQ
     * @date 2019/8/19 14:36
     */
    @Bean
    public ProcessEngineConfiguration processEngineConfiguration(DataSource dataSource, PlatformTransactionManager transactionManager){
        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setDatabaseSchemaUpdate("true");
        processEngineConfiguration.setDatabaseType("mysql");
//        processEngineConfiguration.setIdGenerator();
        processEngineConfiguration.setTransactionManager(transactionManager);

        //流程图字体
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");

        return processEngineConfiguration;
    }

    //流程引擎，与spring整合使用factoryBean
    @Bean
    public ProcessEngineFactoryBean processEngine(ProcessEngineConfiguration processEngineConfiguration){
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
        return processEngineFactoryBean;
    }

    //八大接口

    /**
     * 〈〉
     * 功能描述: 管理流程定义<br>
     * 〈/〉
     * @param processEngine 流程引擎
     * @return RepositoryService
     * @author XQ
     * @date 2019/8/20 14:39
     */
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine){
        return processEngine.getRepositoryService();
    }


    /**
     * 〈〉
     * 功能描述: 执行管理，包括启动、推进、删除流程实例等操作<br>
     * 〈/〉
     * @param processEngine 流程引擎
     * @return RuntimeService
     * @author XQ
     * @date 2019/8/20 14:39
     */
    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine){
        return processEngine.getRuntimeService();
    }


    /**
     * 〈〉
     * 功能描述: 任务管理<br>
     * 〈/〉
     * @param processEngine 流程引擎
     * @return RuntimeService
     * @author XQ
     * @date 2019/8/20 14:39
     */
    @Bean
    public TaskService taskService(ProcessEngine processEngine){
        return processEngine.getTaskService();
    }

    /**
     * 〈〉
     * 功能描述: 历史记录<br>
     * 〈/〉
     * @param processEngine 流程引擎
     * @return RuntimeService
     * @author XQ
     * @date 2019/8/20 14:39
     */
    @Bean
    public HistoryService historyService(ProcessEngine processEngine){
        return processEngine.getHistoryService();
    }

    /**
     * 〈〉
     * 功能描述: 一个可选服务，任务表单管理<br>
     * 〈/〉
     * @param
     * @return
     * @author XQ
     * @date 2019/8/20 14:47
     */
    @Bean
    public FormService formService(ProcessEngine processEngine){
        return processEngine.getFormService();
    }

    /**
     * 〈〉
     * 功能描述: 组织管理<br>
     * 〈/〉
     * @param
     * @return
     * @author XQ
     * @date 2019/8/20 14:46
     */
    @Bean
    public IdentityService identityService(ProcessEngine processEngine){
        return processEngine.getIdentityService();
    }

    @Bean
    public ManagementService managementService(ProcessEngine processEngine){
        return processEngine.getManagementService();
    }

    @Bean
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine){
        return processEngine.getDynamicBpmnService();
    }

    //八大接口 end
}
