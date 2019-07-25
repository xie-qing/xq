package com.tools.es.service.impl;

import com.tools.comm.ResultDB;
import com.tools.es.dao.EsBlog;
import com.tools.es.dao.UserDao;
import com.tools.es.dao.UserEsDto;
import com.tools.es.service.UserEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author xq
 */
@Component
public class UserEsServiceImpl implements UserEsService {

    /**必须使用es6版本  7版本太高不支持*/
    @Autowired
    UserDao userDao;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public ResultDB saveEsUser(EsBlog esBlog) {
//        elasticsearchTemplate.createIndex(EsBlog.class , esBlog);
        userDao.save(esBlog);
        return ResultDB.error("保存失败");

    }

    @Override
    public ResultDB getUsersByUsername(String id) {
        Optional<EsBlog> userInfo = userDao.findById(id);
        if(userInfo  != null){
            return ResultDB.success("添加成功！", userInfo);
        }
        return ResultDB.error("保存失败");
    }

    @Override
    public ResultDB deleteUserById(String id) {
        userDao.deleteById(id);
        return ResultDB.error("删除成功");
    }
}
