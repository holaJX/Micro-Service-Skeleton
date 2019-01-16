package com.microservice.skeleton.gateway.service.impl;

import com.microservice.skeleton.gateway.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: karp
 * Date: 2018-05-14
 * Time: 16:01
 */
@Service("permissionService")
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    /**
     * 可以做URLs匹配，规则如下
     * <p>
     * ？匹配一个字符
     * *匹配0个或多个字符
     * **匹配0个或多个目录
     * 用例如下
     * <p></p>
     */

    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    public String getCurrentUsername() {

        return SecurityContextHolder.getContext().getAuthentication().getName();

    }
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        String currentUsername = getCurrentUsername();
        System.out.println(currentUsername);
        Object principal = authentication.getPrincipal();
        String requestUrl = request.getRequestURI();
        log.info("requestUrl:{}", requestUrl);
        List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        boolean hasPermission = false;

        if (principal != null) {
            if (CollectionUtils.isEmpty(grantedAuthorityList)) {
                return hasPermission;
            }
            for (SimpleGrantedAuthority authority : grantedAuthorityList
                    ) {
                if (antPathMatcher.match(authority.getAuthority(), requestUrl)) {
                    hasPermission = true;
                    break;
                }
            }
        }

        return hasPermission;
    }
}
