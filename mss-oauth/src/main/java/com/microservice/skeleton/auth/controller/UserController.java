package com.microservice.skeleton.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * <p>必须要有，做验证</p>
 * Created by karp
 * Time:10:43
 * ProjectName:Mirco-Service-Skeleton
 */
@RestController
public class UserController {
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
