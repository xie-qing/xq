package com.xq.es.service;


import com.xq.comm.ResultDB;
import com.xq.es.dao.EsBlog;
import com.xq.es.dao.SerchLog;

/**
 * @author xq
 */

public interface UserEsService {

    ResultDB saveEsUser(EsBlog esBlog);

    ResultDB getUsersByUsername(SerchLog s);

    ResultDB deleteUserById(String id);
}
