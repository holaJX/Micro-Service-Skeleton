package com.microservice.skeleton.auth.service;

import com.microservice.skeleton.auth.service.impl.UserServiceImpl;
import com.microservice.skeleton.common.vo.Result;
import com.microservice.skeleton.common.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by karp
 * Time:15:12
 * ProjectName:Mirco-Service-Skeleton
 */
@FeignClient(name = "mss-upms",fallback = UserServiceImpl.class)
public interface UserService {
    @GetMapping("user/findByUsername/{username}")
    Result<UserVo> findByUsername(@PathVariable("username") String username);
}
