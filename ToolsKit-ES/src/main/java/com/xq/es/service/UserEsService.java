package com.xq.es.service;

import com.tools.comm.ResultDB;
import com.tools.es.dao.EsBlog;
import com.tools.es.dao.SerchLog;

/**
 * @author xq
 */

public interface UserEsService {

    ResultDB saveEsUser(EsBlog esBlog);

    ResultDB getUsersByUsername(SerchLog s);

    ResultDB deleteUserById(String id);
}
