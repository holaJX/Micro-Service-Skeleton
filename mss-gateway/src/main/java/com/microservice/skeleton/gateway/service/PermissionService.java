package com.microservice.skeleton.gateway.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: karp
 * Time: 16:01
 */
public interface PermissionService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
