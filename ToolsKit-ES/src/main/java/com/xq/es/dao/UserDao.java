package com.xq.es.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xq
 */
@Repository
public interface UserDao  extends ElasticsearchRepository<EsBlog,String> {



}