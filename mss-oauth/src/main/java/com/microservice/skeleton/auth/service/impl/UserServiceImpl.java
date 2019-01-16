package com.microservice.skeleton.auth.service.impl;

import com.microservice.skeleton.auth.service.UserService;
import com.microservice.skeleton.common.vo.Result;
import com.microservice.skeleton.common.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: karp
 * Time: 10:56
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Override
    public Result<UserVo> findByUsername(String username) {
        log.info("调用{}失败", "findByUsername");
        return Result.failure(100, "调用findByUsername接口失败");
    }


}
