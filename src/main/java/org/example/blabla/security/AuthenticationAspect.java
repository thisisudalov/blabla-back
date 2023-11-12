package org.example.blabla.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.blabla.exception.AppException;
import org.example.blabla.util.AuthUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Aspect
@Service
@RequiredArgsConstructor
public class AuthenticationAspect {

    private final SecurityService securityService;
    private final HttpServletRequest httpServletRequest;

    @Before(value = "@annotation(Authenticated)")
    public void authenticate() {
        var authHeader = httpServletRequest.getHeader("Authorization");

        if (StringUtils.isEmpty(authHeader)) {
            throw new AppException("Token was not provided", HttpStatus.UNAUTHORIZED);
        }

        var authenticatedUser = securityService.findAuthenticationInfo(authHeader);
        if (authenticatedUser == null) {
            throw new AppException("Bad credentials", HttpStatus.FORBIDDEN);
        }

        AuthUtil.saveUserToContext(authenticatedUser);
    }

}
