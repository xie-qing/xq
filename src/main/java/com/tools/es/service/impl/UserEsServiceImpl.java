package com.tools.es.service.impl;

import com.google.common.collect.Lists;
import com.tools.comm.ResultDB;
import com.tools.es.dao.EsBlog;
import com.tools.es.dao.SerchLog;
import com.tools.es.dao.UserDao;
import com.tools.es.dao.UserEsDto;
import com.tools.es.service.UserEsService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.querydsl.QPageRequest;
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
