package com.tools.es.controller;

import com.tools.comm.ResultDB;
import com.tools.es.dao.EsBlog;
import com.tools.es.dao.SerchLog;
import com.tools.es.dao.UserEsDto;
import com.tools.es.service.UserEsService;
import com.tools.rabbitmq.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userEs")
@Validated
public class UserEsController {

    @Autowired
    UserEsService userEsService;

    @PostMapping("/save")
    ResultDB saveEsUser(@RequestBody @Validated EsBlog esBlog) {
        return userEsService.saveEsUser(esBlog);
    }

    @PostMapping("/getUserEsByUserName")
    ResultDB getUsersByUsername(@RequestBody SerchLog serchLog) {
        return userEsService.getUsersByUsername(serchLog);
    }

    @PostMapping("/deleteUserById")
    ResultDB deleteUserById(@RequestParam String id) {
        return userEsService.deleteUserById(id);
    }


}
