package com.xq.actity.controller;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * $〉
 * 功能描述: <br>
 * 〈/$〉
 *
 * @author XQ
 * @date 2019/8/19 11:29
 */
@RestController
@Slf4j
public class TestController {

    @Resource
    RuntimeService runtimeService;

    @Resource
    TaskService taskService;

    @Resource
    RepositoryService repositoryService;

    /**
     * 〈〉
     * 功能描述: 启动流程实例<br>
     * 〈/〉
     * @return String
     * @author XQ
     * @date 2019/8/20 14:49
     */
    @GetMapping("/test")
    public String getTest() {
        log.info("开始申请................");
        //部署流程定义（从classpath）
        Deployment deploy = repositoryService.createDeployment().name("请假流程定义").addClasspathResource("processes/leave.bpmn20.xml").deploy();
        log.info("部署id:" + deploy.getId()  + "  部署名称：" + deploy.getName());
        // 获取流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deploy.getId()).singleResult();
        log.info("流程id:" + processDefinition.getId()  + "  流程名称：" + processDefinition.getName());
        // 启动流程定义，返回流程实例
        //流程启动时设置流程变量
        Map<String,Object> variables = new HashMap<>();
        variables.put("userId","周芷若");
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId() , variables);
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        log.info("任务id:" + task.getId()  + "  任务名称：" + task.getName());
        log.info("结束申请................");
        return "hello world spring boot...." + task.getId();
    }


    /**
     * 〈〉
     * 功能描述: 查看个人任务列表<br>
     * 〈/〉
     * @param
     * @return
     * @author XQ
     * @date 2019/8/20 15:45
     */
    @GetMapping("/getTask")
    public String getTaskByPerson(String name){
        return taskService.createTaskQuery().taskAssignee(name).list().toString();
    }


}
