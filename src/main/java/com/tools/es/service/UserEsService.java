package com.tools.es.service;

import com.tools.comm.ResultDB;
import com.tools.es.dao.EsBlog;
import com.tools.es.dao.UserEsDto;
import com.tools.rabbitmq.User;
import org.springframework.stereotype.Service;

/**
 * @author xq
 */

public interface UserEsService {

    ResultDB saveEsUser(EsBlog esBlog);

    ResultDB getUsersByUsername(String id);

    ResultDB deleteUserById(String id);
}
