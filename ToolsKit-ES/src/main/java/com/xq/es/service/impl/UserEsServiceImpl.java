package com.xq.es.service.impl;

import com.google.common.collect.Lists;
import com.xq.comm.ResultDB;
import com.xq.es.dao.EsBlog;
import com.xq.es.dao.SerchLog;
import com.xq.es.dao.UserDao;
import com.xq.es.service.UserEsService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

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
        return ResultDB.error("保存成功");

    }

    @Override
    public ResultDB getUsersByUsername(SerchLog serchLog) {
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("title", serchLog.getTitle());
        Iterable<EsBlog> userInfo = userDao.search(queryBuilder);
        List<EsBlog> list= Lists.newArrayList(userInfo);
        return ResultDB.success(list);
    }

    @Override
    public ResultDB deleteUserById(String id) {
        userDao.deleteById(id);
        return ResultDB.error("删除成功");
    }
}
