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
    /**
     * 分页查询博客(去重)
     * @param title
     * @param summary
     * @param content
     * @return
     **/
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary, String content, Pageable pageable);

    @Override
    List<EsBlog> findAll();


}