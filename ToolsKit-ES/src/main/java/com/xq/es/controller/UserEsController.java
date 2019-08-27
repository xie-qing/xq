package com.xq.es.controller;


import com.xq.comm.ResultDB;
import com.xq.es.dao.EsBlog;
import com.xq.es.dao.SerchLog;
import com.xq.es.service.UserEsService;
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
