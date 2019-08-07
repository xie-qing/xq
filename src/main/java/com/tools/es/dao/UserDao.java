package com.tools.es.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xq
 */
@Repository
public interface UserDao  extends ElasticsearchRepository<EsBlog,String> {



}